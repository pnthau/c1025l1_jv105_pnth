package module_2.src.ss1_introduce_java.thuc_hanh;

import java.util.Scanner;

public class BMI {
    final static float NORMAL_HIGH = 25.f;
    final static float NORMAL_LOW = 18.5f;
    final static float OVER_WEIGHT = 30.f;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter weight : ");
        float weight = Float.parseFloat(scanner.nextLine());
        System.out.println("Enter height : ");
        float height = Float.parseFloat(scanner.nextLine());

        float bmi = (float) (weight / Math.pow(height, 2.0));
        String interpretation = searchInterpretation2(bmi);
        System.out.println(interpretation);
    }

    public static String searchInterpretation(float bmi) {
        if (bmi < NORMAL_LOW) {
            return "underweight";
        }
        if (bmi < NORMAL_HIGH) {
            return "normal";
        }
        if (bmi < OVER_WEIGHT) {
            return "overweight";
        }
        return "obese";
    }

    public static String searchInterpretation2(float bmi) {
        float[] threshold = {NORMAL_LOW, NORMAL_HIGH, OVER_WEIGHT};
        String[] labels = {"underweight", "normal", "overweight", "Obese"};
        for (int i = 0; i < threshold.length; i++) {
            if (bmi < threshold[i]) {
                return labels[i];
            }
        }
        return labels[labels.length - 1];
    }
}
