package com.ahctx.reward.common.util.http;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

public class HttpUtil {

	public static HttpMethod getMethod(String url, String param)
			throws IOException {
		GetMethod get = new GetMethod(url + "?" + param);
		get.releaseConnection();
		return get;
	}

	public static HttpMethod postMethod(String url, NameValuePair[] param) throws IOException {
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		post.setRequestBody(param);
		post.releaseConnection();
		return post;
	}
	
	public static String RMC(NameValuePair[] param, String host, int port, String callMethod) {
		HttpClient httpClient = new HttpClient();
		httpClient.getHostConfiguration().setHost(
				host, port, "http");
		HttpConnectionManagerParams managerParams = httpClient
				.getHttpConnectionManager().getParams();
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(5000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(5000);
		try {
			HttpMethod method = HttpUtil.postMethod(callMethod, param);
			httpClient.executeMethod(method);
			String data = method.getResponseBodyAsString();
			System.out.println("同步返回--->" + data);
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

// 如果PostMethod提交的是中文字符，需要加上相应的编码格式：
// post.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");
// 如果GetMethod提交的参数有中文字符，需要先转换成utf-8格式： URLEncoder.encode("杭州", "utf-8");
