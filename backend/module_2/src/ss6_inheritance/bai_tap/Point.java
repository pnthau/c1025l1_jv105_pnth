package module_2.src.ss6_inheritance.bai_tap;

public class Point {
    private float x;
    private float y;

    public Point() {
        x = 1.0f;
        y = 1.0f;
    }

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setXY(float x, float y) {
        this.setX(x);
        this.setY(y);
    }

    public float[] getXY() {
        return new float[]{this.x, this.y};
    }

    @Override
    public String toString() {
        return "( " + this.x + ", " + this.y + " )";
    }
}
