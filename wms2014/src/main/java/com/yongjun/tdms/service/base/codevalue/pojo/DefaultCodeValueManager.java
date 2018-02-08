/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.service.base.codevalue.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.codevalue.CodeValueDao;
import com.yongjun.tdms.model.base.codevalue.CodeValue;

import com.yongjun.tdms.service.base.codevalue.CodeValueManager;

/**
 * @author qs
 * @version $Id: DefaultCodeValueManager.java 11325 2008-03-15 06:48:17Z wzou $
 */
public class DefaultCodeValueManager extends BaseManager implements
		CodeValueManager {
	private final CodeValueDao codeValueDao;
	
	public DefaultCodeValueManager(CodeValueDao codeValueDao) {
		this.codeValueDao = codeValueDao;
	}
	
	public List<CodeValue> loadAllValuesByCodeId(Long id) {
		return this.codeValueDao.loadAllValuesByCodeId(id);
	}

	public CodeValue loadCodeValue(Long id) {
		return this.codeValueDao.loadCodeValue(id);
	}

	public List<CodeValue> LoadAllValuesByCode(String code) {
		CodeValue type = this.codeValueDao.loadCodeTypeByCode(code);
		return this.loadAllValuesByCodeId(type.getId());
	}
	
	public List<Long> LoadAllIdsByCode(String code) {
		CodeValue type = this.codeValueDao.loadCodeTypeByCode(code);
		return this.codeValueDao.loadAllIdsByCodeId(type.getId());
	}
	
//	public List<CodeValue> loadSpareTypesByCode() {
//		return this.codeValueDao.loadSpareTypesByCode();
//	}
	
	public List<CodeValue> createSelectCodeValues(String label, String code) {
		CodeValue cv = new CodeValue();
		cv.setId(Long.valueOf(-1L));
		cv.setValue(label);
		List<CodeValue> tmp = LoadAllValuesByCode(code);
		tmp.add(0, cv);
		return tmp;
	}

	public List<CodeValue> createSelectCodeValues(String label, String code,String flag) {
		CodeValue cv = new CodeValue();
		cv.setId(Long.valueOf(-1L));
		cv.setCode("-1");
		cv.setValue(label);
		List<CodeValue> tmp = LoadAllValuesByCode(code);
		tmp.add(0, cv);
		if (flag.equals("Borrow")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0152");//0152表示工装‘在用’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("ToolingChange")||flag.equals("ToolingFault")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0153");//0153表示工装‘整修’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("Trusteeship")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0156");//0156表示工装
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("Unused")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0154");//0156表示工装‘闲置’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("ToolingSealed")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0155");//0155表示工装‘封存’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("DeviceFault")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0032");//0155表示工装‘封存’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("DeviceUnused")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0033");//0033表示工装‘闲置’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("DeviceSealed")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0034");//0034表示工装‘封存’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		if (flag.equals("DeviceTrusteeship")) {
			cv = this.codeValueDao.loadCodeTypeByCode("0035");//0035表示设备‘托管’
			int i=0;
			for (CodeValue c : tmp) {
				if(c.getId().equals(cv.getId())){
					tmp.remove(i);
					break;
				}
				i++;
			}
			tmp.remove(tmp.size()-1);
		}
		//DeviceCheck设备日常检查，ToolingCheck工装日常检查，Migrate设备、工装转移，DeviceIntactness设备鉴定,DeviceDiscard设备报废
		//ToolingDiscard工装报废,DeviceInventory设备清查,ToolingInventory工装清查,DeviceLubrication设备润滑,DeviceMaintenance设备保养,
		//DevicePreRepair设备预防性维修,ToolingWash工装清洗,ToolingCalibration工装标定,ToolingPreRepair工装预防性维修,DeviceCheckPoint设备点检
		if (flag.equals("DeviceCheck")||flag.equals("ToolingCheck")||flag.equals("Migrate")||
				flag.equals("DeviceIntactness")||flag.equals("DeviceDiscard")||flag.equals("ToolingDiscard")||
				flag.equals("DeviceInventory")||flag.equals("ToolingInventory")||flag.equals("ToolingPreRepair")||
				flag.equals("DeviceLubrication")||flag.equals("DeviceMaintenance")||
				flag.equals("DevicePreRepair")||flag.equals("ToolingWash")||flag.equals("ToolingCalibration")
				|| flag.equals("DeviceCheckPoint")) {
			tmp.remove(tmp.size()-1);
		}
		//tmp.remove(tmp.size()-1);		
		return tmp;
	}
	
	public List<CodeValue> createSelectCodeValuesOfDiscard(String label) {
		List<CodeValue> tmp = new ArrayList();
		CodeValue cv = new CodeValue();
		cv.setId(Long.valueOf(-1L));
		cv.setValue(label);
		tmp.add(this.codeValueDao.loadCodeTypeByCode("0031"));
		tmp.add(this.codeValueDao.loadCodeTypeByCode("0032"));
		tmp.add(this.codeValueDao.loadCodeTypeByCode("0033"));
		tmp.add(this.codeValueDao.loadCodeTypeByCode("0034"));
		tmp.add(this.codeValueDao.loadCodeTypeByCode("0035"));
		tmp.add(this.codeValueDao.loadCodeTypeByCode("0036"));
		tmp.add(0, cv);
		return tmp;
	}

	public CodeValue loadCodeTypeByCode(String code) {
		return this.codeValueDao.loadCodeTypeByCode(code);
	}

	public CodeValue loadCodeTypeByRealCode(String realCode) {
		return this.codeValueDao.loadCodeTypeByRealCode(realCode);
	}
	
	public CodeValue loadCodeTypeByReferCode(String referCode) {
		return this.codeValueDao.loadCodeTypeByReferCode(referCode);
	}
	
	public void storeCodeValue(CodeValue codeValue) {
		this.codeValueDao.storeCodeValue(codeValue);
	}

	public List<CodeValue> loadAllValues() {
		return this.codeValueDao.loadAllValues();
	}

	public void deleteAllCodeValue(Collection<CodeValue> codeValueDetails) {
		this.codeValueDao.deleteAllCodeValue(codeValueDetails);
	}

	public List<CodeValue> loadAllValues(Long[] codeValueDetailIds) {
		return this.codeValueDao.loadAllValues(codeValueDetailIds);
	}

	public void disabledAllCodeValues(Collection<CodeValue> codeValues) {
		for (CodeValue codeValue : codeValues) {
			codeValue.setDisabled(true);
			codeValueDao.storeCodeValue(codeValue);
		}
	}

	public void enabledAllChecks(Collection<CodeValue> codeValues) {
		for (CodeValue codeValue : codeValues) {
			codeValue.setDisabled(false);
			codeValueDao.storeCodeValue(codeValue);
		}
	}

	
	public int getCodeValueByValue(String codeValue){
		return this.codeValueDao.getCodeValueByValue(codeValue);
	}


	public int getcodeValueDetailByValueCount(String codeValue, Long id) {
		return this.codeValueDao.getcodeValueDetailByValueCount(codeValue, id);
	}



}
