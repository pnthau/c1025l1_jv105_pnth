package module_2.src.ss4_object.bai_tap;

import java.util.Date;
import java.util.Random;

public class StopWatch {
    private long startTime;
    private long endTime;

    StopWatch() {
        this.startTime = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        selectionSort();
        stopWatch.stop();
        System.out.println("Execution time measurement : " + stopWatch.getElapsedTime());
        stopWatch = null;
    }

    private static void selectionSort() {

        final int length = 100_000;
        int[] numbers = generateNumbers(length);
        int selectedIndex = 0;
        for (int i = 0; i < numbers.length; i++) {
            selectedIndex = i;
            for (int j = selectedIndex + 1; j < numbers.length; j++) {
                if (numbers[selectedIndex] > numbers[j]) {
                    selectedIndex = j;
                }
            }
            int temp = numbers[i];
            numbers[i] = numbers[selectedIndex];
            numbers[selectedIndex] = temp;
        }

    }

    private static int[] generateNumbers(int length) {
        return new Random()
                .ints(length, 0, 100)
                .toArray();
    }

    public void start() {
        this.startTime = System.currentTimeMillis();
    }

    public void stop() {
        this.endTime = System.currentTimeMillis();
    }

    public long getElapsedTime() {
        return this.endTime - this.startTime;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public long getEndTime() {
        return this.endTime;
    }
}
