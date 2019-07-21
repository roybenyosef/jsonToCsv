import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.JsonToCsvConsts;
import com.jsonToCsv.config.Config;
import com.jsonToCsv.dataObjects.Results;
import junit.framework.Assert;
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
import static junit.framework.TestCase.assertTrue;

public class CsvWriterTest {

    List<CSVRecord> csvRecords;
    String jsonInputFilePath;
    Results jsonResults;
    Config config = new Config();

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
        config.writeBomToCsv = true;

        JsonReader jsonReader = new JsonReader();
        jsonResults = jsonReader.read(jsonInputFilePath);

        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, config, jsonResults);
        csvWriter.write();

        CSVFormat csvFormat = csvWriter.getCsvFormat();
        try (StringReader stringReader = new StringReader(stringWriter.toString());
             CSVParser csvParser = new CSVParser(stringReader, csvFormat)) {
            csvRecords = csvParser.getRecords();
        }
    }

    private void assertColumn(int columnIndex, String header, String value) {
        assertEquals(header, csvRecords.get(0).get(columnIndex));
        assertEquals(value, csvRecords.get(1).get(columnIndex));
    }

    @Test
    public void numberOfRecordsIncludingHeader_3_Ok() {
        assertEquals(csvRecords.size(), 3);
    }

    @Test
    public void objType_isAsk_equals() {
        String fileBytesHeader = config.writeBomToCsv ? JsonToCsvConsts.UTF8_BOM : "";
        assertColumn(0, fileBytesHeader + "objType", "ask");
    }

    @Test
    public void id_is7324927_equals() {
        assertColumn(1, "data_id", "7324927");
    }

    @Test
    public void q_isExpectedText_equals() {
        assertColumn(2, "data_q", "אני מרגישה שעושים עליי חרם באתר! (בבקשה תקראו זה חשוב לי)");
    }

    @Test
    public void text_content_isExpectedText_equals() {
        assertColumn(3, "data_text_content", "אני יודעת שזה רק אתר אינטרנט והכל, אבל אני פה כבר 3 שנים, אני משקיעה בתשובות שלי ולא מצפה לקבל כלום בתמורה, אבל שמתי לב שכמעט כולם מדלגים עליי בפרחים, גם אם אני עונה ראשונה, תמיד יתנו לאלה שאחרי ופשוט מדלגים עליי (אני לא עונה בשביל פרחים, אבל השקעתי מזמני כל כך הרבה וכל מה שאני מקבלת זה קללות בקיר התודות)  כדאי לפרוש? אני אוהבת לעזור אבל קשה לי לראות את כל הקללות שכתבו לי");
    }

    @Test
    public void name_isExpectedText_equals() {
        assertColumn(4, "data_name", "אנונימית");
    }

    @Test
    public void tag1_isExpectedText_equals() {
        assertColumn(5, "data_tagslist_item1", "כתיבה");
    }

    @Test
    public void tag2_isExpectedText_equals() {
        assertColumn(6, "data_tagslist_item2", "ביקורת");
    }

    @Test
    public void tag3_isExpectedText_equals() {
        assertColumn(7, "data_tagslist_item3", "חרם");
    }

    @Test
    public void tag4_isExpectedText_equals() {
        assertColumn(8, "data_tagslist_item4", "עצות");
    }

    @Test
    public void tag5_isExpectedText_equals() {
        assertColumn(9, "data_tagslist_item5", "חוות דעת");
    }

    @Test
    public void time_isExpecetdTime_equals() {
        assertColumn(10, "time", "13:06:53");
    }

    @Test
    public void date_isExpectedDate_equals() {
        assertColumn(11, "date", "2019/04/18");
    }

    @Test
    public void archive_isFalse_equals() {
        assertColumn(12,"archive", "false");
    }

    @Test
    public void anonflg_isTrue_equals() {
        assertColumn(13,"anonflg", "true");
    }

    @Test
    public void super_anonflg_isFalse_equals() {
        assertColumn(14,"super_anonflg", "false");
    }

    @Test
    public void userid_isZero_equals() {
        assertColumn(15,"userid", "0");
    }

    @Test
    public void photo_isExpectedText_equals() {
        assertColumn(16,"photo", "unsplash:hjwKMkehBco");
    }

    @Test
    public void ask_type_isEleven_equals() {
        assertColumn(17,"ask_type", "11");
    }

    @Test
    public void photo_type_isOne_equals() {
        assertColumn(18,"photo_type", "1");
    }

    @Test
    public void bit_settings_isZero_equals() {
        assertColumn(19,"bit_settings", "0");
    }

    @Test
    public void q_link_isEmpty_equals() {
        assertColumn(20,"q_link", "");
    }

    @Test
    public void revision_flg_isFalse_equals() {
        assertColumn(21,"revision_flg", "false");
    }

    @Test
    public void city_id_isZero_equals() {
        assertColumn(22,"city_id", "0");
    }

    @Test
    public void answer_count_isTen_equals() {
        assertColumn(23,"answer_count", "10");
    }

    @Test
    public void pin_count_isOne_equals() {
        assertColumn(24,"pin_count", "1");
    }

    @Test
    public void user_pinned_isMinusOne_equals() {
        assertColumn(25,"user_pinned", "-1");
    }

    @Test
    public void safefilter_isFalse_equals() {
        assertColumn(26,"safefilter", "false");
    }

    @Test
    public void channel_id_isTen_equals() {
        assertColumn(27,"channel_id", "10");
    }



}
