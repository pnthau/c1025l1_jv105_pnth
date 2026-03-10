package module_2.src.ss8_clean_code.codegym_manager_mvc.services;

import module_2.src.ss8_clean_code.codegym_manager_mvc.entities.StudentEntity;
import module_2.src.ss8_clean_code.codegym_manager_mvc.repositories.StudentRepository;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class StudentService implements IStudentService {
    private  final int NOT_FOUND = -1;
    private List<StudentEntity> students;
    private static int length;
    private StudentRepository studentRepository = new StudentRepository();

    public StudentService() {
        try {
            this.students = studentRepository.getAll();
            StudentService.length = this.students.size();
        } catch (IOException e) {
            throw new RuntimeException("Not read filePath", e);
        }

    }

    @Override
    public List<StudentEntity> getAll() {
        return this.students;
    }

    @Override
    public void add(StudentEntity student) {
        studentRepository.save(student);
        StudentService.length++;
    }

    @Override
    public boolean delete(String id) {
        int index = this.findIndexById(id);
        if (index == NOT_FOUND) {
            return false;
        }
        StudentService.length--;
        studentRepository.saveAll(this.students, false);
        return true;
    }

    @Override
    public StudentEntity update(String id, StudentEntity newStudent) {
        int index = this.findIndexById(id);
        if (index == NOT_FOUND) {
            return null;
        }
        StudentEntity oldStudent = this.students.get(index);

        oldStudent.setFullName(
                newStudent.getFullName().isEmpty() ?
                        oldStudent.getFullName() :
                        newStudent.getFullName());
        oldStudent.setAddress(
                newStudent.getAddress().isEmpty() ?
                        oldStudent.getAddress() :
                        newStudent.getAddress());
        oldStudent.setScore(
                newStudent.getScore() == 0 ?
                        oldStudent.getScore() :
                        newStudent.getScore());
        return oldStudent;
    }

    @Override
    public List<StudentEntity> find(String fullName) {
        return this.students.stream().filter(new Predicate<StudentEntity>() {
            @Override
            public boolean test(StudentEntity student) {
                return student.getFullName().equals(fullName);
            }
        }).toList();
    }

    public int findIndexById(String id) {
        for (int i = 0; i < this.students.size(); i++) {
            if (id.equals(this.students.get(i).getId())) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public StudentEntity findById(String id) {
        for (int i = 0; i < this.students.size(); i++) {
            if (id.equals(this.students.get(i).getId())) {
                return this.students.get(i);
            }
        }
        return null;
    }

    public static String getNextId() {
        return StudentService.length + "";
    }
}
