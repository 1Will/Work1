package com.ahctx.reward.controller;

import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ahctx.reward.common.enums.OptionType;
import com.ahctx.reward.entity.CmsTraffic;
import com.ahctx.reward.service.ICmsTrafficService;
import com.baomidou.kisso.annotation.Permission;
import com.baomidou.mybatisplus.plugins.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <p>
 * 实时路况相关操作
 * </p>
 *
 *
 * @Author hs
 * @Date 2016-09-02
 */
@Controller
@RequestMapping("/traffic")
public class TrafficController extends BaseController {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private PrintWriter writer = null;
	
	@Autowired
	private ICmsTrafficService trafficService;

	@Permission("6001")
	@RequestMapping("/list")
	public String list(Model model) {
		return "/traffic/list";
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/getTrafficList")
	public String getTrafficList(CmsTraffic cmsTraffic) {
		Page<CmsTraffic> page = getPage();
		page.setRecords(trafficService.selectAllTrafficInfo(cmsTraffic, page.getOffset(), page.getLimit()));
		page.setTotal(trafficService.getTrafficCount(cmsTraffic));
		return jsonPage(page);
	}
	
	@Permission("6001")
    @RequestMapping("/edit")
    public String edit(Model model, Long id ) {
    	if ( id != null ) {
			model.addAttribute("traffic", trafficService.selectById(id));
		}
        return "/news/edit";
    }
	
	/**
	 * 逻辑删除实时路况记录，deleteFlag标记变为1
	 * 
	 * @param Id：路况记录编号
	 * 
	 * @author hs
	 * @date 2016-09-02
	 */
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/deleteByTrafficId/{Id}")
	public String deleteByTrafficId(@PathVariable Long Id) {
		trafficService.deleteByTrafficId(Id);
		return Boolean.TRUE.toString();
	}
	
	@ResponseBody
	@Permission("6001")
	@RequestMapping("/editTraffic")
	public String editTraffic( CmsTraffic traffic ) {
		boolean rlt = false;
		//判断实时路况实体是否为空，为空则不进行操作
		if ( traffic != null ) {
			//判断实时路况是否存在
			if ( traffic.getId() != null ) {
				//存在，更新信息
				traffic = getCommTrafficInfo(OptionType.OP_EDIT.getOp(),traffic); //更新状态下，实时路况实体的公共信息部分填充
				rlt = trafficService.updateById(traffic); //更新操作
			} else {
				//不存在，插入信息
				traffic = getCommTrafficInfo(OptionType.OP_INSERT.getOp(),traffic); //插入状态下，实时路况实体的公共信息部分填充
				rlt = trafficService.insertSelective(traffic); //插入操作
			}
		}
		return callbackSuccess(rlt);
	}
	
	/**
	 * 获取公共部分的实时路况信息
	 * 
	 * @param flag：操作标记（1：插入操作  2：更新操作）
	 * 		  news：要操作的新闻实体对象
	 * 
	 * @author hs
	 * @date 2016-09-01
	 */
	private CmsTraffic getCommTrafficInfo(int flag,CmsTraffic traffic){
		Date time = new Date(System.currentTimeMillis()); //获取当前时间
		//操作信息的添加
		//插入标记
		if(flag == 1){
			traffic.setCreateUser(getSSOToken().getData()); //添加创建用户
			traffic.setCreateTime(time); //添加当前时间作为创建时间
			
			traffic.setVersion(1);	//设置版本号
		}
		//更新标记
		if(flag == 2){
			traffic.setModifyUser(getSSOToken().getData()); //添加更新用户
			traffic.setModifyTime(time); //添加当前时间作为更新时间
		}
	
		return traffic;
	}
	/**
	 * 添加、修改上传
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@Permission("5001")
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public void file_upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ServletContext application = request.getSession().getServletContext();
		String savePath = application.getRealPath("/") + "upload/";
		// 文件保存目录URL
		String saveUrl = request.getContextPath() + "/upload/";

		// 定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("flash", "swf,flv");
		extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		// 最大文件大小
		long maxSize = 1000000;

		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		writer = response.getWriter();
		if (!ServletFileUpload.isMultipartContent(request)) {
			writer.println(objectMapper.writeValueAsString(getError("请选择文件。")));
			return;

		}
		// 检查目录
		File uploadDir = new File(savePath);
		if (!uploadDir.isDirectory()) {
			writer.println(objectMapper
					.writeValueAsString(getError("上传目录不存在。")));
			return;
		}
		// 检查目录写权限
		if (!uploadDir.canWrite()) {
			writer.println(objectMapper
					.writeValueAsString(getError("上传目录没有写权限。")));
			return;
		}

		String dirName = request.getParameter("dir");
		if (dirName == null) {
			dirName = "image";
		}
		if (!extMap.containsKey(dirName)) {
			writer.println(objectMapper.writeValueAsString(getError("目录名不正确。")));
			return;
		}
		// 创建文件夹
		savePath += dirName + "/";
		saveUrl += dirName + "/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		List<?> items = upload.parseRequest(request);
		Iterator<?> itr = items.iterator();
		while (itr.hasNext()) {
			FileItem item = (FileItem) itr.next();
			String fileName = item.getName();
			if (!item.isFormField()) {
				// 检查文件大小
				if (item.getSize() > maxSize) {
					writer.println(objectMapper
							.writeValueAsString(getError("上传文件大小超过限制。")));
					return;
				}
				// 检查扩展名
				String fileExt = fileName.substring(
						fileName.lastIndexOf(".") + 1).toLowerCase();
				if (!Arrays.<String> asList(extMap.get(dirName).split(","))
						.contains(fileExt)) {
					writer.println(objectMapper
							.writeValueAsString(getError("上传文件扩展名是不允许的扩展名。\n只允许"
									+ extMap.get(dirName) + "格式。")));
					return;
				}

				SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				String newFileName = df.format(new Date()) + "_"
						+ new Random().nextInt(1000) + "." + fileExt;
				try {
					File uploadedFile = new File(savePath, newFileName);
					item.write(uploadedFile);
				} catch (Exception e) {
					writer.println(objectMapper
							.writeValueAsString(getError("上传文件失败。")));
				}

				Map<String, Object> msg = new HashMap<String, Object>();
				msg.put("error", 0);
				msg.put("url", saveUrl + newFileName);
				writer.println(objectMapper.writeValueAsString(msg));

				return;
			}
		}
		return;
	}

	@SuppressWarnings("unchecked")
	@ResponseBody
	@Permission("5001")
	@RequestMapping(value = "/fileManager", method = RequestMethod.POST)
	public void file_manager(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ServletContext application = request.getSession().getServletContext();
		ServletOutputStream out = response.getOutputStream();
		// 根目录路径，可以指定绝对路径，比如 /var/www/upload/
		String rootPath = application.getRealPath("/") + "upload/";
		// 根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/upload/
		String rootUrl = request.getContextPath() + "/upload/";
		// 图片扩展名
		String[] fileTypes = new String[] { "gif", "jpg", "jpeg", "png", "bmp" };

		String dirName = request.getParameter("dir");
		if (dirName != null) {
			if (!Arrays.<String> asList(
					new String[] { "image", "flash", "media", "file" })
					.contains(dirName)) {
				out.println("Invalid Directory name.");
				return;
			}
			rootPath += dirName + "/";
			rootUrl += dirName + "/";
			File saveDirFile = new File(rootPath);
			if (!saveDirFile.exists()) {
				saveDirFile.mkdirs();
			}
		}
		// 根据path参数，设置各路径和URL
		String path = request.getParameter("path") != null ? request
				.getParameter("path") : "";
		String currentPath = rootPath + path;
		String currentUrl = rootUrl + path;
		String currentDirPath = path;
		String moveupDirPath = "";
		if (!"".equals(path)) {
			String str = currentDirPath.substring(0,
					currentDirPath.length() - 1);
			moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0,
					str.lastIndexOf("/") + 1) : "";
		}

		// 排序形式，name or size or type
		String order = request.getParameter("order") != null ? request
				.getParameter("order").toLowerCase() : "name";

		// 不允许使用..移动到上一级目录
		if (path.indexOf("..") >= 0) {
			out.println("Access is not allowed.");
			return;
		}
		// 最后一个字符不是/
		if (!"".equals(path) && !path.endsWith("/")) {
			out.println("Parameter is not valid.");
			return;
		}
		// 目录不存在或不是目录
		File currentPathFile = new File(currentPath);
		if (!currentPathFile.isDirectory()) {
			out.println("Directory does not exist.");
			return;
		}
		// 遍历目录取的文件信息
		List<Hashtable<String, Object>> fileList = new ArrayList<Hashtable<String, Object>>();
		if (currentPathFile.listFiles() != null) {
			for (File file : currentPathFile.listFiles()) {
				Hashtable<String, Object> hash = new Hashtable<String, Object>();
				String fileName = file.getName();
				if (file.isDirectory()) {
					hash.put("is_dir", true);
					hash.put("has_file", (file.listFiles() != null));
					hash.put("filesize", 0L);
					hash.put("is_photo", false);
					hash.put("filetype", "");
				} else if (file.isFile()) {
					String fileExt = fileName.substring(
							fileName.lastIndexOf(".") + 1).toLowerCase();
					hash.put("is_dir", false);
					hash.put("has_file", false);
					hash.put("filesize", file.length());
					hash.put("is_photo", Arrays.<String> asList(fileTypes)
							.contains(fileExt));
					hash.put("filetype", fileExt);
				}
				hash.put("filename", fileName);
				hash.put("datetime",
						new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file
								.lastModified()));
				fileList.add(hash);
			}
		}

		if ("size".equals(order)) {
			Collections.sort(fileList, new SizeComparator());
		} else if ("type".equals(order)) {

			Collections.sort(fileList, new TypeComparator());
		} else {
			Collections.sort(fileList, new NameComparator());

		}
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("moveup_dir_path", moveupDirPath);
		msg.put("current_dir_path", currentDirPath);
		msg.put("current_url", currentUrl);
		msg.put("total_count", fileList.size());
		msg.put("file_list", fileList);
		response.setContentType("application/json; charset=UTF-8");
		String msgStr = objectMapper.writeValueAsString(msg);
		out.println(msgStr);
	}

	private Map<String, Object> getError(String message) {
		Map<String, Object> msg = new HashMap<String, Object>();
		msg.put("error", 1);
		msg.put("message", message);
		return msg;
	}

	@SuppressWarnings("rawtypes")
	class NameComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filename"))
						.compareTo((String) hashB.get("filename"));
			}
		}
	}

	@SuppressWarnings("rawtypes")
	class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long) hashA.get("filesize")) > ((Long) hashB
						.get("filesize"))) {
					return 1;
				} else if (((Long) hashA.get("filesize")) < ((Long) hashB
						.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	class TypeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable) a;
			Hashtable hashB = (Hashtable) b;
			if (((Boolean) hashA.get("is_dir"))
					&& !((Boolean) hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean) hashA.get("is_dir"))
					&& ((Boolean) hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String) hashA.get("filetype"))
						.compareTo((String) hashB.get("filetype"));
			}
		}
	}

}
