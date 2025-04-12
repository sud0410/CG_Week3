import java.util.Scanner;
public class FibonacciComparison {
    public static int fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    public static int fibonacciIterative(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1, sum = 0;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Fibonacci number N: ");
        int n = sc.nextInt();
        long start = System.nanoTime();
        int recursive = fibonacciRecursive(n);
        long end = System.nanoTime();
        double recursiveTime = (end - start) / 1e6;
        start = System.nanoTime();
        int iterative = fibonacciIterative(n);
        end = System.nanoTime();
        double iterativeTime = (end - start) / 1e6;
        System.out.println("Recursive Result: " + recursive + ", Time: " + recursiveTime + " ms");
        System.out.println("Iterative Result: " + iterative + ", Time: " + iterativeTime + " ms");
    }
}
