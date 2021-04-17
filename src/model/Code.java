package model;

import java.util.ArrayList;

public class Code extends VectorSpace {
    private int n;
    private int q;
    private int dim;

    private ArrayList<Word> code;
    private int minimalDistance;
    private ArrayList<Word> generatorMatrix;
    private ArrayList<Word> parityCheckMatrix;
    private ArrayList<Branch> rootNodes;

    private Code coset;

    public Code(int n, int q) {
        this.n = n;
        this.q = q;
        this.code = new ArrayList<>();
    }




    // TODO
    public ArrayList<Word> giveCode() {
        ArrayList<Word> code = new ArrayList<>();

        return code;
    }



    public void printG() {
        for (Word row : this.generatorMatrix) {
            for (int i : row.getDigits()) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public void printH() {
        for (Word row : this.parityCheckMatrix) {
            for (int i : row.getDigits()) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public int dimC() {
        return this.generatorMatrix.size();
    }

    public int dimCComplement() {
        return this.parityCheckMatrix.size();
    }

}
