package com.yongjun.tdms.service.asset.device;

/**
 * @author wzou
 * @version $Id: DeviceCardManager.java 11057 2008-02-22 01:39:21Z zbzhang $
 */
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.asset.device.ToolingAccount;

@Transactional(readOnly = true)
public interface ToolingAccountManager {
	
	List<ToolingAccount> loadAllToolingAccountByToolingID(Long id);
}
