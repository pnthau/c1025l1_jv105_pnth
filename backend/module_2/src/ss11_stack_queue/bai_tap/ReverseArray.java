package module_2.src.ss11_stack_queue.bai_tap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class ReverseArray {
    public static void main(String[] args) {
//        System.out.println("Origin array  : ");
//        List<Integer> array = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4));
//        System.out.println(array.toString());
//        array = reverseArrayInteger(array);
//        System.out.println("Reverse array  : ");
//        System.out.println(array.toString());

        String origin = "Trung Hau";
        System.out.println("Origin string  : ");
        System.out.println(origin);
        String output = reverseString(origin);
        System.out.println("Reverse string  : ");
        System.out.println(output);
    }

    private static List<Integer> reverseArrayInteger(List<Integer> array) {
        Stack<Integer> stack = new Stack<Integer>();
        List<Integer> reverse = new ArrayList<>();

        array.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                stack.push(integer);
            }
        });
        while (!stack.empty()) {
            reverse.add(stack.pop());
        }
        return reverse;
    }

    private static String reverseString(String str) {
        Stack<String> wStack = new Stack<String>();
        List<String> mWord = List.of(str.split(" "));
        String output = "";
        mWord.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                wStack.push(s);
            }
        });

        while (!wStack.empty()) {
            output += wStack.pop() + " ";
        }
        return output;
    }
}
