package model;

import java.util.ArrayList;
import java.util.Iterator;

public class Code extends VectorSpace implements Iterable<Word> {
    protected int length;
    protected ArrayList<Word> words;

    public Code(int n, int q) {
        super(q);
        this.length = n;
        this.q = q;
        this.words = new ArrayList<>();
    }

    public int getLength() {
        return length;
    }

    public int getQ() {
        return q;
    }

    public ArrayList<Word> getWords() {
        return words;
    }


    // EFFECTS: calculate the minimal Hamming Distance of 2 words in code; o(n) = (n-1)(n-2)
    public int minimalDistance() {
        int minResult = length;
        for (int i = 0; i < (this.words.size() - 1); i++) {
            for (int j = i; j < (this.words.size() - 1); j++) {
                Word curr = this.words.get(j);
                Word next = this.words.get(j + 1);
                int dist = curr.hammingDistance(next);
                if (dist <= minResult) {
                    minResult = dist;
                }
            }
        }
        return minResult;

    }

    public void addWord(Word w) {
        if (!this.words.contains(w) && checkValidWord(w)) {
            this.words.add(w);
        }
    }

    public boolean contains(Word w) {
        return (this.words.contains(w));
    }


    public boolean checkValidWord(Word w) {
        boolean validDigits = true;
        for (int i : w.getDigits()) {
            if (i >= this.q) {
                validDigits = false;
            }
        }
        return (w.getLength() == this.length && validDigits);
    }


    // generally use incomplete decoding NND
    public Word decode(Word received) {
        int minDist = this.length;
        Word sent = null;
        int d = minimalDistance();
        for (Word w : this) {
            int dist = w.hammingDistance(received);
            if (dist <= minDist) {
                minDist = dist;
                sent = w;
            }
        }
        if (minDist <= ((d - 1) / 2)) {
            return sent;
        } else {
            System.out.print("Retransmission!");
            return new Word("");
        }
    }


    // incomplete decoding NND
    public Word incompleteDecode(Word received) {
        int minDist = this.length;
        Word sent = null;
        int count = 0;
        for (Word w : this) {
            int dist = w.hammingDistance(received);
            if (dist < minDist) {
                minDist = dist;
                sent = w;
                count = 1;
            } else if (dist == minDist) {
                count = count + 1;
//                sent = w;
            }
        }
        if (count == 1) {
            return sent;
        } else {
            System.out.print("Retransmission!");
            return new Word("");
        }
    }


    // complete decoding NND
    public Word completeDecode(Word received) {
        int minDist = this.length;
        Word sent = null;
        ArrayList<Word> sentRecorded = new ArrayList<>();
        for (Word w : this) {
            int dist = w.hammingDistance(received);
            if (dist < minDist) {
                minDist = dist;
                sentRecorded.clear();
                sentRecorded.add(w);
            } else if (dist == minDist) {
                sentRecorded.add(w);
            }
        }
        int len = sentRecorded.size();
        if (len == 1) {
            sent = sentRecorded.get(0);
            return sent;
        } else {
            System.out.print("(Retransmission advised!)");
            // Math.random() gives number in [0, 1)
            int ranIndex = (int) (Math.random() * len) + 0;
            sent = sentRecorded.get(ranIndex);
            return sent;
        }
    }


//    // complete decoding NND(using index to track)
//    public Word completeDecode(Word received) {
//        int minDist = this.length;
//        Word sent = null;
//        ArrayList<Integer> indexRecorded = new ArrayList<>();
//        int index = 0;
//        for (Word w : this) {
//            int dist = w.hammingDistance(received);
//            if (dist < minDist) {
//                minDist = dist;
//                indexRecorded.clear();
//                indexRecorded.add(index);
//            } else if (dist == minDist) {
//                indexRecorded.add(index);
//            }
//            index = index + 1;
//        }
//        int len = indexRecorded.size();
//        if (len == 1) {
//            sent = this.words.get(indexRecorded.get(0));
//            return sent;
//        } else {
//            System.out.print("(Retransmission advised!)");
//            // Math.random() gives number in [0, 1)
//            int ranIndex = (int) (Math.random() * len) + 0;
//            sent = this.words.get(indexRecorded.get(ranIndex));
//            return sent;
//        }
//    }


    // coset leader
    public Word leader() {
        int minWeight = this.length;
        Word leader = null;
        for (Word w : this) {
            if (w.weight() <= minWeight) {
                minWeight = w.weight();
                leader = w;
            }
        }
        return leader;
    }



    @Override
    public Iterator<Word> iterator() {
        return this.words.iterator();
    }



//    public void printCode() {
//        for (Word w : this) {
//            System.out.print(w.getDigits());
//        }
//    }
    public void printCode() {
        ArrayList<String> code = new ArrayList<>();
        for (Word w : this) {
            code.add(w.toString());
        }
        System.out.println(code);
    }

}
