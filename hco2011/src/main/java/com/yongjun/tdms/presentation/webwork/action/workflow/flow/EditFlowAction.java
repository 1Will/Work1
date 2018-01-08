 package com.yongjun.tdms.presentation.webwork.action.workflow.flow;
 
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.model.security.Department;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workflow.Flow;
import com.yongjun.tdms.service.base.org.DepartmentManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.point.PointManager;
 
 public class EditFlowAction extends PrepareAction
 {
   private static final long serialVersionUID = 732668911930478662L;
   private FlowManager flowManager;
   private DepartmentManager departmentManager;
   private PointManager pointManager;
   private Flow flow;
   private CodeValueManager codeValueManager;
   
   public EditFlowAction(FlowManager flowManager, DepartmentManager departmentManager, PointManager pointManager
		   ,CodeValueManager codeValueManager)
   {
     this.flowManager = flowManager;
     this.departmentManager = departmentManager;
     this.pointManager = pointManager;
     this.codeValueManager = codeValueManager;
   }
 
   public void prepare()
     throws Exception
   {
     if (null == this.flow)
     {
       if (hasId("flow.id"))
       {
         this.flow = this.flowManager.loadFlow(getId("flow.id"));
       }
       else
       {
         this.flow = new Flow();
       }
     }
     else
     {
       this.flow = new Flow();
     }
   }
 
   public String save()
   {
     if (!StringUtils.isEmpty(this.request.getParameter("flow.department")))
     {
       Department department = this.departmentManager.loadDepartment(getId("flow.department"));
       this.flow.setDepartment(department);
     }
     if (!StringUtils.isEmpty(this.request.getParameter("flow.flowCode")))
     {
       CodeValue flowCode = this.codeValueManager.loadCodeValue(getId("flow.flowCode"));
       this.flow.setFlowCode(flowCode);
     }
     boolean isNew = this.flow.isNew();
     try{
         this.flowManager.storeFlow(this.flow);
     }catch (Exception e)
     {
       e.printStackTrace();
       addActionMessage(getText("add.error", Arrays.asList(new Object[] { this.flow.getCode() })));
 
       return "error";
     }
 
     if (isNew)
     {
       addActionMessage(getText("add.success", Arrays.asList(new Object[] { this.flow.getCode() })));
 
       return "new";
     }
 
     addActionMessage(getText("edit.success", Arrays.asList(new Object[] { this.flow.getCode() })));
 
     return "success";
   }
 
   public List<Department> getAllDepartment()
   {
     List departments = this.departmentManager.loadAllDepartments();
     return departments;
   }
   /**
    * 獲取流程編碼
    * @return
    */
   public List<CodeValue> getAllFlowCode() {
		List<CodeValue> codes = null;
		try {
			codes = new ArrayList<CodeValue>();
			String[] keys = {"code","name"};
			Object[] values = {"215","流程编码"};
			List<CodeValue> one = this.codeValueManager.loadByKeyArray(keys, values);

			if ((null != one) && (one.size() > 0)) {
				List<CodeValue> list = this.codeValueManager.loadByKey("parentCV.id", ((CodeValue) one.get(0)).getId());

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
   
   public Flow getFlow()
   {
     return this.flow;
   }
 
   public void setFlow(Flow flow)
   {
     this.flow = flow;
   }
 }

