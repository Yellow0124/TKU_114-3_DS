import java.util.Scanner;

public class OrderSystem_demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalQuantity = 0;
        int totalAmount = 0;

        while (option != 0) {
            System.out.println("=== Order Menu ===");
            System.out.println("1. Black tea  $30");
            System.out.println("2. Green tea  $35");
            System.out.println("3. Coffee     $50");
            System.out.println("0. Checkout");
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            if (option == 0) {
                break;
            }

            int price = 0;

            switch (option) {
                case 1:
                    price = 30;
                    break;
                case 2:
                    price = 35;
                    break;
                case 3:
                    price = 50;
                    break;
                default:
                    System.out.println("Invalid option, please try again.\n");
                    continue;
            }

            int quantity = 0;
            while (quantity <= 0) {
                System.out.print("請輸入數量：");
                quantity = sc.nextInt();
                if (quantity <= 0) {
                    System.out.println("數量必須大於 0，請重新輸入。");
                }
            }

            int subtotal = price * quantity;
            System.out.println("Subtotal: " + subtotal);
            System.out.println();

            // 累加總數量與總金額
            totalQuantity += quantity;
            totalAmount += subtotal;
        }

        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Total items: " + totalQuantity);
        System.out.println("Total amount: " + totalAmount);

        sc.close();
    }
}