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
package com.abubusoft.kripton.processor.kripton51.entities;

import java.lang.String;

/**
 * <p>
 * Entity <code>MessageEntity</code> is associated to table <code>message</code>
 * This class represents table associated to entity.
 * </p>
 *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity
 */
public class MessageEntityTable {
  /**
   * Costant represents name of table message
   */
  public static final String TABLE_NAME = "message";

  /**
   * <p>
   * DDL to create table message
   * </p>
   *
   * <pre>CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT, channel_id INTEGER, owner_type TEXT, face_uid TEXT, text TEXT, owner_uid TEXT, channel_uid TEXT, update_time INTEGER, type TEXT);</pre>
   */
  public static final String CREATE_TABLE_SQL = "CREATE TABLE message (id INTEGER PRIMARY KEY AUTOINCREMENT, channel_id INTEGER, owner_type TEXT, face_uid TEXT, text TEXT, owner_uid TEXT, channel_uid TEXT, update_time INTEGER, type TEXT);";

  /**
   * <p>
   * DDL to drop table message
   * </p>
   *
   * <pre>DROP TABLE IF EXISTS message;</pre>
   */
  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS message;";

  /**
   * Entity's property <code>id</code> is associated to table column <code>id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#id
   */
  public static final String COLUMN_ID = "id";

  /**
   * Entity's property <code>channelId</code> is associated to table column <code>channel_id</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#channelId
   */
  public static final String COLUMN_CHANNEL_ID = "channel_id";

  /**
   * Entity's property <code>ownerType</code> is associated to table column <code>owner_type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#ownerType
   */
  public static final String COLUMN_OWNER_TYPE = "owner_type";

  /**
   * Entity's property <code>faceUid</code> is associated to table column <code>face_uid</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#faceUid
   */
  public static final String COLUMN_FACE_UID = "face_uid";

  /**
   * Entity's property <code>text</code> is associated to table column <code>text</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#text
   */
  public static final String COLUMN_TEXT = "text";

  /**
   * Entity's property <code>ownerUid</code> is associated to table column <code>owner_uid</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#ownerUid
   */
  public static final String COLUMN_OWNER_UID = "owner_uid";

  /**
   * Entity's property <code>channelUid</code> is associated to table column <code>channel_uid</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#channelUid
   */
  public static final String COLUMN_CHANNEL_UID = "channel_uid";

  /**
   * Entity's property <code>updateTime</code> is associated to table column <code>update_time</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#updateTime
   */
  public static final String COLUMN_UPDATE_TIME = "update_time";

  /**
   * Entity's property <code>type</code> is associated to table column <code>type</code>. This costant represents column name.
   *
   *  @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity#type
   */
  public static final String COLUMN_TYPE = "type";
}
