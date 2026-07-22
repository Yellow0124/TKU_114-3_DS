import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("請開始輸入成績 (0-100)，輸入 -1 即可結束輸入：");

        while (true) {
            System.out.print("請輸入成績: ");
            String input = scanner.nextLine().trim();

            try {
                int score = Integer.parseInt(input);

                if (score == -1) {
                    break;
                }

                if (score >= 0 && score <= 100) {
                    scores.add(score);
                } else {
                    System.out.println("錯誤：成績必須介於 0 到 100 之間");
                }
            } catch (NumberFormatException e) {
                System.out.println(" 錯誤：請輸入有效的整數數字");
            }
        }

        System.out.println("\n----------------------------------------");
        if (scores.isEmpty()) {
            System.out.println("沒有輸入任何有效的成績資料");
        } else {
            // 呼叫獨立拆出的 Method 進行計算與顯示
            printSummary(scores);
            printPassingScores(scores);
        }
        System.out.println("----------------------------------------");

        scanner.close();
    }

    public static void printSummary(ArrayList<Integer> scores) {
        int count = scores.size();
        int sum = 0;
        int max = scores.get(0);
        int min = scores.get(0);

        for (int score : scores) {
            sum += score;
            if (score > max)
                max = score;
            if (score < min)
                min = score;
        }

        double average = (double) sum / count;

        System.out.println("總筆數：" + count);
        System.out.printf("平均分數：%.2f 分%n", average);
        System.out.println("最高分：" + max);
        System.out.println("最低分：" + min);
    }

    public static void printPassingScores(ArrayList<Integer> scores) {
        ArrayList<Integer> passingList = getPassingScores(scores);
        System.out.println("及格分數清單 (>= 60)：" + passingList);
    }

    public static ArrayList<Integer> getPassingScores(ArrayList<Integer> scores) {
        ArrayList<Integer> passingList = new ArrayList<>();
        for (int score : scores) {
            if (score >= 60) {
                passingList.add(score);
            }
        }
        return passingList;
    }
}