<#include "../../../../includes/eam2008.ftl" />
<@htmlPage title="工装点检卡">
	 <@ww.form name="'listForm'" action="''" method="'post'">
	    <@buttonBar>
        	<input type="button" class="button" name="selectRule" value="选择点检标准" onclick="checkPointRule_openDialog()" />
    	</@buttonBar>
	      <@inputTable>
	        <tr>
               <@ww.textfield label="'点检计划编号'" name="'ruleNo'" value="" cssClass="'underline'"/>
               <@ww.textfield label="'点检计划名称'" name="'name'" value="" cssClass="'underline'"/>
            </tr>
            <tr>
               <@eam2008_ToolingSelector /> 
            </tr>
            <tr>
                <@ww.textfield label="'工序名称'" name="'plan.name'"   value="" cssClass="'underline'" />
                <@ww.textfield label="'产品名称'" name="'plan.name'"   value="" cssClass="'underline'"  />
            </tr>
            <tr>
               <@pp.datePicker label="'计划日期'" name="'scheduleTime'" 
		            value=""
		            cssClass="'underline'" dateFormat="date"/> 
		       <@ww.select label="'部门'"
	                    	name="device"
	                   	 	headerKey="1" 
	                    	headerValue="Select Month"
	                    	list="{
	                    			'机动部', 
	                    			'生产部'
	                    	  	  }"
	                    	value="selectedDevice"
	           /> 
            </tr>
            <tr>
                <td align="right" valign="top">
                    <span class="required"></span><label class="label">操作人:</label>
                </td>
                <td>
                   <input type="text" name="supplier.supplierNo" class="underline"  value=""  maxlength="150" disabled/>
                   <a onClick='eam2008_supplier_OpenDialog("${req.contextPath}/popup/userSelector.html")'>
	                   <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	               </a>
	               <input type="text" style="border:0px" name="supplier.name" disabled value=""/>
                </td>
                <td align="right" valign="top">
                    <span class="required"></span><label class="label">维修人:</label>
                </td>
                <td>
                   <input type="text" name="supplier.supplierNo" class="underline"  value=""  maxlength="150" disabled/>
                   <a onClick='eam2008_supplier_OpenDialog("${req.contextPath}/popup/userSelector.html")'>
	                   <img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	               </a>
	               <input type="text" style="border:0px" name="supplier.name" disabled value=""/>
                </td>
            </tr>
          </@inputTable>  
          <@buttonBar>
		 	<@vsubmit value="'${action.getText('save')}'" />
        	<@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/tooling/checkpoint/listCheckPointPlan.html"/>	
         </@buttonBar> 
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox"  value="true"></th>
			 	<th>日期</th>
			 	<th>表面清洁</th>
			 	<th>润滑可靠</th>
			 	<th>定位销</th>
			 	<th>型面良好</th>
			 	<th>夹紧自如</th>
			 	<th>控制可靠</th>
			 	<th>其它</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">1</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">2</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">损坏</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
			</tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">3</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">损坏</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
				<td style="text-align:left">良好</td>
			</tr>
	     	</@listTable>
	     	</table> 
     <@buttonBar>
     	<@vbutton value="保存" class="button" />
     </@buttonBar>
     </@ww.form>
</@htmlPage>