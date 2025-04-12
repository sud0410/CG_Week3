public class SelectionSort {
    public static void selectionSort(int[] scores) {
        int n = scores.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (scores[j] < scores[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = scores[i];
            scores[i] = scores[minIndex];
            scores[minIndex] = temp;
        }
    }
    public static void main(String[] args) {
        int[] scores = {85, 92, 78, 88, 76, 95};
        System.out.println("Exam scores before sorting:");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
        selectionSort(scores);
        System.out.println("exam score fter sort:");
        for (int score : scores) {
            System.out.print(score + " ");
        }
        System.out.println();
    }
}
