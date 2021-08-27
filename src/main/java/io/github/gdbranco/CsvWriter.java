package io.github.gdbranco;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvWriter<T> {
    private static final Logger LOGGER = Logger.getLogger("CsvWriter");

    public static final String CSV_COLUMN_SEPARATOR = ";";
    public static final String CSV_LINE_SEPARATOR_STRING = "\n";
    private Set<String> header = new LinkedHashSet<>();
    private List<Object> values = new ArrayList<>();
    @SuppressWarnings("rawtypes")
	private List<Class> customPrimitiveList = new ArrayList<>();

    public CsvWriter(@SuppressWarnings("rawtypes") Class... customPrimitiveArray) {
		super();
		this.customPrimitiveList = Arrays.asList(customPrimitiveArray);
	}

	public String writeCsv(List<T> dados) {
        if (dados == null || dados.isEmpty()) {
            return "";
        }
        fillData(dados);
        return writeStringCsv();
    }

    private String writeStringCsv() {
        StringBuilder sb = new StringBuilder();
        for (String cabecalho : header) {
            sb.append(cabecalho).append(CSV_COLUMN_SEPARATOR);
        }
        sb.replace(sb.length() - 1, sb.length(), CSV_LINE_SEPARATOR_STRING);
        int i = 0;
        for (Object value : values) {
            sb.append(value == null ? "" : value.toString()).append(CSV_COLUMN_SEPARATOR);
            if ((++i % header.size()) == 0) {
                sb.replace(sb.length() - 1, sb.length(), CSV_LINE_SEPARATOR_STRING);
            }
        }
        return sb.toString();
    }

    private void fillData(List<T> data) {
        for (T t : data) {
            getItemCsvUtil(t, null);
        }
    }

    private void getItemCsvUtil(Object currentData, Field currentField) {
        @SuppressWarnings("rawtypes")
        Class clazz = currentData.getClass();
        if (isPrimitive(currentData) && currentField != null) {
            header.add(currentField.isAnnotationPresent(CsvField.class) 
            		? currentField.getAnnotation(CsvField.class).name() : currentField.getName());
            values.add(currentData == null ? "" : currentData.toString());
            return;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                if (isStatic(field) || isIgnored(field))
                    continue;
                field.setAccessible(true);
                Object obj = field.get(currentData);
                getItemCsvUtil(obj, field);
                field.setAccessible(false);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOGGER.log(Level.WARNING, e.getMessage());
            }
        }
    }

    private boolean isPrimitive(Object source) {
        @SuppressWarnings("rawtypes")
        Class clazz = source.getClass();
        @SuppressWarnings("rawtypes")
		List<Class> defaultPrimitiveList = Arrays.asList(new Class[] {
						Byte.class, Integer.class, Character.class, Boolean.class,
						Double.class, Float.class, Long.class, Short.class,
						Void.class, BigDecimal.class, Date.class, String.class,
						LocalDate.class});
		return clazz.isPrimitive() || defaultPrimitiveList.contains(clazz) || customPrimitiveList.contains(clazz);
    }

    private boolean isStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

    private boolean isIgnored(Field field) {
        return field.isAnnotationPresent(CsvIgnore.class);
    }
}