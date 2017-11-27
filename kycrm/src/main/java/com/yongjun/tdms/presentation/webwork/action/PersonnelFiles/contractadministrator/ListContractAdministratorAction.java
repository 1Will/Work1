 package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.contractadministrator;
 
 import com.yongjun.pluto.exception.DaoException;
 import com.yongjun.pluto.model.codevalue.CodeValue;
 import com.yongjun.pluto.service.codevalue.CodeValueManager;
 import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
 import com.yongjun.tdms.model.personnelFiles.contractadministrator.ContractAdministrator;
 import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
 import java.util.ArrayList;
 import java.util.List;
 
 public class ListContractAdministratorAction extends ValueListAction
 {
   private static final long serialVersionUID = -7991194195515604965L;
   private final CodeValueManager codeValueManager;
   private final ContractAdministratorManager contractAdministratorManager;
   private List<ContractAdministrator> contractAdministrators;
 
   public ListContractAdministratorAction(CodeValueManager codeValueManager, ContractAdministratorManager contractAdministratorManager)
   {
     this.codeValueManager = codeValueManager;
     this.contractAdministratorManager = contractAdministratorManager;
   }
 
   public void prepare()
     throws Exception
   {
     if (hasIds("contractAdministratorIds"))
       this.contractAdministrators = this.contractAdministratorManager.loadAllContractAdministrator(getIds("contractAdministratorIds"));
   }
 
   protected String getAdapterName()
   {
     return "contractAdministrators";
   }
 
   public String execute()
     throws Exception
   {
     if (isDisabled()) {
       disabled();
     }
     if (isEnable()) {
       enabled();
     }
     if (isDelete()) {
       delete();
     }
     return "success";
   }
 
   public String delete()
   {
     try
     {
       this.contractAdministratorManager.deleteAllContractAdministrator(this.contractAdministrators);
       addActionMessage(getText("contractAdministrator.delete.success"));
     } catch (RuntimeException e) {
       addActionMessage(getText("contractAdministrator.delete.error"));
       return "error";
     }
     return "success";
   }
 
   private String disabled()
   {
     try
     {
       this.contractAdministratorManager.disabledContractAdministrators(this.contractAdministrators);
       addActionMessage(getText("contractAdministrator.disabled.success"));
       return "success";
     } catch (RuntimeException e) {
       addActionMessage(getText("contractAdministrator.disabled.error"));
     }return "error";
   }
 
   private String enabled()
   {
     try
     {
       this.contractAdministratorManager.enabledContractAdministrators(this.contractAdministrators);
       addActionMessage(getText("contractAdministrator.enabled.success"));
       return "success";
     } catch (RuntimeException e) {
       e.printStackTrace();
       addActionMessage(getText("contractAdministrator.enabled.error"));
     }return "error";
   }
 
   public List<CodeValue> getAllContractTypes()
   {
     List codes = null;
     try {
       codes = new ArrayList();
       List one = this.codeValueManager.loadByKey("code", "055555");
 
       if ((null != one) && (one.size() > 0))
       {
         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
 
         if ((null != list) && (list.size() > 0)) {
           codes.addAll(list);
         }
       }
       CodeValue cv = new CodeValue();
       cv.setId(Long.valueOf(-1L));
       cv.setName(getText("select.option.all"));
       codes.add(0, cv);
       return codes;
     } catch (DaoException e) {
       e.printStackTrace();
     }
     return codes;
   }
 
   public List<CodeValue> getAllBecomes()
   {
     List codes = null;
     try {
       codes = new ArrayList();
       List one = this.codeValueManager.loadByKey("code", "055554");
 
       if ((null != one) && (one.size() > 0))
       {
         List list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue)one.get(0)).getId());
 
         if ((null != list) && (list.size() > 0)) {
           codes.addAll(list);
         }
       }
       CodeValue cv = new CodeValue();
       cv.setId(Long.valueOf(-1L));
       cv.setName(getText("select.option.all"));
       codes.add(0, cv);
       return codes;
     } catch (DaoException e) {
       e.printStackTrace();
     }
     return codes;
   }
 
   public List<ContractAdministrator> getContractAdministrators()
   {
     return this.contractAdministrators;
   }
 
   public void setContractAdministrators(List<ContractAdministrator> contractAdministrators)
   {
     this.contractAdministrators = contractAdministrators;
   }
 }

