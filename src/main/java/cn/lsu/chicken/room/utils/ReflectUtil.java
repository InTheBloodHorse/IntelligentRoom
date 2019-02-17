package cn.lsu.chicken.room.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
    public static Class getClassByName(String className) {
        Class result = null;
        try {
            result = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object getObjectByClass(Class className) {
        Object o = null;
        try {
            o = className.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }

    /*
        类
        类方法名
        方法参数
     */
    public static Method getMethodByClassAndMethodNameAndArgs(Class className, String methodName, Class[] args) {
        Method m = null;
        try {
            m = className.getMethod(methodName, args);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return m;
    }

    // 无参方法
    public static Method getMethodByClassAndMethodName(Class className, String methodName) {
        Method m = null;
        try {
            m = className.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return m;
    }

    public static Object invokeByMethodAndObject(Method method, Object o) {
        Object result = null;
        try {
            result = method.invoke(o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Object invokeByMethodAndObjectAndArgs(Method method, Object o, Object... args) {
        Object result = null;
        try {
            result = method.invoke(o, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }
}
