import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingCartSystem {
    private static ArrayList<CartItem> cart = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initDefaultCart();

        boolean running = true;
        while (running) {
            showMenu();
            System.out.print("請選擇功能(1-6): ");
            String choice = sc.nextLine().trim();
            System.out.println("------------------------------------------------------------------");

            switch (choice) {
                case "1":
                    addItemToCart(sc);
                    break;
                case "2":
                    updateQuantity(sc);
                    break;
                case "3":
                    removeItem(sc);
                    break;
                case "4":
                    displayCartAndTotal();
                    break;
                case "5":
                    clearCart();
                    break;
                case "6":
                    System.out.println("感謝使用購物車系統，祝您購物愉快");
                    running = false;
                    break;
                default:
                    System.out.println("錯誤：請輸入有效的選單數字 (1-6)！");
            }
            System.out.println("==================================================================");
        }
        sc.close();
    }

    private static void initDefaultCart() {
        cart.add(new CartItem("P001", "無線滑鼠", 590, 2));
        cart.add(new CartItem("P002", "機械鍵盤", 2490, 1));
    }

    private static void showMenu() {
        System.out.println("\n=== 購物車系統 ===");
        System.out.println("1. 加入商品 (重複代碼自動累加數量)");
        System.out.println("2. 修改商品數量");
        System.out.println("3. 移除商品");
        System.out.println("4. 檢視購物車清單與總額");
        System.out.println("5. 清空購物車");
        System.out.println("6. 結帳離去");
    }

    private static void addItemToCart(Scanner sc) {
        System.out.print("請輸入商品代碼: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            System.out.println("加入失敗：商品代碼不可為空白");
            return;
        }

        CartItem existingItem = findCartItemById(id);

        if (existingItem != null) {
            System.out.println("購物車已存在此商品 (" + existingItem.getName() + "，目前數量: " + existingItem.getQuantity() + ")");
            System.out.print("請輸入欲增加的數量: ");
            try {
                int addQty = Integer.parseInt(sc.nextLine().trim());
                if (existingItem.addQuantity(addQty)) {
                    System.out.println("數量累加成功！" + existingItem.getName() + " 最新數量為: " + existingItem.getQuantity());
                } else {
                    System.out.println("增加失敗：數量必須大於 0");
                }
            } catch (NumberFormatException e) {
                System.out.println("錯誤：請輸入有效的整數數量");
            }
        } else {
            System.out.print("請輸入商品名稱: ");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("加入失敗：商品名稱不可為空白");
                return;
            }

            try {
                System.out.print("請輸入單價: ");
                int price = Integer.parseInt(sc.nextLine().trim());
                if (price <= 0) {
                    System.out.println("加入失敗：數量必須大於 0");
                    return;
                }

                CartItem newItem = new CartItem(id, name, price, price);
                cart.add(newItem);
                System.out.println("成功加入購物車 -> " + newItem.getName());
            } catch (NumberFormatException e) {
                System.out.println("錯誤：單價與數量必須為整數數字！");
            }
        }
    }

    private static void updateQuantity(Scanner sc) {
        System.out.print("請輸入欲修改數量的商品代碼: ");
        String id = sc.nextLine().trim();

        CartItem item = findCartItemById(id);
        if (item == null) {
            System.out.println("修改失敗：購物車中找不到代碼為 \"" + id + "\" 的商品！");
            return;
        }

        System.out.println("目前商品：" + item.getName() + " | 目前數量：" + item.getQuantity());
        System.out.print("請輸入全新的數量 (必須 > 0): ");

        try {
            int newQty = Integer.parseInt(sc.nextLine().trim());
            if (item.setQuantity(newQty)) {
                System.out.println("修改成功！" + item.getName() + " 數量已更新為: " + item.getQuantity());
            } else {
                System.out.println("修改失敗：數量必須大於 0（小於或等於 0 不接受更新）");
            }
        } catch (NumberFormatException e) {
            System.out.println("錯誤：請輸入有效的整數數字");
        }
    }

    private static void removeItem(Scanner sc) {
        System.out.print("請輸入欲移除的商品代碼: ");
        String id = sc.nextLine().trim();

        CartItem item = findCartItemById(id);
        if (item == null) {
            System.out.println("移除失敗：購物車中找不到代碼為 \"" + id + "\" 的商品");
            return;
        }

        cart.remove(item);
        System.out.println("已從購物車中移除商品：" + item.getName() + " (" + item.getId() + ")");
    }

    private static void displayCartAndTotal() {
        if (cart.isEmpty()) {
            System.out.println("您的購物車目前是空的");
            return;
        }

        System.out.println("購物車商品明細：");
        long grandTotal = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            System.out.println("  " + (i + 1) + ". " + item);
            grandTotal += item.getSubtotal();
        }

        System.out.println("------------------------------------------------------------------");
        System.out.println("購物車總金額：$" + grandTotal + " 元");
    }

    private static void clearCart() {
        if (cart.isEmpty()) {
            System.out.println("購物車本來就是空的");
            return;
        }
        cart.clear();
        System.out.println("購物車已全部清空");
    }

    private static CartItem findCartItemById(String id) {
        if (id == null || id.isEmpty())
            return null;

        for (CartItem item : cart) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }
}
