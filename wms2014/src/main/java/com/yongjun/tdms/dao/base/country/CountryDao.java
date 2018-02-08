package com.yongjun.tdms.dao.base.country;

import java.util.List;


import com.yongjun.pluto.model.security.Organization;
import com.yongjun.tdms.model.base.country.Country;

/**
 * @author qs
 * @version $Id: CountryDao.java 8026 2007-10-25 09:58:57Z qsun $
 */
public interface CountryDao {
	//该方法暂时用不到
	List<Country> loadAllCountries(Organization organization);
	/**
	 * 加载所有的Country
	 * @return
	 */
	List<Country> loadAllCountries();
	
	/**
	 * 根据ID加载Country
	 * @param CountryId
	 * @return
	 */
	Country loadCountry(Long countryId);
	
	/**
	 * 记载所有的Country
	 * @return
	 */
	public List<Country> loadAllCountry(Long[] countryIds);
	
	/**
	 * 存储Country
	 * @param country
	 */
    void storeCountry(Country country);
    
}
