package module_2.src.bai_tap_lam_them_1.service;

public class VehicleValidator {
    public boolean isValidLicensePlate(String plate) {
        String regex = "^[1-9]{2}[A-Z]-[0-9]{4,5}$";
        return plate.matches(regex);
    }
}
