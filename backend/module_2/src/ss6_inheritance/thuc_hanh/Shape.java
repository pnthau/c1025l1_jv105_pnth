package module_2.src.ss6_inheritance.thuc_hanh;

import module_2.src.ss7_abstract_class_interface.bai_tap.IResizeable;

public abstract class Shape implements IResizeable {
    private String color;
    private boolean filled;

    public Shape() {
        this.color = "green";
        this.filled = true;
    }

    public Shape(String color, boolean filled) {
        this.filled = filled;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "A Shape with color of " + this.color + (this.filled ? " and filled" : " not filled ");
    }

    protected String getClassName() {
        Class<?> supperClass = this.getClass().getSuperclass();
        int lastPositionDotExist = supperClass.toString().lastIndexOf(".");
        return supperClass.toString().substring(lastPositionDotExist + 1);
    }
}
