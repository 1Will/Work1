package com.yongjun.tdms.service.base.country;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.country.Country;


/**
 * @author qs
 * @version $Id: CountryManager.java 7340 2007-09-13 07:35:00Z qsun $
 */
@Transactional
public interface CountryManager {
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
	@Transactional
	public void storeCountry(Country country);
	
	/**
	 * 国家失效
	 * @return
	 */
	@Transactional
	void disableAllFiliales(Collection<Country> countries);
	/**
	 * 国家有效
	 * @return
	 */
	@Transactional
	void enabledAllFiliales(Collection<Country> countries);
}
