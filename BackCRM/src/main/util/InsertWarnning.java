package main.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import main.pojo.EventNew;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.Session;

public class InsertWarnning {
	public SimpleDateFormat formatter ;
	public Logger logger;
	private Session session;
	
	public InsertWarnning(Session session){
		this.session = session;
	}
	
	public void insert(EventNew event) {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger = Logger.getRootLogger();
		logger.info(formatter.format(new Date()) + ": 开始添加Warnning！");

		String moreInfo = event.getMoreinfo();// 得到传递的参数，解析成map
		Map<String, Object> map = new HashMap<String, Object>();
		if (moreInfo != null && !moreInfo.equals("")) {
			JSONObject jsonObject = JSONObject.fromObject(moreInfo);
			for (Iterator<?> iter = jsonObject.keys(); iter.hasNext();) {
				String key = (String) iter.next();
				map.put(key, jsonObject.get(key));
			}
		}
		
		String url= (String) map.get("url");
		String content = (String) map.get("content");
		String name = (String) map.get("name");
		String u = (String) map.get("users");
		String users = u.replaceAll(" ", ""); // 不管是否有空格，强制去除
		String[] ulist = users.split(",");

		String sql = "";
		if(ulist!=null && ulist.length>0){
			if (url != null) {
				for (int i = 0; i < ulist.length; i++) {
					sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID,URL,NAME) "
							+ "values (0,'"+ event.getName()+ "','"+ event.getName()+ "','"+ formatter.format(new Date()) + "',0," + ulist[i] + ",null,'" + url + "','" +name+ "');";
					session.createSQLQuery(sql).executeUpdate();
				}
			} else {
				if (content != null) {
					name = event.getName();
					for (int i = 0; i < ulist.length; i++) {
						sql = "insert into dbo.t_work_warnning (VERSION,TYPE,CONTENT,WARNNING_DATE,READ_FLAG,WARNED_PEOPLE_ID,REMIND_OBJECT_ID,URL,NAME) "
								+ "values (0,'"+ event.getName()+ "','"+ content+ "','"+ formatter.format(new Date())+ "',0," + ulist[i] + ",null,'','"+ name +"');";
						session.createSQLQuery(sql).executeUpdate();
					}
				}
			}
		}
		logger.info(formatter.format(new Date()) + ": 添加Warnning结束！");
	}

}
