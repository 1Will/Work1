<@inputTable>
	<@ww.hidden name="'cbv'" value=""/>
	
	<!--重新排版wclin 11.7.6____________________________________________________________________________________-->
	<tr>
	<!--回访类型-->
	<#--<@ww.textfield label="'${action.getText('name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	-->
	<@ww.select 
	    		label="'${action.getText('回访类型')}'"
				required="false"
				name="'backVisitType.id'" 
				value="${req.getParameter('backVisitType.id')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allBackVisitType"
			    emptyOption="false" 
		    	disabled="false"/>
	<!--客户名称-->	
	<@ww.textfield label="'${action.getText('customer')}'" name="'customer'" value="'${req.getParameter('customer')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
	<!--联系人-->
	<@ww.textfield label="'${action.getText('contactArchive')}'" name="'contactArchive'" value="'${req.getParameter('contactArchive')?if_exists}'" cssClass="'underline'"/>
	<!--回访人-->
	<@ww.textfield label="'${action.getText('employee')}'" name="'employee'" value="'${req.getParameter('employee')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
	<!--回访时间-->
	<@pp.dateRanger label="'${action.getText('backVisitDate')}'" name="'backVisitDate'" 
            value="'${req.getParameter('backVisitDate_start')?if_exists}|${req.getParameter('backVisitDate_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        	<#if first>
	        	if(getObjByName("backVisitDate_start").value==""){
		        	var date = new Date();
					getObjByName("backVisitDate_end").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
		<!--下次回访时间-->
        <@pp.dateRanger label="'${action.getText('nextBackVisitDate')}'" name="'nextBackVisitDate'" 
	            value="'${req.getParameter('nextBackVisitDate_start')?if_exists}|${req.getParameter('nextBackVisitDate_end')?if_exists}'"
	             cssClass="'underline'" maxlength="10" />
	</tr>
	<tr>
	<!--回访方式-->
	<@ww.select 
    		label="'${action.getText('backVisitWay')}'"
			required="false"
			name="'backVisitWay.id'" 
			value="${req.getParameter('backVisitWay.id')?if_exists}" 
			listKey="id"
			listValue="name"
		    list="allBackVisitWay"
		    emptyOption="false" 
		    disabled="false"/>
		    
		    
	<!--客户等级-->
	<@ww.select label="'${action.getText('客户等级')}'" 
			name="'step.id'" 
			value="'${req.getParameter('step.id')?if_exists}'"
			listKey="id"
			listValue="name"
			list="allSteps"
			emptyOption="false" 
			disabled="false">
		</@ww.select>
		<script language="javascript">
			<#if req.getParameter('step.id')?exists>
				getObjByName('step.id').value = '${req.getParameter('step.id')?if_exists}';
			</#if>
		</script>
	</tr>
	<tr>
	<!--继续回访-->
	<td align="right"><label for="" class="label">${action.getText('continueBackVisit')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="continueBackVisit0" name="continueBackVisit" value="0" />是
	        	<input type="radio" id="continueBackVisit1" name="continueBackVisit" value="1" />否
	        	<input type="radio" id="continueBackVisit2" name="continueBackVisit" value="2" checked />所有
			</td>
	</tr>
	<tr>
	<!--仅查询失效-->
	<@crm_onlySearchInvalid_checkBox />
	<!---->
	</tr>

</@inputTable>
<script language="javascript">
	
	var selector=document.all("backVisitWay.id");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('backVisitWay.id')?exists>
        if(selector.options[i].value=="${req.getParameter('backVisitWay.id')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    var typeSelector=document.all("backVisitType.id");
    var typeGroups=typeSelector.options.length;
    for(j=0;j<typeGroups;j++){
      <#if req.getParameter('backVisitType.id')?exists>
        if(typeSelector.options[j].value=="${req.getParameter('backVisitType.id')?if_exists}"){
           typeSelector.options[j].selected="true";
        }
      </#if>
    }
    <#if req.getParameter('continueBackVisit')?exists>
    	if(${req.getParameter('continueBackVisit')?if_exists}=='0'){
			getObjByName('continueBackVisit0').checked=true;
		}else if(${req.getParameter('continueBackVisit')?if_exists}=='1'){
			getObjByName('continueBackVisit1').checked=true;
		}else{
			getObjByName('continueBackVisit2').checked=true;
		}
	</#if>
	
    function checkInvalidParms(){
    	if(getObjByName('backVisitWay.id').value==-1){
    		getObjByName('backVisitWay.id').value='';
    	}
    	if(getObjByName('backVisitType.id').value==-1){
    		getObjByName('backVisitType.id').value='';
    	}
    	if(getObjByName('step.id').value==-1){
    		getObjByName('step.id').value='';
    	}
       beginDateMsg="${action.getText('backVisitDate.error')}";
	   if(!queryDate("backVisitDate_start","backVisitDate_end",
	      beginDateMsg,null)){
	      return false;
	   }
	   
	   beginDateMsg="${action.getText('nextBackVisitDate.error')}";
	   if(!queryDate("nextBackVisitDate_start","nextBackVisitDate_end",
	      beginDateMsg,null)){
	      return false;
	   }
       return true;
    }
         
         //弹出客户档案查询模态窗体
	function customer_OpenDialog(id,readOnly){
	   var url = "${req.contextPath}/customerRelationship/editCustomerInfo.html?customerInfo.id="+id+"&readOnly="+readOnly+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   //popupModalDialog(url, 800, 600, SelectorHandlerCustomerInfo);
	   openNewWindow(url);
	 }
	 //获得模态窗体返回值
	function SelectorHandlerCustomerInfo(result) {
		if (null != result) {
		}
	}
	
	      //弹出联系人模态窗体
	function contactArchives_OpenDialog(id,readOnly){
	   var url = "${req.contextPath}/customerRelationship/editContactArchives.html?contactArchives.id="+id+"&readOnly="+readOnly+"&popWindowFlag=popWindowFlag&notNewFlag=notNewFlag";
	   //popupModalDialog(url, 800, 600, SelectorHandlerContactArchives);
	   openNewWindow(url);
	 }
	 //获得模态窗体返回值
	function SelectorHandlerContactArchives(result) {
		if (null != result) {
		}
	}
	
	      //弹出回访模态窗体
	function visitBack_OpenDialog(id){
	   var url = "${req.contextPath}/backvisit/listBackVisitTab.html?customerInfo.id="+id;
	   //popupModalDialog(url, 900,700, SelectorHandlerVisitBack);
	   openNewWindow(url);
	 }
	 //获得模态窗体返回值
	function SelectorHandlerVisitBack(result) {
		if (null != result) {
		}
	}
	function editProjectInfo_OpenDialog(id){
	   var url="";
	   url= "${req.contextPath}/projectInfo/editProjectInfo.html?projectInfo.id="+id+"&readOnly=${req.getParameter('readOnly')?if_exists}&openFlag=openFlag&notNewFlag=notNewFlag";
	   //popupModalDialog(url, 850, 600);
	   openNewWindow(url);
	   if(isIE()){self.location.reload();};
	 }
</script>