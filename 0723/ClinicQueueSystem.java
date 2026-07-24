import java.util.ArrayDeque;
import java.util.Deque;

public class ClinicQueueSystem {

    private Deque<Patient> waitingQueue;
    private int totalServedCount;

    public ClinicQueueSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.totalServedCount = 0;
    }

    public boolean containsId(String id) {
        if (id == null || id.trim().isEmpty())
            return false;
        String searchId = id.trim();
        for (Patient p : waitingQueue) {
            if (p.getId().equalsIgnoreCase(searchId)) {
                return true;
            }
        }
        return false;
    }

    public boolean register(String id, String name, String department) {
        if (id == null || id.trim().isEmpty()) {
            System.out.println("掛號失敗: 掛號號碼不可為空白");
            return false;
        }

        if (containsId(id)) {
            System.out.println("掛號失敗: 號碼 \"" + id + "\" 已在等待佇列中，不可重複掛號");
            return false;
        }

        Patient patient = new Patient(id, name, department);
        waitingQueue.offer(patient);
        System.out.println("[掛號成功] " + patient + "，目前總等待人數: " + waitingQueue.size());
        return true;
    }

    public Patient callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[叫號提示] 目前沒有人在等待看診");
            return null;
        }

        Patient patient = waitingQueue.poll();
        totalServedCount++;
        System.out.println("[叫號看診] 請 " + patient + " 至診間看診");
        return patient;
    }

    public Patient peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[下一位] 目前佇列為空，無下一位等待病人");
            return null;
        }

        Patient next = waitingQueue.peek();
        System.out.println("[下一位預告] 請預備: " + next);
        return next;
    }

    public void displayStatusAndStatistics() {
        System.out.println("--------------------------------------------------");
        System.out.println("【診所候診與服務統計報告】");

        if (waitingQueue.isEmpty()) {
            System.out.println("目前等待清單：[無人等待]");
        } else {
            System.out.println("目前等待清單 (依看診順序): ");
            int index = 1;
            for (Patient p : waitingQueue) {
                System.out.println("    " + index + ". " + p);
                index++;
            }
        }

        int internalMedicineCount = 0;
        int ophthalmologyCount = 0;
        int otherDeptCount = 0;

        for (Patient p : waitingQueue) {
            String dept = p.getDepartment();
            if ("內科".equals(dept)) {
                internalMedicineCount++;
            } else if ("眼科".equals(dept)) {
                ophthalmologyCount++;
            } else {
                otherDeptCount++;
            }
        }

        System.out.println("\n 統計數據: ");
        System.out.println("目前總等待人數: " + waitingQueue.size());
        System.out.println("內科等待人數: " + internalMedicineCount);
        System.out.println("眼科等待人數: " + ophthalmologyCount);
        if (otherDeptCount > 0) {
            System.out.println("      - 其他科別等待 ：" + otherDeptCount);
        }
        System.out.println("本日累積總服務人數：" + totalServedCount);
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("【測試 1】空佇列叫號與檢視");
        clinic.callNext();
        clinic.peekNext();

        System.out.println("\n【測試 2】病人掛號測試");
        clinic.register("P001", "王小明", "內科");
        clinic.register("P002", "李美玲", "眼科");
        clinic.register("P003", "張大衛", "內科");

        System.out.println("\n【測試 3】嘗試使用重複號碼 \"P001\" 掛號");
        clinic.register("P001", "陳阿民", "皮膚科");

        System.out.println("\n【測試 4】查看下一位與狀態統計");
        clinic.peekNext();
        clinic.displayStatusAndStatistics();

        System.out.println("\n【測試 5】叫號兩位看診");
        clinic.callNext();
        clinic.callNext();

        System.out.println("\n【測試 6】新病人掛號 (P004 眼科, P005 牙科)");
        clinic.register("P004", "林小華", "眼科");
        clinic.register("P005", "趙子龍", "牙科");

        System.out.println("\n【測試 7】叫號剩餘所有病人並查看最終統計");
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();
        clinic.callNext();

        System.out.println();
        clinic.displayStatusAndStatistics();
    }
}