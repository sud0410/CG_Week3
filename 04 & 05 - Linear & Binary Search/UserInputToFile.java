import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
public class UserInputToFile {
    public static void main(String[] args) {
        String fileName = "real1.txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             FileWriter writer = new FileWriter(fileName, true)) {
            String line;
            System.out.println("Enter text (type 'exit' to stop):");
            while (!(line = reader.readLine()).equalsIgnoreCase("exit")) {
                writer.write(line + System.lineSeparator());
            }
            System.out.println("Input has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}