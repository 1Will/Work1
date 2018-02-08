package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.qualification;

import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.base.institution.Institution;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.personnelFiles.qualification.Qualification;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.personnelFiles.qualification.QualificationManager;

public class EditQualificationAction extends PrepareAction {
	private static final long serialVersionUID = -6543880456612721423L;
	private final QualificationManager qualificationManager;
	private final PersonnelFilesManager personnelFilesManager;
	private final InstitutionManager institutionManager;
	private final DepartmentManager departmentManager;
	private final CodeValueManager codeValueManager;
	private PersonnelFiles personnelFiles;
	private Qualification qualification;
	private Institution institution;
	private Department dept;
	private String popWindowFlag;

	public EditQualificationAction(QualificationManager qualificationManager,PersonnelFilesManager personnelFilesManager,
			InstitutionManager institutionManager,DepartmentManager departmentManager,CodeValueManager codeValueManager) {
		this.qualificationManager = qualificationManager;
		this.personnelFilesManager = personnelFilesManager;
		this.institutionManager = institutionManager;
		this.departmentManager = departmentManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (hasId("qualification.id")) {
			this.qualification = this.qualificationManager.loadQualification(getId("qualification.id"));
			this.personnelFiles = this.qualification.getPersonnelFiles();
			this.institution = this.qualification.getInstitution();
			this.dept = this.qualification.getDept();
		}else {
			this.qualification = new Qualification();
		}

		if (hasId("personnelFiles.id")) {
			this.personnelFiles = this.personnelFilesManager.loadPersonnel(getId("personnelFiles.id"));
		}
		
		if (hasId("institution.id")) {
			this.institution = this.institutionManager.loadInstitution(getId("institution.id"));
		}
		
		if (hasId("dept.id")) {
			this.dept = this.departmentManager.loadDepartment(getId("dept.id"));
		}
		if (hasId("popWindowFlag")) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
	}

	public String save() {
		boolean isNew = this.qualification.isNew();
		if (hasId("personnelFiles.id")) {
			this.qualification.setPersonnelFiles(this.personnelFiles);
		}else {
			this.qualification.setPersonnelFiles(null);
		}
		
		if (hasId("dept.id")) {
			this.qualification.setDept(this.dept);
		}
		
		if (hasId("institution.id")) {
			this.qualification.setInstitution(this.institution);
		}else {
			this.qualification.setInstitution(null);
		}
		
		if (hasId("type.id")) {
			this.qualification.setType(this.codeValueManager.loadCodeValue(getId("type.id")));
		}
		try {
			this.qualificationManager.storeQualification(this.qualification);
			if (isNew) {
				addActionMessage(getText("qualification.add.success"));
				return SUCCESS;
			} else {
				addActionMessage(getText("qualification.edit.success"));
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("qualification.save.error"));
		}
		return ERROR;
	}
	
	public List<Institution> getAllInsts() {
		List<Institution> list = this.institutionManager.loadAllInstitution();
		Institution agency = new Institution();
		agency.setName(getText(""));
		list.add(0, agency);
		return list;
	}
	
	public List<Department> getAllDepts() {
		List<Department> list = departmentManager.loadAllDepartments();
		if (list != null && list.size()>0) {
			for (int i = list.size()-1; i > 0 ; i--) {
				if (list.get(i).getParentDept()!=null) {
					list.remove(i);
				}
			}
		}
		Department dept = new Department();
		dept.setName(getText(""));
		list.add(0,dept);
		return list;
	}

	public List<CodeValue> getAllType() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { "300", Boolean.valueOf(false) };
			List<CodeValue> type = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
			if (type != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) type.get(0)).getId(), Boolean.valueOf(false) };
				List<CodeValue> types = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
				if (types != null)
					return types;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList<CodeValue>();
		}
		return new ArrayList<CodeValue>();
	}
	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public Qualification getQualification() {
		return qualification;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public void setQualification(Qualification qualification) {
		this.qualification = qualification;
	}

	public Department getDept() {
		return dept;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}
}
