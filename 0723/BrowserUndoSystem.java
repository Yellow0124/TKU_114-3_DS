import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserUndoSystem {
    private Deque<String> historyStack;

    public BrowserUndoSystem() {
        this.historyStack = new ArrayDeque<>();
    }

    public void visit(String url) {
        if (url == null || url.trim().isEmpty()) {
            System.out.println("無法載入: 網址不可為空白");
            return;
        }
        historyStack.push(url.trim());
        System.out.println("[開啟新頁] 成功前往: " + url);
    }

    public String back() {
        if (historyStack.isEmpty()) {
            System.out.println("[無法返回] 目前沒有任何瀏覽紀錄");
            return null;
        }

        String currentPage = historyStack.pop();
        System.out.println("[返回上一頁] 離開頁面: " + currentPage);

        if (historyStack.isEmpty()) {
            System.out.println("[提示] 已返回至空白頁（無後續歷史紀錄）");
        } else {
            System.out.println("目前所在頁面: " + historyStack.peek());
        }
        return currentPage;
    }

    public String currentPage() {
        if (historyStack.isEmpty()) {
            System.out.println("目前所在頁面: [空白頁]");
            return null;
        }
        String current = historyStack.peek();
        System.out.println("目前所在頁面: " + current);
        return current;
    }

    public int getHistorySize() {
        return historyStack.size();
    }

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("1. 初始狀態檢查");
        browser.currentPage();

        System.out.println("\n2. 空紀錄時嘗試返回上一頁");
        browser.back();

        System.out.println("\n3. 開啟 Google 首頁");
        browser.visit("https://www.google.com");

        System.out.println("\n4. 搜尋並進入 GitHub");
        browser.visit("https://github.com");

        System.out.println("\n5. 進入 Stack Overflow 查看解答");
        browser.visit("https://stackoverflow.com");

        System.out.println("\n6. 查看當前頁面");
        browser.currentPage();

        System.out.println("\n7. 連續執行兩次「返回上一頁」");
        browser.back();
        browser.back();

        System.out.println("\n8. 開起新頁面 YouTube 並檢查狀態");
        browser.visit("https://www.youtube.com");
        browser.currentPage();
        System.out.println("目前歷史紀錄總深度：" + browser.getHistorySize() + " 頁");
    }
}
