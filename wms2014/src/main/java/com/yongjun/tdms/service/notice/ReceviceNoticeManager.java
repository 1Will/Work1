package com.yongjun.tdms.service.notice;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.yongjun.pluto.model.notice.ReceviceNotice;

@Transactional(readOnly=true)
public interface ReceviceNoticeManager {

    ReceviceNotice loadReceviceNotice(Long receviceNoticeId);
	
	List<ReceviceNotice> loadReceviceNotices(Long[] receviceNoticeIds);
	
	/**
	 * 保存收通知实体
	 * @param notice
	 */
	@Transactional
	void storeReceviceNotice(ReceviceNotice notice);
	
	/**
	 * 删除收通知实体
	 * @param notice
	 */
	@Transactional
	void deleteReceviceNotice(ReceviceNotice notice);
	
	/**
	 * 删除列表中收通知实体
	 * @param notices
	 */
	@Transactional
	void deleteReceviceNotices(Collection <ReceviceNotice> notices);
	
	/**
	 * 失效列表中收通知实体
	 * @param receviceNotices
	 */
	@Transactional
	void disabledAllReceviceNotices(Collection<ReceviceNotice> receviceNotices);
	
	/**
	 * 有效列表中收通知实体
	 * @param receviceNotices
	 */
	@Transactional
	void enabledAllReceviceNotices(Collection<ReceviceNotice> receviceNotices);
	
	@Transactional
	void unreadAllReceviceNotices(Collection<ReceviceNotice> receviceNotices);
	/**
	 * 根据传入的用户ID,获取用户未读通知的个数
	 * @param userId 用户ID
	 * @return int 未读通知的个数
	 */
	Integer getNumberOfUnReadNoticByUserID(Long userId);
	
	/**
	 * 根据传入的用户ID,获取用户未读通知集合
	 * @param userId 用户ID
	 * @return List 未读通知集合
	 */
	List<ReceviceNotice> getAllUnReadNoticByUserID(Long userId, Date validityDate);
	List LoadAllNoticeByNoticeTilteAndContent(String title,String content);
}
