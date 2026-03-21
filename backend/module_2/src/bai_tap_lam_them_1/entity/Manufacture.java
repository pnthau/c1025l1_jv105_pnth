package module_2.src.bai_tap_lam_them_1.entity;

public class Manufacture {
    private String id;
    private String name;
    private String country;

    public Manufacture() {
    }

    public Manufacture(String id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getVehicleCSVToString() {
        return String.join(",", this.getId(), this.getName(), this.getCountry());
    }

    @Override
    public String toString() {
        return String.join(" - ", this.getId(), this.getName(), this.getCountry());
    }
}
