public class Patient {
    private String id;
    private String name;
    private String department;

    public Patient(String id, String name, String department) {
        this.id = (id == null) ? "" : id.trim();
        this.name = (name == null || name.trim().isEmpty()) ? "無名氏" : name.trim();
        this.department = (department == null || department.trim().isEmpty()) ? "一般科" : department.trim();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-5s (%s)", id, name, department);
    }
}