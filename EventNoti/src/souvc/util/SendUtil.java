package souvc.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import souvc.pojo.TemplateData;
import souvc.pojo.WeixinUserInfo;
import souvc.pojo.WxTemplate;


public class SendUtil {

    /**

     * 连接请求微信后台接口

     * @param action 接口url

     * @param json  请求接口传送的json字符串

     */

    public static  void connectWeiXinInterface(String action,String json){

        URL url;

       try {

           url = new URL(action);

           HttpURLConnection http = (HttpURLConnection) url.openConnection();

           http.setRequestMethod("POST");

           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

           http.setDoOutput(true);

           http.setDoInput(true);

           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

           http.connect();

           OutputStream os = http.getOutputStream();

           os.write(json.getBytes("UTF-8"));// 传入参数

           InputStream is = http.getInputStream();

           int size = is.available();

           byte[] jsonBytes = new byte[size];

           is.read(jsonBytes);

           String result = new String(jsonBytes, "UTF-8");

           System.out.println("请求返回结果:"+result);

           os.flush();

           os.close();

       } catch (Exception e) {

           e.printStackTrace();

       }

    }
    
    
    /**

     * 获得ACCESS_TOKEN

     * 

     * @Title: getAccess_token

     * @Description: 获得ACCESS_TOKEN

     * @param @return 设定文件

     * @return String 返回类型

     * @throws

     */

