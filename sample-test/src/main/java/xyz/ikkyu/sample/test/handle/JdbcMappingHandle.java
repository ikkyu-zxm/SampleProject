package xyz.ikkyu.sample.test.handle;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xinming
 * @Date 2020/5/31 21:18
 */
@Slf4j
public class JdbcMappingHandle<T> implements RowMapper<T> {

    private Class<T> clazz;

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Map<Class<?>,Map<String ,Field>> propertyMap;

    public JdbcMappingHandle(Class<T> clazz, Map<Class<?>,Map<String ,Field>> propertyMap) {
        this.clazz = clazz;
        this.propertyMap = propertyMap;
    }


    @Override
    public T mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        //获取 数据行信息
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        Map<String, Field> columnFieldMap = propertyMap.get(clazz);
        if (MapUtils.isEmpty(columnFieldMap)) {
            columnFieldMap = getColumnFieldMap(metaData);
            propertyMap.put(clazz, columnFieldMap);
        }

        try {
            T result = clazz.newInstance(); // 创建此 Class 对象所表示的类的一个新实例
            for (int i = 1; i < columnCount + 1; i++) {
                String columnName = metaData.getColumnName(i);
                // Field 提供有关类或接口的单个字段的信息，以及对它的动态访问权限。反射的字段可能是一个类（静态）字段或实例字段。
                Field field = columnFieldMap.get(columnName);
                if (Objects.isNull(field)) {
                    continue;
                }
                // 修改相应filed的权限，isAccessible 获取此对象的 accessible 标志的值。
                boolean accessFlag = field.isAccessible();
                // 将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java语言访问检查。
                // 值为 false 则指示反射的对象应该实施 Java 语言访问检查。
                field.setAccessible(true);
                // .getString 以 Java 编程语言中 String 的形式获取此 ResultSet 对象的当前行中指定列的值
                String value = resultSet.getString(columnName);
                Object object = resultSet.getObject(columnName);
                String columnTypeName = metaData.getColumnTypeName(i);
                int columnType = metaData.getColumnType(i);
                log.info("columnTypeName {} , columnType {}",columnTypeName,columnType);
                log.info("columnName {}" ,columnName);
                value = value == null ? "" : value;
                // 调用下面的方法 nt--》为新建的object对象
                setFieldValue(result, field, value);
                // 恢复相应field的权限
                field.setAccessible(accessFlag);
            }
            return result;
        } catch (Exception e) {
            log.error("数据转换异常 {}", e);
        }
        return null;
    }


    public Map<String ,Field> getColumnFieldMap(ResultSetMetaData metaData) throws SQLException {
        Field[] declaredFields = clazz.getDeclaredFields();
        Map<String, Field> stringFieldMap = Lists.newArrayList(declaredFields).stream().collect(Collectors.toMap(Field::getName, Function.identity()));

        Map<String ,Field> result = new HashMap<>();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i < columnCount + 1; i++) {
            String columnName = metaData.getColumnName(i);
            String[] split = columnName.split("_");
            StringBuilder columnSb = new StringBuilder();
            columnSb.append(split[0]);
            for (int i1 = 1; i1 < split.length; i1++) {
                String str = upperCase(split[i1]);
                columnSb.append(str);
            }
            String propertyName = columnSb.toString();
            if (Objects.nonNull(stringFieldMap.get(propertyName))) {
                result.put(columnName, stringFieldMap.get(propertyName));
            }
        }
        return result;
    }



    public String upperCase(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }


    public static void setFieldValue(Object form, Field field, String value) {
        if (StringUtils.isBlank(value) || Objects.isNull(form) || Objects.isNull(field)) {
            return;
        }

        //获取已转成String类型的     字段类型
        String elemType = field.getType().toString();
        String typeName = field.getType().getTypeName();
        log.info("elemType {}  typeName {}" ,elemType, typeName);

        //elemType 是否包含boolean 不包含 返回-1
        if (elemType.contains("boolean") || elemType.contains("Boolean")) {
            try {
                //value值转换成 boolean类型
                field.set(form, Boolean.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("byte") || elemType.contains("Byte")) {
            try {
                field.set(form, Byte.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("char") || elemType.contains("Character")) {
            try {
                field.set(form, value.charAt(0));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("double") || elemType.contains("Double")) {
            try {
                field.set(form, Double.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("float") || elemType.contains("Float") ) {
            try {
                field.set(form, Float.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("int") || elemType.contains("Integer")) {
            try {
                field.set(form, Integer.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("long") || elemType.contains("Long")) {
            try {
                field.set(form, Long.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("short") || elemType.contains("Short")) {
            try {
                field.set(form, Short.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }// ↓↓↓↓↓↓ 下面这段就是我添加的Timestamp类型  ↓↓↓↓↓↓
        } else if (elemType.contains("timestamp") || elemType.contains("Timestamp")) {
            try {
                field.set(form, Timestamp.valueOf(value));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else if (elemType.contains("String") || elemType.contains("string")) {
            try {
                field.set(form, value);
            } catch (IllegalAccessException e) {
                log.error("数据转换失败");
            }
        } else if (elemType.contains("date") || elemType.contains("Date")) {
            try {
                java.util.Date date = SIMPLE_DATE_FORMAT.parse(value);
                field.set(form, date);
            } catch (Exception e) {
                log.error("时间转换异常 {}", e);
            }
        }
        else {
            try {
                field.set(form, value);
            } catch (IllegalAccessException e) {
                log.error("数据转换失败");
            }
        }
    }
}
