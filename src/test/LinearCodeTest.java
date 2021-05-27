package test;

import model.Code;
import model.LinearCode;
import model.Word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LinearCodeTest {
    private LinearCode c1;
    private LinearCode c2;



    @BeforeEach
    public void setup() {
        c1 = new LinearCode(5, 3);
        ArrayList<Word> rows1 = new ArrayList<>();
        Word w1 = new Word("10121");
        Word w2 = new Word("01201");
        rows1.add(w1);
        rows1.add(w2);
        c1.formG(rows1);
        c1.formH();
        c1.formCode();


        c2 = new LinearCode(5, 2);
        ArrayList<Word> rows2 = new ArrayList<>();
        Word w3 = new Word("10110");
        Word w4 = new Word("01101");
        rows2.add(w3);
        rows2.add(w4);
        c2.formG(rows2);
        c2.formH();
        c2.formCode();

    }


    @Test
    public void testFormCoords() {
        assertEquals(2, c1.getDim());
        assertEquals(9, c1.getCoords().size());
        for (ArrayList<Integer> coord : c1.getCoords()) {
            assertEquals(2, coord.size());
        }

        assertEquals(2, c2.getDim());
        assertEquals(4, c2.getCoords().size());
        for (ArrayList<Integer> coord : c2.getCoords()) {
            assertEquals(2, coord.size());
        }
    }

    @Test
    public void testMinimalDistance() {
        assertEquals(3, c1.minimalDistance());
        assertEquals(3, c2.minimalDistance());
    }


    @Test
    public void testIsStandardForm() {
        LinearCode c3 = new LinearCode(5, 2);
        ArrayList<Word> rows1 = new ArrayList<>();
        Word w1 = new Word("11101");
        Word w2 = new Word("01001");
        rows1.add(w1);
        rows1.add(w2);
        c3.formG(rows1);

        assertTrue(c1.isInStandardForm(c1.getGeneratorMatrix()));
        assertTrue(c2.isInStandardForm(c2.getGeneratorMatrix()));
        assertFalse(c3.isInStandardForm(c3.getGeneratorMatrix()));
    }


    @Test
    public void testSyndrome() {
        assertEquals(new Word("002"), c1.syndrome(new Word("11021")));
    }


    @Test
    public void testAllWords() {
        Code allWords1 = c1.formAllWords();
        Code allWords2 = c2.formAllWords();
        assertEquals(243, allWords1.getWords().size());
        assertEquals(32, allWords2.getWords().size());
    }

    @Test
    public void testCosets() {
        ArrayList<Code> cosets1 = c1.formCosets();
        assertEquals(27, cosets1.size());
        for (Code c : cosets1) {
            assertEquals(9, c.getWords().size());
        }
        ArrayList<Code> cosets2 = c2.formCosets();
        assertEquals(8, cosets2.size());
        for (Code c : cosets2) {
            assertEquals(4, c.getWords().size());
        }
    }


    @Test
    public void testDecode() {
        assertEquals(new Word("01101"), c2.decode(new Word("01111")));
        assertEquals(new Word("00000"), c2.decode(new Word("00000")));
        assertEquals(new Word("10110"), c2.decode(new Word("10110")));
        assertEquals(new Word("01101"), c2.decode(new Word("01101")));
        assertEquals(new Word("11011"), c2.decode(new Word("11011")));
        assertEquals(new Word("11011"), c2.decode(new Word("10011")));
        assertEquals(new Word(""), c2.decode(new Word("00111")));
        assertEquals(new Word(""), c2.decode(new Word("10001")));
    }


    @Test
    public void testTableDecode() {
        assertEquals(new Word("01101"), c2.tableDecode(new Word("01111")));
        assertEquals(new Word("00000"), c2.tableDecode(new Word("00000")));
        assertEquals(new Word("10110"), c2.tableDecode(new Word("10110")));
        assertEquals(new Word("01101"), c2.tableDecode(new Word("01101")));
        assertEquals(new Word("11011"), c2.tableDecode(new Word("11011")));
        assertEquals(new Word("11011"), c2.tableDecode(new Word("10011")));
        assertEquals(new Word(""), c2.tableDecode(new Word("00111")));
        assertEquals(new Word(""), c2.tableDecode(new Word("10001")));
    }
}
