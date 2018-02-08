package com.yongjun.tdms.service.base.org.pojo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.security.UserDao;
import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.dao.base.org.DepartmentDao;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceConstant;
import com.yongjun.tdms.service.yongJunSequence.YongJunSequenceManager;

@SuppressWarnings({"rawtypes","unchecked"})
public class DefaultDepartmentManager extends BaseManager implements DepartmentManager {
	private final DepartmentDao departmentDao;
	private final UserDao userDao;
	private final UserManager userManager;
	private final YongJunSequenceManager yongJunSequenceManager;

	public DefaultDepartmentManager(DepartmentDao departmentDao, UserDao userDao, UserManager userManager,
			YongJunSequenceManager yongJunSequenceManager) {
		this.departmentDao = departmentDao;
		this.userDao = userDao;
		this.userManager = userManager;
		this.yongJunSequenceManager = yongJunSequenceManager;
	}

	public void storeDepartment(Department department) {
		if (department.isNew()) {
			department.setCode((String) this.yongJunSequenceManager
					.generateByCodeType(YongJunSequenceConstant.CODE_DEPARTMENT));
		}
		if (department.getChildDepts().size() > 0) {
			storeDepartmentEditSTEP(department);
		}
		this.departmentDao.storeDepartment(department);
	}

	public void storeDepartmentEditSTEP(Department dept) {
		for (Department dept1 : dept.getChildDepts()) {
			dept1.setStep(dept1.getParentDept().getStep() + 1);
			this.departmentDao.storeDepartment(dept1);
			if (dept1.getChildDepts().size() > 0)
				storeDepartmentEditSTEP(dept1);
		}
	}

	public Department loadDepartment(Long departmentId) {
		return this.departmentDao.loadDepartment(departmentId);
	}

	public void deleteDepartment(Department department) {
		this.departmentDao.deleteDepartment(department);
	}

	public void deleteAllDepartments(Collection<Department> departmentIds) {
		this.departmentDao.deleteAllDepartments(departmentIds);
	}

	public List<Department> loadAllDepartments(Long[] departmentIds) {
		return this.departmentDao.loadAllDepartment(departmentIds);
	}

	public List<Department> loadAllDepartments() {
		return this.departmentDao.loadAllDepartments();
	}

	public List createSelectDepartments(String name) {
		List list = new ArrayList();

		List<Department> rootDept = this.departmentDao.getDeptsByStep(0);
		for (Department dept : rootDept) {
			List allDeptOfRootDept = getDeptsOfAfterTraversal(dept);
			for (int i = allDeptOfRootDept.size() - 1; i >= 0; i--) {
				list.add(allDeptOfRootDept.get(i));
			}
		}
		Department dept = new Department();
		dept.setId(Long.valueOf(-1L));
		dept.setName(name);
		list.add(0, dept);
		return list;
	}

	public List getParentChildRelationDept() {
		List list = new ArrayList();

		List<Department> rootDept = this.departmentDao.getDeptsByStep(0);
		for (Department dept : rootDept) {
			List allDeptOfRootDept = getDeptsOfAfterTraversal(dept);
			for (int i = allDeptOfRootDept.size() - 1; i >= 0; i--) {
				list.add(allDeptOfRootDept.get(i));
			}
		}
		return list;
	}

	public List createSelectParentGroups(String name) {
		List<Department> list = this.departmentDao.loadAllDepartments();
		Department dept = new Department();
		dept.setId(Long.valueOf(0L));
		dept.setName(name);
		list.add(0, dept);
		return list;
	}

	public int getDeptDepth(Department dept) {
		int maxDepth = 0;

		Department parentDept = dept.getParentDept();
		List treeDept = getDeptsOfAfterTraversal(dept);

		for (int i = treeDept.size() - 1; i >= 0; i--) {
			int tmpDepth = 1;
			Department tempParentDept = ((Department) treeDept.get(i)).getParentDept();
			while (!tempParentDept.equals(parentDept)) {
				tempParentDept = tempParentDept.getParentDept();
				tmpDepth++;
			}
			if (maxDepth < tmpDepth) {
				maxDepth = tmpDepth;
			}
		}
		return maxDepth;
	}

	public int getDeptChildNum(Department dept) {
		int childNum = 0;
		if (0 == dept.getChildDepts().size()) {
			return 1;
		}
		for (Department d : dept.getChildDepts()) {
			childNum += getDeptChildNum(d);
		}
		return childNum;
	}

	public List<Department> getDeptsOfAfterTraversal(Department dept) {
		List container = new ArrayList();
		traversalDepts(dept, container);
		return container;
	}

