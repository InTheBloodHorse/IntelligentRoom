package cn.lsu.chicken.room.utils;

import cn.lsu.chicken.room.enums.QueryFormEnum;
import cn.lsu.chicken.room.enums.ResultEnum;
import cn.lsu.chicken.room.exception.GlobalException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    // Object criteria, Object queryForm, queryFormEnumList(配置项)
    public static void addFilter(Object criteria, Object queryForm, List<QueryFormEnum> queryFormEnumList) {
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
            String methodName = "and" + StringUtil.FirstUpper(fieldName);
            if("null".equals(value.toString().trim())){
                methodName = methodName + "IsNull";
                addFilter2Criteria(criteria, methodName, new Class[]{});
                continue;
            }
            if("not null".equals(value.toString().trim())){
                methodName = methodName + "IsNotNull";
                addFilter2Criteria(criteria, methodName, new Class[]{});
                continue;
            }
            Integer code = queryFormEnum.getCode();
            Boolean judgeIsBetween = QueryFormEnum.BETWEEN_INTEGER.getCode().equals(code)
                    || QueryFormEnum.BETWEEN_BIGDECIMAL.getCode().equals(code)
                    || QueryFormEnum.BETWEEN_DATE.getCode().equals(code);
            if (judgeIsBetween) {
                // 针对 split(因为 ("1,")split 会得到[1] )
                value = " " + value.toString() + " ";
                List<String> params;
                params = new ArrayList<>(Arrays.asList(value.toString().split(",")));
                if (params.size() != 2) {
                    throw new GlobalException(ResultEnum.PARAMETER_ERROR);
                }
                // 假如 两个参数都为 没限制，说明不用添加约束
                Boolean param0 = params.get(0).trim().equals("");
                Boolean param1 = params.get(1).trim().equals("");
                if (param0 && param1) {
                    continue;
                }
                // 假如第一个参数没有限制，条件为 <= ,并移除
                if (param0) {
                    methodName = methodName + "LessThanOrEqualTo";
                    params.remove(params.get(0));
                } else if (param1) {
                    methodName = methodName + "GreaterThanOrEqualTo";
                    params.remove(params.get(1));
                } else {
                    methodName = methodName + queryFormEnum.getOperator();
                }
                Class[] classes;
                if (QueryFormEnum.BETWEEN_INTEGER.getCode().equals(code)) {
                    //参数为 Integer
                    List<Integer> realParams = StringUtil.stringList2IntegerList(params);
                    // 单参数 >= or <=
                    if (param0 || param1) {
                        classes = new Class[]{Integer.class};
                    } else {
                        Boolean judge = realParams.get(0).compareTo(realParams.get(1)) == 1;
                        if (judge) {
                            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
                        }
                        classes = new Class[]{Integer.class, Integer.class};
                    }
                    addFilter2Criteria(criteria, methodName, classes, realParams.toArray());
                } else if (QueryFormEnum.BETWEEN_BIGDECIMAL.getCode().equals(code)) {
                    //参数为 BigDecimal
                    List<BigDecimal> realParams = StringUtil.stringList2BigDecimalList(params);
                    if (param0 || param1) {
                        classes = new Class[]{BigDecimal.class};
                    } else {
                        Boolean judge = realParams.get(0).compareTo(realParams.get(1)) == 1;
                        if (judge) {
                            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
                        }
                        classes = new Class[]{BigDecimal.class, BigDecimal.class};
                    }
                    addFilter2Criteria(criteria, methodName, classes, realParams.toArray());
                } else if (QueryFormEnum.BETWEEN_DATE.getCode().equals(code)) {
                    //参数为 Date
                    List<Date> realParams = StringUtil.stringList2DatelList(params);
                    if (param0 || param1) {
                        classes = new Class[]{Date.class};
                    } else {
                        Boolean judge = realParams.get(0).after(realParams.get(1));
                        if (judge) {
                            throw new GlobalException(ResultEnum.PARAMETER_ERROR);
                        }
                        classes = new Class[]{Date.class, Date.class};
                    }
                    addFilter2Criteria(criteria, methodName, classes, realParams.toArray());
                }
            } else {
                methodName = methodName + queryFormEnum.getOperator();
                if (code == QueryFormEnum.LIKE.getCode()) {
                    // 前面加了 % 就不走索引了
                    value = value + "%";
                }
                addFilter2Criteria(criteria, methodName, new Class[]{field.getType()}, value);
            }
        }

    }

    private static void addFilter2Criteria(Object criteria, String methodName, Class[] paramsType, Object... value) {
        Method method = ReflectUtil.getMethodByClassAndMethodNameAndArgs(criteria.getClass(), methodName, paramsType);
        ReflectUtil.invokeByMethodAndObjectAndArgs(method, criteria, value);
    }


}
