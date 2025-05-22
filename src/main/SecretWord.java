package main;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class SecretWord {
    static String getWord() {
        List<String> list = new ArrayList<>();
        Random random = new Random();
        String word;

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
        return word;
    }

    static String createMask(String word) {
        String mask = "*".repeat(word.length());
        System.out.println(mask);
        return mask;
    }

    static String openLetter(String word, String mask, char letter) {
        char[] maskArray = mask.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                maskArray[i] = letter;
            }
        }
        mask = new String(maskArray);
        return mask;
    }
}