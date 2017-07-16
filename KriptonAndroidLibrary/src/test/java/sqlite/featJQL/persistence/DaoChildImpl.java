package sqlite.featJQL.persistence;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.android.sqlite.SqlUtils;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import sqlite.featJQL.entities.Child;

/**
 * <p>
 * DAO implementation for entity <code>Child</code>, based on interface <code>DaoChild</code>
 * </p>
 *
 *  @see Child
 *  @see DaoChild
 *  @see sqlite.featJQL.entities.ChildTable
 */
public class DaoChildImpl extends AbstractDao implements DaoChild {
  public DaoChildImpl(BindFamilyDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, name, parent_id FROM child</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectAll() {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT _id, name, parent_id FROM child");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();
    String _sqlWhereStatement="";

    // build where condition
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=cursor.getLong(index0);
          resultBean.name=cursor.getString(index1);
          if (!cursor.isNull(index2)) { resultBean.parentId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO child (name, parent_id) VALUES (${bean.name}, ${bean.parentId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>parent_id</dt><dd>is mapped to <strong>${bean.parentId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   *
   */
  @Override
  public Child insert(Child bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    contentValues.put("parent_id", bean.parentId);

    //StringUtils and SqlUtils will be used to format SQL
    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO child (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' of type %s", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("child", null, contentValues);
    bean.id=result;

    return bean;
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>select * from child where parent_id in (select _id from person where _id=${parentId})</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${parentId}</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>${parentId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectByParent(long parentId) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("select * from child ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" where parent_id in (select _id from person where _id=?)";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=cursor.getLong(index0);
          resultBean.name=cursor.getString(index1);
          if (!cursor.isNull(index2)) { resultBean.parentId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT _id, name, parent_id FROM child WHERE parent_id=${parentId}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>_id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>parent_id</dt><dd>is associated to bean's property <strong>parentId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${parentId}</dt><dd>is binded to method's parameter <strong>parentId</strong></dd>
   * </dl>
   *
   * @param parentId
   * 	is binded to <code>${parentId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<Child> selectByParentId(long parentId) {
    StringBuilder _sqlBuilder=new StringBuilder();
    _sqlBuilder.append("SELECT _id, name, parent_id FROM child ");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=new ArrayList<>();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE parent_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(parentId));
    //StringUtils, SqlUtils will be used in case of dynamic parts of SQL
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param (%s): '%s'",(_whereParamCounter++), _whereParamItem);
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<Child> resultList=new LinkedList<Child>();
      Child resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("_id");
        int index1=cursor.getColumnIndex("name");
        int index2=cursor.getColumnIndex("parent_id");

        do
         {
          resultBean=new Child();

          resultBean.id=cursor.getLong(index0);
          resultBean.name=cursor.getString(index1);
          if (!cursor.isNull(index2)) { resultBean.parentId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }
}
