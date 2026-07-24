import java.util.ArrayDeque;
import java.util.Deque;

public class CounterServiceSystem {

    static class Customer {
        private String number;
        private String name;

        public Customer(String number, String name) {
            this.number = number;
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", number, name);
        }
    }

    private Deque<Customer> waitingQueue;
    private Deque<Customer> historyStack;
    private int ticketCounter;

    public CounterServiceSystem() {
        this.waitingQueue = new ArrayDeque<>();
        this.historyStack = new ArrayDeque<>();
        this.ticketCounter = 1;
    }

    public Customer takeTicket(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("取號失敗：顧客姓名不可為空白");
            return null;
        }

        String number = String.format("A%03d", ticketCounter++);
        Customer customer = new Customer(number, name.trim());
        waitingQueue.offer(customer);

        System.out.println("[取號成功] " + customer + "，目前前面有 " + (waitingQueue.size() - 1) + " 人等待");
        return customer;
    }

    public Customer callNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[叫號提示] 目前沒有人在等待，櫃台請稍候");
            return null;
        }

        Customer servedCustomer = waitingQueue.poll();
        historyStack.push(servedCustomer);

        System.out.println("[櫃台叫號] 請 " + servedCustomer + " 至櫃台辦理！");
        return servedCustomer;
    }

    public Customer peekNext() {
        if (waitingQueue.isEmpty()) {
            System.out.println("[下一位] 目前佇列為空，無下一位顧客。");
            return null;
        }

        Customer next = waitingQueue.peek();
        System.out.println("[下一位預告] 下一位應號顧客為：" + next);
        return next;
    }

    public int getWaitingCount() {
        return waitingQueue.size();
    }

    public void printHistory() {
        System.out.println("--------------------------------------------------");
        System.out.println("【已服務完成紀錄 (最近處理優先)】: ");
        if (historyStack.isEmpty()) {
            System.out.println("  (目前尚無任何服務完成紀錄)");
        } else {
            int index = 1;
            for (Customer c : historyStack) {
                System.out.println("  " + index + ". " + c);
                index++;
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public static void main(String[] args) {

        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("1. 空 Queue 叫號與查看");
        system.callNext();
        system.peekNext();

        System.out.println("\n2. 三位顧客依序取號");
        system.takeTicket("Amy");
        system.takeTicket("Ben");
        system.takeTicket("Cara");

        System.out.println("\n3. 查看下一位與等待人數");
        system.peekNext();
        System.out.println("目前總等待人數: " + system.getWaitingCount() + " 人");

        System.out.println("\n4. 櫃台叫號服務兩人");
        system.callNext();
        system.callNext();

        System.out.println("\n5. 新顧客 David 取號");
        system.takeTicket("David");
        system.peekNext();
        System.out.println("目前總等待人數: " + system.getWaitingCount() + " 人");

        System.out.println("\n6. 叫號清空佇列");
        system.callNext();
        system.callNext();
        system.callNext();

        System.out.println("\n7. 檢查歷史處理紀錄");
        system.printHistory();
    }
}