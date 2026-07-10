import java.util.Scanner;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        while (option != 0) {
            printMenu();
            option = sc.nextInt();

            if (option == 0) {
                System.out.println("已離開");
                break;
            }

            switch (option) {
                case 1:
                    printMultiplicationTable();
                    break;
                case 2:
                    int height = readPositiveInt(sc, "請輸入三角形高度: ");
                    printTriangle(height);
                    break;
                case 3:
                    int revHeight = readPositiveInt(sc, "請輸入倒三角形高度: ");
                    printReverseTriangle(revHeight);
                    break;
                case 4:
                    int rows = readPositiveInt(sc, "rows: ");
                    int clos = readPositiveInt(sc, "cols: ");
                    printNumberGrid(rows, clos);
                    break;
                default:
                    System.out.println("無此選項，請重新輸入");
            }
        }
        sc.close();
    }

    public static void printMenu() {
        System.out.println("=== 圖形與表格產生器 ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形");
        System.out.println("3. 倒三角形");
        System.out.println("4. 數字方格");
        System.out.println("0. Exit");
        System.out.print("請輸入選項: ");
    }

    public static int readPositiveInt(Scanner sc, String message) {
        int value = 0;
        while (value <= 0) {
            System.out.print(message);
            value = sc.nextInt();
            if (value <= 0) {
                System.out.println("輸入數值必須大於0，請重新輸入");
            }
        }
        return value;
    }

    public static void printMultiplicationTable() {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.print(i + "x" + j + "=" + (i * j) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printTriangle(int height) {
        for (int i = 1; i <= height; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printReverseTriangle(int height) {
        for (int i = height; i >= 1; i--) {
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printNumberGrid(int rows, int cols) {
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
