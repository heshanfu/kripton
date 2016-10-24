package com.abubusoft.kripton.processor.test03;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for Dummy01DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Dummy01DataSource
 * @see DaoBean01
 * @see BindDaoBean01
 * @see Bean01
 */
public interface BindDummy01DaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao DaoBean01
   */
  BindDaoBean01 getDaoBean01();
}
