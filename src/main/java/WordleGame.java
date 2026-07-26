package main.java;

import main.java.exception.WordNotFoundInDictionary;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Locale.ROOT;

public class WordleGame {
    private final PrintWriter log;
    private final String answer;
    private final WordleDictionary dictionary;
    private int attempts = 6;
    private boolean finished;
    private final List<String> moves = new ArrayList<>();
    private final List<String> hints = new ArrayList<>();

    public WordleGame(WordleDictionary dictionary, PrintWriter log, String answer) {
        this.dictionary = dictionary;
        this.log = log;
        this.answer = answer;
    }

    public WordleGame(WordleDictionary dictionary, PrintWriter log) {
        this(dictionary, log, dictionary.getRandomWord());
    }


    private String normalize(String word) {
        return word.toLowerCase(ROOT)
                .replace('ё', 'е')
                .trim();
    }

    public String makeMove(String word) throws WordNotFoundInDictionary {
        word = normalize(word);

        if (word.length() != 5) {
            throw new WordNotFoundInDictionary();
        }

        if (!dictionary.contains(word)) {
            throw new WordNotFoundInDictionary();
        }

        if (word.equals(answer)) {
            finished = true;
            log.println("Игрок угадал слово");
        }

        attempts--;

        String result = checkWord(word);
        moves.add(word);
        hints.add(result);

        log.println("Использовано попыток: " + (6 - attempts));
        log.println("Введено слово: " + word);
        log.println("Результат: " + result);


        if (attempts == 0) {
            finished = true;
        }
        return result;
    }

    private String checkWord(String word) {
        char[] answerChars = answer.toCharArray();
        char[] wordChars = word.toCharArray();

        StringBuilder result = new StringBuilder();

        boolean[] used = new boolean[answer.length()];

        for (int i = 0; i < answer.length(); i++) {
            if (wordChars[i] == answerChars[i]) {
                result.append("+");
                used[i] = true;
            } else {
                result.append("-");
            }
        }

        for (int i = 0; i < 5; i++) {
            if (result.charAt(i) == '+') {
                continue;
            }
            for (int j = 0; j < 5; j++) {
                if (!used[j] && wordChars[i] == answerChars[j]) {
                    result.setCharAt(i, '^');
                    used[j] = true;
                    break;
                }
            }
        }
        return result.toString();
    }

    public boolean isFinished() {
        return finished || attempts == 0;
    }

    public String getAnswer() {
        return answer;
    }

    public String getHint() {

        List<String> candidates =  new ArrayList<>(dictionary.getWords());

        for (int i = 0; i < moves.size(); i++) {
            String move = moves.get(i);
            String hint = hints.get(i);
            candidates.removeIf(word -> !matches(word, move, hint));
        }


        if (candidates.isEmpty()) {
            return "Подходящих слов нет";
        }

        Random random = new Random();
        return "Возможное слово: " + candidates.get(random.nextInt(candidates.size()));
    }

    private boolean matches(String candidate, String move, String hint) {

        for (int i = 0; i < 5; i++) {
            char letter = move.charAt(i);
            char symbol = hint.charAt(i);
            if (symbol == '+') {
                if (candidate.charAt(i) != letter) {
                    return false;
                }
            }

            if (symbol == '^') {
                if (!candidate.contains(String.valueOf(letter))) {
                    return false;
                }

                if (candidate.charAt(i) == letter) {
                    return false;
                }
            }

            if (symbol == '-') {
                if (candidate.contains(String.valueOf(letter))) {
                    return false;
                }
            }
        }
        return true;
    }
}
