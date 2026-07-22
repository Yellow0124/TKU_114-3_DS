public class CartItem {
    private String id;
    private String name;
    private int price;
    private int quantity;

    public CartItem(String id, String name, int price, int quantity) {
        this.id = (id == null) ? "" : id.trim();
        this.name = (name == null || name.trim().isEmpty()) ? "未命名商品" : name.trim();
        this.price = Math.max(price, 1);
        this.quantity = Math.max(quantity, 1);
    }

    public String getId() {
        return id.trim();
    }

    public String getName() {
        return name.trim();
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean addQuantity(int amount) {
        if (amount <= 0) {
            return false;
        }
        this.quantity += amount;
        return true;
    }

    public boolean setQuantity(int newQuantity) {
        if (newQuantity <= 0) {
            return false;
        }
        this.quantity = newQuantity;
        return true;
    }

    public long getSubtotal() {
        return (long) price * quantity;
    }

    @Override
    public String toString() {
        return String.format("代碼：%-8s | 名稱：%-12s | 單價：$%6d | 數量：%3d | 小計：$%8d",
                id, name, price, quantity, getSubtotal());
    }
}
