package com.yongjun.tdms.presentation.webwork.action.base.additionalInformation;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.base.additionalInformation.AdditionalInformation;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.base.additionalInformation.AdditionalInformationManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;

public class AdditionalInformationAction extends PrepareAction{
	   private static final long serialVersionUID = 3269015023775508432L;
	   private Department department;
	   private AdditionalInformation additionalInformation;
	   private PersonnelFiles personnelFiles;
	   private final DepartmentManager departmentManager;
	   private final UserManager userManager;
	   private final AdditionalInformationManager additionalInformationManager;
	   private final PersonnelFilesManager personnelFilesManager;
	   public AdditionalInformationAction(DepartmentManager departmentManager, UserManager userManager,AdditionalInformationManager additionalInformationManager,PersonnelFilesManager personnelFilesManager) { 
		   this.departmentManager = departmentManager;
	       this.userManager = userManager;
	       this.additionalInformationManager = additionalInformationManager;
	       this.personnelFilesManager=personnelFilesManager;
	   }
	   
	   
	   
	   
	   public void prepare() throws Exception {
		       if (hasId("department.id")) {
		    	 this.department = this.departmentManager.loadDepartment(getId("department.id"));
		    	 List<AdditionalInformation>  additionalInformations= this.additionalInformationManager.loadByKey("department.id",Long.valueOf(this.request.getParameter("department.id")));
		    	 if(additionalInformations!=null && additionalInformations.size()>0){
		    		 this.additionalInformation=additionalInformations.get(0);
		    	 }else{
		    		 this.additionalInformation=new AdditionalInformation();
		    		 this.additionalInformation.setDepartment(department);
		    	 }
		       }/*else {
		           this.additionalInformation = new AdditionalInformation();
		           this.additionalInformation.setDepartment(department);
		         }*/
		   
	   }
	   
	   public String save() {
		   boolean isNew = this.additionalInformation.isNew();
		   try { 
			   if (!StringUtils.isEmpty(this.request.getParameter("leader.id"))) {
				  List <PersonnelFiles> personnelFileses= this.personnelFilesManager.loadByKey("id", Long.valueOf(this.request.getParameter("leader.id")));
				   if(personnelFileses!=null && personnelFileses.size()>0){
					   this.additionalInformation.setLeader(personnelFileses.get(0));
				   }
				}
		   if (!StringUtils.isEmpty(this.request.getParameter("manageLeader.id"))) {
			   List <PersonnelFiles> personnelFiless= this.personnelFilesManager.loadByKey("id", Long.valueOf(this.request.getParameter("manageLeader.id")));
			   if(personnelFiless!=null && personnelFiless.size()>0){
				   this.additionalInformation.setManageLeader(personnelFiless.get(0));
			   }
			}
				this.additionalInformationManager.storeAdditionalInformation(this.additionalInformation);
				if (null != this.additionalInformation) {
					this.additionalInformationManager.storeAdditionalInformation(this.additionalInformation);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (isNew) {
					addActionMessage(getText("additionalInformation.add.error",
							Arrays.asList(new Object[] { this.additionalInformation.getId() })));

					return "error";
				}
				addActionMessage(getText("additionalInformation.edit.error",
						Arrays.asList(new Object[] { this.additionalInformation.getId()  })));

				return "error";
			}
		   
		   if (isNew) {
				addActionMessage(getText("additionalInformation.add.success",
						Arrays.asList(new Object[] { this.additionalInformation.getId() })));

				return "new";
			}
			addActionMessage(getText("additionalInformation.edit.success"));
		   
		return "success";
		   
	   }
	   
	   
	   
	   public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	public AdditionalInformation getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(AdditionalInformation additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public PersonnelFiles getPersonnelFiles() {
		return personnelFiles;
	}

	public void setPersonnelFiles(PersonnelFiles personnelFiles) {
		this.personnelFiles = personnelFiles;
	}


}
