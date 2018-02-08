package com.yongjun.tdms.service.runmaintenance.unused.pojo;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.runmaintenance.unused.unusedDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.unused.UnUsed;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.unused.UnusedManager;

public class DefaultUnusedManager extends BaseManager implements UnusedManager {
	private final unusedDao unuseddao;

	private final SequenceManager sequenceManager;

	private final CodeValueManager codeValueManager;
	
	private final DeviceCardManager deviceCardManager;

	public DefaultUnusedManager(unusedDao unuseddao,
			SequenceManager sequenceManager,
			CodeValueManager codeValueManager,
			DeviceCardManager deviceCardManager) {
		this.unuseddao = unuseddao;
		this.sequenceManager = sequenceManager;
		this.codeValueManager = codeValueManager;
		this.deviceCardManager = deviceCardManager;
	}

	public UnUsed loadUnUsed(Long UnUsedId) {

		return unuseddao.loadUnUsed(UnUsedId);
	}

	public List<UnUsed> loadUnUseds(Long[] UnUsedIds) {

		return unuseddao.loadUnUseds(UnUsedIds);
	}

	public List<UnUsed> loadUnUseds() {

		return unuseddao.loadUnUseds();
	}

	public void storeUnUsed(UnUsed unUsed) {
		if (unUsed.isNew()) {
			String code = (String) sequenceManager.generate("-");
			unUsed.setCode(code);
		}
		unuseddao.storeUnUsed(unUsed);
		
		if (unUsed.getIsUnUsedAduit()||unUsed.getIsUsedAduit()) {
			 DeviceCard device = unUsed.getAsset();
			  if(unUsed.getIsUnUsedAduit()){
				device.setToolingStatus(this.codeValueManager
							.loadCodeTypeByRealCode(CodeConstants.TOOLING_UNUSED_STATUS));  
			    device.setDeviceStatus(this.codeValueManager
							.loadCodeTypeByRealCode(CodeConstants.DEVICE_UNUSED_STATUS));
			  }
			  if (unUsed.getIsUsedAduit()){
				    device.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));  
					device.setDeviceStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.DEVICE_NORMAL_STATUS));
										
			  }
			  this.deviceCardManager.storeDevice(device);
		 }
		
	}

	public void deleteUnUsed(UnUsed unUsed) {
		unuseddao.deleteUnUsed(unUsed);

	}

	public void deleteAllUnUseds(Collection<UnUsed> UnUseds) {
		unuseddao.deleteAllUnUseds(UnUseds);

	}

	public void disabledAllUnUseds(Collection<UnUsed> unUseds) {
		for (UnUsed oil : unUseds) {
			oil.setDisabled(true);
			this.unuseddao.storeUnUsed(oil);
		}

	}

	public void enabledAllUnUseds(Collection<UnUsed> unUseds) {

		for (UnUsed oil : unUseds) {
			oil.setDisabled(false);
			this.unuseddao.storeUnUsed(oil);
		}

	}
	  @SuppressWarnings("unchecked")
	public List<UnUsed> Query(String[] queryInfo) throws HibernateException {
		return this.unuseddao.Query(queryInfo);
	}
}
