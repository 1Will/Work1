package com.github.wp.business.controller.baseinfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.wp.factory.FactoryProvider;
import com.github.wp.factory.io.DownloadFileFactory;
import com.github.wp.factory.io.UploadFileFactory;
import com.github.wp.system.service.UserService;
import com.github.wp.system.util.io.DownloadFile;
import com.github.wp.system.util.io.SuperFileItem;
import com.github.wp.system.util.io.UploadFile;
import com.github.wp.system.web.spring.aspectj.SystemLog;
import com.github.wp.system.web.spring.aspectj.SystemLog.UserOperationLog;



/**
 * 公共Controller，其他模块中要调用的功能，比如用户选择。
 * @author 杜鹏
 * @version 1.0
 * @param <RadarProlocation>
 * @since 07-26-2016
 */
@Controller
@RequestMapping("/common")
public class CommonController<RadarProlocation> {

	@Autowired
	private UserService userService;

	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model) {
		return "common/dropzone";
	}
	
	/**
	 * 跳转到用户选择页面
	 * @param model
	 * @param response
	 * @return 跳转地址
	 * @author 杜鹏
	 */
	@RequestMapping("/selectUserList")
	public String selectUserList(Model model, HttpServletResponse response) {
		return "common/selectUserList";
	}
	
	/**
	 * 附件上传
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = "/addAttach")
	public String addAttach(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FactoryProvider fp = new UploadFileFactory();
		UploadFile uf = (UploadFile) fp.produce();
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String PATH = "uploadFiles\\";
		String apath = savePath+PATH;
		uf.setFilePath(apath);
		uf.doUpload(request);
		List<SuperFileItem> fileItems = uf.getSuperFileItem();
		for (SuperFileItem fileItem : fileItems) {
			System.out.println("上传名称" + fileItem.getName() + ",保存路径："
					+ fileItem.getSavedFilePath() + "保存名称："
					+ fileItem.getSavedFileName());
			//System.out.println("-----2222------"+fileItem.getFieldName());
		}
		return "chenggong!";
	}
	
	public List<SuperFileItem> addAttachCommon(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		FactoryProvider fp = new UploadFileFactory();
		UploadFile uf = (UploadFile) fp.produce();
		String savePath = request.getSession().getServletContext().getRealPath("/");
		String PATH = "uploadFiles\\";
		String apath = savePath+PATH;
		uf.setFilePath(apath);
		uf.doUpload(request);
		List<SuperFileItem> fileItems = uf.getSuperFileItem();
		return fileItems;
	}
	
	/**
	 * 下载
	 * @param response
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/download")
	public void download(HttpServletResponse response, String saveLocation,String fileName)
			throws Exception {
		
		FactoryProvider fp = new DownloadFileFactory();
		DownloadFile df = (DownloadFile) fp.produce();
		if (saveLocation == null || "".equals(saveLocation) || fileName == null || "".equals(fileName)) {
			throw new Exception();
		}
		else {
			df.doDownload(response, saveLocation,fileName, false);
		}
	}
	
}
