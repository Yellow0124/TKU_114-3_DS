import java.util.Scanner;

public class TaskLinkedListSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addNormalTask("T001", "撰寫資料結構作業");
        taskList.addNormalTask("T002", "預習 Linked List 範例");
        taskList.addUrgentTask("T000", "回覆重要郵件 (緊急)");

        boolean running = true;
        while (running) {
            System.out.println("\n=== 工作項目管理 ===");
            System.out.println("1. 新增緊急工作 (插隊至最前端)");
            System.out.println("2. 新增一般工作 (排隊至尾端)");
            System.out.println("3. 標示工作為【已完成】");
            System.out.println("4. 刪除工作");
            System.out.println("5. 列出未完成工作");
            System.out.println("6. 顯示所有工作與統計資訊");
            System.out.println("7. 離開系統");
            System.out.print("請選擇功能 (1-7): ");

            String choice = scanner.nextLine().trim();
            System.out.println("--------------------------------------------------");

            switch (choice) {
                case "1":
                    System.out.print("請輸入緊急工作代碼: ");
                    String uId = scanner.nextLine().trim();
                    System.out.print("請輸入工作說明: ");
                    String uDesc = scanner.nextLine().trim();
                    taskList.addUrgentTask(uId, uDesc);
                    break;

                case "2":
                    System.out.print("請輸入一般工作代碼: ");
                    String nId = scanner.nextLine().trim();
                    System.out.print("請輸入工作說明: ");
                    String nDesc = scanner.nextLine().trim();
                    taskList.addNormalTask(nId, nDesc);
                    break;

                case "3":
                    System.out.print("請輸入欲完成的工作代碼: ");
                    String doneId = scanner.nextLine().trim();
                    taskList.markTaskCompleted(doneId);
                    break;

                case "4":
                    System.out.print("請輸入欲刪除的工作代碼: ");
                    String delId = scanner.nextLine().trim();
                    taskList.removeTask(delId);
                    break;

                case "5":
                    taskList.listPendingTasks();
                    break;

                case "6":
                    taskList.displayAllAndStatistics();
                    break;

                case "7":
                    System.out.println("已離開");
                    running = false;
                    break;

                default:
                    System.out.println("錯誤: 請輸入有效的選單數字 (1-7)");
            }
            System.out.println("==================================================");
        }

        scanner.close();
    }
}