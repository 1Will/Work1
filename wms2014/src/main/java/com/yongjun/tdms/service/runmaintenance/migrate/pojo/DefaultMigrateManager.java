package com.yongjun.tdms.service.runmaintenance.migrate.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.migrate.MigrateDao;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateManager;

public class  DefaultMigrateManager implements MigrateManager {
     private final MigrateDao migratedao;
     private final SequenceManager sequenceManager;
     public  DefaultMigrateManager(MigrateDao migratedao,SequenceManager sequenceManager){
    	 this.migratedao=migratedao;
    	 this.sequenceManager=sequenceManager;
     }
	public Migrate loadMigrate(Long MigrateId) {
		
		return migratedao.loadMigrate(MigrateId);
	}

	public List<Migrate> loadMigrateds(Long[] MigrateIds) {
		return migratedao.loadMigrateds(MigrateIds);
	}

	public List<Migrate> loadMigrates() {
		return migratedao.loadMigrates();
	}

	public void storeMigrate(Migrate migrate) {
		if(migrate.isNew()){
			String billNo=(String)sequenceManager.generate("-");
			migrate.setBillNo(billNo);
		}

		migratedao.storeMigrate(migrate);
		
	}
  public void updateOriginProductAndDepartment(Migrate migrate){	//当转移单被保存之后改变原所属部门原所属生产线
	  migrate.setOldDepartment(migrate.getNewDepartment());   
	  migrate.setNewProductionLine(migrate.getNewProductionLine());
	 
  }
	public void deleteMigrate(Migrate migrate) {
		migratedao.deleteMigrate(migrate);
		
	}

	public void deleteAllMigrates(Collection<Migrate> Migrates) {
	   migratedao.deleteAllMigrates(Migrates);
		
	}
	public void disabledAllMigrates(Collection<Migrate> migrates) {            //失效所有的转移单
		for (Migrate oil : migrates) {
			oil.setDisabled(true);
			this.migratedao.storeMigrate(oil);
		}
		
	}

	public void enabledAllMigrates(Collection<Migrate> migrates) {          //有效所有的转移单
	
		for (Migrate oil : migrates) {
			oil.setDisabled(false);
			this.migratedao.storeMigrate(oil);
		}
   }
}