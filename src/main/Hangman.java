package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Hangman {
    private static List<String> list = new ArrayList<>();
    private static String secretWord;
    private static char[] secretWordMask;
    private static char letter;
    private static int wrongTriesNumber;
    private static List<Character> usedLetters = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\nПриветствуем Вас в игре \"Виселица\"!");
        startGame();
    }

    private static void createSecretWord() {
        Random random = new Random();
        {
            try (FileReader reader = new FileReader("src/resources/glossary.txt"); Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }
                secretWord = list.get(random.nextInt(list.size())).toUpperCase();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createSecretWordMask() {
        secretWordMask = secretWord.toCharArray();
        Arrays.fill(secretWordMask, '*');
        System.out.println(secretWordMask);
    }

    private static void getLetter() {
        System.out.println("Введите одну букву (кириллица)");
        Scanner scanner = new Scanner(System.in);
        String scannerInput = scanner.next();
        if (scannerInput.length() != 1) {
            System.out.println("Введите ТОЛЬКО одну букву (кириллица)!");
            getLetter();
        } else {
            letter = scannerInput.charAt(0);
        }
    }

    private static boolean isCyrillic(char symbol) {
        return Character.UnicodeBlock.of(symbol).equals(Character.UnicodeBlock.CYRILLIC);
    }

    private static void checkLetter() {
        if (isCyrillic(letter)) {
            if (!usedLetters.contains(letter)) {
                usedLetters.add(letter);
                System.out.println("Вы ввели букву: " + String.valueOf(letter).toUpperCase());
            } else {
                System.out.println("Такая буква уже была, введите другую.");
                getLetter();
            }
        } else {
            System.out.println("Ошибка ввода! Введенный символ не является кириллицей");
            getLetter();
        }
    }

    private static void playGame() {
        if (wrongTriesNumber < 6) {
            if (new String(secretWordMask).contains("*")) {
                getLetter();
                checkLetter();
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

    private static void startGame() {
        System.out.println();
        System.out.println("Для продолжения ведите цифру:\n1 - Новая игра\n2 - Выход из игры");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        switch (option) {
            case ("1"):
                usedLetters.clear();
                wrongTriesNumber = 0;
                createSecretWord();
                createSecretWordMask();
                Graphics.drawHangman(wrongTriesNumber);
                playGame();
                break;
            case ("2"):
                System.out.println("Игра окончена");
                break;
            default:
                System.out.println("Введите 1 или 2!");
                startGame();
        }
    }
}