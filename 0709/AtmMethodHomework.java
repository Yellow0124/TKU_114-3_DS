import java.util.Scanner;

public class AtmMethodHomework {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int balance = 1000;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();

            if (option == 0) {
                System.out.println("已離開");
                break;
            }

            switch (option) {
                case 1:
                    printBalance(balance);
                    break;

                case 2:
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額: ");
                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額: ");
                    if (withdrawAmount > balance) {
                        System.out.println("餘額不足，取消提款");
                    } else {
                        balance = withdraw(balance, withdrawAmount);
                    }
                    break;

                default:
                    System.out.println("無此選項，請重新輸入");
            }
            System.out.println();
        }

        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== ATM System ===");
        System.out.println("1. 查詢餘額");
        System.out.println("2. 存款");
        System.out.println("3. 提款");
        System.out.println("0. 離開");
        System.out.print("請輸入選項：");
    }

    public static int readPositiveAmount(Scanner sc, String message) {
        int amount = 0;
        while (amount <= 0) {
            System.out.print(message);
            amount = sc.nextInt();
            if (amount <= 0) {
                System.out.println("金額必須大於 0，請重新輸入");
            }
        }
        return amount;
    }

    public static int deposit(int balance, int amount) {
        int newBalance = balance + amount;
        System.out.println("已存入 $" + amount);
        printBalance(newBalance);
        return newBalance;
    }

    public static int withdraw(int balance, int amount) {
        int newBalance = balance - amount;
        System.out.println("已領取 $" + amount);
        printBalance(newBalance);
        return newBalance;
    }

    public static void printBalance(int balance) {
        System.out.println("餘額為: $" + balance);
    }
}