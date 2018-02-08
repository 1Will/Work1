package com.yongjun.tdms.dao.base.information;

import java.util.Collection;
import java.util.List;



import com.yongjun.tdms.model.base.information.Msg;

public interface MsgDao  {   
	Msg loadMsg(Long msgId);
	List<Msg> loadAllMsgs(Long[] msgIds);
    List<Msg> loadAllMsgs();
	void storeMsg(Msg msg);
	void deleteMsg(Msg msgIds);
	void deleteAllMsgs(Collection<Msg> msgIds);
}
