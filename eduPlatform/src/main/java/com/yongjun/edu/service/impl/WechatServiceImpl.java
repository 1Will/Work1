package com.yongjun.edu.service.impl;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.yongjun.edu.enums.WechatParame;
import com.yongjun.edu.service.WechatService;
import com.yongjun.edu.util.WechatUtil;

@Component
public class WechatServiceImpl implements WechatService {

	private WechatUtil shalUtil= new WechatUtil();

	@Override
	public boolean checkSignature(String signature, String timestamp, String nonce) {
		// 1、排序
		String[] arr = new String[] { WechatParame.token, timestamp, nonce };
		Arrays.sort(arr);
		// 2、生成新的字符串
		StringBuffer content = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		// 3、shal加密
		String temp = shalUtil.getSha1(content.toString());
		return temp.equals(signature);
	}

}
