package module_2.src.ss8_clean_code.CodeGymManagerMVC.Entity;

public abstract class PersonEntity {
    private String id;
    private String fullName;
    private String address;

    public PersonEntity() {
    }

    public PersonEntity(String id, String fullName, String address) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s", this.id, this.fullName, this.address);
    }
}
