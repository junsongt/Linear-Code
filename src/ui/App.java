package ui;

import model.Code;
import model.LinearCode;
import model.Word;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private Scanner input;

    public App() {
        input = new Scanner(System.in);
        menu();
    }


    public void constructGeneralCode() {
        System.out.println("please input field");
        int q = input.nextInt();
        System.out.println("please input length");
        int n = input.nextInt();
        input.nextLine();

        Code c = new Code(n, q);
        boolean stop = false;
        while (!stop) {
            String digits = input.next();
            if (digits.equals("")) {
                stop = true;
            } else {
                Word w = new Word(digits);
                c.addWord(w);
            }
        }
    }

    public void constructLinearCode() {
        System.out.println("please input field");
        int q = input.nextInt();
        System.out.println("please input length");
        int n = input.nextInt();
        input.nextLine();
        // !!! input.nextInt() method - it only reads the int value.
        // So when you continue reading with input.nextLine() you receive the "\n" Enter key.
        // So to skip this you have to add the input.nextLine()

        LinearCode c = new LinearCode(n, q);
        ArrayList<Word> gm = new ArrayList<>();

        System.out.println("Input words to construct G");
        boolean stop = false;
        while (!stop) {
            String digits = input.nextLine();
            if (digits.equals("")) {
                stop = true;
            } else {
                Word w = new Word(digits);
                gm.add(w);
            }
        }
        c.formG(gm);
        c.formH();
        c.formCode();
    }


    public void menu() {
        System.out.println("Select one mode:");
        System.out.println("\tGeneral Code --> g");
        System.out.println("\tLinear Code --> l");
        System.out.println("\tQuit --> q");
        processCmd();
    }

    public void processCmd() {
        String cmd = null;
        boolean quit = false;
        while (!quit) {
            cmd = input.next();
            if (cmd.equals("l")) {
                constructLinearCode();
            } else if (cmd.equals("g")) {
                constructGeneralCode();
            } else if (cmd.equals("q")){
                quit = true;
            } else {
                System.out.println("Press the right key!");
            }
        }
    }


}
