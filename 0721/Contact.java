public class Contact {
    private String id;
    private String name;
    private String phone;
    private String email;

    public Contact(String id, String name, String phone, String email) {
        this.id = (id == null) ? "" : id.trim();
        this.name = (name == null) ? "" : name.trim();
        this.phone = (phone == null || phone.trim().isEmpty()) ? "未提供" : phone.trim();
        this.email = (email == null || email.trim().isEmpty()) ? "未提供" : email.trim();
    }

    public String getId() {
        return id.trim();
    }

    public String getName() {
        return name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public boolean setPhone(String newPhone) {
        if (newPhone == null || newPhone.trim().isEmpty()) {
            return false;
        }
        this.phone = newPhone.trim();
        return true;
    }

    @Override
    public String toString() {
        return String.format("代碼：%-8s | 姓名：%-10s | 電話：%-12s | Email：%s", id, name, phone, email);
    }
}