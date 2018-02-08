package com.yongjun.tdms.presentation.webwork.action.prophase.supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateDetail;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateReport;
import com.yongjun.tdms.model.prophase.supplier.SupplierEvaluateView;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateDetailManager;
import com.yongjun.tdms.service.prophase.supplier.SupplierEavluateManager;

public class ListSupplierEvaluateReportController extends JasperReportsController{
private SupplierEavluateDetailManager supplierEavluateDetailManager;
private List<SupplierEvaluateDetail> details = new ArrayList();
private SupplierEvaluateReport report = new SupplierEvaluateReport();
 public ListSupplierEvaluateReportController(SupplierEavluateDetailManager supplierEavluateDetailManager){
	 this.supplierEavluateDetailManager=supplierEavluateDetailManager;
 }
	@Override
	protected Map getModel(HttpServletRequest httpservletrequest) throws Exception {
		Map model = new HashMap();
		
		String supplierEvaluateid = RequestUtils.getStringParameter(httpservletrequest, "supplierEvaluateid");
		if(supplierEvaluateid != null && !supplierEvaluateid.equals("")){
			this.details = this.supplierEavluateDetailManager.loadSupplierEvaluateDetailById(Long.parseLong(supplierEvaluateid));
			for (SupplierEvaluateDetail detail : details) {
				report.setSupplierevaluate(detail.getSupplierevaluate());
				if ("DESIGN_CAPABILITY".equals(detail.getGradeFlag().toString())) {
					report.setGradezero(detail.getGrade());
					report.setCommentZero(detail.getComent());
					continue;
				}
				if("MAKE_CAPABILITY".equals(detail.getGradeFlag().toString())){
					report.setGradeOne(detail.getGrade());
					report.setCommentOne(detail.getComent());
					continue;
				}
				if("QA_CAPABILITY".equals(detail.getGradeFlag().toString())){
					report.setGradeTwo(detail.getGrade());
					report.setCommentTwo(detail.getComent());
					continue;
				}
				if("SERVICE_CAPABILITY".equals(detail.getGradeFlag().toString())){
					report.setGradeThree(detail.getGrade());
					report.setCommentThree(detail.getComent());
					continue;
				}
				if("BASIC_CONTROL_CAPABILITY".equals(detail.getGradeFlag().toString())){
					report.setGradeFour(detail.getGrade());
					report.setCoomentFour(detail.getComent());
					continue;
				} 
				
			}
		}
		model.put("reportData", new SupplierEvaluateReport[] {report});
		return model;
	}
}
