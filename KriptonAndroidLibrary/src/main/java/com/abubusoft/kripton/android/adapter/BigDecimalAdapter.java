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
package com.abubusoft.kripton.android.adapter;

import java.math.BigDecimal;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Transformer between a string and a java.math.BigDecimal object
 * 
 * @author bulldog
 *
 */
class BigDecimalAdapter implements SqliteAdapter<BigDecimal> {

	@Override
	public BigDecimal readCursor(Cursor cursor, int columnIndex) throws Exception {
		String value=cursor.getString(columnIndex);
		if (value==null) return null;
		
		return (new BigDecimal(value));
	}

	@Override
	public void writeValue(BigDecimal value, ContentValues content, String columnKey) throws Exception {
		content.put(columnKey, value.toString());
	}

}
