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
package com.abubusoft.kripton.processor.kripton48.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>Bean02</code> is associated to table <code>bean02</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton48.entities.Bean02
 */
public class Bean02Table {
  /**
   * Costant represents name of table bean02
   */
  public static final String TABLE_NAME = "bean02";

  /**
   * <p>
   * DDL to create table bean02
   * </p>
   *
   * <pre>CREATE TABLE bean02 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE bean02 (id INTEGER PRIMARY KEY AUTOINCREMENT, text TEXT);";

  /**
   * <p>
   * DDL to drop table bean02
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS bean02;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS bean02;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton48.entities.Bean02#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton48.entities.Bean02#text
   */
  public static final String COLUMN_TEXT = "text";
}
