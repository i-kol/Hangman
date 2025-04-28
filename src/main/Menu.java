package main;

import java.util.Scanner;

import static main.Hangman.*;

public class Menu {
    static void startGame() {
        System.out.println();
        System.out.println("Для продолжения ведите цифру:\n1 - Новая игра\n2 - Выход из игры");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        switch (option) {
            case ("1"):
                usedLetters.clear();
                wrongTriesNumber = 0;
                getSecretWord();
                createSecretWordMask();
                Graphics.drawHangman(wrongTriesNumber);
                playGame();
                break;
            case ("2"):
                System.out.println("Игра окончена");
                break;
            default:
                System.out.println("Введите только 1 или 2!");
                startGame();
        }
    }
}