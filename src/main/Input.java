package main;

import java.util.Scanner;

import static main.InputValidation.checkLetter;
import static main.Main.letter;

public class Input {
    static void getLetter() {
        char enteredLetter;

        System.out.println("Введите букву (кириллица)");

        Scanner scanner = new Scanner(System.in);

        String scannerInput = scanner.next();
        enteredLetter = scannerInput.charAt(0);
        letter = Character.toUpperCase(enteredLetter);

        checkLetter(scannerInput, letter);
    }
}
