package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.salarymanager.salaryset;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.salarymanager.salaryset.SalarySet;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.personnelFiles.salarymanager.salaryset.SalarySetManager;

import java.util.ArrayList;
import java.util.List;

public class EditSalarySetAction extends PrepareAction {
	private static final long serialVersionUID = -6543880456612721423L;
	private final CodeValueManager codeValueManager;
	private final SalarySetManager salarySetManager;
	private final PersonnelFilesManager personnelFilesManager;
	private SalarySet salarySet;
	private PersonnelFiles personnelFiles;

	public EditSalarySetAction(SalarySetManager salarySetManager, CodeValueManager codeValueManager,
			PersonnelFilesManager personnelFilesManager) {
		this.salarySetManager = salarySetManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public void prepare() throws Exception {
		if ((this.salarySet == null) && (hasId("salarySet.id"))) {
			this.salarySet = this.salarySetManager.loadSalarySet(getId("salarySet.id"));
		} else{
			this.salarySet = new SalarySet();
		}
		if(hasId("personnelFiles.id")){
			this.personnelFiles = personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
			List<SalarySet> salarySets =salarySetManager.loadByKey("emplyee.id", personnelFiles.getId());
			if(salarySets!=null&&salarySets.size()>0){
				this.salarySet = salarySets.get(0);
			}
		}
	}

	public String save() {
		boolean isNew = this.salarySet.isNew();
		this.salarySet.setEmplyee(personnelFiles);
		if (hasId("status.id")) {
			this.salarySet.setStatus(this.codeValueManager.loadCodeValue(getId("status.id")));
		}

		if (hasId("emplyee.id")){
			this.salarySet.setEmplyee(this.personnelFilesManager.loadPersonnel(getId("emplyee.id")));
		}
		try {
			if (isNew) {
				this.salarySetManager.storeSalarySet(this.salarySet);
				addActionMessage(getText("salarySet.add.success"));
				return "success";
			}
			this.salarySetManager.storeSalarySet(this.salarySet);
			addActionMessage(getText("salarySet.edit.success"));
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error";
	}

	public List<CodeValue> getAllStatus() {
		List codes = null;
		try {
			codes = new ArrayList();
			List one = this.codeValueManager.loadByKey("code", String.valueOf("055558"));

			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

				if ((null != list) && (list.size() > 0)) {
					codes.addAll(list);
				}
			}
			return codes;
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return codes;
	}

	public SalarySet getSalarySet() {
		return this.salarySet;
	}

	public void setSalarySet(SalarySet salarySet) {
		this.salarySet = salarySet;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}
}
