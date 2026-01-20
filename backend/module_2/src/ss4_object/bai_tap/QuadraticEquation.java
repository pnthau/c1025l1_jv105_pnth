package module_2.src.ss4_object.bai_tap;

public class QuadraticEquation {
    private double a;
    private double b;
    private double c;

    QuadraticEquation() {
        this.a = 0;
        this.b = 0;
        this.c = 0;
    }

    QuadraticEquation(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double[] solveEquation() {
        if (getDelta() > 0) {
            double x1 = -this.b - Math.sqrt(getDelta()) / 2 * this.a;
            double x2 = -this.b + Math.sqrt(getDelta()) / 2 * this.a;
            return new double[]{x1, x2};
        }
        if (getDelta() == 0) {
            double x1 = -this.b / 2 * this.a;
            return new double[]{x1};
        }
        return new double[]{};

    }
    private double getDelta() {
        return this.a * this.a / 4 * this.b * this.c;
    }
}
