package test;

import model.Code;
import model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CodeTest {
    private Code testCode1;
    private Code testCode2;


    @BeforeEach
    public void setUp() {
        testCode1 = new Code(4,3);
        testCode2 = new Code(5,5);
    }

    @Test
    public void testMinimalDistance() {
        Word w1 = new Word("01234");
        Word w2 = new Word("43210");
        Word w3 = new Word("32111");
        Word w4 = new Word("14444");
        testCode2.addWord(w1);
        testCode2.addWord(w2);
        testCode2.addWord(w3);
        testCode2.addWord(w4);
        assertEquals(4, testCode2.minimalDistance());
        Word w5 = new Word("0000");
        Word w6 = new Word("2211");
        Word w7 = new Word("1110");
        testCode1.addWord(w5);
        testCode1.addWord(w6);
        testCode1.addWord(w7);
        assertEquals(3, testCode1.minimalDistance());

    }




    @Test
    public void testAddWord() {
        Word w1 = new Word("01221");
        Word w2 = new Word("0123");
        Word w3 = new Word("12326");
        Word w4 = new Word("1231344");
        Word w5 = new Word("0122");
        Word w6 = new Word("01234");
        assertFalse(testCode1.checkValidWord(w1));
        assertFalse(testCode1.checkValidWord(w2));
        assertFalse(testCode2.checkValidWord(w3));
        assertFalse(testCode2.checkValidWord(w4));
        assertTrue(testCode1.checkValidWord(w5));
        assertTrue(testCode2.checkValidWord(w6));

        testCode1.addWord(w1);
        testCode1.addWord(w2);
        testCode1.addWord(w5);
        assertTrue(testCode1.getWords().contains(w5));
        assertFalse(testCode1.getWords().contains(w1));
        assertFalse(testCode1.getWords().contains(w2));

        testCode2.addWord(w3);
        testCode2.addWord(w4);
        testCode2.addWord(w6);
        assertTrue(testCode2.getWords().contains(w6));
        assertFalse(testCode2.getWords().contains(w3));
        assertFalse(testCode2.getWords().contains(w4));
    }


    @Test
    public void testDecode() {
        Code c1 = new Code(5, 2);
        Word w1 = new Word("00000");
        Word w2 = new Word("11100");
        Word w3 = new Word("00111");
        Word w4 = new Word("11011");
        c1.addWord(w1);
        c1.addWord(w2);
        c1.addWord(w3);
        c1.addWord(w4);
        assertEquals(3, c1.minimalDistance());

        assertEquals(w3, c1.decode(new Word("00111")));
        assertEquals(w1, c1.decode(new Word("01000")));
        assertEquals(w3, c1.decode(new Word("01111")));
        assertEquals(new Word(""), c1.decode(new Word("10101")));

        assertEquals(w3, c1.incompleteDecode(new Word("00111")));
        assertEquals(w1, c1.incompleteDecode(new Word("01000")));
        assertEquals(w3, c1.incompleteDecode(new Word("01111")));
        assertEquals(new Word(""), c1.incompleteDecode(new Word("10101")));

        assertEquals(w3, c1.completeDecode(new Word("00111")));
        assertEquals(w1, c1.completeDecode(new Word("01000")));
        assertEquals(w3, c1.completeDecode(new Word("01111")));
//        c1.completeDecode(new Word("10101")).printWord();
    }

    @Test
    public void testLeader() {
        Code c1 = new Code(5, 2);
        Word w1 = new Word("10000");
        Word w2 = new Word("00110");
        Word w3 = new Word("11101");
        Word w4 = new Word("01011");
        c1.addWord(w1);
        c1.addWord(w2);
        c1.addWord(w3);
        c1.addWord(w4);
        assertEquals(w1, c1.leader());

        Code c2 = new Code(5, 2);
        Word w5 = new Word("11000");
        Word w6 = new Word("01110");
        Word w7 = new Word("10101");
        Word w8 = new Word("00011");
        c2.addWord(w5);
        c2.addWord(w6);
        c2.addWord(w7);
        c2.addWord(w8);
        assertEquals(w8, c2.leader());

    }


}
