package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlashCardTest {
    private FlashCard card;

    @BeforeEach
    void runBefore() {
        card = new FlashCard("CPSC210");
        card.setFront("Midterm grade:");
        card.setBack("95");
    }

    @Test
    void testFlashCard() {
        assertEquals("CPSC210", card.getName());
    }

    @Test
    void testChangeCard() {
        assertTrue(card.getIsFront());
        card.setFront("1");
        card.setBack("2");
        assertEquals("1", card.getFront());
        assertEquals("2", card.getBack());
        card.changeCard("3");
        assertEquals("3", card.getFront());
        assertEquals("2", card.getBack());
        card.flipSide();
        assertFalse(card.getIsFront());
        card.changeCard("a");
        assertEquals("a", card.getBack());
        card.flipSide();
        card.changeCard("b");
        assertEquals("b",card.getFront());
    }
    @Test
    void testGetFront() {
        assertEquals("Midterm grade:", card.getFront());
    }

    @Test
    void testGetBack() {
        assertEquals("95", card.getBack());
    }

    @Test
    void testGetName() {
        assertEquals("CPSC210", card.getName());
    }

    @Test
    void testGetIsFront() {
        assertTrue(card.getIsFront());
        card.flipSide();
        assertFalse(card.getIsFront());
    }

    @Test
    void testFlipSide() {
        assertTrue(card.getIsFront());
        card.changeCard("front");
        assertEquals("front",card.getFront());
        card.flipSide();
        card.changeCard("back");
        assertEquals("back",card.getBack());
    }

    @Test
    void testToString() {
        assertEquals("-------------------------------------\nMidterm grade:\n-------------------------------------\n",card.toString());
    }
}