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
package com.yongjun.pluto.presentation.webwork;

import java.io.File;
import java.io.InputStream;

import com.yongjun.pluto.webwork.action.PrepareAction;

/**
 * @author qs
 * @version $Id: FileTransportAction.java 8316 2007-11-15 03:46:02Z qsun $
 */
public abstract class FileTransportAction extends PrepareAction {
	private File file;
	private InputStream inputStream;
	private String contentType;
	private String fileName;
	private String fileContentType;
	private String fileFileName;
	private String fileUUID;
	
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileContentType() {
		return fileContentType;
	}
	
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
	public String getFileFileName() {
		return fileFileName;
	}
	
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
	public String getFileUUID() {
		return fileUUID;
	}
	
	public void setFileUUID(String fileUUID) {
		this.fileUUID = fileUUID;
	}
}
