package gallows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import lombok.Getter;

public class Question {

    private final Set<Character> possibleLetters;

    private final List<Character> currentState;

    private final Integer difficulty;

    @Getter
    private final Word word;

    @Getter
    private int countMistake = 0;

    public Question(Word word, Integer difficulty) {
        this.word = word;
        this.difficulty = difficulty;
        possibleLetters = createAlphabet();
        currentState = createPlaceHolder(word.text().length());
    }

    public boolean isGameEnd() {
        return isGuessWord() || countMistake >= difficulty;
    }

    public boolean isGuessWord() {
        return !currentState.contains('_');
    }

    public boolean isPossibleLetter(Character letter) {
        return possibleLetters.contains(Character.toUpperCase(letter));
    }

    public boolean guessLetter(Character letter) {
        Character upper = Character.toUpperCase(letter);
        removePossibleLetters(upper);

        if (findLetter(upper) != -1) {
            changeCurrentState(upper);
            return true;
        } else {
            addOneCountMistake();
            return false;
        }
    }

    public String getPossibleLetters() {
        StringBuilder t = new StringBuilder();
        for (Character i : this.possibleLetters) {
            t.append(i).append(" ");
        }
        return new String(t);
    }

    public String getCurrentState() {
        StringBuilder t = new StringBuilder();
        for (Character character : currentState) {
            t.append(character).append(" ");
        }
        return new String(t);
    }

    private void addOneCountMistake() {
        this.countMistake++;
    }

    private void removePossibleLetters(Character letters) {
        possibleLetters.remove(letters);
    }

    private static List<Character> createPlaceHolder(int n) {
        return new ArrayList<>(Collections.nCopies(n, '_'));
    }

    private Set<Character> createAlphabet() {
        Set<Character> set = new TreeSet<>();
        for (char i = 'A'; i <= 'Z'; i++) {
            set.add(i);
        }
        return set;
    }

    private int findLetter(Character letter) {
        return word.text().indexOf(letter);
    }

    private void changeCurrentState(Character let) {
        String text = word.text();
        for (int i = 0; i < currentState.size(); i++) {
            if (let.equals(text.charAt(i))) {
                currentState.set(i, let);
            }
        }
    }
}
