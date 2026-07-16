public class Rectangle {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        setWidth(width);
        setHeight(height);
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    public boolean setWidth(double width) {
        if (width <= 0) {
            return false;
        }
        this.width = width;
        return true;
    }

    public boolean setHeight(double height) {
        if (height <= 0) {
            return false;
        }
        this.height = height;
        return true;
    }

    public double calculateArea() {
        return this.width * this.height;
    }

    public double calculatePerimeter() {
        return 2 * (this.width + this.height);
    }

    public boolean isSquare() {
        return Double.compare(this.width, this.height) == 0;
    }

    @Override
    public String toString() {
        return "寬：" + this.width + "，高：" + this.height;
    }
}