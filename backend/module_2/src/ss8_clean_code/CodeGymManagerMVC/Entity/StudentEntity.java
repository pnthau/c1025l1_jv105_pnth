package module_2.src.ss8_clean_code.CodeGymManagerMVC.Entity;

public class StudentEntity extends PersonEntity {
    private float score;

    public StudentEntity() {
    }

    public StudentEntity(String id, String fullName, String address, float score) {
        super(id, fullName, address);
        this.score = score;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

//    @Override
//    public boolean equals(Object object) {
//        String id = (String) object;
//        return this.getId().equals(id);
//    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - %.1f", this.score);
    }
}
