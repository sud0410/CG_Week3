public class CountingSort {
    public static void countingSort(int[] ages) {
        int maxAge = 18;
        int minAge = 10;
        int range = maxAge - minAge + 1;
        int[] count = new int[range];
        int[] output = new int[ages.length];
        for (int i = 0; i < ages.length; i++) {
            count[ages[i] - minAge]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        for (int i = ages.length - 1; i >= 0; i--) {
            output[count[ages[i] - minAge] - 1] = ages[i];
            count[ages[i] - minAge]--;
        }
        System.arraycopy(output, 0, ages, 0, ages.length);
    }
    public static void main(String[] args) {
        int[] ages = {10,16,50,12,02,22,11,55,21,22,55};
        System.out.println("Student ages before sorting:");
        for (int age : ages) {
            System.out.print(age + " ");
        }
        System.out.println();
        countingSort(ages);
        System.out.println("Student ages after sorting:");
        for (int age : ages) {
            System.out.print(age + " ");
        }
        System.out.println();
    }
}
