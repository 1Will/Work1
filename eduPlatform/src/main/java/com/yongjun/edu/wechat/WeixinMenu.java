package com.yongjun.edu.wechat;

import java.util.ArrayList;
import java.util.List;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;

public class WeixinMenu{

	public static WxMenu getWxMenu() {
		String url = "http://dzgj.tunnel.qydev.com/eduPlatform/user/list.do";    // 测试账号域名  项目URL
		
		// 创建菜单，第一个菜单
		WxMenuButton button1 = new WxMenuButton();
		button1.setName("客户管理");
		
		WxMenuButton button1_1 = new WxMenuButton();
		button1_1.setType("view"); 
		button1_1.setName("客户档案");
		button1_1.setUrl(url);  

		WxMenuButton button1_2 = new WxMenuButton();  
        button1_2.setName("联系人档案");  
        button1_2.setType("view");  
        button1_2.setUrl(url); 
        
        WxMenuButton button1_3 = new WxMenuButton();  
        button1_3.setName("项目档案");  
        button1_3.setType("view");  
        button1_3.setUrl(url);  
 
        
        WxMenuButton button1_4 = new WxMenuButton();  
        button1_4.setName("回访登记");  
        button1_4.setType("view");  
        button1_4.setUrl(url);  

		List<WxMenuButton> subButtons1 = new ArrayList<WxMenuButton>();
		subButtons1.add(button1_1);
		subButtons1.add(button1_2);
		subButtons1.add(button1_3);
		subButtons1.add(button1_4);
		button1.setSubButtons(subButtons1);
		
		
		// 创建一个复合菜单，第二个菜单
		WxMenuButton button2 = new WxMenuButton();
		button2.setName("日常办公");

        WxMenuButton button2_1 = new WxMenuButton();
        button2_1.setName("消息");  
        button2_1.setType("view");  
        button2_1.setUrl(url);
        
        WxMenuButton button2_2 = new WxMenuButton();  
        button2_2.setName("任务");  
        button2_2.setType("view");  
        button2_2.setUrl(url);
     
        WxMenuButton button2_3 = new WxMenuButton();  
        button2_3.setName("日报");  
        button2_3.setType("view");  
        button2_3.setUrl(url);
  
        WxMenuButton button2_4 = new WxMenuButton();  
        button2_4.setName("周计划");  
        button2_4.setType("view");  
        button2_4.setUrl(url);

        List<WxMenuButton> subButtons2 = new ArrayList<WxMenuButton>();
        subButtons2.add(button2_1);
        subButtons2.add(button2_2);
        subButtons2.add(button2_3);
        subButtons2.add(button2_4);
        button2.setSubButtons(subButtons2);
        
        //第三个菜单
		WxMenuButton button3 = new WxMenuButton();
		button3.setName("个人中心");

		WxMenuButton button3_1 = new WxMenuButton();  
        button3_1.setName("个人签到");  
        button3_1.setType("view");  
        button3_1.setUrl(url);
         
        WxMenuButton button3_2 = new WxMenuButton();  
        button3_2.setName("售后服务");  
        button3_2.setType("view");  
        button3_2.setUrl(url);
        
  
        WxMenuButton button3_3 = new WxMenuButton();  
        button3_3.setName("我的审批");  
        button3_3.setType("view");  
        button3_3.setUrl(url);

        
        WxMenuButton button3_4 = new WxMenuButton();  
        button3_4.setName("我的申请");  
        button3_4.setType("view");  
        button3_4.setUrl(url);
        
  
        WxMenuButton button3_5 = new WxMenuButton();  
        button3_5.setName("用户绑定");  
        button3_5.setType("view");  
        button3_5.setUrl(url);  

        List<WxMenuButton> subButtons3 = new ArrayList<WxMenuButton>();
        subButtons3.add(button3_1);
        subButtons3.add(button3_2);
        subButtons3.add(button3_3);
        subButtons3.add(button3_4);
        subButtons3.add(button3_5);
        button3.setSubButtons(subButtons3);
		
		List<WxMenuButton> buttons = new ArrayList<WxMenuButton>();
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);

		WxMenu menu = new WxMenu();
		menu.setButtons(buttons);
		return menu;
	}
}
