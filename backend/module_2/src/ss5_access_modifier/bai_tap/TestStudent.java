package module_2.src.ss5_access_modifier.bai_tap;

public class TestStudent {
    public static void main(String[] args) {
        Student student = new Student();
        student.setClasses("h2");
        student.setName("HO");
    }
}

class Student {
    private String name = "john";
    private String classes = "CO2";

    public static void main(String[] args) {
        Student student = new Student();
        student.setClasses("h3");
        student.setName("HO");
        System.out.println(student.classes);
    }

    Student() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }
}
