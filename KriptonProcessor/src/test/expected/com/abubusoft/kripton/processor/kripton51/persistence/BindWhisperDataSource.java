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
package com.abubusoft.kripton.processor.kripton51.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource WhisperDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see WhisperDataSource
 * @see BindWhisperDaoFactory
 * @see DaoMessage
 * @see BindDaoMessage
 * @see com.abubusoft.kripton.processor.kripton51.entities.MessageEntity
 */
public class BindWhisperDataSource extends AbstractDataSource implements BindWhisperDaoFactory {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindWhisperDataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "whisper";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected BindDaoMessage daoMessage = new BindDaoMessage(this);

  protected BindWhisperDataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BindDaoMessage getDaoMessage() {
    return daoMessage;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
    SQLiteDatabase connection=openDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static BindWhisperDataSource instance() {
    if (instance==null) {
      instance=new BindWhisperDataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable.DROP_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable.CREATE_TABLE_SQL);
    database.execSQL(com.abubusoft.kripton.processor.kripton51.entities.MessageEntityTable.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindWhisperDaoFactory> {
  }
}
