package souvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.pojo.Duty;
import souvc.pojo.Code;
import souvc.pojo.TleaveBill;
import souvc.pojo.UserInfo;

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
		String sql = "insert into t_leavebill(TITLE,CREATE_DATE,APPLY_PERSON_ID,DEPT_ID,START_TIME,END_TIME,MAN_HOUR,TYPE,BETREFFZEILE,SHENHE,DISABLED,VERSION,STATUS) values(?, ?, ?, ?, ?,?,?,?,?,?,?,?,?)";
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
			ps.setInt(11, Integer.parseInt(bill.getStatus()));
			ps.setInt(12, 0);
			ps.setInt(13, 0);
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
	 * 获取用户信息详情
	 * 
	 */
	public static UserInfo getUserDetail(int id) {
		UserInfo user = null;
		String sql = "select * from t_users where id=?";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserInfo();
				user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setName(rs.getString("NAME"));
				//user.setCompany(rs.getString("COMPANY"));
				//user.setPosition(rs.getString("POSITION"));
				//user.setBusiness(rs.getString("BUSINESS"));
				//user.setSex(rs.getString("SEX"));
				//user.setHobby(rs.getString("HOBBY"));
				//user.setEmail(rs.getString("EMAIL"));
				//user.setConstellation(rs.getString("CONSTELLATION"));
				//user.setLocale(rs.getString("LOCALE"));
				//user.setTel(rs.getString("TEL_NUMBER"));
				user.setOpenid(rs.getString("OPENID"));
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
	 * 获取用户名
	 * 
	 */
	public static String getUserName(int id) {
		String name="";
		String sql = "select * from t_users where id=?";
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
	
	
	
	public static Map<String,String> getBackVisit(int id){
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_backvisit where Id="+id;
		try {
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
				Date date = rs.getDate("VISIT_DATE");
				map.put("visitDate", format1.format(date));
				Date nextVisitDate = rs.getDate("NEXT_VISIT_DATE"); //下次回访时间
				map.put("nextVisitDate", format1.format(nextVisitDate)); 
				map.put("employees", rs.getString("employees")); //回访人同行者
				map.put("expendTime", rs.getString("EXPEND_TIME"));  // 耗时（分）
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
	 * 获取用户信息详情
	 * 
	 */
	public static UserInfo getAllUserId() {
		UserInfo user = null;
		String sql = "select * from t_users ";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserInfo();
				//user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setId(rs.getString("Id"));
				//user.setCompany(rs.getString("COMPANY"));
				//user.setPosition(rs.getString("POSITION"));
				//user.setBusiness(rs.getString("BUSINESS"));
				//user.setSex(rs.getString("SEX"));
				//user.setHobby(rs.getString("HOBBY"));
				//user.setEmail(rs.getString("EMAIL"));
				//user.setConstellation(rs.getString("CONSTELLATION"));
				//user.setLocale(rs.getString("LOCALE"));
				//user.setTel(rs.getString("TEL_NUMBER"));
				//user.setOpenid(rs.getString("OPENID"));
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
	
	
	
}
