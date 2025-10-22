package backend.academy.samples.gallows;

import gallows.FileReader;
import gallows.Word;
import gallows.WordChoose;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
public class WordChooseTest {
    private static final String NAME = "test.json";

    @Test
    public void testWordChoose1() {
        //given
        try (MockedStatic<FileReader> mock = mockStatic(FileReader.class)) {
            Map<String, List<Word>> words = Map.of("Animals", new ArrayList<>(List.of(
                new Word("dog", "first dog"),
                new Word("cat", "second cat")
            )));
            mock.when(() -> FileReader.read(NAME)).thenReturn(words);
            WordChoose wordChoose = new WordChoose(NAME);
            //when
            List<Word> test = List.of(wordChoose.getWord(), wordChoose.getWord());
            //then
            assertThat(test).containsAll(words.get("Animals"));
        }
    }

    @Test
    public void testWordChoose2() {
        //given
        try (MockedStatic<FileReader> mock = mockStatic(FileReader.class)) {
            Word checkAnswer = new Word("dog", "first dog");
            Map<String, List<Word>> words = Map.of("Animals", new ArrayList<>(List.of(
                checkAnswer
            )));
            mock.when(() -> FileReader.read(NAME)).thenReturn(words);
            WordChoose wordChoose = new WordChoose(NAME);
            //when
            List<Word> test = List.of(wordChoose.getWord(), wordChoose.getWord());
            //then
            assertThat(test).allMatch(w -> w.equals(checkAnswer));
            assertThat(test).hasSize(2);
        }
    }
}
