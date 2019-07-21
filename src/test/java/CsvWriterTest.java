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

import static junit.framework.TestCase.*;

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
    public void objType_isAsk() {
        String fileBytesHeader = config.writeBomToCsv ? JsonToCsvConsts.UTF8_BOM : "";
        assertColumn(0, fileBytesHeader + "objType", "ask");
    }

    @Test
    public void id_is7324927() {
        assertColumn(1, "data_id", "7324927");
    }

    @Test
    public void q_isExpectedText() {
        assertColumn(2, "data_q", "text of a question");
    }

    @Test
    public void text_content_isExpectedText() {
        assertColumn(3, "data_text_content", "content text 1");
    }

    @Test
    public void name_isExpectedText() {
        assertColumn(4, "data_name", "anonymous");
    }

    @Test
    public void tag1_isExpectedText() {
        assertColumn(5, "data_tagslist_item1", "writing");
    }

    @Test
    public void tag2_isExpectedText() {
        assertColumn(6, "data_tagslist_item2", "review");
    }

    @Test
    public void tag3_isExpectedText() {
        assertColumn(7, "data_tagslist_item3", "ban");
    }

    @Test
    public void tag4_isExpectedText() {
        assertColumn(8, "data_tagslist_item4", "advise");
    }

    @Test
    public void tag5_isExpectedText() {
        assertColumn(9, "data_tagslist_item5", "consulting");
    }

    @Test
    public void time_isExpecetdTime() {
        assertColumn(10, "time", "13:06:53");
    }

    @Test
    public void date_isExpectedDate() {
        assertColumn(11, "date", "2019/04/18");
    }

    @Test
    public void archive_isFalse() {
        assertColumn(12,"archive", "false");
    }

    @Test
    public void anonflg_isTrue() {
        assertColumn(13,"anonflg", "true");
    }

    @Test
    public void super_anonflg_isFalse() {
        assertColumn(14,"super_anonflg", "false");
    }

    @Test
    public void userid_isZero() {
        assertColumn(15,"userid", "0");
    }

    @Test
    public void photo_isExpectedText() {
        assertColumn(16,"photo", "unsplash:hjwKMkehBco");
    }

    @Test
    public void ask_type_isEleven() {
        assertColumn(17,"ask_type", "11");
    }

    @Test
    public void photo_type_isOne() {
        assertColumn(18,"photo_type", "1");
    }

    @Test
    public void bit_settings_isZero() {
        assertColumn(19,"bit_settings", "0");
    }

    @Test
    public void q_link_isEmpty() {
        assertColumn(20,"q_link", "");
    }

    @Test
    public void revision_flg_isFalse() {
        assertColumn(21,"revision_flg", "false");
    }

    @Test
    public void city_id_isZero() {
        assertColumn(22,"city_id", "0");
    }

    @Test
    public void answer_count_isTen() {
        assertColumn(23,"answer_count", "10");
    }

    @Test
    public void pin_count_isOne() {
        assertColumn(24,"pin_count", "1");
    }

    @Test
    public void user_pinned_isMinusOne() {
        assertColumn(25,"user_pinned", "-1");
    }

    @Test
    public void safefilter_isFalse() {
        assertColumn(26,"safefilter", "false");
    }

    @Test
    public void channel_id_isTen() {
        assertColumn(27,"channel_id", "10");
    }

    @Test
    public void photo_id_isNull() {
        assertColumn(28,"extra_photo_id", null);
    }

    @Test
    public void url_small_expectedLink() {
        assertColumn(29,"extra_content_image_url_small", "https://images.unsplash.com/photo-1473186505569-9c61870c11f9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjE4OTkzfQ&s=a618b4c44bfe3936866e6d6055db192c");
    }

    @Test
    public void url_medium_expectedLink() {
        assertColumn(30,"extra_content_image_url_medium", "https://images.unsplash.com/photo-1473186505569-9c61870c11f9?ixlib=rb-0.3.5&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=680&fit=crop&ixid=eyJhcHBfaWQiOjE4OTkzfQ&s=6582605df1d99d5dc763495971d3b515");
    }


    @Test
    public void creator_name_expectedName() {
        assertColumn(31,"extra_content_image_creator_name", "lvaro Serrano");
    }

    @Test
    public void creator_username_expectedUsername() {
        assertColumn(32,"extra_content_image_creator_username", "alvaroserrano");
    }

    @Test
    public void hebrew_time_Is19Minutes() {
        assertColumn(33,"extra_hebrew_time", "19 min");
    }

    @Test
    public void url_IsExpectedLink() {
        assertColumn(34,"extra_url", "ask/7324927/%D7%90%D7%A0%D7%99-%D7%9E%D7%A8%D7%92%D7%99%D7%A9%D7%94-%D7%A9%D7%A2%D7%95%D7%A9%D7%99%D7%9D-%D7%A2%D7%9C%D7%99%D7%99-%D7%97%D7%A8%D7%9D");
    }

    @Test
    public void url_title_IsExpectedText() {
        assertColumn(35,"extra_url_title", "this is the title of the url");
    }

    @Test
    public void nickname_IsAnonymous() {
        assertColumn(36,"extra_item_profile_nickname", "anonymous");
    }

    @Test
    public void anonflg_IsTrue() {
        assertColumn(37,"extra_item_profile_anonflg", "true");
    }

    @Test
    public void active_IsTrue() {
        assertColumn(38,"extra_item_profile_active", "true");
    }

    @Test
    public void meta_active_IsTrue() {
        assertColumn(39,"meta_active", "true");
    }

    @Test
    public void meta_permissions_edit_IsTrue() {
        assertColumn(40,"meta_permissions_edit", "true");
    }

    @Test
    public void meta_permissions_delete_IsTrue() {
        assertColumn(41,"meta_permissions_delete", "true");
    }

    @Test
    public void meta_permissions_deleteWithoutMsg_IFalse() {
        assertColumn(42,"meta_permissions_deleteWithoutMsg", "false");
    }

    @Test
    public void meta_permissions_ban_IsTrue() {
        assertColumn(43,"meta_permissions_ban", "true");
    }

    @Test
    public void meta_permissions_report_IsTrue() {
        assertColumn(44,"meta_permissions_report", "true");
    }

    @Test
    public void meta_permissions_showAdminMsgs_IsTrue() {
        assertColumn(45,"meta_permissions_showAdminMsgs", "true");
    }

    @Test
    public void meta_permissions_itemOwner_IsFalse() {
        assertColumn(46,"meta_permissions_itemOwner", "false");
    }

    @Test
    public void meta_permissions_restore_IsTrue() {
        assertColumn(47,"meta_permissions_restore", "true");
    }

    @Test
    public void meta_permissions_removeFromChannel_IsTrue() {
        assertColumn(48,"meta_permissions_removeFromChannel", "true");
    }

    @Test
    public void userid_isExpectedValue() {
        assertColumn(49,"meta_moreUserDetails_userid", "229390");
    }

    @Test
    public void ip_isExpectedValue() {
        assertColumn(50,"meta_moreUserDetails_ip", "176.230.34.156");
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
