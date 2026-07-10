import java.util.Scanner;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        int totalQuantity = 0;
        int totalAmount = 0;

        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();

            if (option == 0) {
                break;
            }

            int price = getPrice(option);
            String itemName = getItemName(option);

            if (price == 0) {
                System.out.println("輸入錯誤，請重新輸入");
                continue;
            }

            int quantity = readValidQuantity(sc);

            switch (option) {
                case 1:
                    blackTeaCount += quantity;
                    break;
                case 2:
                    greenTeaCount += quantity;
                    break;
                case 3:
                    milkTeaCount += quantity;
                    break;
                case 4:
                    coffeeCount += quantity;
                    break;
            }

            int subtotal = calculateSubtotal(price, quantity);
            System.out.println(itemName + " x " + quantity);
            System.out.println("Subtotal: " + subtotal);
            System.out.println();

            totalQuantity += quantity;
            totalAmount += subtotal;
        }

        int finalAmount = calculateDiscountedTotal(totalAmount);

        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, coffeeCount, totalQuantity, finalAmount);
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black Tea $30");
        System.out.println("2. Green Tea $35");
        System.out.println("3. Milk Tea  $45");
        System.out.println("4. Coffee    $50");
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
                return 45;
            case 4:
                return 50;
            default:
                return 0;
        }
    }

    public static String getItemName(int option) {
        switch (option) {
            case 1:
                return "Black tea";
            case 2:
                return "Green tea";
            case 3:
                return "Milk tea";
            case 4:
                return "Coffee";
            default:
                return "Unknown";
        }
    }

    public static int readValidQuantity(Scanner sc) {
        int quantity = 0;
        while (quantity <= 0) {
            System.out.print("請輸入數量: ");
            quantity = sc.nextInt();
            if (quantity <= 0) {
                System.out.println("數量必須大於0，請重新輸入");
            }
        }
        return quantity;
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    public static int calculateDiscountedTotal(int totalAmount) {
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }

    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount,
            int totalItems, int totalAmount, int finalAmount) {
        System.out.println();
        System.out.println("=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green Tea: " + greenTeaCount);
        System.out.println("Milk Tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);

        if (finalAmount < totalAmount) {
            System.out.println("Discount: 10% off");
        } else {
            System.out.println("Discount: No");
        }
        System.out.println("Final amount: " + finalAmount);
    }
}
