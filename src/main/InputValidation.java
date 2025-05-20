package main;

import java.util.Scanner;

import static main.Main.letter;
import static main.Main.usedLetters;

public class InputValidation {

    static char getLetter() {
        System.out.println("Введите букву (кириллица)");

        Scanner scanner = new Scanner(System.in);

        boolean isValid = false;

        while (!isValid) {
            String scannerInput = scanner.next();
            letter = Character.toUpperCase(scannerInput.charAt(0));

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

            isValid = true;
        }

        return letter;
    }

    private static boolean isCyrillic(char letter) {
        return Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC);
    }
}
