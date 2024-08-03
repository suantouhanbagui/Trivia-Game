package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestingHelperFunctions {

    // Methods that can be used for accessing private classes/variables/methods

    @SuppressWarnings("unchecked")
    public static <T, V> V getPrivateVariableHelper(T obj, String variable) throws IllegalAccessException, NoSuchFieldException {
        Field field = obj.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        return (V) field.get(obj);
    }

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
}
