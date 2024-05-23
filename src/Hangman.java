import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    static List<String> list = new ArrayList<>();
    static String secretWord;
    static char[] secretWordSolvingProcess;
    static char[] secretWordMask;
    static char letter;
    static final int wrongTriesNumber = 6;

    public Hangman(List<String> list, String secretWord, char[] secretWordSolvingProcess, char[] secretWordMask, char letter) {
        this.list = list;
        this.secretWord = secretWord;
        this.secretWordSolvingProcess = secretWordSolvingProcess;
        this.secretWordMask = secretWordMask;
        this.letter = letter;
    }

    public static void main(String[] args) {
        Random random = new Random();
        {
            try (FileReader reader = new FileReader("glossary.txt");
                 Scanner scanner = new Scanner(reader);) {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }
                secretWord = list.get(random.nextInt(list.size())).toUpperCase();
                System.out.println(secretWord);

                gameProcess();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static void gameProcess() {
        for (int i = 0; i < wrongTriesNumber; i++) {
            checkLetter();
            secretWordSolvingProcess = secretWordMask;
            System.out.println(secretWordSolvingProcess);
        }

    }


    static char[] secretWordMaskCreation() {
        int quantityOfLetters = secretWord.length();
        secretWordMask = new char[quantityOfLetters];
        for (int i = 0; i < quantityOfLetters; i++) {
            secretWordMask[i] = '*';
        }
        System.out.println(secretWordMask);
        return secretWordMask;
    }

    static char enteringLetter() {
        System.out.println("Введите букву (кириллица)");
        Scanner scanner = new Scanner(System.in);
        letter = scanner.next().charAt(0);
        if (!Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC)) {
            System.out.println("Ошибка ввода!\n1 - Вы не ввели букву\n2 - Ввели больше одной буквы\n3 - Введенный символ не является кириллицей");
        } else {
            System.out.println("Вы ввели букву: " + String.valueOf(letter).toUpperCase());
        }
        return letter;
    }

    static char[] checkLetter() {
        secretWordMaskCreation();
        enteringLetter();
        for (int i = 0; i < secretWordMask.length; i++) {
            if (Character.toString(secretWord.charAt(i)).equalsIgnoreCase(String.valueOf(letter))) {
                secretWordMask[i] = letter;
            }
        }
        System.out.println(secretWordMask);
        return secretWordMask;
    }


}