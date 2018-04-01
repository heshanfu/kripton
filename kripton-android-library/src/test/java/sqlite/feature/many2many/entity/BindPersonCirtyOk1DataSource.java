package sqlite.feature.many2many.entity;

import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import com.abubusoft.kripton.android.sqlite.DataSourceOptions;
import com.abubusoft.kripton.android.sqlite.SQLContextInSessionImpl;
import com.abubusoft.kripton.android.sqlite.SQLiteTable;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTask;
import com.abubusoft.kripton.android.sqlite.SQLiteUpdateTaskHelper;
import com.abubusoft.kripton.android.sqlite.TransactionResult;
import com.abubusoft.kripton.exception.KriptonRuntimeException;
import java.util.List;
import sqlite.feature.many2many.CityTable;
import sqlite.feature.many2many.PersonTable;

/**
 * <p>
 * Represents implementation of datasource PersonCirtyOk1DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see PersonCirtyOk1DataSource
 * @see BindPersonCirtyOk1DaoFactory
 * @see PersonOk1Dao
 * @see PersonOk1DaoImpl
 * @see Person
 * @see CityOk1Dao
 * @see CityOk1DaoImpl
 * @see City
 * @see PersonCityOk1Dao
 * @see PersonCityOk1DaoImpl
 * @see PersonCityOk1
 */
public class BindPersonCirtyOk1DataSource extends AbstractDataSource implements BindPersonCirtyOk1DaoFactory, PersonCirtyOk1DataSource {
  /**
   * <p>datasource singleton</p>
   */
  static volatile BindPersonCirtyOk1DataSource instance;

  /**
   * <p>Mutex to manage multithread access to instance</p>
   */
  private static final Object mutex = new Object();

  /**
   * Unique identifier for Dao PersonOk1Dao
   */
  public static final int PERSON_OK1_DAO_UID = 0;

  /**
   * Unique identifier for Dao CityOk1Dao
   */
  public static final int CITY_OK1_DAO_UID = 1;

  /**
   * Unique identifier for Dao PersonCityOk1Dao
   */
  public static final int PERSON_CITY_OK1_DAO_UID = 2;

  /**
   * List of tables compose datasource
   */
  static final SQLiteTable[] TABLES = {new PersonCityOk1Table(), new CityTable(), new PersonTable()};

  /**
   * <p>dao instance</p>
   */
  protected PersonOk1DaoImpl personOk1Dao = new PersonOk1DaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected CityOk1DaoImpl cityOk1Dao = new CityOk1DaoImpl(context);

  /**
   * <p>dao instance</p>
   */
  protected PersonCityOk1DaoImpl personCityOk1Dao = new PersonCityOk1DaoImpl(context);

  /**
   * Used only in transactions (that can be executed one for time
   */
  protected DataSourceSingleThread _daoFactorySingleThread = new DataSourceSingleThread();

  protected BindPersonCirtyOk1DataSource(DataSourceOptions options) {
    super("person.db", 1, options);
  }

  @Override
  public PersonOk1DaoImpl getPersonOk1Dao() {
    return personOk1Dao;
  }

  @Override
  public CityOk1DaoImpl getCityOk1Dao() {
    return cityOk1Dao;
  }

