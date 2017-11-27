package com.yongjun.tdms.util.myremind.pojo;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.User;
import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.pluto.service.security.UserManager;
import com.yongjun.tdms.model.CustomerRelationship.contactArchives.ContactArchives;
import com.yongjun.tdms.model.CustomerRelationship.customerProfiles.CustomerInfo;
import com.yongjun.tdms.model.backvisit.BackVisit;
import com.yongjun.tdms.model.customercontract.contractmanagement.ContractManagement;
import com.yongjun.tdms.model.customercontract.plan.ReturnPlan;
import com.yongjun.tdms.model.notice.ReceviceNotice;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workspace.warnning.Remind;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnning;
import com.yongjun.tdms.model.workspace.warnning.WorkWarnningDetail;
import com.yongjun.tdms.service.CustomerRelationship.contactArchives.ContactArchivesManager;
import com.yongjun.tdms.service.CustomerRelationship.customerProfiles.CustomerInfoManager;
import com.yongjun.tdms.service.backvisit.BackVisitManager;
import com.yongjun.tdms.service.customercontract.contractmanagement.ContractManagementManager;
import com.yongjun.tdms.service.customercontract.plan.ReturnPlanManager;
import com.yongjun.tdms.service.notice.ReceviceNoticeManager;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workspace.remind.RemindManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningDetailManager;
import com.yongjun.tdms.service.workspace.warnning.WorkWarnningManager;
import com.yongjun.tdms.util.myremind.MyRemindManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.logging.Log;

