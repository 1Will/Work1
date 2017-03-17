package com.test.servlet;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@SuppressWarnings("serial")
public class HelloFreeMarkerServlet extends HttpServlet {
	// �������FreeMarkerģ���Configurationʵ��
	private Configuration cfg = null;

	public void init() throws ServletException {
		// ����һ��FreeMarkerʵ��
		cfg = new Configuration();
		// ָ��FreeMarkerģ���ļ���λ��
		cfg.setServletContextForTemplateLoading(getServletContext(),
				"/WEB-INF/templates");
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��������ģ��
		Map root = new HashMap();
		root.put("message", "hello world");
		root.put("name", "javaСǿ");
		// ��ȡģ���ļ�
		Template t = cfg.getTemplate("test.html");
		// ʹ��ģ���ļ���Charset��Ϊ��ҳ���charset
		// ʹ��text/html MIME-type
		response.setContentType("text/html; charset=" + t.getEncoding());
		Writer out = response.getWriter();
		// �ϲ�����ģ�ͺ�ģ�壬������������out��
		try {
			t.process(root, out); // ��ģ����д����
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void destroy() {
		super.destroy();
	}
}
