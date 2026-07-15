import java.util.Scanner;

public class ArrayStatistics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = readCount(sc);

        int[] scores = new int[count];

        inputScores(sc, scores);

        System.out.print("成績：[ ");
        for (int i = 0; i < scores.length; i++) {
            System.out.print(scores[i] + (i == scores.length - 1 ? "" : ", "));
        }
        System.out.println(" ]");

        int total = calculateTotal(scores);
        double average = (double) total / scores.length;
        System.out.println("總分：" + total);
        System.out.println("平均：" + average);

        System.out.println("最高分: " + findMax(scores));
        System.out.println("最低分: " + findMin(scores));

        int passCount = countPass(scores);
        int failCount = scores.length - passCount;
        System.out.println("及格人數: " + passCount);
        System.out.println("不及格人數: " + failCount);

        System.out.print("請輸入要搜尋的目標成績: ");
        int target = sc.nextInt();
        int index = findIndex(scores, target);

        if (index != -1) {
            System.out.println("成績 " + target + " 第一次出現的索引位置: " + index);
        } else {
            System.out.println("找不到該成績 " + target + " 的紀錄。");
        }

        sc.close();
    }

    public static int readCount(Scanner sc) {
        int count = 0;
        while (count < 1 || count > 50) {
            System.out.print("請輸入資料筆數 (1 ~ 50): ");
            count = sc.nextInt();
            if (count < 1 || count > 50) {
                System.out.println("筆數超出範圍，請重新輸入");
            }
        }
        return count;
    }

    // 2. 逐筆輸入成績（輸入驗證：0 到 100）
    public static void inputScores(Scanner sc, int[] scores) {
        System.out.println("請輸入每筆成績 (範圍 0~100)");
        for (int i = 0; i < scores.length; i++) {
            int score = -1;
            while (score < 0 || score > 100) {
                System.out.print("第" + i + "筆成績: ");
                score = sc.nextInt();
                if (score < 0 || score > 100) {
                    System.out.println("成績範圍必須在0 ~ 100之間，請重新輸入");
                }
            }
            scores[i] = score;
        }
    }

    public static int calculateTotal(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    public static int findMax(int[] scores) {
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        return max;
    }

    public static int findMin(int[] scores) {
        int min = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) {
                min = scores[i];
            }
        }
        return min;
    }

    public static int countPass(int[] scores) {
        int passCount = 0;
        for (int score : scores) {
            if (score >= 60) {
                passCount++;
            }
        }
        return passCount;
    }

    public static int findIndex(int[] scores, int target) {
        for (int i = 0; i < scores.length; i++) {
            if (scores[i] == target) {
                return i;
            }
        }
        return -1;
    }
}