public class DefaultMyRemindManager extends BaseManager
  implements MyRemindManager
{
  private final RemindManager remindManager;
  private final WorkWarnningManager workWarnningManager;
  private final UserManager userManager;
  private final ContactArchivesManager contactArchivesManager;
  private final WorkWarnningDetailManager workWarnningDetailManager;
  private final BackVisitManager backVisitManager;
  private final CustomerInfoManager customerInfoManager;
  private final PersonnelFilesManager personnelFilesManager;
  private final ReceviceNoticeManager receviceNoticeManager;
  private final ContractManagementManager contractManagementManager;
  private final ReturnPlanManager returnPlanManager;

  public DefaultMyRemindManager(RemindManager remindManager, WorkWarnningManager workWarnningManager, UserManager userManager, ContactArchivesManager contactArchivesManager, WorkWarnningDetailManager workWarnningDetailManager, BackVisitManager backVisitManager, CustomerInfoManager customerInfoManager, PersonnelFilesManager personnelFilesManager, ReceviceNoticeManager receviceNoticeManager, ContractManagementManager contractManagementManager, ReturnPlanManager returnPlanManager)
  {
    this.remindManager = remindManager;
    this.workWarnningManager = workWarnningManager;
    this.userManager = userManager;
    this.contactArchivesManager = contactArchivesManager;
    this.workWarnningDetailManager = workWarnningDetailManager;
    this.backVisitManager = backVisitManager;
    this.customerInfoManager = customerInfoManager;
    this.personnelFilesManager = personnelFilesManager;
    this.receviceNoticeManager = receviceNoticeManager;
    this.contractManagementManager = contractManagementManager;
    this.returnPlanManager = returnPlanManager;
  }

  public void storInfoByTime()
  {
    pFBirthdayRemind();

    Remind remind = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    User user = null;
    PersonnelFiles personelFiles = null;
    int m = 7;
    try
    {
      List remindList = this.remindManager.loadByKey("type", "客户生日提醒");
      if ((null != remindList) && (!remindList.isEmpty()))
        remind = (Remind)remindList.get(0);
    }
    catch (DaoException e1)
    {
      e1.printStackTrace();
    }
    if (null != remind) {
      Long mount = remind.getDays();
      m = Integer.valueOf(Long.valueOf(mount.longValue()).toString()).intValue();
    } else {
      remind = new Remind();
      remind.setCode("R001");
      remind.setDays(Long.valueOf(7L));
      remind.setType("客户生日提醒");
      this.remindManager.storeRemind(remind);
    }

    List<ContactArchives> contactArchives = this.contactArchivesManager.loadAllContactArchives();

    if ((null != contactArchives) && (contactArchives.size() > 0))
      for (ContactArchives contactArchive : contactArchives)
      {
        if (null != contactArchive.getBirthday())
        {
          String str = "";
          str = contactArchive.getBirthday().toString();
          boolean flag = dateAddMonth(str, m, date);
          if (flag) {
            try {
              CustomerInfo customer = contactArchive.getCustomerName();
              if (null != customer) {
                personelFiles = customer.getSalesman();
                List userList = new ArrayList();
                if (null != personelFiles) {
                  userList = this.userManager.loadByKey("code", personelFiles.getCode().trim());
                }

                if ((null != userList) && (!userList.isEmpty()))
                  user = (User)userList.get(0);
                else {
                  continue;
                }
                return;
              }continue;
            }
            catch (DaoException e) {
              e.printStackTrace();
            }

            String[] keyNames = new String[3];
            Object[] keyValues = new Object[3];
            keyNames[0] = "warnedPeople";
            keyNames[1] = "type";
            keyNames[2] = "remindObjectId";
            keyValues[0] = user.getId();
            keyValues[1] = remind.getType();
            keyValues[2] = contactArchive.getId();
            List workWarnningList = null;
            try {
              workWarnningList = this.workWarnningManager.loadByKeyArray(keyNames, keyValues);
            }
            catch (DaoException e)
            {
              e.printStackTrace();
            }

            WorkWarnning w = null;
            if ((null == workWarnningList) || (workWarnningList.size() <= 0))
              w = new WorkWarnning();
            else {
              w = (WorkWarnning)workWarnningList.get(0);
            }

            if (null != user) {
              w.setType(remind.getType());
              w.setWarnningDate(date);
              w.setWarnedPeople(user);
              w.setRemindObjectId(contactArchive.getId());
              w.setContent("有客户：" + contactArchive.getName() + "(" + contactArchive.getCustomerName().getName() + ")" + " 生日提醒");

              w.setOrganization(user.getOrganization());
            }
            this.workWarnningManager.storeWorkWarnning(w);

            WorkWarnningDetail wwd = null;
            List wwdlist = null;
            try {
              String[] keyName = new String[3];
              Object[] keyValue = new Object[3];
              keyName[0] = "workWarnningId";
              keyValue[0] = w.getId();
              keyName[1] = "name";
              keyValue[1] = contactArchive.getName();
              keyName[2] = "code";
              keyValue[2] = contactArchive.getCustomerName().getCode();

              wwdlist = this.workWarnningDetailManager.loadByKeyArray(keyName, keyValue);

              if ((null != wwdlist) && (wwdlist.size() > 0)) {
                wwd = (WorkWarnningDetail)wwdlist.get(0);
              }

              if (null == wwd) {
                wwd = new WorkWarnningDetail();
              }

              wwd.setName(contactArchive.getName());

              wwd.setCode(contactArchive.getCustomerName().getCode());
              wwd.setWorkWarnningId(w.getId());
              Date d = sdf.parse(str);
              Long n = Long.valueOf((d.getTime() + 86400000L - date.getTime()) / 86400000L);

              wwd.setRemaindays(n);
              wwd.setWarnDate(sdf.parse(sdf.format(date)));
              wwd.setOrganization(user.getOrganization());
              this.workWarnningDetailManager.storeWorkWarnningDetail(wwd);
            } catch (DaoException e) {
              e.printStackTrace();
            } catch (ParseException e) {
              e.printStackTrace();
            }
          }
        }
      }
  }

  public void pFBirthdayRemind()
  {
    Remind remind = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    User user = null;
    PersonnelFiles personelFiles = null;
    int m = 7;
    try
    {
      List remindList = this.remindManager.loadByKey("type", "员工生日提醒");
      if ((null != remindList) && (!remindList.isEmpty()))
        remind = (Remind)remindList.get(0);
    }
    catch (DaoException e1)
    {
      e1.printStackTrace();
    }
    if (null != remind) {
      Long mount = remind.getDays();
      m = Integer.valueOf(Long.valueOf(mount.longValue()).toString()).intValue();
    } else {
      remind = new Remind();
      remind.setCode("R004");
      remind.setDays(Long.valueOf(7L));
      remind.setType("员工生日提醒");
      this.remindManager.storeRemind(remind);
    }

    List<PersonnelFiles> pfList = this.personnelFilesManager.loadAllPersonnel();

    if ((null != pfList) && (pfList.size() > 0))
      for (PersonnelFiles pf : pfList)
      {
        if (null != pf.getBirthday())
        {
          String str = "";
          str = pf.getBirthday().toString();
          boolean flag = dateAddMonth(str, m, date);
          if (flag) {
            personelFiles = this.personnelFilesManager.loadPersonnel(Long.valueOf(2L));
            List userList = new ArrayList();
            if (null != personelFiles) {
              try {
                userList = this.userManager.loadByKey("code", personelFiles.getCode().trim());
              }
              catch (DaoException e) {
                this.logger.info("根据编码查询用户出错！");
              }
            }

            if ((null != userList) && (!userList.isEmpty())) {
              user = (User)userList.get(0);

              String[] keyNames = new String[3];
              Object[] keyValues = new Object[3];
              keyNames[0] = "warnedPeople";
              keyNames[1] = "type";
              keyNames[2] = "remindObjectId";
              keyValues[0] = user.getId();
              keyValues[1] = remind.getType();
              keyValues[2] = pf.getId();
              List workWarnningList = null;
              try
              {
                workWarnningList = this.workWarnningManager.loadByKeyArray(keyNames, keyValues);
              }
              catch (DaoException e) {
                this.logger.info("员工生日提醒查询出错！");
              }

              WorkWarnning w = null;
              if ((null == workWarnningList) || (workWarnningList.size() <= 0))
                w = new WorkWarnning();
              else {
                w = (WorkWarnning)workWarnningList.get(0);
              }
              w.setType(remind.getType());
              w.setWarnningDate(date);
              w.setWarnedPeople(user);
              w.setRemindObjectId(pf.getId());
              w.setContent("有员工：" + pf.getName() + "(" + pf.getCode() + ")" + " 生日提醒");
              w.setOrganization(user.getOrganization());
              this.workWarnningManager.storeWorkWarnning(w);

              WorkWarnningDetail wwd = null;
              List wwdlist = null;
              try {
                String[] keyName = new String[3];
                Object[] keyValue = new Object[3];
                keyName[0] = "workWarnningId";
                keyValue[0] = w.getId();
                keyName[1] = "name";
                keyValue[1] = pf.getName();
                keyName[2] = "code";
                keyValue[2] = pf.getCode();

                wwdlist = this.workWarnningDetailManager.loadByKeyArray(keyName, keyValue);

                if ((null != wwdlist) && (wwdlist.size() > 0)) {
                  wwd = (WorkWarnningDetail)wwdlist.get(0);
                }
                if (null == wwd) {
                  wwd = new WorkWarnningDetail();
                }

                wwd.setName(pf.getName());

                wwd.setCode(pf.getCode());
                wwd.setWorkWarnningId(w.getId());
                Date d = sdf.parse(str);
                Long n = Long.valueOf(((d.getTime() + 86400000L - date.getTime()) / 86400000L % 365L + 365L) % 365L);

                wwd.setRemaindays(n);
                wwd.setWarnDate(sdf.parse(sdf.format(date)));
                wwd.setOrganization(user.getOrganization());
                this.workWarnningDetailManager.storeWorkWarnningDetail(wwd);
              } catch (DaoException e) {
                e.printStackTrace();
              } catch (ParseException e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
  }

  public boolean dateAddMonth(String str, int mount, Date date)
  {
    boolean flag = true;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date t = null;
    Date date1 = null;
    Date date2 = null;
    try
    {
      t = sdf.parse(str);
      t = new Date(date.getYear(), t.getMonth(), t.getDate());

      GregorianCalendar worldTour = new GregorianCalendar(date.getYear() + 1900, t.getMonth(), t.getDate());

      worldTour.add(5, -mount);
      Date d = worldTour.getTime();
      String strDate = sdf.format(d);
      date2 = sdf.parse(strDate);

      date1 = sdf.parse(sdf.format(date));
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    if ((date1.after(date2)) && (date1.before(t))) {
      return flag;
    }
    flag = false;
    return flag;
  }

  public boolean compareBackVisistDate(Date backVisitDate, int mount, Date date)
  {
    boolean flag = true;
    Date d = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    try {
      GregorianCalendar worldTour = new GregorianCalendar(backVisitDate.getYear() + 1900, backVisitDate.getMonth(), backVisitDate.getDate(), backVisitDate.getHours(), backVisitDate.getMinutes());

      worldTour.add(10, -mount);
      d = worldTour.getTime();
    } catch (Exception e) {
      e.printStackTrace();
    }
    Date date1 = null;
    Date date2 = null;
    Date date3 = null;
    try {
      date1 = sdf.parse(sdf.format(date));
      date2 = sdf.parse(sdf.format(d));
      date3 = sdf.parse(sdf.format(backVisitDate));
    }
    catch (ParseException e) {
      e.printStackTrace();
    }
    if ((date1.getTime() >= date2.getTime()) && (date1.getTime() <= date3.getTime())) {
      return flag;
    }
    flag = false;
    return flag;
  }

  public void storBackInfoByTime()
  {
    Remind remind = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    User user = null;
    int m = 7;
    try
    {
      List remindList = this.remindManager.loadByKey("type", "回访提醒");
      if ((null != remindList) && (!remindList.isEmpty()))
        remind = (Remind)remindList.get(0);
    }
    catch (DaoException e1)
    {
      e1.printStackTrace();
    }
    if (null != remind) {
      Long mount = remind.getDays();
      m = Integer.valueOf(Long.valueOf(mount.longValue()).toString()).intValue();
    }

    List<BackVisit> backVisits = new ArrayList();
    try {
      backVisits = this.backVisitManager.loadByKey("disabled", Boolean.valueOf(false));
    } catch (DaoException e1) {
      this.logger.info("查询回访出错");
    }
    if ((null != backVisits) && (backVisits.size() > 0))
      for (BackVisit bv : backVisits) {
        Date d = bv.getNextBackVisitDate();

        String str = "";
        if (null != bv.getNextBackVisitDate())
        {
          str = d.toString();
          WorkWarnningDetail wwd = null;
          List list = null;
          WorkWarnning w = null;
          if (null == remind) {
            break;
          }
          boolean flag = false;
          if (null != d) {
            flag = compareBackVisistDate(d, m, date);
          }
          if (flag) {
            List userList = new ArrayList();
            try {
              userList = this.userManager.loadByKey("code", bv.getEmployee().getCode().trim());
            }
            catch (DaoException e)
            {
              e.printStackTrace();
            }

            if ((null != userList) && (!userList.isEmpty())) {
              user = (User)userList.get(0);

              String[] keyNames = new String[3];
              Object[] keyValues = new Object[3];
              keyNames[0] = "warnedPeople";
              keyNames[1] = "type";
              keyNames[2] = "remindObjectId";
              keyValues[0] = user.getId();
              keyValues[1] = remind.getType();
              keyValues[2] = bv.getId();
              List workWarnningList = null;
              try {
                workWarnningList = this.workWarnningManager.loadByKeyArray(keyNames, keyValues);
              }
              catch (DaoException e) {
                this.logger.info("查询提醒出错");
              }
              if ((null == workWarnningList) || (workWarnningList.isEmpty()) || (workWarnningList.size() == 0))
              {
                w = new WorkWarnning();
              }
              else w = (WorkWarnning)workWarnningList.get(0);

              w.setType(remind.getType());
              w.setWarnningDate(date);
              w.setWarnedPeople(user);
              w.setRemindObjectId(bv.getId());
              w.setContent("有1条条回访提醒");
              w.setOrganization(user.getOrganization());
              this.workWarnningManager.storeWorkWarnning(w);
              try
              {
                String[] keyName = new String[3];
                Object[] keyValue = new Object[3];

                keyName[0] = "workWarnningId";
                keyName[1] = "name";
                keyName[2] = "code";
                keyValue[0] = w.getId();
                keyValue[1] = bv.getCaName();
                keyValue[2] = bv.getCustomerInfo().getCode();
                list = this.workWarnningDetailManager.loadByKeyArray(keyName, keyValue);

                if ((null != list) && (list.size() > 0)) {
                  wwd = (WorkWarnningDetail)list.get(0);
                }
                if (null == wwd) {
                  wwd = new WorkWarnningDetail();
                }

                wwd.setName(bv.getCaName());
                wwd.setCode(bv.getCustomerInfo().getCode());
                wwd.setWorkWarnningId(w.getId());
                d = sdf.parse(str);
                Long n = Long.valueOf((d.getTime() - date.getTime()) / 3600000L);
                wwd.setRemaindays(n);
                wwd.setWarnDate(sdf.parse(sdf.format(date)));
                wwd.setOrganization(user.getOrganization());
                this.workWarnningDetailManager.storeWorkWarnningDetail(wwd);
              }
              catch (Exception e) {
                this.logger.info("根据提醒人、提醒明细名称和客户名称查询提醒明细出错! ");
              }
            }
          }
        }
      }
  }

  public void storAccountAuditByTime()
  {
  }

  public void storUnReadByTime()
  {
    String[] kNames = new String[2];
    Object[] kValues = new Object[2];
    kNames[0] = "readStatus";
    kNames[1] = "disabled";
    kValues[0] = "UNREAD";
    kValues[1] = Boolean.valueOf(false);
    List<ReceviceNotice> rNList = new ArrayList();
    try
    {
      rNList = this.receviceNoticeManager.loadByKeyArray(kNames, kValues);
    } catch (DaoException e1) {
      this.logger.info("查询未读通知出错!!!");
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    for (ReceviceNotice rN : rNList)
      if (!rN.getValidityDate().before(date))
      {
        kNames[0] = "type";
        kNames[1] = "warnedPeople";
        kValues[0] = "未读通知提醒";
        kValues[1] = rN.getReceviceUser().getId();
        List workWarnningList = null;
        WorkWarnning w = null;
        try {
          workWarnningList = this.workWarnningManager.loadByKeyArray(kNames, kValues);
        } catch (Exception e) {
          this.logger.info("未读通知提醒出错!!!");
        }
        if ((null != workWarnningList) && (!workWarnningList.isEmpty())) {
          w = (WorkWarnning)workWarnningList.get(0);
          w.setWarnningDate(date);
        } else {
          w = new WorkWarnning();
          w.setType("未读通知提醒");
          w.setWarnningDate(date);
          w.setWarnedPeople(rN.getReceviceUser());
          w.setContent("有1条条未读通知提醒！");
          this.workWarnningManager.storeWorkWarnning(w);
        }
        List<WorkWarnningDetail> wwdlist = null;
        WorkWarnningDetail wwd = null;
        String[] keyName = new String[2];
        Object[] keyValue = new Object[2];

        keyName[0] = "workWarnningId";
        keyName[1] = "name";
        keyValue[0] = w.getId();
        keyValue[1] = rN.getNoticeUser().getName();
        try
        {
          wwdlist = this.workWarnningDetailManager.loadByKeyArray(keyName, keyValue);
        } catch (Exception e) {
          this.logger.info("未读通知提醒明细出错!!!");
        }
        wwd = new WorkWarnningDetail();
        if ((null != wwdlist) && (!wwdlist.isEmpty())) {
          for (WorkWarnningDetail wwdFor : wwdlist) {
            if (wwdFor.getCode().equals(String.valueOf(rN.getId()))) {
              wwd = wwdFor;
            }
          }
          w.setContent("有" + wwdlist.size() + "条条未读通知提醒！");
          this.workWarnningManager.storeWorkWarnning(w);
        }
        wwd.setCode(String.valueOf(rN.getId()));
        wwd.setName(rN.getNoticeUser().getName());
        wwd.setWorkWarnningId(w.getId());
        Long n = Long.valueOf((rN.getValidityDate().getTime() + 86400000L - date.getTime()) / 86400000L);

        wwd.setRemaindays(n);
        try {
          wwd.setWarnDate(sdf.parse(sdf.format(date)));
        } catch (ParseException e) {
          this.logger.info("日期格式转换出错!!!");
        }
        this.workWarnningDetailManager.storeWorkWarnningDetail(wwd);
      }
  }

  public void cleanRemind()
  {
    List<WorkWarnning> wwList = this.workWarnningManager.loadAllWorkWarnnings();
    Date date = new Date();
    for (WorkWarnning w : wwList)
      if (w.isReadFlag())
      {
        Long n = Long.valueOf((w.getWarnningDate().getTime() + 86400000L - date.getTime()) / 86400000L);

        if (Math.abs(n.longValue()) >= 30L)
        {
          List<WorkWarnningDetail> wwdList = null;
          try {
            wwdList = this.workWarnningDetailManager.loadByKey("workWarnningId", w.getId());
          }
          catch (DaoException e) {
            this.logger.info("查询提醒明细出错!!!");
          }
          for (WorkWarnningDetail wwd : wwdList)
            this.workWarnningDetailManager.deleteWorkWarnningDetail(wwd);
        }
      }
  }

  public void contractExpires()
  {
    Remind remind = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    User user = null;
    PersonnelFiles personelFiles = new PersonnelFiles();
    int m = 7;
    try
    {
      List remindList = this.remindManager.loadByKey("type", "合同到期提醒");
      if ((null != remindList) && (!remindList.isEmpty()))
        remind = (Remind)remindList.get(0);
    }
    catch (DaoException e1)
    {
      e1.printStackTrace();
    }
    if (null != remind) {
      Long mount = remind.getDays();
      m = Integer.valueOf(Long.valueOf(mount.longValue()).toString()).intValue();
    } else {
      remind = new Remind();
      remind.setCode("R003");
      remind.setDays(Long.valueOf(7L));
      remind.setType("合同到期提醒");
      this.remindManager.storeRemind(remind);
    }
    List<ContractManagement> cMList = this.contractManagementManager.loadContractManagement();
    if ((null != cMList) && (cMList.size() > 0))
      for (ContractManagement conManage : cMList)
      {
        if (null != conManage.getEndTime())
        {
          String str = "";
          str = conManage.getEndTime().toString();
          boolean flag = dateAddMonth(str, m, date);
          if (flag) {
            personelFiles = this.personnelFilesManager.loadPersonnel(Long.valueOf(2L));
            List userList = new ArrayList();
            if (null != personelFiles) {
              try {
                userList = this.userManager.loadByKey("code", personelFiles.getCode().trim());
              }
              catch (DaoException e) {
                this.logger.info("根据编码查询用户出错！");
              }
            }

            if ((null != userList) && (!userList.isEmpty())) {
              user = (User)userList.get(0);

              String[] keyNames = new String[2];
              Object[] keyValues = new Object[2];
              keyNames[0] = "warnedPeople";
              keyNames[1] = "type";
              keyValues[0] = user.getId();
              keyValues[1] = remind.getType();
              List workWarnningList = new ArrayList();
              try {
                workWarnningList = this.workWarnningManager.loadByKeyArray(keyNames, keyValues);
              }
              catch (DaoException e)
              {
                e.printStackTrace();
              }
              WorkWarnning w = null;
              if ((null == workWarnningList) || (workWarnningList.size() <= 0))
                w = new WorkWarnning();
              else {
                w = (WorkWarnning)workWarnningList.get(0);
              }
              w.setType(remind.getType());
              w.setWarnningDate(date);
              w.setWarnedPeople(user);
              w.setContent("有合同：" + conManage.getContractName() + "(" + "批次：" + conManage.getCode() + ")" + " 到期提醒");
              w.setOrganization(user.getOrganization());
              this.workWarnningManager.storeWorkWarnning(w);

              WorkWarnningDetail wwd = null;
              List wwdlist = null;
              try {
                String[] keyName = new String[3];
                Object[] keyValue = new Object[3];
                keyName[0] = "workWarnningId";
                keyValue[0] = w.getId();
                keyName[1] = "name";
                keyValue[1] = conManage.getContractName().trim();
                keyName[2] = "code";
                keyValue[2] = conManage.getCode().trim();

                wwdlist = this.workWarnningDetailManager.loadByKeyArray(keyName, keyValue);

                if ((null != wwdlist) && (wwdlist.size() > 0)) {
                  wwd = (WorkWarnningDetail)wwdlist.get(0);
                }
                if (null == wwd) {
                  wwd = new WorkWarnningDetail();
                }

                wwd.setName(conManage.getContractName().trim());

                wwd.setCode(conManage.getCode().trim());
                wwd.setWorkWarnningId(w.getId());
                Date d = sdf.parse(str);
                Long n = Long.valueOf((d.getTime() + 86400000L - date.getTime()) / 86400000L);

                wwd.setRemaindays(n);
                wwd.setWarnDate(sdf.parse(sdf.format(date)));
                wwd.setOrganization(user.getOrganization());
                this.workWarnningDetailManager.storeWorkWarnningDetail(wwd);
              } catch (DaoException e) {
                e.printStackTrace();
              } catch (ParseException e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
  }

  public void returnPlanRemind()
  {
    Remind remind = null;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    User user = null;
    PersonnelFiles personelFiles = new PersonnelFiles();
    int m = 7;
    try
    {
      List remindList = this.remindManager.loadByKey("type", "收款计划提醒");
      if ((null != remindList) && (!remindList.isEmpty()))
        remind = (Remind)remindList.get(0);
    }
    catch (DaoException e1)
    {
      e1.printStackTrace();
    }
    if (null != remind) {
      Long mount = remind.getDays();
      m = Integer.valueOf(Long.valueOf(mount.longValue()).toString()).intValue();
    } else {
      remind = new Remind();
      remind.setCode("R005");
      remind.setDays(Long.valueOf(7L));
      remind.setType("收款计划提醒");
      this.remindManager.storeRemind(remind);
    }
    String[] keyNames = new String[2];
    Object[] keyValues = new Object[2];
    keyNames[0] = "isOrNot";
    keyNames[1] = "notOrIs";
    keyValues[0] = "1";
    keyValues[1] = "0";
    List<ReturnPlan> rePlanList = null;
    try {
      rePlanList = this.returnPlanManager.loadByKeyArray(keyNames, keyValues);
    } catch (DaoException e1) {
      this.logger.info("收款计划查询出错！");
    }
    if ((null != rePlanList) && (rePlanList.size() > 0))
      for (ReturnPlan rePlan : rePlanList) {
        rePlan = this.returnPlanManager.loadReturnPlan(rePlan.getId());

        if (null != rePlan.getPlanDate())
        {
          String str = "";
          str = rePlan.getPlanDate().toString();
          boolean flag = dateAddMonth(str, m, date);
          if (flag) {
            personelFiles = rePlan.getPayee();
            List userList = new ArrayList();
            if (null == personelFiles) {
              personelFiles = this.personnelFilesManager.loadPersonnel(Long.valueOf(11L));
            }
            if (null != personelFiles) {
              try {
                userList = this.userManager.loadByKey("code", personelFiles.getCode().trim());
              }
              catch (DaoException e) {
                this.logger.info("根据编码查询用户出错！");
              }
            }

            if ((null != userList) && (!userList.isEmpty())) {
              user = (User)userList.get(0);

              keyNames = new String[3];
              keyValues = new Object[3];

              keyNames[0] = "warnedPeople";
              keyNames[1] = "type";
              keyNames[2] = "remindObjectId";
              keyValues[0] = user.getId();
              keyValues[1] = remind.getType();
              keyValues[2] = rePlan.getId();
              List workWarnningList = new ArrayList();
              try {
                workWarnningList = this.workWarnningManager.loadByKeyArray(keyNames, keyValues);
              }
              catch (DaoException e) {
                e.printStackTrace();
              }
              WorkWarnning w = null;
              if ((null == workWarnningList) || (workWarnningList.size() <= 0))
                w = new WorkWarnning();
              else {
                w = (WorkWarnning)workWarnningList.get(0);
              }
              w.setType(remind.getType());
              w.setWarnningDate(date);
              w.setWarnedPeople(user);
              w.setRemindObjectId(rePlan.getId());
              ContractManagement objectCon = this.contractManagementManager.loadContractManagement(rePlan.getContractManagement().getId());

              w.setContent("有收款计划：" + objectCon.getCode() + "(" + rePlan.getBatch().getName() + ")" + " 提醒");
              w.setOrganization(user.getOrganization());
              this.workWarnningManager.storeWorkWarnning(w);

              WorkWarnningDetail wwd = null;
              List wwdlist = null;
              try {
                String[] keyName = new String[3];
                Object[] keyValue = new Object[3];
                keyName[0] = "workWarnningId";
                keyValue[0] = w.getId();
                keyName[1] = "name";
                keyValue[1] = rePlan.getCustomerInfo().getName().trim();
                keyName[2] = "code";
                keyValue[2] = rePlan.getCustomerInfo().getCode().trim();

                wwdlist = this.workWarnningDetailManager.loadByKeyArray(keyName, keyValue);

                if ((null != wwdlist) && (wwdlist.size() > 0)) {
                  wwd = (WorkWarnningDetail)wwdlist.get(0);
                }
                if (null == wwd) {
                  wwd = new WorkWarnningDetail();
                }

                wwd.setName(rePlan.getCustomerInfo().getName().trim());

                wwd.setCode(rePlan.getCustomerInfo().getCode().trim());
                wwd.setWorkWarnningId(w.getId());
                Date d = sdf.parse(str);
                Long n = Long.valueOf((d.getTime() + 86400000L - date.getTime()) / 86400000L);

                wwd.setRemaindays(n);
                wwd.setWarnDate(sdf.parse(sdf.format(date)));
                wwd.setOrganization(user.getOrganization());
                this.workWarnningDetailManager.storeWorkWarnningDetail(wwd);
              } catch (DaoException e) {
                e.printStackTrace();
              } catch (ParseException e) {
                e.printStackTrace();
              }
            }
          }
        }
      }
  }
}