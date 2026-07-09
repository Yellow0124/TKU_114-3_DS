import java.util.Scanner;

public class OrderSystemRefactor_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalQuantity = 0;
        int totalAmount = 0;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();

            if (option == 0) {
                break;
            }

            int price = getPrice(option);
            if (price == 0) {
                System.out.println("無此商品，請重新輸入");
                continue;
            }

            int quantity = readValidQuantity(sc);

            int subtotal = calculateSubtotal(price, quantity);

            totalQuantity += quantity;
            totalAmount += subtotal;

            System.out.println("以購買數量: " + quantity + "杯，小計: $" + subtotal);
            System.out.println();
        }

        printReceipt(totalQuantity, totalAmount);
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 點餐系統 ===");
        System.out.println("1. Black tea $30");
        System.out.println("2. Green tea $35");
        System.out.println("3. Coffee    $50");
        System.out.println("0. Checkout");
        System.out.print("請輸入選項: ");
    }

    public static int getPrice(int option) {
        switch (option) {
            case 1:
                return 30;
            case 2:
                return 35;
            case 3:
                return 50;
            default:
                return 0;
        }
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("請輸入數量(需大於0): ");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("數量輸入錯誤，請重新輸入");
            }
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("=== 帳單明細 ===");
        System.out.println("總共購買杯數: " + totalItems + " 杯");
        System.out.println("總金額: $" + totalAmount);
    }
}
