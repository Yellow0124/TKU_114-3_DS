public class LinkedListSearchRemove {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;

    public LinkedListSearchRemove() {
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

    public boolean contains(int target) {
        Node current = head;
        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean removeValue(int target) {
        if (head == null) {
            return false;
        }

        if (head.data == target) {
            head = head.next;
            return true;
        }

        Node previous = head;
        Node current = head.next;

        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("鏈結狀態：null (空串列)");
            return;
        }

        Node current = head;
        System.out.print("鏈結狀態：");
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        LinkedListSearchRemove list = new LinkedListSearchRemove();

        // 建立初始測試資料: 10 -> 20 -> 30 -> 40 -> 50
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);

        System.out.print("初始 ");
        list.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("搜尋測試");
        System.out.println("包含 30: " + list.contains(30));
        System.out.println("包含 99: " + list.contains(99));
        System.out.println("--------------------------------------------------");

        System.out.println("刪除頭節點 (10)");
        boolean r1 = list.removeValue(10);
        System.out.println("刪除結果: " + (r1 ? "成功" : "失敗"));
        list.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("刪除中間節點 (30)");
        boolean r2 = list.removeValue(30);
        System.out.println("刪除結果: " + (r2 ? "成功" : "失敗"));
        list.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("刪除最後一個節點 (50)");
        boolean r3 = list.removeValue(50);
        System.out.println("刪除結果: " + (r3 ? "成功" : "失敗"));
        list.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("刪除不存在的資料 (99)");
        boolean r4 = list.removeValue(99);
        System.out.println("刪除結果: " + (r4 ? "成功" : "失敗"));
        list.printList();
        System.out.println("--------------------------------------------------");

        System.out.println("邊界測試: 刪到只剩單一節點，再刪至空串列");
        list.removeValue(20);
        list.printList();
        list.removeValue(40);
        list.printList();

        System.out.println("\n對空串列嘗試刪除 100: ");
        boolean r5 = list.removeValue(100);
        System.out.println("刪除結果: " + (r5 ? "成功" : "失敗"));
        list.printList();
    }
}
