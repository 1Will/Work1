package com.ahctx.reward.jobs;

import java.util.List;
import java.util.Map;

public interface JgywService {
	public Map<?, ?> findJdcxx(String hostname, String key, String[] hpzl, String[] hphm, String[] clsbdh,
			int selectPage, int pageSize);

	public Map<?, ?> findJdcwfxx(String hostname, String key, String[] hpzl, String[] hphm, String swfsj, String ewfsj,
			String clbj, String jkbj, int selectPage, int pageSize);

	public Map<?, ?> findJszxx(String hostname, String key, String[] sfzmhm, String[] fzjg, String[] dabh,
			int selectPage, int pageSize);

	public Map<?, ?> findJdclxxx(String hostname, String key, String hpzl, String hphm);

	public Map<?, ?> findJszwfxx(String hostname, String key, String[] sfzmhm, String[] fzjg, String swfsj,
			String ewfsj, String jkbj, String type, int selectPage, int pageSize);

	public List<Map<?, ?>> findJdcwfxxByXh(String hostname, String key, String xh, String hphm);

	public Map<?, ?> findJdcwftp(String hostname, String key, String xh, String hphm, String hpzl, String wfsj);

	public List<Map<?, ?>> findJszwfxxByWfbh(String hostname, String key, String wfbh, String fzjg);

	public List<Map<?, ?>> findWfxwByWfxw(String hostname, String key, String wfxw);

	public List<Map<?, ?>> findAllWfxwByWfxw(String hostname, String key, String wfxw);

	public Map<?, ?> findJtsgByDsr(String hostname, String key, String[] sfzmhm, String[] xm, String type,
			String[] xzqh, int selectPage, int pageSize);

	public Map<?, ?> findJtsgByJdc(String hostname, String key, String[] hpzl, String[] hphm, String[] xzqh,
			int selectPage, int pageSize);

	public Map<?, ?> findJdcwfxxByGxsj(String hostname, String key, String[] hpzl, String[] hphm, String gxkssj,
			String gxjssj, String clbj, String jkbj, int selectPage, int pageSize);

	public Map<?, ?> findJszwfxxByGxsj(String hostname, String key, String[] sfzmhm, String[] fzjg, String gxkssj,
			String gxjssj, String jkbj, int selectPage, int pageSize);

	public Map<?, ?> findJdcPic(String hostname, String key, String hpzl, String hphm);

	public Map<?, ?> findJdcPhone(String hostname, String key, String fzjg, String kssj, String jssj, int selectPage,
			int pageSize);

	public Map<?, ?> checkPersonCarServer(String hostname, String key, String sfzmhm, String hphm, String czxm);

	public Map<?, ?> checkVehDrvInfo(String hostname, String key, String hphm, String hpzl, String syr, String sfzmhm);

	public List<Map<?, ?>> findWfxxByJdsbh(String hostname, String key, String jdsbh);

	public List<?> findJtsgList(String hostname, String key, String qssj, String jssj, String jsz, String sgbh,
			String rds, String fzjg);

	public List<?> getBzxx(String hostname, String key, String jdbh, String fzjg);

	public List<?> findJdcsgxx(String hostname, String key, String hphm, String hpzl);

	public Map<?, ?> findJdcxx205(String hostname, String key, String[] hpzl, String[] hphm, String[] clsbdh,
			int selectPage, int pageSize);

	/**
	 * 根据身份证号码查询驾驶证发证机关
	 * 
	 * @param hostname
	 * @param key
	 * @param sfzmhm
	 * @param fzjg
	 * @param dabh
	 * @param selectPage
	 * @param pageSize
	 * @return
	 */
	public Map<?, ?> findFzjgBySfzmhm(String hostname, String key, String sfzmhm);

	public boolean findIsInfoBySfzmhm(String hostname, String key, String sfzmhm);

}
