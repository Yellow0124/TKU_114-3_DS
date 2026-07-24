public class TaskNode {
    private String id;
    private String description;
    private boolean completed;
    public TaskNode next;

    public TaskNode(String id, String description) {
        this.id = (id == null) ? "" : id.trim();
        this.description = (description == null || description.trim().isEmpty()) ? "未命名工作" : description.trim();
        this.completed = false;
        this.next = null;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        String status = completed ? "[已完成]" : "[未完成]";
        return String.format("[%s] %-15s | 狀態：%s", id, description, status);
    }
}