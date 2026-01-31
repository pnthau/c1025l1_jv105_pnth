package module_2.src.ss7_abstract_class_interface.bai_tap;

public interface IResizeable {
    void resize(double precent);

    default double randomResize() {
        return Math.random() * 100;
    }
}
