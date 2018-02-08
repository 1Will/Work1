<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="工装标定规则查询">
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
  </style>
	 <@ww.form name="'listForm'" action="'searchProducts'" method="'post'">
	 <#include "demarcateRuleSearcher.ftl"/>
         <@buttonBar>        
        	<@redirectButton value="${action.getText('search')}" url="#"/>
        </@buttonBar>      
            <table width="100%" border="0" cellspacing="2" cellpadding="0" class="w01_11px_grey">
            <@listTable>
            <tr>
            	<th><input type="checkbox" name="checkbox" onClick="selectedAll()" value="true"></th>
			 	<th>工装编号</th>
			 	<th>工装名称</th>
			 	<th>工装图号</th>
			 	<th>制造单位</th>
			 	<th>使用单位</th>
			 	<th>责任人</th>
			 	<th>本次标定日期</th>
			 	<th>标定周期(月)</th>
			<tr>
			<tr>
			    <link rel="stylesheet" href="/eam2008/stylesheets/calendar.css" type="text/css"/>
                <script language="javascript" src="/eam2008/javascripts/calendar.js"></script>
                <script language="javascript" src="/eam2008/javascripts/lang/calendar.js"></script>
                <script language="javascript" src="/eam2008/javascripts/calendar-setup.js"></script>	
				<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">AMS10L-1</td>
				<td style="text-align:left">左前纵梁总成补焊</td>
				<td style="text-align:left">AMS10L</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">
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
                       </script>          
				</td>
				<td style="text-align:middle"><input type="text" name="spareDetail.unitPrice" value="12"  class="definedLength"  /></td>
			</tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">AMS11L</td>
				<td style="text-align:left">左前纵梁总成补焊</td>
				<td style="text-align:left">AMS10L</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">
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
                       </script>          
				</td>
				<td style="text-align:middle"><input type="text" name="spareDetail.unitPrice" value="12"  class="definedLength"  /></td>
			</tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">AMS11R</td>
				<td style="text-align:left">右前纵梁外板总成</td>
				<td style="text-align:left">AMS11R</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">
				<input type="text"
                                   name="demarDetail.thisDemarcateTime2"
                                   size="15"                    
                                   value="2007-11-25"                     
                                   id="demarDetail.thisDemarcateTime2"                        
                                   class="definedLength"     disabled  />                    
                        <img id="demarcatePlanDetail_demarDetail.thisDemarcateTime2_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
                        <script language="javascript">
                        Calendar.setup({
                                        formName       :    "demarcatePlanDetail",
                                        inputField     :    "demarDetail.thisDemarcateTime2",
                                        button         :    "demarcatePlanDetail_demarDetail.thisDemarcateTime2_trigger",
                                        ifFormat       :  "%Y-%m-%d",
                                        showsTime      :   false,
                                        timeFormat     :    "24",
                                        showOthers     :   true
                                       });
                       </script>          
				</td>
				<td style="text-align:middle"><input type="text" name="spareDetail.unitPrice" value="12"  class="definedLength"  /></td>
			</tr>
			<tr>
			<td><input type="checkbox" name="checkbox" value="true"></td>
				<td style="text-align:left">AMS15L</td>
				<td style="text-align:left">前纵梁内板内加强板总成补焊</td>
				<td style="text-align:left">AMS15L</td>
				<td style="text-align:left">天津福臻</td>
				<td style="text-align:left">凌江</td>
				<td style="text-align:left">郑广慧</td>
				<td style="text-align:left">
				<input type="text"
                                   name="demarDetail.thisDemarcateTime3"
                                   size="15"                    
                                   value="2007-11-25"                     
                                   id="demarDetail.thisDemarcateTime3"                        
                                   class="definedLength"     disabled  />                    
                        <img id="demarcatePlanDetail_demarDetail.thisDemarcateTime3_trigger" src="/eam2008/images/calendar/calendar.gif" align=absMiddle border=0 />
                        <script language="javascript">
                        Calendar.setup({
                                        formName       :    "demarcatePlanDetail",
                                        inputField     :    "demarDetail.thisDemarcateTime3",
                                        button         :    "demarcatePlanDetail_demarDetail.thisDemarcateTime3_trigger",
                                        ifFormat       :  "%Y-%m-%d",
                                        showsTime      :   false,
                                        timeFormat     :    "24",
                                        showOthers     :   true
                                       });
                       </script>          
				</td>
				<td style="text-align:middle"><input type="text" name="spareDetail.unitPrice" value="12"  class="definedLength"  /></td>
			</tr>
	     	</@listTable>
	     	</table> 
     <@buttonBar>
     	<@vbutton value="保存" class="button"/>
     </@buttonBar>
     </@ww.form>
</@htmlPage>