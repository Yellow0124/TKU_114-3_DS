public class SubtotalCalculator {
    public static void main(String[] args) {
        int result = calculateSubtotal(50, 3);
        System.out.println("小計: " + result);
    }

    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }
}
