public class LinearSearchNegative {
    public static int findFirstNegative(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 0) {
                return i;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] arr = {12, 5, 0, -13, 18, -7};
        int index = findFirstNegative(arr);
        System.out.println("1st negative number is at index: " + index);
    }
}
