package main;

import java.util.HashSet;
import java.util.Set;

import static main.InputValidation.getLetter;
import static main.SecretWord.createMask;
import static main.SecretWord.getWord;

class GameLoop {

    static Set<Character> usedLetters = new HashSet<>();

    static void runGameLoop() {
        final int MAX_MISTAKES_NUMBER = 6;

        boolean isWin = false;
        boolean isLose = false;

        usedLetters.clear();
        int mistakes = 0;

        String word = getWord();
        String mask = createMask(word);

        do {
            Graphics.printHangman(mistakes);
            System.out.printf("Ошибок: %s из 6%n", mistakes);
            System.out.println(mask);

            char letter = getLetter();
            usedLetters.add(letter);

            System.out.printf("Вы ввели букву: %s%n", letter);

            if (word.indexOf(letter) != -1) {
                mask = SecretWord.openLetter(word, mask, letter);
            } else {
                mistakes++;
            }

            if (mistakes == MAX_MISTAKES_NUMBER) {
                Graphics.printHangman(mistakes);
                System.out.printf("%nВы проиграли :-(%nЭто было слово: %s%nХотите сыграть еще?%n", word);
                isLose = true;
            }

            if (!mask.contains("*")) {
                System.out.printf("Верно! Вы отгадали слово: %s%nХотите сыграть еще?%n", word);
                isWin = true;
            }
        }
        while (!isWin && !isLose);
    }
}