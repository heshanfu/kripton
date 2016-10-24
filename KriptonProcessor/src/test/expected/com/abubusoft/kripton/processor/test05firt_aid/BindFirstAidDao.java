package com.abubusoft.kripton.processor.test05firt_aid;

import android.content.ContentValues;
import android.database.Cursor;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDao;
import com.abubusoft.kripton.common.StringUtil;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * DAO implementation for entity <code>FirstAid</code>, based on interface <code>FirstAidDao</code>
 * </p>
 *  @see com.abubusoft.kripton.processor.test05firt_aid.FirstAid
 *  @see com.abubusoft.kripton.processor.test05firt_aid.FirstAidDao
 *  @see com.abubusoft.kripton.processor.test05firt_aid.FirstAidTable
 */
public class BindFirstAidDao extends AbstractDao implements FirstAidDao {
  public BindFirstAidDao(BindFirstAidDataSource dataSet) {
    super(dataSet);
  }

  /**
   * <p>Select query is:</p>
   * <pre>SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid WHERE 1=1 ORDER BY name</pre>
   *
   * <p>Its parameters are:</p>
   *
   * <pre>[]</pre>
   *
   * <p>Projected column are:</p>
   *
   * <pre>[id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time]</pre>
   *
   *
   * @return list of bean or empty list.
   */
  @Override
  public List<FirstAid> selectAll() {
    // build where condition
    String[] args={};

    Logger.info(StringUtil.formatSQL("SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid WHERE 1=1 ORDER BY name"),(Object[])args);
    Cursor cursor = database().rawQuery("SELECT id, uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time FROM first_aid WHERE 1=1 ORDER BY name", args);
    Logger.info("Rows found: %s",cursor.getCount());

    LinkedList<FirstAid> resultList=new LinkedList<FirstAid>();
    FirstAid resultBean=null;

    if (cursor.moveToFirst()) {

      int index0=cursor.getColumnIndex("id");
      int index1=cursor.getColumnIndex("uid");
      int index2=cursor.getColumnIndex("description");
      int index3=cursor.getColumnIndex("info");
      int index4=cursor.getColumnIndex("longitude");
      int index5=cursor.getColumnIndex("latitude");
      int index6=cursor.getColumnIndex("address");
      int index7=cursor.getColumnIndex("address2");
      int index8=cursor.getColumnIndex("city");
      int index9=cursor.getColumnIndex("phone");
      int index10=cursor.getColumnIndex("total_patient_count");
      int index11=cursor.getColumnIndex("white_waiting_patients");
      int index12=cursor.getColumnIndex("white_visiting_patients");
      int index13=cursor.getColumnIndex("white_average_waiting_time");
      int index14=cursor.getColumnIndex("green_waiting_patients");
      int index15=cursor.getColumnIndex("green_visiting_patients");
      int index16=cursor.getColumnIndex("green_average_waiting_time");
      int index17=cursor.getColumnIndex("yellow_waiting_patients");
      int index18=cursor.getColumnIndex("yellow_visiting_patients");
      int index19=cursor.getColumnIndex("yellow_average_waiting_time");
      int index20=cursor.getColumnIndex("red_waiting_patients");
      int index21=cursor.getColumnIndex("red_average_waiting_time");

      do
       {
        resultBean=new FirstAid();

        if (!cursor.isNull(index0)) { resultBean.id=cursor.getLong(index0); }
        if (!cursor.isNull(index1)) { resultBean.uid=cursor.getString(index1); }
        if (!cursor.isNull(index2)) { resultBean.description=cursor.getString(index2); }
        if (!cursor.isNull(index3)) { resultBean.info=cursor.getString(index3); }
        if (!cursor.isNull(index4)) { resultBean.longitude=cursor.getFloat(index4); }
        if (!cursor.isNull(index5)) { resultBean.latitude=cursor.getFloat(index5); }
        if (!cursor.isNull(index6)) { resultBean.address=cursor.getString(index6); }
        if (!cursor.isNull(index7)) { resultBean.address2=cursor.getString(index7); }
        if (!cursor.isNull(index8)) { resultBean.city=cursor.getString(index8); }
        if (!cursor.isNull(index9)) { resultBean.phone=cursor.getString(index9); }
        if (!cursor.isNull(index10)) { resultBean.totalPatientCount=0; }
        if (!cursor.isNull(index11)) { resultBean.whiteWaitingPatients=0; }
        if (!cursor.isNull(index12)) { resultBean.whiteVisitingPatients=0; }
        if (!cursor.isNull(index13)) { resultBean.whiteAverageWaitingTime=cursor.getString(index13); }
        if (!cursor.isNull(index14)) { resultBean.greenWaitingPatients=0; }
        if (!cursor.isNull(index15)) { resultBean.greenVisitingPatients=0; }
        if (!cursor.isNull(index16)) { resultBean.greenAverageWaitingTime=cursor.getString(index16); }
        if (!cursor.isNull(index17)) { resultBean.yellowWaitingPatients=0; }
        if (!cursor.isNull(index18)) { resultBean.yellowVisitingPatients=0; }
        if (!cursor.isNull(index19)) { resultBean.yellowAverageWaitingTime=cursor.getString(index19); }
        if (!cursor.isNull(index20)) { resultBean.redWaitingPatients=0; }
        if (!cursor.isNull(index21)) { resultBean.redAverageWaitingTime=cursor.getString(index21); }

        resultList.add(resultBean);
      } while (cursor.moveToNext());
    }
    cursor.close();

    return resultList;
  }

