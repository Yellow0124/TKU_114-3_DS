public class Course {
    private String id;
    private String name;
    private int capacity;
    private int enrolled;

    public Course(String id, String name, int capacity) {
        this.id = (id == null) ? "" : id.trim();
        this.name = (name == null || name.trim().isEmpty()) ? "未命名課程" : name.trim();
        this.capacity = Math.max(capacity, 1);
        this.enrolled = 0;
    }

    public String getId() {
        return id.trim();
    }

    public String getName() {
        return name.trim();
    }

    public int getCapacity() {
        return capacity;
    }

    public int getEnrolled() {
        return enrolled;
    }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public boolean enroll() {
        if (isFull()) {
            return false;
        }
        enrolled++;
        return true;
    }

    public boolean drop() {
        if (enrolled <= 0) {
            return false;
        }
        enrolled--;
        return true;
    }

    @Override
    public String toString() {
        String status = isFull() ? "[已額滿]" : "[尚可選課]";
        return String.format("課程代碼：%-8s | 課程名稱：%-14s | 人數：%2d/%2d 人 | 狀態：%s",
                id, name, enrolled, capacity, status);
    }
}