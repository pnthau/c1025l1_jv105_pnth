package module_2.src.ss6_inheritance.thuc_hanh;

public class Square extends Rectangle {

    public Square() {
        super(1.0, 1.0);
    }

    public Square(double side) {
        super(side, side);
    }

    public Square(boolean filled, String color, double width) {
        super(filled, color, width, width);
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        super.setLength(width);
    }

    @Override
    public double getWidth() {
        return super.getWidth();
    }

    @Override
    public String toString() {
        return "A Square with side=" + this.getWidth() + " ,which is a subclass of " + super.getClassName();
    }
}
