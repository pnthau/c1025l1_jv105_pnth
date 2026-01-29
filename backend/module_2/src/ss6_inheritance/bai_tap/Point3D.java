package module_2.src.ss6_inheritance.bai_tap;

public class Point3D extends Point2D {
    private float z;

    public Point3D() {
        super(1.0f, 1.0f);
        this.z = 1.0f;
    }

    public Point3D(float x, float y, float z) {
        super(x, y);
        this.z = z;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public void setXYZ(float x, float y, float z) {
        this.setX(x);
        this.setY(y);
        this.setZ(z);
    }

    public float[] getXYZ() {
        return new float[]{this.getX(), this.getY(), this.getZ()};
    }

    @Override
    public String toString() {
        return "( " + this.getX() +
                ", " + this.getY() +
                ", " + this.getZ() + ")";
    }
}
