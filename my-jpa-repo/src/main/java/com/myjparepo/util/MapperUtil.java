package main.java.com.myjparepo.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import main.java.com.myjparepo.annotation.Id;
import main.java.com.myjparepo.exception.JpaRepositoryException;
import main.java.com.myjparepo.query.model.ObjectEntityModel;

/**
 * The Class MapperUtil consists methods used for object and entity mapping.
 */
public class MapperUtil {

	/** The Constant DOUBLE. */
	private static final String DOUBLE = "double";
	
	/** The type map. */
	private static Map<String, String> typeMap = null;

	/**
	 * Instantiates a new mapper util.
	 */
	private MapperUtil() {

	}

	static {
		typeMap = new HashMap<>();
		typeMap.put("int", "int");
		typeMap.put("Integer", "int");
		typeMap.put("String", "varchar");
		typeMap.put(DOUBLE, DOUBLE);
		typeMap.put("Double", DOUBLE);
	}

	/**
	 * Gets the column type.
	 *
	 * @param objectType the object type
	 * @return the column type
	 */
	public static String getColumnType(String objectType) {
		return typeMap.get(objectType);
	}

	/**
	 * To column value.
	 *
	 * @param <T> the generic type
	 * @param clazz the clazz
	 * @param object the object
	 * @return the string
	 */
	public static <T> String toColumnValue(Class<T> clazz, Object object) {
		if (Objects.isNull(object)) {
			return null;
		} else if (String.class.equals(clazz) || Character.class.equals(clazz) || char.class.equals(clazz)) {
			return "'" + object + "'";
		} else
			return String.valueOf(object);
	}

	/**
	 * To object.
	 *
	 * @param <T> the generic type
	 * @param resultSet the result set
	 * @param t the t
	 * @return the t
	 * @throws NoSuchFieldException the no such field exception
	 * @throws SecurityException the security exception
	 * @throws SQLException the SQL exception
	 * @throws JpaRepositoryException the jpa repository exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 */
	public static <T> T toObject(ResultSet resultSet, T t) throws NoSuchFieldException, SecurityException, SQLException,
			JpaRepositoryException, IllegalArgumentException, IllegalAccessException {
		Map<String, ObjectEntityModel> fieldColumnMap = getFieldColumnMap(t);
		for (ObjectEntityModel objectEntityModel : fieldColumnMap.values()) {
			Object obj = resultSet.getObject(objectEntityModel.getColumnName());
			Field field = t.getClass().getDeclaredField(objectEntityModel.getObjectName());
			setField(t, obj, field);
		}
		return t;
	}

	/**
	 * Gets the field column map.
	 *
	 * @param <T> the generic type
	 * @param t the t
	 * @return the field column map
	 * @throws JpaRepositoryException the jpa repository exception
	 */
	public static <T> Map<String, ObjectEntityModel> getFieldColumnMap(T t) throws JpaRepositoryException {
		Map<String, ObjectEntityModel> map = new HashMap<>();
		for (Field field : t.getClass().getDeclaredFields()) {
			boolean isPk = false;
			if (field.isAnnotationPresent(Id.class)) {
				isPk = true;
			}
			try {
				Object fieldValue = t.getClass().getDeclaredMethod(getter(field)).invoke(t);
				map.put(field.getName(), new ObjectEntityModel(field.getName(), fieldValue,
						StringUtil.toPascalCase(field.getName()), toColumnValue(field.getType(), fieldValue), isPk));
			} catch (Exception e) {
				throw new JpaRepositoryException(
						"No getter found for " + field.getName() + " in " + t.getClass().getSimpleName());
			}
		}
		return map;
	}

	/**
	 * Gets the ter.
	 *
	 * @param field the field
	 * @return the ter
	 */
	public static String getter(Field field) {
		return "get" + StringUtil.toUppercase(field.getName(), 0, 1);
	}

	/**
	 * Setter.
	 *
	 * @param field the field
	 * @return the string
	 */
	public static String setter(Field field) {
		return "set" + StringUtil.toUppercase(field.getName(), 0, 1);
	}

	/**
	 * Sets the field.
	 *
	 * @param <T> the generic type
	 * @param t the t
	 * @param obj the obj
	 * @param field the field
	 * @throws IllegalAccessException the illegal access exception
	 */
	public static <T> void setField(T t, Object obj, Field field) throws IllegalAccessException {
		field.setAccessible(true);
		field.set(t, obj);
	}

}
