package main;

import static main.Main.*;
import static main.Menu.startGame;

public class LetterChecks {

    static void checkErrorsNumber() {
        if (wrongTriesNumber < MAX_ERRORS_NUMBER) {
            checkSecretWordSolved();
        } else {
            System.out.println("\nВы проиграли :-(\nЭто было слово: " + secretWord + "\nХотите сыграть еще?");
            startGame();
        }
    }

    static void checkSecretWordSolved() {

        if (new String(secretWordMask).contains("*")) {
            getLetter();
            usedLetters.add(letter);
            System.out.println("Вы ввели букву: " + letter);
            System.out.println("Вы уже использовали буквы:\n" + usedLetters);
            checkSecretWordContainLetter();
        } else {
            System.out.println("Верно! Вы отгадали слово!\nХотите сыграть еще?\n");
            startGame();
        }
    }

    static void checkSecretWordContainLetter() {
        if (secretWord.contains(String.valueOf(letter).toUpperCase())) {
            for (int i = 0; i < secretWordMask.length; i++) {
                if (Character.toString(secretWord.charAt(i)).equalsIgnoreCase(String.valueOf(letter))) {
                    secretWordMask[i] = letter;
                }
            }
            System.out.println("\n" + new String(secretWordMask).toUpperCase());
        } else {
            wrongTriesNumber++;
            Graphics.drawHangman(wrongTriesNumber);
            System.out.println("Ошибка: " + wrongTriesNumber + " из 6!");
            System.out.println(new String(secretWordMask).toUpperCase());
        }
        checkErrorsNumber();
    }
}
