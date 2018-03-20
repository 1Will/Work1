package com.ahctx.reward.service.impl;

import java.text.ParseException;

import org.apache.commons.lang.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.jobs.RewardCompareJob;
import com.ahctx.reward.service.IQuartzService;
import com.baomidou.framework.quartz.QuartzJobManager;

/**
 * Quartz 测试业务类
 */
@Service
public class QuartzServiceImpl implements IQuartzService {
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzServiceImpl.class);

	@Autowired
	private QuartzJobManager quartzJobManager;

	@Override
	public boolean addDemoJob(String jobName) throws ParseException, SchedulerException {
		LOGGER.debug("---------- 添加测试Job处理开始 ---------");

		if (StringUtils.isEmpty(jobName)) {
			jobName = "DemoJob";
		}

		// 已经添加过job返回添加失败
		if (quartzJobManager.isJobAdded(jobName, "job_group")) {
			LOGGER.error("名称[" + jobName + "]的任务已存在]");
			return false;
		}

		// 添加一个每分钟执行的任务
		quartzJobManager.addJob(jobName, RewardCompareJob.class, "0 0 16 * * ?");

		LOGGER.debug("---------- 添加测试Job处理结束 ---------");
		return true;
	}

	@Override
	public boolean deleteDemoJob(String jobName) throws ParseException, SchedulerException {
		LOGGER.debug("---------- 添加测试Job处理开始 ---------");

		if (StringUtils.isEmpty(jobName)) {
			jobName = "DemoJob";
		}

		// 已经添加过job返回添加失败
		if (quartzJobManager.isJobAdded(jobName, "job_group")) {
			quartzJobManager.removeJob(jobName);
			return true;
		} else {
			LOGGER.error("名称[" + jobName + "]的任务不存在]");
			LOGGER.debug("---------- 添加测试Job处理结束 ---------");
			return false;
		}

	}
}