import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.CsvData;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class JsonReaderTest {

    @Test
    public void jsonReader_fieldOnlyExistOnlyInMiddleElements_nullFieldsAreAddedToMissingElements() {
        try {
            Config config = new Config();
            config.rootElement = "data";
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString(
                    "{\"data\" :" +
                            "[{\"first\" : \"john\", \"last\" : \"wick\", \"pet\" : \"dog\"}, " +
                            " {\"last\" : \"wick\", \"pet\" : \"dog\"}, " +
                            " {\"first\" : \"john\", \"last\" : \"wick\", \"pet\" : \"dog\"}]}");

            CsvData csvData = jsonReader.getCsvData();
            assertTrue(csvData.getCsvRows().stream().allMatch(x -> x.size() == 3));
            assertEquals("null", csvData.getCsvRows().get(1).get(0));

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void jsonReader_fieldOnlyExistOnlyInLaterElements_nullFieldsAreAddedToPreviousElements() {
        try {
            Config config = new Config();
            config.rootElement = "data";
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString(
                    "{\"data\" :" +
                            "[{\"last\" : \"wick\", \"pet\" : \"dog\"}, " +
                            " {\"last\" : \"wick\", \"pet\" : \"dog\"}, " +
                            " {\"first\" : \"john\", \"last\" : \"wick\", \"pet\" : \"dog\"}]}");

            CsvData csvData = jsonReader.getCsvData();
            assertTrue(csvData.getCsvRows().stream().allMatch(x -> x.size() == 3));
            assertEquals("null", csvData.getCsvRows().get(0).get(0));
            assertEquals("null", csvData.getCsvRows().get(1).get(0));
            assertEquals("john", csvData.getCsvRows().get(2).get(0));

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void jsonReader_fieldOnlyExistOnlyInSecondElements_nullFieldsAreAddedToPreviousElements() {
        try {
            Config config = new Config();
            config.rootElement = "data";
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString(
                    "{\"data\" :" +
                            "[{\"last\" : \"wick\", \"pet\" : \"dog\"}, " +
                            " {\"first\" : \"john\", \"last\" : \"wick\", \"pet\" : \"dog\"}]}");

            CsvData csvData = jsonReader.getCsvData();
            assertTrue(csvData.getCsvRows().stream().allMatch(x -> x.size() == 3));
            assertEquals("null", csvData.getCsvRows().get(0).get(0));
            assertEquals("john", csvData.getCsvRows().get(1).get(0));

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void jsonReader_fieldOnlyExistOnlyInPreviousElements_nullFieldsAreAddedToLaterElements() {
        try {
            Config config = new Config();
            config.rootElement = "data";
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString(
                    "{\"data\" :" +
                            "[{\"first\" : \"john\", \"last\" : \"wick\", \"pet\" : \"dog\"}, " +
                            " {\"last\" : \"wick\", \"pet\" : \"dog\"}]}");

            CsvData csvData = jsonReader.getCsvData();
            assertTrue(csvData.getCsvRows().stream().allMatch(x -> x.size() == 3));
            assertEquals("john", csvData.getCsvRows().get(0).get(0));
            assertEquals("null", csvData.getCsvRows().get(1).get(0));

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void jsonReader_arrayIsAutoFilledWhenFirstArrayIsFiveElements_secondArrayIsFilledWithEmptyStrings() {
        try {
            Config config = new Config();
            config.rootElement = "data";
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString("{\"data\": [{\"array\": [\"first-1\",\"second-1\",\"third-1\",\"fourth-1\",\"fifth-1\"]},{\"array\": [\"first-2\", \"second-2\", \"third-2\"]}]}");
            CsvData csvData = jsonReader.getCsvData();
            assertEquals(5, csvData.getCsvHeaders().size());
            assertEquals(2, csvData.getCsvRows().size());
            assertEquals(2 * 5, csvData.getCsvRows().stream().mapToInt(i -> i.size()).sum());

            assertEquals("first-2", csvData.getCsvRows().get(1).get(0));
            assertEquals("second-2", csvData.getCsvRows().get(1).get(1));
            assertEquals("third-2", csvData.getCsvRows().get(1).get(2));
            assertEquals("", csvData.getCsvRows().get(1).get(3));
            assertEquals("", csvData.getCsvRows().get(1).get(4));
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void jsonReader_arrayIsAutoFilledWhenSecondArrayIsFiveElements_firstArrayIsFilledWithEmptyStrings() {

        try {
            Config config = new Config();
            config.rootElement = "data";
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString("{\"data\": [{\"array\": [\"first-1\",\"second-1\",\"third-1\"]},{\"array\": [\"first-2\", \"second-2\", \"third-2\",\"fourth-2\",\"fifth-2\"]}]}");
            CsvData csvData = jsonReader.getCsvData();
            assertEquals(5, csvData.getCsvHeaders().size());
            assertEquals(2, csvData.getCsvRows().size());
            assertEquals(2 * 5, csvData.getCsvRows().stream().mapToInt(i -> i.size()).sum());

            assertEquals("first-1", csvData.getCsvRows().get(0).get(0));
            assertEquals("second-1", csvData.getCsvRows().get(0).get(1));
            assertEquals("third-1", csvData.getCsvRows().get(0).get(2));
            assertEquals("", csvData.getCsvRows().get(0).get(3));
            assertEquals("", csvData.getCsvRows().get(0).get(4));
        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void jsonReader_splitTwoColumnsByComma_mosheColumnIsSplit() {
        try {
            Config config = new Config();
            config.rootElement = "data";
            config.columnsToSplit.put("moshe", ",");
            JsonReader jsonReader = new JsonReader(config);

            jsonReader.readFromString("{\"data\": [ {\"moshe\" : \"blabla11,blabla12\"}, {\"moshe\" : \"blabla21,blabla22\"}]}");
            CsvData csvData = jsonReader.getCsvData();

            assertEquals(2, csvData.getCsvHeaders().size());
            assertEquals("moshe0", csvData.getCsvHeaders().get(0));
            assertEquals("moshe1", csvData.getCsvHeaders().get(1));

            assertEquals("blabla11", csvData.getCsvRows().get(0).get(0));
            assertEquals("blabla12", csvData.getCsvRows().get(0).get(1));
            assertEquals("blabla21", csvData.getCsvRows().get(1).get(0));
            assertEquals("blabla22", csvData.getCsvRows().get(1).get(1));

        } catch (IOException e) {
            Assert.fail(e.getMessage());
        }
    }
}
