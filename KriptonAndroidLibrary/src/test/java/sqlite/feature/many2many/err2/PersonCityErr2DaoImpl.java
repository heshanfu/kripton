package sqlite.feature.many2many.err2;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtils;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>PersonCityErr2</code>, based on interface <code>GeneratedPersonCityErr2Dao</code>
 * </p>
 *
 *  @see PersonCityErr2
 *  @see GeneratedPersonCityErr2Dao
 *  @see PersonCityErr2Table
 */
public class PersonCityErr2DaoImpl extends AbstractDao implements GeneratedPersonCityErr2Dao {
  public PersonCityErr2DaoImpl(BindPersonCirtyErr2DataSource dataSet) {
    super(dataSet);
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_id, city_id FROM person_city_err2 WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>city_id</dt><dd>is associated to bean's property <strong>cityId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to <code>${id}</code>
   * @return selected bean or <code>null</code>.
   */
  @Override
  public PersonCityErr2 selectById(long id) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, person_id, city_id FROM person_city_err2");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(id));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      PersonCityErr2 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("person_id");
        int index2=cursor.getColumnIndex("city_id");

        resultBean=new PersonCityErr2();

        resultBean.id=cursor.getLong(index0);
        if (!cursor.isNull(index1)) { resultBean.personId=cursor.getLong(index1); }
        if (!cursor.isNull(index2)) { resultBean.cityId=cursor.getLong(index2); }

      }
      return resultBean;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_id, city_id FROM person_city_err2 WHERE person_id=${personId}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>city_id</dt><dd>is associated to bean's property <strong>cityId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${personId}</dt><dd>is binded to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is binded to <code>${personId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PersonCityErr2> selectByPersonId(long personId) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, person_id, city_id FROM person_city_err2");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE person_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(personId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<PersonCityErr2> resultList=new LinkedList<PersonCityErr2>();
      PersonCityErr2 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("person_id");
        int index2=cursor.getColumnIndex("city_id");

