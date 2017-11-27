package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.rankAndGrade;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.rankAndGrade.RankAndGrade;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.personnelFiles.rankAndGrade.RankAndGradeManager;

public class EditRankAndGradeAction extends PrepareAction {

	private static final long serialVersionUID = -6353207655478426422L;
	private final PersonnelFilesManager personnelFilesManager;
	private final RankAndGradeManager rankAndGradeManager;
	private final CodeValueManager codeValueManager;
	private PersonnelFiles personnelFiles;
	private RankAndGrade rankAndGrade;
	
	public EditRankAndGradeAction (PersonnelFilesManager personnelFilesManager,RankAndGradeManager rankAndGradeManager,
			CodeValueManager codeValueManager) {
		this.personnelFilesManager = personnelFilesManager;
		this.rankAndGradeManager = rankAndGradeManager;
		this.codeValueManager = codeValueManager;
	}
	
	
	public void prepare() throws Exception {
		if (hasId("personnelFiles.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
		}
		if (hasId("rankAndGrade.id")) {
			this.rankAndGrade = this.rankAndGradeManager.loadRankAndGrade(getId("rankAndGrade.id"));
		}else {
			this.rankAndGrade =new RankAndGrade();
		}
	}
	
	public String save() {
		this.rankAndGrade.setBeforeGrade(codeValueManager.loadCodeValue(getId("beforeGrade.id")));
		this.rankAndGrade.setNewGrade(codeValueManager.loadCodeValue(getId("newGrade.id")));
		this.rankAndGrade.setBeforeRank(codeValueManager.loadCodeValue(getId("beforeRank.id")));
		this.rankAndGrade.setNewRank(codeValueManager.loadCodeValue(getId("newRank.id")));
		this.rankAndGrade.setPersonnelFiles(this.personnelFiles);
		try{
			this.rankAndGradeManager.storeRankAndGrade(this.rankAndGrade);
			this.personnelFiles.setGrade(codeValueManager.loadCodeValue(getId("newGrade.id")));
			this.personnelFiles.setRank(codeValueManager.loadCodeValue(getId("newRank.id")));
			this.personnelFilesManager.storePersonnel(this.personnelFiles);
		}catch(Exception e){
			e.printStackTrace();
			addActionMessage(getText("rankAndGrade.add.error"));
			return ERROR;
		}
		addActionMessage(getText("rankAndGrade.add.success"));
		return SUCCESS;
	}


	public List<CodeValue> getAllGrade() {
		try {
			List companyNatures = new ArrayList();
			String[] keys = { "code", "name" };
			String[] values = { "217", "等级" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}

	public List<CodeValue> getAllRank() {
		try {
			List companyNatures = new ArrayList();
			String[] keys = { "code", "name" };
			String[] values = { "215", "职级" };
			List one = this.codeValueManager.loadByKeyArray(keys, values);
			if ((null != one) && (one.size() > 0)) {
				List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());
				if ((null != list) && (list.size() > 0)) {
					companyNatures.addAll(list);
				}
			}
			return companyNatures;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}
	}


	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}


	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}


	public RankAndGrade getRankAndGrade() {
		return rankAndGrade;
	}


	public void setRankAndGrade(RankAndGrade rankAndGrade) {
		this.rankAndGrade = rankAndGrade;
	}

}
