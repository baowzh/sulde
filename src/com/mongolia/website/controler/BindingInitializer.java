package com.mongolia.website.controler;

import java.beans.PropertyEditor;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomBooleanEditor;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;


public class BindingInitializer implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// TODO Auto-generated method stub
		PropertyEditor booleanEditor = new CustomBooleanEditor(true);
		binder.registerCustomEditor(boolean.class, booleanEditor);
		binder.registerCustomEditor(Boolean.class, booleanEditor);
		PropertyEditor byteEditor = new NullableDecimalPropertyEditor(
				NullableDecimalPropertyEditor.BYTE_CONVERTER);
		PropertyEditor shortEditor = new NullableDecimalPropertyEditor(
				NullableDecimalPropertyEditor.SHORT_CONVERTER,
				NullableDecimalPropertyEditor.NUMBER);
		PropertyEditor integerEditor = new NullableDecimalPropertyEditor(
				NullableDecimalPropertyEditor.INTEGER_CONVERTER,
				NullableDecimalPropertyEditor.NUMBER);
		PropertyEditor longEditor = new NullableDecimalPropertyEditor(
				NullableDecimalPropertyEditor.LONG_CONVERTER,
				NullableDecimalPropertyEditor.NUMBER);
//		PropertyEditor dateEditor = new NullableDecimalPropertyEditor(
//				NullableDecimalPropertyEditor.DATE_CONVERTER);
		PropertyEditor floatEditor = new NullableDecimalPropertyEditor(
				NullableDecimalPropertyEditor.FLOAT_CONVERTER,
				NullableDecimalPropertyEditor.NUMBER);
		PropertyEditor doubleEditor = new NullableDecimalPropertyEditor(
				NullableDecimalPropertyEditor.DOUBLE_CONVERTER,
				NullableDecimalPropertyEditor.NUMBER);

		//binder.registerCustomEditor(Date.class, dateEditor);
		binder.registerCustomEditor(byte.class, byteEditor);
		binder.registerCustomEditor(Byte.class, byteEditor);

		binder.registerCustomEditor(short.class, shortEditor);
		binder.registerCustomEditor(Short.class, shortEditor);

		binder.registerCustomEditor(int.class, integerEditor);
		binder.registerCustomEditor(Integer.class, integerEditor);

		binder.registerCustomEditor(long.class, longEditor);
		binder.registerCustomEditor(Long.class, longEditor);

		binder.registerCustomEditor(float.class, floatEditor);
		binder.registerCustomEditor(Float.class, floatEditor);

		binder.registerCustomEditor(double.class, doubleEditor);
		binder.registerCustomEditor(Double.class, doubleEditor);

		binder.registerCustomEditor(BigDecimal.class, new CustomNumberEditor(
				BigDecimal.class, true));
		binder.registerCustomEditor(BigInteger.class, new CustomNumberEditor(
				BigInteger.class, true));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));

	}

}
