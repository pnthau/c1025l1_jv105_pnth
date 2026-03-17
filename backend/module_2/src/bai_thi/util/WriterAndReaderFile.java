package module_2.src.bai_thi.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class WriterAndReaderFile {
    private static final int EOF = -1;

    public static void writeCSVFile(List<String> studentsString, String filePath, boolean isAppend) throws IOException {
        File file = new File(filePath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            for (String studentString : studentsString) {
                bufferedWriter.write(studentString);
                bufferedWriter.newLine();
                bufferedWriter.close();
            }
        }
    }

    public static void writeCSVFile(String studentString, String filePath) throws IOException {
        File file = new File(filePath);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(studentString);
            bufferedWriter.newLine();
            bufferedWriter.close();
        }
    }

    public static List<String> readCSVFile(String filePath) throws IOException {
        File file = new File(filePath);
        List<String> studentsString = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                studentsString.add(line);
            }
        }
        return studentsString;
    }

    public static void copyFile(String sourcePath, String destinationPath) throws IOException {
        File sourceFile = new File(sourcePath);
        File destionationFile = new File(destinationPath);
        if (sourceFile.length() <= 0) {
            return;
        }
        try (
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                FileOutputStream fileOutputStream = new FileOutputStream(destionationFile);
        ) {
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = fileInputStream.read(buffer)) != EOF) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }
        }
    }

}
