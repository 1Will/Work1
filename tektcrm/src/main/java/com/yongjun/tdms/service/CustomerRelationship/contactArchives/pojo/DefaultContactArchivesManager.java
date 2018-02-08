package com.yongjun.tdms.service.CustomerRelationship.contactArchives.pojo;

import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.CustomerRelationship.contactArchives.ContactArchivesDao;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;

public class DefaultContactArchivesManager extends BaseManager implements ContactArchivesManager {
	public final ContactArchivesDao contactArchivesDao;
	private final UserManager userManager;

	public DefaultContactArchivesManager(ContactArchivesDao contactArchivesDao, UserManager userManager) {
		this.contactArchivesDao = contactArchivesDao;
		this.userManager = userManager;
	}

	public void storeContactArchives(ContactArchives ca) {
		setInfoIntegrity(ca);
		this.contactArchivesDao.storeContactArchives(ca);
	}

	public void setInfoIntegrity(ContactArchives contactArchives) {
		float base = 60.0F;
		if ((null != contactArchives.getAbbreviations()) && (!"".equals(contactArchives.getAbbreviations()))) {
			base = (float) (base + 2.5D);
		}

		if ((null != contactArchives.getDept()) && (!"".equals(contactArchives.getDept()))) {
			base = (float) (base + 2.2D);
		}

		if ((null != contactArchives.getDuty()) && (!"".equals(contactArchives.getDuty()))) {
			base = (float) (base + 2.2D);
		}

		base = (float) (base + 2.2D);

		if ((null != contactArchives.getMobilePhone()) && (!"".equals(contactArchives.getMobilePhone()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getFax()) && (!"".equals(contactArchives.getFax()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getPostCode()) && (!"".equals(contactArchives.getPostCode()))) {
			System.out.println("6");
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getAddress()) && (!"".equals(contactArchives.getAddress()))) {
			System.out.println("7");
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getEmail()) && (!"".equals(contactArchives.getEmail()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getQq()) && (!"".equals(contactArchives.getQq()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getMsn()) && (!"".equals(contactArchives.getMsn()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getPhone()) && (!"".equals(contactArchives.getPhone()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getSchool()) && (!"".equals(contactArchives.getSchool()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getAddress()) && (!"".equals(contactArchives.getAddress()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getComment()) && (!"".equals(contactArchives.getComment()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getProfessional()) && (!"".equals(contactArchives.getProfessional()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getFavorite()) && (!"".equals(contactArchives.getFavorite()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getBirthplace()) && (!"".equals(contactArchives.getBirthplace()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getNationality()) && (!"".equals(contactArchives.getNationality()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getTemperament()) && (!"".equals(contactArchives.getTemperament()))) {
			base = (float) (base + 2.2D);
		}
		if ((null != contactArchives.getBirthday()) && (!"".equals(contactArchives.getBirthday()))) {
			base = (float) (base + 2.2D);
		}
		contactArchives.setCustomerInfoIntegrity(Float.valueOf(base));
	}

	public ContactArchives loadContactArchives(Long caId) {
		return this.contactArchivesDao.loadContactArchives(caId);
	}

	public List<ContactArchives> loadAllContactArchives() {
		return this.contactArchivesDao.loadAllContactArchives();
	}

	public List<ContactArchives> loadAllContactArchives(Long[] caIds) {
		return this.contactArchivesDao.loadAllContactArchives(caIds);
	}

	public void deleteContactArchives(ContactArchives ca) {
		this.contactArchivesDao.deleteContactArchives(ca);
	}

	public void deleteAllContactArchives(List<ContactArchives> caIds) {
		this.contactArchivesDao.deleteAllContactArchives(caIds);
	}

	public List<ContactArchives> loadByKey(String key, Object value) throws DaoException {
		return this.contactArchivesDao.loadByKey(key, value);
	}

	public void disabledAllContactArchives(List<ContactArchives> cas) {
		for (ContactArchives archives : cas) {
			archives.setDisabled(true);
			this.contactArchivesDao.storeContactArchives(archives);
		}
	}

	public void enabledAllContactArchives(List<ContactArchives> cas) {
		for (ContactArchives archives : cas) {
			archives.setDisabled(false);
			this.contactArchivesDao.storeContactArchives(archives);
		}
	}

	public List<ContactArchives> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return this.contactArchivesDao.loadByKeyArray(keyNames, keyValues);
	}

	public int loadContactArchivesByDate(String userId, String da) {
		User user = userManager.loadUser(Long.parseLong(userId));
		List<ContactArchives> list = this.contactArchivesDao.getContactArchivesByCodeAndDate(da, user.getName());
		int size = list.size();
		return size;
		// List<ContactArchives> clist = new ArrayList<ContactArchives>();
		// for(ContactArchives contactArchives:list){
		// ContactArchives cs =new ContactArchives();
		// cs.setId(contactArchives.getId());
		// clist.add(cs);
		// }
		// return clist;
	}
}
