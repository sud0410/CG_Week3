import java.io.*;
import java.util.Scanner;
public class FileReadComparison {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        long start = System.nanoTime();
        FileReader fr = new FileReader(file);
        while (fr.read() != -1) {}
        fr.close();
        long end = System.nanoTime();
        double fileReaderTime = (end - start) / 1e6;
        System.out.printf("FileReader Time: %.2f ms%n", fileReaderTime);
        start = System.nanoTime();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        while (isr.read() != -1) {}
        isr.close();
        end = System.nanoTime();
        double inputStreamReaderTime = (end - start) / 1e6;
        System.out.printf("InputStreamReader Time: %.2f ms%n", inputStreamReaderTime);
    }
}

