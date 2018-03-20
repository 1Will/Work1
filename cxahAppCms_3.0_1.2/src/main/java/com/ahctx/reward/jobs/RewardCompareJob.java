package com.ahctx.reward.jobs;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.quartz.JobExecutionContext;

import com.ahctx.reward.entity.RegisterUser;
import com.ahctx.reward.entity.RewardValidate;
import com.ahctx.reward.service.IRewardService;
import com.ahctx.reward.service.IRewardValidateService;
import com.baomidou.framework.quartz.QuartzJobSupport;
import com.caucho.hessian.client.HessianProxyFactory;

/**
 * <p>
 * Quartz Demo Job
 * </p>
 * <p>
 * 继承 QuartzJobSupport
 * </p>
 */
public class RewardCompareJob extends QuartzJobSupport {

	private static final String URL_HOST = "http://61.132.135.219:8083/jtwfssfw205/hessian/jgywService";

	private static final String HOST_NAME = "安徽科力";

	private static final String PRIMARY_KEY= "7f2ee3d0ea7a83bc85ca19a7d0a50449";

	private int READ_TIME_OUT= 10000;
	
	//用户验证信息
	private static final String FZJG_LOG = "用户发证机关或行政区划不存在！";
	
	private static final String IS_INFO = "用户不是2014年12月31日前发放的A1、A2、A3、B1、B2、C1、C2有效驾驶证件！";

	private static final String JSZW_FXX = "用户在2015年1月1日之前有交通违法记分未处理完毕的！";
	
	private static final String JSZW_FXX_ = "用户在2015年1月1日到2015年12月31日有交通违法记分！";
	
	private static final String JTS = "用户在2015年1月1日零时至2015年12月31日24时是有交通事故记录！";
	
	private static final String VALIDATE_TRUE = "用户符合抽奖条件！";
	
	// 日志编码
	private static final String ERROR_CODE = "ERROR";
	
	private static final String FZJG_LOG_CODE = "FZJG_LOG";
	
	private static final String IS_INFO_CODE = "IS_INFO";
	
    private static final String JSZW_FXX_CODE = "JSZW_FXX";
	
	private static final String JSZW_FXX__CODE = "JSZW_FXX_";
	
	private static final String JTS_CODE = "JTS_CODE";
	
	private static final String VALIDATE_TRUE_CODE = "VALIDATE_TRUE";
	
