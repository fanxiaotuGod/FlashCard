package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FolderTest {
    private FlashList list1;
    private FlashList list2;
    private FlashList list3;
    private Folder folder;
    @BeforeEach
    void runBefore() {
        list1 = new FlashList("1");
        list2 = new FlashList("2");
        list3 = new FlashList("3");
        folder = new Folder("name");
    }

    @Test
    void testDeleteList() {
        assertTrue(folder.isEmpty());
        folder.addList(list1);
        folder.addList(list2);
        assertEquals(2, folder.getLists().size());
        folder.deleteList(0);
        assertEquals(list2, folder.getList(0));

    }






}
