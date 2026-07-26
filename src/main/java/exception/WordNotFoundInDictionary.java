package main.java.exception;

public class WordNotFoundInDictionary extends GameException {
    public WordNotFoundInDictionary() {
        super("Слова нет в словаре");
    }
}
