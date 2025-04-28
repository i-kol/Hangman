package main;

import static main.Hangman.getLetter;
import static main.Hangman.usedLetters;

public class LetterCheck {
    static void checkLetter(String scannerInput, char symbol) {
        checkEnteredLettersNumber(scannerInput);
        checkLanguage(symbol);
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

    private static void checkLanguage(char symbol) {
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
