import java.io.*;
public class CompareUtilities {
    public static void main(String[] args) throws Exception {
        int repetitions = 1000000;
        String text = "hello";
        long startSB = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repetitions; i++) {
            sb.append(text);
        }
        long endSB = System.nanoTime();
        System.out.println("StringBuilder Time: " + (endSB - startSB) / 1e6 + " ms");
        long startSBF = System.nanoTime();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < repetitions; i++) {
            sbf.append(text);
        }
        long endSBF = System.nanoTime();
        System.out.println("StringBuffer Time: " + (endSBF - startSBF) / 1e6 + " ms");
        File file = new File("real1.txt");
        long startFR = System.nanoTime();
        FileReader fr = new FileReader(file);
        BufferedReader brFR = new BufferedReader(fr);
        String lineFR;
        int wordCountFR = 0;
        while ((lineFR = brFR.readLine()) != null) {
            wordCountFR += lineFR.split("\\s+").length;
        }
        brFR.close();
        long endFR = System.nanoTime();
        System.out.println("FileReader Word Count: " + wordCountFR);
        System.out.println("FileReader Time: " + (endFR - startFR) / 1e6 + " ms");
        long startISR = System.nanoTime();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader brISR = new BufferedReader(isr);
        String lineISR;
        int wordCountISR = 0;
        while ((lineISR = brISR.readLine()) != null) {
            wordCountISR += lineISR.split("\\s+").length;
        }
        brISR.close();
        long endISR = System.nanoTime();
        System.out.println("InputStreamReader Word Count: " + wordCountISR);
        System.out.println("InputStreamReader Time: " + (endISR - startISR) / 1e6 + " ms");
    }
}

