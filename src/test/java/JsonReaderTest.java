import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.config.Config;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;

public class JsonReaderTest {

    @Test
    void arrayIsAutoFilled_endsWithFiveElements_ok() {
        Config config = new Config();
        config.rootElement = "data";

        JsonReader jsonReader = new JsonReader(config);
        jsonReader.read(jsonInputFilePath);

        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, config, jsonReader.getCsvData());
        csvWriter.write();

        CSVFormat csvFormat = csvWriter.getCsvFormat();
        try (StringReader stringReader = new StringReader(stringWriter.toString());
             CSVParser csvParser = new CSVParser(stringReader, csvFormat)) {
            csvRecords = csvParser.getRecords();
        }
    }
}
