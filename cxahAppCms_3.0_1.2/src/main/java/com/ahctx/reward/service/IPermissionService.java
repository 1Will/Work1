package com.ahctx.reward.service;

import java.util.List;

import com.ahctx.reward.entity.Permission;
import com.ahctx.reward.entity.vo.MenuVO;
import com.baomidou.framework.service.ISuperService;
import com.baomidou.kisso.Token;

/**
*
* Permission 表数据服务层接口
*
*/
public interface IPermissionService extends ISuperService<Permission> {

	List<MenuVO> selectMenuVOByUserId( Long userId );


	List<Permission> selectAllByUserId( Long userId );


	/**
	 * <p>
	 * 是否为可操作的权限
	 * </p>
	 * @param token
	 * 				SSO 票据顶级父类 
	 * @param permission
	 * 				操作权限编码
	 * @return
	 */
	boolean isActionable( Token token, String permission );


	boolean addPermission(Permission permission);
}