package backend.academy.samples.gallows;

import gallows.exceptions.FileReadException;
import gallows.FileReader;
import gallows.Word;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FileReaderTest {

    @Test
    public void testException1() {
        //given
        String file = "1234.json";
        //when - then
        assertThatThrownBy(() -> {
            FileReader.read(file);
        }).isInstanceOf(FileReadException.class)
            .hasMessageContaining(file);
    }

    @Test
    public void testFileRead() {
        //given
        String file = "src/test/resources/testDictionary.json";
        //when
        Map<String, List<Word>> words = FileReader.read(file);
        //then
        assertThat(words).containsValues(
            List.of(
                new Word("dog", "first"),
                new Word("cat", "second")
            )
        );

        assertThat(words).containsKey("Animals");
    }
}
