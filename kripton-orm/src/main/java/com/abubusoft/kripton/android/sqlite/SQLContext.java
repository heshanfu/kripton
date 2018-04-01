package com.abubusoft.kripton.android.sqlite;

import java.util.Set;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * SQL context for queries execution. There are two kind of SQLContext:
 * <ul>
 * <li>Standard context:</li>
 * <li></li>
 * </ul>
 * 
 * @author Francesco Benincasa (info@abubusoft.com)
 *
 */
public interface SQLContext {

	KriptonContentValues contentValuesForUpdate(SQLiteStatement compiledStatement);

	KriptonContentValues contentValues(SQLiteStatement compiledStatement);

	KriptonContentValues contentValuesForContentProvider(ContentValues values);

	/**
	 * StringBuilder used to generate SQL
	 * 
	 * @return
	 */
	StringBuilder sqlBuilder();

	/**
	 * Return true if log is enabled
	 * 
	 * @return
	 */
	boolean isLogEnabled();

	/**
	 * Get SQLite database
	 * 
	 * @return
	 */
	SQLiteDatabase database();

	/**
	 * Fired when transaction or shared connection is opened
	 */
	void onSessionOpened();

	/**
	 * In the standar SQLContext you can not use session (transaction and shared
	 * connection), but this is allow to support LiveData.
	 * 
	 * @return
	 */
	boolean isInSession();

	/**
	 * Registry an SQL event for a DAO
	 * 
	 * @param daoKey
	 */
	void registrySQLEvent(int daoKey);

	/**
	 * Fired when transaction or shared connection is closed
	 * 
	 * @return
	 * 		all daoKey that registry an event
	 */
	Set<Integer> onSessionClosed();
}