    public static String getAccess_token() {
    	
    	String app_id = "wxd869d12157582551";
    	String app_secret = "c91907e07d6aab4f1eb37c2009080bb3";

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="

                + app_id+ "&secret=" + app_secret;

        String accessToken = null;

        try {

            URL urlGet = new URL(url);

            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

            http.setRequestMethod("GET"); // 必须是get方式请求

            http.setRequestProperty("Content-Type",

                    "application/x-www-form-urlencoded");

            http.setDoOutput(true);

            http.setDoInput(true);

            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

            http.connect();

            InputStream is = http.getInputStream();

            int size = is.available();

            byte[] jsonBytes = new byte[size];

            is.read(jsonBytes);

            String message = new String(jsonBytes, "UTF-8");

            JSONObject demoJson = JSONObject.fromObject(message);

            accessToken = demoJson.getString("access_token");

            System.out.println(accessToken);

            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return accessToken;

    }
    
    /**

     * 获取所有的关注用户

     * 

     * @return

     */

    public static List<String> getAllWeiXinUser() {

        String accessToken = SendUtil.getAccess_token();

        List<String> openIds = new ArrayList<String>();

        // 上传文件请求路径

        String action = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=" + accessToken;

        try {

            URL urlGet = new URL(action);

            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

            http.setRequestMethod("GET"); // 必须是get方式请求

            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            http.setDoOutput(true);

            http.setDoInput(true);

            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

            http.connect();

            InputStream is = http.getInputStream();

            int size = is.available();

            byte[] jsonBytes = new byte[size];

            is.read(jsonBytes);

            String result = new String(jsonBytes, "UTF-8");

            JSONObject jsonObj = JSONObject.fromObject(result);

            System.out.println("users" + jsonObj.get("data"));

            JSONObject json1 = JSONObject.fromObject(jsonObj.get("data").toString());

            System.out.println(json1.toString());

            JSONArray json2 =JSONArray.fromObject(json1.get("openid").toString());
            for (int i = 0; i < json2.size(); i++) {

                openIds.add(i, json2.getString(i));

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return openIds;

    }
    
    /**

     * 微信公共账号发送给账号

     * @param content 文本内容

     * @param toUser 微信用户  

     * @return

     */

    public static void sendTextMessageToUser(String content,String toUser){

       String json = "{\"touser\": \""+toUser+"\",\"msgtype\": \"text\", \"text\": {\"content\": \""+content+"\"}}";

       //获取access_token


       String accessToken = SendUtil.getAccess_token();

       //获取请求路径

       String action = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+accessToken;

       System.out.println("json:"+json);

       try {

           connectWeiXinInterface(action,json);

       } catch (Exception e) {

           e.printStackTrace();

       }

   }
    
    public static WeixinUserInfo getUserInfo(String fromUserName){
    	WeixinUserInfo user = new WeixinUserInfo();
    	String access_token = getAccess_token();
    	String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+fromUserName+"&lang=zh_CN";
    	try {

            URL urlGet = new URL(url);

            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();

            http.setRequestMethod("GET"); // 必须是get方式请求

            http.setRequestProperty("Content-Type",

                    "application/x-www-form-urlencoded");

            http.setDoOutput(true);

            http.setDoInput(true);

            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

            http.connect();

            InputStream is = http.getInputStream();

            int size = is.available();

            byte[] jsonBytes = new byte[size];

            is.read(jsonBytes);

            String message = new String(jsonBytes, "UTF-8");

            JSONObject demoJson = JSONObject.fromObject(message);
            
            user.setNickname(demoJson.getString("nickname"));
            user.setGroupid(demoJson.getString("groupid"));
            user.setOpenid(demoJson.getString("openid"));
            user.setHeadimgurl(demoJson.getString("headimgurl"));
            user.setSex(demoJson.getString("sex"));
            is.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
        return user;
    }
    
    
    public static JSONObject addMaterialEver(File file) throws Exception {  
        try {  
            System.out.println("开始上传图文消息内的图片---------------------");
              
            //开始获取证书  
            String accessToken=getAccess_token();
              
            //上传图片素材      
            String path="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+accessToken+"&type=image"; 
            String result=connectHttpsByPost(path, file);  
            result=result.replaceAll("[\\\\]", "");  
            System.out.println("result:"+result);      
            JSONObject resultJSON=JSONObject.fromObject(result);  
            if(resultJSON!=null){  
                if(resultJSON.get("url")!=null){  
                	System.out.println("上传图文消息内的图片成功---"+resultJSON.get("url"));  
                    return resultJSON;  
                }else{  
                	System.out.println("上传图文消息内的图片失败");  
                }  
            }  
              
            return null;  
        } catch (Exception e) {  
        	System.out.println("程序异常！"+e);
            throw e;  
        }finally{  
        	System.out.println("结束上传图文消息内的图片---------------------");   
        }  
    }
    
    public static String connectHttpsByPost(String url,File file) throws IOException{
    	String result =null;  
    	URL urlObj = new URL(url);  
    	HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
    	con.setDoInput(true);  
    	con.setDoOutput(true);  
    	con.setUseCaches(false); // post方式不能使用缓存  
    	// 设置请求头信息  
    	con.setRequestProperty("Connection", "Keep-Alive");  
    	con.setRequestProperty("Charset", "UTF-8");  
    	// 设置边界  
    	String BOUNDARY = "----------" + System.currentTimeMillis();  
    	con.setRequestProperty("Content-Type","multipart/form-data; boundary="+ BOUNDARY);  
    	// 请求正文信息  
    	// 第一部分：  
    	StringBuilder sb = new StringBuilder();  
    	sb.append("--"); // 必须多两道线  
    	sb.append(BOUNDARY);  
    	sb.append("\r\n");  
    	sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""  
    	        + file.getName() + "\"\r\n");  
    	sb.append("Content-Type:application/octet-stream\r\n\r\n");  
    	byte[] head = sb.toString().getBytes("utf-8");  
    	// 获得输出流  
    	OutputStream out = new DataOutputStream(con.getOutputStream());  
    	// 输出表头  
    	out.write(head);  
    	// 文件正文部分  
    	// 把文件已流文件的方式 推入到url中  
    	DataInputStream in = new DataInputStream(new FileInputStream(file));  
    	int bytes = 0;  
    	byte[] bufferOut = new byte[1024];  
    	while ((bytes = in.read(bufferOut)) != -1) {  
    	    out.write(bufferOut, 0, bytes);  
    	}  
    	in.close();  
    	// 结尾部分  
    	byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
    	out.write(foot);  
    	out.flush();  
    	out.close();  
    	StringBuffer buffer = new StringBuffer();  
    	BufferedReader reader = null;  
    	try {  
    	    // 定义BufferedReader输入流来读取URL的响应  
    	    reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
    	    String line = null;  
    	    while ((line = reader.readLine()) != null) {  
    	        buffer.append(line);  
    	    }  
    	    if (result == null) {  
    	        result = buffer.toString();  
    	    }  
    	} catch (IOException e) {  
    	    System.out.println("发送POST请求出现异常！" + e);  
    	    e.printStackTrace();  
    	} finally {  
    	    if (reader != null) {  
    	        reader.close();  
    	    }  
    	  
    	}  
    	return result;
    }

    /**
     * 发送模板消息
     * touser：发送对象的openid
     * url:跳转地址
     * template_id：模板id
     */
    public static void sendTemplateMessage(String openid,String url,String template_id){
    	
    	String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+SendUtil.getAccess_token();
    	String json = "{\"touser\":\""+openid+"\",\"template_id\":\""+template_id+"\",\"url\":\""+url+"\"}";
    	try {

			SendUtil.connectWeiXinInterface(action,json);

	       } catch (Exception e) {

	           e.printStackTrace();

	       }
    }
    /**
     * 发送回访模板消息
     * touser：发送对象的openid
     * url:跳转地址
     * template_id：模板id
     */
   public static void sendTemplateHf(String openid,String url,String title,String contact,String kh,String xm,String result,String fankui,String end){
	   
	   String accessToken = SendUtil.getAccess_token();
	   String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	   action = action.replace("ACCESS_TOKEN", accessToken);
	   WxTemplate temp = new WxTemplate();
       temp.setUrl(url);
       temp.setTouser(openid);
       temp.setTopcolor("#000000");
       temp.setTemplate_id("ihbm9Ff4jhH9GLT0Jp432TMCTrpvHFZz2wGbDn0a_DI");
       Map<String,TemplateData> m = new HashMap<String,TemplateData>();
       TemplateData first = new TemplateData();
       first.setColor("#000000");  
       first.setValue(title);  
       m.put("first", first);
       
       TemplateData keyword1 = new TemplateData();  
       keyword1.setColor("#FF0000");  
       keyword1.setValue(contact);  
       m.put("keyword1", keyword1);

       TemplateData keyword2 = new TemplateData();  
       keyword2.setColor("#000000");  
       keyword2.setValue(kh);  
       m.put("keyword2", keyword2);
       
       TemplateData keyword3 = new TemplateData();  
       keyword3.setColor("#000000");  
       keyword3.setValue(xm);  
       m.put("keyword3", keyword3);
       
       TemplateData keyword4 = new TemplateData();  
       keyword4.setColor("#FF0000");  
       keyword4.setValue(result);  
       m.put("keyword4", keyword4);
       
       TemplateData keyword5 = new TemplateData();  
       keyword5.setColor("#000000");  
       keyword5.setValue(fankui);  
       m.put("keyword5", keyword5);
       
       TemplateData remark = new TemplateData();  
       remark.setColor("#000000");  
       remark.setValue(end);  
       m.put("remark", remark);
       temp.setData(m);
       
       String jsonString = JSONObject.fromObject(temp).toString();
       JSONObject jsonObject = WeixinUtil.httpRequest(action, "POST", jsonString);
       System.out.println(jsonObject);
       
   }
   
   
   
   /**
    * 发送请假提醒模板消息
    * touser：发送对象的openid
    * url:跳转地址
    * template_id：模板id
    */
  public static void sendTemplateQj(String openid,String url,String title,String name,String type,String time,String timelong,String yuanyin){
	   
	   String accessToken = SendUtil.getAccess_token();
	   String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	   action = action.replace("ACCESS_TOKEN", accessToken);
	   WxTemplate temp = new WxTemplate();
      temp.setUrl(url);
      temp.setTouser(openid);
      temp.setTopcolor("#000000");
      temp.setTemplate_id("aa0C4765_Y_lGBntPLx2MF0rfoiS3ZJvdqwpVGKIXeU");
      Map<String,TemplateData> m = new HashMap<String,TemplateData>();
      TemplateData first = new TemplateData();
      first.setColor("#000000");  
      first.setValue(title);  
      m.put("first", first);
      
      TemplateData keyword1 = new TemplateData();  
      keyword1.setColor("#FF0000");  
      keyword1.setValue(name);  
      m.put("keyword1", keyword1);

      TemplateData keyword2 = new TemplateData();  
      keyword2.setColor("#000000");  
      keyword2.setValue(type);  
      m.put("keyword2", keyword2);
      
      TemplateData keyword3 = new TemplateData();  
      keyword3.setColor("#000000");  
      keyword3.setValue(time);  
      m.put("keyword3", keyword3);
      
      TemplateData keyword4 = new TemplateData();  
      keyword4.setColor("#FF0000");  
      keyword4.setValue(timelong+"天");  
      m.put("keyword4", keyword4);
      
      TemplateData keyword5 = new TemplateData();  
      keyword5.setColor("#000000");  
      keyword5.setValue(yuanyin);  
      m.put("keyword5", keyword5);
      
    
      temp.setData(m);
      
      String jsonString = JSONObject.fromObject(temp).toString();
      JSONObject jsonObject = WeixinUtil.httpRequest(action, "POST", jsonString);
      System.out.println(jsonObject);
      
  }
  public static void sendTemplateShQj(String openid,String url,String title,String type,String time,String person,String yuanyin,String state,String yijian){
	 
	   String accessToken = SendUtil.getAccess_token();
	   String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	   action = action.replace("ACCESS_TOKEN", accessToken);
	   WxTemplate temp = new WxTemplate();
     temp.setUrl(url);
     temp.setTouser(openid);
     temp.setTopcolor("#000000");
     temp.setTemplate_id("HVVZW7Co2ni_Nqgeolvcna1DCMbl6coGVpkcwKuyIFk");
     Map<String,TemplateData> m = new HashMap<String,TemplateData>();
     TemplateData first = new TemplateData();
     first.setColor("#000000");  
     first.setValue(title);  
     m.put("first", first);
     
     TemplateData keyword1 = new TemplateData();  
     keyword1.setColor("#FF0000");  
     keyword1.setValue(type);  
     m.put("keyword1", keyword1);

     TemplateData keyword2 = new TemplateData();  
     keyword2.setColor("#000000");  
     keyword2.setValue(time);  
     m.put("keyword2", keyword2);
     
     TemplateData keyword3 = new TemplateData();  
     keyword3.setColor("#000000");  
     keyword3.setValue(yuanyin);  
     m.put("keyword3", keyword3);
     
     TemplateData keyword4 = new TemplateData();  
     keyword4.setColor("#FF0000");  
     keyword4.setValue(person);  
     m.put("keyword4", keyword4);
     
     TemplateData keyword5 = new TemplateData();  
     keyword5.setColor("#000000");  
     keyword5.setValue(state);  
     m.put("keyword5", keyword5);
     
     TemplateData keyword6 = new TemplateData();  
     keyword6.setColor("#000000");  
     keyword6.setValue(yijian);  
     m.put("keyword6", keyword6);
     temp.setData(m);
     
     String jsonString = JSONObject.fromObject(temp).toString();
     JSONObject jsonObject = WeixinUtil.httpRequest(action, "POST", jsonString);
     System.out.println(jsonObject);
     
 }

  public static void sendTemplatePLHf(String openid,String url,String title,String customName,String xmName,String visitor,String Content,String end){
	   
	   String accessToken = SendUtil.getAccess_token();
	   String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	   action = action.replace("ACCESS_TOKEN", accessToken);
	   WxTemplate temp = new WxTemplate();
      temp.setUrl(url);
      temp.setTouser(openid);
      temp.setTopcolor("#000000");
      temp.setTemplate_id("3G35dTfyqc0FglNbX-PvY0jVbAtOLFmDw2RWwSX8EOc");
      Map<String,TemplateData> m = new HashMap<String,TemplateData>();
      TemplateData first = new TemplateData();
      first.setColor("#000000");  
      first.setValue(title);  
      m.put("first", first);
      
      TemplateData keyword1 = new TemplateData();  
      keyword1.setColor("#FF0000");  
      keyword1.setValue(customName);  
      m.put("keyword1", keyword1);

      TemplateData keyword2 = new TemplateData();  
      keyword2.setColor("#000000");  
      keyword2.setValue(xmName);  
      m.put("keyword2", keyword2);
      
      TemplateData keyword3 = new TemplateData();  
      keyword3.setColor("#000000");  
      keyword3.setValue(visitor);  
      m.put("keyword3", keyword3);
      
      TemplateData keyword4 = new TemplateData();  
      keyword4.setColor("#FF0000");  
      keyword4.setValue(Content);  
      m.put("keyword4", keyword4);
      
      TemplateData keyword5 = new TemplateData();  
      keyword5.setColor("#000000");  
      keyword5.setValue(end);  
      m.put("keyword5", keyword5);
      temp.setData(m);
      
      String jsonString = JSONObject.fromObject(temp).toString();
      JSONObject jsonObject = WeixinUtil.httpRequest(action, "POST", jsonString);
      System.out.println(jsonObject);
      
  }
  
  
}