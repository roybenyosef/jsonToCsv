import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class JsonReaderTest {

    @Test
    void arrayIsAutoFilled_endsWithFiveElements_ok() {
        Config config = new Config();
        config.rootElement = "data";

        JsonReader jsonReader = new JsonReader(config);
        try {
            jsonReader.readFromString("{data {}}");

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
}
