import java.util.ArrayList;
import java.util.Scanner;

public class EquipmentManager {
    private static ArrayList<Equipment> equipmentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initDefaultData();

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("請選擇功能(1-6): ");
            String choice = sc.nextLine().trim();
            System.out.println("--------------------------------------------------");

            switch (choice) {
                case "1":
                    addEquipment(sc);
                    break;
                case "2":
                    searchEquipmentById(sc);
                    break;
                case "3":
                    borrowEquipment(sc);
                    break;
                case "4":
                    returnEquipment(sc);
                    break;
                case "5":
                    listAvailableEquipment();
                    break;
                case "6":
                    System.out.println("已離開");
                    running = false;
                    break;
                default:
                    System.out.println("⚠️ 錯誤：請輸入有效的選單數字 (1-6)！");
            }
        }
        sc.close();
    }

    private static void initDefaultData() {
        equipmentList.add(new Equipment("EQ001", "MacBook Pro"));
        equipmentList.add(new Equipment("EQ002", "Projector"));
        equipmentList.add(new Equipment("EQ003", "iPad Air"));
    }

    private static void showMenu() {
        System.out.println("\n=== 設備物件集合 ===");
        System.out.println("1. 新增設備");
        System.out.println("2. 依代碼搜尋");
        System.out.println("3. 借出設備");
        System.out.println("4. 歸還設備");
        System.out.println("5. 列出所有可借用設備");
        System.out.println("6. 離開系統");
    }

    private static void addEquipment(Scanner sc) {
        System.out.print("請輸入新設備代碼: ");
        String id = sc.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("失敗: 設備代碼不可以為空白");
            return;
        }

        if (findEquipmentById(id) != null) {
            System.out.println("失敗：設備代碼 \"" + id + "\" 已存在，代碼不可重複");
            return;
        }

        System.out.print("請輸入新設備名稱: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("失敗: 設備名稱不可以為空白");
            return;
        }

        Equipment newEq = new Equipment(id, name);
        equipmentList.add(newEq);
        System.out.println("成功：已新增設備 -> " + newEq);
    }

    private static void searchEquipmentById(Scanner sc) {
        System.out.print("請輸入欲尋找的設備代碼: ");
        String id = sc.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq != null) {
            System.out.println("搜尋結果: \n  " + eq);
        } else {
            System.out.println("搜尋結果: 找不到代碼為 \"" + id + "\" 的設備");
        }
    }

    private static void borrowEquipment(Scanner sc) {
        System.out.print("請輸入預借出的設備代碼: ");
        String id = sc.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("借出失敗：找不到代碼為 \"" + id + "\" 的設備");
            return;
        }

        if (eq.borrow()) {
            System.out.println("借出成功！已借出設備: " + eq.getName());
        } else {
            System.out.println("借出失敗：設備 \"" + eq.getName() + "\" 目前已被他人借出中");
        }
    }

    private static void returnEquipment(Scanner sc) {
        System.out.print("請輸入欲歸還的設備代碼: ");
        String id = sc.nextLine().trim();

        Equipment eq = findEquipmentById(id);
        if (eq == null) {
            System.out.println("搜尋結果: 找不到代碼為 \"" + id + "\" 的設備");
            return;
        }

        if (eq.returnEquipment()) {
            System.out.println("歸還成功！已歸還設備: " + eq.getName());
        } else {
            System.out.println("歸還失敗：設備 \"" + eq.getName() + "\" 尚未被借出，無需歸還");
        }
    }

    private static void listAvailableEquipment() {
        System.out.println("目前可用設備清單: ");
        boolean hasAvailable = false;
        int count = 0;

        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) {
                count++;
                System.out.println("  " + count + ". " + eq);
                hasAvailable = true;
            }
        }

        if (!hasAvailable) {
            System.out.println("目前無任何可借用的設備");
        }
    }

    private static Equipment findEquipmentById(String id) {
        if (id == null || id.isEmpty())
            return null;

        for (Equipment eq : equipmentList) {
            if (eq.getId().equalsIgnoreCase(id)) {
                return eq;
            }
        }
        return null;
    }
}
