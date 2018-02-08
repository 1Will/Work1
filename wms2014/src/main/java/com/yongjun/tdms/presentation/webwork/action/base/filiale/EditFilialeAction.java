package com.yongjun.tdms.presentation.webwork.action.base.filiale;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;

import org.apache.commons.lang.StringUtils;


import com.yongjun.pluto.model.security.Filiale;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.exception.ErroSaveException;
import com.yongjun.tdms.service.base.filiale.FilialeManager;

/**
 * <p>
 * Title: EditFilialeAction
 * <p>
 * Description: 分公司页面访问控制类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2001 yj-technology
 * </p>
 * <p>
 * Company: www.yj-technology.com
 * </p>
 * 
 * @author mfzhang@yj-technology.com
 * @version
 */
public class EditFilialeAction extends PrepareAction {
	private static final long serialVersionUID = -5670040722519229105L;

	private final FilialeManager filialeManager;

	private Filiale filiale;

	public EditFilialeAction(FilialeManager filialeManager) {
		this.filialeManager = filialeManager;
	}
	
	/**
	 * 初始化，获取参数<b>filiale.id</b>，如果存在就获取这个分公司实体， 如果不存在，就新建一个分公司实体
	 */
	public void prepare() throws Exception {
		if (null == this.filiale) {
			if (this.hasId("filiale.id")) {
				this.filiale = this.filialeManager.loadFiliale(this
						.getId("filiale.id"));
			} else {
				this.filiale = new Filiale();
/** 隐藏域解决之丢失问题的方法		
				//获取文本框的值
				if(null == request.getParameter("filiale.codeHidden")){
					this.filiale = new Filiale();
				}else{//输入重复时，获取隐藏域的值
					this.filiale = new Filiale();
					对中文进行解码
					filiale.setCode(URLDecoder.decode(request.getParameter("filiale.codeHidden"),"utf-8"));
					filiale.setName(URLDecoder.decode(request.getParameter("filiale.nameHidden"),"utf-8"));
					filiale.setLeader(URLDecoder.decode(request.getParameter("filiale.leaderHidden"),"utf-8"));
					filiale.setTel(request.getParameter("filiale.telHidden"));
					filiale.setAddress(URLDecoder.decode(request.getParameter("filiale.addressHidden"),"utf-8"));
					filiale.setComment(URLDecoder.decode(request.getParameter("filiale.commentHidden"),"utf-8"));
				}
*/
			}
		}
	}

	/**
	 * 删除一个分公司
	 * 
	 * @return String SUCCESS
	 */
	public String delete() {
		this.filialeManager.deleteFiliale(this.filiale);
		this.addActionMessage(this.getText("filiale.delete.success", Arrays
				.asList(new Object[] { filiale.getName() })));
		return SUCCESS;
	}
	
	/**
	 * 保存一个分公司信息
	 * 
	 * @return String SUCCESS 或者 NEW
	 */
	public String save()  {

/**	隐藏域解决值丢失的方法	
 * 对中文进行编码
		try {
			if(!StringUtils.isEmpty(request.getParameter("filiale.code"))){
				filiale.setCode(URLEncoder.encode(request.getParameter("filiale.code"), "utf-8"));
			}	
			if(!StringUtils.isEmpty(request.getParameter("filiale.name"))){
				filiale.setName(URLEncoder.encode(request.getParameter("filiale.name"), "utf-8"));
			}
			if(!StringUtils.isEmpty(request.getParameter("filiale.leader"))){
				filiale.setLeader(URLEncoder.encode(request.getParameter("filiale.leader"), "utf-8"));
			}
//			if(!StringUtils.isEmpty(request.getParameter("filiale.tel"))){
//				filiale.setTel(URLEncoder.encode(request.getParameter("filiale.tel"), "utf-8"));
//			}
			if(!StringUtils.isEmpty(request.getParameter("filiale.address"))){
				filiale.setAddress(URLEncoder.encode(request.getParameter("filiale.address"), "utf-8"));
			}
			if(!StringUtils.isEmpty(request.getParameter("filiale.comment"))){
				filiale.setComment(URLEncoder.encode(request.getParameter("filiale.comment"), "utf-8"));
			}
		} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		}
*/			
		
		
		
		boolean isNew = this.filiale.isNew();
		try{
	        try {
	            this.filialeManager.storeFiliale(this.filiale);
	        } catch (Exception e) {
	        	throw new ErroSaveException();
	        }
		} catch (ErroSaveException e) {
	        e.printStackTrace();
			this.addActionMessage(this.getText("filiale.code.exists", Arrays
					.asList(new Object[] { filiale.getCode() })));
//			try {
//				this.addActionMessage(this.getText("filiale.code.exists", Arrays
//						.asList(new Object[] { URLDecoder.decode(filiale.getName(),"utf-8") })));
//			} catch (UnsupportedEncodingException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	        return ERROR;
		}


		if (isNew) {
			this.addActionMessage(this.getText("filiale.add.success", Arrays
					.asList(new Object[] { filiale.getName() })));
			return NEW;
		} else {
			this.addActionMessage(this.getText("filiale.edit.success",
					Arrays.asList(new Object[] { filiale.getName() })));
			return SUCCESS;
		}
	}
	
	public Filiale getFiliale() {
		return filiale;
	}

	public void setFiliale(Filiale filiale) {
		this.filiale = filiale;
	}

}
