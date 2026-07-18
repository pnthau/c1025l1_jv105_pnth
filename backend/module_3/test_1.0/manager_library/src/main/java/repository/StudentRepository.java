package repository;

import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private static final String SELECT_ALL_STUDENTS = "SELECT * FROM students;";
    private static final String SELECT_STUDENT = "SELECT * FROM students where id = ?;";
    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_ALL_STUDENTS);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String studentCode = rs.getString("student_code");
                String name = rs.getString("name");
                String className = rs.getString("class");

                studentList.add(new Student(id, studentCode,name, className));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try (Connection connection = BaseRepository.getConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_STUDENT)) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    String studentCode = resultSet.getString("student_code");
                    String name = resultSet.getString("name");
                    String className = resultSet.getString("class");

                    student = new Student(id, studentCode, name, className);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
}
