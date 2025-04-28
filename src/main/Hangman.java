package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static main.LetterCheck.checkLetter;
import static main.Menu.startGame;

public class Hangman {

    private static String secretWord;
    private static char[] secretWordMask;
    private static char letter;
    static int wrongTriesNumber;
    static List<Character> usedLetters = new ArrayList<>();
    private static final int MAX_ERRORS_NUMBER = 6;

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
                e.printStackTrace();
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

    static void playGame() {
        if (wrongTriesNumber < MAX_ERRORS_NUMBER) {
            if (new String(secretWordMask).contains("*")) {
                getLetter();
                usedLetters.add(letter);
                System.out.println("Вы ввели букву: " + letter);
                System.out.println("Вы уже использовали буквы:\n" + usedLetters);
                if (secretWord.contains(String.valueOf(letter).toUpperCase())) {
                    for (int i = 0; i < secretWordMask.length; i++) {
                        if (Character.toString(secretWord.charAt(i)).equalsIgnoreCase(String.valueOf(letter))) {
                            secretWordMask[i] = letter;
                        }
                    }
                    System.out.println("\n" + new String(secretWordMask).toUpperCase());
                    playGame();
                } else {
                    wrongTriesNumber++;
                    Graphics.drawHangman(wrongTriesNumber);
                    System.out.println("Ошибка: " + wrongTriesNumber + " из 6!");
                    System.out.println(new String(secretWordMask).toUpperCase());
                    playGame();
                }
            } else {
                System.out.println("Верно! Вы отгадали слово!");
                System.out.println("Хотите сыграть еще?");
                startGame();
            }
        } else {
            System.out.println("\nВы проиграли :-(\nЭто было слово: " + secretWord + "\nХотите сыграть еще?");
            startGame();
        }
    }
}