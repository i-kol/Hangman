import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Hangman {
    static List<String> list = new ArrayList<>();
    static String secretWord;
    static char[] secretWordMask;
    static char letter;
    static int wrongTriesNumber;
    static List<Character> usedLetters = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("\nПриветствуем Вас в игре \"Виселица\"!");
        menu();
    }

    static void secretWordCreation() {
        Random random = new Random();
        {
            try (FileReader reader = new FileReader("glossary.txt"); Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }
                secretWord = list.get(random.nextInt(list.size())).toUpperCase();
                //System.out.println(secretWord); //Раскомментировать, если нужно перед началом игры узнать слова
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void secretWordMaskCreation() {
        secretWordMask = secretWord.toCharArray();
        Arrays.fill(secretWordMask, '*');
        System.out.println(secretWordMask);
    }

    static void enteringLetter() {
        System.out.println("Введите одну букву (кириллица)");
        Scanner scanner = new Scanner(System.in);
        letter = scanner.next().charAt(0);
        if (Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC)) { //Проверка, что вводимый символ является кириллицей
            if (!usedLetters.contains(letter)) {
                usedLetters.add(letter);
                System.out.println("Вы ввели букву: " + String.valueOf(letter).toUpperCase());
            } else {
                System.out.println("Такая буква уже была, введите другую.");
                enteringLetter();
            }
        } else {
            System.out.println("Ошибка ввода! Введенный символ не является кириллицей");
            enteringLetter();
        }
    }

    static void checkLetter() {
        if (wrongTriesNumber < 6) {
            if (new String(secretWordMask).contains("*")) {
                enteringLetter();
                System.out.println("Вы уже использовали буквы:\n" + usedLetters);
                if (secretWord.contains(String.valueOf(letter).toUpperCase())) {
                    for (int i = 0; i < secretWordMask.length; i++) {
                        if (Character.toString(secretWord.charAt(i)).equalsIgnoreCase(String.valueOf(letter))) {
                            secretWordMask[i] = letter;
                        }
                    }
                    System.out.println("\n" + new String(secretWordMask).toUpperCase());
                    checkLetter();
                } else {
                    wrongTriesNumber++;
                    drawHangman(wrongTriesNumber);
                    System.out.println("Ошибка: " + wrongTriesNumber + " из 6!");
                    System.out.println(new String(secretWordMask).toUpperCase());
                    checkLetter();
                }
            } else {
                System.out.println("Верно! Вы отгадали слово!");
                System.out.println("Хотите сыграть еще?");
                menu();
            }
        }
        else {
            System.out.println("\nВы проиграли :-(\nЭто было слово: " + secretWord + "\nХотите сыграть еще?");
            menu();
        }
    }

    static void menu() {
        System.out.println();
        System.out.println("Для продолжения ведите цифру:\n1 - Новая игра\n2 - Выход из игры");
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        switch (option) {
            case ("1"):
                usedLetters.clear();
                wrongTriesNumber = 0;
                secretWordCreation();
                secretWordMaskCreation();
                checkLetter();
            case ("2"):
                System.out.println("Игра окончена");
                break;
            default:
                System.out.println("Введите 1 или 2!");
                menu();
        }
    }

    static void drawHangman(int wrongTriesNumber) {
        switch (wrongTriesNumber) {
            case (1):
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            case (2):
                System.out.println("_________");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            case (3):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            case (4):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|       |");
                System.out.println("|");
                System.out.println("|");
                break;
            case (5):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|      /|\\");
                System.out.println("|");
                System.out.println("|");
                break;
            case (6):
                System.out.println("_________");
                System.out.println("|       |");
                System.out.println("|       O");
                System.out.println("|      /|\\");
                System.out.println("|      /-\\");
                System.out.println("|");
                break;
        }
    }
}