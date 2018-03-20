package com.ahctx.reward.common.util.http;

import javax.servlet.http.HttpServletRequest;

public class PreventLink {

	public static boolean isOutLink(HttpServletRequest request) {

		String referer = request.getHeader("Referer");
		// 如果链接出自地址栏的输入，则跳转至本站点RequestAndResponse应用的首页
		if (referer == null) {
			return true;
		}
		// 如果链接出自本站点的RequestAndResponse应用的页面，则正常显示，如果是出自其他站点或本站点的其他应用，则跳转至本站点RequestAndResponse应用的首页
		if (!referer.startsWith("http://wjf.ah122.cn") && !referer.startsWith("http://weixin.ah122.cn")
				&& !referer.startsWith("http://www.ah122.cn")) {
			return true;
		} else {
			return false;
		}
	}
}
