package persistence;

import model.FlashList;
import model.Folder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Folder fd = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFolder() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            Folder fd = reader.read();
            assertEquals("My folder", fd.getName());
            assertEquals(0, fd.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFolder() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            Folder fd = reader.read();
            assertEquals("Stephen's folder", fd.getName());
            List<FlashList> lists = fd.getLists();
            assertEquals(1, lists.size());
            assertEquals("partI:92",lists.get(0).getFlashCard(0).getFront());
            assertEquals("partII:80",lists.get(0).getFlashCard(0).getBack());
            assertEquals("midterm grade",lists.get(0).getFlashCard(0).getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

