import com.xebia.xke.benchmark.model.SimpleFlut;
import com.xebia.xke.benchmark.service.BenchmarkService;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class StreamParser {
    public static void main(String[] args) throws IOException {
        EmbeddedGraphDatabase database = new EmbeddedGraphDatabase("/tmp/benchmark/neo");
        BenchmarkService service = new BenchmarkService(database);

        InputStream stream = StreamParser.class.getResourceAsStream("/data.json");

        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            StringTokenizer tokenizer = new StringTokenizer(line.substring(1, line.length() - 1), ",");
            if (tokenizer.countTokens() == 5) {
                BigInteger intNumber = new BigInteger(getTokenValue(tokenizer.nextToken()));
                boolean trueOrFalse = getTokenValue(tokenizer.nextToken()).equals("true") ? true : false;
                String longStringAttribute = getTokenValue(tokenizer.nextToken());
                BigInteger id = new BigInteger(getTokenValue(tokenizer.nextToken()));
                String shortStringAttribute = getTokenValue(tokenizer.nextToken());
                SimpleFlut simpleFlut = new SimpleFlut(id, shortStringAttribute, longStringAttribute, intNumber, trueOrFalse);
                System.out.println(simpleFlut);
                service.saveFlut(simpleFlut);
            }
        }
    }

    private static String getTokenValue(String i) {
        return (i.substring(i.indexOf(":") + 1, i.length())).trim();
    }
}
