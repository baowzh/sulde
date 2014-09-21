package com.mongolia.website.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.MessageFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ClassUtils;

/**
 * General iBATIS Utilities class with rules for primary keys and
 * query names.
 *
 * @author zeting
 */
public  class iBatisDaoUtils {

	protected static final Log log = LogFactory.getLog(iBatisDaoUtils.class);

	private iBatisDaoUtils() {
	}
	
	public static String getPrimaryKeyFieldName(Object o) {
		Field[] fieldlist = o.getClass().getDeclaredFields();
		String fieldName = null;
		for (Field fld : fieldlist) {
			if (fld.getName().equals("id") || fld.getName().indexOf("Id") > -1
					|| fld.getName().indexOf("id") > -1
					|| fld.getName().equals("code")
					|| fld.getName().indexOf("Code") > -1) {
				fieldName = fld.getName();
				break;
			}
		}
		return fieldName;
	}

	public static Object getPrimaryKeyValue(Object o) {
		// Use reflection to find the first property that has the name "id" or "Id"
		String fieldName = getPrimaryKeyFieldName(o);
		String getterMethod = "get"
				+ Character.toUpperCase(fieldName.charAt(0))
				+ fieldName.substring(1);

		try {
			Method getMethod = o.getClass().getMethod(getterMethod,
					(Class[]) null);
			return getMethod.invoke(o, (Object[]) null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Could not invoke method '" + getterMethod + "' on "
					+ ClassUtils.getShortName(o.getClass()));
		}
		return null;
	}

	/**
	 * Sets the primary key's value
	 * @param o the object to examine
	 * @param clazz the class type of the primary key
	 * @param value the value of the new primary key
	 */
	@SuppressWarnings("unchecked")
	public static void setPrimaryKey(Object o, Class clazz, Object value) {
		String fieldName = getPrimaryKeyFieldName(o);
		String setMethodName = "set"
				+ Character.toUpperCase(fieldName.charAt(0))
				+ fieldName.substring(1);

		try {
			Method setMethod = o.getClass().getMethod(setMethodName, clazz);
			if (value != null) {
				setMethod.invoke(o, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(MessageFormat.format(
					"Could not set ''{0}.{1} with value {2}", ClassUtils
							.getShortName(o.getClass()), fieldName, value));
		}
	}

	/**
	 * @return Returns the select query name.
	 * @param className the name of the class - returns "get" + className + "s"
	 */
	public static String getSelectQuery(String className) {
		return "get" + className + "s";
	}

	/**
	 * @return Returns the find query name.
	 * @param className the name of the class - returns "get" + className
	 */
	public static String getFindQuery(String className) {
		return "get" + className;
	}

	/**
	 * @return Returns the insert query name.
	 * @param className the name of the class - returns "add" + className
	 */
	public static String getInsertQuery(String className) {
		return "add" + className;
	}

	/**
	 * @return Returns the update query name.
	 * @param className the name of the class - returns "update" + className
	 */
	public static String getUpdateQuery(String className) {
		return "update" + className;
	}

	/**
	 * @return Returns the delete query name.
	 * @param className the name of the class - returns "delete" + className
	 */
	public static String getDeleteQuery(String className) {
		return "delete" + className;
	}

	public static String getValueQuery(String className) {
		return "exist" + className;
	}
}
