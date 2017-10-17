package com.github.wp.business.controller.contract;


import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.business.pojo.qiandan.Hcontractmanagement;
import com.github.wp.business.service.contract.ContractService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysUser;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.annotation.CurrentUser;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;


/**
 * 合同管理控制器
 * @author dupeng
 * @version 1.0
 * @since 2016年8月12日
 */
@Controller
@RequestMapping("/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	/*@Autowired
	private DatadicService datadicService;*/

	/**
	 * 跳转到列表界面
	 * @param model
	 * @param response
	 * @return 跳转页面地址
	 * @author dupeng
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, HttpServletResponse response) {
		return "business/contract/info/list";
	}
	
	/**
	 * 查询合同信息列表
	 * @param Hcontractmanagement
	 * @param pagination
	 * @return 分页对象数据
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/contractList")
	public Pager<Hcontractmanagement> titleList(Hcontractmanagement contract, Pagination pagination) {
		Pager<Hcontractmanagement> list = contractService.findPage(contract, pagination);
		
		return list;
		
	}
	
	@RequestMapping(value = "/contractAdd")
	public String contractAdd(Model model, HttpServletResponse response){
		Hcontractmanagement contract = new Hcontractmanagement();
		/*List<SysDatadic> qyxzList = datadicService.findChildByCodingname("QYXZ");//根据编码得到--企业性质--列表
		List<SysDatadic> khztList = datadicService.findChildByCodingname("KHZT");//根据编码得到--客户状态--列表
		List<SysDatadic> khdjList = datadicService.findChildByCodingname("KHDJ");//根据编码得到--客户等级--列表
		List<SysDatadic> xxlyList = datadicService.findChildByCodingname("XXLY");//根据编码得到--信息来源--列表
*/		model.addAttribute("contract", contract);
		
		/*model.addAttribute("qyxzList", qyxzList);
		model.addAttribute("khztList", khztList);
		model.addAttribute("khdjList", khdjList);
		model.addAttribute("xxlyList", xxlyList);*/
		
		return "business/contract/info/add";
	}
	
	/**
	 * @author 杜鹏
	 * @param model
	 * @param Hcontractmanagement
	 * @return map
	 * @since 08-10-16
	 */
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.CONTRACT_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Model model, Hcontractmanagement contract,@CurrentUser SysUser user) {
		if(contract.getId() != null && !contract.getId().equals("")){
			contract.setLastModifiedTime(new Timestamp(System.currentTimeMillis()));
			contract.setLastOperator(user.getUsername());
		}else{
			contract.setCreatedTime(new Timestamp(System.currentTimeMillis()));
			contract.setCreator(user.getUsername());
		}
		contractService.saveOrUpdate(contract);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CONTRACT_SAVEORUPDATE_SUCESS.getInfo());
		return map;
	}
	
	/**
	 * 根据id假删除合同信息
	 * @param id
	 * @return map
	 * @author 杜鹏
	 */
	@ResponseBody
	@RequestMapping(value = "/delete")
	@SystemLog(description=UserOperationLog.CONTRACT_DELETEONE)
	public Map<String, Object> deleteOne(@RequestParam("ids[]") Long[] ids) {
		contractService.deleteOne(ids);
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.CONTRACT_DELETEONE_SUCESS.getInfo());
	    return map;
	}
	
	/**
	 * 进入合同信息修改界面
	 * @param model
	 * @return 界面url
	 * @author 杜鹏
	 */
	@RequestMapping(value = "/contractEdit")
	@SystemLog(description=UserOperationLog.CONTRACT_ADD)
	public String customEdit(Model model,Long id) {
		Hcontractmanagement contract = contractService.findOne(id);
		/*List<SysDatadic> qyxzList = datadicService.findChildByCodingname("QYXZ");//根据编码得到--企业性质--列表
		List<SysDatadic> khztList = datadicService.findChildByCodingname("KHZT");//根据编码得到--客户状态--列表
		List<SysDatadic> khdjList = datadicService.findChildByCodingname("KHDJ");//根据编码得到--客户等级--列表
		List<SysDatadic> xxlyList = datadicService.findChildByCodingname("XXLY");//根据编码得到--信息来源--列表
*/		model.addAttribute("contract", contract);
		
		/*model.addAttribute("qyxzList", qyxzList);
		model.addAttribute("khztList", khztList);
		model.addAttribute("khdjList", khdjList);
		model.addAttribute("xxlyList", xxlyList);*/
		return "business/contract/info/add";
	}

}
