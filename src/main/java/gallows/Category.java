package gallows;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Category {
    private final List<Word> words;
    private int currentWord = 0;

    public Word nextWord() {
        if (currentWord == words.size()) {
            currentWord = 0;
            Collections.shuffle(words);
        }
        return words.get(currentWord++);
    }
}
