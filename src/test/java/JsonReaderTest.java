import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.CsvData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static junit.framework.TestCase.assertEquals;

public class JsonReaderTest {

    @Test
    public void arrayIsAutoFilled_firstArrayIsFiveElements_FiveEleventsInAllRowsSecondFilledWithEmpty() {
        Config config = new Config();
        config.rootElement = "data";

        JsonReader jsonReader = new JsonReader(config);
        try {
            jsonReader.readFromString("{\"data\": [{\"array\": [\"first-1\",\"second-1\",\"third-1\",\"fourth-1\",\"fifth-1\"]},{\"array\": [\"first-2\", \"second-2\", \"third-2\"]}]}");
            CsvData csvData = jsonReader.getCsvData();
            assertEquals(5, csvData.getCsvHeaders().size());
            assertEquals(2, csvData.getCsvRows().size());
            assertEquals(2 * 5, csvData.getCsvRows().stream().mapToInt(i -> i.size()).sum());

            assertEquals("first-2", csvData.getCsvRows().get(1).get(0));
            assertEquals("second-2", csvData.getCsvRows().get(1).get(1));
            assertEquals("third-2", csvData.getCsvRows().get(1).get(2));
            assertEquals("", csvData.getCsvRows().get(1).get(4));
            assertEquals("", csvData.getCsvRows().get(1).get(5));
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void arrayIsAutoFilled_secondArrayIsFiveElements_FiveEleventsInAllRowsFirstFilledWithEmpty() {
        Config config = new Config();
        config.rootElement = "data";

        JsonReader jsonReader = new JsonReader(config);
        try {
            jsonReader.readFromString("{\"data\": [{\"array\": [\"first-1\",\"second-1\",\"third-1\",\"fourth-1\",\"fifth-1\"]},{\"array\": [\"first-2\", \"second-2\", \"third-2\"]}]}");
            CsvData csvData = jsonReader.getCsvData();
            assertEquals(5, csvData.getCsvHeaders().size());
            assertEquals(2, csvData.getCsvRows().size());
            assertEquals(2 * 5, csvData.getCsvRows().stream().mapToInt(i -> i.size()).sum());

            assertEquals("first-2", csvData.getCsvRows().get(0).get(0));
            assertEquals("second-2", csvData.getCsvRows().get(0).get(1));
            assertEquals("third-2", csvData.getCsvRows().get(0).get(2));
            assertEquals("", csvData.getCsvRows().get(0).get(4));
            assertEquals("", csvData.getCsvRows().get(0).get(5));
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }


}
