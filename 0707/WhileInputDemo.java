import java.util.Scanner;

public class WhileInputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入整數: ");
        int input = sc.nextInt();

        while (input > 0) {
            System.out.println(input);
            input--;
        }

        System.out.println("Start");

        sc.close();
    }
}
