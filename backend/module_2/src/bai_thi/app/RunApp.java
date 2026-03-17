package module_2.src.bai_thi.app;


import module_2.src.bai_thi.controller.ProductController;
import module_2.src.bai_thi.view.MenuView;

public class RunApp {
    //    private static final IVehicleRepository repository = new VehicleRepository();
//    private static final VehicleValidator vehicleValidator = new VehicleValidator();
//    private static final IVehicleService service = new VehicleService(repository, vehicleValidator);
//    private static final ProductController controller = new ProductController(service);
//    private static final MenuView menu = new MenuView(controller);
    private static final MenuView menu = new MenuView();

    public static void main(String[] args) {
        menu.start();
    }

}
