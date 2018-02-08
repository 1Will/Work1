<#--$Id: syncHistoryList.ftl 8874 2007-12-02 02:47:00Z qsun $ -->
<#include "../../includes/eam2008.ftl" />

<@htmlPage title="数据同步查询">
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 	<@inputTable>
	 		<tr>
	 			<@pp.dateRanger label="'${action.getText('sync.time0')}'" name="'procExecTime'" 
			                  value="'${req.getParameter('procExecTime_start')?if_exists}|${req.getParameter('procExecTime_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>			                  	 			
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
	    	<@redirectButton value="${action.getText('sync.settings')}" url="${req.contextPath}/dataimport/mrpsync/editSyncProfile.html"/>
	    	</@buttonBar>
     </@ww.form>
</@htmlPage>