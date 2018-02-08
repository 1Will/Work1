<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spareInHistory.title')}">
 <base target="_self">
<@ww.form name="'spares'" action="'searchSpareInHistory'" method="'post'">
	 <@ww.token name="searchSpareInHistoryToken"/>
	 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
   	 <@inputTable>
	      <tr>
		      <@ww.hidden name="'spare.id'" value="#{spare.id?if_exists}"/>
		      <@pp.dateRanger label="'${action.getText('spareInHistory.sender_date')}'" name="'procExecTime'" 
			                  value="'${req.getParameter('procExecTime_start')?if_exists}|${req.getParameter('procExecTime_end')?if_exists}'"
			                  cssClass="'underline'" dateFormat="date"/>
	     </tr>
    </@inputTable> 
	 <@buttonBar>
	 	<@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return validateExcuteTime();'"/>
     </@buttonBar>
     <@list title="${action.getText('spareInHistory.list')}" 
                   includeParameters="id|spare.id|procExecTime_start|procExecTime_end" 
                   fieldMap="like:id,date:procExecTime_start|procExecTime_end" >
            <@vcolumn title="${action.getText('spareInHistory.spare_inout_bill_id')}" property="bill.billNo" sortable="desc">
              <a href="#" onclick="popupModalDialog('${req.contextPath}/asset/spare/editSpareIn.html?spareInBill.id=#{object.bill.id}&toolingDevFlag=${toolingDevFlag?if_exists}&popupPage='+'T'+'&readOnly=${req.getParameter('readOnly')?if_exists}',850,600);spares.submit();">${object.bill.billNo}</a>
              <@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('spare.spareNo')}" property="spare.spareNo" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.name')}" property="spare.name" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spare.en_name')}" property="spare.enName" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.modelSpecs')}" property="spare.modelSpecs" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.category')}" property="spare.category.value">
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.number')}" property="number" >
              <@alignRight/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.unit')}" property="spare.unit.value" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.auditor')}" property="auditor" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.comment')}" property="bill.comment" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('department')}" property="spare.department.name" >
              <@alignLeft/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.sender_date')}" property="sendDateTm" format="yyyy-MM-dd" >
              <@alignCenter/>
             </@vcolumn>
             <@vcolumn title="${action.getText('spareInHistory.audit_datetime')}" property="auditDateTm" format="yyyy-MM-dd" >
              <@alignCenter/>
             </@vcolumn>
        </@list>
     </@ww.form>
     <script language="JavaScript" type="text/JavaScript">
     function validateTime(s){ 
        if(!(s=='')){
        		var regu="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        		var re =new RegExp(regu);
        		if (!re.test(s)){
        			return false;
        		}
        		else{
        		   if(!validateProcTime(s)){
        		      return false;
        		   }
        		}
        	}
		     return true;
	}
	
	  function validateProcTime(validateTime){
                    var timeYear=validateTime.substring(0,validateTime.indexOf('-')).length;
                    var timeDay=validateTime.substring(validateTime.lastIndexOf('-')+1,validateTime.length).length;
                    if(timeYear>4)
                    {
                        return false;
                    }
                    if(timeDay>2)
                    {
                        return false;
                    }
                    return true;

     }
     
     function validateExcuteTime(){
          if(document.forms[0].elements["procExecTime_start"].value!=""){
              if(!validateTime(document.forms[0].elements["procExecTime_start"].value)){
                   alert("${action.getText('spareInHistory.startTimeMistake')}");
                   return false;
              }
          }
          if(document.forms[0].elements["procExecTime_end"].value!=""){
             if(!validateTime(document.forms[0].elements["procExecTime_end"].value)){
                   alert("${action.getText('spareInHistory.endTimeMistake')}");
                   return false;
              }
          }
          return true;
     }
	
     </script>
</@htmlPage>