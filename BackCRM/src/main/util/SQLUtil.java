package main.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.pojo.Code;
import main.pojo.CodeValue;
import main.pojo.Department;
import main.pojo.Duty;
import main.pojo.News;
import main.pojo.NewsLog;
import main.pojo.PersonnelFiles;
import main.pojo.ProjectInfo;
import main.pojo.ProjectInfoPlan;
import main.pojo.TleaveBill;
import main.pojo.UserInfo;
import main.pojo.UsersInfo;

/**
 * sql数据库操作类
 */
public class SQLUtil {
	private static String URL = null;
	private static String USER = null;
	private static String PWD = null;
	private static String DRIVER = null;
	
	static {
		PropertiesUtils.loadFile("db.properties");
		URL = PropertiesUtils.getPropertyValue("url");
		USER = PropertiesUtils.getPropertyValue("username");
		PWD = PropertiesUtils.getPropertyValue("password");
		DRIVER = PropertiesUtils.getPropertyValue("driverClassName");

		}
	/**
	 * 获取数据库连接
	 * @return Connection
	 */
	
	public Connection getConn() {
		Connection conn=null;
		
		try{
			Class.forName(DRIVER).newInstance();
			String url=URL;
			String user=USER; 
			String password=PWD; 
			conn= DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * 释放JDBC资源
	 * 
	 * @param conn 数据库连接
	 * @param ps
	 * @param rs 记录集
	 */
	public void releaseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (null != rs)
				rs.close();
			if (null != ps)
				ps.close();
			if (null != conn)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 保存请假信息
	 * 
	 */
	public static int saveQingJia(TleaveBill bill) {
		int id=0;//保存生成的ID  
		String sql = "insert into t_leavebill(TITLE,CREATE_DATE,APPLY_PERSON_ID,DEPT_ID,START_TIME,END_TIME,MAN_HOUR,TYPE,BETREFFZEILE,SHENHE,DISABLED,VERSION,STATUS,CREATED_TIME,CREATOR,LAST_MODIFIED_TIME,LAST_OPERATOR,ORG_ID) values(?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, bill.getTitle());
			ps.setDate(2, new java.sql.Date(bill.getCreateDate().getTime()));
			ps.setInt(3, Integer.parseInt(bill.getPersonId()));
			ps.setInt(4, Integer.parseInt(bill.getDeptId()));
			ps.setDate(5, new java.sql.Date(bill.getStartDate().getTime()));
			ps.setDate(6, new java.sql.Date(bill.getEndDate().getTime()));
			ps.setFloat(7, bill.getMainHour());
			ps.setInt(8, Integer.parseInt(bill.getType()));
			ps.setString(9, bill.getBetreffzeile());
			ps.setString(10, bill.getShenhe());
			ps.setInt(11, 0);
			ps.setInt(12, 0);
			ps.setInt(13, Integer.parseInt(bill.getStatus()));
            ps.setDate(14,  new java.sql.Date(bill.getCreatedTime().getTime()));
            ps.setString(15, bill.getCreator());
            ps.setDate(16,  new java.sql.Date(bill.getLastOperatorTime().getTime()));
			ps.setString(17, bill.getLastOperator());
			ps.setInt(18, bill.getOranaration());
			
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();  
			if (rs != null&&rs.next()) {  
			    id=rs.getInt(1);  
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			sqlUtil.releaseResources(conn, ps, rs);
		}
		return id;
	}
	
	
	/**
	 * 更新请假信息
	 * 
	 */
	public static int updateQingJia(TleaveBill bill,int id) {
		String sql = "update t_leavebill set FAIL_REASON ='"+bill.getBohui()+"',STATUS="+bill.getStatus()+" where ID = "+id;
		int i = 0;
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}


	/**
	 * 获取请假信息详情
	 * 
	 */
	public static TleaveBill getQingJia(int id) {
		TleaveBill bill = null;
		String sql = "select APPLY_PERSON_ID,DEPT_ID,START_TIME,END_TIME,MAN_HOUR,TYPE,BETREFFZEILE,SHENHE,DISABLED,FAIL_REASON,STATUS from t_leavebill where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				bill = new TleaveBill();
				bill.setPersonId(rs.getInt("APPLY_PERSON_ID")+"");
				bill.setDeptId(rs.getInt("DEPT_ID")+"");
				bill.setStartDate(rs.getDate("START_TIME"));
				bill.setEndDate(rs.getDate("END_TIME"));
				bill.setMainHour(rs.getFloat("MAN_HOUR"));
				bill.setType(rs.getString("TYPE"));
				bill.setBetreffzeile(rs.getString("BETREFFZEILE"));
				bill.setShenhe(rs.getString("SHENHE"));
				bill.setBohui(rs.getString("FAIL_REASON"));
				bill.setStatus(rs.getString("STATUS"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bill;
	}
	
	
	/**
	 * 根据用户名获取用户信息
	 * 
	 */
	public static List<UserInfo> getUser(String name) {
		List<UserInfo> list = new ArrayList<UserInfo>();
		String sql = "select ID,LOGIN_NAME,NAME from t_users where LOGIN_NAME like '%"+name+"%' or tel_number like '%"+name+"%'";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserInfo user = new UserInfo();
				user.setName(rs.getString("NAME"));
				user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setId(String.valueOf(rs.getInt("ID")));
				list.add(user);
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据 LOGIN_NAME 获取用户信息
	 * 6.02
	 */
	public static UsersInfo getUserByLoginName(String loginName) {
		UsersInfo usersInfo=null;
		String sql = "select NAME from t_users where LOGIN_NAME like '%"+loginName+"%' ";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				usersInfo=new UsersInfo();
				usersInfo.setName(rs.getString("NAME"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersInfo;
	}
	
	/**
	 * 更新用户信息，绑定微信openid
	 * 
	 */
	public static int updateUser(String openid,int id) {
		String sql = "update t_users set OPENID ='"+openid+"' where ID = "+id;
		int i = 0;
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	/**
	 * 保存请假信息
	 * 
	 */
	public static int saveUserInfo(UserInfo user) {
		int num = (int)((Math.random()*9+1)*100000);
		String sql = "insert into t_users(ID,LOGIN_NAME,NAME,TEL_NUMBER,COMPANY,POSITION,BUSINESS,HOBBY,EMAIL,LOCALE,SEX,CONSTELLATION) values(?, ?, ?, ?, ?,?,?,?,?,?,?,?)";
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, user.getLoginName());
			ps.setString(3, user.getName());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getCompany());
			ps.setString(6, user.getPosition());
			ps.setString(7, user.getBusiness());
			ps.setString(8, user.getHobby());
			ps.setString(9, user.getEmail());
			ps.setString(10, user.getLocale());
			ps.setString(11, user.getSex());
			ps.setString(12, user.getConstellation());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			sqlUtil.releaseResources(conn, ps, rs);
		}
		return num;
	}
	
	/**
	 * 保存 微信历史事件信息 定时清除2个月之前的数据
	 * 
	 */
	public static void saveNews(News news) {
		String sql = "insert into t_news(eventName,URL,TIME,userid,CURRENTDATE,isRead,mainContent) values( ?, ?, ?,?,?,?,?)";
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, news.getEventName());
			ps.setString(2, news.getUrl());
			ps.setFloat(3, news.getTime());
			ps.setFloat(4, news.getUserid());
			ps.setString(5, news.getCurrentDate());
			ps.setString(6, news.getIsRead());
			ps.setString(7, news.getMainContent());
	
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			sqlUtil.releaseResources(conn, ps, rs);
		}
	}
	
	/**
	 * 保存 微信历史事件信息 用于存档人工查询
	 * 
	 */
	
	public static void saveNewsLog(NewsLog newsLog) {
		String sql = "insert into t_newslog (eventName,URL,TIME,userid,CURRENTDATE,isRead,mainContent) values( ?, ?, ?,?,?,?,?)";
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, newsLog.getEventName());
			ps.setString(2, newsLog.getUrl());
			ps.setFloat(3, newsLog.getTime());
			ps.setFloat(4, newsLog.getUserid());
			ps.setString(5, newsLog.getCurrentDate());
			ps.setString(6, newsLog.getIsRead());
			ps.setString(7, newsLog.getMainContent());
			
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			sqlUtil.releaseResources(conn, ps, rs);
		}
	}
	
	/**
	 * 更新消息列表 更改阅读状态
	 * 
	 */
	public static int updateNews(int id) {
		String sql = "update t_news set isRead ='1' where ID = "+id;
		int i = 0;
		SQLUtil sqlUtil = new SQLUtil();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = sqlUtil.getConn();
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	
	/**
	 * 获取用户信息详情
	 * 
	 */
	public static UserInfo getUserDetail(int id) {
		UserInfo user = null;
		String sql = "select LOGIN_NAME,NAME,OPENID,DEPARTMENT_ID from t_users where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserInfo();
				user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setName(rs.getString("NAME"));
	
				user.setOpenid(rs.getString("OPENID"));
				user.setDeptid(rs.getString("DEPARTMENT_ID"));
				
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 获取职位信息详情
	 * 
	 */
	public static Duty getDutyById(Long id) {
		Duty duty = null;
		String sql = "select * from t_duty where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				duty = new Duty();
				duty.setName(rs.getString("NAME"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return duty;
	}

	/**
	 * 获取部门信息详情
	 * 
	 */
	public static Department getDeptById(Long id) {
		Department dept = null;
		String sql = "select NAME from t_department where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				dept = new Department();
				dept.setName(rs.getString("NAME"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
	
	
	/**
	 * 根据openid获取用户信息
	 * 
	 */
	public static UserInfo getUserByOpenid(String openid) {
		UserInfo user = new UserInfo();
		String sql = "select ID,NAME,DEPARTMENT_ID,ORGANIZATION_ID,INSTITUSTION_ID,LOGIN_NAME,CODE from t_users where OPENID = '"+openid+"'";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(String.valueOf(rs.getInt("ID")));
				user.setDeptid(String.valueOf(rs.getInt("DEPARTMENT_ID")));
				user.setName(rs.getString("NAME"));
				user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setCode(rs.getString("CODE"));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 获取请假类型
	 * 
	 */
	public static List<Code> getType() {
		List<Code> list = new ArrayList<Code>();
		String sql = "select ID,NAME from t_codevalue where CV_ID=223";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Code code = new Code();
				code.setId(rs.getInt("ID")+"");
				code.setValue(rs.getString("NAME"));
				list.add(code);
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据码表Id,获得名称
	 * 
	 */
	public static String getNameById(String id) {
		String name = "";
		String sql = "select NAME from t_codevalue where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("NAME");
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * 根据码表Code,获得实体
	 * 
	 */
	public static CodeValue getCodeValueByCode(String Code) {
		CodeValue codeValue=new CodeValue();
		String sql = "select * from t_codevalue where CODE='"+Code+"'";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				codeValue.setId(Long.parseLong(rs.getString("Id")));
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codeValue;
	}
	
	/**
	 * 根据codeValueId 获取codeValueName
	 * 
	 */
	public static String getCodeValueName(int id) {
		String name="";
		String sql = "select NAME from t_codevalue where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("NAME");
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 获取用户名
	 * 
	 */
	public static String getUserName(int id) {
		String name="";
		String sql = "select NAME from t_users where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString("NAME");
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * 根据projectId获取项目信息
	 * 
	 */
	public static Map<String,String> getProject(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_projectinfo where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("name", rs.getString("NAME"));
				map.put("code", rs.getString("code"));
				map.put("customerInfoId", rs.getString("customerInfo_id"));
				map.put("contactId", rs.getString("CONTACT_ID"));
				map.put("projectControllerId", rs.getString("project_Controller_id"));
				map.put("creator", rs.getString("CREATOR"));
				map.put("createdTime", rs.getString("CREATED_TIME"));
				map.put("introduce", rs.getString("project_outline"));
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据customerId获取客户信息
	 * 20170711
	 */
	public static Map<String,String> getCustomerInfo(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_customerinfo where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("name", rs.getString("NAME"));
				map.put("code", rs.getString("code"));
				map.put("maorContact", rs.getString("MAOR_CONTACT")); //主要联系人
				map.put("businessMan", rs.getString("BUSINESSMAN"));  //业务员
				map.put("businessScope", rs.getString("BUSINESS_SCOPE"));  //业务范围
				map.put("advisoryContent", rs.getString("ADVISORY_CONTENT")); //咨询内容
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 根据contactId获取联系人信息
	 * 20170714
	 */
	public static Map<String,String> getContactArchives(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_contactArchives  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("contactName", rs.getString("NAME")); //联系人
				map.put("customerName", rs.getString("CUST_NAME")); //客户名称
				map.put("creatorMan", rs.getString("CREATOR"));  //创建人
				map.put("enterpriseSynopsis", rs.getString("ENTERPRISE_SYNOPSIS"));  //印象描述
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据personnelFilesId获取人事表信息
	 * 20170714
	 */
	public static PersonnelFiles getPersonnelFilesById(int id){
		PersonnelFiles personnelFiles=null;
		String sql = "select * from t_personnelfiles  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				personnelFiles=new PersonnelFiles();
				personnelFiles.setId(rs.getLong("Id"));
				personnelFiles.setName(rs.getString("NAME")); //人事  姓名
				personnelFiles.setCode(rs.getString("CODE")); //编号
				personnelFiles.setDeptId(rs.getLong("DEPT_ID")); //部门id
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnelFiles;
	}

	/**
	 * 根据userid获取 用户code 再获取人事表id
	 * 20170714
	 *//*
	public static PersonnelFiles getPersonnelFilesIdByUserid(int id){
		PersonnelFiles personnelFiles=null;
		String sql = "select * from t_personnelfiles  where CODE=(select CODE from t_users where Id="+id+")";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				personnelFiles=new PersonnelFiles();
				personnelFiles.setId(rs.getLong("Id"));
				personnelFiles.setName(rs.getString("NAME")); //人事  姓名
				personnelFiles.setCode(rs.getString("CODE")); //编号
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnelFiles;
	}*/
	
	/**
	 * 根据contractManagementId获取合同信息
	 * 20170717
	 */
	public static Map<String,String> getContactManagement(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_contractManagement  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("CManagementName", rs.getString("CONTRACTNAME")); //合同名称
				map.put("customerId", rs.getString("CUSTOMERINFO_ID")); //客户id
				map.put("linkManId", rs.getString("LINKMAN"));  //联系人id
				map.put("salemanId", rs.getString("SALEMAN_ID"));  //业务员id
				SimpleDateFormat format1=new SimpleDateFormat("yyyy年MM月dd日");
				map.put("ciemdingTime",format1.format( rs.getDate("CIEMDINGHTIME") ) );  //签订日期  在这里进行日期格式化
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据financialManagementId获取收款信息
	 * 20170718
	 */
	public static Map<String,String> getFinancialManagement(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select  * from t_financial_management  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("CManagementId", rs.getString("CONTRACT_MANAGEMENT_ID")); //合同ID
				map.put("customerId", rs.getString("CUSTOMERINFO_ID")); //客户id
				map.put("salemanId", rs.getString("SALEMAN_ID"));  //负责人id
				map.put("sumReceivable", rs.getString("SUM_RECEIVABLE"));  //应收金额
				map.put("trueSum", rs.getString("TRUE_SUM"));  //实收金额
				SimpleDateFormat format1=new SimpleDateFormat("yyyy年MM月dd日  ");
				map.put("collectionDate",format1.format( rs.getDate("COLLECTION_DATE") ) );  //收款日期 非空  在这里进行日期格式化
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据productsId获取收款信息  
	 * 20170719
	 */
	public static Map<String,String> getProductsManagement(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_products  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("productName", rs.getString("name")); //产品名称
				map.put("model", rs.getString("model")); //型号
				map.put("isNoMain", rs.getString("isNoMain"));  //是否主营
				map.put("remark", rs.getString("remark")); // 概述
				SimpleDateFormat format1=new SimpleDateFormat("yyyy年MM月dd日");
				map.put("launch",format1.format( rs.getDate("launch") ) );  //推出时间  非空  在这里进行日期格式化
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * 根据t_product_type 获取产品类型信息  
	 * 20170719
	 */
	public static String getProductTypeManagement(long id){
		String ptName =null;
		String sql = "select * from  t_product_type  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ptName= rs.getString("name"); //产品类别
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ptName;
	}
	
	/**
	 * 根据t_supplier 获取供应商信息  
	 * 20170719
	 */
	public static String getSupplierManagement(long id){
		String supplier =null;
		String sql = "select * from  t_supplier  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				supplier= rs.getString("name"); //公司名称
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplier;
	}
	
	/**
	 * 根据weeklyId获取周计划信息  
	 * 20170728
	 */
	public static Map<String,String> getWeeklyById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_weekly  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("weeklyName", rs.getString("NAME")); //周计划名称
				map.put("startDate", format.format(rs.getDate("STARTDATE")) ); //开始日期
				map.put("endDate", format.format(rs.getDate("ENDDATE")) ); //结束日期
				map.put("rapporteurId", rs.getString("RAPPORTEUR_ID"));  //计划人
				map.put("summary", rs.getString("SUMMARY"));
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据projectInfoPlanId获取工作计划信息  
	 * 20170801
	 */
	public static Map<String,String> getProjectInfoPlanById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_projectInfoPlan  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat format =new SimpleDateFormat("yyyy年MM月dd日");
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("planName", rs.getString("name")); //计划名称
				map.put("creator", rs.getString("CREATOR") ); //负责人人事id
				map.put("outline", rs.getString("outline")); // 计划描述
				map.put("planState", rs.getString("planState")); // 计划状态 l
				map.put("percent", rs.getString("PERCENTT")); // 完成百分比
		        // 获取日期天数差
				cal1.setTime(rs.getDate("start_Date"));
				int day1 =	cal1.get(Calendar.DAY_OF_YEAR);
				cal2.setTime(rs.getDate("end_Date"));
				int day2 =	cal2.get(Calendar.DAY_OF_YEAR);
				map.put("dayNum", (day2-day1+1)+"天"); // 开始时间
				map.put("endDate", format.format(rs.getDate("end_Date"))); // 结束时间
			
	//			map.put("startDate", rs.getDate("start_Date")); // 开始时间
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 根据projectInfoPlanId获取工作计划实体  
	 * 20170830
	 */
	public static ProjectInfoPlan getProjectInfoPlanById(long id){
		ProjectInfoPlan projectInfoPlan=new ProjectInfoPlan();
		String sql = "select * from t_projectInfoPlan  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//	SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				projectInfoPlan.setName(rs.getString("name")); //工作计划名称
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectInfoPlan;
	}
	
	/**
	 * 根据projectId获取项目实体  
	 * 20170809
	 */
	public static ProjectInfo getProjectInfoById(long id){
		ProjectInfo projectInfo=new ProjectInfo();
		String sql = "select * from t_projectInfo  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//	SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				projectInfo.setProjectName(rs.getString("NAME")); ; //项目名称
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projectInfo;
	}

	/**
	 * 根据billingRecordId获取开票信息  
	 * 20170803
	 */
	public static Map<String,String> getBillingRecordById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_billingRecord  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//	SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("ContractManagementId", rs.getString("CONTRACT_MANAGEMENT_ID")); //合同ID
				map.put("customerInfoId", rs.getString("CUSTOMERINFO_ID")); //客户ID
				map.put("sum", rs.getString("SUM") ); //开票金额
				map.put("payeeId", rs.getString("PAYEE_ID")); // 开票人 人事表 
				map.put("invoiceTitle", rs.getString("INVOICE_TITLE")); // 开票抬头
				map.put("content", rs.getString("CONTENT")); // 发票内容 
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据paymentorderId获取开票信息  
	 * 20170803
	 */
	public static Map<String,String> getPaymentOrderById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_paymentorder  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("contractManagementId", rs.getString("contractManagement_id")); //合同ID
				map.put("totalMoney", rs.getString("TOTALMONEY") ); //付款金额
				map.put("supplierId", rs.getString("SUPPLIER") ); // 供货商id
				map.put("paymentPersionId", rs.getString("PAYMENTPERSION")); // 开票人 人事表 
				map.put("remark", rs.getString("REMARK") ); // 备注
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据expenseFormId获取  费用报销信息  
	 * 20170815
	 */
	public static Map<String,String> getExpenseFormById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_expenseForm  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("projectId", rs.getString("PROJECTINFO_ID")); //项目ID
			//	map.put("applyDate", rs.getString("APPLY_DATE") ); //申请日期
				map.put("personnelId", rs.getString("APPLY_PEOPLE") ); // 申请人Id
				map.put("money", rs.getString("MONEY")); // 报销金额
				map.put("remark", rs.getString("REMARK") ); // 备注
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	

	
	/**
	 * 根据overTimeBillId获取  加班单信息  
	 * 20170825
	 */
	public static Map<String,String> getOverTimeBillById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_overtimebill  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("applyPersonId", rs.getString("APPLY_PERSON_ID")); //项目ID
				map.put("betreffzeile", rs.getString("betreffzeile") ); // 申请人Id
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据onTheRoadBillId获取  公出单信息  
	 * 20170826
	 */
	public static Map<String,String> getOnTheRoadBillById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_onTheRoadBill  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("applyPersonId", rs.getString("APPLY_PERSON_ID")); //申请人ID
				map.put("betreffzeile", rs.getString("betreffzeile") ); // 申请原由
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 根据weekPlanId获取 项目周计划信息  
	 * 20170828
	 */
	public static Map<String,String> getWeekPlanById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_weekPlan  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("projectInfoId", rs.getString("PROJECTINFO_ID")); 
				map.put("userId", rs.getString("USER_ID") ); 
				map.put("thisPlan", rs.getString("THISPLAN") ); 
				map.put("weeklyId", rs.getString("WEEKLY_ID") ); 
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 根据supplierId  获取供货商信息  
	 * 20170803
	 */
	public static Map<String,String> getSupplierById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_supplier  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("supplierName", rs.getString("NAME") ); //供应商名称
				map.put("maorContact", rs.getString("MAOR_CONTACT") ); //主要联系人
				map.put("mobile", rs.getString("MOBILE") ); //	手机号
				
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * @param 查询回访信息 
	 * @return
	 */
	
	public static Map<String,String> getBackVisit(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_backvisit where Id="+id;
		try {
            SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");			
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("customName", rs.getString("CUSTOMER_NAME"));
				map.put("customerId", rs.getString("CUSTOMER_ID"));    //获取客户id
				map.put("contactName", rs.getString("CONTACT_NAME"));
				map.put("contactId", rs.getString("CONTACT_ID"));    //获取联系人id
				map.put("visitor", rs.getString("VISITOR"));
				map.put("visitContent", rs.getString("VISIT_CONTENT"));
				map.put("laterAtten", rs.getString("LATER_ATTEN"));
				map.put("projectName", rs.getString("projectName"));

				map.put("projectInfoId", rs.getString("projectInfo_id"));    //获取项目id

				map.put("state", rs.getString("customer_Stating"));
				map.put("feed", rs.getString("CUSTOMER_FEED"));
				map.put("remark", rs.getString("REMARKS"));
				Date  date = rs.getDate("VISIT_DATE");
				if (date!=null) {
					map.put("visitDate", format1.format(date)); 
				} else {
					map.put("visitDate", ""); 
                     
				}
				Date nextVisitDate = rs.getDate("NEXT_VISIT_DATE"); //下次回访时间
				if (nextVisitDate!=null) {
					map.put("nextVisitDate", format1.format(nextVisitDate)); 
				} else {
					map.put("nextVisitDate", ""); 
                     
				}
				map.put("employees", rs.getString("employees")); //回访人同行者
				map.put("expendTime", rs.getString("EXPEND_TIME"));  // 耗时（分）
				map.put("replyTime", rs.getString("REPLY_TIME"));  // 记录回访次数
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取日报信息
	 * 
	 */
	public static Map<String,String> getDaily(int id){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_daily where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("weekDate", rs.getString("WEEKDATE"));//记录星期  not null
				map.put("backvisitContext", rs.getString("BACKVISITCONTEXT"));//拜访工作内容 
				map.put("workContext", rs.getString("WORKCONTEXT"));//其他工作内容  not null
				map.put("questions", rs.getString("QUESTIONS"));//问题  收获、感想
				map.put("rapporteurId", rs.getString("RAPPORTEUR_ID"));//填写人 ID user表  not null
				map.put("deptId", rs.getString("DEPT_ID"));//部门ID 部门表  not null
				map.put("dutyId", rs.getString("DUTY_ID"));//职位ID 职位表  not null
				
				map.put("creator", rs.getString("CREATOR"));//创建人 user表 lf
				Date date = rs.getDate("CURRENTDATE"); //当前日期  not null
				map.put("currentDate", format1.format(date));
				
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 获取 审批任务实体类信息
	 *  subiao   20180112
	 */
	public static Map<String,String> getRunTask(int id){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd  hh:mm");
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_RunTask where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("flowId", rs.getString("FLOW"));  
				map.put("submitPerId", rs.getString("submitPer"));//记录星期  not null
				Date date = rs.getDate("createTime"); //当前日期  not null
				map.put("createTime", format1.format(date));
				
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * 获取 审批流结果信息
	 *  subiao   20180112
	 */
	
	public static Map<String,String> getRunPoint(int id){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd  hh:mm");
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_RunPoint where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("id", rs.getString("Id"));
				map.put("flowId", rs.getString("FLOW"));  
				map.put("resultId", rs.getString("result"));  
				map.put("remark", rs.getString("REMARK"));  
				map.put("inspectPserId", rs.getString("inspectPser"));//审批人 人事表id
				Date date = rs.getDate("CREATED_TIME"); //当前日期  not null
				map.put("createTime", format1.format(date));
		      }
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 获取 审批类型 信息
	 *  subiao   20180112
	 */
	
	public static Map<String,String> getPoint(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select NAME from t_flow where Id="+id;
		try {
			
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("name", rs.getString("NAME"));  
				
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * 获取用户信息详情
	 * 
	 */
	public static UserInfo getAllUserId() {
		UserInfo user = null;
		String sql = "select Id from t_users ";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserInfo();
				//user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setId(rs.getString("Id"));
		
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	/**
	 * 获取微信日报通知组
	 * 5.19 
	 * subiao
	 */
	public static List<String> getWXGroupsUserId() {
		List<String> list1=new ArrayList<String>();
		String sql = "select user_id from t_group_user where group_id in(select id from t_groups where name='微信日报通知组')";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			for (;rs.next();) {
				 list1.add(rs.getString("USER_ID"));
			     continue ;
			}
			// 释放资源
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list1;
	}
	
	
	
}
