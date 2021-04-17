package model;

import java.util.ArrayList;

public class Branch {
    private int value;
    private ArrayList<Branch> children;


    public Branch(int value) {
        this.value = value;
        this.children = new ArrayList<>();

    }



    public void addBranch(Branch b) {
        if (!this.children.contains(b)) {
            this.children.add(b);
        }

    }

    public void generateTree(int q, int dim) {
        for (int i = 0; i < q; i++) {
            Branch b = new Branch(i);
            addBranch(b);
        }

        if (dim >= 3) {
            for (Branch b : children) {
                b.generateTree(q, dim - 1);
            }
        }
    }


    public ArrayList<ArrayList<Integer>> generatePath(ArrayList<ArrayList<Integer>> rsf, ArrayList<Integer> path) {
        ArrayList<Integer> nPath = new ArrayList<>();
        for (int i : path){
            nPath.add(i);
        }
        nPath.add(this.value);
        if (this.children.isEmpty()) {
            rsf.add(nPath);

        } else {
            for (Branch b : this.children) {
                b.generatePath(rsf, nPath);
            }
        }
        return rsf;

    }
}
