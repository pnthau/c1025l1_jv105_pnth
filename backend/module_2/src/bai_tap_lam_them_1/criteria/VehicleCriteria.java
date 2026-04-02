package module_2.src.bai_tap_lam_them_1.criteria;

public class VehicleCriteria {
    private String licensePlate;

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleCriteria(Builder builder) {
        this.licensePlate = builder.licensePlate;
    }

    public static class Builder {
        private String licensePlate;

        public Builder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public VehicleCriteria build() {
            return new VehicleCriteria(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
