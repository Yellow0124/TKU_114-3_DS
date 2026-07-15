import java.util.Scanner;

public class ProductArraySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] names = { "Keyboard", "Mouse", "Monitor", "USB Cable", "Headset" };
        int[] prices = { 890, 490, 5200, 250, 1290 };
        int[] stocks = { 12, 20, 5, 30, 8 };

        int totalSalesAmount = 0;
        int totalSoldItems = 0;

        int option = -1;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();

            if (option == 0) {
                break;
            }

            switch (option) {
                case 1:
                    printAllProducts(names, prices, stocks);
                    break;

                case 2:
                    queryProduct(sc, names, prices, stocks);
                    break;

                case 3:
                    int salesEarned = buyProduct(sc, names, prices, stocks);
                    if (salesEarned > 0) {
                        totalSalesAmount += salesEarned;
                        totalSoldItems++;
                    }
                    break;

                case 4:
                    restockProduct(sc, names, stocks);
                    break;

                case 5:
                    printLowStock(names, stocks);
                    break;

                case 6:
                    printTotalInventoryValue(names, prices, stocks);
                    break;

                default:
                    System.out.println("無此選項，請重新輸入。\n");
            }
        }

        System.out.println("\n=== 系統關閉操作摘要 ===");
        System.out.println("本次系統運行累積銷售額：$" + totalSalesAmount);
        System.out.println("成功交易商品次數：" + totalSoldItems + " 次");
        System.out.println("感謝您的使用，再見！");

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 商品庫存管理系統 ===");
        System.out.println("1. 顯示全部商品");
        System.out.println("2. 依商品編號查詢");
        System.out.println("3. 購買商品");
        System.out.println("4. 補充商品庫存");
        System.out.println("5. 顯示低庫存商品 (< 10)");
        System.out.println("6. 顯示庫存總價值");
        System.out.println("0. 結束離開");
        System.out.print("請輸入選項：");
    }

    public static void printAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 全商品列表 ---");
        System.out.println("編號\t商品名稱\t價格\t庫存");
        for (int i = 0; i < names.length; i++) {
            System.out.println((i + 1) + "\t" + names[i] + "\t$" + prices[i] + "\t" + stocks[i] + " 個");
        }
        System.out.println();
    }

    public static void queryProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入要查詢的商品編號 (1~" + names.length + ")：");
        int id = sc.nextInt();

        if (id < 1 || id > names.length) {
            System.out.println("錯誤：無此商品編號！\n");
            return;
        }

        int index = id - 1;
        System.out.println("\n--- 商品明細 ---");
        System.out.println("商品名稱：" + names[index]);
        System.out.println("單價  ：$" + prices[index]);
        System.out.println("目前庫存：" + stocks[index] + " 個\n");
    }

    public static int buyProduct(Scanner sc, String[] names, int[] prices, int[] stocks) {
        System.out.print("請輸入想購買的商品編號 (1~" + names.length + ")：");
        int id = sc.nextInt();

        if (id < 1 || id > names.length) {
            System.out.println("錯誤：無此商品編號！\n");
            return 0;
        }

        int index = id - 1;
        System.out.println("已選擇：" + names[index] + " (剩餘庫存：" + stocks[index] + " 個)");

        if (stocks[index] == 0) {
            System.out.println("抱歉，該商品已無庫存，無法購買。\n");
            return 0;
        }

        int quantity = 0;
        while (true) {
            System.out.print("請輸入購買數量：");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("錯誤：購買數量必須大於 0！");
            } else if (quantity > stocks[index]) {
                System.out.println("錯誤：庫存不足！目前僅剩 " + stocks[index] + " 個。");
            } else {
                break;
            }
        }

        stocks[index] -= quantity;
        int cost = prices[index] * quantity;
        System.out.println("購買成功！您購買了 " + names[index] + " x " + quantity + " 個，總共：$" + cost);
        System.out.println("該商品剩餘庫存：" + stocks[index] + " 個\n");
        return cost;
    }

    public static void restockProduct(Scanner sc, String[] names, int[] stocks) {
        System.out.print("請輸入要補貨的商品編號 (1~" + names.length + ")：");
        int id = sc.nextInt();

        if (id < 1 || id > names.length) {
            System.out.println("錯誤：無此商品編號！\n");
            return;
        }

        int index = id - 1;
        System.out.println("目前 " + names[index] + " 的庫存為：" + stocks[index] + " 個");

        int amount = 0;
        while (amount <= 0) {
            System.out.print("請輸入補貨數量：");
            amount = sc.nextInt();
            if (amount <= 0) {
                System.out.println("錯誤：補貨數量必須大於 0！");
            }
        }

        stocks[index] += amount;
        System.out.println("補貨成功！" + names[index] + " 目前新庫存為：" + stocks[index] + " 個\n");
    }

    public static void printLowStock(String[] names, int[] stocks) {
        System.out.println("\n--- 低庫存預警 (庫存 < 10) ---");
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (stocks[i] < 10) {
                System.out.println("編號 " + (i + 1) + " | " + names[i] + " - 目前庫存僅剩：" + stocks[i] + " 個");
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前所有商品庫存充足！");
        }
        System.out.println();
    }

    public static void printTotalInventoryValue(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 庫存價值明細 ---");
        int grandTotal = 0;
        for (int i = 0; i < names.length; i++) {
            int itemValue = prices[i] * stocks[i];
            grandTotal += itemValue;
            System.out.println(names[i] + "\t: " + stocks[i] + " 個 x $" + prices[i] + " = 庫存價值 $" + itemValue);
        }
        System.out.println("-------------------------------------");
        System.out.println("目前倉庫全商品總庫存價值為：$" + grandTotal + "\n");
    }
}