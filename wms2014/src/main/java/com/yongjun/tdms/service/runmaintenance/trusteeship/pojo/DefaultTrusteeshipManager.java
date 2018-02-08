package com.yongjun.tdms.service.runmaintenance.trusteeship.pojo;

import java.util.Collection;
import java.util.List;
import com.yongjun.tdms.dao.runmaintenance.trusteeship.*;
import com.yongjun.pluto.sequence.service.SequenceManager;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.runmaintenance.trusteeship.Trusteeship;
import com.yongjun.tdms.model.runmaintenance.trusteeship.TrusteeshipDetail;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.runmaintenance.trusteeship.*;

public class DefaultTrusteeshipManager implements TrusteeshipManager {
	private final TrusteeshipDao trusteeshipdao;
	private final SequenceManager sequenceManager;
	
	private final DeviceCardManager deviceCardManager; 

	public DefaultTrusteeshipManager(TrusteeshipDao trusteeshipdao,
			SequenceManager sequenceManager
			,DeviceCardManager deviceCardManager) {
		this.sequenceManager = sequenceManager;
		this.trusteeshipdao = trusteeshipdao;
		this.deviceCardManager=deviceCardManager;
	
	}

	public Trusteeship loadTrusteeship(Long TrusteeshipId) {

		return trusteeshipdao.loadTrusteeship(TrusteeshipId);
	}

	public List<Trusteeship> loadTrusteeships(Long[] TrusteeshipIds) {
		return trusteeshipdao.loadTrusteeships(TrusteeshipIds);
	}

	public List<Trusteeship> loadTrusteeships() {

		return trusteeshipdao.loadTrusteeships();
	}

	public void storeTrusteeship(Trusteeship trusteeship) {
		if (trusteeship.isNew()) {
			String billNo = (String) sequenceManager.generate("-");
			trusteeship.setBillNo(billNo);
		}
		trusteeshipdao.storeTrusteeship(trusteeship);
		
		if(trusteeship.getTrusteeshipDetail().size()>0){
			for(TrusteeshipDetail detail:trusteeship.getTrusteeshipDetail()){
				DeviceCard deviceCard=detail.getAsset();
				if (trusteeship.getSupplier() != null) {
					deviceCard.setTrusteeshipSupplier(trusteeship.getSupplier());
					deviceCard.setTrusteeshipSupplierName(trusteeship.getSupplier().getName());
				}
				deviceCardManager.storeDevice(deviceCard);
			}
		}
	}

	public void deleteTrusteeship(Trusteeship trusteeship) {
		trusteeshipdao.deleteTrusteeship(trusteeship);
	}

	public void deleteAllTrusteeships(Collection<Trusteeship> Trusteeships) {
		trusteeshipdao.deleteAllTrusteeships(Trusteeships);

	}

	public void disabledAllTrusteeships(Collection<Trusteeship> Trusteeships) {
		for (Trusteeship oil : Trusteeships) {
			oil.setDisabled(true);
			this.trusteeshipdao.storeTrusteeship(oil);
		}

	}

	public void enabledAllTrusteeships(Collection<Trusteeship> Trusteeships) {

		for (Trusteeship oil : Trusteeships) {
			oil.setDisabled(false);
			this.trusteeshipdao.storeTrusteeship(oil);
		}

	}

}
