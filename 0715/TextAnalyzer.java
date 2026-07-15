import java.util.Scanner;

public class TextAnalyzer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String text = readNonEmptyText(sc);

        System.out.println("--- 字元分析 ---");
        System.out.println(("原始字元數(含空白): " + text.length()));
        String trimmedText = text.trim();
        System.out.println("Trim 後字元數: " + trimmedText.length());

        String[] words = splitWords(trimmedText);
        System.out.println("--- 單字與母音分析 ---");
        System.out.println("有效單字數量: " + words.length);

        int vowelCount = countVowels(trimmedText);
        System.out.println("母音(a, e, i, o, u)總數: " + vowelCount);

        String longestWord = findLongestWord(words);
        System.out.println("最長的單字: " + longestWord + "(長度: " + longestWord.length() + ")");

        System.out.println("--- 關鍵字搜尋 ---");
        System.out.print("請輸入想搜尋的關鍵字: ");
        String keyword = sc.next();
        int keywordCount = countKeyword(words, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 (忽略大小寫)：" + keywordCount + " 次");

        sc.close();
    }

    // 讀取非空白文字，若輸入空字串或全空白會要求重新輸入
    public static String readNonEmptyText(Scanner sc) {
        String input = "";
        while (input.trim().isEmpty()) {
            System.out.print("請輸入一行非空白文字: ");
            input = sc.nextLine();
            if (input.trim().isEmpty()) {
                System.out.println("輸入內容不能為空或全空白，請重新輸入");
            }
        }
        return input;
    }

    // 使用空白切割單字，並妥善處理連續空白
    public static String[] splitWords(String text) {
        return text.split("\\s+");
    }

    // 計算母音 a, e, i, o, u 的總數量 (不分大小寫)
    public static int countVowels(String text) {
        int count = 0;
        String lowerText = text.toLowerCase();
        for (int i = 0; i < lowerText.length(); i++) {
            char c = lowerText.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    // 找出陣列中最長的單字
    public static String findLongestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        String longest = words[0];
        for (int i = 1; i < words.length; i++) {
            if (words[i].length() > longest.length()) {
                longest = words[i];
            }
        }
        return longest;
    }

    // 輸入關鍵字並顯示出現次數，忽略大小寫
    public static int countKeyword(String[] words, String keyword) {
        int count = 0;
        String lowerKeyword = keyword.toLowerCase();

        for (String word : words) {
            String cleanWord = word.replaceAll("[^a-zA-Z0-9]]", "").toString();
            if (cleanWord.equals(lowerKeyword)) {
                count++;
            }
        }
        return count;
    }
}
