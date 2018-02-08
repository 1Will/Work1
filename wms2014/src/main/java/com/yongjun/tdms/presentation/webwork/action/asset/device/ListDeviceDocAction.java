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
package com.yongjun.tdms.presentation.webwork.action.asset.device;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.service.file.FileTransportManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.asset.device.DeviceCard;
import com.yongjun.tdms.model.base.document.ApplicationDoc;
import com.yongjun.tdms.service.asset.device.DeviceCardManager;
import com.yongjun.tdms.service.base.document.ApplicationDocManager;

/**
 * @author qs
 * @version $Id: ListDeviceDocAction.java 10840 2008-02-01 01:58:31Z qsun $
 */
public class ListDeviceDocAction extends ValueListAction  {
	private static final long serialVersionUID = -5005536686720555656L;
	private final Log log = LogFactory.getLog(getClass());
	private final DeviceCardManager deviceCardManager;
	private final ApplicationDocManager applicationDocManager;
	private final FileTransportManager fileTransportManager;
	private DeviceCard device;
	private List<ApplicationDoc> deviceDocs;
	private ApplicationDoc doc;
	
	public ListDeviceDocAction(DeviceCardManager deviceCardManager,
			ApplicationDocManager applicationDocManager,
			FileTransportManager fileTransportManager) {
		this.deviceCardManager = deviceCardManager;
		this.applicationDocManager = applicationDocManager;
		this.fileTransportManager = fileTransportManager;
	}
	
	public void prepare() throws Exception {
		if (this.hasId("device.id")) {
			this.device = this.deviceCardManager.loadDevice(this.getId("device.id"));
			log.debug("device.doc size is " + device.getDeviceDoc().size());
		}
		
		if (this.hasId("deviceDocIds")) {
			this.deviceDocs = this.applicationDocManager.loadAllApplicationDocs(this.getIds("deviceDocIds"));
		}
		if (this.hasId("doc.id")) {
			doc = this.applicationDocManager.loadApplicationDoc(this.getId("doc.id"));
			this.download();
		}
		
		if (isDelete()) {
			this.delete();
		}
		
		this.setFirst(false);
	}
	
    private void delete() {
//		String path = this.request.getRealPath("/");
//		if(path.endsWith("\\")){
//			path = path.substring(0, path.length()-1);
//		}else if(path.endsWith("/")){
//			path = path.substring(0, path.length()-1);
//		}
//    	Iterator it = deviceDocs.iterator();
//    	while (it.hasNext()) {
//    		DeviceDoc deviceDoc = (DeviceDoc)it.next();
//    		File file = new File(path +  File.separator + deviceDoc.getPosition());
//    		if (file.isFile()) {
//    			file.delete();
//    		} 
//    	}
    	for (Iterator iter = deviceDocs.iterator(); iter.hasNext(); ) {
    		ApplicationDoc doc = (ApplicationDoc)iter.next(); 
    		this.fileTransportManager.delete(request, doc.getPosition());
    	}
    	this.device.getDeviceDoc().removeAll(this.deviceDocs);
    	this.deviceCardManager.storeDevice(device);    	
        this.addActionMessage(this.getText("deviceDoc.delete.success"));
    }
    
	public String download(){
		fileTransportManager.download(request, response, doc.getFileName(), doc.getPosition());
		return null;
	}
	
	public DeviceCard getDevice() {
		return this.device;
	}
	
	public void setDevice(DeviceCard device) {
		this.device = device;
	}
	
	@SuppressWarnings("unchecked")
	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
        if (this.hasId("device.id")){
        	map.put("device.id", this.getId("device.id"));
		}
		return map;
	}
	
	@Override
	protected String getAdapterName() {
		return "docRecord";
	}
}
