package module_2.src.ss6_inheritance.thuc_hanh;

public class RunApp {
    public static void main(String[] args) {
        Square square = new Square();
        System.out.println(square);

        square = new Square(2.3);
        System.out.println(square);

        square = new Square(true, "yellow", 5.8);
        System.out.println(square);
    }
}
