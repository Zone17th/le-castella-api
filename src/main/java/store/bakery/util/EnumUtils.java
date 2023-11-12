package store.bakery.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumUtils {

    public static <T extends Enum<T>> List<T> getEnumValues(Class<T> enumClass) {
        T[] enumValues = enumClass.getEnumConstants();
        List<T> enums = new ArrayList<>();

        Collections.addAll(enums, enumValues);

        return enums;
    }
}
