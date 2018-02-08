package com.yongjun.tdms.presentation.webwork.action.base.area;


import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.dao.area.Area;
import com.yongjun.tdms.service.area.AreaManager;


/**
 * @author qs
 * @version $Id: EditAreaAction.java 6901 2007-09-06 01:04:10Z qsun $
 */
public class EditAreaAction
        extends PrepareAction {
	private static final long serialVersionUID = -6722017437417848485L;
	private final Log log = LogFactory.getLog(this.getClass());
	private final AreaManager areaManager;
    private Area area;
    private Long[] deletetransLocationIds;

    public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Long[] getDeletetransLocationIds() {
		return deletetransLocationIds;
	}

	public void setDeletetransLocationIds(Long[] deletetransLocationIds) {
		this.deletetransLocationIds = deletetransLocationIds;
	}

	public EditAreaAction(AreaManager areaManager) {
		this.areaManager = areaManager;
	}

	public void prepare() throws Exception {
		if (this.area == null) {
			if (this.hasId("area.id")) {
				this.area = this.areaManager.loadArea(this.getId("area.id"));
			} else {
				this.area = new Area();
			}
		}
	}

	public String delete() {
		this.areaManager.deleteArea(area);
		this.addActionMessage(this.getText("area.invalid.success", Arrays
				.asList(new Object[] { area.getName() })));
		return SUCCESS;
	}

	public String save() {
		if (this.isDelete()) {
			return this.delete();
		}

		boolean isNew = this.area.isNew();
		area.setCode("a");
		area.setName("a");
		area.setLocaleName("a");
		this.areaManager.storeArea(area);
		if (isNew) {

			log.debug("get text is : " + this.getText("area.add.success"));

			this.addActionMessage(this.getText("area.add.success", Arrays
					.asList(new Object[] { area.getName() })));

			return NEW;
		} else {
			this.addActionMessage(this.getText("area.edit.success", Arrays
					.asList(new Object[] { area.getName() })));
			return SUCCESS;
		}
	}
}

