class CustomHashMap {
    static class Entry {
        int key, value;
        Entry next;
        Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    private final int SIZE = 10;
    private Entry[] table;
    public CustomHashMap() {
        table = new Entry[SIZE];
    }
    private int hash(int key) {
        return key % SIZE;
    }
    public void put(int key, int value) {
        int index = hash(key);
        Entry head = table[index];
        while (head != null) {
            if (head.key == key) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
    }
    public Integer get(int key) {
        int index = hash(key);
        Entry head = table[index];
        while (head != null) {
            if (head.key == key) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }
    public void remove(int key) {
        int index = hash(key);
        Entry head = table[index];
        Entry prev = null;
        while (head != null) {
            if (head.key == key) {
                if (prev != null) {
                    prev.next = head.next;
                } else {
                    table[index] = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }
    public void printMap() {
        for (int i = 0; i < SIZE; i++) {
            Entry head = table[i];
            System.out.print("Bucket " + i + ": ");
            while (head != null) {
                System.out.print("[" + head.key + "=" + head.value + "] ");
                head = head.next;
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1, 10);
        map.put(2, 20);
        map.put(11, 110);
        map.put(12, 120);
        map.printMap();
        System.out.println("Get key 11: " + map.get(11));
        map.remove(2);
        map.printMap();
        System.out.println("Get key 2: " + map.get(2));
    }
}
