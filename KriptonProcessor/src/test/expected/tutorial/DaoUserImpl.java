package tutorial;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>User</code>, based on interface <code>DaoUser</code>
 * </p>
 *
 *  @see User
 *  @see DaoUser
 *  @see UserTable
 */
public class DaoUserImpl extends AbstractDao implements DaoUser {
  public DaoUserImpl(BindSecurityDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>SQL insert:</p>
   * <pre>INSERT INTO user (email, name, surname, username) VALUES (${bean.email}, ${bean.name}, ${bean.surname}, ${bean.username})</pre>
   *
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * <p><strong>Inserted columns:</strong></p>
   * <dl>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>username</dt><dd>is mapped to <strong>${bean.username}</strong></dd>
   * </dl>
   *
   * @param bean
   * 	is mapped to parameter <strong>bean</strong>
   *
   * @return <strong>id</strong> of inserted record
   */
  @Override
  public long insert(User bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.email!=null) {
      contentValues.put("email", bean.email);
    } else {
      contentValues.putNull("email");
    }

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.surname!=null) {
      contentValues.put("surname", bean.surname);
    } else {
      contentValues.putNull("surname");
    }

    if (bean.username!=null) {
      contentValues.put("username", bean.username);
    } else {
      contentValues.putNull("username");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO user (email, name, surname, username) VALUES ('"+StringUtil.checkSize(contentValues.get("email"))+"', '"+StringUtil.checkSize(contentValues.get("name"))+"', '"+StringUtil.checkSize(contentValues.get("surname"))+"', '"+StringUtil.checkSize(contentValues.get("username"))+"')"));
    long result = database().insert("user", null, contentValues);
    bean.id=result;

    return result;
  }

  /**
   * <p>SQL Update:</p>
   * <pre>UPDATE user SET email=${bean.email}, name=${bean.name}, surname=${bean.surname}, username=${bean.username} WHERE 1=1</pre>
   *
   * <p><strong>Updated columns:</strong></p>
   * <dl>
   * 	<dt>email</dt><dd>is mapped to <strong>${bean.email}</strong></dd>
   * 	<dt>name</dt><dd>is mapped to <strong>${bean.name}</strong></dd>
   * 	<dt>surname</dt><dd>is mapped to <strong>${bean.surname}</strong></dd>
   * 	<dt>username</dt><dd>is mapped to <strong>${bean.username}</strong></dd>
   * </dl>
   * @param bean
   * 	is used as ${bean}
   *
   * @return number of updated records
   */
  @Override
  public long update(User bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.email!=null) {
      contentValues.put("email", bean.email);
    } else {
      contentValues.putNull("email");
    }

    if (bean.name!=null) {
      contentValues.put("name", bean.name);
    } else {
      contentValues.putNull("name");
    }

    if (bean.surname!=null) {
      contentValues.put("surname", bean.surname);
    } else {
      contentValues.putNull("surname");
    }

    if (bean.username!=null) {
      contentValues.put("username", bean.username);
    } else {
      contentValues.putNull("username");
    }

    String[] whereConditions={};

    Logger.info(StringUtil.formatSQL("UPDATE user SET email='"+StringUtil.checkSize(contentValues.get("email"))+"', name='"+StringUtil.checkSize(contentValues.get("name"))+"', surname='"+StringUtil.checkSize(contentValues.get("surname"))+"', username='"+StringUtil.checkSize(contentValues.get("username"))+"' WHERE 1=1"), (Object[])whereConditions);
    int result = database().update("user", contentValues, "1=1", whereConditions);
    return result;
  }

  /**
   * <p>SQL delete:</p>
   * <pre>DELETE user WHERE id=${id}</pre>
   *
   * <p><strong>Where parameters:</strong></p>
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
  public long delete(long id) {
    String[] whereConditions={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("DELETE user WHERE id=%s"), (Object[])whereConditions);
    int result = database().delete("user", "id=?", whereConditions);
    return result;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, email, name, surname, username FROM user WHERE id=${id}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${id}</dt><dd>is binded to method's parameter <strong>id</strong></dd>
   * </dl>
   *
   * @param id
   * 	is binded to ${id}
   *
   * @return selected bean or <code>null</code>.
   */
  @Override
  public User selectById(long id) {
    // build where condition
    String[] args={String.valueOf(id)};

    Logger.info(StringUtil.formatSQL("SELECT id, email, name, surname, username FROM user WHERE id='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, email, name, surname, username FROM user WHERE id=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    User resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("email");
      int index2=cursor.getColumnIndex("name");
      int index3=cursor.getColumnIndex("surname");
      int index4=cursor.getColumnIndex("username");

      resultBean=new User();

      if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
      if (!cursor.isNull(index1)) { resultBean.email=cursor.getString(index1); }
      if (!cursor.isNull(index2)) { resultBean.name=cursor.getString(index2); }
      if (!cursor.isNull(index3)) { resultBean.surname=cursor.getString(index3); }
      if (!cursor.isNull(index4)) { resultBean.username=cursor.getString(index4); }

    }
    cursor.close();

    return resultBean;
  }

  /**
   * <h2>Select SQL:</h2>
   * <p>
   * <pre>SELECT id, email, name, surname, username FROM user WHERE name=${name}</pre>
   *
   * <h2>Projected columns:</h2>
   * <p>
   * <dl>
   * 	<dt>id</dt><dd>is associated to bean's property <strong>id</strong></dd>
   * 	<dt>email</dt><dd>is associated to bean's property <strong>email</strong></dd>
   * 	<dt>name</dt><dd>is associated to bean's property <strong>name</strong></dd>
   * 	<dt>surname</dt><dd>is associated to bean's property <strong>surname</strong></dd>
   * 	<dt>username</dt><dd>is associated to bean's property <strong>username</strong></dd>
   * </dl>
   *
   * <h2>Query's parameters:</h2>
   * <p>
   * <dl>
   * 	<dt>${name}</dt><dd>is binded to method's parameter <strong>name</strong></dd>
   * </dl>
   *
   * @param name
   * 	is binded to ${name}
   *
   * @return collection of bean or empty collection.
   */
  @Override
  public List<User> selectById(String name) {
    // build where condition
    String[] args={(name==null?null:name)};

    Logger.info(StringUtil.formatSQL("SELECT id, email, name, surname, username FROM user WHERE name='%s'"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, email, name, surname, username FROM user WHERE name=?", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<User> resultList=new LinkedList<User>();
    User resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("email");
      int index2=cursor.getColumnIndex("name");
      int index3=cursor.getColumnIndex("surname");
      int index4=cursor.getColumnIndex("username");

      do
       {
        resultBean=new User();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.email=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.name=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.surname=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.username=cursor.getString(index4); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }
}