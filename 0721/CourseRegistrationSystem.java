import java.util.ArrayList;
import java.util.Scanner;

public class CourseRegistrationSystem {

    private static ArrayList<Course> courseList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initDefaultCourses();

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("請選擇功能 (1-8): ");
            String choice = scanner.nextLine().trim();
            System.out.println("-------------------------------------------------------------------------");

            switch (choice) {
                case "1":
                    addCourse(scanner);
                    break;
                case "2":
                    enrollCourse(scanner);
                    break;
                case "3":
                    dropCourse(scanner);
                    break;
                case "4":
                    deleteCourse(scanner);
                    break;
                case "5":
                    searchCourse(scanner);
                    break;
                case "6":
                    listAllCourses();
                    break;
                case "7":
                    displayStatistics();
                    break;
                case "8":
                    System.out.println("感謝使用選課管理系統，再見！");
                    running = false;
                    break;
                default:
                    System.out.println("⚠️ 錯誤：請輸入有效的選單數字 (1-8)！");
            }
            System.out.println("=========================================================================");
        }

        scanner.close();
    }

    private static void initDefaultCourses() {
        Course c1 = new Course("CS101", "Java 程式設計", 3);
        Course c2 = new Course("CS102", "資料結構", 2);

        c2.enroll();
        c2.enroll();

        courseList.add(c1);
        courseList.add(c2);
    }

    private static void showMenu() {
        System.out.println("\n=== 選課管理系統 ===");
        System.out.println("1. 新增課程");
        System.out.println("2. 學生選課 (額滿不可再加)");
        System.out.println("3. 學生退選");
        System.out.println("4. 刪除課程");
        System.out.println("5. 搜尋課程 (依代碼或名稱)");
        System.out.println("6. 列出所有課程");
        System.out.println("7. 顯示統計摘要 (總數/總人次/額滿清單)");
        System.out.println("8. 離開系統");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("請輸入新課程代碼: ");
        String id = scanner.nextLine().trim();

        if (id.isEmpty()) {
            System.out.println("新增失敗：課程代碼不可為空白！");
            return;
        }

        if (findCourseById(id) != null) {
            System.out.println("新增失敗：代碼 \"" + id + "\" 已存在，代碼不可重複！");
            return;
        }

        System.out.print("請輸入課程名稱: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("新增失敗：課程名稱不可為空白！");
            return;
        }

        System.out.print("請輸入課程容量人數 (必須 > 0): ");
        try {
            int capacity = Integer.parseInt(scanner.nextLine().trim());
            if (capacity <= 0) {
                System.out.println("新增失敗：課程容量必須大於 0！");
                return;
            }

            Course newCourse = new Course(id, name, capacity);
            courseList.add(newCourse);
            System.out.println("成功新增課程 -> " + newCourse.getName() + " (容量: " + capacity + " 人)");

        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的容量數字");
        }
    }

    private static void enrollCourse(Scanner scanner) {
        System.out.print("請輸入欲選課的課程代碼: ");
        String id = scanner.nextLine().trim();

        Course course = findCourseById(id);
        if (course == null) {
            System.out.println("選課失敗：找不到代碼為 \"" + id + "\" 的課程");
            return;
        }

        if (course.enroll()) {
            System.out.println("選課成功！\"" + course.getName() + "\" 目前人數："
                    + course.getEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println("選課失敗：\"" + course.getName() + "\" 已達容量上限 ("
                    + course.getCapacity() + " 人)，無法再新增人數！");
        }
    }

    private static void dropCourse(Scanner scanner) {
        System.out.print("請輸入欲退選的課程代碼: ");
        String id = scanner.nextLine().trim();

        Course course = findCourseById(id);
        if (course == null) {
            System.out.println("退選失敗：找不到代碼為 \"" + id + "\" 的課程！");
            return;
        }

        if (course.drop()) {
            System.out.println("退選成功！\"" + course.getName() + "\" 目前人數："
                    + course.getEnrolled() + "/" + course.getCapacity());
        } else {
            System.out.println("退選失敗：\"" + course.getName() + "\" 目前選課人數為 0，無法退選！");
        }
    }

    private static void deleteCourse(Scanner scanner) {
        System.out.print("請輸入欲刪除的課程代碼: ");
        String id = scanner.nextLine().trim();

        Course course = findCourseById(id);
        if (course == null) {
            System.out.println("刪除失敗：找不到代碼為 \"" + id + "\" 的課程！");
            return;
        }

        courseList.remove(course);
        System.out.println("刪除成功：已移除課程 \"" + course.getName() + "\" (" + course.getId() + ")。");
    }

    private static void searchCourse(Scanner scanner) {
        System.out.print("請輸入欲搜尋的課程代碼或關鍵字: ");
        String keyword = scanner.nextLine().trim();

        if (keyword.isEmpty()) {
            System.out.println(" 請輸入有效的關鍵字！");
            return;
        }

        boolean found = false;
        System.out.println("搜尋結果：");
        for (Course c : courseList) {
            if (c.getId().equalsIgnoreCase(keyword) || c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println("  " + c);
                found = true;
            }
        }

        if (!found) {
            System.out.println("  找不到符合 \"" + keyword + "\" 的課程。");
        }
    }

    private static void listAllCourses() {
        if (courseList.isEmpty()) {
            System.out.println("目前系統內尚無任何課程。");
            return;
        }

        System.out.println("所有課程清單：");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + courseList.get(i));
        }
    }

    private static void displayStatistics() {
        int totalCourses = courseList.size();
        int totalEnrolledStudents = 0;
        ArrayList<Course> fullCourses = new ArrayList<>();

        for (Course c : courseList) {
            totalEnrolledStudents += c.getEnrolled();
            if (c.isFull()) {
                fullCourses.add(c);
            }
        }

        System.out.println("【選課系統統計摘要資訊】");
        System.out.println("  1. 總開設課程數: " + totalCourses + " 門");
        System.out.println("  2. 總選課人次: " + totalEnrolledStudents + " 人次");
        System.out.println("  3. 已額滿課程數: " + fullCourses.size() + " 門");

        if (!fullCourses.isEmpty()) {
            System.out.println("額滿課程明細如下：");
            for (Course fc : fullCourses) {
                System.out.println("     - [" + fc.getId() + "] " + fc.getName() + " (滿額: " + fc.getCapacity() + " 人)");
            }
        } else {
            System.out.println("目前沒有任何額滿的課程");
        }
    }

    private static Course findCourseById(String id) {
        if (id == null || id.isEmpty())
            return null;

        for (Course c : courseList) {
            if (c.getId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}