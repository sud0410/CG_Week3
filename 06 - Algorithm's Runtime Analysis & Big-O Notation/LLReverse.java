public class LLReverse{
    class Node{
        int data;
        Node next;

        Node(int data){
            this.data = data;
        }
    }
    Node First;
    Node Last;

    void addf(int data){
        Node node = new Node(data);
        if (First == null){
            First = Last = node;
        }else {
            node.next = First;
            First = node;
        }
    }

    void addl(int data){
        Node node = new Node(data);
        if (First == null){
            First = Last = node;
        }else {
            Last.next = node;
            Last = node;
        }
    }

    void removef{
        if (First == Last == null){

        }
    }
}
