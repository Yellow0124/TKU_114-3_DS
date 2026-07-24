import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorUndoSystem {

    private StringBuilder currentText;
    private Deque<String> historyStack;

    public TextEditorUndoSystem() {
        this.currentText = new StringBuilder();
        this.historyStack = new ArrayDeque<>();
    }

    public void type(String textToAppend) {
        if (textToAppend == null || textToAppend.isEmpty()) {
            System.out.println("[新增無效] 輸入文字為空，不進行修改");
            return;
        }

        saveSnapshot();

        currentText.append(textToAppend);
        System.out.println("[輸入文字] 加入: \"" + textToAppend + "\"");
        printCurrentText();
    }

    public void delete(int count) {
        if (count <= 0) {
            System.out.println("[刪除無效] 刪除字數必須大於 0。");
            return;
        }

        if (currentText.length() == 0) {
            System.out.println("[刪除無效] 目前內文已為空白，無法刪除");
            return;
        }

        saveSnapshot();

        int actualDeleteCount = Math.min(count, currentText.length());
        int start = currentText.length() - actualDeleteCount;
        String deletedPart = currentText.substring(start);
        currentText.delete(start, currentText.length());

        System.out.println("[刪除文字] 移除最後 " + actualDeleteCount + " 個字元: \"" + deletedPart + "\"");
        printCurrentText();
    }

    public boolean undo() {
        if (historyStack.isEmpty()) {
            System.out.println("[Undo 失敗] 目前沒有任何歷史紀錄可供復原");
            return false;
        }

        String previousText = historyStack.pop();
        currentText = new StringBuilder(previousText);

        System.out.println("[執行 Undo] 已復原至上一個狀態");
        printCurrentText();
        return true;
    }

    private void saveSnapshot() {
        historyStack.push(currentText.toString());
    }

    public void printCurrentText() {
        System.out.println("當前文字內容: \"" + currentText.toString() + "\" (長度: " + currentText.length() + ")");
    }

    public int getHistorySize() {
        return historyStack.size();
    }

    public static void main(String[] args) {

        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("【測試 1】初始空歷史時嘗試 Undo");
        editor.undo();

        System.out.println("\n【測試 2】輸入 \"Hello\"");
        editor.type("Hello");

        System.out.println("\n【測試 3】輸入 \" World\"");
        editor.type(" World");

        System.out.println("\n【測試 4】輸入 \" Java!\"");
        editor.type(" Java!");

        System.out.println("\n【測試 5】刪除最後 5 個字元");
        editor.delete(5);

        System.out.println("\n【測試 6】輸入 \" DS!\"");
        editor.type(" DS!");

        System.out.println("\n==================================================");
        System.out.println("進行連續 3 次以上的 Undo 驗證測試 (歷史步數: " + editor.getHistorySize() + " 步)");
        System.out.println("==================================================\n");

        System.out.println("【測試 7-1】連續 Undo - 第 1 次");
        editor.undo();

        System.out.println("\n【測試 7-2】連續 Undo - 第 2 次");
        editor.undo();

        System.out.println("\n【測試 7-3】連續 Undo - 第 3 次");
        editor.undo();

        System.out.println("\n測試 7-4】連續 Undo - 第 4 次");
        editor.undo();

        System.out.println("\n【測試 7-5】連續 Undo - 第 5 次 (回到初始空狀態)");
        editor.undo();

        System.out.println("\n測試 7-6】再次 Undo (驗證邊界安全)");
        editor.undo();
    }
}