public class Equipment {
    private String id;
    private String name;
    private boolean isAvailable;

    public Equipment(String id, String name) {
        if (id == null || id.trim().isEmpty()) {
            this.id = "UNKNOWN";
        } else {
            this.id = id.trim();
        }

        if (name == null || name.trim().isEmpty()) {
            this.name = "未命名設備";
        } else {
            this.name = name.trim();
        }

        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean borrow() {
        if (!isAvailable) {
            return false;
        }
        isAvailable = false;
        return true;
    }

    public boolean returnEquipment() {
        if (isAvailable) {
            return false;
        }
        isAvailable = true;
        return true;
    }

    @Override
    public String toString() {
        String statusStr = isAvailable ? "可借用" : "已借出";
        return String.format("設備代碼：%-8s | 名稱：%-12s | 狀態：%s", id, name, statusStr);
    }
}