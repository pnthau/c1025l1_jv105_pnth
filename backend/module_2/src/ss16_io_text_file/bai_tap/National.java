package module_2.src.ss16_io_text_file.bai_tap;

public class National {
    String id;
    String code;
    String name;

    public National(String id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return CapitalizeFirst();
    }

    public void setName(String name) {
        this.name = name;
    }

    private String CapitalizeFirst() {
        return this.name.substring(0, 1).toUpperCase() + this.name.substring(1);
    }

    @Override
    public String toString() {
        return "National{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
