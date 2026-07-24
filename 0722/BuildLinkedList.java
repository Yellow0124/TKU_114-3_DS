public class BuildLinkedList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        Node n3 = new Node(30);
        Node n4 = new Node(40);

        Node head = n1;
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println("正常串列走訪");
        traverseAndCalculate(head);

        System.out.println("空串列邊界處理");
        Node emptyHead = null;
        traverseAndCalculate(emptyHead);
    }

    public static void traverseAndCalculate(Node head) {
        if (head == null) {
            System.out.println("此串列為空串列");
            System.out.println("總結點數: 0");
            System.out.println("節點總和: 0");
            return;
        }

        Node current = head;
        int count = 0;
        int sum = 0;

        System.out.print("鏈結串列內容: ");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : " -> null\n"));
            sum += current.data;
            count++;
            current = current.next;
        }
        System.out.println("總結點數: " + count);
        System.out.println("節點總和: " + sum);
    }
}
