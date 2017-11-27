 package com.yongjun.tdms.presentation.webwork.action.PersonnelFiles.rankAndGrade;
 
 import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.service.personnelFiles.contractadministrator.ContractAdministratorManager;
 
 public class ListRankAndGradeAction extends ValueListAction
 {
   private static final long serialVersionUID = -7991194195515604965L;
   private final CodeValueManager codeValueManager;
 
   public ListRankAndGradeAction(CodeValueManager codeValueManager)
   {
     this.codeValueManager = codeValueManager;
   }
 
 
   protected String getAdapterName()
   {
     return "rankAndGradeHQL";
   }
 
   public String execute(){
     return "success";
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
 }

