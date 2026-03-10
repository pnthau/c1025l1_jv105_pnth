package module_2.src.ss11_stack_queue.bai_tap;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiFunction;

public class CountWords {
    public static void main(String[] args) {
        Map<String, Integer> wMap = new TreeMap<>();
        String sentence = "ACt Abc dt Fc act ACT";
        List<String> wList = Arrays.asList(sentence.split(" "));

        for (String word : wList) {
            String lowerWord = word.toLowerCase();
            wMap.compute(lowerWord, new BiFunction<String, Integer, Integer>() {
                @Override
                public Integer apply(String key, Integer value) {
                    return value == null ? 1 : value + 1;
                }
            });
        }


        for (Map.Entry<String, Integer> pairWordCount : wMap.entrySet()) {
            System.out.println(pairWordCount.getKey() + " = " + pairWordCount.getValue());
        }

    }


}
