package java;

import main.java.WordleDictionary;
import main.java.WordleDictionaryLoader;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;


class WordleDictionaryLoaderTest {
    @Test
    void loadDictionary() throws Exception {

        try (FileWriter writer = new FileWriter("test_dictionary.txt")) {
            writer.write("Ёлка\n");
            writer.write("Герой\n");
        }

        WordleDictionaryLoader loader =  new WordleDictionaryLoader();
        WordleDictionary dictionary =  loader.load("test_dictionary.txt");
        assertTrue(dictionary.contains("елка"));
        assertTrue(dictionary.contains("герой"));
    }
}
