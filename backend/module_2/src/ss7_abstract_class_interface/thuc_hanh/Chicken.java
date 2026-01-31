package module_2.src.ss7_abstract_class_interface.thuc_hanh;

public class Chicken extends Animal implements IEdible {
    @Override
    public String howtoEat() {
        return "boil";
    }

    @Override
    public String makeSound() {
        return "Cook~~";
    }
}
