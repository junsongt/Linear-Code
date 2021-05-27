package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class Word implements Iterable<Integer> {
    private ArrayList<Integer> digits;
    private int length;

    public Word(String digits) {
        this.digits = new ArrayList<>();
        for (int i = 0; i < digits.length(); i++) {
            int digit = Integer.parseInt(digits.substring(i, i+1));
            this.digits.add(digit);
        }
        this.length = this.digits.size();

    }

    public ArrayList<Integer> getDigits() {
        return this.digits;
    }

    public int getLength() {
        return this.length;
    }

    // REQUIRES: w has the same length as this
    public int hammingDistance(Word w) {
        int diffNum = 0;
        for(int i = 0; i < this.length; i++) {
            if (this.digits.get(i) != w.getDigits().get(i)) {
                diffNum = diffNum + 1;
            }
        }
        return diffNum;
    }


    public void addDigit(int d) {
        this.digits.add(d);
    }


    public int weight() {
        int w = 0;
        for (int i : this) {
          if (i != 0) {
              w = w + 1;
          }
        }
        return w;
    }

    public boolean isZeroWord() {
        return (this.weight() == 0);
//        boolean check = true;
//        for (int i : this) {
//            if (i != 0) {
//                check = false;
//            }
//        }
//        return check;
    }


    public void modifyDigit(int index, int digit) {
        this.digits.set(index, digit);
    }

//    public void printWord() {
//        System.out.println(this.digits);
//    }

    public void printWord() {
        System.out.println(toString());
    }



    public String toString() {
        String s = "";
        for (Integer i : this) {
            s = s + i.toString();
        }
        return s;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word integers = (Word) o;
        return length == integers.length &&
                digits.equals(integers.digits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(digits, length);
    }



    @Override
    public Iterator<Integer> iterator() {
        return this.digits.iterator();
    }


}
