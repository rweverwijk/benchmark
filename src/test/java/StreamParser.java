import org.neo4j.graphdb.index.BatchInserterIndex;
import org.neo4j.index.impl.lucene.LuceneBatchInserterIndexProvider;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

import java.io.*;
import java.util.HashMap;

public class StreamParser {
    public static void main(String[] args) throws IOException {
        BatchInserterImpl batchInserter = new BatchInserterImpl("/home/ubuntu/benchmark/neo");
        LuceneBatchInserterIndexProvider luceneBatchInserterIndexProvider = new LuceneBatchInserterIndexProvider(batchInserter);
        HashMap<String, String> config = new HashMap<String, String>();
        config.put("type", "exact");
        BatchInserterIndex nodeIdIndex = luceneBatchInserterIndexProvider.nodeIndex("NodeIdIndex", config);

        FileInputStream stream = new FileInputStream(new File("/home/ubuntu/benchmark/data.json"));
//        InputStream stream = StreamParser.class.getResourceAsStream("/data.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        int rows = 0;
        while ((line = reader.readLine()) != null) {
            rows++;
            String[] lineArgs = line.substring(1, line.length() - 1).split(", \"");
            if (lineArgs.length == 5) {
                Long intNumber = new Long(getTokenValue(lineArgs[0]));
                boolean trueOrFalse = getTokenValue(lineArgs[1]).equals("true") ? true : false;
                String longStringAttribute = getTokenValue(lineArgs[2]);
                Long id = new Long(getTokenValue(lineArgs[3]));
                String shortStringAttribute = getTokenValue(lineArgs[4]);

                Long nodeId = batchInserter.createNode(createHashMap(intNumber, trueOrFalse, longStringAttribute, id, shortStringAttribute));
                HashMap<String, Object> idMap = new HashMap<String, Object>();
                idMap.put("id", id);

                nodeIdIndex.add(nodeId, idMap);
            } else {
                System.out.println("line not imported: " + line);
            }
            if (rows % 10000 == 0) {
                System.out.println("number of rows: " + rows);
            }
        }
        batchInserter.shutdown();
        nodeIdIndex.flush();
        luceneBatchInserterIndexProvider.shutdown();
    }

    private static HashMap<String, Object> createHashMap(Long intNumber, boolean trueOrFalse, String longStringAttribute, Long id, String shortStringAttribute) {
        HashMap<String, Object> values = new HashMap<String, Object>();
        values.put("id", id);
        values.put("longString", longStringAttribute);
        values.put("shortString", shortStringAttribute);
        values.put("intNumber", intNumber);
        values.put("trueOrFalse", trueOrFalse);
        return values;
    }

    private static String getTokenValue(String i) {
        return (i.substring(i.indexOf(":") + 1, i.length())).trim();
    }
}
