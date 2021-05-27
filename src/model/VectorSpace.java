package model;

import java.util.ArrayList;

public abstract class VectorSpace {
    protected int q;

    public VectorSpace(int q) {
        this.q = q;
    }


    public Word addition(Word w1, Word w2) {
        ArrayList<Integer> v1 = w1.getDigits();
        ArrayList<Integer> v2 = w2.getDigits();
        String s = "";
        for (int i = 0; i < v1.size(); i++) {
            Integer e = (v1.get(i) + v2.get(i)) % q;
            s = s + e.toString();
        }
        return new Word(s);
    }


    public Word multiply(int k, Word w) {
        ArrayList<Integer> v = w.getDigits();
        String s = "";
        for (int i = 0; i < v.size(); i++) {
            Integer e = (v.get(i) * k) % q;
            s = s + e.toString();
        }
        return new Word(s);
    }



    public int dotProduct(Word w1, Word w2) {
        ArrayList<Integer> v1 = w1.getDigits();
        ArrayList<Integer> v2 = w2.getDigits();
        int result = 0;
        for (int i = 0; i < v1.size(); i++) {
            result  = result + v1.get(i) * v2.get(i);
        }
        return (result % q);
    }


}
