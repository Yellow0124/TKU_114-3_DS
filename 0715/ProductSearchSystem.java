import java.util.Scanner;

/**
 * 1. 完整名稱搜尋
 * 請輸入完整的商品名稱: mOniTor
 * 【搜尋結果】
 * 商品編號: 3
 * 商品名稱: Monitor
 * 商品價格: $5200
 * 目前庫存: 5 個
 * 
 * 2. 完整名稱搜尋（查無此商品）
 * 請輸入完整的商品名稱: Laptop
 * 找不到該商品！
 * 
 * 3. 部分名稱搜尋
 * 請輸入要搜尋的部分名稱關鍵字: o
 * === 部分名稱搜尋結果 ===
 * 編號 1 | Keyboard 價格: $890 庫存: 12
 * 編號 2 | Mouse 價格: $490 庫存: 20
 * 編號 3 | Monitor 價格: $5200 庫存: 5
 * 
 * 4. 部分名稱搜尋（查無此商品）
 * 請輸入要搜尋的部分名稱關鍵字: xyz
 * === 部分名稱搜尋結果 ===
 * 沒有任何商品名稱包含關鍵字 "xyz"
 * 
 * 5. 顯示名稱最長的商品
 * 【名稱最長的商品】: USB Cable (長度: 9 個字元)
 * 
 * 6. 顯示商品名稱與關鍵字第一次出現的位置
 * 請輸入要查詢位置的關鍵字: board
 * === 關鍵字第一次出現位置 (不分大小寫) ===
 * 商品 "Keyboard" -> 關鍵字 "board" 首次出現於 Index: 3
 */

public class ProductSearchSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = { "Keyboard", "Mouse", "Monitor", "USB Cable", "Headset" };
        int[] prices = { 890, 490, 5200, 250, 1290 };
        int[] stocks = { 12, 20, 5, 30, 8 };

        int option = -1;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();
            sc.nextLine();

            if (option == 0) {
                System.out.println("已離開");
                break;
            }

            switch (option) {
                case 1:
                    printAllProducts(names, prices, stocks);
                    break;

                case 2:
                    System.out.print("請輸入完整的商品名稱: ");
                    String fullName = sc.nextLine();
                    searchExactName(names, prices, stocks, fullName);
                    break;

                case 3:
                    System.out.print("請輸入要搜尋的部分名稱關鍵字: ");
                    String keyword = sc.nextLine();
                    searchPartialName(names, prices, stocks, keyword);
                    break;

                case 4:
                    showLongestProductName(names);
                    break;

                case 5:
                    System.out.print("請輸入要查詢位置的關鍵字: ");
                    String searchKey = sc.nextLine();
                    showKeywordIndex(names, searchKey);
                    break;

                default:
                    System.out.println("無此選項，請重新輸入");
            }
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 商品名稱搜尋系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 完整名稱搜尋 (忽略大小寫與前後空白)");
        System.out.println("3. 部分名稱搜尋 (可顯示多筆結果)");
        System.out.println("4. 顯示名稱最長的商品");
        System.out.println("5. 顯示商品與關鍵字第一次出現的位置");
        System.out.println("0. 結束");
        System.out.print("請輸入選項: ");
    }

    public static void printAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n=== 全商品列表 ===");
        System.out.println("編號\t商品名稱\t\t價格\t庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + "\t" + names[i] + "\t\t$" + prices[i] + "\t" + stocks[i] + " 個");
        }
        System.out.println();
    }

    public static void searchExactName(String[] names, int[] prices, int[] stocks, String target) {
        String cleanTarget = target.trim();
        boolean found = false;

        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(cleanTarget)) {
                System.out.println("\n【搜尋結果】");
                System.out.println("商品編號: " + (i + 1));
                System.out.println("商品名稱: " + names[i]);
                System.out.println("商品價格: $" + prices[i]);
                System.out.println("目前庫存: " + stocks[i] + " 個\n");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("找不到該商品！\n");
        }
    }

    public static void searchPartialName(String[] names, int[] prices, int[] stocks, String keyword) {
        String lowerKeyword = keyword.trim().toLowerCase();
        boolean found = false;

        System.out.println("\n=== 部分名稱搜尋結果 ===");
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(lowerKeyword)) {
                System.out.println("編號 " + (i + 1) + " | " + names[i] + "\t價格: $" + prices[i] + "\t庫存: " + stocks[i]);
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有任何商品名稱包含關鍵字 \"" + keyword + "\"");
        }
        System.out.println();
    }

    public static void showLongestProductName(String[] names) {
        String longest = names[0];
        for (int i = 1; i < names.length; i++) {
            if (names[i].length() > longest.length()) {
                longest = names[i];
            }
        }
        System.out.println("\n【名稱最長的商品】: " + longest + " (長度: " + longest.length() + " 個字元)\n");
    }

    public static void showKeywordIndex(String[] names, String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        boolean found = false;

        System.out.println("\n=== 關鍵字第一次出現位置 (不分大小寫) ===");
        for (String name : names) {
            int index = name.toLowerCase().indexOf(lowerKeyword);
            if (index != -1) {
                System.out.println("商品 \"" + name + "\"\t-> 關鍵字 \"" + keyword + "\" 首次出現於 Index: " + index);
                found = true;
            }
        }

        if (!found) {
            System.out.println("沒有任何商品名稱內含有此關鍵字。");
        }
        System.out.println();
    }
}
