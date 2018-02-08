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
 * sql���ݿ������
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
	 * ��ȡ���ݿ�����
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
	 * �ͷ�JDBC��Դ
	 * 
	 * @param conn ���ݿ�����
	 * @param ps
	 * @param rs ��¼��
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
	 * ���������Ϣ
	 * 
	 */
	public static int saveQingJia(TleaveBill bill) {
		int id=0;//�������ɵ�ID  
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
			// �ͷ���Դ
			sqlUtil.releaseResources(conn, ps, rs);
		}
		return id;
	}
	
	
	/**
	 * ���������Ϣ
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
	 * ��ȡ�����Ϣ����
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bill;
	}
	
	
	/**
	 * �����û�����ȡ�û���Ϣ
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * ���� LOGIN_NAME ��ȡ�û���Ϣ
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersInfo;
	}
	
	/**
	 * �����û���Ϣ����΢��openid
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
	 * ���������Ϣ
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
			// �ͷ���Դ
			sqlUtil.releaseResources(conn, ps, rs);
		}
		return num;
	}
	
	/**
	 * ���� ΢����ʷ�¼���Ϣ ��ʱ���2����֮ǰ������
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
			// �ͷ���Դ
			sqlUtil.releaseResources(conn, ps, rs);
		}
	}
	
	/**
	 * ���� ΢����ʷ�¼���Ϣ ���ڴ浵�˹���ѯ
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
			// �ͷ���Դ
			sqlUtil.releaseResources(conn, ps, rs);
		}
	}
	
	/**
	 * ������Ϣ�б� �����Ķ�״̬
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
	 * ��ȡ�û���Ϣ����
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * ��ȡְλ��Ϣ����
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return duty;
	}

	/**
	 * ��ȡ������Ϣ����
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}
	
	
	/**
	 * ����openid��ȡ�û���Ϣ
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * ��ȡ�������
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * �������Id,�������
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	/**
	 * �������Code,���ʵ��
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codeValue;
	}
	
	/**
	 * ����codeValueId ��ȡcodeValueName
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * ��ȡ�û���
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * ����projectId��ȡ��Ŀ��Ϣ
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
	 * ����customerId��ȡ�ͻ���Ϣ
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
				map.put("maorContact", rs.getString("MAOR_CONTACT")); //��Ҫ��ϵ��
				map.put("businessMan", rs.getString("BUSINESSMAN"));  //ҵ��Ա
				map.put("businessScope", rs.getString("BUSINESS_SCOPE"));  //ҵ��Χ
				map.put("advisoryContent", rs.getString("ADVISORY_CONTENT")); //��ѯ����
				
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
	 * ����contactId��ȡ��ϵ����Ϣ
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
				map.put("contactName", rs.getString("NAME")); //��ϵ��
				map.put("customerName", rs.getString("CUST_NAME")); //�ͻ�����
				map.put("creatorMan", rs.getString("CREATOR"));  //������
				map.put("enterpriseSynopsis", rs.getString("ENTERPRISE_SYNOPSIS"));  //ӡ������
				
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
	 * ����personnelFilesId��ȡ���±���Ϣ
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
				personnelFiles.setName(rs.getString("NAME")); //����  ����
				personnelFiles.setCode(rs.getString("CODE")); //���
				personnelFiles.setDeptId(rs.getLong("DEPT_ID")); //����id
				
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
	 * ����userid��ȡ �û�code �ٻ�ȡ���±�id
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
				personnelFiles.setName(rs.getString("NAME")); //����  ����
				personnelFiles.setCode(rs.getString("CODE")); //���
				
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
	 * ����contractManagementId��ȡ��ͬ��Ϣ
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
				map.put("CManagementName", rs.getString("CONTRACTNAME")); //��ͬ����
				map.put("customerId", rs.getString("CUSTOMERINFO_ID")); //�ͻ�id
				map.put("linkManId", rs.getString("LINKMAN"));  //��ϵ��id
				map.put("salemanId", rs.getString("SALEMAN_ID"));  //ҵ��Աid
				SimpleDateFormat format1=new SimpleDateFormat("yyyy��MM��dd��");
				map.put("ciemdingTime",format1.format( rs.getDate("CIEMDINGHTIME") ) );  //ǩ������  ������������ڸ�ʽ��
				
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
	 * ����financialManagementId��ȡ�տ���Ϣ
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
				map.put("CManagementId", rs.getString("CONTRACT_MANAGEMENT_ID")); //��ͬID
				map.put("customerId", rs.getString("CUSTOMERINFO_ID")); //�ͻ�id
				map.put("salemanId", rs.getString("SALEMAN_ID"));  //������id
				map.put("sumReceivable", rs.getString("SUM_RECEIVABLE"));  //Ӧ�ս��
				map.put("trueSum", rs.getString("TRUE_SUM"));  //ʵ�ս��
				SimpleDateFormat format1=new SimpleDateFormat("yyyy��MM��dd��  ");
				map.put("collectionDate",format1.format( rs.getDate("COLLECTION_DATE") ) );  //�տ����� �ǿ�  ������������ڸ�ʽ��
				
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
	 * ����productsId��ȡ�տ���Ϣ  
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
				map.put("productName", rs.getString("name")); //��Ʒ����
				map.put("model", rs.getString("model")); //�ͺ�
				map.put("isNoMain", rs.getString("isNoMain"));  //�Ƿ���Ӫ
				map.put("remark", rs.getString("remark")); // ����
				SimpleDateFormat format1=new SimpleDateFormat("yyyy��MM��dd��");
				map.put("launch",format1.format( rs.getDate("launch") ) );  //�Ƴ�ʱ��  �ǿ�  ������������ڸ�ʽ��
				
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
	 * ����t_product_type ��ȡ��Ʒ������Ϣ  
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
				ptName= rs.getString("name"); //��Ʒ���
				
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
	 * ����t_supplier ��ȡ��Ӧ����Ϣ  
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
				supplier= rs.getString("name"); //��˾����
				
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
	 * ����weeklyId��ȡ�ܼƻ���Ϣ  
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
				map.put("weeklyName", rs.getString("NAME")); //�ܼƻ�����
				map.put("startDate", format.format(rs.getDate("STARTDATE")) ); //��ʼ����
				map.put("endDate", format.format(rs.getDate("ENDDATE")) ); //��������
				map.put("rapporteurId", rs.getString("RAPPORTEUR_ID"));  //�ƻ���
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
	 * ����projectInfoPlanId��ȡ�����ƻ���Ϣ  
	 * 20170801
	 */
	public static Map<String,String> getProjectInfoPlanById(int id){
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select * from t_projectInfoPlan  where Id="+id;
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			SimpleDateFormat format =new SimpleDateFormat("yyyy��MM��dd��");
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				map.put("planName", rs.getString("name")); //�ƻ�����
				map.put("creator", rs.getString("CREATOR") ); //����������id
				map.put("outline", rs.getString("outline")); // �ƻ�����
				map.put("planState", rs.getString("planState")); // �ƻ�״̬ l
				map.put("percent", rs.getString("PERCENTT")); // ��ɰٷֱ�
		        // ��ȡ����������
				cal1.setTime(rs.getDate("start_Date"));
				int day1 =	cal1.get(Calendar.DAY_OF_YEAR);
				cal2.setTime(rs.getDate("end_Date"));
				int day2 =	cal2.get(Calendar.DAY_OF_YEAR);
				map.put("dayNum", (day2-day1+1)+"��"); // ��ʼʱ��
				map.put("endDate", format.format(rs.getDate("end_Date"))); // ����ʱ��
			
	//			map.put("startDate", rs.getDate("start_Date")); // ��ʼʱ��
				
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
	 * ����projectInfoPlanId��ȡ�����ƻ�ʵ��  
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
				projectInfoPlan.setName(rs.getString("name")); //�����ƻ�����
				
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
	 * ����projectId��ȡ��Ŀʵ��  
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
				projectInfo.setProjectName(rs.getString("NAME")); ; //��Ŀ����
				
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
	 * ����billingRecordId��ȡ��Ʊ��Ϣ  
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
				map.put("ContractManagementId", rs.getString("CONTRACT_MANAGEMENT_ID")); //��ͬID
				map.put("customerInfoId", rs.getString("CUSTOMERINFO_ID")); //�ͻ�ID
				map.put("sum", rs.getString("SUM") ); //��Ʊ���
				map.put("payeeId", rs.getString("PAYEE_ID")); // ��Ʊ�� ���±� 
				map.put("invoiceTitle", rs.getString("INVOICE_TITLE")); // ��Ʊ̧ͷ
				map.put("content", rs.getString("CONTENT")); // ��Ʊ���� 
				
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
	 * ����paymentorderId��ȡ��Ʊ��Ϣ  
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
				map.put("contractManagementId", rs.getString("contractManagement_id")); //��ͬID
				map.put("totalMoney", rs.getString("TOTALMONEY") ); //������
				map.put("supplierId", rs.getString("SUPPLIER") ); // ������id
				map.put("paymentPersionId", rs.getString("PAYMENTPERSION")); // ��Ʊ�� ���±� 
				map.put("remark", rs.getString("REMARK") ); // ��ע
				
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
	 * ����expenseFormId��ȡ  ���ñ�����Ϣ  
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
				map.put("projectId", rs.getString("PROJECTINFO_ID")); //��ĿID
			//	map.put("applyDate", rs.getString("APPLY_DATE") ); //��������
				map.put("personnelId", rs.getString("APPLY_PEOPLE") ); // ������Id
				map.put("money", rs.getString("MONEY")); // �������
				map.put("remark", rs.getString("REMARK") ); // ��ע
				
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
	 * ����overTimeBillId��ȡ  �Ӱ൥��Ϣ  
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
				map.put("applyPersonId", rs.getString("APPLY_PERSON_ID")); //��ĿID
				map.put("betreffzeile", rs.getString("betreffzeile") ); // ������Id
				
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
	 * ����onTheRoadBillId��ȡ  ��������Ϣ  
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
				map.put("applyPersonId", rs.getString("APPLY_PERSON_ID")); //������ID
				map.put("betreffzeile", rs.getString("betreffzeile") ); // ����ԭ��
				
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
	 * ����weekPlanId��ȡ ��Ŀ�ܼƻ���Ϣ  
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
	 * ����supplierId  ��ȡ��������Ϣ  
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
				map.put("supplierName", rs.getString("NAME") ); //��Ӧ������
				map.put("maorContact", rs.getString("MAOR_CONTACT") ); //��Ҫ��ϵ��
				map.put("mobile", rs.getString("MOBILE") ); //	�ֻ���
				
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
	 * @param ��ѯ�ط���Ϣ 
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
				map.put("customerId", rs.getString("CUSTOMER_ID"));    //��ȡ�ͻ�id
				map.put("contactName", rs.getString("CONTACT_NAME"));
				map.put("contactId", rs.getString("CONTACT_ID"));    //��ȡ��ϵ��id
				map.put("visitor", rs.getString("VISITOR"));
				map.put("visitContent", rs.getString("VISIT_CONTENT"));
				map.put("laterAtten", rs.getString("LATER_ATTEN"));
				map.put("projectName", rs.getString("projectName"));

				map.put("projectInfoId", rs.getString("projectInfo_id"));    //��ȡ��Ŀid

				map.put("state", rs.getString("customer_Stating"));
				map.put("feed", rs.getString("CUSTOMER_FEED"));
				map.put("remark", rs.getString("REMARKS"));
				Date  date = rs.getDate("VISIT_DATE");
				if (date!=null) {
					map.put("visitDate", format1.format(date)); 
				} else {
					map.put("visitDate", ""); 
                     
				}
				Date nextVisitDate = rs.getDate("NEXT_VISIT_DATE"); //�´λط�ʱ��
				if (nextVisitDate!=null) {
					map.put("nextVisitDate", format1.format(nextVisitDate)); 
				} else {
					map.put("nextVisitDate", ""); 
                     
				}
				map.put("employees", rs.getString("employees")); //�ط���ͬ����
				map.put("expendTime", rs.getString("EXPEND_TIME"));  // ��ʱ���֣�
				map.put("replyTime", rs.getString("REPLY_TIME"));  // ��¼�طô���
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * ��ȡ�ձ���Ϣ
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
				map.put("weekDate", rs.getString("WEEKDATE"));//��¼����  not null
				map.put("backvisitContext", rs.getString("BACKVISITCONTEXT"));//�ݷù������� 
				map.put("workContext", rs.getString("WORKCONTEXT"));//������������  not null
				map.put("questions", rs.getString("QUESTIONS"));//����  �ջ񡢸���
				map.put("rapporteurId", rs.getString("RAPPORTEUR_ID"));//��д�� ID user��  not null
				map.put("deptId", rs.getString("DEPT_ID"));//����ID ���ű�  not null
				map.put("dutyId", rs.getString("DUTY_ID"));//ְλID ְλ��  not null
				
				map.put("creator", rs.getString("CREATOR"));//������ user�� lf
				Date date = rs.getDate("CURRENTDATE"); //��ǰ����  not null
				map.put("currentDate", format1.format(date));
				
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * ��ȡ ��������ʵ������Ϣ
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
				map.put("submitPerId", rs.getString("submitPer"));//��¼����  not null
				Date date = rs.getDate("createTime"); //��ǰ����  not null
				map.put("createTime", format1.format(date));
				
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	/**
	 * ��ȡ �����������Ϣ
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
				map.put("inspectPserId", rs.getString("inspectPser"));//������ ���±�id
				Date date = rs.getDate("CREATED_TIME"); //��ǰ����  not null
				map.put("createTime", format1.format(date));
		      }
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * ��ȡ �������� ��Ϣ
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * ��ȡ�û���Ϣ����
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
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	/**
	 * ��ȡ΢���ձ�֪ͨ��
	 * 5.19 
	 * subiao
	 */
	public static List<String> getWXGroupsUserId() {
		List<String> list1=new ArrayList<String>();
		String sql = "select user_id from t_group_user where group_id in(select id from t_groups where name='΢���ձ�֪ͨ��')";
		try {
			Connection conn = new SQLUtil().getConn();
			PreparedStatement ps = conn.prepareStatement(sql);
			//ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			for (;rs.next();) {
				 list1.add(rs.getString("USER_ID"));
			     continue ;
			}
			// �ͷ���Դ
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list1;
	}
	
	
	
}
