package souvc.servlet;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import souvc.pojo.TemplateData;
import souvc.pojo.WxTemplate;
import souvc.util.SendUtil;
import souvc.util.WeixinUtil;

public class Demo {

	public static void main(String[] args) throws Exception{
		String accessToken = SendUtil.getAccess_token();
		String openid = "ohrRBxGRXItyp-sPqvvGkwyhvjps";
		String action = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
		action = action.replace("ACCESS_TOKEN", accessToken);
		
		WxTemplate temp = new WxTemplate();
        temp.setUrl("www.baidu.com");
        temp.setTouser(openid);
        temp.setTopcolor("#000000");
        temp.setTemplate_id("ihbm9Ff4jhH9GLT0Jp432TMCTrpvHFZz2wGbDn0a_DI"); //发布号 回访成功提醒ID
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();
        TemplateData first = new TemplateData();
        first.setColor("#000000");  
        first.setValue("您好，您已填写成功.");  
        m.put("first", first);
        
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#FF0000");  
        keyword1.setValue("Peter");  
        m.put("keyword1", keyword1);

        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("上海中心");  
        m.put("keyword2", keyword2);
        
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue("GP");  
        m.put("keyword3", keyword3);
        
        TemplateData keyword4 = new TemplateData();  
        keyword4.setColor("#FF0000");  
        keyword4.setValue("满意");  
        m.put("keyword4", keyword4);
        
        TemplateData keyword5 = new TemplateData();  
        keyword5.setColor("#000000");  
        keyword5.setValue("这次项目合作非常愉快");  
        m.put("keyword5", keyword5);
        
        TemplateData remark = new TemplateData();  
        remark.setColor("#000000");  
        remark.setValue("感谢您的使用");  
        m.put("remark", remark);
        temp.setData(m);
        
        String jsonString = JSONObject.fromObject(temp).toString();
        JSONObject jsonObject = WeixinUtil.httpRequest(action, "POST", jsonString);
        System.out.println(jsonObject);
        
        int result = 0;
        if (null != jsonObject) {  
             if (0 != jsonObject.getInt("errcode")) {  
                 result = jsonObject.getInt("errcode");
             }  
         }
		
	}
	
	
}
