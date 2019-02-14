package cn.lsu.chicken.room.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

@Component
public class EntityExampleUtil {
    @Autowired
    private Environment environment;

    @Autowired
    private static Environment staticEnvironment;

    @PostConstruct
    public void init() {
        staticEnvironment = environment;
    }

    public static Object getEntityExample(String className, String name) {
        String classPath = staticEnvironment.getProperty("entity_path") + "." + className;
        Class example = null;
        Class criteria = null;
        try {
            example = Class.forName(classPath);
            criteria = Class.forName(classPath + "$Criteria");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object entityExample = null;
        try {
            entityExample = example.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Method create = null;
        try {
            create = example.getMethod("createCriteria");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        Object entitycriteria = null;
        try {
            entitycriteria = create.invoke(entityExample);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.asList(criteria.getMethods()));
        Method add = null;
        try {
            add = criteria.getMethod("andNameEqualTo", new Class[]{String.class});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
//        cn.lsu.chicken.room.domain.CompanyExample$Criteria.andNameEqualTo
        try {
            add.invoke(entitycriteria, name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return entityExample;
    }

}
