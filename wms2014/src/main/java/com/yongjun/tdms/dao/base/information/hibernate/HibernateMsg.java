package com.yongjun.tdms.dao.base.information.hibernate;

import java.util.Collection;
import java.util.List;

import com.yongjun.pluto.dao.hibernate.BaseHibernateDao;
import com.yongjun.tdms.dao.base.information.MsgDao;
import com.yongjun.tdms.model.base.information.Msg;

public class HibernateMsg extends BaseHibernateDao implements MsgDao{
	/**
	 * @author 陈晓松
	 * @param msgId 表示每条消息的ID号,用它来查询指定的每条消息;
	 * @return Msg 表示查询的结果
	 */
	public Msg loadMsg(Long msgId){
		
		Msg msg=(Msg)this.load(Msg.class,msgId);
		return msg;
	}
	/**
	 * @author 陈晓松
	 * @param 无
	 * @return 查询Msg表中所有的记录
	 */
	public List<Msg> loadAllMsgs(Long[] msgIds){
	  return this.loadAll(Msg.class,msgIds);
		
	}
	public  List<Msg> loadAllMsgs(){
		return this.loadAll(Msg.class);
	}
	/**
	 * @author 陈晓松
	 * @param Msg 表示要插入的记录
	 * @return void类型
	 */
	public void storeMsg(Msg msg){
	 this.store(msg);
	}
	/**
	 * @author 陈晓松
	 * @param Msg表示要删除的记录
	 * @return void类型 
	 */
	public void deleteMsg(Msg msgIds){
		this.delete(msgIds);
	}
	public void deleteAllMsgs(Collection<Msg> msgIds){
		this.deleteAll(msgIds);
	}

}
