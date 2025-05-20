package main;

import static main.InputValidation.getLetter;
import static main.Main.*;
import static main.Menu.startGame;

public class LetterChecks {

//    boolean isWin = false;
//    boolean isLose = false;
//    do {
//
//    }
//    while (!isWin && !isLose);

    static void checkErrorsNumber() {
        if (wrongTriesNumber < MAX_ERRORS_NUMBER) {
            checkSecretWordSolved();
        } else {
            System.out.printf("\nВы проиграли :-(\nЭто было слово: %s\nХотите сыграть еще?", word);
            startGame();
        }
    }

    static void checkSecretWordSolved() {

        if (mask.contains("*")) {
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

        if (word.contains(String.valueOf(letter))) {
            char[] maskArray = mask.toCharArray();
            for (int i = 0; i < word.length(); i++) {
                if (Character.toString(word.charAt(i)).equalsIgnoreCase(String.valueOf(letter))) {
                    maskArray[i] = letter;
                }
            }
            mask = new String(maskArray);
            System.out.println("\n" + mask);
        } else {
            wrongTriesNumber++;
            Graphics.drawHangman(wrongTriesNumber);
            System.out.println("Ошибка: " + wrongTriesNumber + " из 6!");

            System.out.println(mask);
        }
        checkErrorsNumber();
    }
}
