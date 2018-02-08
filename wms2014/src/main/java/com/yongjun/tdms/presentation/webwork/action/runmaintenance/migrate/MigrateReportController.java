/*
 * Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of YongJun
 * Technology Pte.,Ltd. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with YongJun.
 * 
 * YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
 * NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
 * LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 * DERIVATIVES.
 */
package com.yongjun.tdms.presentation.webwork.action.runmaintenance.migrate;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.RequestUtils;

import com.yongjun.pluto.spring.controller.JasperReportsController;
import com.yongjun.tdms.model.runmaintenance.migrate.Migrate;
import com.yongjun.tdms.model.runmaintenance.migrate.MigrateDetail;
import com.yongjun.tdms.service.runmaintenance.migrate.MigrateManager;


/**
 * <p>Title: EditDiscardAction
 * <p>Description: 设备转移单打印控制类</p>
 * <p>Copyright: Copyright (c) 2001 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author smzhu@yj-technology.com
 * @version $Id: MigrateReportController.java 9353 2008-4-14 10:36:28Z smzhu $
 */
public class MigrateReportController extends JasperReportsController{

	private final MigrateManager migrateManager;
	private Migrate migrate;
	HttpServletRequest request;
	private Set<MigrateDetail> migrateDetails = new HashSet();
	
	public MigrateReportController(MigrateManager migrateManager){
		this.migrateManager=migrateManager;		
	}
	
	protected Map getModel(HttpServletRequest httpServletRequest) throws Exception {
		Map model = new HashMap();
		String migrateId = RequestUtils.getStringParameter(httpServletRequest, "migrateId");
		if(migrateId != null && !migrateId.equals("")){
			this.migrate = this.migrateManager.loadMigrate(Long.parseLong(migrateId));
			this.migrateDetails = this.migrate.getMigrateDetail();
		}
		migrateDetails = this.migrate.getMigrateDetail();
		model.put("reportData", migrateDetails);
		return model;
	}
	
	protected boolean hasId(String idName)
    {
        return !StringUtils.isEmpty(request.getParameter(idName));
    }
}
