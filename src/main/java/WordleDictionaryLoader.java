package main.java;

import main.java.exception.DictionaryException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WordleDictionaryLoader {
    public WordleDictionary load(String filename)
            throws DictionaryException {

        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename),
                                     StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(normalize(line));
            }
        } catch (IOException e) {
            throw new DictionaryException("Не удалось загрузить словарь: " + filename, e);
        }
        return new WordleDictionary(words);
    }

    private String normalize(String word) {
        return word
                .toLowerCase()
                .replace('ё', 'е')
                .trim();
    }
}
