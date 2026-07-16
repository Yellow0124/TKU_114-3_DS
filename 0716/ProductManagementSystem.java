import java.util.Scanner;

/**
 * 1. 顯示全部商品
 * [所有商品清單]
 * [1] iPhone 15，價格：29900，庫存：15
 * [2] MacBook，價格：35900，庫存：8
 * [3] iPad Pro，價格：27900，庫存：12
 * [4] AirPods Pro，價格：7490，庫存：20
 * [5] Apple Watch，價格：13500，庫存：5
 * 
 * 2. 依完整名稱搜尋
 * 請選擇操作功能(1-9): 2
 * 請輸入完整商品名稱: mAcBoOk aIr
 * 商品資訊: MacBook Air，價格：35900，庫存：8
 * 
 * 3. 新增商品(預防空字串)
 * 請選擇操作功能(1-9): 3
 * 請輸入新商品名稱:
 * 請輸入新商品價格: 200
 * 請輸入初始庫存量: 5
 * 已成功加入商品: Unnamed，價格：200，庫存：5
 * 
 * 4. 新增商品(預防重複名稱)
 * 請選擇操作功能(1-9): 3
 * 請輸入新商品名稱: iphone 15
 * [新增失敗] 已存在同名商品，不可重複新增
 * 
 * 5. 出售商品(預防超額)
 * 請選擇操作功能(1-9): 4
 * 請輸入欲出售的商品名稱: Apple Watch
 * 目前庫存為 5，請輸入欲出售數量: 10
 * [出售失敗] 出售數量必須大於 0，且不可大於目前庫存
 * 
 * 6. 出售商品(預防負數)
 * 請輸入欲出售的商品名稱: iPad Pro
 * 目前庫存為 12，請輸入欲出售數量: -5
 * [出售失敗] 出售數量必須大於 0，且不可大於目前庫存
 * 
 * 7. 補充庫存
 * 請選擇操作功能(1-9): 5
 * 目前庫存為 12，請輸入欲補貨數量: 0
 * [補貨失敗] 補貨數量必須大於 0
 * 
 * 8. 修改商品價格
 * 請選擇操作功能(1-9): 6
 * 請輸入欲修改價格的商品名稱: AirPods Pro
 * 目前價格為 7490，請輸入新價格: -1
 * [修改失敗] 修改之商品價格必須大於 0
 * 
 * 9. 顯示低庫存商品
 * 請選擇操作功能(1-9): 7
 * [低庫存警示清單 (庫存少於 10)]
 * MacBook Air，價格：35900，庫存：8
 * Apple Watch，價格：13500，庫存：5
 * 
 * 10. 顯示全部庫存總價值
 * 請選擇操作功能(1-9): 8
 * [全部庫存總價值]
 * 目前倉庫中商品總品項數: 5 種
 * 所有商品的總資產價值: $1287800 元
 * ProductManagementSystem
 */

public class ProductManagementSystem {
    private static Product[] products = new Product[10];
    private static int productCount = 0;
    private static int addCount = 0;
    private static int sellCount = 0;
    private static int restockCount = 0;
    private static int priceChangeCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        initDefaultProducts();

        System.out.println("=== 物件導向商品管理系統 ===");
        boolean running = true;

