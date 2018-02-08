<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('会议室预订详细')}">
	<@ww.form action="'saveBookBoardroom'" namespace="'/workReport'" method="'post'" >
		<@ww.token name="saveBookBoardroomToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'isSaved'" value=""/>
		<#if bookBoardroom.id?exists>
			<@ww.hidden name="'bookBoardroom.id'" value="#{bookBoardroom.id?if_exists}"/>
		</#if>

		<#if boardroom?exists>
			<#if boardroom.id?exists>
				<@ww.hidden name="'boardroom.id'" value="#{boardroom.id?if_exists}"/>
			</#if>
		<#else>
			<@ww.hidden name="'boardroom.id'" value=""/>
		</#if>

		<#if applicant?exists>
			<#if applicant.id?exists>
				<@ww.hidden name="'applicant.id'" value="#{applicant.id?if_exists}"/>
			</#if>
		<#else>
			<@ww.hidden name="'applicant.id'" value=""/>
		 </#if>
		
		<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag' >
			<@ww.hidden  name="popWindowFlag"  value="${popWindowFlag}"/>
		</#if>
        <@inputTable>
    	<tr>
			<#--预订人弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
				<label class="label">${action.getText('会议室')}:</label>
			</td>
			<td>
				<#if bookBoardroom.boardroom?exists>
					<input type="text" name="bookBoardroom.boardroom.name" class="underline"  value="${bookBoardroom.boardroom.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="bookBoardroom.boardroom.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="boardroom_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
			</td><td></td>
			
			<#--预订人弹出框-->
			<td align="right" valign="top">
				<span class="required">*</span>
				<label class="label">${action.getText('申请人')}:</label>
			</td>
			<td>
				<#if bookBoardroom.applicant?exists>
					<input type="text" name="bookBoardroom.applicant.name" class="underline"  value="${bookBoardroom.applicant.name?if_exists}" maxlength="140" size="20" disabled="true"/>
				<#else>
					<input type="text" name="bookBoardroom.applicant.name" class="underline"  value="" maxlength="140" size="20" disabled="true"/>
				</#if>
				<a onClick="applicant_OpenDialog();">
					<img src="${req.contextPath}/images/icon/files.gif" align="absMiddle" border="0" style="cursor: hand"/>
				</a>
        	<td></td>
		</tr>
		<tr id="date">
			<#include "time.ftl"/>
			<script language="JavaScript" type="text/JavaScript">
				var tds = getObjByName("date").getElementsByTagName("td");
				tds[1].setAttribute("style","width: 170px");
				tds[4].setAttribute("style","width: 170px");
			</script>
		</tr>
		
		<tr>
			<@select
				label="${action.getText('预订状态')}" 
				name="state.id" 
				id="state.id" 
				value="${req.getParameter('state.id')?if_exists}"
				listKey="id"
				listValue="name"
				list="allState"
				required="true"
				emptyOption="false" 
				disabled="true">
			</@select>
		</tr>
		
		<tr>
			<@textarea id="bookBoardroom.explain" name="bookBoardroom.explain" label="${action.getText('说明')}" value="${bookBoardroom.explain?if_exists}" rows="4" />
			<script language="JavaScript" type="text/JavaScript">
				var width=document.body.clientWidth/9;
				getObjByName("bookBoardroom.explain").cols =width;
			</script>
		</tr>
		
		</@inputTable>
			<@buttonBar>
				<#--按钮逻辑层，请勿瞎动！-->
				<#if !(action.isReadOnly())>
					<#if !bookBoardroom.id?exists>
						<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
					</#if>
					<#if bookBoardroom.isSaved?exists && bookBoardroom.isSaved=='0' >
						<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return savee();'"/>
						<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'"/>
					<#else>
						<@vsubmit name="'submit'" value="'${action.getText('refer')}'" onclick="'return submitt();'" disabled="true"/>
						<#if bookBoardroom.state?exists>
							<#if  bookBoardroom.state.code =='30201' || bookBoardroom.state.code =='30202'>
								<@vbutton class="button" value="${action.getText('取消')}" onclick="ccancel(${bookBoardroom.id?if_exists})"/>
							<#else>
								<input type="button" class="button" value="${action.getText('取消')}"  disabled="true"/>
							</#if>
						</#if>
					</#if>
				</#if>
				
				
			<#if popWindowFlag?exists&&popWindowFlag=='popWindowFlag'>
				<@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
			<#else>
				<@redirectButton value="${action.getText('back')}" url="listBookBoardroom.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
			</#if>
			</@buttonBar>	
		</@ww.form>
		
