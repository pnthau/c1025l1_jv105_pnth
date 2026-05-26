package test_knowledge.src.champion_manager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        ArrayList<Champion> championList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Champion Manager v2.0 ---");

        while (true) {
            System.out.print("Nhập tên tướng (hoặc gõ 'exit' để dừng): ");
            String input = scanner.nextLine();

            if (input.equals("exit")) {
                break;
            }

            System.out.print("Nhập vai trò tướng: ");
            String inputRole = scanner.nextLine();


            Champion champion = new Champion(input, inputRole);
            championList.add(champion);
        }

        System.out.println("\nDanh sách tướng của John:");

        for (Champion champion : championList) {
            System.out.println(champion);

        }
    }
}
