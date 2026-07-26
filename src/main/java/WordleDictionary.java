package main.java;

import main.java.exception.DictionaryException;

import java.util.List;
import java.util.Random;

public class WordleDictionary {
    private final List<String> words;

    public WordleDictionary(List<String> words) throws DictionaryException {
        this.words = words.stream()
                .filter(w -> !w.isBlank())
                .filter(w -> w.length() == 5)
                .toList();

        if(this.words.isEmpty()){
            throw new DictionaryException(
                    "Словарь пуст"
            );
        }
    }

    public boolean contains(String word){
        return words.contains(
                word.toLowerCase()
                        .replace('ё','е')
        );
    }

    public String getRandomWord(){
        Random random = new Random();
        return words.get(
                random.nextInt(words.size())
        );
    }

    public List<String> getWords(){
        return words;
    }
}
