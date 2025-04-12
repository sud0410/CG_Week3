public class RotationPointBinarySearch {
    public static int findRotationPoint(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
    public static void main(String[] args) {
        int[] arr = {125, 518, 482, 583, 696, 142};
        int index = findRotationPoint(arr);
        System.out.println("RotationPoint Index: " + index);
        System.out.println("SmallestElement: " + arr[index]);
    }
}
