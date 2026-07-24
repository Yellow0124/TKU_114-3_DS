import java.util.Scanner;

public class PlaylistSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlaylistLinkedList playlist = new PlaylistLinkedList();

        playlist.addLast("S001", "晴天");
        playlist.addLast("S002", "稻香");
        playlist.addLast("S003", "七里香");

        boolean running = true;
        while (running) {
            System.out.println("\n=== 播放清單統 ===");
            System.out.println("1. 尾端新增歌曲 (代碼不可重複)");
            System.out.println("2. 依代碼搜尋歌曲");
            System.out.println("3. 依代碼刪除歌曲 (支援第一首與最後一首)");
            System.out.println("4. 顯示完整播放順序");
            System.out.println("5. 離開系統");
            System.out.print("請選擇功能 (1-5): ");

            String choice = scanner.nextLine().trim();
            System.out.println("--------------------------------------------------");

            switch (choice) {
                case "1":
                    System.out.print("請輸入歌曲代碼: ");
                    String id = scanner.nextLine().trim();
                    System.out.print("請輸入歌曲名稱: ");
                    String title = scanner.nextLine().trim();
                    playlist.addLast(id, title);
                    break;

                case "2":
                    System.out.print("請輸入欲搜尋的歌曲代碼: ");
                    String searchId = scanner.nextLine().trim();
                    PlaylistNode found = playlist.findById(searchId);
                    if (found != null) {
                        System.out.println("找到歌曲：" + found);
                    } else {
                        System.out.println("找不到代碼為 \"" + searchId + "\" 的歌曲！");
                    }
                    break;

                case "3":
                    System.out.print("請輸入欲刪除的歌曲代碼: ");
                    String deleteId = scanner.nextLine().trim();
                    playlist.removeById(deleteId);
                    break;

                case "4":
                    playlist.displayPlaylist();
                    break;

                case "5":
                    System.out.println("已離開");
                    running = false;
                    break;

                default:
                    System.out.println("錯誤：請輸入有效的選單數字 (1-5)");
            }
            System.out.println("==================================================");
        }

        scanner.close();
    }
}