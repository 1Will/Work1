package com.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.DBUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class LoadPlace
 */
public class LoadPlace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPlace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		DBUtils db = DBUtils.getInstance();
		db.getConnection();
		String word ="";
		if("province".equals(type)){
			String sql = "select * from provinces ";
			List<Map<String, Object>> province=null;
			List provinceid = new ArrayList<>();
			try {
				province = db.executeQuery(sql, provinceid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			word = JSONArray.fromObject(province).toString();
		}
		
		if("city".equals(type)){
			String sql = "select * from cities where provinceid = ?";
			List<Map<String, Object>> city=null;
			List provinceid = new ArrayList<>();
			provinceid.add(request.getParameter("provinceid"));
			try {
				city = db.executeQuery(sql, provinceid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			word = JSONArray.fromObject(city).toString();
		}
		if("area".equals(type)){
			String sql = "select * from areas where cityid = ?";
			List<Map<String, Object>> area=null;
			List cityid = new ArrayList<>();
			cityid.add(request.getParameter("cityid"));
			try {
				area = db.executeQuery(sql, cityid);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			word = JSONArray.fromObject(area).toString();
		}
		//System.out.println(word);
		response.getWriter().write(word);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
