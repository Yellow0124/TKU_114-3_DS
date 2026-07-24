public class TaskLinkedList {
    private TaskNode head;
    private int size;

    public TaskLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public TaskNode findById(String id) {
        if (id == null || id.trim().isEmpty())
            return null;

        TaskNode current = head;
        while (current != null) {
            if (current.getId().equalsIgnoreCase(id.trim())) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean addUrgentTask(String id, String description) {
        if (!validateNewTask(id, description))
            return false;

        TaskNode newNode = new TaskNode(id, description);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println(" 成功加入【緊急工作】至最前端: " + newNode.getDescription());
        return true;
    }

    public boolean addNormalTask(String id, String description) {
        if (!validateNewTask(id, description))
            return false;

        TaskNode newNode = new TaskNode(id, description);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("成功加入【一般工作】至尾端: " + newNode.getDescription());
        return true;
    }

    private boolean validateNewTask(String id, String description) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("新增失敗: 工作代碼不可為空白");
            return false;
        }
        if (findById(id) != null) {
            System.out.println("新增失敗: 工作代碼 \"" + id + "\" 已存在，不可重複");
            return false;
        }
        return true;
    }

    public boolean markTaskCompleted(String id) {
        TaskNode task = findById(id);
        if (task == null) {
            System.out.println("操作失敗: 找不到代碼為 \"" + id + "\" 的工作");
            return false;
        }

        if (task.isCompleted()) {
            System.out.println("工作 [" + task.getId() + "] 原本就已經是完成狀態");
            return true;
        }

        task.setCompleted(true);
        System.out.println("成功將工作 [" + task.getId() + "] " + task.getDescription() + " 標示為【已完成");
        return true;
    }

    public boolean removeTask(String id) {
        if (head == null) {
            System.out.println("刪除失敗: 目前無任何工作項目");
            return false;
        }

        if (id == null || id.trim().isEmpty()) {
            System.out.println("刪除失敗: 作代碼不可為空白");
            return false;
        }

        String searchId = id.trim();

        if (head.getId().equalsIgnoreCase(searchId)) {
            TaskNode removed = head;
            head = head.next;
            size--;
            System.out.println("成功刪除工作: " + removed.getDescription());
            return true;
        }

        TaskNode previous = head;
        TaskNode current = head.next;

        while (current != null) {
            if (current.getId().equalsIgnoreCase(searchId)) {
                previous.next = current.next;
                size--;
                System.out.println("成功刪除工作: " + current.getDescription());
                return true;
            }
            previous = current;
            current = current.next;
        }

        System.out.println("刪除失敗：找不到代碼為 \"" + searchId + "\" 的工作");
        return false;
    }

    public void listPendingTasks() {
        if (head == null) {
            System.out.println("目前工作清單為空");
            return;
        }

        System.out.println("未完成工作清單: ");
        TaskNode current = head;
        int count = 0;

        while (current != null) {
            if (!current.isCompleted()) {
                count++;
                System.out.println("  " + count + ". " + current);
            }
            current = current.next;
        }

        if (count == 0) {
            System.out.println("目前所有工作皆已完成");
        }
    }

    public void displayAllAndStatistics() {
        System.out.println("------------------------------------------------------------------");
        System.out.println("【工作項目統計】");

        if (head == null) {
            System.out.println("工作總數: 0");
            System.out.println("未完成數量: 0");
            System.out.println("已完成數量: 0");
            System.out.println("------------------------------------------------------------------");
            return;
        }

        int pendingCount = 0;
        int completedCount = 0;

        System.out.println(" 所有工作清單內容: ");
        TaskNode current = head;
        int index = 1;

        while (current != null) {
            System.out.println("  " + index + ". " + current);
            if (current.isCompleted()) {
                completedCount++;
            } else {
                pendingCount++;
            }
            current = current.next;
            index++;
        }

        System.out.println("\n工作總數: " + size);
        System.out.println("未完成數量: " + pendingCount);
        System.out.println("已完成數量: " + completedCount);
        System.out.println("------------------------------------------------------------------");
    }
}