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
package com.yongjun.pluto.service.file;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author qs
 * @version $Id: FileTransportManager.java 9835 2007-12-27 07:46:24Z qsun $
 */
public interface FileTransportManager {
	/**
	 * 下载文件
	 * @param request	
	 * @param response 
	 * @param fileName 文件名，真实文件名
	 * @param position 文件名，UUID后的文件名
	 */
	public void download(HttpServletRequest request, HttpServletResponse response, String fileName, String position);
	
	/**
	 * 上传文件
	 * @param request
	 * @param file	上传文件
	 * @param sourceFileName	原始文件名称
	 * @return 文件名的UUID
	 * @throws Exception 
	 */
	public String upload(HttpServletRequest request, File file, String sourceFileName) throws Exception;
	
	/**
	 * 删除一个文件，包含数据库记录的删除，和磁盘上的物理删除
	 * @param request
	 * @param position	文件名，UUID后的文件名
	 */
	public void delete(HttpServletRequest request, String position);		
}
