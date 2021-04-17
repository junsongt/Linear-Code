package model;

import java.util.ArrayList;

public class Word extends VectorSpace {

    private ArrayList<Integer> digits;
    private int q;
    private int length;

    public Word(int q, int n) {
        this.digits = new ArrayList<>();
        this.q = q;
        this.length = n;

    }

    public ArrayList<Integer> getDigits() {
        return this.digits;
    }
}
