package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;
import cn.lsu.chicken.room.helper.ExampleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Deprecated
public class EntityExampleUtil {


    @Autowired
    private Environment environment;

    @Autowired
    private static Environment staticEnvironment;

    @PostConstruct
    public void init() {
        staticEnvironment = environment;
    }

    static Map operatorMap;

    static {
        operatorMap = new HashMap<String, String>();
        operatorMap.put("eq", "EqualTo");
    }

    public static Object getEntityExample(String className, List<ExampleHelper> filter) {
        // Example类
        String classPath = staticEnvironment.getProperty("entity_path") + "." + className;
        Class example = ReflectUtil.getClassByName(classPath);
        // ExampleCriteria类
        String criteriaName = classPath + "$Criteria";
        Class criteria = ReflectUtil.getClassByName(criteriaName);

        String methodCreate = "createCriteria";
        Method create = ReflectUtil.getMethodByClassAndMethodName(example, methodCreate);
        Object entityExample = ReflectUtil.getObjectByClass(example);
        // Example.Criteria对象
        Object entityCriteria = ReflectUtil.invokeByMethodAndObject(create, entityExample);

        for (ExampleHelper f : filter) {
            String methodName = getMethodNameByExample(f);
            Object[] args = f.getArgs();
            Method method = ReflectUtil.getMethodByClassAndMethodNameAndArgs(criteria, methodName, getArgsClass(args));
            ReflectUtil.invokeByMethodAndObjectAndArgs(method, entityCriteria, args);
        }


        return entityExample;
    }

    private static String getMethodNameByExample(ExampleHelper e) {
        String logic = e.getLogic();
        if (!"or".equals(logic) && !"and".equals(logic)) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        String operator = e.getOperator();
        if (operatorMap.containsKey(operator) == false) {
            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
        }
        return logic + e.getField() + operatorMap.get(operator);
    }

    private static Class[] getArgsClass(Object[] objects) {
//        System.out.println(objects[0].getClass());
        Class[] classes = new Class[objects.length];
        for (int i = 0; i < objects.length; i++) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }
}
