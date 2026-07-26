package java;

import main.java.WordleDictionary;
import main.java.WordleGame;
import main.java.exception.DictionaryException;
import main.java.exception.WordNotFoundInDictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class WordleGameTest {
    private WordleGame game;

    @BeforeEach
    void setUp() throws DictionaryException {
        WordleDictionary dictionary = new WordleDictionary(List.of("герой", "гонец", "книга", "домик"));
        PrintWriter log = new PrintWriter(new StringWriter());
        game = new WordleGame(dictionary, log);
    }

    @Test
    void wrongWordThrowsException() {
        assertThrows(WordNotFoundInDictionary.class, () -> game.makeMove("машина"));
    }

    @Test
    void resultHasFiveSymbols() throws WordNotFoundInDictionary {
        String result = game.makeMove("герой");
        assertEquals(5, result.length());
    }

    @Test
    void gameFinishesAfterSixAttempts() throws WordNotFoundInDictionary {
        for (int i = 0; i < 6; i++) {
            if (!game.isFinished()) {
                game.makeMove("книга");
            }
        }
        assertTrue(game.isFinished());
    }

    @Test
    void answerIsFromDictionary() {
        String answer = game.getAnswer();
        assertNotNull(answer);
    }

    @Test
    void hintReturnsWord() {
        String hint = game.getHint();
        assertNotNull(hint);
    }
}

