<#include "../../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('职级等级变更')}">
     <@ww.form action="'saveRankAndGrade'" namespace="'/personnelFile'" method="'post'" >
       <@ww.token name="saveRankAndGrade"/>
       <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
       
         <#if personnelFiles?exists>
                <@ww.hidden name="'personnelFiles.id'" value="#{personnelFiles.id?if_exists}"/>
         </#if>
         <#if rankAndGrade.id?exists>
                <@ww.hidden name="'rankAndGrade.id'" value="#{rankAndGrade.id?if_exists}"/>
         </#if>
         
        <@inputTable>
    	<tr>
    	<@ww.select label="'${action.getText('变更前职级')}'" 
				name="'beforeRank.id'" 
				value="'${req.getParameter('beforeRank.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allRank"
				required="false"
				emptyOption="true" 
				disabled="true">
		</@ww.select>
			<script language="javascript">
				<#if rankAndGrade.beforeRank?exists>
					getObjByName("beforeRank.id").value ='#{rankAndGrade.beforeRank.id?if_exists}';
				<#else>
					getObjByName("beforeRank.id").value ='#{personnelFiles.rank.id?if_exists}';
				</#if>
			</script>
		
    	<@ww.select label="'${action.getText('变更后职级')}'" 
				name="'newRank.id'" 
				value="'${req.getParameter('newRank.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allRank"
				required="false"
				emptyOption="true" 
				disabled="false">
		</@ww.select>
			<script language="javascript">
				<#if rankAndGrade.newRank?exists>
					getObjByName("newRank.id").value ='#{rankAndGrade.newRank.id?if_exists}';
				<#else>
					getObjByName("newRank.id").value ='#{personnelFiles.rank.id?if_exists}';
				</#if>
			</script>
     	</tr>
    	<@ww.select label="'${action.getText('变更前等级')}'" 
				name="'beforeGrade.id'" 
				value="'${req.getParameter('beforeGrade.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allGrade"
				required="false"
				emptyOption="true" 
				disabled="true">
		</@ww.select>
			<script language="javascript">
				<#if rankAndGrade.beforeGrade?exists>
					getObjByName("beforeGrade.id").value ='#{rankAndGrade.beforeGrade.id?if_exists}';
				<#else>
					getObjByName("beforeGrade.id").value ='#{personnelFiles.grade.id?if_exists}';
				</#if>
			</script>
    	<@ww.select label="'${action.getText('变更后等级')}'" 
				name="'newGrade.id'" 
				value="'${req.getParameter('newGrade.id')?if_exists}'"
				listKey="id"
				listValue="name"
				list="allGrade"
				required="false"
				emptyOption="true" 
				disabled="false">
		</@ww.select>
			<script language="javascript">
				<#if rankAndGrade.newGrade?exists>
					getObjByName("newGrade.id").value ='#{rankAndGrade.newGrade.id?if_exists}';
				<#else>
					getObjByName("newGrade.id").value ='#{personnelFiles.grade.id?if_exists}';
				</#if>
			</script>
     	
     	<tr>
	    	<td align="right" valign="top">
	    		<label class="label">${action.getText('变更说明')}:</label>
	    	</td>
	        <td colspan="10">
	        	<textarea name="rankAndGrade.explain" rows="4"  >${rankAndGrade.explain?if_exists}</textarea>
	        </td>
	        <script language="javascript">
				var width=document.body.clientWidth/9;
				getObjByName("rankAndGrade.explain").cols =width;
			</script>
	   </tr>
     	
    	<tr>
         </@inputTable>
         <@buttonBar>
           	 <#if !(action.isReadOnly())>
	         	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return storeValidation();'"/>
	         </#if>
	         <@vbutton class="button" value="${action.getText('close')}" onclick="closeThis()"/>
         </@buttonBar>	
     </@ww.form>

<script>
	function storeValidation(){
		getObjByName('beforeRank.id').removeAttribute('disabled');
		getObjByName('beforeGrade.id').removeAttribute('disabled');
		return true;
	}
</script>
</@htmlPage>