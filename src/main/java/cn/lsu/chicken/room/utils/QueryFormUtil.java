package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class QueryFormUtil {

    public static Object getExample(Class objectClass, Integer page, Integer size, String order) {
        Constructor constructor = null;
        if (page <= 0 || size <= 0) {
            page = null;
            size = null;
        }
        Object o = null;
        try {
            constructor = objectClass.getConstructor(Integer.class, Integer.class, String.class);
            o = constructor.newInstance(page, size, order);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return o;
    }

    // Object criteria, Class criteriaClass, Object queryForm, Class queryFormClass
    public static void addFilter(Object criteria, Object queryForm, List<QueryFormEnum> queryFormEnumList) {
        Class criteriaClass = criteria.getClass();
        Field[] fields = queryForm.getClass().getDeclaredFields();
        // 除去配置项 QueryFormEnum
        Integer filedLength = fields.length - 1;
        Integer queryLength = queryFormEnumList.size();
        if (filedLength != queryLength) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        for (int i = 0; i < filedLength; i++) {
            QueryFormEnum queryFormEnum = queryFormEnumList.get(i);
            if (queryFormEnum.getCode() == QueryFormEnum.IGNORE.getCode()) {
                continue;
            }
            Field field = fields[i];
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(queryForm);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value == null) {
                continue;
            }
            String fieldName = field.getName();
            String methodName = "and" + StringUtil.FirstUpper(fieldName) + queryFormEnum.getOperator();
            Class[] classes = new Class[1];
            classes[0] = field.getType();
            Method method = ReflectUtil.getMethodByClassAndMethodNameAndArgs(criteriaClass, methodName, classes);
            if (queryFormEnum.getCode() == QueryFormEnum.LIKE.getCode()) {
                value = "%" + value + "%";
            }
            ReflectUtil.invokeByMethodAndObjectAndArgs(method, criteria, value);
        }

    }

}
