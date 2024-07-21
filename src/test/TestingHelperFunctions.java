package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestingHelperFunctions {

    // Helper method that uses reflection to directly access private variables
    @SuppressWarnings("unchecked")
    public static <T, V> V getPrivateVariableHelper(T obj, String variable) throws IllegalAccessException, NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        return (V) field.get(obj);
    }

    // Helper method that uses reflection to directly set private variables
    public static <T, V> void setPrivateVariableHelper(T obj, String variable, V value) throws IllegalAccessException, NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static Object instantiatePrivateClass(Class<?> outerClass, String innerClassName, Class<?>[] paramTypes, Object[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        Class<?> innerClass = Class.forName(outerClass.getName() + "$" + innerClassName);
        Constructor<?> constructor = innerClass.getDeclaredConstructor(paramTypes);
        constructor.setAccessible(true);
        return constructor.newInstance(args);
    }

    public static Object callPrivateMethod(Object instance, String method, Class<?>[] parameterTypes, Object... parameters) throws Exception {
        Class<?> classA = instance.getClass();
        Method methodA = classA.getDeclaredMethod(method, parameterTypes);
        methodA.setAccessible(true);
        return methodA.invoke(instance, parameters);
    }

    public static Object getPrivateField(Object obj, String fieldName)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(obj);
    }
}
