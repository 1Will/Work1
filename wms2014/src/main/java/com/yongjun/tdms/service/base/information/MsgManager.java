package com.yongjun.tdms.service.base.information;

import java.util.Collection;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yongjun.tdms.model.base.information.Msg;

@Transactional(readOnly=true)
public interface MsgManager 
{
	Msg loadMsg(Long msgId);
	List<Msg> loadAllMsgs(Long[] msgIds);
    List<Msg> loadAllMsgs();
    @Transactional
	void storeMsg(Msg msg);
    @Transactional
	void deleteMsg(Msg msgIds);
    @Transactional
	void deleteAllMsgs(Collection<Msg> msgs);
}
