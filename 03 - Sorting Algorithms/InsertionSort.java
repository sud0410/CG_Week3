public class InsertionSort {
    public static void insertionSort(int[] employeeIDs) {
        int n = employeeIDs.length;
        for (int i = 1; i < n; i++) {
            int key = employeeIDs[i];
            int j = i - 1;
            while (j >= 0 && employeeIDs[j] > key) {
                employeeIDs[j + 1] = employeeIDs[j];
                j = j - 1;
            }
            employeeIDs[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        int[] employeeIDs = {1123, 4875, 4452, 6245, 2678, 2168};
        System.out.println("EmployeeIDs before sorting:");
        for (int id : employeeIDs) {
            System.out.print(id + " ");
        }
        System.out.println();
        insertionSort(employeeIDs);
        System.out.println("EmployeeIDs after sorting:");
        for (int id : employeeIDs) {
            System.out.print(id + " ");
        }
        System.out.println();
    }
}

