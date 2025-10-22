package backend.academy.samples.gallows;

import gallows.Question;
import gallows.Word;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {

    @Test
    public void test1() {
        //given
        Question q = new Question(new Word("hello", ""), 10);
        Character guessLetter = 'E';
        //when
        boolean guess = q.guessLetter(guessLetter);
        //then
        assertTrue(guess);
        assertEquals(0, q.countMistake());
        assertThat(q.getCurrentState()).contains(guessLetter.toString());
    }

    @Test
    public void test2() {
        //given
        Question q = new Question(new Word("hello", ""), 10);
        //when
        boolean guess = q.guessLetter('d');
        //then
        assertFalse(guess);
        assertEquals(1, q.countMistake());
    }

    @Test
    public void test3() {
        //given
        Question q = new Question(new Word("d", ""), 10);
        //when
        q.guessLetter('d');
        //then
        assertTrue(q.isGuessWord());
    }

    @Test
    public void test4() {
        //given
        Question q = new Question(new Word("de", ""), 10);
        //when
        q.guessLetter('d');
        //then
        assertFalse(q.isGuessWord());
    }

    @Test
    public void test5() {
        //given
        Question q = new Question(new Word("d", ""), 10);
        //when
        q.guessLetter('d');
        //then
        assertTrue(q.isGameEnd());
    }

    @Test
    public void test6() {
        //given
        Question q = new Question(new Word("da", ""), 1);
        Character letter = 'd';
        //when
        boolean hasLetter = q.isPossibleLetter(letter);
        q.guessLetter(letter);
        boolean alreadyGuessed = q.isPossibleLetter(letter);
        //then
        assertTrue(hasLetter);
        assertFalse(alreadyGuessed);
    }

    @Test
    public void test7() {
        //given
        Question q = new Question(new Word("da", ""), 1);
        Character letter = 'd';
        q.guessLetter(letter);

        //when
        String currentState = q.getCurrentState();

        //then
        assertThat(currentState).isEqualTo("D _ ");
    }

}
