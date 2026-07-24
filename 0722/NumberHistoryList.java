public class NumberHistoryList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public NumberHistoryList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("[前端新增] 加入: " + value);
    }

    public void addLast(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("[尾端新增] 加入: " + value);
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
            size--;
            System.out.println("[刪除成功] 移除數值: " + target);
            return true;
        }

        Node previous = head;
        Node current = head.next;

        while (current != null) {
            if (current.data == target) {
                previous.next = current.next;
                size--;
                System.out.println("[刪除成功] 移除數值: " + target);
                return true;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("[刪除失敗] 串列中找不到數值: " + target);
        return false;
    }

    public void printList() {
        if (head == null) {
            System.out.println("數字紀錄內容：null (空串列)");
            return;
        }

        System.out.print("數字紀錄內容: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " -> " : " -> null\n"));
            current = current.next;
        }
    }

    public void printStatistics() {
        System.out.println("--------------------------------------------------");
        System.out.println("數字紀錄統");

        if (head == null) {
            System.out.println("狀態：目前為空串列，無法計算數值統計。");
            System.out.println("Size (總筆數): 0");
            System.out.println("總和 (Sum): 0");
            System.out.println("最大值 (Max): N/A (無資料)");
            System.out.println("最小值 (Min): N/A (無資料)");
            System.out.println("--------------------------------------------------");
            return;
        }

        long sum = 0;
        int max = head.data;
        int min = head.data;

        Node current = head;
        while (current != null) {
            sum += current.data;
            if (current.data > max)
                max = current.data;
            if (current.data < min)
                min = current.data;
            current = current.next;
        }

        System.out.println("Size (總筆數): " + size);
        System.out.println("總和 (Sum): " + sum);
        System.out.println("最大值 (Max): " + max);
        System.out.println("最小值 (Min): " + min);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        NumberHistoryList history = new NumberHistoryList();

        System.out.println("1. 測試空串列統計能力");
        history.printList();
        history.printStatistics();

        System.out.println("2. 尾端新增 50, 20, 80");
        history.addLast(50);
        history.addLast(20);
        history.addLast(80);
        history.printList();

        System.out.println("3. 前端新增 100, 10");
        history.addFirst(100);
        history.addFirst(10);
        history.printList();
        history.printStatistics();

        System.out.println("4. 搜尋測試 (包含 50 與 999)");
        System.out.println("搜尋 50 存在？ " + (history.contains(50) ? "存在" : "不存在"));
        System.out.println("搜尋 999 存在？ " + (history.contains(999) ? "存在" : "不存在"));

        System.out.println("5. 刪除頭節點 (10)");
        history.removeValue(10);
        history.printList();

        System.out.println("6. 刪除中間節點 (50)");
        history.removeValue(50);
        history.printList();

        System.out.println("7. 刪除不存在的資料 (999)");
        history.removeValue(50);
        history.printList();

        System.out.println("8. 刪除剩餘所有節點至空串列 (100, 20, 80)");
        history.removeValue(100);
        history.removeValue(20);
        history.removeValue(80);
        history.printList();
        history.printStatistics();
    }
}
