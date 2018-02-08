package com.yongjun.tdms.service.base.country.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.country.CountryDao;
import com.yongjun.tdms.model.base.country.Country;
import com.yongjun.tdms.service.base.country.CountryManager;

/**
 * @author qs
 * @version $Id: DefaultCountryManager.java 8026 2007-10-25 09:58:57Z qsun $
 */
public class DefaultCountryManager extends BaseManager implements
		CountryManager {
	private final CountryDao countryDao;
	
	public DefaultCountryManager(CountryDao countryDao) {
		this.countryDao = countryDao;
	}

	public void disableAllFiliales(Collection<Country> countries) {
		for(Country country : countries){
			country.setDisabled(true);
			this.countryDao.storeCountry(country);
		}
	}

	public void enabledAllFiliales(Collection<Country> countries) {
		for(Country country : countries){
			country.setDisabled(false);
			this.countryDao.storeCountry(country);
		}
	}

	public List<Country> loadAllCountries() {
		return this.countryDao.loadAllCountries();
	}


	public void storeCountry(Country country) {
		this.countryDao.storeCountry(country);
	}

	public List<Country> loadAllCountry(Long[] countryIds) {
		return this.countryDao.loadAllCountry(countryIds);
	}

	public Country loadCountry(Long countryId) {
		return this.countryDao.loadCountry(countryId);
	}
}
