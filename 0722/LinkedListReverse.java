public class LinkedListReverse {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public LinkedListReverse() {
        this.head = null;
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    public void reverse() {
        if (head == null || head.next == null) {
            return;
        }

        Node previous = null;
        Node current = head;
        Node nextNode = null;

        while (current != null) {
            nextNode = current.next;
            current.next = previous;
            current = nextNode;
        }

        head = previous;
    }

    public void printList() {
        if (head == null) {
            System.out.println("null (空串列)");
            return;
        }

        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        System.out.println("多節點反轉");
        LinkedListReverse list1 = new LinkedListReverse();
        list1.addLast(10);
        list1.addLast(20);
        list1.addLast(30);
        list1.addLast(40);
        list1.addLast(50);

        System.out.print("反轉前: ");
        list1.printList();

        list1.reverse();

        System.out.print("反轉後: ");
        list1.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("單一節點");
        LinkedListReverse list2 = new LinkedListReverse();
        list2.addLast(100);

        System.out.print("反轉前: ");
        list2.printList();

        list2.reverse();

        System.out.print("反轉後: ");
        list2.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("空串列");
        LinkedListReverse list3 = new LinkedListReverse();

        System.out.print("反轉前: ");
        list3.printList();

        list3.reverse();

        System.out.print("反轉後: ");
        list3.printList();
    }
}
