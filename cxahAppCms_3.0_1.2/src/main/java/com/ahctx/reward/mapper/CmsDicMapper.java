package com.ahctx.reward.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ahctx.reward.entity.CmsDic;
import com.baomidou.mybatisplus.mapper.AutoMapper;

/**
 *
 * CmsDic 表数据库控制层接口
 *
 */
public interface CmsDicMapper extends AutoMapper<CmsDic> {

	List<CmsDic> getCmsDicByPid(@Param("pId") Long pId);


}