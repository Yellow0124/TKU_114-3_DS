import java.util.ArrayDeque;
import java.util.Deque;

public class DeliveryProcessingSystem {

    private Deque<DeliveryTask> waitingQueue;
    private Deque<DeliveryTask> completedStack;

    public DeliveryProcessingSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.completedStack = new ArrayDeque<>();
    }

    public void addTask(String taskId, String address, String itemName) {
        if (taskId == null || taskId.trim().isEmpty()) {
            System.out.println("[新增失敗] 任務編號不可為空白");
            return;
        }

        DeliveryTask task = new DeliveryTask(taskId, address, itemName);
        waitingQueue.offer(task);
        System.out.println("[新增任務] " + task);
    }

    public DeliveryTask completeNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[完成失敗] 目前沒有等待配送的工作");
            return null;
        }

        DeliveryTask task = waitingQueue.poll();
        completedStack.push(task);
        System.out.println("[配送完成] " + task);
        return task;
    }

    public DeliveryTask peekNextTask() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[下一位] 目前無待配送任務");
            return null;
        }

        DeliveryTask next = waitingQueue.peek();
        System.out.println("[下一筆待配送] " + next);
        return next;
    }

    public DeliveryTask undoLastCompleted() {
        if (completedStack.isEmpty()) {
            System.out.println("[Undo 失敗] 目前無已完成的任務可供復原");
            return null;
        }

        DeliveryTask task = completedStack.pop();
        waitingQueue.offer(task);

        System.out.println("[復原任務] 任務 " + task.getTaskId() + " 已從完成紀錄撤回，重新回到等待佇列尾端");
        return task;
    }

    public int getWaitingCount() {
        return waitingQueue.size();
    }

    public int getCompletedCount() {
        return completedStack.size();
    }

    public void printSystemStatus() {
        System.out.println("--------------------------------------------------");
        System.out.println("【配送系統當前狀態報告】");
        System.out.println("  待配送數量 (Queue Size): " + getWaitingCount() + " 件");
        System.out.println("  已完成數量 (Stack Size): " + getCompletedCount() + " 件");

        System.out.println("\n  [待配送佇列 (依處理順序)]: ");
        if (waitingQueue.isEmpty()) {
            System.out.println("    (無待處理任務)");
        } else {
            int idx = 1;
            for (DeliveryTask t : waitingQueue) {
                System.out.println("    " + idx + ". " + t);
                idx++;
            }
        }

        System.out.println("\n  [已完成歷史紀錄 (最近完成優先)]: ");
        if (completedStack.isEmpty()) {
            System.out.println("    (無已完成紀錄)");
        } else {
            int idx = 1;
            for (DeliveryTask t : completedStack) {
                System.out.println("    " + idx + ". " + t);
                idx++;
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("【測試 1】空佇列/空堆疊操作");
        system.completeNextTask();
        system.undoLastCompleted();
        system.peekNextTask();

        System.out.println("\n【測試 2】新增 3 筆配送任務");
        system.addTask("T001", "淡水區中正路100號", "筆記型電腦");
        system.addTask("T002", "台北市信義路五段7號", "人體工學椅");
        system.addTask("T003", "新莊區中興街20號", "機械鍵盤");

        System.out.println("\n【測試 3】查看下一筆待配送任務");
        system.peekNextTask();
        system.printSystemStatus();

        System.out.println("\n【測試 4】完成前 2 筆配送任務");
        system.completeNextTask();
        system.completeNextTask();

        system.printSystemStatus();

        System.out.println("\n【測試 5】復原最近一次完成的任務 (T002)");
        system.undoLastCompleted();

        system.printSystemStatus();

        System.out.println("\n【測試 6】依序完成剩餘所有任務");
        system.completeNextTask();
        system.completeNextTask();
        system.completeNextTask();

        System.out.println();
        system.printSystemStatus();
    }
}