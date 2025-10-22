package gallows;

import java.util.List;
import java.util.Random;
import static gallows.DrawGallows.printPicture;

public class MainGame {

    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 7;
    private static final Random RANDOM = new Random();

    private final WordChoose wordChoose;
    private final Console console;

    private int difficulty;

    public MainGame(String fileName) {
        this.console = new Console(System.in, System.out);
        this.wordChoose = new WordChoose(fileName);
        this.difficulty = RANDOM.nextInt(MIN_VALUE, MAX_VALUE + 1);
    }

    private void setTools() {
        console.println("Categories:");
        List<String> categories = wordChoose.categories();
        for (int i = 0; i < categories.size(); i++) {
            console.println("%d) %s".formatted(i + 1, categories.get(i)));
        }

        console.print("Input category number: ");
        Integer category = InputReader.readNumber(console.read());
        if (category != null) {
            if (!wordChoose.setCategory(category - 1)) {
                console.println(
                    "You entered an incorrect category, category set %s".formatted(wordChoose.currentCategory()));
            }
        }

        console.print("Input difficulty (number of mistakes from 1 to 7): ");
        Integer diff = InputReader.readNumber(console.read());
        if (diff != null && diff >= MIN_VALUE && diff <= MAX_VALUE) {
            difficulty = diff;
        } else {
            difficulty = RANDOM.nextInt(MIN_VALUE, MAX_VALUE + 1);
        }
    }

    private boolean guess(Question question) {
        while (!question.isGameEnd()) {
            console.println("Current category: %s".formatted(wordChoose.currentCategory().toUpperCase()));
            console.println("Your mistakes %d / %d".formatted(question.countMistake(), difficulty));
            console.println(question.getCurrentState());
            console.println(question.getPossibleLetters());
            console.println("Input letter or command (help/stop): ");
            String line = console.read();
            Character letter = InputReader.readLetter(line);
            if (letter == null) {
                Command command = InputReader.readCommand(line);
                switch (command) {
                    case Command.HELP -> console.println("Clue: %s".formatted(question.word().help()));
                    case Command.STOP -> {
                        return false;
                    }
                    default -> {
                    }
                }
                continue;
            }
            console.clear();
            if (!question.isPossibleLetter(letter)) {
                console.println();
                console.println("This letter is incorrect or has already been entered");
                continue;
            }
            if (!question.guessLetter(letter)) {
                console.println(printPicture(question.countMistake(), difficulty));
            }
        }
        console.println();

        return question.isGuessWord();
    }

    public void start() {

        while (true) {
            console.println("Command list: 'stop', 'start', 'help', 'settings'");

            Command command = InputReader.readCommand(console.read());

            switch (command) {
                case Command.START -> {
                }
                case Command.STOP -> {
                    return;
                }
                case Command.SETTINGS -> setTools();
                case Command.HELP -> {
                    console.println("Can only be used during gameplay");
                    continue;
                }
                default -> {
                    console.println(Command.OTHER.text());
                    continue;
                }
            }

            Question question = new Question(wordChoose.getWord(), difficulty);

            boolean win = guess(question);

            if (!win) {
                console.println("YOU LOSE!");
            } else {
                console.println("YOU WIN!");
            }
            console.println("Correct answer: '%s'".formatted(question.word().text()));
        }
    }
}
