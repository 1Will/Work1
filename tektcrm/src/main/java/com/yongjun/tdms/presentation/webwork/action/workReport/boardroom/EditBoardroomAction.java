package com.yongjun.tdms.presentation.webwork.action.workReport.boardroom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.workReport.boardroom.Boardroom;
import com.yongjun.tdms.service.workReport.boardroom.BoardroomManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditBoardroomAction extends PrepareAction {
	private static final long serialVersionUID = 3022668162752790885L;
	private final BoardroomManager boardroomManager;
	private final CodeValueManager codeValueManager;
	private Boardroom boardroom;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");

	public EditBoardroomAction(BoardroomManager boardroomManager,CodeValueManager codeValueManager){
		this.boardroomManager = boardroomManager;
		this.codeValueManager = codeValueManager;
}

	public void prepare() throws Exception {
		if (hasId("boardroom.id")) {
			this.boardroom = boardroomManager.loadBoardroom(getId("boardroom.id"));
		}else {
			this.boardroom = new Boardroom();
		}
	}

	public String save() {
		boolean isNew = this.boardroom.isNew();
		try{
			if (hasId("state.id")) {
				this.boardroom.setState(this.codeValueManager.loadCodeValue(getId("state.id")));
			}
			this.boardroomManager.storeBoardroom(this.boardroom);
			if (isNew) {
				addActionMessage(getText("boardroom.add.success"));
				return NEW;
			}else {
				addActionMessage(getText("boardroom.edit.success"));
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("boardroom.add.error"));
		}
		return "error";
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
				if (states != null)
					return states;
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

	public void setBoardroom(Boardroom boardroom) {
		this.boardroom = boardroom;
	}

}
