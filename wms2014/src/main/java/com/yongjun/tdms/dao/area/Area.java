package com.yongjun.tdms.dao.area;

import com.yongjun.pluto.model.tracking.CreatedTimeTracking;
import com.yongjun.pluto.model.tracking.CreatorTracking;
import com.yongjun.pluto.model.tracking.LastModifiedTimeTracking;
import com.yongjun.pluto.model.tracking.LastOperatorTracking;
import com.yongjun.tdms.model.BaseInfoEntity;


/**
 * @author qs
 * @version $Id: Area.java 8026 2007-10-25 09:58:57Z qsun $
 */


public class Area extends BaseInfoEntity implements CreatorTracking,
		CreatedTimeTracking, LastOperatorTracking, LastModifiedTimeTracking {
	private static final long serialVersionUID = -7846671811506329855L;
	private String code;
	private String name;
	private String localeName;
	private String comments;
	private Long orgId;
	
	public Area() {
		
	}
	
	public Long getOrgId() {
		return this.orgId;
	}
	
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getLocaleName() {
		return localeName;
	}

	public void setLocaleName(String localeName) {
		this.localeName = localeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return code.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) { return true; }
		if (!(o instanceof Area)) { return false; }
		
		final Area area = (Area)o;
		
		if (!code.equals(area.code)) { return false; }
		
		return true;
	}	
}
