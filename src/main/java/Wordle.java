package main.java;

import java.io.PrintWriter;
import java.util.Scanner;

public class Wordle {
    private static final String LOG_FILE = "wordle.log";
    private static final String DICTIONARY_FILE = "dictionary.txt";

    private final PrintWriter log;

    public Wordle(PrintWriter log) {
        this.log = log;
    }

    public void play() {
        try {
            WordleDictionaryLoader loader = new WordleDictionaryLoader();
            WordleDictionary dictionary = loader.load(DICTIONARY_FILE);
            WordleGame game = new WordleGame(dictionary, log);
            Scanner scanner = new Scanner(System.in);

            while (!game.isFinished()) {
                System.out.print("Введите слово: ");
                String word = scanner.nextLine();

                if (word.isBlank()) {
                    System.out.println(game.getHint());
                    continue;
                }

                String result = game.makeMove(word);
                System.out.println(word);
                System.out.println(result);
            }

            System.out.println("Ответ: " + game.getAnswer());
        } catch (Exception e) {
            log.println("Ошибка программы: ");
            e.printStackTrace(log);
        }
    }

    public static void main(String[] args) {
        try (PrintWriter log = new PrintWriter(LOG_FILE)) {
            Wordle wordle = new Wordle(log);
            wordle.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
