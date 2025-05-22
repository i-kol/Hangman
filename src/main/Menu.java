package main;

import java.util.Scanner;

import static main.GameLoop.runGameLoop;

class Menu {
    static void startGame() {
        Scanner scanner = new Scanner(System.in);
        boolean isGameActive = true;

        do {
            System.out.println("\nДля продолжения нажмите:\n1 - Новая игра\n2 - Выход из игры");
            String userResponse = scanner.next();

            if (userResponse.equals("1")) {
                runGameLoop();
            } else if (userResponse.equals("2")) {
                System.out.println("Игра окончена");
                isGameActive = false;
            } else {
                System.out.println("Введите только 1 или 2!");
            }
        } while (isGameActive);
    }
}