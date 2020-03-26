package com.cmb.jiantao.utils;

import com.alibaba.otter.canal.protocol.CanalEntry;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CanalDataHandler extends TypeConvertHandler {

    public static <T> T ConvertToBean(List<CanalEntry.Column> columnList, Class<T> clazz) {
        T bean = null;
        try {
            bean = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            Field.setAccessible(fields, true);
            Map<String, Field> fieldMap = new HashMap<>(fields.length);
            for (Field field : fields) {
                fieldMap.put(field.getName().toLowerCase(), field);
            }
            if (fieldMap.containsKey("serialVersionUID")) {
                fieldMap.remove("serialVersionUID".toLowerCase());
            }
            for (CanalEntry.Column column : columnList) {
                String columnName = column.getName();
                String columnValue = column.getValue();
                if (fieldMap.containsKey(columnName)) {
                    Field field = fieldMap.get(columnName);
                    Class<?> type = field.getType();
                    if (BEAN_FIELD_TYPE.containsKey(type)) {
                        switch (BEAN_FIELD_TYPE.get(type)) {
                            case "Integer":
                                field.set(bean, parseToInteger(columnValue));
                                break;
                            case "Long":
                                field.set(bean, parseToLong(columnValue));
                                break;
                            case "Double":
                                field.set(bean, parseToDouble(columnValue));
                                break;
                            case "String":
                                field.set(bean, columnValue);
                                break;
                            case "java.util.Date":
                                field.set(bean, parseToDate(columnValue));
                                break;
                            case "java.sql.Date":
                                field.set(bean, parseToSqlDate(columnValue));
                                break;
                            case "java.sql.Timestamp":
                                field.set(bean, parseToTimestamp(columnValue));
                                break;
                            case "java.sql.Time":
                                field.set(bean, parseToTime(columnValue));
                                break;

                        }
                    }else {
                    }
                }
            }
        } catch (InstantiationException|IllegalAccessException e){
            System.err.println("无法转换对象");
        }
        return bean;
    }
}
