package repository;

import entity.Book;
import entity.Student;

import java.util.List;

public interface IStudentRepository {
    List<Student> findAll();
    public Student findById(int id);
}
