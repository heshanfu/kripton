/*******************************************************************************
 * Copyright 2015, 2016 Francesco Benincasa (info@abubusoft.com).
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
package bind.feature.typeAdapter.kripton87;

import java.net.MalformedURLException;
import java.net.URL;

import com.abubusoft.kripton.BindTypeAdapter;

public class UrlByteArrayTypeAdapter implements BindTypeAdapter<URL, byte[]> {

	@Override
	public URL toJava(byte[] dataValue) {
		try {
			return new URL(new String(dataValue));
		} catch (MalformedURLException e) {
			throw(new RuntimeException(e));
		}
	}

	@Override
	public byte[] toData(URL javaValue) {
		return javaValue.toExternalForm().getBytes();
	}
}
