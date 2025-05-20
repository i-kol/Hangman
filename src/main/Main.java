package main;

import java.util.ArrayList;
import java.util.List;

import static main.LetterChecks.checkErrorsNumber;
import static main.Menu.startGame;
import static main.SecretWord.createSecretWordMask;
import static main.SecretWord.getSecretWord;

public class Main {

    static String word;
    static String mask;
    static char letter;
    static int wrongTriesNumber;
    static List<Character> usedLetters = new ArrayList<>();
    static final int MAX_ERRORS_NUMBER = 6;

    public static void main(String[] args) {
        System.out.println("\nПриветствуем Вас в игре \"Виселица\"!");
        startGame();
    }

    static void gameLoop() {
        usedLetters.clear();
        wrongTriesNumber = 0;
        getSecretWord();
        createSecretWordMask();
        Graphics.drawHangman(wrongTriesNumber);
        checkErrorsNumber();
    }
}