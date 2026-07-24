public class PlaylistLinkedList {
    private PlaylistNode head;
    private int size;

    public PlaylistLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public PlaylistNode findById(String id) {
        if (id == null || id.trim().isEmpty()) {
            return null;
        }

        PlaylistNode current = head;
        while (current != null) {
            if (current.getId().equalsIgnoreCase(id.trim())) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public boolean addLast(String id, String title) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("新增失敗: 歌曲代碼不可為空白");
            return false;
        }

        if (findById(id) != null) {
            System.out.println("新增失敗: 歌曲代碼 \"" + id + "\" 已存在，代碼不可重複");
            return false;
        }

        PlaylistNode newNode = new PlaylistNode(id, title);

        if (head == null) {
            head = newNode;
        } else {
            PlaylistNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }

        size++;
        System.out.println("成功將歌曲加入播放清單：" + newNode);
        return true;
    }

    public boolean removeById(String id) {
        if (head == null) {
            System.out.println("刪除失敗：播放清單目前是空的");
            return false;
        }

        if (id == null || id.trim().isEmpty()) {
            System.out.println("刪除失敗：歌曲代碼不可為空白");
            return false;
        }

        String searchId = id.trim();

        if (head.getId().equalsIgnoreCase(searchId)) {
            PlaylistNode removedNode = head;
            head = head.next;
            size--;
            System.out.println("成功移除播放清單第一首歌曲：" + removedNode);
            return true;
        }

        PlaylistNode previous = head;
        PlaylistNode current = head.next;

        while (current != null) {
            if (current.getId().equalsIgnoreCase(searchId)) {
                previous.next = current.next;
                size--;
                System.out.println("成功移除歌曲：" + current);
                return true;
            }
            previous = current;
            current = current.next;
        }
        System.out.println("刪除失敗：播放清單中找不到代碼為 \"" + searchId + "\" 的歌曲");
        return false;
    }

    public void displayPlaylist() {
        if (head == null) {
            System.out.println("播放清單目前為空");
            return;
        }

        System.out.println("播放清單完整順序 (共 " + size + " 首): ");
        PlaylistNode current = head;
        int index = 1;
        while (current != null) {
            while (current != null) {
                System.out.print(" " + index + ". " + current);
                System.out.println(current.next != null ? " " : " [播放結束]");
                current = current.next;
                index++;
            }
        }
    }
}
