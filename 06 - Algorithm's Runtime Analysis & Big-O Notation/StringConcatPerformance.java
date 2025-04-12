public class StringConcatPerformance {
    public static void main(String[] args) {
        int[] sizes = {1000, 10000, 1000000};
        for (int n : sizes) {
            System.out.println("Operations Count: " + n);
            long start = System.nanoTime();
            String str = "";
            for (int i = 0; i < n; i++) {
                str += "a";
            }
            long end = System.nanoTime();
            double stringTime = (end - start) / 1e6;
            System.out.printf("String Time: %.4f ms%n", stringTime);
            start = System.nanoTime();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb.append("a");
            }
            end = System.nanoTime();
            double builderTime = (end - start) / 1e6;
            System.out.printf("StringBuilder Time: %.4f ms%n", builderTime);
            start = System.nanoTime();
            StringBuffer sbuf = new StringBuffer();
            for (int i = 0; i < n; i++) {
                sbuf.append("a");
            }
            end = System.nanoTime();
            double bufferTime = (end - start) / 1e6;
            System.out.printf("StringBuffer Time: %.4f ms%n", bufferTime);
            System.out.println("-----");
        }
    }
}