	protected void traversalDepts(Department dept, List container) {
		if (0 == dept.getChildDepts().size()) {
			container.add(dept);
			return;
		}
		for (Department d : dept.getChildDepts()) {
			traversalDepts(d, container);
		}
		container.add(dept);
	}

	public List<Integer> getDeptStepAfterGroupingByStep() {
		return this.departmentDao.getDeptStepAfterGroupingByStep();
	}

	public List<Department> getDeptsByStep(int step) {
		return this.departmentDao.getDeptsByStep(step);
	}

	public List<Department> getDeptsByParentDept(Long parentId) {
		return this.departmentDao.getDeptsByParentDept(parentId);
	}

	public List<Department> getAllChilds(Department dept) {
		List container = new ArrayList();
		traversalDepts(dept, container);
		container.remove(dept);
		return container;
	}

	public void storeDepartment(Department department, boolean changParentDept) {
		if (department.isNew()) {
			setStepOfDept(department);
		} else if (changParentDept) {
			int orgStep = department.getStep();
			setStepOfDept(department);
			int stepDiff = department.getStep() - orgStep;
			updateStepOfChildDepts(stepDiff, getAllChilds(department));
		}
		this.departmentDao.storeDepartment(department);
	}

	protected void setStepOfDept(Department department) {
		if (null != department.getParentDept())
			department.setStep(department.getParentDept().getStep() + 1);
		else
			department.setStep(0);
	}

	public void updateStepOfChildDepts(int stepDiff, List<Department> depts) {
		for (Department dept : depts) {
			dept.setStep(dept.getStep() + stepDiff);
			this.departmentDao.storeDepartment(dept);
		}
	}

	public void joinUsersForDepartment(String[] userIds, Department department) {
		for (int i = 0; i < userIds.length; i++) {
			User user = this.userDao.loadUser(Long.valueOf(userIds[i]));
			department.getUsers().add(user);
		}
		this.departmentDao.storeDepartment(department);
	}

	public List displaySortUser(Long departmentId) {
		return this.departmentDao.displaySortUser(departmentId);
	}

	public void disabledAllDepartment(Collection<Department> departments) {
		for (Department dept : departments) {
			dept.setDisabled(true);
			this.departmentDao.storeDepartment(dept);
		}
	}

	public void enabledAllDepartment(Collection<Department> departments) {
		for (Department dept : departments) {
			dept.setDisabled(false);
			this.departmentDao.storeDepartment(dept);
		}
	}

	public List<Department> InstitutionSelectDept(String instId, boolean isNew) {
		List list = this.departmentDao.InstitutionSelectDept(Long.valueOf(instId), isNew);
		// Department dept = new Department();
		// list.add(0, dept);
		return list;
	}

	public List<Department> loadByKey(String keyName, Object keyValue) throws DaoException {
		return this.departmentDao.loadByKey(keyName, keyValue);
	}

	public int getDeptSteps() {
		return this.departmentDao.getDeptSteps();
	}

	public List<Department> getDepartments(boolean isNew, String name, Long id) {
		if (isNew) {
			List<Department> list = createSelectDepartments("");
			List enableDept = new ArrayList();

			for (Department dept : list) {
				if (!dept.getDisabled()) {
					enableDept.add(dept);
				}
			}
			return enableDept;
		}
		List list = loadAllDepartments();

		Department dept = new Department();
		dept.setId(Long.valueOf(id.longValue()));
		dept.setName(name);
		list.add(0, dept);
		return list;
	}

	public List<Department> getDepartments() {
		List<Department> list = createSelectDepartments("");
		List enableDept = new ArrayList();

		for (Department dept : list) {
			if (!dept.getDisabled()) {
				enableDept.add(dept);
			}
		}
		return enableDept;
	}

	public List<Department> getDeptsByUserId(Long userId, String flag) throws Exception {
		return this.departmentDao.getDeptsByUserId(userId, flag);
	}

	public List<Department> getDeptsByInstIdInUser(Long instId, String flag) throws Exception {
		Long userId = this.userManager.getUser().getId();
		List depts = getDeptsByUserId(userId, flag);
		if (instId.longValue() == -1L) {
			return null;
		}
		List deptlist = new ArrayList();
		for (int i = 0; i < depts.size(); i++) {
			if (((Department) depts.get(i)).getInst().getId().equals(instId)) {
				deptlist.add(depts.get(i));
			}
		}
		return deptlist;
	}

	public List<Department> loadByKeyArray(String[] keyNames, Object[] keyValues) throws DaoException {
		return null;
	}
}
