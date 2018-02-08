package com.yongjun.tdms.presentation.webwork.action.security;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.log.service.BusinessLogger;
import com.yongjun.pluto.model.DomainModel;
import com.yongjun.pluto.model.LabelValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.model.security.UserType;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.base.institution.InstitutionManager;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class ListUsersAction extends ValueListAction
{
  private static final long serialVersionUID = 6596923569257153107L;
  protected final UserManager userManager;
  private final DepartmentManager departmentManager;
  private final InstitutionManager institutionManager;
  private final PersonnelFilesManager personnelFilesManager;
  private List<User> users;
  private String multipleSelect;
  private List<Long> listFilterUserIds;
  private String filterUserIds;

  public String getMultipleSelect()
  {
    return this.multipleSelect;
  }

  public void setMultipleSelect(String multipleSelect) {
    this.multipleSelect = multipleSelect;
  }

  public ListUsersAction(UserManager userManager, DepartmentManager departmentManager, InstitutionManager institutionManager, PersonnelFilesManager personnelFilesManager)
  {
    this.userManager = userManager;
    this.departmentManager = departmentManager;
    this.institutionManager = institutionManager;
    this.personnelFilesManager = personnelFilesManager;
  }

  public String execute() throws Exception {
    if (isDisable()) {
      return disable();
    }
    return "success";
  }

  private boolean isDisable() {
    return hasKey("disable");
  }

  private String disable() {
    this.userManager.disableAllUsers(this.users);
    for (int i = 0; i < this.users.size(); i++) {
      if (((User)this.users.get(i)).getCode() != null) {
        try {
         List e = this.personnelFilesManager.loadByKey("code", ((User)this.users.get(i)).getCode());
        }
        catch (DaoException e)
        {
          e.printStackTrace();
        }
      }

    }

    getLogger().logStore((DomainModel)this.users.get(0), "(登录名:" + logDisabled(this.users) + "的用户)被失效", "user_manager");

    addActionMessage(getText("users.disable.success"));
    return "success";
  }

  private String logDisabled(List<User> users)
  {
    String logDisabled = "";
    Integer index = null;
    for (User u : users) {
      logDisabled = logDisabled + u.getLoginName() + ",";
    }
    index = Integer.valueOf(logDisabled.lastIndexOf(","));
    logDisabled = logDisabled.substring(0, index.intValue());
    return logDisabled;
  }

  public void prepare() throws Exception {
    if ((this.users == null) && (hasIds("userIds"))) {
      this.users = this.userManager.loadAllUsers(getIds("userIds"));
    }
    if (hasId("multiple"))
      this.multipleSelect = this.request.getParameter("multiple");
    else {
      this.multipleSelect = "F";
    }
    if ((null == this.listFilterUserIds) && 
      (hasId("filterUserIds"))) {
      if ((!isFirst()) || (isSearch()))
      {
        String[] filterUserId = this.request.getParameter("filterUserIds").split(",");
        this.listFilterUserIds = new ArrayList();
        for (int i = 0; i < filterUserId.length; i++)
        {
          this.listFilterUserIds.add(Long.valueOf(filterUserId[i]));
        }
      }
      this.filterUserIds = this.request.getParameter("filterUserIds");
    }
  }

  protected Map getRequestParameterMap()
  {
    Map map = super.getRequestParameterMap();
    if (hasId("filterUserIds"))
    {
      String[] filterUserId = this.request.getParameter("filterUserIds").split(",");
      this.listFilterUserIds = new ArrayList();
      for (int i = 0; i < filterUserId.length; i++)
      {
        this.listFilterUserIds.add(Long.valueOf(filterUserId[i]));
      }
      map.put("filterUserIds", this.listFilterUserIds);
    }
    return map;
  }

  public boolean isSearch()
  {
    return hasKey("search");
  }

  protected String getAdapterName() {
    return "users";
  }

  public List getDepartments()
  {
    return this.departmentManager.createSelectDepartments(getText("select.option.all"));
  }

  public String getFilterUserIds() {
    return this.filterUserIds;
  }

  public void setFilterUserIds(String filterUserIds) {
    this.filterUserIds = filterUserIds;
  }

  public List<LabelValue> getUserType()
  {
    LabelValue[] arrays = wrapEnum(UserType.class);
    LabelValue labelValue = new LabelValue(Long.valueOf(-1L).toString(), getText("select.option.all"));

    List tmp = new ArrayList();
    tmp.add(labelValue);
    for (int i = 0; i < arrays.length; i++) {
      tmp.add(arrays[i]);
    }
    return tmp;
  }

  public List getInstitutions()
  {
    return this.institutionManager.loadAllInstitution(getText("select.option.all"));
  }
}