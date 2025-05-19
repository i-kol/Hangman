package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import static main.Main.mask;
import static main.Main.word;

public class SecretWord {
    static void getSecretWord() {
        List<String> list = new ArrayList<>();
        Random random = new Random();

        {
            try (FileReader reader = new FileReader("src/resources/glossary.txt"); Scanner scanner = new Scanner(reader)) {
                while (scanner.hasNextLine()) {
                    list.add(scanner.nextLine());
                }

                word = list.get(random.nextInt(list.size())).toUpperCase();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static String createSecretWordMask() {
        mask = "*".repeat(word.length());
        System.out.println(mask);
        return mask;
    }
}
