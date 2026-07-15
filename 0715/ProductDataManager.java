import java.util.Scanner;

/**
 * 1. 顯示全部解析商品
 * ================= 商品數據報表 =================
 * 編號 商品名稱 價格 庫存
 * ------------------------------------------------
 * 1 Keyboard $890 12
 * 2 Mouse $490 20
 * 3 Monitor $5200 5
 * 4 USB Cable $250 30
 * 5 Headset $1290 8
 * ================================================
 * 
 * 2. 完整名稱搜尋
 * 請輸入完整的商品名稱：mOuSe
 * [精確搜尋結果]
 * 商品編號: 2
 * 商品名稱: Mouse
 * 商品價格: $490
 * 目前庫存: 20
 * 
 * 3. 部分名稱搜尋
 * 請輸入部分商品名稱關鍵字：o
 * === 部分名稱搜尋結果 ===
 * 編號 1 | Keyboard 價格: $890 庫存: 12
 * 編號 2 | Mouse 價格: $490 庫存: 20
 * 編號 3 | Monitor 價格: $5200 庫存: 5
 * 
 * 4. 新增資料 - 成功
 * 請輸入新商品文字 (格式: 名稱,價格,庫存)：
 * Speaker,1500,15
 * 【解析成功】商品 "Speaker" 已成功加入系統！
 * 
 * 5. 新增資料 - 格式錯誤（欄位數量不足）
 * 請輸入新商品文字 (格式: 名稱,價格,庫存)：
 * Hub,600
 * 【解析失敗】格式錯誤：必須剛好有 3 個欄位（名稱,價格,庫存）。您輸入了 2 個欄位。
 * 
 * 6. 新增資料 - 格式錯誤（價格非數字）
 * Hub,abc,10
 * 【解析失敗】數字轉換錯誤：價格或庫存必須為整數。原因: For input string: "abc"
 * 
 * 7. 新增資料 - 格式錯誤（數值小於 0）
 * Hub,-100,5
 * 【解析失敗】數值錯誤：價格與庫存不得小於 0。
 * 
 * 8. 庫存總價值計算
 * === 庫存價值明細 ===
 * Keyboard : 12 個 x $890 = 庫存價值 $10680
 * Mouse : 20 個 x $490 = 庫存價值 $9800
 * USB Cable : 30 個 x $250 = 庫存價值 $7500
 * Headset : 8 個 x $1290 = 庫存價值 $10320
 * Speaker : 15 個 x $1500 = 庫存價值 $22500
 * ----------------------------------------
 * 目前倉庫全商品總庫存價值為: $86800
 */

