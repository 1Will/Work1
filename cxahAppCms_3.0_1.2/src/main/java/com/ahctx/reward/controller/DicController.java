package com.ahctx.reward.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahctx.reward.common.Constants;
import com.ahctx.reward.entity.CmsDic;
import com.ahctx.reward.service.ICmsDicService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 字典管理相关操作
 * </p>
 *
 *
 * @Author wangping
 * @Date 2016/4/15 15:03
 */
@Controller
@RequestMapping("/basedata/dic")
public class DicController extends BaseController {

	@Autowired
	private ICmsDicService cmsDicService;
	
	@Permission("6001")
	@RequestMapping()
	public String list(Model model) {
		return "/dic/list";
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/getList/{id}")
	public String getList(@PathVariable Long id) {
		Page<CmsDic> page = getPage();
		CmsDic cmsDic = new CmsDic();
		cmsDic.setPid(id);
		Page<CmsDic> list = cmsDicService.selectPage(page, cmsDic, "sort asc");
		return jsonPage(list);
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/getNodes")
	public String getNodes(Model model, Long id) {
		if(id == null ) id = Constants.DIC_ROOT;
		CmsDic condition = new CmsDic();
		condition.setPid(id);
		List<CmsDic> list = cmsDicService.selectList(condition, "sort asc");
		List<Map<String,String>> mapLists = new ArrayList<Map<String,String>>();
		for(CmsDic r : list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("id", r.getId().toString());
			map.put("pid", r.getPid().toString());
			map.put("name", r.getName());
			//默认展开树
			condition.setPid(r.getId());
			List<CmsDic> childs = cmsDicService.selectList(condition);
			if(childs.size() > 0) {
			    map.put( "isParent", "true");
			} else {
				map.put( "isParent", "false");
			}
			mapLists.add(map);
		}
		return JSONObject.toJSONString(mapLists);
	}
	
	@Permission("6001")
	@RequestMapping("/add/{id}")
	public String add(Model model, @PathVariable Long id) {
		model.addAttribute("pid", id);
		return "/dic/add";
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/addCmsDic")
	public String addCmsDic(CmsDic cmsDic) {
		boolean rlt = cmsDicService.insert(cmsDic);
		return callbackSuccess(rlt);
	}
	
	@Permission("6001")
	@RequestMapping("/update/{id}")
	public String update(Model model, @PathVariable Long id) {
		CmsDic cmsDic = cmsDicService.selectById(id);
		model.addAttribute("cmsDic", cmsDic);
		return "/dic/update";
	}
	
	@Permission("6001")
	@RequestMapping("/updateTable/{id}")
	public String updateTable(Model model, @PathVariable Long id) {
		CmsDic cmsDic = cmsDicService.selectById(id);
		model.addAttribute("cmsDic", cmsDic);
		return "/dic/update-table";
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/updateCmsDic")
	public String updateCmsDic(CmsDic cmsDic) {
		boolean rlt = cmsDicService.updateById(cmsDic);
		return callbackSuccess(rlt);
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/delCmsDic/{id}")
	public String delCmsDic(@PathVariable Long id) {
		boolean rlt = cmsDicService.deleteById(id);
		return callbackSuccess(rlt);
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/updateRelation")
	public String updateRelation(Long nodeId, Long targetId) {
		CmsDic cmsDic = cmsDicService.selectById(nodeId);
		cmsDic.setPid(targetId);
		boolean rlt = cmsDicService.updateById(cmsDic);
		return callbackSuccess(rlt);
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/isSaveUnique")
	public String isSaveUnique(CmsDic cmsDic) {
		List<CmsDic> cmsDics = cmsDicService.selectList(cmsDic);
		Map<String, String> map = new HashMap<String, String>();
		if (cmsDics.size() > 0) {
			map.put("error", "该编码重复");
		} else {
			map.put("ok", "该编码可用");
		}
		return callbackSuccess(map);
		
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/isUpdateUnique")
	public String isUpdateUnique(CmsDic cmsDic) {
		List<CmsDic> cmsDics = cmsDicService.selectList(cmsDic);
		Map<String, String> map = new HashMap<String, String>();
		if (cmsDics.size() > 1) {
			map.put("error", "该编码重复");
		} else {
			map.put("ok", "该编码可用");
		}
		return callbackSuccess(map);
	}
	
}
