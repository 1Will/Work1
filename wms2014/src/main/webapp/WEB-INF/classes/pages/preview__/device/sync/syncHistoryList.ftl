<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/macros2.ftl" />
<#include "/javascripts/selectAll.js" />

<@htmlPage title="数据同步查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
	 			<@pp.datePicker label="'${action.getText('sync.time0')}'" name="'repair.estimate.time0'"
	     			value="" cssClass="'underline'" size="15"/>
	     			
	     		<@pp.datePicker label="'${action.getText('sync.time1')}'" name="'repair.estimate.time1'"
	     			value="" cssClass="'underline'" size="15"/>
	        </tr>
        </@inputTable>
        <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>
        
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
		 	<th  >${action.getText('sync.time0')}</th>
		 	<th  >${action.getText('sync.time1')}</th>
		 	<th  >${action.getText('sync.operator')}</th>
		 	<th  >${action.getText('sync.result')}</th>
		 	</tr>
			<tr>
				<td >2004-03-23 11:23</td>
				<td >2004-03-23 12:15</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">OK</td>
			</tr>
			<tr>
				<td>2004-03-23 11:23</td>
				<td>2004-03-23 12:00</td>
				<td style="text-align:left">system</td>
				<td style="text-align:left">OK</td>
			</tr>
			<tr>
				<td>2004-03-23 11:23</td>
				<td>2004-03-23 12:30</td>
				<td style="text-align:left">jack</td>
				<td style="text-align:left">NG</td>
			</tr>			
	     	</@listTable>
	     	</table>
	     
	     	<@buttonBar>
	    	<@redirectButton value="${action.getText('sync.immediate')}" url="#"/>
	    	<@redirectButton value="${action.getText('sync.settings')}" url="${req.contextPath}/preview/device/sync/editSyncProfile.html"/>
	    	</@buttonBar>
     </@ww.form>
</@htmlPage>