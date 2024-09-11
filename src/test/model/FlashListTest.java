package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FlashListTest {
    private FlashList list;
    private FlashCard card1;
    private FlashCard card2;
    private FlashCard card3;
    @BeforeEach
    void runBefore() {
        list = new FlashList("CPSC210");
        card1 = new FlashCard("homework");
        card2 = new FlashCard("quiz");
        card1.setFront("1");
        card1.setBack("11");
        card2.setFront("2");
        card2.setBack("22");
    }

    @Test
    void testFlashList() {
        FlashList emptyList = new FlashList("1");
        assertEquals(0, emptyList.getSize());

    }

    @Test
    void testAddFlashCard() {
        list.addFlashCard(card1);
        assertEquals(1,list.getSize());
    }

    @Test
    void testDeleteCard() {
        list.addFlashCard(card1);
        assertEquals(1,list.getSize());
        list.deleteCard(0);
        assertEquals(0,list.getSize());
    }

    @Test
    void testGetFlashCard() {
        list.addFlashCard(card1);
        assertEquals(card1, list.getFlashCard(0));
    }

    @Test
    void testGetSize() {
        list.addFlashCard(card1);
        list.addFlashCard(card2);
        assertEquals(2, list.getSize());
    }

    @Test
    void testGetName() {
        assertEquals("CPSC210", list.getName());
    }

    @Test
    void testToString() {
        list.addFlashCard(card1);
        String output = "1. homework\n-------------------------------------\n1\n-------------------------------------\n";
        assertEquals(output, list.toString());
    }

    @Test
    void testGetCards() {
        list.addFlashCard(card1);
        list.addFlashCard(card2);
       assertEquals(2, list.getCards().size());
       assertTrue(list.getCards().contains(card1));
        assertTrue(list.getCards().contains(card2));

       list.setName("a");
       assertEquals("a", list.getName());
    }


}
