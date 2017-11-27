package com.yongjun.tdms.presentation.webwork.action.workflow.point;

import java.util.Arrays;
import java.util.List;

import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workflow.Point;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workflow.flow.FlowManager;
import com.yongjun.tdms.service.workflow.point.PointManager;

public class EditPointAction extends PrepareAction {
	private static final long serialVersionUID = 732668911930478662L;
	private PointManager pointManager;
	private PersonnelFilesManager personnelFilesManager;
	private FlowManager flowManager;
	private Point point;
	private Long flowId;

	public EditPointAction(PointManager pointManager, PersonnelFilesManager personnelFilesManager,
			FlowManager flowManager) {
		this.pointManager = pointManager;
		this.personnelFilesManager = personnelFilesManager;
		this.flowManager = flowManager;
	}

	public void prepare() throws Exception {
		if (null == this.point) {
			if (hasId("point.id")) {
				this.point = this.pointManager.loadPoint(getId("point.id"));
				this.flowId = this.point.getFlow().getId();
			} else {
				if ((null != this.request.getParameter("flow.id")) && ("" != this.request.getParameter("flow.id"))) {
					this.flowId = Long.valueOf(this.request.getParameter("flow.id"));
				}
				this.point = new Point();
			}
		} else {
			this.flowId = Long.valueOf(this.request.getParameter("flow.id"));
			this.point = new Point();
		}
	}

	public String save() {
		boolean isNew = this.point.isNew();
		try {
			if (hasId("point.personnelFiles")) {
				if (this.request.getParameter("flag").equals("0")) {
					this.point.setPersonnelFiles(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request
							.getParameter("point.personnelFiles"))));
				} else {
					this.point.setPersonnelFiles(this.personnelFilesManager.loadPersonnel(Long.valueOf(this.request
							.getParameter("point.personnelFiles.id"))));
				}

			}

			if (hasId("flow.id")) {
				this.point.setFlow(this.flowManager.loadFlow(Long.valueOf(this.request.getParameter("flow.id"))));
			}

			if (isNew) {
				this.pointManager.storePoint(this.point);
			} else {
				this.pointManager.storePoint(this.point);
			}

		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("point.add.error", Arrays.asList(new Object[] { this.point.getCode() })));

			return "error";
		}

		if (isNew) {
			addActionMessage(getText("point.add.success", Arrays.asList(new Object[] { this.point.getCode() })));

			return "new";
		}

		addActionMessage(getText("point.edit.success", Arrays.asList(new Object[] { this.point.getCode() })));

		return "success";
	}

	public List<Point> getAllPreviousPoint() {
		List pointList = this.pointManager.loadAllPoints();
		return pointList;
	}

	public List<Point> getAllRearPoint() {
		List pointList = this.pointManager.loadAllPoints();
		return pointList;
	}

	public Point getPoint() {
		return this.point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Long getFlowId() {
		return this.flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}
}
