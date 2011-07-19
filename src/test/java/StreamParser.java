import org.neo4j.graphdb.index.BatchInserterIndex;
import org.neo4j.index.impl.lucene.LuceneBatchInserterIndexProvider;
import org.neo4j.kernel.impl.batchinsert.BatchInserterImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class StreamParser {
    public static void main(String[] args) throws IOException {
        BatchInserterImpl batchInserter = new BatchInserterImpl("/tmp/benchmark/neo");
        LuceneBatchInserterIndexProvider luceneBatchInserterIndexProvider = new LuceneBatchInserterIndexProvider(batchInserter);
        HashMap<String, String> config = new HashMap<String, String>();
        config.put("type", "exact");
        BatchInserterIndex nodeIdIndex = luceneBatchInserterIndexProvider.nodeIndex("NodeIdIndex", config);

        InputStream stream = StreamParser.class.getResourceAsStream("/data.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        int rows = 0;
        while ((line = reader.readLine()) != null) {
            rows++;
            StringTokenizer tokenizer = new StringTokenizer(line.substring(1, line.length() - 1), ",");
            if (tokenizer.countTokens() == 5) {
                Long intNumber = new Long(getTokenValue(tokenizer.nextToken()));
                boolean trueOrFalse = getTokenValue(tokenizer.nextToken()).equals("true") ? true : false;
                String longStringAttribute = getTokenValue(tokenizer.nextToken());
                Long id = new Long(getTokenValue(tokenizer.nextToken()));
                String shortStringAttribute = getTokenValue(tokenizer.nextToken());
                long nodeId = batchInserter.createNode(createHashMap(intNumber, trueOrFalse, longStringAttribute, id, shortStringAttribute));
                HashMap<String, Object> idMap = new HashMap<String, Object>();
                idMap.put("id", id);

                nodeIdIndex.add(nodeId, idMap);
            }
            if (rows % 1000 == 0) {
                System.out.println("number of rows: " + rows);
            }
        }
        batchInserter.shutdown();
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
