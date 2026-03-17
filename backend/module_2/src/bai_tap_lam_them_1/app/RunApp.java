package module_2.src.bai_tap_lam_them_1.app;

import module_2.src.bai_tap_lam_them_1.controller.VehicleController;
import module_2.src.bai_tap_lam_them_1.repository.IVehicleRepository;
import module_2.src.bai_tap_lam_them_1.repository.VehicleRepository;
import module_2.src.bai_tap_lam_them_1.service.IVehicleService;
import module_2.src.bai_tap_lam_them_1.service.VehicleService;
import module_2.src.bai_tap_lam_them_1.service.VehicleValidator;
import module_2.src.bai_tap_lam_them_1.view.MenuView;

public class RunApp {
    private static final IVehicleRepository repository = new VehicleRepository();
    private static final VehicleValidator vehicleValidator = new VehicleValidator();
    private static final IVehicleService service = new VehicleService(repository, vehicleValidator);
    private static final VehicleController controller = new VehicleController(service);
    private static final MenuView menu = new MenuView(controller);

    public static void main(String[] args) {
        menu.start();
    }


}
