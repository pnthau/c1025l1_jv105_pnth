package module_2.src.ss8_clean_code.codegym_manager_mvc.entities;

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

    public String getInfoCSVFile() {
        return String.join(",", this.getId(), this.getFullName(), this.getAddress(), this.getScore() + "");
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" - %.1f", this.score);
    }
}
