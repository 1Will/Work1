package com.github.wp.business.controller.baseinfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.business.pojo.product.Product;
import com.github.wp.business.service.baseinfo.ProductService;
import com.github.wp.system.Constants;
import com.github.wp.system.pojo.SysDatadic;
import com.github.wp.system.service.DatadicService;
import com.github.wp.system.util.common.Pager;
import com.github.wp.system.util.common.Pagination;
import com.github.wp.system.web.bind.method.JsonUtil;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;

@Controller
@RequestMapping("/baseinfo/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private DatadicService datadicService;
	
	@RequestMapping()
	@SystemLog(description=UserOperationLog.LOG_LIST)
	@RequiresPermissions(Constants.SysPermission.LOG_LIST)
	public String list() {
		return "business/baseinfo/product/list";
	}
	
	@ResponseBody
	@RequestMapping(value = "/productListDG")
	@RequiresPermissions(Constants.SysPermission.USER_USERLISTDG)
	public Pager<Product> productListDG(Product product, Pagination pagination) {
		Pager<Product> list = productService.findPage(product, pagination);
		return list;
	}
	/**
	 * 跳转到新增产品对话框
	 * @param model
	 * @return 跳转界面地址
	 * @author wangping
	 */
	@RequestMapping(value = "/productAdd")
	@SystemLog(description=UserOperationLog.USER_USERADD)
	@RequiresPermissions(Constants.SysPermission.USER_USERAdd)
	public String userAdd(Model model) {
		model.addAttribute("prodccut", new Product());
		return "business/baseinfo/product/productAdd";
	}
	/**
	 * 产品来源combotree
	 * @param model
	 * @return 产品来源数据
	 * @author byt
	 */
	@ResponseBody
	@RequestMapping(value = "/cplyCombotree")
	@RequiresPermissions(Constants.SysPermission.USER_ORGCOMBOTREE)
	public List<Map<String, Object>> cplyCombotree() {
		List<SysDatadic> sysDatadics = datadicService.findChildByCodingname("CPLY");
		List<Map<String, Object>> map = JsonUtil.getAsynDatadicTree("CPLY",
				sysDatadics);
		return map;
	}
	/**
	 * 产品类型combotree
	 * @param model
	 * @return 产品来源数据
	 * @author byt
	 */
	@ResponseBody
	@RequestMapping(value = "/cplxCombotree")
	@RequiresPermissions(Constants.SysPermission.USER_ORGCOMBOTREE)
	public List<Map<String, Object>> cplxCombotree() {
		List<SysDatadic> sysDatadics = datadicService.findChildByCodingname("CPLX");
		List<Map<String, Object>> map = JsonUtil.getAsynDatadicTree("CPLX",
				sysDatadics);
		return map;
	}
	@ResponseBody
	@RequestMapping(value = "/saveOrUpdate")
	@RequiresPermissions(Constants.SysPermission.DIC_SAVEORUPDATE)
	public Map<String, Object> saveOrUpdate(Product product) {
		productService.saveOrUpdate(product);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.Others.RESPONSE_VERSION.value(), product.getVersion());
		map.put(Constants.Others.RESPONSE_MSG.value(), Constants.UserNotice.DATADIC_SAVEORUPDATE_SUCCESS.getInfo());
		return map;
	}
	
	/**
	 * 跳转到更新产品界面
	 * @param id
	 * @param model
	 * @return 更新用户界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
	@SystemLog(description=UserOperationLog.USER_UPDATE)
	@RequiresPermissions(Constants.SysPermission.USER_UPDATE)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productService.getProductByid(id));
		return "business/baseinfo/product/productAdd";
	}
	
	
	/**
	 * 删除产品，同时跳转到产品列表界面
	 * @param id
	 * @param redirectAttributes
	 * @return 用户列表界面路径
	 * @author wangping
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@SystemLog(description=UserOperationLog.USER_DELETE)
	@RequiresPermissions(Constants.SysPermission.USER_DELETE)
	public String delete(@RequestParam("id[]") Long[] id) {
		productService.deleteProduct(id);
		return "redirect:/baseinfo/product";
	}
	
}
