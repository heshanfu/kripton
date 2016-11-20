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
package com.abubusoft.kripton.processor.bind.transform;

import static com.abubusoft.kripton.processor.core.reflect.PropertyUtility.getter;

import com.abubusoft.kripton.processor.bind.model.BindProperty;
import com.squareup.javapoet.MethodSpec.Builder;
import com.squareup.javapoet.TypeName;

/**
 * Transformer between a string and a java.math.BigDecimal object
 * 
 * @author bulldog
 *
 */
class BigDecimalTransform extends AbstractBindTransform {
	
	public BigDecimalTransform()
	{
		this.nullable=true;
		defaultValue="0";
	}
	
	protected boolean nullable;
	
	protected String defaultValue;
	
	@Override
	public void generateParseOnXml(Builder methodBuilder, String parserName, TypeName beanClass, String beanName, BindProperty property) {
		/*
		if (add) {
						
			methodBuilder.addCode("$L." + setter(beanClass, property) + (property.isFieldWithSetter()?"(":"=")+"", beanName);
		}
		
		methodBuilder.addCode("($L.getString($S, null)!=null) ? ", preferenceName, property.getName());
		methodBuilder.addCode("new $T($L.getString($S, $S))",  BigDecimal.class, preferenceName, property.getName(), defaultValue);
		methodBuilder.addCode(": null");
		
		if (add) {
			methodBuilder.addCode((property.isFieldWithSetter()?")":""));
		}*/
	}


	@Override
	public void generateSerializeOnXml(Builder methodBuilder, String editorName, TypeName beanClass, String beanName, BindProperty property) {
		if (beanClass!=null)
		{
			if (nullable)
			{
				methodBuilder.addCode("if ($L." + getter(beanClass, property)+"!=null) ", beanName);
			}
			methodBuilder.addCode("$L.putString($S,$L." + getter(beanClass, property) + ".toPlainString() )", editorName, property.getName(), beanName);
			if (nullable)
			{
				methodBuilder.addCode(";");
				methodBuilder.addCode(" else ");
				methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
			}
		} else {
			if (nullable)
			{
				methodBuilder.addCode("if ($L!=null) ", beanName);
			}
			methodBuilder.addCode("$L.putString($S,$L.toPlainString())", editorName, property.getName(), beanName);
			if (nullable)
			{
				methodBuilder.addCode(";");
				methodBuilder.addCode(" else ");
				methodBuilder.addCode("$L.putString($S, null)", editorName, property.getName());
			}
		}
			
	}


	@Override
	public void generateSerializeOnJackson(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void generateSerializeOnJacksonAsString(Builder methodBuilder, String serializerName, TypeName beanClass, String beanName, BindProperty property) {
		// TODO Auto-generated method stub
		
	}

}