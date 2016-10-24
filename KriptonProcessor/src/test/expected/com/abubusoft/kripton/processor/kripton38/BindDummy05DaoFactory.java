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
package com.abubusoft.kripton.processor.kripton38;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy05DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy05DataSource
 * @see DaoBean05
 * @see BindDaoBean05
 * @see Bean05
 */
public interface BindDummy05DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean05
   */
  BindDaoBean05 getDaoBean05();
}
