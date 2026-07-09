import java.util.Scanner;

public class OrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalQuantity = 0;
        int totalAmount = 0;

        while (option != 0) {
            System.out.println("=== 點餐系統 ===");
            System.out.println("1. Black tea $30");
            System.out.println("2. Green tea $35");
            System.out.println("3. Coffee    $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項: ");
            option = sc.nextInt();

            if (option == 0) {
                break;
            }

            int price = 0;
            String itemName = "";

            switch (option) {
                case 1:
                    itemName = "Black tea";
                    price = 30;
                    break;
                case 2:
                    itemName = "Green tea";
                    price = 35;
                    break;
                case 3:
                    itemName = "Coffee";
                    price = 50;
                    break;
                default:
                    System.out.println("無此商品，請重新輸入");
                    continue;
            }

            int quantity = 0;
            while (quantity <= 0) {
                System.out.println("請輸入數量(需大於0): ");
                quantity = sc.nextInt();
                if (quantity <= 0) {
                    System.out.println("數量輸入錯誤，請重新輸入");
                }
            }

            int subtotal = price * quantity;

            totalQuantity += quantity;
            totalAmount += subtotal;

            System.out.println("已點選: " + itemName + " x " + quantity + "杯，小計: $" + subtotal);
            System.out.println();
        }

        System.out.println("=== 結帳明細 ===");
        System.out.println("總共購買杯數: " + totalQuantity + " 杯");
        System.out.println("總金額: $" + totalAmount);

        sc.close();
    }
}
