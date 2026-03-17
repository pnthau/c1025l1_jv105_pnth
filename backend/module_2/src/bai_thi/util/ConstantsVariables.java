package module_2.src.bai_thi.util;

import java.util.Scanner;

public final class ConstantsVariables {
    public static final String SPACE_CHAR = " - ";
    public static final int NOT_FOUND = -1;
    public static final byte TRUCK = 1;
    public static final byte CAR = 2;
    public static final byte BIKE = 3;
    public static final byte ELECTRIC = 4;
    public static final byte RETURN = 5;
    public static final byte EXIT = 5;

    public static final byte ADD = 1;
    public static final byte DISPLAY = 2;
    public static final byte DELETE = 3;
    public static final byte FIND = 4;

    //SP-0004,Ao dai,1000000,50,Viet Nam ,Singapore
    public static final byte ID = 0;
    public static final byte NAME = 1;
    public static final byte PRICE = 2;
    public static final byte QUANTITY = 3;
    public static final byte COUNTRY = 4;

    public static final byte IMPORT_COUNTRY = 5;
    public static final byte IMPORT_DUTY = 5;

    public static final String EXPORT_FILE = "backend/module_2/src/bai_thi/data/exportProducts.csv";
    public static final String IMPORT_FILE = "backend/module_2/src/bai_thi/data/importProducts.csv";

    public static final Scanner scanner = new Scanner(System.in);

    private ConstantsVariables() {
    }
}
