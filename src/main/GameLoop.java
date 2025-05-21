package main;

import java.util.HashSet;
import java.util.Set;

import static main.InputValidation.getLetter;
import static main.SecretWord.createSecretWordMask;
import static main.SecretWord.getSecretWord;

public class GameLoop {

    static final int MAX_MISTAKES_NUMBER = 6;
    static int mistakes;
    static Set<Character> usedLetters = new HashSet<>();

    static void runGameLoop() {
        boolean isWin = false;
        boolean isLose = false;

        usedLetters.clear();
        mistakes = 0;

        String word = getSecretWord();
        String mask = createSecretWordMask(word);

        do {
            Graphics.drawHangman(mistakes);
            System.out.printf("Ошибок: %s из 6%n", mistakes);
            System.out.println(mask);

            char letter = getLetter();
            usedLetters.add(letter);

            System.out.printf("Вы ввели букву: %s%n", letter);

            if (word.indexOf(letter) != -1) {
                mask = openLetter(word, mask, letter);
            } else {
                mistakes++;
            }

            if (mistakes == MAX_MISTAKES_NUMBER) {
                System.out.printf("%nВы проиграли :-(%nЭто было слово: %s%nХотите сыграть еще?%n", word);
                isLose = true;
            }

            if (!mask.contains("*")) {
                System.out.println("Верно! Вы отгадали слово!\nХотите сыграть еще?\n");
                isWin = true;
            }
        }
        while (!isWin && !isLose);
    }

    static String openLetter(String word, String mask, char letter) {
        char[] maskArray = mask.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                maskArray[i] = letter;
            }
        }
        mask = new String(maskArray);
        return mask;
    }
}
