<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="标定计划维护">
 <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        width:80%;
    }
    
    .definePersondLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        width:30%;
    }
    
   .definedDemoLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        width:20%;
    }
  </style>
    <@ww.form namespace="'/runmaintenance/tooling/record'" name="'demarcatePlan'" action="'saveDemarcatePlan'" method="'post'" >
        <@ww.token name="saveDemarcatePlanToken"/> 
        <@inputTable>
        	<tr>
        		<@ww.textfield label="'标定计划编号'" name="'demarcatePlan.planNo'" value="" cssClass="'underline'" required="true"/>				       
			    <@ww.textfield label="'标定计划名称'" name="'demarcatePlan.planName'" value="" cssClass="'underline'"/>			
        	</tr>
            <tr>
	        	<@pp.datePicker label="'计划执行日期'" name="'repair.estimate.time0'"
			     			value="" cssClass="'underline'" size="15" required="true"/>			       
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
		 	  	<@ww.textarea label="'备注'" 
					         name="'demarcatePlan.content'" 
					         value="" rows="'3'" cols="'60'" 
							 />
				<@ww.checkbox label="'录入实施'"  />
		    </tr> 
		    <tr><td colspan="6"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
		    <tr>
		        <@ww.textfield label="'编制人'" name="'demarcatePlan.planNo'" value="" cssClass="'underline'" required="true"/>				       
			    <@ww.textfield label="'审核人'" name="'demarcatePlan.planName'" value="" cssClass="'underline'" required="true"/>	
			    <@ww.textfield label="'批准人'" name="'demarcatePlan.planNo'" value="" cssClass="'underline'" required="true"/>			
		    </tr>          
        </@inputTable>
        <@buttonBar>
	      <@vsubmit name="'save'" value="'${action.getText('save')}'" />
	      <@redirectButton name="back" value="${action.getText('back')}" url="${req.contextPath}/runmaintenance/tooling/record/listDemarcatePlans.html"/>
	    </@buttonBar>
	     <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" value="true"></th>
            	<th>项目号</th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>制造单位</th>
			 	<th>使用单位</th>
			 	<th>责任人</th>
			 	<th>本次标定日期</th>
			 	<th>标定周期(月)</th>
			 	<th>下次标定日期</th>
			 	<th class="definedDemoLength">备注</th>
			<tr>
			<tr>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">1</td>
				<td style="text-align:left">AMS10L-1</td>
				<td style="text-align:left">左前纵梁总成补焊</td>
				<td style="text-align:left">AMS10L</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left" >
				<input type="text"
                                   name="demarDetail.thisDemarcateTime"
                                   size="15"                    
                                   value="2007-11-25"                     
                                   id="demarDetail.thisDemarcateTime"                        
                                   class="definedLength"     disabled  />                    
                        <img id="demarcatePlanDetail_demarDetail.thisDemarcateTime_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
                        <script language="javascript">
                        Calendar.setup({
                                        formName       :    "demarcatePlanDetail",
                                        inputField     :    "demarDetail.thisDemarcateTime",
                                        button         :    "demarcatePlanDetail_demarDetail.thisDemarcateTime_trigger",
                                        ifFormat       :  "%Y-%m-%d",
                                        showsTime      :   false,
                                        timeFormat     :    "24",
                                        showOthers     :   true
                                       });
                       </script>                   </td>
				<td style="text-align:middle"><input type="text" name="spareDetail.unitPrice" value="12"  class="definedLength"  /></td>
				<td style="text-align:left">
				<input type="text"
                               name="demarDetail.nextDemarcateTime"
                               size="15"                    
                               value="2007-11-26"                     
                               id="demarDetail.nextDemarcateTime"                         
                               class="definedLength"     disabled  />

                       <img id="demarcatePlanDetail_demarDetail.nextDemarcateTime_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
                       <script language="javascript">
                         Calendar.setup({
                                          formName       :    "demarcatePlanDetail",
                                          inputField     :    "demarDetail.nextDemarcateTime",
                                          button         :    "demarcatePlanDetail_demarDetail.nextDemarcateTime_trigger",
                                          ifFormat       :  "%Y-%m-%d",
                                          showsTime      :   false,
                                          timeFormat     :    "24",
                                          showOthers     :   true
                                         });
                       </script>                
				</td>
				<td style="text-align:middle" class="definedDemoLength"><input type="text" name="spareDetail.unitPrice" value=""  class="definedLength"  /></td>
			</tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">2</td>
				<td style="text-align:left">AMS10L</td>
				<td style="text-align:left">左前纵梁总成 </td>
				<td style="text-align:left">AMS10L</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left" >
				<input type="text"
                                   name="demarDetail.thisDemarcateTime1"
                                   size="15"                    
                                   value="2007-11-25"                     
                                   id="demarDetail.thisDemarcateTime1"                        
                                   class="definedLength"     disabled  />                    
                        <img id="demarcatePlanDetail_demarDetail.thisDemarcateTime1_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
                        <script language="javascript">
                        Calendar.setup({
                                        formName       :    "demarcatePlanDetail",
                                        inputField     :    "demarDetail.thisDemarcateTime1",
                                        button         :    "demarcatePlanDetail_demarDetail.thisDemarcateTime1_trigger",
                                        ifFormat       :  "%Y-%m-%d",
                                        showsTime      :   false,
                                        timeFormat     :    "24",
                                        showOthers     :   true
                                       });
                       </script>                   </td>
				<td style="text-align:middle"><input type="text" name="spareDetail.unitPrice" value="12"  class="definedLength"  /></td>
				<td style="text-align:left">
				<input type="text"
                               name="demarDetail.nextDemarcateTime1"
                               size="15"                    
                               value="2007-11-26"                     
                               id="demarDetail.nextDemarcateTime1"                         
                               class="definedLength"     disabled  />

                       <img id="demarcatePlanDetail_demarDetail.nextDemarcateTime1_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
                       <script language="javascript">
                         Calendar.setup({
                                          formName       :    "demarcatePlanDetail",
                                          inputField     :    "demarDetail.nextDemarcateTime1",
                                          button         :    "demarcatePlanDetail_demarDetail.nextDemarcateTime1_trigger",
                                          ifFormat       :  "%Y-%m-%d",
                                          showsTime      :   false,
                                          timeFormat     :    "24",
                                          showOthers     :   true
                                         });
                       </script>                
				</td>
				<td style="text-align:middle" class="definedDemoLength"><input type="text" name="spareDetail.unitPrice" value=""  class="definedLength"  /></td>
			</tr>
	     	</@listTable>
	     	<@buttonBar>
	          <@vsubmit name="'save'" value="'新增'" />
	          <@vsubmit name="back" value="'删除'" />
	        </@buttonBar>
    </@ww.form>
</@htmlPage>