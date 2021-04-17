package model;

import java.util.ArrayList;

public class VectorSpace {


    public VectorSpace() {

    }


    public ArrayList<Integer> addition(ArrayList<Integer> v1, ArrayList<Integer> v2) {
        ArrayList<Integer> newVector = new ArrayList<>();
        for (int i = 0; i < v1.size(); i++) {
            newVector.add(v1.get(i) + v2.get(i));
        }
        return newVector;
    }


    public ArrayList<Integer> sMultiply(int k, ArrayList<Integer> v) {
        ArrayList<Integer> newVector = new ArrayList<>();
        for (int i = 0; i < v.size(); i++) {
            newVector.add(v.get(i) * k);
        }
        return newVector;
    }


    public int dotProduct(ArrayList<Integer> v1, ArrayList<Integer> v2) {
        int result = 0;
        for (int i = 0; i < v1.size(); i++) {
            result  = result + v1.get(i) * v2.get(i);
        }
        return result;
    }
}
