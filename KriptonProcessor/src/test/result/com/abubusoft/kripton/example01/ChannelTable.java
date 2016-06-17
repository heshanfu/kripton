package com.abubusoft.kripton.example01;

import java.lang.String;

/**
 * Generated by Kripton Library.
 *
 *  @since Sat Jun 18 00:50:14 CEST 2016
 *
 */
public class ChannelTable {
  public static final String TABLE_NAME = "channel";

  public static final String CREATE_TABLE_SQL = "CREATE TABLE channel( uid TEXT, owner_uid TEXT, update_time INTEGER, name TEXT, id INTEGER PRIMARY KEY AUTOINCREMENT);";

  public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS channel;";

  public static final String COLUMN_UID = "uid";

  public static final String COLUMN_OWNER_UID = "owner_uid";

  public static final String COLUMN_UPDATE_TIME = "update_time";

  public static final String COLUMN_NAME = "name";

  public static final String COLUMN_ID = "id";
}
