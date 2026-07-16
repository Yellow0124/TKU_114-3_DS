public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount first = new BankAccount("A001", "Amy", 5000);
        BankAccount second = new BankAccount("A002", "Ben", 1000);

        System.out.println("=== 初始狀態 ===");
        System.out.println(first);
        System.out.println(second);
        System.out.println();

        System.out.println("存款 500 元至 Amy 帳戶：" + first.deposit(500));
        System.out.println("提款 300 元自 Ben 帳戶：" + second.withdraw(300));
        System.out.println("Amy 轉帳 2000 元給 Ben：" + first.transferTo(second, 2000));
        System.out.println("Ben 嘗試超額轉帳 10000 元給 Amy：" + second.transferTo(first, 10000));

        System.out.println("=== 最終狀態 ===");
        System.out.println(first);
        System.out.println(second);
    }
}