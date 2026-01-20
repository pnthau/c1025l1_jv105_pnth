package module_2.src.ss4_object.bai_tap;

public class Fan {
    final static byte SLOW = 1;
    final static byte MEDIUM = 2;
    final static byte FAST = 3;

    private int speed;
    private boolean on;
    private double radius;
    private String color;

    public static void main(String[] args) {
        Fan fan1 = new Fan();
        fan1.setSpeed((int) Fan.FAST);
        fan1.setRadius(10.0);
        fan1.setColor("yellow");
        fan1.setOn(true);

        System.out.println(fan1);

        Fan fan2 = new Fan();

        System.out.println(fan2);
    }

    public Fan() {
        this.speed = this.SLOW;
        this.on = false;
        this.radius = 5.0;
        this.color = "blue";
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.isOn()
                ? String.format("speed = %d, color = %s, radius = %.2f, fan is on", this.speed, this.color, this.radius)
                : String.format("color = %s, radius = %f, fan is off", this.color, this.radius);
    }
}
