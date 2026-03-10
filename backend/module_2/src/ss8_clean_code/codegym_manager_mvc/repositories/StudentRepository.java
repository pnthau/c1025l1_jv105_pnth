package module_2.src.ss8_clean_code.codegym_manager_mvc.repositories;

import module_2.src.ss8_clean_code.codegym_manager_mvc.entities.StudentEntity;
import module_2.src.ss8_clean_code.codegym_manager_mvc.utils.WriterAndReaderFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private static final String filePath = "backend/module_2/src/ss8_clean_code/codegym_manager_mvc/data/students.csv";


    public List<StudentEntity> getAll() throws IOException {
        List<StudentEntity> students = new ArrayList<>();
        List<String> studentsString = WriterAndReaderFile.readCSVFile(filePath);
        for (String studentString : studentsString) {
            String[] infoStudent = studentString.split(",");
            students.add(new StudentEntity(infoStudent[0], infoStudent[1], infoStudent[2], Float.parseFloat(infoStudent[3])));
        }


        return students;
    }

    public void saveAll(List<StudentEntity> students, boolean isAppend) {
        List<String> studentsString = new ArrayList<>();
        for (StudentEntity student : students) {
            studentsString.add(student.getInfoCSVFile());
        }

        try {
            WriterAndReaderFile.writeCSVFile(studentsString, filePath, isAppend);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(StudentEntity student) {
        try {
            WriterAndReaderFile.writeCSVFile(student.getInfoCSVFile(), filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
