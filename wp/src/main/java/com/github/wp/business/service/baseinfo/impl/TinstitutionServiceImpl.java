package com.github.wp.business.service.baseinfo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.wp.business.dao.baseinfo.TinstitutionDao;
import com.github.wp.business.pojo.base.Tinstitution;
import com.github.wp.business.service.baseinfo.TinstitutionService;


@Service
public class TinstitutionServiceImpl implements TinstitutionService {

	@Autowired
	private TinstitutionDao tinstitutionDao;

	@Override
	public List<?> findChildById(Long id) {
		
		return tinstitutionDao.findChildById(id);
	}
	
	/**
	 * @param id
	 * @return Tinstitution 单位
	 * @author dupeng
	 * @since 07-20-2016
	 */
	public Tinstitution findOne(Long id) {
		return tinstitutionDao.findOne(id);
	}
	
	/**
	 * @param Tinstitution
	 * @return Tinstitution 保存或新增单位信息
	 * @author dupeng
	 * @since 07-21-2016
	 */
	
	public void saveOrUpdate(Tinstitution tinstitution){
		
		tinstitutionDao.saveOrUpdate(tinstitution);
	}
	
	/**
	 * @param id
	 * @author 杜鹏
	 * @since 07-21-16
	 * 根据id得到tinstitution单位对象，改变单位的表示状态为D，保存，假删除
	 */
	public void deleteOne(Long id){
		tinstitutionDao.deleteOne(id);
	}

	
}
