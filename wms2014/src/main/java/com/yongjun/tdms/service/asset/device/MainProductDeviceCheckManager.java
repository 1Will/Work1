package com.yongjun.tdms.service.asset.device;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.report.MainProductDeviceCheck;

/**
 * <p>Title: MainProductDeviceCheckManager
 * <p>Description: 主要生成设备使用状况业务接口定义类</p>
 * <p>Copyright: Copyright (c) 2007 yj-technology</p>
 * <p>Company: www.yj-technology.com</p>
 * @author chenxs@yj-technology.com
 * @version $Id:$
 */
@Transactional(readOnly=true)
public interface MainProductDeviceCheckManager {
	 @Transactional
 void storeMainProductDeviceCheck(MainProductDeviceCheck mainProductDeviceCheck);
     /**
	 * 生成上月生产设备使用状况综合考核月报表
	 */
   @Transactional
   void createMainDeviceProductByScheduler();
   
   public List Query(String month)throws HibernateException;
   
   public List getMonths();
}
