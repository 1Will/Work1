package com.ahctx.reward.common;

import com.baomidou.kisso.common.util.EnvUtil;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.ConfigGenerator;

/**
 * 
 * 自动生成映射工具类
 * 
 * @Author wangping
 * @Create 2016-8-20 20:38:18
 */
public class AutoGeneratorHelper {

	/**
	 * 
	 * 测试 run 执行
	 * 
	 * <p>
	 * 配置方法查看 {@link ConfigGenerator}
	 * </p>
	 * 
	 */
	public static void main( String[] args ) {
		ConfigGenerator cg = new ConfigGenerator();
		cg.setEntityPackage("com.ahctx.reward.entity");
		cg.setMapperPackage("com.ahctx.reward.mapper");
		cg.setServicePackage("com.ahctx.reward.service");
		cg.setSuperServiceImpl("com.ahctx.reward.service.support.BaseServiceImpl");
		cg.setIdType(IdType.ID_WORKER);
		if (EnvUtil.isLinux()) {
			cg.setSaveDir("/ceshi/");
		} else {
			cg.setSaveDir("E:/软件项目源码/畅行安徽3.0/entity/");
		}
		cg.setDbDriverName("com.mysql.jdbc.Driver");
		cg.setDbUser("root");
		cg.setDbPassword("");
		cg.setDbUrl("jdbc:mysql://127.0.0.1:3306/ssm?characterEncoding=utf8");
		cg.setDbPrefix(false);
		AutoGenerator.run(cg);
	}
	
}
