package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static main.Main.secretWord;
import static main.Main.secretWordMask;

public class SecretWord {
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
}
