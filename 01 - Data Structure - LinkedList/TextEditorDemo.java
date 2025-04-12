class TextState {
    String content;
    TextState prev, next;
    public TextState(String content) {
        this.content = content;
        this.prev = null;
        this.next = null;
    }
}
class TextEditor {
    private TextState head, tail, current;
    private int size = 0;
    private final int MAX_HISTORY = 10;
    public void type(String content) {
        TextState newState = new TextState(content);
        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
        }
        if (head == null) {
            head = tail = current = newState;
        } else {
            current.next = newState;
            newState.prev = current;
            current = newState;
            tail = current;
        }
        size++;
        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }
    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo performed.");
        } else {
            System.out.println("Nothing to undo.");
        }
    }
    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo performed.");
        } else {
            System.out.println("Nothing to redo.");
        }
    }
    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.content);
        } else {
            System.out.println("Editor is empty.");
        }
    }
    public void displayHistory() {
        TextState temp = head;
        System.out.print("History: ");
        while (temp != null) {
            System.out.print("[" + temp.content + "] ");
            temp = temp.next;
        }
        System.out.println();
    }
}
public class TextEditorDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        editor.type("Hello");
        editor.type("Hello World");
        editor.type("Hello World!");
        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.undo();
        editor.displayCurrentState();
        editor.redo();
        editor.displayCurrentState();
        editor.type("Hello Everyone");
        editor.displayCurrentState();
        editor.redo();
        for (int i = 1; i <= 10; i++) {
            editor.type("Version " + i);
        }
        editor.displayCurrentState();
        editor.displayHistory();
    }
}
