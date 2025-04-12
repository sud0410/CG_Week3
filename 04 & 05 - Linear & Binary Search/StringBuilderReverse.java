import java.util.Scanner;
public class StringBuilderReverse {
    public static String reverseString(String input) {
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println("real string: " + input);
        String reversed = reverseString(input);
        System.out.println("Reversed String: " + reversed);
    }
}

