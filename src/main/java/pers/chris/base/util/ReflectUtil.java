package pers.chris.base.util;

import java.lang.reflect.InvocationTargetException;

/**
 * @Description
 * @Author Chris
 * @Date 2023/5/14
 */
public class ReflectUtil {

    private ReflectUtil() {
    }

    public static <T> T newInstance(String className) {
        Class<T> clazz = newClass(className);
        return newInstance(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> Class<T> newClass(String className) {
        Class<T> clazz;
        try {
            clazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clazz;
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
