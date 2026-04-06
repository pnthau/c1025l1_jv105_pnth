package module_2.src.ss9_automated_testing_tdd.thuc_hanh;

public class Calculator {
    public int add(int firstOperand, int secondOperand) {
        if (firstOperand / 2 + secondOperand / 2 >= Integer.MAX_VALUE / 2) {
            throw new RuntimeException("Out of range exception");
        }

        if (firstOperand / 2 + secondOperand / 2 <= Integer.MIN_VALUE / 2) {
            throw new RuntimeException("Out of range exception");
        }
        return firstOperand + secondOperand;
    }

}