        do
         {
          resultBean=new PersonCityErr2();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.personId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.cityId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>Select SQL:</h2>
   *
   * <pre>SELECT id, person_id, city_id FROM person_city_err2 WHERE city_id=${cityId}</pre>
   *
   * <h2>Projected columns:</h2>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>person_id</dt><dd>is associated to bean's property <strong>personId</strong></dd>
   * 	<dt>city_id</dt><dd>is associated to bean's property <strong>cityId</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <dl>
   * 	<dt>${cityId}</dt><dd>is binded to method's parameter <strong>cityId</strong></dd>
   * </dl>
   *
   * @param cityId
   * 	is binded to <code>${cityId}</code>
   * @return collection of bean or empty collection.
   */
  @Override
  public List<PersonCityErr2> selectByCityId(long cityId) {
    StringBuilder _sqlBuilder=getSQLStringBuilder();
    _sqlBuilder.append("SELECT id, person_id, city_id FROM person_city_err2");
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" WHERE city_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // build where condition
    _sqlWhereParams.add(String.valueOf(cityId));
    String _sql=_sqlBuilder.toString();
    String[] _sqlArgs=_sqlWhereParams.toArray(new String[_sqlWhereParams.size()]);
    Logger.info(_sql);

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    try (Cursor cursor = database().rawQuery(_sql, _sqlArgs)) {
      Logger.info("Rows found: %s",cursor.getCount());

      LinkedList<PersonCityErr2> resultList=new LinkedList<PersonCityErr2>();
      PersonCityErr2 resultBean=null;

      if (cursor.moveToFirst()) {

        int index0=cursor.getColumnIndex("id");
        int index1=cursor.getColumnIndex("person_id");
        int index2=cursor.getColumnIndex("city_id");

        do
         {
          resultBean=new PersonCityErr2();

          resultBean.id=cursor.getLong(index0);
          if (!cursor.isNull(index1)) { resultBean.personId=cursor.getLong(index1); }
          if (!cursor.isNull(index2)) { resultBean.cityId=cursor.getLong(index2); }

          resultList.add(resultBean);
        } while (cursor.moveToNext());
      }

      return resultList;
    }
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_city_err2 WHERE id=${id}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${id}</dt><dd>is mapped to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is used as where parameter <strong>${id}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteById(long id) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(id));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM person_city_err2 WHERE id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("person_city_err2", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_city_err2 WHERE person_id=${personId}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${personId}</dt><dd>is mapped to method's parameter <strong>personId</strong></dd>
   * </dl>
   *
   * @param personId
   * 	is used as where parameter <strong>${personId}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteByPersonId(long personId) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(personId));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" person_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM person_city_err2 WHERE person_id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("person_city_err2", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <h2>SQL delete</h2>
   * <pre>DELETE FROM person_city_err2 WHERE city_id=${cityId}</pre>
   *
   *
   * <h2>Where parameters:</h2>
   * <dl>
   * 	<dt>${cityId}</dt><dd>is mapped to method's parameter <strong>cityId</strong></dd>
   * </dl>
   *
   * @param cityId
   * 	is used as where parameter <strong>${cityId}</strong>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteByCityId(long cityId) {
    ArrayList<String> _sqlWhereParams=getWhereParamsArray();
    _sqlWhereParams.add(String.valueOf(cityId));

    StringBuilder _sqlBuilder=getSQLStringBuilder();
    // generation CODE_001 -- BEGIN
    // generation CODE_001 -- END

    // manage WHERE arguments -- BEGIN

    // manage WHERE statement
    String _sqlWhereStatement=" city_id=?";
    _sqlBuilder.append(_sqlWhereStatement);

    // manage WHERE arguments -- END

    // display log
    Logger.info("DELETE FROM person_city_err2 WHERE city_id=?");

    // log for where parameters -- BEGIN
    int _whereParamCounter=0;
    for (String _whereParamItem: _sqlWhereParams) {
      Logger.info("==> param%s: '%s'",(_whereParamCounter++), StringUtils.checkSize(_whereParamItem));
    }
    // log for where parameters -- END
    int result = database().delete("person_city_err2", _sqlWhereStatement, _sqlWhereParams.toArray(new String[_sqlWhereParams.size()]));
    return result;
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO person_city_err2 (person_id, city_id) VALUES (${personId}, ${cityId})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>person_id</dt><dd>is mapped to <strong>${bean.personId}</strong></dd>
   * 	<dt>city_id</dt><dd>is mapped to <strong>${bean.cityId}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public int insert(PersonCityErr2 bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    contentValues.put("person_id", bean.personId);
    contentValues.put("city_id", bean.cityId);

    // log for insert -- BEGIN 
    StringBuffer _columnNameBuffer=new StringBuffer();
    StringBuffer _columnValueBuffer=new StringBuffer();
    String _columnSeparator="";
    for (String columnName:contentValues.keySet()) {
      _columnNameBuffer.append(_columnSeparator+columnName);
      _columnValueBuffer.append(_columnSeparator+":"+columnName);
      _columnSeparator=", ";
    }
    Logger.info("INSERT INTO person_city_err2 (%s) VALUES (%s)", _columnNameBuffer.toString(), _columnValueBuffer.toString());

    // log for content values -- BEGIN
    Object _contentValue;
    for (String _contentKey:contentValues.keySet()) {
      _contentValue=contentValues.get(_contentKey);
      if (_contentValue==null) {
        Logger.info("==> :%s = <null>", _contentKey);
      } else {
        Logger.info("==> :%s = '%s' (%s)", _contentKey, StringUtils.checkSize(_contentValue), _contentValue.getClass().getCanonicalName());
      }
    }
    // log for content values -- END
    // log for insert -- END 

    long result = database().insert("person_city_err2", null, contentValues);
    bean.id=result;

    return (int)result;
  }
}
