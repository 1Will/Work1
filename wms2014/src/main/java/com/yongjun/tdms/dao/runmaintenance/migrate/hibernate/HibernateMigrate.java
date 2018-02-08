package com.yongjun.tdms.dao.runmaintenance.migrate.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.runmaintenance.migrate.MigrateDao;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
/**
 * <p>Title: HibernateMigrate
 * <p>Description: 转移单数据访问实现类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author zbzhang@yj-technology.com
 * @version $Id:  $
 * @see com.yongjun.tdms.dao.runmaintenance.migrate.MigrateDao
 */
public class HibernateMigrate extends BaseHibernateDao implements MigrateDao {

	public Migrate loadMigrate(Long MigrateId) {
		
		return this.load(Migrate.class,MigrateId);
	}

	public List<Migrate> loadMigrateds(Long[] MigrateIds) {
		return this.loadAll(Migrate.class,MigrateIds);
	}

	public List<Migrate> loadMigrates() {
		return this.loadAll(Migrate.class);
	}

	public void storeMigrate(Migrate migrate) {
		this.store(migrate);
		
	}

	public void deleteMigrate(Migrate migrate) {
		this.delete(migrate);
	}

	public void deleteAllMigrates(Collection<Migrate> Migrates) {
		this.deleteAll(Migrates);
	}

}
