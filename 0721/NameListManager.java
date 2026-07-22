import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {

    private static ArrayList<String> nameList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();
            System.out.println("----------------------------------------");

            switch (choice) {
                case "1":
                    addName(scanner);
                    break;
                case "2":
                    searchName(scanner);
                    break;
                case "3":
                    modifyName(scanner);
                    break;
                case "4":
                    deleteName(scanner);
                    break;
                case "5":
                    listAllNames();
                    break;
                case "6":
                    System.out.println("已離開");
                    running = false;
                    break;
                default:
                    System.out.println(" 錯誤：請輸入有效的選項 (1-6)");
            }
            System.out.println("========================================");
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n=== 名單管理系統 ===");
        System.out.println("1. 新增姓名");
        System.out.println("2. 搜尋姓名");
        System.out.println("3. 修改姓名");
        System.out.println("4. 刪除姓名");
        System.out.println("5. 列出全部姓名");
        System.out.println("6. 離開系統");
    }

    // 1. 新增姓名 (不可以加入空白)
    private static void addName(Scanner scanner) {
        System.out.print("請輸入要新增的姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("失敗：姓名不可以為空白！");
            return;
        }

        nameList.add(name);
        System.out.println("成功：已加入姓名 \"" + name);
    }

    private static void searchName(Scanner scanner) {
        System.out.print("請輸入要搜尋的姓名: ");
        String target = scanner.nextLine().trim();

        int index = findNameIndex(target);
        if (index != -1) {
            System.out.println(" 搜尋結果：找到姓名 \"" + nameList.get(index) + "\" (位置第 " + (index + 1) + " 筆)");
        } else {
            System.out.println("搜尋結果：找不到姓名 \"" + target);
        }
    }

    private static void modifyName(Scanner scanner) {
        System.out.print("請輸入要修改的舊姓名: ");
        String oldName = scanner.nextLine().trim();

        int index = findNameIndex(oldName);
        if (index == -1) {
            System.out.println("修改失敗：名單中找不到姓名 \"" + oldName);
            return;
        }

        System.out.print("請輸入新姓名: ");
        String newName = scanner.nextLine().trim();

        if (newName.isEmpty()) {
            System.out.println("修改失敗：新姓名不可以為空白！");
            return;
        }

        String originalName = nameList.get(index);
        nameList.set(index, newName);
        System.out.println("成功：已將 \"" + originalName + "\" 修改為 \"" + newName);
    }

    private static void deleteName(Scanner scanner) {
        System.out.print("請輸入要刪除的姓名: ");
        String target = scanner.nextLine().trim();

        int index = findNameIndex(target);
        if (index != -1) {
            String removedName = nameList.remove(index);
            System.out.println("刪除成功：已移除姓名 \"" + removedName);
        } else {
            System.out.println("刪除失敗：名單中找不到姓名 \"" + target);
        }
    }

    private static void listAllNames() {
        if (nameList.isEmpty()) {
            System.out.println("目前名單內無任何姓名");
            return;
        }

        System.out.println("所有姓名清單 (共 " + nameList.size() + " 筆)：");
        for (int i = 0; i < nameList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + nameList.get(i));
        }
    }

    private static int findNameIndex(String target) {
        if (target == null || target.isEmpty())
            return -1;
        for (int i = 0; i < nameList.size(); i++) {
            if (nameList.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}