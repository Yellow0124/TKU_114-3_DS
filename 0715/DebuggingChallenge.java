import java.util.Scanner;

/**
 * 1. 編譯錯誤 (Syntax Error)
 *    - 錯誤位置：`if (command == "exit") { System.out.println("系統結束，年齡：" + age) }` 這一行。
 *    - 原因：`System.out.println` 語句末尾漏掉了分號 `;`。
 *    - 修正方式：在該語句後方加上分號 `;`。
 * 
 * 2. 陣列越界錯誤 (ArrayIndexOutOfBoundsException)
 *    - 錯誤位置：`for (int i = 0; i <= scores.length; i++)`
 *    - 原因：陣列長度 `scores.length` 為 3，合法索引為 0, 1, 2。迴圈條件寫 `<=` 會讓 `i` 變成 3，當執行到 `scores[3]` 時就會導致程式崩潰。
 *    - 修正方式：將條件修改為 `i < scores.length`。
 * 
 * 3. 整數除法邏輯錯誤 (Integer Division Loss of Precision)
 *    - 錯誤位置：`double average = total / scores.length;`
 *    - 原因：`total` 和 `scores.length` 皆為 `int` 型態，兩者直接相除會先進行「整數除法」（無條件捨去小數點），最後才隱式轉型為 `double`。這會導致平均值失去精準度（例如本例 247 / 3 會變成 82.00 而不是 82.33）。
 *    - 修正方式：將其中一個變數強制轉型為 `double` 進行運算，改為 `(double) total / scores.length`。
 * 
 * 4. Scanner 換行殘留問題 (Scanner Buffer Issue)
 *    - 錯誤位置：在 `sc.nextInt()` 後方直接呼叫 `sc.nextLine()`。
 *    - 原因：`nextInt()` 只讀取了整數數值，但使用者按下的「Enter 鍵（換行符 \n）」依然殘留在輸入緩衝區中。後面的 `nextLine()` 會直接讀取到這個換行符，導致指令輸入被直接跳過（讀到空字串）。
 *    - 修正方式：在 `sc.nextInt()` 後方額外加上一行 `sc.nextLine()`，用來吃掉多餘的換行符。
 * 
 * 5. 字串比較錯誤 (String Comparison Logic Error)
 *    - 錯誤位置：`if (command == "exit")`
 *    - 原因：在 Java 中，`==` 比較的是字串的「記憶體位址（Reference）」，而不是「內容值（Value）」。從 Scanner 讀進來的動態字串與字面值 `"exit"` 的位址不同，所以條件永遠不會成立。
 *    - 修正方式：改用字串的內容比對方法 `command.equals("exit")`。
 * 
 * === 錯誤發生時的重要變數值紀錄 (Breakpoint Data) ===
 * - `scores` 陣列內容：{80, 75, 92}，長度為 3
 * - 當 `i = 3` 時，`total` 累加值為 247。此時拋出 `ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3`。
 * - 當輸入年齡為 `20` 後，`age` 值為 20。隨後 `command` 變數在未輸入時即被賦值為 `""`（空字串），且 `command == "exit"` 的結果為 `false`。
 */
public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        // 修正 1：修改迴圈結束條件，避免陣列越界
        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        // 修正 2：強制轉型為 double，保留精準小數點
        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        
        // 修正 3：吃掉 nextInt() 殘留的 Enter 換行符號
        sc.nextLine(); 

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        // 修正 4：改用 .equals() 來比較字串內容，並加上結尾分號（修正 5）
        if (command.equals("exit")) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}