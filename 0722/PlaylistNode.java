public class PlaylistNode {
    private String id;
    private String title;
    public PlaylistNode next;

    public PlaylistNode(String id, String title) {
        this.id = (id == null) ? "" : id.trim();
        this.title = (title == null || title.trim().isEmpty()) ? "未命名歌曲" : title.trim();
        this.next = null;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", id, title);
    }
}
