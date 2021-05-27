package model;

import java.util.ArrayList;
import java.util.Iterator;

public class LinearCode extends Code {
    private int dim;
    private ArrayList<Word> generatorMatrix;
    private ArrayList<Word> parityCheckMatrix;
    private ArrayList<Tree> rootNodes;
    private ArrayList<ArrayList<Integer>> coords;



    public LinearCode(int n, int q) {
        super(n, q);
        this.generatorMatrix = new ArrayList<>();
        this.parityCheckMatrix = new ArrayList<>();
        this.rootNodes = new ArrayList<>();
        this.coords = new ArrayList<>();

    }

    public int getDim() {
        return dim;
    }

    public ArrayList<Word> getGeneratorMatrix() {
        return generatorMatrix;
    }

    public ArrayList<Word> getParityCheckMatrix() {
        return parityCheckMatrix;
    }

    public ArrayList<ArrayList<Integer>> getCoords() {
        return coords;
    }




    // REQUIRES: given gm is in standard form
    // MODIFIES: this
    public void formG(ArrayList<Word> gm) {
        this.generatorMatrix = gm;
        this.dim = this.generatorMatrix.size();
    }


    // REQUIRES: given gm is in standard form
    // MODIFIES: this
    public void formH() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = dim; i < length; i++) {
            String s = "";
            for (Word w : this.generatorMatrix) {
                Integer e = null;
                int digit = w.getDigits().get(i);
                if (digit == 0) {
                    e = 0;
                } else {
                    e = q - digit;
                }
                s = s + e.toString();
            }
            temp.add(s);
        }

        for (int j = 0; j < length - dim; j++) {
            String s = temp.get(j);
            for (int k = 0; k < length -dim; k++) {
                if (j == k) {
                    s = s + "1";
                } else {
                    s = s + "0";
                }
            }
            this.parityCheckMatrix.add(new Word(s));
        }
    }


    // MODIFIES: this
    public void formCode() {
        formCoords();
        for (ArrayList<Integer> coord : this.coords) {
            Word word = formWord(coord);
            addWord(word);
        }
    }

    // MODIFIES: this
    public void formCoords() {
        for (int i = 0; i < q; i++) {
            Tree t = new Tree(i);
            t.generateTree(q, dim);
            this.rootNodes.add(t);
        }

        for (Tree t : this.rootNodes) {
            ArrayList<ArrayList<Integer>> temp = t.generatePath(new ArrayList<>(), new ArrayList<>());
            this.coords.addAll(temp);
        }
    }


    //EFFECTS: generate V(n,q)
    public Code formAllWords() {
        ArrayList<Tree> roots = new ArrayList<>();
        Code allWords = new Code(length, q);
        for (int i = 0; i < q; i++) {
            Tree t = new Tree(i);
            t.generateTree(q, length);
            roots.add(t);
        }

        ArrayList<ArrayList<Integer>> allPaths = new ArrayList<>();
        for (Tree t : roots) {
            ArrayList<ArrayList<Integer>> temp = t.generatePath(new ArrayList<>(), new ArrayList<>());
            allPaths.addAll(temp);
        }

        for (ArrayList<Integer> digitList : allPaths) {
            String s = "";
            for (Integer i : digitList) {
                s = s + i.toString();
            }
            Word w = new Word(s);
            allWords.addWord(w);
        }
        return allWords;
    }



    public Word formWord(ArrayList<Integer> coord) {
        Word result = formZeroWord();
        for (int i = 0; i < coord.size(); i++) {
            Word temp = multiply(coord.get(i), this.generatorMatrix.get(i));
            result = addition(result, temp);
        }
        return result;
    }

    public Word formZeroWord() {
        String s = "";
        for (int i = 0; i < this.length; i++) {
            s = s + "0";
        }
        return new Word(s);
    }


    public ArrayList<Word> transpose(ArrayList<Word> pcm) {
        ArrayList<Word> hT = new ArrayList<>();
        for (int i = 0; i < this.length; i++) {
            String s = "";
            for (Word w : pcm) {
                Integer digit = w.getDigits().get(i);
                s = s + digit.toString();
            }
            hT.add(new Word(s));
        }
        return hT;
    }


    public Word syndrome(Word w) {
        String s = "";
        for (Word word : this.parityCheckMatrix) {
            Integer digit = dotProduct(word, w);
            s = s + digit.toString();
        }
        return new Word(s);
    }


    @Override
    public int minimalDistance() {
        int minWeight = this.length;
        for (Word w : this) {
            if (!w.isZeroWord()) {
                int currWeight = w.weight();
                if (currWeight <= minWeight) {
                    minWeight = currWeight;
                }
            }
        }
        return minWeight;
    }



    // generally use incomplete syndrome decoding
    @Override
    public Word decode(Word received) {
        int d = minimalDistance();
        boolean foundSyndrome = false;
        Word sent = null;
        Word s = syndrome(received);
        Word error = formZeroWord();
        ArrayList<Word> hT = transpose(this.parityCheckMatrix);
        for (int i = 0; i < hT.size(); i++) {
            Word row = hT.get(i);
            for (int k = 0; k < q; k++) {
                if (s.equals(multiply(k, row))) {
                    error.modifyDigit(i, k);
                    foundSyndrome = true;
                }
            }
        }
        if (foundSyndrome) {
            sent = addition(received, multiply(q-1, error));
            return sent;
        } else {
            System.out.print("Retransmission!");
            return new Word("");
        }

    }



    public boolean isInStandardForm(ArrayList<Word> gm) {
        int k = gm.size();
        boolean check = true;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                Word w = gm.get(i);
                int digit = w.getDigits().get(j);
                if (i == j && digit != 1) {
                    check = false;
                }
                if (i != j && digit != 0) {
                    check = false;
                }
            }
        }
        return check;
    }



    public void printG() {
        System.out.println("Generator Matrix:");
        for (Word row : this.generatorMatrix) {
            for (int i : row) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public void printH() {
        System.out.println("Parity Check Matrix:");
        for (Word row : this.parityCheckMatrix) {
            for (int i : row) {
                System.out.print(i);
            }
            System.out.println();
        }
    }



    public Word tableDecode(Word received) {
        Word sent = null;
        ArrayList<Code> cosets = formCosets();
        for (Code coset : cosets) {
            int d = minimalDistance();
            if (coset.contains(received)) {
                Word error = coset.leader();
                if (error.weight() <= (d-1)/2) {
                    sent = addition(received, multiply(q-1, error));
                }
            }
        }
        if (sent != null) {
            return sent;
        } else {
            System.out.print("Retransmission!");
            return new Word("");
        }
    }


    // MODIFIES: this
    public ArrayList<Code> formCosets() {
        ArrayList<Code> cosets = new ArrayList<>();
        cosets.add(this);
        Code allWords = formAllWords();

        Iterator it = allWords.iterator();
        while (it.hasNext()) {
            Word w = (Word) it.next();
            if (this.contains(w)) {
                it.remove();
            }
        }

        for (Word w : allWords) {
            boolean checkNotIn = true;
            for (Code coset : cosets) {
                if (!coset.contains(w)) {
                    checkNotIn = checkNotIn & true;
                } else {
                    checkNotIn = false;
                    break;
                }
            }
            if (checkNotIn) {
                Code coset = formCoset(w);
                cosets.add(coset);
            }
        }
        return cosets;
    }


    public Code formCoset(Word error) {
        Code coset = new Code(length, q);
        for (Word w : this) {
           Word v = addition(w, error);
           coset.addWord(v);
        }
        return coset;
    }


    public void printCosets() {
        ArrayList<Code> cosets = formCosets();
        for (Code coset : cosets) {
            coset.printCode();
        }
    }


}
