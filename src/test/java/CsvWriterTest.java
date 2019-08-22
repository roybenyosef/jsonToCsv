import com.jsonToCsv.IO.CsvWriter;
import com.jsonToCsv.IO.JsonReader;
import com.jsonToCsv.JsonToCsvConsts;
import com.jsonToCsv.config.Config;
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
import java.util.Map;

import static junit.framework.TestCase.*;

public class CsvWriterTest {

    private List<CSVRecord> csvRecords;

    private Map<String, String> headers;
    private Map<String, String> valuesRow;
    private String jsonInputFilePath;
    private Config config;

    @Before
    public void setUp() {
        try {
            config = new Config();
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
        config.rootElement = "data";
        config.columnsToSplit.put("data_time", " ");
        config.columnsPrefix.put("extra_url", "https://stips.co.il/");

        JsonReader jsonReader = new JsonReader(config);
        jsonReader.readFromFile(jsonInputFilePath);

        StringWriter stringWriter = new StringWriter();
        CsvWriter csvWriter = new CsvWriter(stringWriter, config, jsonReader.getCsvData());
        csvWriter.write();

        CSVFormat csvFormat = csvWriter.getCsvFormat();
        try (StringReader stringReader = new StringReader(stringWriter.toString());
             CSVParser csvParser = new CSVParser(stringReader, csvFormat)) {
            csvRecords = csvParser.getRecords();
            headers = csvRecords.get(0).toMap();
            valuesRow = csvRecords.get(1).toMap();
        }
    }

    private void assertColumn(String header, String value) {
        assertTrue("header not found", valuesRow.containsKey(header));
        assertEquals("value not found", value, valuesRow.get(header));
    }

    @Test
    public void numberOfRecordsIncludingHeader_3_Ok() {
        assertEquals(csvRecords.size(), 3);
    }

    @Test
    public void objType_isAskWithBom() {
        String fileBytesHeader = config.writeBomToCsv == true ? JsonToCsvConsts.UTF8_BOM : "";
        int indexOfFirstHeader = 0;
        assertEquals(fileBytesHeader + "objType", csvRecords.get(0).get(indexOfFirstHeader));
        assertEquals("ask", csvRecords.get(1).get(indexOfFirstHeader));
    }

    @Test
    public void id_is7324927() {
        assertColumn("data_id", "7324927");
    }

    @Test
    public void q_isExpectedText() {
        assertColumn( "data_q", "text of a question");
    }

    @Test
    public void text_content_isExpectedText() {
        assertColumn( "data_text_content", "content text 1");
    }

    @Test
    public void name_isExpectedText() {
        assertColumn( "data_name", "anonymous");
    }

    @Test
    public void tag1_isExpectedText() {
        assertColumn( "data_tagslist0", "writing");
    }

    @Test
    public void tag2_isExpectedText() {
        assertColumn( "data_tagslist1", "review");
    }

    @Test
    public void tag3_isExpectedText() {
        assertColumn( "data_tagslist2", "ban");
    }

    @Test
    public void tag4_isExpectedText() {
        assertColumn( "data_tagslist3", "advise");
    }

    @Test
    public void tag5_isExpectedText() {
        assertColumn( "data_tagslist4", "consulting");
    }

    @Test
    public void date_isExpectedDate() {
        assertColumn( "data_time0", "2019/04/18");
    }

    @Test
    public void time_isExpecetdTime() {
        assertColumn( "data_time1", "13:06:53");
    }

    @Test
    public void archive_isFalse() {
        assertColumn("data_archive", "false");
    }

    @Test
    public void anonflg_isTrue() {
        assertColumn("data_anonflg", "true");
    }

    @Test
    public void super_anonflg_isFalse() {
        assertColumn("data_super_anonflg", "false");
    }

    @Test
    public void userid_isZero() {
        assertColumn("data_userid", "0");
    }

    @Test
    public void photo_isExpectedText() {
        assertColumn("data_photo", "unsplash:hjwKMkehBco");
    }

    @Test
    public void ask_type_isEleven() {
        assertColumn("data_ask_type", "11");
    }

    @Test
    public void photo_type_isOne() {
        assertColumn("data_photo_type", "1");
    }

    @Test
    public void bit_settings_isZero() {
        assertColumn("data_bit_settings", "0");
    }

    @Test
    public void q_link_isEmpty() {
        assertColumn("data_q_link", "");
    }

    @Test
    public void revision_flg_isFalse() {
        assertColumn("data_revision_flg", "false");
    }

    @Test
    public void city_id_isZero() {
        assertColumn("data_city_id", "0");
    }

    @Test
    public void answer_count_isTen() {
        assertColumn("data_answer_count", "10");
    }

    @Test
    public void pin_count_isOne() {
        assertColumn("data_pin_count", "1");
    }

    @Test
    public void user_pinned_isMinusOne() {
        assertColumn("data_user_pinned", "-1");
    }

    @Test
    public void safefilter_isFalse() {
        assertColumn("data_safefilter", "false");
    }

    @Test
    public void channel_id_isTen() {
        assertColumn("data_channel_id", "10");
    }

    @Test
    public void photo_id_isNull() {
        assertColumn("extra_photo_id", null);
    }

    @Test
    public void url_small_expectedLink() {
        assertColumn("extra_content_image_url_small", "https://images.unsplash.com/photo-1473186505569-9c61870c11f9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjE4OTkzfQ&s=a618b4c44bfe3936866e6d6055db192c");
    }

    @Test
    public void url_medium_expectedLink() {
        assertColumn("extra_content_image_url_medium", "https://images.unsplash.com/photo-1473186505569-9c61870c11f9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=680&fit=crop&ixid=eyJhcHBfaWQiOjE4OTkzfQ&s=6582605df1d99d5dc763495971d3b515");
    }


    @Test
    public void creator_name_expectedName() {
        assertColumn("extra_content_image_creator_name", "lvaro Serrano");
    }

    @Test
    public void creator_username_expectedUsername() {
        assertColumn("extra_content_image_creator_username", "alvaroserrano");
    }

    @Test
    public void hebrew_time_Is19Minutes() {
        assertColumn("extra_hebrew_time", "19 min");
    }

    @Test
    public void url_IsExpectedLink() {
        assertColumn("extra_url", config.columnsPrefix.get("extra_url") + "ask/7324927/%D7%90%D7%A0%D7%99-%D7%9E%D7%A8%D7%92%D7%99%D7%A9%D7%94-%D7%A9%D7%A2%D7%95%D7%A9%D7%99%D7%9D-%D7%A2%D7%9C%D7%99%D7%99-%D7%97%D7%A8%D7%9D");
    }

    @Test
    public void url_title_IsExpectedText() {
        assertColumn("extra_url_title", "this is the title of the url");
    }

    @Test
    public void nickname_IsAnonymous() {
        assertColumn("extra_item_profile_nickname", "anonymous");
    }

    @Test
    public void anonflg_IsTrue() {
        assertColumn("extra_item_profile_anonflg", "true");
    }

    @Test
    public void active_IsTrue() {
        assertColumn("extra_item_profile_active", "true");
    }

    @Test
    public void meta_active_IsTrue() {
        assertColumn("meta_active", "true");
    }

    @Test
    public void meta_permissions_edit_IsTrue() {
        assertColumn("meta_permissions_edit", "true");
    }

    @Test
    public void meta_permissions_delete_IsTrue() {
        assertColumn("meta_permissions_delete", "true");
    }

    @Test
    public void meta_permissions_deleteWithoutMsg_IFalse() {
        assertColumn("meta_permissions_deleteWithoutMsg", "false");
    }

    @Test
    public void meta_permissions_ban_IsTrue() {
        assertColumn("meta_permissions_ban", "true");
    }

    @Test
    public void meta_permissions_report_IsTrue() {
        assertColumn("meta_permissions_report", "true");
    }

    @Test
    public void meta_permissions_showAdminMsgs_IsTrue() {
        assertColumn("meta_permissions_showAdminMsgs", "true");
    }

    @Test
    public void meta_permissions_itemOwner_IsFalse() {
        assertColumn("meta_permissions_itemOwner", "false");
    }

    @Test
    public void meta_permissions_restore_IsTrue() {
        assertColumn("meta_permissions_restore", "true");
    }

    @Test
    public void meta_permissions_removeFromChannel_IsTrue() {
        assertColumn("meta_permissions_removeFromChannel", "true");
    }

    @Test
    public void userid_isExpectedValue() {
        assertColumn("meta_moreUserDetails_userid", "229390");
    }

    @Test
    public void ip_isExpectedValue() {
        assertColumn("meta_moreUserDetails_ip", "176.230.34.156");
    }

    @Test
    public void tagsList_lessThanFiveTags_isAutoFilled() {
        assertFalse(csvRecords.get(2).get(5).isEmpty());
        assertFalse(csvRecords.get(2).get(6).isEmpty());
        assertFalse(csvRecords.get(2).get(7).isEmpty());
        assertEquals("", csvRecords.get(2).get(8));
        assertEquals("", csvRecords.get(2).get(9));
    }
}
