/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.abubusoft.kripton.processor.sqlite.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.setter;

import java.util.Locale;
import java.util.regex.Pattern;

import com.abubusoft.kripton.processor.core.ModelProperty;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.MethodSpec.Builder;

/**
 * Transformer between a string and a java.util.Locale object
 * 
 * @author bulldog
 *
 */
public class LocaleTransform  extends AbstractCompileTimeTransform {

	private final Pattern pattern;

	public LocaleTransform() {
		this.pattern = Pattern.compile("_");
	}

	public Locale read(String locale) throws Exception {
		String[] list = pattern.split(locale);

		if (list.length < 1) {
			throw new IllegalArgumentException("Invalid locale " + locale);
		}
		return read(list);
	}

	private Locale read(String[] locale) throws Exception {
		String[] list = new String[] { "", "", "" };

		for (int i = 0; i < list.length; i++) {
			if (i < locale.length) {
				list[i] = locale[i];
			}
		}
		return new Locale(list[0], list[1], list[2]);
	}

	public String write(Locale locale) {
		return locale.toString();
	}

	@Override
	public void generateReadProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property, String cursorName, String indexName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateResetProperty(Builder methodBuilder, TypeName beanClass, String beanName, ModelProperty property,  String cursorName, String indexName) {
		methodBuilder.addCode("$L." + setter(beanClass, property, "null"), beanName);
	}

	@Override
	public void generateDefaultValue(Builder methodBuilder) {
		methodBuilder.addCode("null");
	}

	@Override
	public void generateRead(Builder methodBuilder, String cursorName, String indexName) {
		methodBuilder.addCode("$L.getString($L)", cursorName, indexName);
	}

	@Override
	public String generateColumnType(ModelProperty property) {
		return "TEXT";
	}

}
