package com.yongjun.tdms.service.runmaintenance.alteration.pojo;

import java.util.List;

import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.dao.runmaintenance.alteration.AlterationDao;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.codevalue.CodeConstants;
import com.yongjun.tdms.model.runmaintenance.alteration.Alteration;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.codevalue.CodeValueManager;
import com.yongjun.tdms.service.runmaintenance.alteration.AlterationManager;

public class DefaultAlterationManager implements AlterationManager {

	private final AlterationDao aterationDao;

	private final SequenceManager sealedSequenceManager;
	
	private final DeviceCardManager deviceCardManager;
	
	private final CodeValueManager codeValueManager;


	public DefaultAlterationManager(AlterationDao aterationDao,
			SequenceManager sealedSequenceManager,
			CodeValueManager codeValueManager,
			DeviceCardManager deviceCardManager) {
		this.aterationDao = aterationDao;
		this.sealedSequenceManager = sealedSequenceManager;
		this.codeValueManager=codeValueManager;
		this.deviceCardManager=deviceCardManager;
	}

	public void storeAlteration(Alteration alteration) {

			if (alteration.isNew()) {
				String sealedBillNo = (String) sealedSequenceManager
						.generate("-");
				alteration.setSealedBillNo(sealedBillNo);
			}

		aterationDao.storeAlteration(alteration);
	}

	public List<Alteration> loadAllAlteration(Long[] alterationIds) {
		return aterationDao.loadAllAlteration(alterationIds);
	}

	public Alteration loadAlteration(Long id) {
		return aterationDao.loadAlteration(id);
	}
	
	public void deleteAllAlteration(List<Alteration> list) {
		aterationDao.deleteAllAlteration(list);
		DeviceCard tooling=new DeviceCard();
		for(Alteration alteration :list){
			tooling=alteration.getAsset();
			tooling.setToolingStatus(this.codeValueManager.loadCodeTypeByRealCode(CodeConstants.TOOLING_NORMAL_STATUS));
			this.deviceCardManager.storeTooling(tooling);
		}
		
	}

	public void disabledAllAlterations(List<Alteration> alterations) {
		for (Alteration alteration : alterations) {
			alteration.setDisabled(true);
			this.aterationDao.storeAlteration(alteration);
		}
	}

	public void enabledAllAlterations(List<Alteration> alterations) {
		for (Alteration alteration : alterations) {
			alteration.setDisabled(false);
			this.aterationDao.storeAlteration(alteration);
		}
	}
}
