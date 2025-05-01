package main;

import static main.Input.getLetter;
import static main.Main.usedLetters;

public class InputValidation {
    static void checkLetter(String scannerInput, char symbol) {
        checkEnteredLettersNumber(scannerInput);
        checkInputLanguage(symbol);
        checkUsedLetters(symbol);
    }

    private static boolean isOneLetterEntered(String scannerInput) {
        return scannerInput.length() == 1;
    }

    private static boolean isCyrillic(char symbol) {
        return Character.UnicodeBlock.of(symbol).equals(Character.UnicodeBlock.CYRILLIC);
    }

    private static boolean isUsed(char symbol) {
        return usedLetters.contains(symbol);
    }

    private static void checkEnteredLettersNumber(String scannerInput) {
        if (!isOneLetterEntered(scannerInput)) {
            System.out.println("Введите ТОЛЬКО ОДНУ букву!");
            getLetter();
        }
    }

    private static void checkInputLanguage(char symbol) {
        if (!isCyrillic(symbol)) {
            System.out.println("Ошибка ввода! Введенный символ не относится к кириллице");
            getLetter();
        }
    }

    private static void checkUsedLetters(char symbol) {
        if (isUsed(symbol)) {
            System.out.println("Такая буква уже была!");
            getLetter();
        }
    }
}
