package com.mongolia.website.controler;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;


public class NullableDecimalPropertyEditor extends PropertyEditorSupport {
	private Converter converter;

	public static final int NUMBER = 0;

	private int converType;

	public int getConverType() {
		return converType;
	}

	public void setConverType(int converType) {
		this.converType = converType;
	}

	public NullableDecimalPropertyEditor(Converter converter) {
		this.converter = converter;
	}

	public NullableDecimalPropertyEditor(Converter converter, int converType) {
		this.converter = converter;
		this.converType = converType;
	}

	public Converter getConverter() {
		return this.converter;
	}

	public static final Converter BYTE_CONVERTER = new Converter() {
		public Object stringToNumber(String str) {
			return Byte.valueOf(str);
		}
	};

	public static final Converter SHORT_CONVERTER = new Converter() {
		public Object stringToNumber(String str) {
			if ("on".equals(str))// checkbox
				return 1;
			if (str == null || "".equals(str.trim()))// support string trans
														// int;
				return 0;
			return Short.valueOf(str);
		}
	};

	public static final Converter INTEGER_CONVERTER = new Converter() {
		public Object stringToNumber(String str) {
			if ("on".equals(str))// checkbox
				return 1;
			if (str == null || "".equals(str.trim()))// support string trans
														// int;
				return 0;
			try {
				return Integer.valueOf(str);
			} catch (Exception e) {
				return 0;
			}
		}
	};
	public static final Converter LONG_CONVERTER = new Converter() {
		public Object stringToNumber(String str) {
			if ("on".equals(str))// checkbox
				return 1;
			if (str == null || "".equals(str.trim()))// support string trans
														// int;
				return 0;
			return Long.valueOf(str);
		}
	};
	public static final Converter DOUBLE_CONVERTER = new Converter() {
		public Object stringToNumber(String str) {
			java.text.NumberFormat numberFormat = new java.text.DecimalFormat(
					"0.00");
			if (str == null || "".equals(str.trim()))// support string trans
														// double;
				return 0;
			return Double.valueOf(numberFormat.format(Double.valueOf(str)));
		}
	};
	public static final Converter FLOAT_CONVERTER = new Converter() {
		public Object stringToNumber(String str) {
			java.text.NumberFormat numberFormat = new java.text.DecimalFormat(
					"0.00");
			if (str == null || "".equals(str.trim()))// support string trans
														// double;
				return 0;
			return Float.valueOf(numberFormat.format(Float.valueOf(str)));
		}
	};

	public void setAsText(String text) throws IllegalArgumentException {
		if (!StringUtils.hasText(text)) {
			if (getConverType() == 0) {
				setValue(0);
			} else {
				setValue(null);
			}
		} else {
			setValue(this.converter.stringToNumber(text));
		}
	}

	public String getAsText() {
		Object value = getValue();
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	private interface Converter {
		public Object stringToNumber(String str);
	}
}
