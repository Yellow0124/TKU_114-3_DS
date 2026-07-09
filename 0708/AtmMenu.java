import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int balance = 1000;

        while (option != 0) {
            System.out.println("=== ATM System ===");
            System.out.println("1. 查詢餘額");
            System.out.println("2. 存款");
            System.out.println("3. 提款");
            System.out.println("0. 離開");
            System.out.print("請輸入選項: ");
            option = sc.nextInt();

            if (option == 0) {
                System.out.println("已離開");
                break;
            }

            switch (option) {
                case 1:
                    System.out.println("餘額為: $" + balance);
                    break;
                case 2:
                    int deposit = 0;
                    while (deposit <= 0) {
                        System.out.print("請輸入存款金額:");
                        deposit = sc.nextInt();
                        if (deposit <= 0) {
                            System.out.println("存款金額必須大於0，請重新輸入");
                        }
                    }
                    balance += deposit;
                    System.out.println("已存入 $" + deposit + "餘額為 $" + balance);
                    break;

                case 3:
                    int withdraw = 0;
                    while (true) {
                        System.out.print("請輸入提款金額: ");
                        withdraw = sc.nextInt();

                        if (withdraw <= 0) {
                            System.out.println("提款金額必須大於0，請重新輸入");
                        } else if (withdraw > balance) {
                            System.out.println("餘額不足，目前餘額為: $" + balance);
                        } else {
                            break;
                        }
                    }
                    balance -= withdraw;
                    System.out.println("已提取 $" + withdraw + "，餘額為: $" + balance);
                    break;

                default:
                    System.out.println("無此選項，請重新輸入");
                    break;
            }
            System.out.println();
        }

        sc.close();
    }
}
