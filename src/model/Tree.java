package model;

import java.util.ArrayList;

public class Tree {
    private int value;
    private ArrayList<Tree> children;


    public Tree(int value) {
        this.value = value;
        this.children = new ArrayList<>();

    }


    public void addTree(Tree t) {
        if (!this.children.contains(t)) {
            this.children.add(t);
        }
    }

    public void generateTree(int q, int dim) {
        for (int i = 0; i < q; i++) {
            Tree t = new Tree(i);
            addTree(t);
        }
        if (dim >= 3) {
            for (Tree t : children) {
                t.generateTree(q, dim - 1);
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
            for (Tree b : this.children) {
                b.generatePath(rsf, nPath);
            }
        }
        return rsf;

    }
}
