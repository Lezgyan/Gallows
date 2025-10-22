package gallows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;

public class WordChoose {

    private final Map<String, Category> mapQuestions;

    @Getter
    private final List<String> categories;

    @Getter
    private String currentCategory;

    public WordChoose(String name) {
        Map<String, List<Word>> temp = FileReader.read(name);
        mapQuestions = new HashMap<>();
        for (var entry : temp.entrySet()) {
            List<Word> words = entry.getValue();
            Collections.shuffle(words);
            mapQuestions.put(entry.getKey(), new Category(words));
        }
        this.categories = new ArrayList<>(mapQuestions.keySet());
        Collections.shuffle(categories);
        this.currentCategory = categories.getFirst();
    }

    public boolean setCategory(int i) {
        if (i >= 0 && i < categories.size()) {
            currentCategory = categories.get(i);
            return true;
        } else {
            return false;
        }
    }

    public Word getWord() {
        return mapQuestions.get(currentCategory).nextWord();
    }
}
