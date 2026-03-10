package module_2.src.ss12_java_collection.bai_tap.views;

import module_2.src.ss12_java_collection.bai_tap.controllers.ProductController;
import module_2.src.ss12_java_collection.bai_tap.entities.Product;

public class RunApp {
    private final static ProductController productController;

    static {
        productController = new ProductController();
    }

    public static void main(String[] args) {
        productController.displayMenu();
    }
}
