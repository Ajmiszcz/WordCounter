import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a sentence (at least 5 words):");
        String sentence = scanner.nextLine();

        if (isSentenceValid(sentence)) {
            Map<String, Integer> wordOccurrences = countWordOccurrences(sentence);
            printWordOccurrences(wordOccurrences);

            System.out.println("Enter the filename to save the results:");
            String fileName = scanner.nextLine();
            saveToFile(wordOccurrences, fileName);
        } else {
            System.out.println("Invalid sentence. At least 5 words are required.");
        }
    }

    static boolean isSentenceValid(String sentence) {
        String[] words = sentence.split("\\s+");
        return words.length >= 5;
    }

    static Map<String, Integer> countWordOccurrences(String sentence) {
        Map<String, Integer> wordOccurrences = new HashMap<>();
        String[] words = sentence.split("\\s+");

        for (String word : words) {
            word = word.toLowerCase();
            wordOccurrences.put(word, wordOccurrences.getOrDefault(word, 0) + 1);
        }

        return wordOccurrences;
    }

    static void printWordOccurrences(Map<String, Integer> wordOccurrences) {
        System.out.println("Occurrences of each word:");
        for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }

    static void saveToFile(Map<String, Integer> wordOccurrences, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Occurrences of each word:\n");
            for (Map.Entry<String, Integer> entry : wordOccurrences.entrySet()) {
                writer.write(entry.getKey() + "-" + entry.getValue() + "\n");
            }
            System.out.println("Data saved to the file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error while saving to the file.");
        }
    }
}
