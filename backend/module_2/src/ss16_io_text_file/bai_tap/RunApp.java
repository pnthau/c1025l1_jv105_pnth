package module_2.src.ss16_io_text_file.bai_tap;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RunApp {
    private static final String filePath = "backend/module_2/src/ss16_io_text_file/bai_tap/national.csv";

    public static void main(String[] args) {
        List<National> nationalList = new ArrayList<>();
        try {
            List<String> nationalsString = ReadTextFile(filePath);
            for (String nationalString : nationalsString) {
                String[] stringArray = nationalString.split(",");
                National national = new National(stringArray[0], stringArray[1], stringArray[2]);
                nationalList.add(national);
            }

            for (National national : nationalList) {
                System.out.println(national.toString());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> ReadTextFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.length() <= 0) {
            return null;
        }
        List<String> results = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((line = bufferedReader.readLine()) != null) {
                results.add(line);
            }
            return results;
        }
    }
}
