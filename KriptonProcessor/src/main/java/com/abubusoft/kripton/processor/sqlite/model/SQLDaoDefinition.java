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
package com.abubusoft.kripton.processor.sqlite.model;

import java.lang.ref.WeakReference;

import javax.lang.model.element.TypeElement;

import com.abubusoft.kripton.common.Converter;
import com.abubusoft.kripton.processor.core.ModelBucket;

public class SQLDaoDefinition extends ModelBucket<SQLiteModelMethod, TypeElement> implements SQLiteModelElement {
	
	private WeakReference<SQLiteDatabaseSchema> parent;

	/**
	 * @return the parent
	 */
	public SQLiteDatabaseSchema getParent() {
		return parent.get();
	}

	private String entityClassName;

	private String entitySimplyClassName;

	/**
	 * @return the entitySimplyClassName
	 */
	public String getEntitySimplyClassName() {
		return entitySimplyClassName;
	}

	/**
	 * @return the entityClassName
	 */
	public String getEntityClassName() {
		return entityClassName;
	}

	public String getSimpleEntityClassName() {
		return entitySimplyClassName;
	}

	public SQLDaoDefinition(SQLiteDatabaseSchema databaseSchema, TypeElement element, String entityClassName) {
		super(element.getSimpleName().toString(), element);
		this.parent=new WeakReference<SQLiteDatabaseSchema>(databaseSchema);
		this.entityClassName = entityClassName;

		int i = 0;
		i = entityClassName.indexOf(".");

		if (i > 0) {
			entitySimplyClassName = entityClassName.substring(entityClassName.lastIndexOf(".")+1);
		} else {
			entitySimplyClassName = entityClassName;
		}
	}

	@Override
	public void accept(SQLiteModelElementVisitor visitor) throws Exception {
		visitor.visit(this);
	}

	public SQLEntity getEntity() {
		 return getParent().getEntity(getEntityClassName());
	}

	public Converter<String, String> getColumnNameConverter() {
		return getParent().columnNameConverter;
	}

	public Converter<String, String> getClassNameConverter() {
		return getParent().classNameConverter;
	}

	/**
	 * Return true if log must be generated.
	 * 
	 * @return
	 * 	Return true if log must be generated.
	 */
	public boolean isLogEnabled() {
		return getParent().generateLog;
	}

}
