import java.util.Scanner;

public class QuantityValidation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入使用者輸入商品數量(大於0)：");
        int count = sc.nextInt();

        while (count <= 0) {
            System.out.print("數量不合法，請重新輸入（大於0）：");
            count = sc.nextInt();
        }

        System.out.println("Count: " + count);

        sc.close();
    }
}