  /**
   * <p>Delete query:</p>
   * <pre>DELETE first_aid WHERE 1=1</pre>
   *
   * @return number of deleted records
   */
  @Override
  public int deleteAll() {
    String[] whereConditions={};

    Logger.info(StringUtil.formatSQL("DELETE first_aid WHERE 1=1"), (Object[])whereConditions);
    int result = database().delete("first_aid", "1=1", whereConditions);
    return result;
  }

  /**
   * <p>Insert query:</p>
   * <pre>INSERT INTO first_aid (uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time) VALUES (${bean.uid}, ${bean.description}, ${bean.info}, ${bean.longitude}, ${bean.latitude}, ${bean.address}, ${bean.address2}, ${bean.city}, ${bean.phone}, ${bean.totalPatientCount}, ${bean.whiteWaitingPatients}, ${bean.whiteVisitingPatients}, ${bean.whiteAverageWaitingTime}, ${bean.greenWaitingPatients}, ${bean.greenVisitingPatients}, ${bean.greenAverageWaitingTime}, ${bean.yellowWaitingPatients}, ${bean.yellowVisitingPatients}, ${bean.yellowAverageWaitingTime}, ${bean.redWaitingPatients}, ${bean.redAverageWaitingTime})</pre>
   * <p><code>bean.id</code> is automatically updated because it is the primary key</p>
   *
   * @param bean
   * 	used as updated field and in where condition
   * @return id of inserted record
   */
  @Override
  public int insert(FirstAid bean) {
    ContentValues contentValues=contentValues();
    contentValues.clear();

    if (bean.uid!=null) {
      contentValues.put("uid", bean.uid);
    } else {
      contentValues.putNull("uid");
    }

    if (bean.description!=null) {
      contentValues.put("description", bean.description);
    } else {
      contentValues.putNull("description");
    }

    if (bean.info!=null) {
      contentValues.put("info", bean.info);
    } else {
      contentValues.putNull("info");
    }

    if (bean.longitude!=null) {
      contentValues.put("longitude", bean.longitude);
    } else {
      contentValues.putNull("longitude");
    }

    if (bean.latitude!=null) {
      contentValues.put("latitude", bean.latitude);
    } else {
      contentValues.putNull("latitude");
    }

    if (bean.address!=null) {
      contentValues.put("address", bean.address);
    } else {
      contentValues.putNull("address");
    }

    if (bean.address2!=null) {
      contentValues.put("address2", bean.address2);
    } else {
      contentValues.putNull("address2");
    }

    if (bean.city!=null) {
      contentValues.put("city", bean.city);
    } else {
      contentValues.putNull("city");
    }

    if (bean.phone!=null) {
      contentValues.put("phone", bean.phone);
    } else {
      contentValues.putNull("phone");
    }

    contentValues.put("total_patient_count", bean.totalPatientCount);

    contentValues.put("white_waiting_patients", bean.whiteWaitingPatients);

    contentValues.put("white_visiting_patients", bean.whiteVisitingPatients);

    if (bean.whiteAverageWaitingTime!=null) {
      contentValues.put("white_average_waiting_time", bean.whiteAverageWaitingTime);
    } else {
      contentValues.putNull("white_average_waiting_time");
    }

    contentValues.put("green_waiting_patients", bean.greenWaitingPatients);

    contentValues.put("green_visiting_patients", bean.greenVisitingPatients);

    if (bean.greenAverageWaitingTime!=null) {
      contentValues.put("green_average_waiting_time", bean.greenAverageWaitingTime);
    } else {
      contentValues.putNull("green_average_waiting_time");
    }

    contentValues.put("yellow_waiting_patients", bean.yellowWaitingPatients);

    contentValues.put("yellow_visiting_patients", bean.yellowVisitingPatients);

    if (bean.yellowAverageWaitingTime!=null) {
      contentValues.put("yellow_average_waiting_time", bean.yellowAverageWaitingTime);
    } else {
      contentValues.putNull("yellow_average_waiting_time");
    }

    contentValues.put("red_waiting_patients", bean.redWaitingPatients);

    if (bean.redAverageWaitingTime!=null) {
      contentValues.put("red_average_waiting_time", bean.redAverageWaitingTime);
    } else {
      contentValues.putNull("red_average_waiting_time");
    }

    // log
    Logger.info(StringUtil.formatSQL("SQL: INSERT INTO first_aid (uid, description, info, longitude, latitude, address, address2, city, phone, total_patient_count, white_waiting_patients, white_visiting_patients, white_average_waiting_time, green_waiting_patients, green_visiting_patients, green_average_waiting_time, yellow_waiting_patients, yellow_visiting_patients, yellow_average_waiting_time, red_waiting_patients, red_average_waiting_time) VALUES ('"+StringUtil.checkSize(contentValues.get("uid"))+"', '"+StringUtil.checkSize(contentValues.get("description"))+"', '"+StringUtil.checkSize(contentValues.get("info"))+"', '"+StringUtil.checkSize(contentValues.get("longitude"))+"', '"+StringUtil.checkSize(contentValues.get("latitude"))+"', '"+StringUtil.checkSize(contentValues.get("address"))+"', '"+StringUtil.checkSize(contentValues.get("address2"))+"', '"+StringUtil.checkSize(contentValues.get("city"))+"', '"+StringUtil.checkSize(contentValues.get("phone"))+"', '"+StringUtil.checkSize(contentValues.get("total_patient_count"))+"', '"+StringUtil.checkSize(contentValues.get("white_waiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("white_visiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("white_average_waiting_time"))+"', '"+StringUtil.checkSize(contentValues.get("green_waiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("green_visiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("green_average_waiting_time"))+"', '"+StringUtil.checkSize(contentValues.get("yellow_waiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("yellow_visiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("yellow_average_waiting_time"))+"', '"+StringUtil.checkSize(contentValues.get("red_waiting_patients"))+"', '"+StringUtil.checkSize(contentValues.get("red_average_waiting_time"))+"')"));
    long result = database().insert("first_aid", null, contentValues);
    bean.id=result;

    return (int)result;
  }
}
