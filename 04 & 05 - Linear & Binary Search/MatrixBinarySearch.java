public class MatrixBinarySearch {
    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, cols = matrix[0].length;
        int left = 0, right = rows * cols - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = matrix[mid / cols][mid % cols];
            if (midVal == target) return true;
            if (midVal < target) left = mid + 1;
            else right = mid - 1;
        }
        return false;
    }
    public static void main(String[] args) {
        int[][] matrix = {
                {1, 3, 5, 7},
                {10, 11, 16, 30},
                {23, 30, 20, 60}
        };
        int target = 16;
        System.out.println(searchMatrix(matrix, target));
    }
}
