package model;

import java.util.ArrayList;

public class LinearCode extends VectorSpace {
    private int n;
    private int q;
    private int dim;
    private ArrayList<ArrayList<Integer>> generatorMatrix;
    private ArrayList<ArrayList<Integer>> parityCheckMatrix;
    private ArrayList<Branch> rootNodes;

    public LinearCode(int n, int q) {
        this.n = n;
        this.q = q;
        this.generatorMatrix = new ArrayList<>();
        this.parityCheckMatrix = new ArrayList<>();
        this.dim = 3;
        this.rootNodes = new ArrayList<>();
        for (int i = 0; i < q; i++) {
            Branch b = new Branch(i);
            b.generateTree(q, dim);
            this.rootNodes.add(b);

        }
    }

    public ArrayList<ArrayList<Integer>> generateCoeff() {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (Branch b : this.rootNodes) {
            ArrayList<ArrayList<Integer>> temp = b.generatePath(new ArrayList<>(), new ArrayList<>());
            result.addAll(temp);
        }
        return result;
    }

    public void generateG() {
        ArrayList<ArrayList<Integer>> g = new ArrayList<>();

    }

    public void generateH() {

    }


    public void printG() {
        for (ArrayList<Integer> row : this.generatorMatrix) {
            for (int i : row) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public void printH() {
        for (ArrayList<Integer> row : this.parityCheckMatrix) {
            for (int i : row) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

//    public int dimC() {
//        return this.generatorMatrix.size();
//    }
//
//    public int dimCComplement() {
//        return this.parityCheckMatrix.size();
//    }


//    public ArrayList<Integer> vAdd(ArrayList<Integer> v1, ArrayList<Integer> v2) {
//        ArrayList<Integer> newVector = new ArrayList<>();
//        for (int i = 0; i < v1.size(); i++) {
//            newVector.add(v1.get(i) + v2.get(i));
//        }
//        return newVector;
//    }
//
//
//    public ArrayList<Integer> vSM(int k, ArrayList<Integer> v) {
//        ArrayList<Integer> newVector = new ArrayList<>();
//        for (int i = 0; i < v.size(); i++) {
//            newVector.add(v.get(i) * k);
//        }
//        return newVector;
//    }

    public ArrayList<ArrayList<Integer>> giveCode() {
        ArrayList<ArrayList<Integer>> code = new ArrayList<>();
        ArrayList<ArrayList<Integer>> coeff = generateCoeff();
        for (ArrayList<Integer> coord : coeff) {
            ArrayList<Integer> word = formWord(coord);
            code.add(word);
        }

        return code;
    }


    public ArrayList<Integer> formWord(ArrayList<Integer> coord) {
        ArrayList<Integer> word = new ArrayList<>();

        for (int i = 0; i < dim; i++) {
            word.add(0);
        }

        for (int j = 0; j < dim; j++) {
            ArrayList<Integer> temp = sMultiply(coord.get(j), generatorMatrix.get(j));
            word = addition(word, temp);
        }

        return word;
    }
}
