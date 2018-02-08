package com.yongjun.tdms.presentation.webwork.action.workReport.boardroom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workReport.boardroom.Boardroom;
import com.yongjun.tdms.service.workReport.boardroom.BoardroomManager;

public class ListBoardroomAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final BoardroomManager boardroomManager;
	private final CodeValueManager codeValueManager;
	private Boardroom boardroom;
	private List<Boardroom> boardrooms;

	public ListBoardroomAction(BoardroomManager boardroomManager,CodeValueManager codeValueManager){
		this.boardroomManager = boardroomManager;
		this.codeValueManager = codeValueManager;
	}

	public void prepare() throws Exception {
		if (hasId("boardroom.id")) {
			this.boardroom = boardroomManager.loadBoardroom(getId("boardroom.id"));
		}
		
		if (hasIds("boardroomIds")) {
			this.boardrooms = this.boardroomManager.loadAllBoardroom(getIds("boardroomIds"));
		}
	}

	protected String getAdapterName() {
		return "boardroomHQL";
	}

	protected Map getRequestParameterMap() {
		Map map = super.getRequestParameterMap();
		return map;
	}

	private String delete() {
		try {
			this.boardroomManager.deleteAllBoardroom(this.boardrooms);
			addActionMessage(getText("boardroom.delete.success"));
		} catch (Exception e) {
			addActionError(getText("boardroom.delete.error"));
		}
		return "success";
	}

	public String execute() throws Exception {
		if (isDelete()) {
			return delete();
		}
		return "success";
	}

	public List<CodeValue> getAllState() {
		try {
			String[] keyNames1 = { "code", "disabled" };
			Object[] keyValues1 = { String.valueOf("301"), Boolean.valueOf(false) };
			List stateList = this.codeValueManager.loadByKeyArray(keyNames1, keyValues1);
			if (stateList != null) {
				String[] keyNames2 = { "parentCV.id", "disabled" };
				Object[] keyValues2 = { ((CodeValue) stateList.get(0)).getId(), Boolean.valueOf(false) };
				List states = this.codeValueManager.loadByKeyArray(keyNames2, keyValues2);
				if (states != null){
					CodeValue n = new CodeValue();
					states.add(0, n);
					return states;
				}
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public Boardroom getBoardroom() {
		return boardroom;
	}

	public List<Boardroom> getBoardrooms() {
		return boardrooms;
	}

}
