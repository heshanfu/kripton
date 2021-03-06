package sqlite.feature.javadoc.delete.bean;

import com.abubusoft.kripton.android.annotation.BindContentProviderEntry;
import com.abubusoft.kripton.android.annotation.BindContentProviderPath;
import com.abubusoft.kripton.android.annotation.BindDao;
import com.abubusoft.kripton.android.annotation.BindSqlDelete;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhere;
import com.abubusoft.kripton.android.annotation.BindSqlDynamicWhereParams;

import sqlite.feature.javadoc.Person;

@BindContentProviderPath(path = "persons")
@BindDao(Person.class)
public interface DeleteBeanPersonDao {

	/**
	 * delete BEAN with parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "${bean.id}")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteOneBean(Person bean);

	/**
	 * delete BEAN with some parameters
	 * 
	 * @param bean
	 */
	@BindSqlDelete(jql = "DELETE FROM Person WHERE personName=${bean.personName} AND personSurname=${bean.personSurname} AND student = 0")
	void deleteAllBeansJQL(Person bean);

	/**
	 * JQL DELETE-FROM-SELECT can be used as content provider method. The
	 * important thing is params.
	 * 
	 * @param bean
	 */
	@BindContentProviderEntry(path = "a/${bean.personSurname}/${bean.personName}")
	@BindSqlDelete(jql = "DELETE FROM Person WHERE personSurname=${bean.personSurname} and student = (select student from Person where personName=${bean.personName})")
	int deleteFromSelectAllBeansJQL(Person bean);

	/**
	 * Update BEAN with one parameter.
	 * 
	 * @param bean
	 * @return
	 */
	@BindContentProviderEntry(path = "single/${bean.id}")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteBean(Person bean);

	@BindContentProviderEntry(path = "single2/${bean.id}")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteBeanDynamic(Person bean, @BindSqlDynamicWhere String where);

	@BindContentProviderEntry(path = "${bean.id}/moreAndMore")
	@BindSqlDelete(where = "id=${bean.id}")
	int deleteBeanDynamicWithArgs(Person bean, @BindSqlDynamicWhere String where, @BindSqlDynamicWhereParams String[] args);

}
