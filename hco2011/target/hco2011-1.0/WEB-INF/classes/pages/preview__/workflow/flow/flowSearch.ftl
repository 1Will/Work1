<#--
	Copyright (c) 2001-2010 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->

<#-- $Id: custContactReportSearch.ftl 2010-04-06 wliu $ -->

<#include "../../../includes/macros.ftl" />

<@inputTable>
	<tr>
   	    <@ww.textfield label="'${action.getText('流程名称')}'" 
		                name="'flowName'" 
		                value="'${req.getParameter('flowName')?if_exists}'" 
		                cssClass="'underline'"/>
		
   	    <td align="right"><label for="" class="label">${action.getText('是否启用')}:</label></td>
		<td align="left">
        	<input type="radio" id="agree" name="advisory.isNoBack" value="0" checked>是
        	<input type="radio" id="notAgree" name="advisory.isNoBack" value="1">否
        	<script language="javascript">
	     		if(getObjByName('agree').value=='true'){
	     			getObjByName('agree').checked = true;
	     		}else if(getObjByName('notAgree').value=='false'){
	     			getObjByName('notAgree').checked = true;
	     		}
			</script>
		</td>
		
	    <@ww.textfield label="'${action.getText('工作时限')}'" 
		                name="'time'" 
		                value="'${req.getParameter('time')?if_exists}'" 
		                cssClass="'underline'"/> 
	</tr>
		 
	    <#-- 
	    <@pp.dateRanger 
			label="'${action.getText('CustContactReport_visitdate')}'" 
			name="'day'" 
    		value="'${req.getParameter('day_start')?if_exists}|${req.getParameter('day_end')?if_exists}'"
     		cssClass="'underline'" 
     		maxlength="10" >
     	</@pp.dateRanger>
     	
        <script language="javascript">
	        <#if first>
	        	if(getObjByName("day_start").value==""){
		        	var date = new Date();
					getObjByName("day_start").value = date.format("yyyy-MM-dd");
				}
			</#if>
		</script>
		
		<@ww.textfield label="'${action.getText('CustContactReport_pioneer')}'" name="'pioneer'" value="'${req.getParameter('pioneer')?if_exists}'" cssClass="'underline'"/>
		<@ww.textfield label="'${action.getText('CustContactReport_servingPeople')}'" name="'servingPeople'" value="'${req.getParameter('servingPeople')?if_exists}'" cssClass="'underline'"/>
	    -->
</@inputTable>
<script language="javascript">
function checkInvalidParms(){
	if(!queryDate("day_start","day_end",'${action.getText('dateFormate.error')}',null)){
		return false;
	}
	return true;
}
</script>