<script type='text/javascript' src='${req.contextPath}/dwr/interface/BookBoardroom.js'></script>
<script type='text/javascript' src='${req.contextPath}/dwr/interface/CancelBook.js'></script>
<script>
<#if bookBoardroom?exists>
<#if bookBoardroom.state?exists>
	getObjByName('state.id').value = '#{bookBoardroom.state.id?if_exists}';
</#if>
</#if>
	var flag = true;
	
	function submitt(){
		getObjByName('isSaved').value="1";
		return storeValidation();
	}
	function savee(){
		getObjByName('isSaved').value="0";
	 	return storeValidation();
	}

	function storeValidation(){
		if(getObjByName('bookBoardroom.boardroom.name').value==''){
			alert('请选择会议室');
			return false;
		}
		
		if(getObjByName('bookBoardroom.applicant.name').value==''){
			alert('请选择申请人');
			return false;
		}
		
		var startDate = getObjByName('bookBoardroom.startDate').value;
		if(startDate ==''){
			alert('请选择开始时间');
			return false;
		}
		
		var endDate = getObjByName('bookBoardroom.endDate').value;
		if(endDate ==''){
			alert('请选择结束时间');
			return false;
		}
		
		var startHour = getObjByName("startHour").value;
		var startMinute = getObjByName("startMinute").value;
		var start = startDate.replace(/-/g,"")+startHour+startMinute;
		var endHour = getObjByName("endHour").value;
		var endMinute = getObjByName("endMinute").value;
		var end = endDate.replace(/-/g,"")+endHour+endMinute;
		if(parseFloat(end)<=parseFloat(start)){
			alert('时间区间有误，请重新选择！');
			return false;
		}
		check();
		if(flag){
			alert('时间区间存在冲突，请检查重新选择！');
			return false;
		}
		getObjByName("state.id").removeAttribute("disabled");
		
		return true;
	}
	
	function check(){
		var boardroomId = getObjByName("boardroom.id").value;
		var startDate = getObjByName('bookBoardroom.startDate').value;
		var startHour = getObjByName("startHour").value;
		var startMinute = getObjByName("startMinute").value;
		var start = startDate+" "+startHour+":"+startMinute;
		var endDate = getObjByName('bookBoardroom.endDate').value;
		var endHour = getObjByName("endHour").value;
		var endMinute = getObjByName("endMinute").value;
		var end = endDate+" "+endHour+":"+endMinute;
		DWREngine.setAsync(false); 
		BookBoardroom.hasConflict(boardroomId,start,end,getResult); 
		DWREngine.setAsync(true); 
	}
	
	function getResult(data){
		if(data == 1){
			flag = false;
		}
	}
	
	function ccancel(id){
		DWREngine.setAsync(false); 
		CancelBook.cancelBook(id,cancelResult); 
		DWREngine.setAsync(true); 
	}
	function cancelResult(data){
		if(data == 1){
			alert("已取消");
		}else{
			alert("取消错误！");
		}
		location.reload();
	}
	
	//业务员模态窗体弹出框
	function applicant_OpenDialog(){
	   var url = "${req.contextPath}/personnelFile/listPersonByUser.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorApplicant);
	 }
	 //获得模态窗体返回值
	function creatorApplicant(result) {
		if (null != result) {
			getObjByName("applicant.id").value = result[0];
	   		getObjByName('bookBoardroom.applicant.name').value=result[2];
		}
	}
	
	//业务员模态窗体弹出框
	function boardroom_OpenDialog(){
	   var url = "${req.contextPath}/workReport/listBoardroomWindow.html?readOnly=${req.getParameter('readOnly')?if_exists}";
	   popupModalDialog(url, 800, 600, creatorBoardroom);
	}
	//获得模态窗体返回值
	function creatorBoardroom(result) {
		if (null != result) {
			getObjByName("boardroom.id").value = result[0];
	   		getObjByName('bookBoardroom.boardroom.name').value=result[1];
		}
	}
</script>
</@htmlPage>