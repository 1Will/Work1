package com.yongjun.tdms.presentation.webwork.action.workReport.bookBoardroom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.valuelist.ValueListAction;
import com.yongjun.tdms.model.workReport.boardroom.BookBoardroom;
import com.yongjun.tdms.service.workReport.boardroom.BoardroomManager;
import com.yongjun.tdms.service.workReport.boardroom.BookBoardroomManager;

public class ListBookBoardroomAction extends ValueListAction {
	private static final long serialVersionUID = 1L;
	private final BoardroomManager boardroomManager;
	private final CodeValueManager codeValueManager;
	private final BookBoardroomManager bookBoardroomManager;
	private BookBoardroom bookBoardroom;
	private List<BookBoardroom> bookBoardrooms;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public ListBookBoardroomAction(BoardroomManager boardroomManager,CodeValueManager codeValueManager,
			BookBoardroomManager bookBoardroomManager){
		this.boardroomManager = boardroomManager;
		this.codeValueManager = codeValueManager;
		this.bookBoardroomManager = bookBoardroomManager;
	}

	public void prepare() throws Exception {
		if (hasId("bookBoardroom.id")) {
			this.bookBoardroom = bookBoardroomManager.loadBookBoardroom(getId("bookBoardroom.id"));
		}
		
		if (hasIds("bookBoardroomIds")) {
			this.bookBoardrooms = this.bookBoardroomManager.loadAllBookBoardroom(getIds("bookBoardroomIds"));
		}
	}

	protected String getAdapterName() {
		return "bookBoardroomHQL";
	}

	protected Map getRequestParameterMap() {
		
		Map map = super.getRequestParameterMap();
		return map;
	}

	private String delete() {
		try {
			this.bookBoardroomManager.deleteAllBookBoardroom(this.bookBoardrooms);
			addActionMessage(getText("bookBoardroom.delete.success"));
		} catch (Exception e) {
			addActionError(getText("bookBoardroom.delete.error"));
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
			Object[] keyValues1 = { String.valueOf("302"), Boolean.valueOf(false) };
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

	public BookBoardroom getBookBoardroom() {
		return bookBoardroom;
	}

	public List<BookBoardroom> getBookBoardrooms() {
		return bookBoardrooms;
	}

}
