package backend.academy.samples.gallows;

import gallows.Command;
import gallows.InputReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InputReaderTest {

    @ParameterizedTest
    @ValueSource(strings = {"1", "   1", "1    "})
    void test1(String val) {
        // given
        // when
        Integer i = InputReader.readNumber(val);
        // then
        assertEquals(1, i);
    }

    @Test
    void test2() {
        // given
        String s = "a";
        // when
        Integer i = InputReader.readNumber(s);
        // then
        assertNull(i);
    }

    @Test
    void test3() {
        // given
        String s = "a";
        // when
        Character i = InputReader.readLetter(s);
        // then
        assertEquals('A', i);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "test"})
    void test4(String val) {
        // given
        // when
        Character i = InputReader.readLetter(val);
        // then
        assertNull(i);
    }

    @ParameterizedTest
    @ValueSource(strings = {"start", "start   ", "   start"})
    void test5(String val) {
        // given
        // when
        Command i = InputReader.readCommand(val);
        // then
        assertEquals(Command.START, i);
    }

}
