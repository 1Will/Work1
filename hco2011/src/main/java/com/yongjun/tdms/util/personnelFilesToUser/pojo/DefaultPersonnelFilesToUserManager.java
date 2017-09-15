package com.yongjun.tdms.util.personnelFilesToUser.pojo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.project.projectInfoPersonnels.ProjectInfoPersonnelsManager;
import com.yongjun.tdms.util.personnelFilesToUser.PersonnelFilesToUserManager;

public class DefaultPersonnelFilesToUserManager implements PersonnelFilesToUserManager {
	private final UserManager userManager;
	private final ProjectInfoPersonnelsManager projectInfoPersonnelsManager;
	private final PersonnelFilesManager personnelFilesManager;

	public DefaultPersonnelFilesToUserManager(UserManager userManager,
			ProjectInfoPersonnelsManager projectInfoPersonnelsManager, PersonnelFilesManager personnelFilesManager) {
		this.userManager = userManager;
		this.projectInfoPersonnelsManager = projectInfoPersonnelsManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public String loadUserIdToStrByProjectInfoId(Long projectInfoId, User user) {
		// flag 用于标识user是否在项目组成员中，假设为true，即不存在
		boolean flag = true;
		// 用于最终的结果返回
		String result = "";
		// 跟项目id查询项目组成员所有的工号code
		List<String> codeList = this.projectInfoPersonnelsManager.loadPersonnelsCodeByProjectInfoId(projectInfoId);
		// 判断查询结果是否为空和是否有数据
		if (codeList != null && codeList.size() > 0) {
			// 循环遍历，将每一个人事档案中工号code在用户表查询
			for (int i = 0; i < codeList.size(); i++) {
				List<User> users;
				try {
					// 执行查询
					users = this.userManager.loadByKey("code", codeList.get(i));
					// 判断是否有查询到用户， 如果有，则拼接到result中
					if (users != null && users.size() > 0) {
						if (result.equals("") || result == "") {
							result = users.get(0).getId() + "";
						} else {
							result += "," + users.get(0).getId();
						}
						// 如果list中有用户id和创建者id相等，则把flag设为false
						if (user != null && users.get(0).getId() == user.getId()) {
							flag = false;
						}

					}
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		// 判断是否存在，。如果不存在 就要将项目创建者拼接到result中
		if (flag) {
			if (result.equals("") || result == "") {
				result = user.getId() + "";
			} else {
				result += "," + user.getId();
			}

		}

		return result;
	}

	public String loadUserIdToStrByEnable() {
		// 用于最终的结果返回
		String result = "";
		// 查询所有有效员工的工号code
		List<String> codeList = this.projectInfoPersonnelsManager.loadPersonnelsCodeByEnable();
		// 判断查询结果是否为空和是否有数据
		if (codeList != null && codeList.size() > 0) {
			// 循环遍历，将每一个人事档案中工号code在用户表查询
			for (int i = 0; i < codeList.size(); i++) {
				List<User> users;
				try {
					// 执行查询
					users = this.userManager.loadByKey("code", codeList.get(i));
					// 判断是否有查询到用户， 如果有，则拼接到result中
					if (users != null && users.size() > 0) {
						if (result.equals("") || result == "") {
							result = users.get(0).getId() + "";
						} else {
							result += "," + users.get(0).getId();
						}

					}
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return result;
	}

	public String loadUserIdToStrByType(String code) {
		// 用于最终的结果返回
		String result = "";
		// 查询所有有效员工的工号code
		List<String> codeList = this.projectInfoPersonnelsManager.loadPersonnelsCodeByType(code);
		// 判断查询结果是否为空和是否有数据
		if (codeList != null && codeList.size() > 0) {
			// 循环遍历，将每一个人事档案中工号code在用户表查询
			for (int i = 0; i < codeList.size(); i++) {
				List<User> users;
				try {
					// 执行查询
					users = this.userManager.loadByKey("code", codeList.get(i));
					// 判断是否有查询到用户， 如果有，则拼接到result中
					if (users != null && users.size() > 0) {
						if (result.equals("") || result == "") {
							result = users.get(0).getId() + "";
						} else {
							result += "," + users.get(0).getId();
						}

					}
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		return result;
	}

	public Set<User> getTypeUser(Set<User> uSet, String typeCode) {
		Set<User> newSet = new HashSet<User>();
		try {
			for (User user : uSet) {
				List<PersonnelFiles> persons = this.personnelFilesManager.loadByKey("code", user.getCode());
				if(persons!=null&&persons.size()>0){
					PersonnelFiles person =persons.get(0);
					if(person.getBusinessType().getCode().equals(typeCode)||person.getBusinessType().getCode().equals("21003")){
						newSet.add(user);
					}
				}
			}

		} catch (DaoException e) {
			e.printStackTrace();
		}
		return newSet;
	}

}