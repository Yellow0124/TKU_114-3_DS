public class PricePrinter {
    public static void main(String[] args) {
        printItem("ball", 500);
    }

    public static void printItem(String itemName, int price) {
        System.out.println("商品名稱: " + itemName + ", 價格: $" + price);
    }
}
