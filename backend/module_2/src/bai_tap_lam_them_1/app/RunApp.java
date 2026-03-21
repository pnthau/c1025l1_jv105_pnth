package module_2.src.bai_tap_lam_them_1.app;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.entity.Vehicle;
import module_2.src.bai_tap_lam_them_1.repository.CSVVehicleRepository;
import module_2.src.bai_tap_lam_them_1.repository.IVehicleRepository;
import module_2.src.bai_tap_lam_them_1.service.IVehicleService;
import module_2.src.bai_tap_lam_them_1.service.VehicleService;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidator;
import module_2.src.bai_tap_lam_them_1.view.MenuView;

import java.util.ArrayList;
import java.util.List;

public class RunApp {
    private final static List<Vehicle> vehicleList = new ArrayList<>();
    private static final IVehicleRepository repository = new CSVVehicleRepository(vehicleList);
    private static final VehicleValidator vehicleValidator = new VehicleValidator();
    private static final IVehicleService service = new VehicleService(repository, vehicleValidator);
    private static final VehicleController controller = new VehicleController(service);
    private static final MenuView menu = new MenuView(controller);

    public static void main(String[] args) {
        menu.start();
    }

}
