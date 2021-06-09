package be.lennert.finalwork.server.core.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ObjectUtils {

    private ObjectUtils() {
    }


    //https://stackoverflow.com/questions/41125384/copy-non-null-properties-from-one-object-to-another-using-beanutils-or-similar
    public static <T extends Object> T copyNonNullProperties(T from, T to) {
        if (from == null || from == null || from.getClass() != to.getClass()) return null;

        final BeanWrapper src = new BeanWrapperImpl(from);
        final BeanWrapper target = new BeanWrapperImpl(to);

        for (final Field property : to.getClass().getDeclaredFields()) {
            Object providedObject = src.getPropertyValue(property.getName());
            if (providedObject != null) {
                target.setPropertyValue(
                        property.getName(),
                        providedObject);
            }
        }
        return to;
    }

    public static <T extends Object> T nullifyStringsAndArrays(T o) {

        for (Field f : o.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (f.getType().equals(String.class)) {
                    String value = (String) f.get(o);
                    if (value != null && value.trim().isEmpty()) {
                        f.set(o, null);
                    }
                } else if (f.getType().equals(ArrayList.class)) {
                    ArrayList value = (ArrayList) f.get(o);
                    if (value.size() == 0) {
                        f.set(o, null);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return o;
    }
}