  @Override
  public PersonCityOk1DaoImpl getPersonCityOk1Dao() {
    return personCityOk1Dao;
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode. This method uses default error listener to intercept errors.</p>
   *
   * @param transaction
   * 	transaction to execute
   */
  public void execute(Transaction transaction) {
    execute(transaction, onErrorListener);
  }

  /**
   * <p>Executes a transaction. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. The database will be open in write mode.</p>
   *
   * @param transaction
   * 	transaction to execute
   * @param onErrorListener
   * 	error listener
   */
  public void execute(Transaction transaction, AbstractDataSource.OnErrorListener onErrorListener) {
    boolean needToOpened=!this.isOpenInWriteMode();
    boolean success=false;
    @SuppressWarnings("resource")
    SQLiteDatabase connection=needToOpened ? openWritableDatabase() : database();
    DataSourceSingleThread currentDaoFactory=_daoFactorySingleThread.bindToThread();
    currentDaoFactory.onSessionOpened();
    try {
      connection.beginTransaction();
      if (transaction!=null && TransactionResult.COMMIT == transaction.onExecute(currentDaoFactory)) {
        connection.setTransactionSuccessful();
        success=true;
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      if (onErrorListener!=null) onErrorListener.onError(e);
    } finally {
      try {
        connection.endTransaction();
      } catch (Throwable e) {
        Logger.warn("error closing transaction %s", e.getMessage());
      }
      if (needToOpened) { close(); }
      if (success) { currentDaoFactory.onSessionClosed(); } else { currentDaoFactory.onSessionClear(); }
    }
  }

  /**
   * <p>Executes a batch opening a read only connection. This method <strong>is thread safe</strong> to avoid concurrent problems.</p>
   *
   * @param commands
   * 	batch to execute
   */
  public <T> T executeBatch(Batch<T> commands) {
    return executeBatch(commands, false);
  }

  /**
   * <p>Executes a batch. This method <strong>is thread safe</strong> to avoid concurrent problems. The drawback is only one transaction at time can be executed. if <code>writeMode</code> is set to false, multiple batch operations is allowed.</p>
   *
   * @param commands
   * 	batch to execute
   * @param writeMode
   * 	true to open connection in write mode, false to open connection in read only mode
   */
  public <T> T executeBatch(Batch<T> commands, boolean writeMode) {
    boolean needToOpened=writeMode?!this.isOpenInWriteMode(): !this.isOpen();
    if (needToOpened) { if (writeMode) { openWritableDatabase(); } else { openReadOnlyDatabase(); }}
    DataSourceSingleThread currentDaoFactory=new DataSourceSingleThread();
    currentDaoFactory.onSessionOpened();
    try {
      if (commands!=null) {
        return commands.onExecute(currentDaoFactory);
      }
    } catch(Throwable e) {
      Logger.error(e.getMessage());
      e.printStackTrace();
      throw(e);
    } finally {
      if (needToOpened) { close(); }
      currentDaoFactory.onSessionClosed();
    }
    return null;
  }

  /**
   * <p>Retrieve instance.</p>
   */
  public static BindPersonCirtyOk1DataSource instance() {
    BindPersonCirtyOk1DataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          DataSourceOptions options=DataSourceOptions.builder()
          	.inMemory(false)
          	.log(true)
          	.build();
          instance=result=new BindPersonCirtyOk1DataSource(options);
          SQLiteDatabase database=instance.openWritableDatabase();
          try {
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          } finally {
            instance.close();
          }
        }
      }
    }
    return result;
  }

  /**
   * Retrieve data source instance and open it.
   * @return opened dataSource instance.
   */
  public static BindPersonCirtyOk1DataSource open() {
    BindPersonCirtyOk1DataSource instance=instance();
    instance.openWritableDatabase();
    return instance;
  }

  /**
   * Retrieve data source instance and open it in read only mode.
   * @return opened dataSource instance.
   */
  public static BindPersonCirtyOk1DataSource openReadOnly() {
    BindPersonCirtyOk1DataSource instance=instance();
    instance.openReadOnlyDatabase();
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    // log section BEGIN
    if (this.logEnabled) {
      if (options.inMemory) {
        Logger.info("Create database in memory");
      } else {
        Logger.info("Create database '%s' version %s",this.name, this.version);
      }
    }
    // log section END
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PersonTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",CityTable.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(CityTable.CREATE_TABLE_SQL);
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("DDL: %s",PersonCityOk1Table.CREATE_TABLE_SQL);
    }
    // log section END
    database.execSQL(PersonCityOk1Table.CREATE_TABLE_SQL);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onCreate(database);
    }
    justCreated=true;
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int previousVersion, int currentVersion) {
    // log section BEGIN
    if (this.logEnabled) {
      Logger.info("Update database '%s' from version %s to version %s",this.name, previousVersion, currentVersion);
    }
    // log section END
    // if we have a list of update task, try to execute them
    if (options.updateTasks != null) {
      List<SQLiteUpdateTask> tasks = buildTaskList(previousVersion, currentVersion);
      for (SQLiteUpdateTask task : tasks) {
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("Begin update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        task.execute(database, previousVersion, previousVersion+1);
        // log section BEGIN
        if (this.logEnabled) {
          Logger.info("End update database from version %s to %s", previousVersion, previousVersion+1);
        }
        // log section END
        previousVersion++;
      }
    } else {
      // drop all tables
      SQLiteUpdateTaskHelper.dropTablesAndIndices(database);

      // generate tables
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PersonTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PersonTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",CityTable.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(CityTable.CREATE_TABLE_SQL);
      // log section BEGIN
      if (this.logEnabled) {
        Logger.info("DDL: %s",PersonCityOk1Table.CREATE_TABLE_SQL);
      }
      // log section END
      database.execSQL(PersonCityOk1Table.CREATE_TABLE_SQL);
    }
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onUpdate(database, previousVersion, currentVersion, true);
    }
  }

  /**
   * onConfigure
   */
  @Override
  public void onConfigure(SQLiteDatabase database) {
    // configure database
    database.setForeignKeyConstraintsEnabled(true);
    if (options.databaseLifecycleHandler != null) {
      options.databaseLifecycleHandler.onConfigure(database);
    }
  }

  public void clearCompiledStatements() {
    PersonOk1DaoImpl.clearCompiledStatements();
    CityOk1DaoImpl.clearCompiledStatements();
    PersonCityOk1DaoImpl.clearCompiledStatements();
  }

  /**
   * <p>Build instance. This method can be used only one time, on the application start.</p>
   */
  public static BindPersonCirtyOk1DataSource build(DataSourceOptions options) {
    BindPersonCirtyOk1DataSource result=instance;
    if (result==null) {
      synchronized(mutex) {
        result=instance;
        if (result==null) {
          instance=result=new BindPersonCirtyOk1DataSource(options);
          SQLiteDatabase database=instance.openWritableDatabase();
          try {
          } catch(Throwable e) {
            Logger.error(e.getMessage());
            e.printStackTrace();
          } finally {
            instance.close();
          }
        } else {
          throw new KriptonRuntimeException("Datasource BindPersonCirtyOk1DataSource is already builded");
        }
      }
    } else {
      throw new KriptonRuntimeException("Datasource BindPersonCirtyOk1DataSource is already builded");
    }
    return result;
  }

  /**
   * List of tables compose datasource:
   */
  public static SQLiteTable[] tables() {
    return TABLES;
  }

  /**
   * Rapresents transational operation.
   */
  public interface Transaction extends AbstractDataSource.AbstractExecutable<BindPersonCirtyOk1DaoFactory> {
    /**
     * Execute transation. Method need to return {@link TransactionResult#COMMIT} to commit results
     * or {@link TransactionResult#ROLLBACK} to rollback.
     * If exception is thrown, a rollback will be done.
     *
     * @param daoFactory
     * @return
     * @throws Throwable
     */
    TransactionResult onExecute(BindPersonCirtyOk1DaoFactory daoFactory);
  }

  /**
   * Rapresents batch operation.
   */
  public interface Batch<T> {
    /**
     * Execute batch operations.
     *
     * @param daoFactory
     * @throws Throwable
     */
    T onExecute(BindPersonCirtyOk1DaoFactory daoFactory);
  }

  class DataSourceSingleThread implements BindPersonCirtyOk1DaoFactory {
    private SQLContextInSessionImpl _context;

    protected PersonOk1DaoImpl _personOk1Dao;

    protected CityOk1DaoImpl _cityOk1Dao;

    protected PersonCityOk1DaoImpl _personCityOk1Dao;

    DataSourceSingleThread() {
      _context=new SQLContextInSessionImpl(BindPersonCirtyOk1DataSource.this);
    }

    /**
     *
     * retrieve dao PersonOk1Dao
     */
    public PersonOk1DaoImpl getPersonOk1Dao() {
      if (_personOk1Dao==null) {
        _personOk1Dao=new PersonOk1DaoImpl(_context);
      }
      return _personOk1Dao;
    }

    /**
     *
     * retrieve dao CityOk1Dao
     */
    public CityOk1DaoImpl getCityOk1Dao() {
      if (_cityOk1Dao==null) {
        _cityOk1Dao=new CityOk1DaoImpl(_context);
      }
      return _cityOk1Dao;
    }

    /**
     *
     * retrieve dao PersonCityOk1Dao
     */
    public PersonCityOk1DaoImpl getPersonCityOk1Dao() {
      if (_personCityOk1Dao==null) {
        _personCityOk1Dao=new PersonCityOk1DaoImpl(_context);
      }
      return _personCityOk1Dao;
    }

    protected void onSessionOpened() {
    }

    protected void onSessionClear() {
    }

    protected void onSessionClosed() {
    }

    public DataSourceSingleThread bindToThread() {
      return this;
    }
  }
}