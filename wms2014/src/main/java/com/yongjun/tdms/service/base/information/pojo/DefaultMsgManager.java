package com.yongjun.tdms.service.base.information.pojo;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.service.impl.BaseManager;
import com.yongjun.tdms.dao.base.information.MsgDao;
import com.yongjun.tdms.model.base.information.Msg;
import com.yongjun.tdms.service.base.information.MsgManager;

public class DefaultMsgManager extends BaseManager implements MsgManager {
	private final MsgDao msgDao;

	public DefaultMsgManager(MsgDao msgDao) {
		this.msgDao = msgDao;
	}

	public Msg loadMsg(Long msgId) {
		return this.msgDao.loadMsg(msgId);
	}

	public List<Msg> loadAllMsgs(Long[] msgIds) {
		return this.msgDao.loadAllMsgs(msgIds);
	}

	public List<Msg> loadAllMsgs() {
		return this.msgDao.loadAllMsgs();
	}

	public void storeMsg(Msg msg) {
		this.msgDao.storeMsg(msg);
	}

	public void deleteMsg(Msg msgIds) {
		this.msgDao.deleteMsg(msgIds);
	}

	public void deleteAllMsgs(Collection<Msg> msgIds) {
		this.msgDao.deleteAllMsgs(msgIds);
	}

}
