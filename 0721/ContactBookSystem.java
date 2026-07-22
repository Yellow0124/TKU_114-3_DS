import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {

    private static ArrayList<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initDefaultContacts();

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("請選擇功能 (1-6): ");
            String choice = scanner.nextLine().trim();
            System.out.println("--------------------------------------------------");

            switch (choice) {
                case "1":
                    addContact(scanner);
                    break;
                case "2":
                    searchContact(scanner);
                    break;
                case "3":
                    modifyPhone(scanner);
                    break;
                case "4":
                    deleteContact(scanner);
                    break;
                case "5":
                    listAllContacts();
                    break;
                case "6":
                    System.out.println("已離開");
                    running = false;
                    break;
                default:
                    System.out.println("錯誤：請輸入有效的選單數字(1-6)");
            }
            System.out.println("==================================================");
        }

        scanner.close();
    }

    private static void initDefaultContacts() {
        contactList.add(new Contact("C001", "Alice", "0912-345678", "alice@example.com"));
        contactList.add(new Contact("C002", "Bob", "0987-654321", "bob@example.com"));
    }

    private static void showMenu() {
        System.out.println("\n=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人 (依代碼或姓名)");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出完整清單");
        System.out.println("6. 離開系統");
    }

    public static void addContact(Scanner scanner) {
        System.out.print("請輸入聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("失敗：代碼不可以為空白");
            return;
        }

        if (findContactById(id) != null) {
            System.out.println("失敗：代碼 \"" + id + "\" 已存在，代碼不可重複");
            return;
        }

        System.out.print("請輸入姓名: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("失敗：姓名不可以為空白");
            return;
        }

        System.out.print("請輸入電話: ");
        String phone = scanner.nextLine().trim();

        System.out.print("請輸入 Email: ");
        String email = scanner.nextLine().trim();

        Contact contact = new Contact(id, name, phone, email);
        contactList.add(contact);
        System.out.println("✅ 成功：已新增聯絡人 -> " + contact);
    }

    public static void searchContact(Scanner scanner) {
        System.out.print("請輸入欲搜尋的代碼或姓名: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println("請輸入有效的關鍵字！");
            return;
        }

        boolean found = false;
        System.out.println("搜尋結果：");
        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                System.out.println("  " + c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("  找不到符合 \"" + keyword + "\" 的聯絡人。");
        }
    }

    public static void modifyPhone(Scanner scanner) {
        System.out.print("請輸入欲修改電話的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact contact = findContactById(id);
        if (contact == null) {
            System.out.println("失敗：找不到代碼為 \"" + id + "\" 的聯絡人");
            return;
        }

        System.out.println("目前聯絡人資訊：" + contact);
        System.out.print("請輸入新電話號碼: ");
        String newPhone = scanner.nextLine().trim();

        if (contact.setPhone(newPhone)) {
            System.out.println(" 成功：電話已更新為 " + contact.getPhone());
        } else {
            System.out.println("失敗：新電話號碼不可以為空白");
        }
    }

    public static void deleteContact(Scanner scanner) {
        System.out.print("請輸入欲刪除的聯絡人代碼: ");
        String id = scanner.nextLine().trim();

        Contact contact = findContactById(id);
        if (contact == null) {
            System.out.println("刪除失敗：找不到代碼為 \"" + id + "\" 的聯絡人");
            return;
        }

        contactList.remove(contact);
        System.out.println("刪除成功：已移除聯絡人 \"" + contact.getName() + "\" (" + contact.getId() + ")");
    }

    public static void listAllContacts() {
        if (contactList.isEmpty()) {
            System.out.println("ℹ目前通訊錄內無任何聯絡人");
            return;
        }

        System.out.println("完整聯絡人清單 (共 " + contactList.size() + " 筆): ");
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + contactList.get(i));
        }
    }

    public static Contact findContactById(String id) {
        if (id == null || id.isEmpty())
            return null;

        for (Contact c : contactList) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}