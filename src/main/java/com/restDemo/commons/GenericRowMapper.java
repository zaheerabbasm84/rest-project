package com.restDemo.commons;

import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Generic RowMapper that maps snake_case DB columns to camelCase DTO fields
 */
public class GenericRowMapper<T> implements RowMapper<T> {

    private final Class<T> type;
    private final boolean strict; // if true, throw error if column missing

    public GenericRowMapper(Class<T> type) {
        this(type, false);
    }

    public GenericRowMapper(Class<T> type, boolean strict) {
        this.type = type;
        this.strict = strict;
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) {
        try {
            T instance = type.getDeclaredConstructor().newInstance();

            // Get all column names from ResultSet
            ResultSetMetaData metaData = rs.getMetaData();
            Set<String> columnNames = new HashSet<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                columnNames.add(metaData.getColumnName(i).toLowerCase());
            }

            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);

                String columnName = camelToSnake(field.getName());

                if (!columnNames.contains(columnName.toLowerCase())) {
                    if (strict) {
                        throw new SQLException("Column '" + columnName + "' is not present in ResultSet");
                    } else {
                        continue; // skip missing column
                    }
                }

                Object value = rs.getObject(columnName);
                if (value != null) {
                    Class<?> fieldType = field.getType();

                    if (fieldType == Long.class) {
                        field.set(instance, ((Number) value).longValue());
                    } else if (fieldType == Integer.class) {
                        field.set(instance, ((Number) value).intValue());
                    } else if (fieldType == Boolean.class) {
                        field.set(instance, ((Boolean) value));
                    } else if (fieldType == String.class) {
                        field.set(instance, value.toString());
                    } else {
                        field.set(instance, value); // fallback
                    }
                }
            }

            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map ResultSet to " + type.getSimpleName(), e);
        }
    }

    /**
     * Convert camelCase to snake_case
     */
    private String camelToSnake(String camelCase) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        return camelCase.replaceAll(regex, replacement).toLowerCase();
    }
}