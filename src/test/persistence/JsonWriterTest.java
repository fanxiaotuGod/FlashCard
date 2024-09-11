package persistence;

import model.FlashCard;
import model.Folder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import model.FlashList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Folder fd = new Folder("My folder");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            Folder fd = new Folder("My folder");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(fd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            fd = reader.read();
            assertEquals("My folder", fd.getName());
            assertEquals(0, fd.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Folder fd = new Folder("My folder");
            FlashList list = new FlashList("CPSC210");
            FlashCard card = new FlashCard("midterm grade");
            list.addFlashCard(card);
            fd.addList(list);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(fd);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            fd = reader.read();
            assertEquals("My folder", fd.getName());
            List<FlashList> lists = fd.getLists();
            assertEquals(1, lists.size());
            assertEquals("CPSC210",lists.get(0).getName());
            assertEquals("midterm grade",lists.get(0).getFlashCard(0).getName());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
