package module_2.src.ss8_clean_code.CodeGymManagerMVC.Responsitory;

import module_2.src.ss8_clean_code.CodeGymManagerMVC.Entity.StudentEntity;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private static List<StudentEntity> students;

    static {
        students = new ArrayList<>();

        students.add(new StudentEntity("1", "nguyen a", "hanoi", 10.f));
        students.add(new StudentEntity("2", "nguyen b", "hanoi2", 7.f));
        students.add(new StudentEntity("3", "nguyen c", "hanoi3", 8.f));
    }

    public static List<StudentEntity> getAll() {
        return StudentRepository.students;
    }
}
