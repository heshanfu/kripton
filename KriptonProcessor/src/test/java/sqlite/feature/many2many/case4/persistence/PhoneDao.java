package sqlite.feature.many2many.case4.persistence;

import java.util.List;

import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlSelect;

import sqlite.feature.many2many.case4.model.PhoneNumber;
import sqlite.feature.many2many.case4.persistence.AbstractDao;


@BindDao(PhoneNumber.class)
public interface PhoneDao extends AbstractDao<PhoneNumber> {

    @BindSqlSelect(where = " number = ${number}")
    PhoneNumber selectByNumber(String number);

    @BindSqlSelect(orderBy = "contactName, number")
    List<PhoneNumber> selectAll();
}
