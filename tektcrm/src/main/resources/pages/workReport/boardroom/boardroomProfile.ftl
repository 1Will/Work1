<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('会议室详细')}">
     <@ww.form action="'saveBoardroom'" namespace="'/workReport'" method="'post'" >
       <@ww.token name="saveBoardroomToken"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
         
         <#if boardroom.id?exists>
                <@ww.hidden name="'boardroom.id'" value="#{boardroom.id?if_exists}"/>
         </#if>
         
        <@inputTable>
    	<tr>
    		<@ww.textfield label="'${action.getText('会议室编码')}'" name="'boardroom.code'"  cssClass="'underline'"  disabled = "true" />
    		<@ww.textfield label="'${action.getText('会议室名称')}'" name="'boardroom.name'"  cssClass="'underline'"  required="true"/>
    		<@ww.textfield label="'${action.getText('面积')}'" name="'boardroom.square'"  cssClass="'underline'" required="false"/>
    	</tr>
    	<tr>
    		<@ww.textfield label="'${action.getText('最大人数')}'" name="'boardroom.maxPeople'"  cssClass="'underline'" required="true"/>
    			<#-- 是否有投影仪 -->
				<td align="right">
						<label for="" class="label">${action.getText('有投影仪')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yes" name="boardroom.hasProjector" value="true" />是
			        	<input type="radio" id="no" name="boardroom.hasProjector" value="false" />否
					</td>
					<script language="javascript">
					<#if boardroom.id?exists>
						<#if boardroom.hasProjector>
								getObjByName("yes").checked = true;
						<#else>
								getObjByName("no").checked = true;
						</#if>
					<#else>
							getObjByName("yes").checked = true;
					</#if>
				</script>
				
    			<#-- 是否有网络 -->
				<td align="right">
						<label for="" class="label">${action.getText('有网络')}:</label>
					</td>
					<td align="left">
			        	<input type="radio" id="yesNet" name="boardroom.hasNet" value="true" />是
			        	<input type="radio" id="noNet" name="boardroom.hasNet" value="false" />否
					</td>
					<script language="javascript">
					<#if boardroom.id?exists>
						<#if boardroom.hasNet>
								getObjByName("yesNet").checked = true;
						<#else>
								getObjByName("noNet").checked = true;
						</#if>
					<#else>
							getObjByName("yesNet").checked = true;
					</#if>
				</script>
				
	   <tr>
        	<@select 
        		label="${action.getText('会议室状态')}" 
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
			<@textarea id="boardroom.explain" name="boardroom.explain" label="${action.getText('说明')}" value="${boardroom.explain?if_exists}" rows="4" />
			<script language="JavaScript" type="text/JavaScript">
				var width=document.body.clientWidth/9;
				getObjByName("boardroom.explain").cols =width;
			</script>
    	</tr>
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	         </#if>
	         
	         <@redirectButton value="${action.getText('back')}" url="listBoardroom.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
	         
         </@buttonBar>	
     </@ww.form>

<script>
<#if boardroom?exists>
<#if boardroom.state?exists>
	getObjByName('state.id').value = '#{boardroom.state.id?if_exists}';
</#if>

</#if>

	function storeValidation(){
		if(getObjByName('boardroom.name').value==''){
			alert('请输入会议室名称');
			getObjByName('boardroom.name').focus();
			return false;
		}
		if(getObjByName('boardroom.maxPeople').value==''){
			alert('请输入最大人数');
			getObjByName('maxPeople').focus();
			return false;
		}
		
		getObjByName("state.id").removeAttribute("disabled");
		
		return true;
	}
</script>
</@htmlPage>
<#if boardroom.id?exists>
<ul id="beautytab">
	<li>
		<a id="productInfo" onclick="activeTab(this);" class="selectedtab" href='${req.contextPath}/workReport/listBookBoardroomTab.html?boardroom.id=#{boardroom.id}&readOnly=${req.getParameter('readOnly')?if_exists}' target="frame" >${action.getText('会议室预订')}</a>
	</li>
</ul>
<iframe name="frame" frameborder="0.5" src="${req.contextPath}/workReport/listBookBoardroomTab.html?boardroom.id=#{boardroom.id}&readOnly=${req.getParameter('readOnly')?if_exists}" marginHeight="0" marginWidth="0" scrolling="auto" vspace=0 hspace=0 width="100%" height="50%"/>
</#if>