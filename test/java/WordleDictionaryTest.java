package java;

import main.java.WordleDictionary;
import main.java.exception.DictionaryException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordleDictionaryTest {
    @Test
    void containsWord() throws DictionaryException {
        WordleDictionary dictionary = new WordleDictionary(List.of("герой", "домик", "книга"));
        assertTrue(dictionary.contains("герой"));
    }

    @Test
    void notContainsWord() throws DictionaryException {
        WordleDictionary dictionary = new WordleDictionary(List.of("герой", "домик"));
        assertFalse(dictionary.contains("машина"));
    }

    @Test
    void getRandomWordReturnsWordFromDictionary() throws DictionaryException {
        WordleDictionary dictionary = new WordleDictionary(List.of("герой", "домик"));
        String word = dictionary.getRandomWord();
        assertTrue(dictionary.contains(word));
    }

    @Test
    void emptyDictionaryThrowsException() {
        assertThrows(RuntimeException.class, () -> new WordleDictionary(List.of()));
    }
}
