public class TemperatureLevel {
    public static void main(String[] args) {
        int temperature = 25;

        if (temperature < 15) {
            System.out.println("Cold");
        } else if (temperature > 15 && temperature < 28) {
            System.out.println("Comfortable");
        } else {
            System.out.println("Hot");
        }
    }
}
