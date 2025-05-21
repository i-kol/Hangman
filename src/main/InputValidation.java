package main;

import java.util.Scanner;

import static main.GameLoop.usedLetters;

public class InputValidation {

    static char getLetter() {
        while (true) {
            System.out.println("Вы уже использовали буквы:\n" + usedLetters);
            System.out.println("Введите букву (кириллица)");

            Scanner scanner = new Scanner(System.in);
            String scannerInput = scanner.next();
            char letter = Character.toUpperCase(scannerInput.charAt(0));

            if (scannerInput.length() != 1) {
                System.out.println("Введите ТОЛЬКО ОДНУ букву!");
                continue;
            }

            if (!isCyrillic(letter)) {
                System.out.println("Ошибка ввода! Введенный символ не относится к кириллице");
                continue;
            }

            if (usedLetters.contains(letter)) {
                System.out.println("Ошибка ввода! Вы уже использовали эту букву");
                continue;
            }

            return letter;
        }
    }

    private static boolean isCyrillic(char letter) {
        return Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC);
    }
}
