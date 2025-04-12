import java.util.*;
public class PairWithGivenSum {
    public static boolean hasPairWithSum(int[] arr, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int num : arr) {
            if (seen.contains(target - num)) {
                System.out.println("Pair found: (" + num + ", " + (target - num) + ")");
                return true;
            }
            seen.add(num);
        }
        System.out.println("No pair found with the given sum.");
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {84, 64, 21, 96};
        int target = 20;
        hasPairWithSum(arr, target);
    }
}
