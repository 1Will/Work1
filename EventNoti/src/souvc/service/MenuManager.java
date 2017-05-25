package souvc.service;

import souvc.pojo.AccessToken;
import souvc.pojo.Button;
import souvc.pojo.ComplexButton;
import souvc.pojo.Menu;
import souvc.pojo.ViewButton;
import souvc.util.WeixinUtil;

/** 
 * 菜单管理器类 
 */  
public class MenuManager {  
  
    public static void main(String[] args) {  
        // 第三方用户唯一凭证  
        String appId = "wx948a0fbfe8c5b079";  
        // 第三方用户唯一凭证密钥  
        String appSecret = "a381bc2aba5f9cb989b08625dca73747";  
  
        // 调用接口获取access_token  
        AccessToken at = WeixinUtil.getAccessToken(appId, appSecret);  
  
        if (null != at) {  
            // 调用接口创建菜单  
            int result = WeixinUtil.createMenu(getMenu(), at.getToken());  
  
            // 判断菜单创建结果  
            if (0 == result){  
                System.out.println("菜单创建成功！");  
            }
            else{  
            	System.out.println("菜单创建失败，错误码：" + result); 
            }
        }  
    }  
  
    /** 
     * 组装菜单数据 
     *  
     * @return 
     */  
    private static Menu getMenu() {  
    	ViewButton btn12 = new ViewButton();  
    	btn12.setName("客户档案");  
    	btn12.setType("view");  
    	btn12.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=addCustomerInfo#wechat_redirect");  
  
        ViewButton btn11 = new ViewButton();  
        btn11.setName("用户绑定");  
        btn11.setType("view");  
        btn11.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2FregisterServlet&response_type=code&scope=snsapi_base&state=123#wechat_redirect");  
       
        ViewButton btn13 = new ViewButton();  
        btn13.setName("联系人档案");  
        btn13.setType("view");  
        btn13.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=addContactArchives#wechat_redirect"); 
        
        ViewButton btn14 = new ViewButton();  
        btn14.setName("项目档案");  
        btn14.setType("view");  
        btn14.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=addProjectInfo#wechat_redirect");  
 
        
        ViewButton btn15 = new ViewButton();  
        btn15.setName("回访登记");  
        btn15.setType("view");  
        btn15.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=backvisit#wechat_redirect");  
  
        ViewButton btn21 = new ViewButton();
        btn21.setName("请假");  
        btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=qingjia#wechat_redirect");  
        btn21.setType("view");  
        
        ViewButton btn22 = new ViewButton();  
        btn22.setName("签到");  
        btn22.setType("view");  
        btn22.setUrl("http://www.baidu.com/");
        
        ViewButton btn23 = new ViewButton();  
        btn23.setName("消息");  
        btn23.setType("view");  
        btn23.setUrl("http://www.baidu.com/");
        
        ViewButton btn24 = new ViewButton();  
        btn24.setName("日报");  
        btn24.setType("view");  
        btn24.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=addDaily#wechat_redirect");
  
        ViewButton btn25 = new ViewButton();  
        btn25.setName("周计划");  
        btn25.setType("view");  
        btn25.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx948a0fbfe8c5b079&redirect_uri=http%3A%2F%2Fwww.yj-tech.com%2FEventNoti%2Foauth2Servlet&response_type=code&scope=snsapi_base&state=addWeekly#wechat_redirect");
        
        ComplexButton mainBtn1 = new ComplexButton();  
        mainBtn1.setName("客户管理");  
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13, btn14, btn15});  
  
        ComplexButton mainBtn2 = new ComplexButton();  
        mainBtn2.setName("日常办公");  
        mainBtn2.setSub_button(new Button[] { btn21,btn22,btn23,btn24,btn25});  
  
  
        Menu menu = new Menu();  
        menu.setButton(new Button[] { mainBtn1, mainBtn2 });  
  
        return menu;  
    }  
}  
