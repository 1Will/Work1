package main.util;

import java.io.IOException; 
import java.util.Properties; 
public class PropertiesUtils { 
	//����һ�����������ļ��Ķ��� 
static Properties prop = new Properties(); 
/** * 
* @param fileName ��Ҫ���ص�properties�ļ����ļ���Ҫ����src��Ŀ¼�� 
* @return �Ƿ���سɹ� 
*/ 
public static boolean loadFile(String fileName){ 
  try { 
	  prop.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(fileName));
  		} catch (IOException e) { 
  			e.printStackTrace(); 
  			return false; 
  		} 
  return true;
} 

/** 
* ����KEYȡ����Ӧ��value 
* @param key 
* @return 
*/
public static String getPropertyValue(String key){ 
	return prop.getProperty(key); 
	} 
}
