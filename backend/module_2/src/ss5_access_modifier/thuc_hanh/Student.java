package module_2.src.ss5_access_modifier.thuc_hanh;

public class Student {
    private int rollno;
    private String name;
    private static String college = "BBDIT";

    public Student(int rollno, String name) {
        this.rollno = rollno;
        this.name = name;
    }

    public void getName() {
        System.out.println(college);
    }

    public static void change(String college) {
        Student.college = college;
    }

    public void display() {
        System.out.println(this.rollno + " " + this.name + " " + Student.college);
    }

    public static void main(String[] args) {
        Student.change("codegym"); //calling change method

        //creating objects
        Student s1 = new Student(111, "Hoang");
        Student s2 = new Student(222, "Khanh");
        Student s3 = new Student(333, "Nam");

        //calling display method
        s1.display();
        s2.display();
        s3.display();
    }
}
