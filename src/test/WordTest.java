package test;

import model.Word;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WordTest {


    @Test
    public void testConstructor() {
        Word w = new Word("01234");
        assertEquals(5, w.getLength());
        assertTrue(w.getDigits().size() == w.getLength());
        assertTrue(w.getDigits().contains(0));
        assertTrue(w.getDigits().contains(1));
        assertTrue(w.getDigits().contains(2));
        assertTrue(w.getDigits().contains(3));
        assertTrue(w.getDigits().contains(4));
    }


    @Test
    public void testHammingDistance() {
        Word w1 = new Word("01234");
        Word w2 = new Word("43210");
        Word w3 = new Word("32111");
        Word w4 = new Word("14444");
        assertEquals(0, w1.hammingDistance(w1));
        assertEquals(4, w1.hammingDistance(w2));
        assertEquals(5, w1.hammingDistance(w3));
        assertEquals(4, w1.hammingDistance(w4));
        assertEquals(4, w2.hammingDistance(w3));
        assertEquals(5, w2.hammingDistance(w4));
        assertEquals(5, w3.hammingDistance(w4));
    }


    @Test
    public void testAddDigit() {
        Word w = new Word("01234");
        w.addDigit(5);
        assertEquals(6, w.getDigits().size());
        assertTrue(w.getDigits().contains(5));
    }


    @Test
    public void testWeight() {
        Word w1 = new Word("0000");
        Word w2 = new Word("0010");
        Word w3 = new Word("0101");
        Word w4 = new Word("1110");
        Word w5 = new Word("1111");
        assertEquals(0, w1.weight());
        assertEquals(1, w2.weight());
        assertEquals(2, w3.weight());
        assertEquals(3, w4.weight());
        assertEquals(4, w5.weight());
    }

    @Test
    public void testIsZeroWord() {
        Word w1 = new Word("0000");
        Word w2 = new Word("0010");
        Word w3 = new Word("0101");
        assertTrue(w1.isZeroWord());
        assertFalse(w2.isZeroWord());
        assertFalse(w3.isZeroWord());
    }

    @Test
    public void testModifyDigit() {
        Word w = new Word("00101");
        w.modifyDigit(1, 1);
        assertEquals("01101", w.toString());
        assertEquals(new Word("01101"), w);
    }

}
