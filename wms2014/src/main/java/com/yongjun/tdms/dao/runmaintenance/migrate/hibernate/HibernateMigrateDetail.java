package com.yongjun.tdms.dao.runmaintenance.migrate.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.migrate.MigrateDetailDao;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;

public class HibernateMigrateDetail extends BaseHibernateDao implements
		MigrateDetailDao {

	public MigrateDetail loadMigrateDetail(Long MigrateDetailId) {
		return this.load(MigrateDetail.class,MigrateDetailId);
	}

	public List<MigrateDetail> loadAllMigrateDetails(Long[] MigrateDetailIds) {
		return this.loadAll(MigrateDetail.class,MigrateDetailIds);
	}

	public List<MigrateDetail> loadAllMigrateDetail() {
		return this.loadAll(MigrateDetail.class);
	}

	public void storeMigrateDetail(MigrateDetail migrateDetail) {
		this.store(migrateDetail);
	}
	
	public void deleteMigrateDetail(MigrateDetail migrateDetail) {
		this.delete(migrateDetail);
	}

	public void deleteAllMigrateDetails(Collection<MigrateDetail> MigrateDetails) {
		this.deleteAll(MigrateDetails);
	}

}