        while (running) {
            showMenu();
            System.out.print("請選擇操作功能(1-9): ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    displayAllProducts();
                    break;
                case "2":
                    searchProductByName(sc);
                    break;
                case "3":
                    addNewProduct(sc);
                    break;
                case "4":
                    sellProduct(sc);
                    break;
                case "5":
                    restockProduct(sc);
                    break;
                case "6":
                    changeProductPrice(sc);
                    break;
                case "7":
                    displayLowStockProducts();
                    break;
                case "8":
                    displayTotalInventoryValue();
                    break;
                case "9":
                    showSummaryAndExit();
                    running = false;
                    break;
                default:
                    System.out.println("輸入錯誤！請輸入 1 至 9 的數字");
            }
        }
        sc.close();
    }

    private static void initDefaultProducts() {
        products[0] = new Product("iPhone 15", 29900, 15);
        products[1] = new Product("MacBook Air", 35900, 8);
        products[2] = new Product("iPad Pro", 27900, 12);
        products[3] = new Product("AirPods Pro", 7490, 20);
        products[4] = new Product("Apple Watch", 13500, 5);
        productCount = 5;
    }

    private static void showMenu() {
        System.out.println("\n[系統功能選單]");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依完整名稱搜尋");
        System.out.println("3. 新增商品");
        System.out.println("4. 出售商品");
        System.out.println("5. 補充庫存");
        System.out.println("6. 修改商品價格");
        System.out.println("7. 顯示低庫存商品 (少於 10)");
        System.out.println("8. 顯示全部庫存總價值");
        System.out.println("9. 結束並顯示操作摘要");
    }

    private static void displayAllProducts() {
        if (productCount == 0) {
            System.out.println("目前系統內無任何商品");
            return;
        }
        System.out.println("[所有商品清單]");
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null) {
                System.out.printf("[%d] %s%n", (i + 1), products[i].toString());
            }
        }
    }

    private static void searchProductByName(Scanner sc) {
        System.out.print("請輸入完整商品名稱: ");
        String searchName = sc.nextLine();

        Product found = findProductHelper(searchName);
        if (found != null) {
            System.out.println("商品資訊: " + found);
        } else {
            System.out.println("找不到名稱為 \"" + searchName.trim() + "\" 的商品");
        }
    }

    private static void addNewProduct(Scanner sc) {
        if (productCount >= products.length) {
            System.out.println("[新增失敗] 空間已滿 (上限 10 項商品)，無法再新增");
            return;
        }

        System.out.print("請輸入新商品名稱: ");
        String nameInput = sc.nextLine();

        if (findProductHelper(nameInput) != null) {
            System.out.println("[新增失敗] 已存在同名商品，不可重複新增");
            return;
        }

        System.out.print("請輸入新商品價格: ");
        int priceInput = Integer.parseInt(sc.nextLine().trim());
        System.out.print("請輸入初始庫存量: ");
        int stockInput = Integer.parseInt(sc.nextLine().trim());

        Product newProd = new Product(nameInput, priceInput, stockInput);
        products[productCount] = newProd;
        productCount++;
        addCount++;
        System.out.println("已成功加入商品: " + newProd);
    }

    private static void sellProduct(Scanner sc) {
        System.out.print("請輸入欲出售的商品名稱: ");
        String searchName = sc.nextLine();

        Product target = findProductHelper(searchName);
        if (target == null) {
            System.out.println("[出售失敗] 找不到該商品");
            return;
        }

        System.out.print("目前庫存為 " + target.getStock() + "，請輸入欲出售數量: ");
        int qty = Integer.parseInt(sc.nextLine().trim());

        if (target.sell(qty)) {
            System.out.println("[出售成功] 已售出 " + qty + " 個 " + target.getName());
            sellCount++;
        } else {
            System.out.println("[出售失敗] 出售數量必須大於 0，且不可大於目前庫存");
        }
    }

    private static void restockProduct(Scanner sc) {
        System.out.print("請輸入欲補貨的商品名稱: ");
        String searchName = sc.nextLine();

        Product target = findProductHelper(searchName);
        if (target == null) {
            System.out.println("[補貨失敗] 找不到該商品");
            return;
        }

        System.out.print("目前庫存為 " + target.getStock() + "，請輸入欲補貨數量: ");
        int qty = Integer.parseInt(sc.nextLine().trim());

        if (target.restock(qty)) {
            System.out.println("[補貨成功] 商品 " + target.getName() + " 已成功補貨，新庫存為: " + target.getStock());
            restockCount++;
        } else {
            System.out.println("[補貨失敗] 補貨數量必須大於 0");
        }
    }

    private static void changeProductPrice(Scanner sc) {
        System.out.print("請輸入欲修改價格的商品名稱: ");
        String searchName = sc.nextLine();

        Product target = findProductHelper(searchName);
        if (target == null) {
            System.out.println("[修改失敗] 找不到該商品");
            return;
        }

        System.out.print("目前價格為 " + target.getPrice() + "，請輸入新價格: ");
        int newPrice = Integer.parseInt(sc.nextLine().trim());

        if (target.setPrice(newPrice)) {
            System.out.println("[修改成功] 商品 " + target.getName() + " 價格已調整為: $" + target.getPrice());
            priceChangeCount++;
        } else {
            System.out.println("[修改失敗] 修改之商品價格必須大於 0");
        }
    }

    private static Product findProductHelper(String name) {
        if (name == null)
            return null;
        String formattedName = name.trim();
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].getName().equalsIgnoreCase(formattedName)) {
                return products[i];
            }
        }
        return null;
    }

    private static void displayLowStockProducts() {
        System.out.println("[低庫存警示清單 (庫存少於 10)]");
        boolean hasLowStock = false;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && products[i].isLowStock()) {
                System.out.println(products[i]);
                hasLowStock = true;
            }
        }
        if (!hasLowStock) {
            System.out.println("目前所有商品庫存皆足夠");
        }
    }

    private static void displayTotalInventoryValue() {
        long grandTotalValue = 0;
        for (int i = 0; i < productCount; i++) {
            if (products[i] != null) {
                grandTotalValue += products[i].getInventoryValue();
            }
        }
        System.out.println("[全部庫存總價值]");
        System.out.println("目前倉庫中商品總品項數: " + productCount + " 種");
        System.out.println("所有商品的總資產價值: $" + grandTotalValue + " 元");
    }

    private static void showSummaryAndExit() {
        System.out.println("=== 操作摘要 ===");
        System.out.println("新增商品總次數: " + addCount + " 次");
        System.out.println("出售交易總筆數: " + sellCount + " 次");
        System.out.println("庫存進貨總筆數: " + restockCount + " 次");
        System.out.println("價格異動總筆數: " + priceChangeCount + " 次");
    }
}
