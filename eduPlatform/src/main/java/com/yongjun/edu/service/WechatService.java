package com.yongjun.edu.service;

public interface WechatService {

	boolean checkSignature(String signature, String timestamp, String nonce);

}
