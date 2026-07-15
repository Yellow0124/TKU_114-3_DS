import java.util.Scanner;

public class SalesMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] sales = new int[3][4];

        inputSales(sc, sales);
        printSalesTable(sales);
        int[] productTotals = calculateProductTotals(sales);
        calculateDailyTotals(sales);
        findBestSellingProduct(productTotals);

        sc.close();
    }

    public static void inputSales(Scanner sc, int[][] sales) {
        for (int i = 0; i < sales.length; i++) {
            for (int j = 0; j < sales[i].length; j++) {
                int quantity = -1;
                while (quantity < 0) {
                    System.out.print("商品 " + (i + 1) + " 第" + (j + 1) + "天銷售量: ");
                    quantity = sc.nextInt();
                    if (quantity < 0) {
                        System.out.println("銷售量不得小於0，請重新輸入");
                    }
                }
                sales[i][j] = quantity;
            }
            System.out.println();
        }
    }

    public static void printSalesTable(int[][] sales) {
        System.out.println("\tDay 1\tDay 2\tDay 3\tDay 4");
        for (int i = 0; i < sales.length; i++) {
            System.out.print("商品 " + (i + 1) + "\t");
            for (int j = 0; j < sales[i].length; j++) {
                System.out.print(sales[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int[] calculateProductTotals(int[][] sales) {
        int[] totals = new int[sales.length];
        for (int i = 0; i < sales.length; i++) {
            int sum = 0;
            for (int j = 0; j < sales[i].length; j++) {
                sum += sales[i][j];
            }
            totals[i] = sum;
            System.out.println("商品 " + (i + 1) + " 銷售總量: " + sum);
        }
        System.out.println();
        return totals;
    }

    public static void calculateDailyTotals(int[][] sales) {
        int numRows = sales.length;
        int numCols = sales[0].length;

        for (int j = 0; j < numCols; j++) {
            int dailySum = 0;
            for (int i = 0; i < numRows; i++) {
                dailySum += sales[i][j];
            }
            System.out.println("第" + (j + 1) + "天總銷售量: " + dailySum);
        }
        System.out.println();
    }

    public static void findBestSellingProduct(int[] productTotals) {
        int maxSales = productTotals[0];
        int bestProductIndex = 0;

        for (int i = 1; i < productTotals.length; i++) {
            if (productTotals[i] > maxSales) {
                maxSales = productTotals[i];
                bestProductIndex = i;
            }
        }
        System.out.println("總銷售量最高的是商品 " + (bestProductIndex + 1) + "(總共銷售: " + maxSales + ")");
    }
}
