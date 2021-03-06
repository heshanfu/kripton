/*******************************************************************************
 * Copyright 2015, 2017 Francesco Benincasa (info@abubusoft.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package sqlite.adapter.example01;

import java.util.Date;
import com.abubusoft.kripton.android.BindSQLTypeAdapter;

public class DateAdapter implements BindSQLTypeAdapter<Date, Long> {

	@Override
	public Date toJava(Long dataValue) {
		if (dataValue==null) return null;
		
		return new Date(dataValue);
	}

	@Override
	public Long toData(Date javaValue) {
		if (javaValue!=null) return javaValue.getTime();
		
		return null;
	}

	@Override
	public String toString(Date javaValue) {
		if (javaValue==null) return null;
		
		return String.valueOf(toData(javaValue));
	}
	


}
