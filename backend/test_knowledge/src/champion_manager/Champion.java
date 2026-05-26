package test_knowledge.src.champion_manager;

public class Champion {
    private String name;
    private String role;

    public Champion(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Tướng: " + name + " | Vai trò: " + role;
    }
}
