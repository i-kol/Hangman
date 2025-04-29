package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static main.InputValidation.checkLetter;
import static main.Menu.startGame;

public class Main {

    static String secretWord;
    static char[] secretWordMask;
    static char letter;
    static int wrongTriesNumber;
    static List<Character> usedLetters = new ArrayList<>();
    static final int MAX_ERRORS_NUMBER = 6;

    public static void main(String[] args) {
        System.out.println("\nПриветствуем Вас в игре \"Виселица\"!");
        startGame();
    }

    static void getSecretWord() {
        List<String> list = new ArrayList<>();
        Random random = new Random();

        {
            try (FileReader reader = new FileReader("src/resources/glossary.txt"); Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }

                secretWord = list.get(random.nextInt(list.size())).toUpperCase();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static void createSecretWordMask() {
        secretWordMask = secretWord.toCharArray();
        Arrays.fill(secretWordMask, '*');
        System.out.println(secretWordMask);
    }

    static void getLetter() {
        char enteredLetter;

        System.out.println("Введите букву (кириллица)");

        Scanner scanner = new Scanner(System.in);

        String scannerInput = scanner.next();
        enteredLetter = scannerInput.charAt(0);
        letter = Character.toUpperCase(enteredLetter);

        checkLetter(scannerInput, letter);
    }
}