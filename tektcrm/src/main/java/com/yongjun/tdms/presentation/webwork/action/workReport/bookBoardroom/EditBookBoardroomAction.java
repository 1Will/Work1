package com.yongjun.tdms.presentation.webwork.action.workReport.bookBoardroom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yongjun.pluto.exception.DaoException;
import com.yongjun.pluto.model.codevalue.CodeValue;
import com.yongjun.pluto.service.codevalue.CodeValueManager;
import com.yongjun.pluto.webwork.action.PrepareAction;
import com.yongjun.tdms.model.personnelFiles.PersonnelFiles;
import com.yongjun.tdms.model.workReport.boardroom.Boardroom;
import com.yongjun.tdms.model.workReport.boardroom.BookBoardroom;
import com.yongjun.tdms.service.personnelFiles.personnel.PersonnelFilesManager;
import com.yongjun.tdms.service.workReport.boardroom.BoardroomManager;
import com.yongjun.tdms.service.workReport.boardroom.BookBoardroomManager;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class EditBookBoardroomAction extends PrepareAction {
	private static final long serialVersionUID = 3022668162752790885L;
	private final BookBoardroomManager bookBoardroomManager;
	private final BoardroomManager boardroomManager;
	private final CodeValueManager codeValueManager;
	private final PersonnelFilesManager personnelFilesManager;
	private BookBoardroom bookBoardroom;
	private Boardroom boardroom;
	private PersonnelFiles applicant;
	private String popWindowFlag;
	private Date startDate;
	private Date endDate;
	
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm");

	public EditBookBoardroomAction(BoardroomManager boardroomManager,CodeValueManager codeValueManager,
			BookBoardroomManager bookBoardroomManager,PersonnelFilesManager personnelFilesManager){
		this.bookBoardroomManager = bookBoardroomManager;
		this.boardroomManager = boardroomManager;
		this.codeValueManager = codeValueManager;
		this.personnelFilesManager = personnelFilesManager;
	}

	public void prepare() throws Exception {
		if (hasId("bookBoardroom.id")) {
			this.bookBoardroom = bookBoardroomManager.loadBookBoardroom(getId("bookBoardroom.id"));
			this.applicant = this.bookBoardroom.getApplicant();
			this.boardroom = this.bookBoardroom.getBoardroom();
		}else {
			this.bookBoardroom = new BookBoardroom();
		}
		
		if (hasId("boardroom.id")) {
			this.boardroom = boardroomManager.loadBoardroom(getId("boardroom.id"));
		}
		
		if (hasId("applicant.id")) {
			this.applicant = personnelFilesManager.loadPersonnel(getId("applicant.id"));
		}
		
		if (hasId("popWindowFlag")) {
			this.popWindowFlag = request.getParameter("popWindowFlag");
		}
		
		if (hasId("startHour") && hasId("startMinute")) {
			String start = request.getParameter("bookBoardroom.startDate");
			String startHour = request.getParameter("startHour");
			String startMinute = request.getParameter("startMinute");
			this.startDate = format.parse(start+" "+startHour+":"+startMinute);
		}
		if (hasId("endHour") && hasId("endMinute")) {
			String end = request.getParameter("bookBoardroom.endDate");
			String endHour = request.getParameter("endHour");
			String endMinute = request.getParameter("endMinute");
			this.endDate = format.parse(end+" "+endHour+":"+endMinute);
		}
	}

	public String save() {
		boolean isNew = this.bookBoardroom.isNew();
		try{
			if (hasId("state.id")) {
				this.bookBoardroom.setState(this.codeValueManager.loadCodeValue(getId("state.id")));
			}
			if (hasId("boardroom.id")) {
				this.bookBoardroom.setBoardroom(this.boardroom);;
			}
			if (hasId("applicant.id")) {
				this.bookBoardroom.setApplicant(this.applicant);
			}
			if (hasId("startHour") && hasId("startMinute")) {
				this.bookBoardroom.setStartDate(startDate);
			}
			if (hasId("endHour") && hasId("endMinute")) {
				this.bookBoardroom.setEndDate(endDate);
			}
			this.bookBoardroom.setIsSaved(this.request.getParameter("isSaved"));
			
			this.bookBoardroomManager.storeBookBoardroom(this.bookBoardroom);
			String submit = null;
			if ("1".equals(this.request.getParameter("isSaved"))) {
				submit = "submit";
			}
			
			if (isNew) {
				addActionMessage(getText("bookBoardroom.add.success"));
				return NEW;
			}else {
				if (submit != null) {
					addActionMessage(getText("bookBoardroom.submit.success"));
				}else {
					addActionMessage(getText("bookBoardroom.edit.success"));
				}
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage(getText("bookBoardroom.add.error"));
		}
		return "error";
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
				if (states != null)
					return states;
			}
		} catch (DaoException e) {
			e.printStackTrace();
			return new ArrayList();
		}
		return new ArrayList();
	}

	public String cancelBook(Long id){
		try {
		BookBoardroom b = bookBoardroomManager.loadBookBoardroom(id);
		List<CodeValue> state = this.codeValueManager.loadByKey("code", "30204");
		if (state!=null && state.size()>0) {
			b.setState(state.get(0));
			this.bookBoardroomManager.storeBookBoardroom(b);
			return "1";
		}
		return "0";
		} catch (DaoException e) {
			e.printStackTrace();
			return "0";
		}
	}
	public BookBoardroom getBookBoardroom() {
		return bookBoardroom;
	}

	public void setBookBoardroom(BookBoardroom bookBoardroom) {
		this.bookBoardroom = bookBoardroom;
	}

	public Boardroom getBoardroom() {
		return boardroom;
	}

	public void setBoardroom(Boardroom boardroom) {
		this.boardroom = boardroom;
	}

	public PersonnelFiles getApplicant() {
		return applicant;
	}

	public void setApplicant(PersonnelFiles applicant) {
		this.applicant = applicant;
	}

	public String getPopWindowFlag() {
		return popWindowFlag;
	}

	public void setPopWindowFlag(String popWindowFlag) {
		this.popWindowFlag = popWindowFlag;
	}

}
