package ui;

import model.Code;
import model.Word;

import java.util.Scanner;

public class App {
    private Scanner input;

    public App() {
        Scanner input = new Scanner(System.in);
    }

    public Word constructWord() {
        System.out.println("please input a field and length");
        int q = input.nextInt();
        int n = input.nextInt();
        Word word = new Word(q, n);
        boolean stop = false;
        while (!stop) {
            String digit = input.nextLine();
            if (digit == "") {
                stop = true;
            } else {
                word.getDigits().add(Integer.parseInt(digit));
            }
        }
        return word;
    }

    public Code constructG(int n, int q) {
        Code g = new Code(n, q);
        boolean stop = false;
        while (!stop) {
            String digit = input.nextLine();
            if (digit == "q") {
                stop = true;
            } else {
            }
        }
        return g;
    }
}
