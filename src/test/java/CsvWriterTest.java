import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.Results;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class CsvWriterTest {

    List<CSVRecord> csvRecords;
    String jsonInputFilePath;

    @Before
    public void setUp() {
        try {
            getResourceFilePath();
            readJsonToCsv();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getResourceFilePath() {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("2results.json").getFile());
        jsonInputFilePath = file.getAbsolutePath();
    }

    private void readJsonToCsv() throws IOException {
        Config config = new Config();
        config.writeBomToCsv = true;

        JsonReader jsonReader = new JsonReader();
        Results jsonResults = jsonReader.read(jsonInputFilePath);

        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, config, jsonResults);
        csvWriter.write();

        CSVFormat csvFormat = csvWriter.getCsvFormat();
        try (StringReader stringReader = new StringReader(stringWriter.toString());
             CSVParser csvParser = new CSVParser(stringReader, csvFormat)) {
            csvRecords = csvParser.getRecords();
        }
    }

    @Test
    public void numberOfRecordsIncludingHeader_3_Ok() {
        assertEquals(csvRecords.size(), 3);
    }
}
