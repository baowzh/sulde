package com.mongolia.website.controler.json;

import java.beans.PropertyEditor;
import java.util.Date;

import net.sf.sojo.common.WalkerInterceptor;
import net.sf.sojo.core.Constants;
import net.sf.sojo.core.UniqueIdGenerator;
import net.sf.sojo.interchange.SerializerException;
import net.sf.sojo.util.Util;

import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.web.servlet.view.json.JsonStringWriter;

import com.mongolia.website.util.DateUtil;


public class OwnJsonViewWalkerInterceptor implements WalkerInterceptor {
	private StringBuffer jsonString = new StringBuffer();
	private boolean ignoreNullValues = true;
	private PropertyEditorRegistry propertyEditorRegistry;
	private String objectName;
	private boolean convertAllMapValues;
	private String keepValueTypesMode;

	public String getJsonString() {
		return jsonString.toString();
	}

	public boolean getIgnoreNullValues() {
		return ignoreNullValues;
	}

	public void setIgnoreNullValues(boolean ignoreNullValues) {
		this.ignoreNullValues = ignoreNullValues;
	}

	public void startWalk(Object pvStartObject) {
		jsonString = new StringBuffer();
	}

	public void endWalk() {
		Util.delLastComma(jsonString);
	}

	/**
	 * Convert escape (control) character from a JSON representation in a
	 * Java-String. This means: <code>\\b</code> to <code>\b</code>
	 * 
	 * @param pvValue
	 * @return converted String
	 */
	public static String handleControlCharacterBack(final String pvValue) {

		if (pvValue == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		int l = pvValue.length();
		char c;
		for (int i = 0; i < l; i++) {
			c = pvValue.charAt(i);
			switch (c) {
			case 0:
				break;
			case '\n':
			case '\r':
			case '\t':
				sb.append(c);
				break;
			case '\\':
				i++;
				c = pvValue.charAt(i);
				switch (c) {
				case 'b':
					sb.append('\b');
					break;
				case 't':
					sb.append('\t');
					break;
				case 'n':
					sb.append('\n');
					break;
				case 'f':
					sb.append('\f');
					break;
				case 'r':
					sb.append('\r');
					break;
				default:
					sb.append(c);
				}
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * Convert escape (control) character to a JSON representation. This means:
	 * <code>\b</code> to <code>\\b</code>
	 * 
	 * @param pvValue
	 * @return converted String
	 */
	public static Object handleControlCharacter(final Object pvValue) {
		Object lvReturn = pvValue;
		if (lvReturn != null && lvReturn.getClass().equals(String.class)) {
			String lvString = lvReturn.toString();
			int len = lvString.length();
			char vorgChar;
			char c = 0;
			StringBuffer sb = new StringBuffer(len + 4);

			for (int i = 0; i < len; i++) {
				vorgChar = c;
				c = lvString.charAt(i);
				switch (c) {
				case '\\':
					sb.append("\\\\");
					break;
				case '"':
					sb.append('\\').append(c);
					break;
				case '/':
					sb.append('\\').append(c);
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\t':
					sb.append("\\t");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\r':
					sb.append("\\r");
					break;
				default:
					sb.append(c);
				}
			}
			return sb.toString();
		} else {
			if(Date.class.isAssignableFrom(lvReturn.getClass())){
				return DateUtil.convertDateToString((Date) lvReturn);
			}
			return lvReturn;
		}
	}

	public String handleSimpleJsonValue(Object pvValue, String path) {
		Object convertedValue = convertJsonValue(pvValue, path);
		return handleJsonValue(convertedValue);
	}

	public String handleJsonValue(Object pvValue) {
		String s = "";
		if (pvValue != null) {
			Object o = handleControlCharacter(pvValue);
			s = object2StringWithDoubleQuote(o);
		}
		return s;
	}

	public String object2StringWithDoubleQuote(Object pvObject) {
		StringBuffer s = new StringBuffer("");
		if (JsonStringWriter.MODE_KEEP_VALUETYPES_BOOLEANS
				.equals(keepValueTypesMode)) {
			if (pvObject.getClass().equals(Boolean.class))
				s.append(pvObject);
			else
				s.append("\"").append(pvObject).append("\"");
		} else if (JsonStringWriter.MODE_KEEP_VALUETYPES_ALL
				.equals(keepValueTypesMode)) {
			if (pvObject.getClass().equals(String.class)
					|| pvObject.getClass().equals(Character.class)) {
				s.append("\"").append(pvObject).append("\"");
			} else if(Date.class.isAssignableFrom(pvObject.getClass())){
				s.append("\"").append(DateUtil.convertDateToString((Date) pvObject)).append("\"");
			}else {
				s.append(pvObject);
			}
		} else
			s.append("\"").append(pvObject).append("\"");

		return s.toString();
	}

	private Object convertJsonValue(Object pvValue, String path) {
		if (path != null
				&& propertyEditorRegistry != null
				&& (convertAllMapValues || path.startsWith("(" + objectName
						+ ")."))) {
			String p = path.replaceAll("^\\(" + objectName + "\\).", "")
					.replaceAll("\\).\\[", "\\)\\[");
			PropertyEditor ce = propertyEditorRegistry.findCustomEditor(
					pvValue.getClass(), p);
			if (ce == null)
				return pvValue;
			else {
				ce.setValue(pvValue);
				return ce.getAsText();
			}

		} else
			return pvValue;
	}

	public boolean visitElement(Object pvKey, int pvIndex, Object pvValue,
			int pvType, String pvPath, int pvNumberOfRecursion) {
		// --- SIMPLE ---
		if (pvPath.indexOf("class") >= 0
				|| pvPath.indexOf(UniqueIdGenerator.UNIQUE_ID_PROPERTY) >= 0) {
			return false;
		}
		if (pvType == Constants.TYPE_SIMPLE) {
			if (pvKey != null && pvKey.getClass().equals(String.class)) {
				jsonString.append(handleJsonValue(pvKey)).append(":");
			} else if (pvKey != null) {
				throw new SerializerException(
						"JSON support only properties/keys from type String and not: '"
								+ pvKey.getClass().getName() + "' (" + pvKey
								+ ")");
			}
			jsonString.append(handleSimpleJsonValue(pvValue, pvPath)).append(
					",");
		}

		// --- NULL ---
		else if (pvType == Constants.TYPE_NULL) {
			if ("".equals(pvPath))
				jsonString.append("null");
			else if (!getIgnoreNullValues()) {
				jsonString.append(handleJsonValue(pvKey)).append(":null,");
			}
		}

		// -- KEY and not SIMPLE ---
		else if (pvKey != null && pvValue != null) {
			if (pvKey != null && pvKey.getClass().equals(String.class)) {
				jsonString.append(handleJsonValue(pvKey)).append(":");
			} else {
				throw new SerializerException(
						"JSON support only properties/keys from type String and not: '"
								+ pvKey.getClass().getName() + "' (" + pvKey
								+ ")");
			}
		}

		return false;
	}

	public void visitIterateableElement(Object pvValue, int pvType,
			String pvPath, int pvBeginEnd) {

		if (pvBeginEnd == Constants.ITERATOR_BEGIN) {

			if (pvType == Constants.TYPE_ITERATEABLE) {
				// if(!pvPath.endsWith("."))
				jsonString.append("[");
			} else if (pvType == Constants.TYPE_MAP) {
				jsonString.append("{");
			}
		} else if (pvBeginEnd == Constants.ITERATOR_END) {
			Util.delLastComma(jsonString);
			if (pvType == Constants.TYPE_ITERATEABLE) {
				jsonString.append("],");
			} else if (pvType == Constants.TYPE_MAP) {
				jsonString.append("},");
			}
		}
	}

	public void setPropertyEditorRegistry(
			PropertyEditorRegistry propertyEditorRegistry) {
		this.propertyEditorRegistry = propertyEditorRegistry;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public void setConvertAllMapValues(boolean convertAllMapValues) {
		this.convertAllMapValues = convertAllMapValues;
	}

	public void setKeepValueTypesMode(String keepValueTypesMode) {
		this.keepValueTypesMode = keepValueTypesMode;
	}
}
