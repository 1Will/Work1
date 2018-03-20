package com.ahctx.reward.common.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CommonUtil {

	public static Map<String, Object> Object2Map(Object obj) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		PropertyDescriptor pd = null;
		Method method = null;
		for (int i = 0; i < obj.getClass().getDeclaredFields().length; i++) {
			String FieldName = obj.getClass().getDeclaredFields()[i].getName();
			if (FieldName.equals("serialVersionUID"))
				continue;
			pd = new PropertyDescriptor(FieldName, obj.getClass());
			method = pd.getReadMethod();
			Object v = method.invoke(obj);
			map.put(FieldName, v);
		}
		return map;
	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1).toUpperCase();
			}
		}
		return null;
	}

	// 该方法等同于Integer.toBinaryString(b)

	public static String byte2bits(byte b) {

		int z = b;
		z |= 256;
		String str = Integer.toBinaryString(z);
		int len = str.length();
		return str.substring(len - 8, len);
	}

	// 将二进制字符串转换回字节

	public static byte bit2byte(String bString) {
		byte result = 0;
		for (int i = bString.length() - 1, j = 0; i >= 0; i--, j++) {
			result += (Byte.parseByte(bString.charAt(i) + "") * Math.pow(2, j));
		}
		return result;
	}
}
