import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Hangman {

    static List<String> list = new ArrayList<>();
    static String secretWord;
    static char[] secretWordMask;
    static char letter;
    static int wrongTriesNumber = 0;

    public static void main(String[] args) {
        Random random = new Random();
        {
            try (FileReader reader = new FileReader("glossary.txt"); Scanner scanner = new Scanner(reader);) {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }
                secretWord = list.get(random.nextInt(list.size())).toUpperCase();
                System.out.println(secretWord);

                secretWordMaskCreation();
                checkLetter();


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static char[] secretWordMaskCreation() {
        secretWordMask = secretWord.toCharArray();
        Arrays.fill(secretWordMask, '*');
        System.out.println(secretWordMask);
        return secretWordMask;
    }

    static char enteringLetter() {
        System.out.println("Введите одну букву (кириллица)");
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

        while (new String(secretWordMask).contains("*")) {
            enteringLetter();
            System.out.println(letter);
            if (secretWord.contains(String.valueOf(letter).toUpperCase())) {
                for (int i = 0; i < secretWordMask.length; i++) {
                    if (Character.toString(secretWord.charAt(i)).equalsIgnoreCase(String.valueOf(letter))) {
                        secretWordMask[i] = letter;
                    }
                }
                System.out.println(new String(secretWordMask).toUpperCase());
            } else {
                wrongTriesNumber++;
                if (wrongTriesNumber < 6) {
                    System.out.println("Ошибка: " + wrongTriesNumber + " из 6!");
                } else {
                    System.out.println("\nВы проиграли.\nХотите сыграть еще?");
                }
            }
        }
        System.out.println("\nВерно! Вы отгадали слово.\nХотите сыграть еще?");

        return secretWordMask;
    }
}