public class ProductDataManager {
    private static String[] names = new String[100];
    private static int[] prices = new int[100];
    private static int[] stocks = new int[100];
    private static int productCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] records = {
                "Keyboard,890,12",
                "Mouse,490,20",
                "Monitor,5200,5",
                "USB Cable,250,30",
                "Headset,1290,8"
        };

        for (String record : records) {
            parseAndAddRecord(record, false);
        }

        int option = -1;
        while (option != 0) {
            printMenu();
            option = sc.nextInt();
            sc.nextLine();

            if (option == 0) {
                System.out.println("系統已關閉，謝謝使用！");
                break;
            }

            switch (option) {
                case 1:
                    printAllProducts();
                    break;
                case 2:
                    System.out.print("請輸入完整的商品名稱：");
                    String fullName = sc.nextLine();
                    searchExactName(fullName);
                    break;
                case 3:
                    System.out.print("請輸入部分商品名稱關鍵字：");
                    String keyword = sc.nextLine();
                    searchPartialName(keyword);
                    break;
                case 4:
                    printLowStock();
                    break;
                case 5:
                    printTotalValue();
                    break;
                case 6:
                    System.out.println("請輸入新商品文字 (格式: 名稱,價格,庫存)：");
                    String newRecord = sc.nextLine();
                    parseAndAddRecord(newRecord, true);
                    break;
                default:
                    System.out.println("無此選項，請重新輸入。\n");
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 商品文字資料管理器 ===");
        System.out.println("1. 顯示全部商品表格");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (模糊搜尋)");
        System.out.println("4. 顯示低庫存商品 (< 10)");
        System.out.println("5. 顯示庫存總價值");
        System.out.println("6. 輸入新商品文字資料");
        System.out.println("0. 結束");
        System.out.print("請輸入選項: ");
    }

    public static void parseAndAddRecord(String record, boolean showSuccessMsg) {
        if (record == null || record.trim().isEmpty()) {
            System.out.println("【解析失敗】輸入內容不可為空白。");
            return;
        }

        String[] parts = record.split(",");

        if (parts.length != 3) {
            System.out.println("【解析失敗】格式錯誤：必須剛好有 3 個欄位（名稱,價格,庫存）。您輸入了 " + parts.length + " 個欄位。");
            return;
        }

        try {
            String name = parts[0].trim();
            int price = Integer.parseInt(parts[1].trim());
            int stock = Integer.parseInt(parts[2].trim());

            if (name.isEmpty()) {
                System.out.println("【解析失敗】格式錯誤：商品名稱不能為空。");
                return;
            }

            if (price < 0 || stock < 0) {
                System.out.println("【解析失敗】數值錯誤：價格與庫存不得小於 0。");
                return;
            }

            if (productCount >= names.length) {
                System.out.println("【解析失敗】容量不足：系統最多只能儲存 " + names.length + " 筆商品。");
                return;
            }

            names[productCount] = name;
            prices[productCount] = price;
            stocks[productCount] = stock;
            productCount++;

            if (showSuccessMsg) {
                System.out.println("【解析成功】商品 \"" + name + "\" 已成功加入系統！\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("【解析失敗】數字轉換錯誤：價格或庫存必須為整數。原因: " + e.getMessage());
        }
    }

    public static void printAllProducts() {
        System.out.println("\n================= 商品數據報表 =================");
        System.out.printf("%-5s\t%-15s\t%-10s\t%-10s%n", "編號", "商品名稱", "價格", "庫存");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < productCount; i++) {
            System.out.printf("%-5d\t%-15s\t$%-10d\t%-10d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
        System.out.println("================================================\n");
    }

    public static void searchExactName(String target) {
        String cleanTarget = target.trim();
        boolean found = false;

        for (int i = 0; i < productCount; i++) {
            if (names[i].equalsIgnoreCase(cleanTarget)) {
                System.out.println("\n[精確搜尋結果]");
                System.out.println("商品編號: " + (i + 1));
                System.out.println("商品名稱: " + names[i]);
                System.out.println("商品價格: $" + prices[i]);
                System.out.println("目前庫存: " + stocks[i]);
                System.out.println();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到該商品！\n");
        }
    }

    public static void searchPartialName(String keyword) {
        String lowerKeyword = keyword.trim().toLowerCase();
        boolean found = false;

        System.out.println("\n=== 部分名稱搜尋結果 ===");
        for (int i = 0; i < productCount; i++) {
            if (names[i].toLowerCase().contains(lowerKeyword)) {
                System.out.printf("編號 %d | %-12s\t價格: $%d\t庫存: %d%n", (i + 1), names[i], prices[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("沒有商品名稱包含關鍵字 \"" + keyword + "\"");
        }
        System.out.println();
    }

    public static void printLowStock() {
        System.out.println("\n--- 低庫存預警 (庫存 < 10) ---");
        boolean found = false;
        for (int i = 0; i < productCount; i++) {
            if (stocks[i] < 10) {
                System.out.printf("編號 %d | %-12s - 目前庫存僅剩: %d 個%n", (i + 1), names[i], stocks[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前所有商品庫存充足");
        }
        System.out.println();
    }

    public static void printTotalValue() {
        System.out.println("\n=== 庫存價值明細 ===");
        int totalValue = 0;
        for (int i = 0; i < productCount; i++) {
            int itemValue = prices[i] * stocks[i];
            totalValue += itemValue;
            System.out.printf("%-12s\t: %d 個 x $%d = 庫存價值 $%d%n", names[i], stocks[i], prices[i], itemValue);
        }
        System.out.println("----------------------------------------");
        System.out.println("目前倉庫全商品總庫存價值為: $" + totalValue + "\n");
    }
}
