package module_2.src.ss6_inheritance.bai_tap;

public class MovablePoint extends Point {
    private float xSpeed;
    private float ySpeed;

    public MovablePoint() {
        super(1.0f, 1.f);
        this.xSpeed = 1.f;
        this.ySpeed = 1.f;
    }

    public MovablePoint(float xSpeed, float ySpeed) {
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public MovablePoint(float x, float y, float xSpeed, float ySpeed) {
        super(x, y);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }

    public void setySpeed(float ySpeed) {
        this.ySpeed = ySpeed;
    }

    @Override
    public String toString() {
        return super.toString() + " , speed=(" + this.xSpeed + ", " + this.ySpeed + ")";
    }

    public MovablePoint move() {
        this.setX(super.getX() + this.xSpeed);
        this.setY(super.getY() + this.ySpeed);
        return this;
    }
}
