package com.ahctx.reward.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahctx.reward.entity.RolePermission;
import com.ahctx.reward.mapper.RolePermissionMapper;
import com.ahctx.reward.service.IRolePermissionService;
import com.ahctx.reward.service.support.BaseServiceImpl;
import com.baomidou.framework.annotations.Log;

/**
*
* RolePermission 表数据服务层接口实现类
*
*/
@Service
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermissionMapper, RolePermission>
		implements IRolePermissionService {

	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	@Log("菜单查询")
	@Override
	public boolean existRolePermission(Long permissionId) {
		RolePermission rp = new RolePermission();
		rp.setPid(permissionId);
		int rlt = baseMapper.selectCount(rp);
		return rlt >= 1;
	}

	@Log("角色关联菜单查询")
	@Override
	public List<Long> selecPermissionIdsByRoleId(Long id) {
		return rolePermissionMapper.selecPermissionIdsByRoleId(id);
	}

}