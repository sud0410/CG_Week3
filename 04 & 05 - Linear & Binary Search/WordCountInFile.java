import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class WordCountInFile {
    public static void main(String[] args) {
        String fileName = "real1.txt";
        String targetWord = "line";
        int wordCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (word.equalsIgnoreCase(targetWord)) {
                        wordCount++;
                    }
                }
            }
            System.out.println("The word '" + targetWord + "' comes " + wordCount + " times.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
