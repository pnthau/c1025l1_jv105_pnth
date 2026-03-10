package module_2.src.ss12_java_collection.bai_tap.utils;

import module_2.src.ss12_java_collection.bai_tap.entities.Product;
import module_2.src.ss16_io_text_file.bai_tap.National;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WriterAndReaderFile {
    public static <T> void writeObjectToBinaryFile(String filePath, List<T> list) throws IOException {
        File file = new File(filePath);
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(list);
        }
    }

    public static <T> List<T> readBinaryFileToObject(String filePath) throws IOException, ClassNotFoundException {
        File file = new File(filePath);
        if (file.length() == 0) {
            return new ArrayList<>();
        }
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (List<T>) objectInputStream.readObject();
        }
    }
}