	@Override
	@SuppressWarnings("unchecked")
	public void innerIter(JobExecutionContext jobExecutionContext) {
		System.out.println("reward 定时任务开始=======================================");
		IRewardService service = getBean("iRewardService", IRewardService.class);
		IRewardValidateService validateService = getBean("iRewardValidateService", IRewardValidateService.class);
		int count = service.selectCount(new RegisterUser());
		int offset = 100; // 每次查询100条数据
		HessianProxyFactory factory = new HessianProxyFactory();
		factory.setReadTimeout(READ_TIME_OUT);
		JgywService jgywApi = null;
		try {
			jgywApi = (JgywService) factory.create(JgywService.class, URL_HOST);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return ;
		}
		for (int i = 0 ; i < count ; ) {
			List<RegisterUser> users = service.selectRegisterUser(new RegisterUser(), null, null, null, i, offset);
			for (RegisterUser user : users) {
				if (user.getDriverNumber() == null || user.getDriverNumber().isEmpty())
					continue;
				Map<String, Object> map = null;
				RewardValidate rewardValidate = new RewardValidate();
				try {
					// 根据身份证号码查询驾驶证发证机关和行政区划
					map = (Map<String, Object>) jgywApi.findFzjgBySfzmhm(HOST_NAME, PRIMARY_KEY,
							user.getDriverNumber());
					// 用户domain不存在，执行下次循环
					if (map != null && map.get("FZJG") != null && !map.get("FZJG").toString().equals("")
							&& map.get("XZQH") != null && !map.get("XZQH").toString().equals("")) {
						user.setDomain(map.get("FZJG").toString());
						user.setAreaCode(map.get("XZQH").toString());
					} else {
						saveValidateLog(user, rewardValidate, FZJG_LOG, FZJG_LOG_CODE);
						continue;
					}
					// 查询是否是2014年12月31日前发放的A1、A2、A3、B1、B2、C1、C2有效驾驶证件
					boolean findIsInfoBySfzmhm = jgywApi.findIsInfoBySfzmhm(HOST_NAME, PRIMARY_KEY,
							user.getDriverNumber());
					if (!findIsInfoBySfzmhm) {
						continueAndSave(user, map);
						saveValidateLog(user, rewardValidate, IS_INFO, IS_INFO_CODE);
						continue;
					}
					// type=null时查询在某个时间段内是否有违法行为，当type=1时查询2015年1月1日之前有交通违法记分未处理完毕的，返回的集合为空表示在某个时间段内没有违法行为，
					// 2015年1月1日之前没有违法积分未处理
					Map<String, Object> findJszwfxx = (Map<String, Object>) jgywApi.findJszwfxx(HOST_NAME, PRIMARY_KEY,
							new String[] { user.getDriverNumber() }, new String[] { user.getDomain() }, null, null,
							null, "1", -1, -1);
					if(findJszwfxx.get("jszwfxxList") != null && 
							!((ArrayList<?>) findJszwfxx.get("jszwfxxList")).isEmpty()) {
						continueAndSave(user, map);
						saveValidateLog(user, rewardValidate, JSZW_FXX, JSZW_FXX_CODE);
						continue;
					}
					Map<String, Object> findJszwfxx_ = (Map<String, Object>) jgywApi.findJszwfxx(HOST_NAME, PRIMARY_KEY,
							new String[] { user.getDriverNumber() }, new String[] { user.getDomain() }, "2015-01-01", "2015-12-31",
							null, null, -1, -1);
					if(findJszwfxx_.get("jszwfxxList") != null && 
							!((ArrayList<?>) findJszwfxx_.get("jszwfxxList")).isEmpty()) {
						continueAndSave(user, map);
						saveValidateLog(user, rewardValidate, JSZW_FXX_, JSZW_FXX__CODE);
						continue;
					}
					// type=1
					// 查询在2015年1月1日零时至2015年12月31日24时是否有违法记录，返回集合为空表示没有，不为空表示有
					Map<String, Object> findJtsgByDsr = (Map<String, Object>) jgywApi.findJtsgByDsr(HOST_NAME, PRIMARY_KEY,
							new String[] { user.getDriverNumber() }, new String[] {}, "1",
							new String[] { user.getAreaCode() }, -1, -1);
					if(findJtsgByDsr != null && !findJtsgByDsr.isEmpty()) {
						continueAndSave(user, map);
						saveValidateLog(user, rewardValidate, JTS, JTS_CODE);
						continue;
					}
					user.setValidateTrue("Y");
					saveValidateLog(user, rewardValidate, VALIDATE_TRUE, VALIDATE_TRUE_CODE);
				} catch (Exception e) {
                    e.printStackTrace();
					continueAndSave(user, map);
					saveValidateLog(user, rewardValidate, e.getMessage(), ERROR_CODE);
				} finally {
					service.updateById(user);
					validateService.insert(rewardValidate);
				}
			}
			i += offset;
			System.out.println(i);
		}
		System.out.println("reward 定时任务结束=======================================");
	}

	private void continueAndSave(RegisterUser user, Map<String, Object> map) {
		if (map != null && map.get("FZJG") != null && !map.get("FZJG").toString().equals("")
				&& map.get("XZQH") != null && !map.get("XZQH").toString().equals("")) {
		    user.setDomain(map.get("FZJG") == null ? null : map.get("FZJG").toString());
		    user.setAreaCode(map.get("XZQH") == null ? null : map.get("XZQH").toString());
		    user.setValidateTrue("N");
	    }
    }
	
	private void saveValidateLog(RegisterUser user, RewardValidate rewardValidate, String contents, String logType) {
		rewardValidate.setRegUserId(user.getId());
		rewardValidate.setRegUserName(user.getNameOfDriver());
		rewardValidate.setContents(contents);
		rewardValidate.setLogType(logType);
	}
}
