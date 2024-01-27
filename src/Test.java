import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

    @Test
    void isSentenceValid_ValidSentence_ReturnsTrue() {
        assertTrue(WordCounter.isSentenceValid("This is a valid sentence with more than five words."));
    }

    @Test
    void isSentenceValid_InvalidSentence_ReturnsFalse() {
        assertFalse(WordCounter.isSentenceValid("Short sentence."));
    }

    @Test
    void countWordOccurrences() {
        String sentence = "This is a test. This is only a test.";
        Map<String, Integer> wordOccurrences = WordCounter.countWordOccurrences(sentence);

        assertEquals(2, wordOccurrences.get("this"));
        assertEquals(2, wordOccurrences.get("is"));
        assertEquals(2, wordOccurrences.get("a"));
        assertEquals(2, wordOccurrences.get("test"));
        assertEquals(1, wordOccurrences.get("only"));
    }

    @Test
    void printWordOccurrences() {
        WordCounter.printWordOccurrences(Map.of("word", 3, "count", 2));
    }

    @Test
    void saveToFile() throws IOException {
        String fileName = "testOutput.txt";
        Map<String, Integer> wordOccurrences = Map.of("word", 3, "count", 2);

        WordCounter.saveToFile(wordOccurrences, fileName);


        assertTrue(Files.exists(Path.of(fileName)));
        assertEquals("Occurrences of each word:\nword-3\ncount-2\n", Files.readString(Path.of(fileName)));

        Files.deleteIfExists(Path.of(fileName));
    }
}