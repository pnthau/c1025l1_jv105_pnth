package module_2.src.ss8_clean_code.CodeGymManagerMVC.Service;

import module_2.src.ss8_clean_code.CodeGymManagerMVC.Entity.StudentEntity;
import module_2.src.ss8_clean_code.CodeGymManagerMVC.Responsitory.StudentRepository;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class StudentService implements IService<StudentEntity> {
    private static final int NOT_FOUND = -1;
    private List<StudentEntity> students;
    private static int length;

    public StudentService() {
        this.students = StudentRepository.getAll();
        StudentService.length = this.students.size();
    }

    @Override
    public void getAll() {
//        this.students.forEach(new Consumer<StudentEntity>() {
//            @Override
//            public void accept(StudentEntity student) {
//                System.out.println( "Student " + student.toString());
//            }
//        });
        for (int i = 0; i < this.students.size(); i++) {
            System.out.println("Student " + (i + 1) + "  : " + this.students.get(i));
        }
    }

    @Override
    public void add(StudentEntity student) {
        this.students.add(student);
        StudentService.length++;
    }

    @Override
    public void delete(String id) {
        if (isIdEmpty(id)) {
            System.out.println("the id is empty");
            return;
        }

        int index = this.findIndexById(id);
        if (index == -1) {
            System.out.println("Not found the id student = " + index);
            return;
        }

        students.remove(index);
        StudentService.length--;
        System.out.println("Delete succeed!");
    }

    @Override
    public StudentEntity update(String id) {
        if (isIdEmpty(id)) {
            System.out.println("the id is empty");
            return null;
        }

        int index = this.findIndexById(id);
        if (index == NOT_FOUND) {
            System.out.println("Not found the id student = " + index);
            return null;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update full name or press Enter : ");
        String fullName = scanner.nextLine();
        System.out.println("Update Address  or press Enter : ");
        String address = scanner.nextLine();
        System.out.println("Update Score or press Enter : ");
        String score = scanner.nextLine();
        float parseScore = Float.parseFloat(score);

        StudentEntity oldStudent = this.students.get(index);

        oldStudent.setFullName(fullName.isEmpty() ? oldStudent.getFullName() : fullName);
        oldStudent.setAddress(address.isEmpty() ? oldStudent.getAddress() : address);
        oldStudent.setScore(score.isEmpty() ? oldStudent.getScore() : parseScore);

        System.out.println("Update succeed!");
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

    private boolean isIdEmpty(String id) {
        return id.isEmpty();
    }

    private int findIndexById(String id) {
        for (int i = 0; i < this.students.size(); i++) {
            if (id.equals(this.students.get(i).getId())) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public static String getNextId() {
        return StudentService.length + "";
    }
}
