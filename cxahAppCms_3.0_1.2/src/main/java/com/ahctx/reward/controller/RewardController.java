package com.ahctx.reward.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahctx.reward.entity.RegisterUser;
import com.ahctx.reward.service.IRewardService;
import com.baomidou.framework.annotations.Log;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * <p>
 * 用户管理相关操作
 * </p>
 *
 *
 * @Author wangping
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/reward")
public class RewardController extends BaseController {

	@Autowired
	private IRewardService rewardService;

	@Permission("5001")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/reward/list";
	}

	@Log("查询用户报名信息")
	@ResponseBody
	@Permission("5001")
	@RequestMapping("/getRegUserList")
	public String getRegUserList(RegisterUser regUser, String startDate, String endDate, String dataComplete) {
		Page<RegisterUser> page = getPage();
		page.setRecords(rewardService.selectRegisterUser(regUser, startDate, endDate, dataComplete, page.getOffset(), page.getLimit()));
		page.setTotal(rewardService.getRegUserCount(regUser, startDate, endDate, dataComplete));
		return jsonPage(page);
	}

	/**
	 * 返回iReport报表视图
	 * @param model
	 * @return
	 */
	@Permission("5001")
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report(Model model, RegisterUser regUser, String startDate, String endDate, String dataComplete) {
		List<?> users = rewardService.selectAllRegisterUser(regUser, startDate, endDate, dataComplete);
		// 报表数据源
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(users);
		
		// 动态指定报表模板url
		model.addAttribute("url", "/WEB-INF/static/report/reg_user.jasper");
		model.addAttribute("format", "xls"); // 报表格式
		model.addAttribute("Content-Disposition","attachment; filename=reg_user.xls");
		model.addAttribute("jrMainDataSource", jrDataSource);
		
		return "iReportView"; // 对应jasper-defs.xml中的bean id
	}
}
