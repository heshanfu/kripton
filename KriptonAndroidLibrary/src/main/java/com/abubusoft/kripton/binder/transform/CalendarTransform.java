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
package com.abubusoft.kripton.binder.transform;

import java.util.Calendar;
import java.util.Date;

/**
 * Transformer between a string and a java.util.Calendar object
 * 
 * @author bulldog
 *
 */
public class CalendarTransform implements Transform<Calendar> {
	
	private DateTransform dateTransform = new DateTransform();

	public Calendar read(String value) throws Exception {
		Date date = dateTransform.read(value);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public String write(Calendar value) throws Exception {
		Date date = value.getTime();
		String text = dateTransform.write(date);
		return text;
	}

}
