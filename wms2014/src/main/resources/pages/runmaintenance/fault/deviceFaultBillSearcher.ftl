<@inputTable>
		<@ww.hidden name="'toolingDevFlag'" value=""/>
        <@ww.hidden name="'solutionStatus'" value=""/>
        <@ww.hidden name="'noSolutionStatus'" value=""/>
        <@ww.hidden name="'faultBillType_fault'" value=""/>
        <@ww.hidden name="'faultBillType_accident'" value=""/>
		<tr>
			<@ww.textfield label="'${action.getText('faultBillNo')}'" name="'billNo'" value="'${req.getParameter('billNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('faultBillName')}'" name="'billName'" value="'${req.getParameter('billName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('device.name')}'" name="'deviceName'" value="'${req.getParameter('deviceName')?if_exists}'" cssClass="'underline'"/>
	 	</tr>
	 	<tr>
    	    <@pp.dateRanger label="'${action.getText('faultOccurDateTime')}'" name="'faultOccurDateTm'" 
		       value="'${req.getParameter('faultOccurDateTm_start')?if_exists}|${req.getParameter('faultOccurDateTm_end')?if_exists}'"
		       cssClass="'underline'" maxlength="10"/> 
		    <@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
	    </tr>
		<tr>	    
	    	<td align="right" valign="top"><label  for="searchFaultBills_Type.code" class="label">${action.getText('faultBillCategory')}:</label></td>
           <td>
             <select name="device.faultBillType" id="search_faultBillType.code">       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="FAULT">${action.getText('faultBillType.FAULT')}</option>       
               <option value="ACCIDENT">${action.getText('faultBillType.ACCIDENT')}</option>              
             </select>
           </td>
	    
	       <td align="right" valign="top"><label  for="searchFaultBills_toolingStatus.code" class="label">${action.getText('faultBillstatus')}:</label></td>
           <td>
             <select name="device.faultBillstatus" id="searchToolingBorrowBills_toolingStatus.code">       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('faultBill.solution')}</option>       
               <option value="false">${action.getText('faultBill.noSolution')}</option>              
            </select>
           </td>
	     </tr>	
	     <tr>	
 			<@ww.select label="'${action.getText('faultCatorgy')}'" name="'faultBill.faultCatorgy'"
		    		listKey="id" listValue="value" value="'${(faultBill.faultCatorgy)?if_exists}'"
		    		list="faultCatorgys" 
		    		required="false">
		    	</@ww.select>
	     	<@eam2008_onlySearchInvalid_checkBox/>
           </tr>
	</@inputTable>
	
		<script language="javascript">
		  getObjByNameRe("toolingDevFlag").value = 'false';
		  selector = document.all("department.id");
		  groups = selector.options.length;
		  for (i=0; i<groups; i++) {
		    <#if req.getParameter('department.id')?exists>
		    if (selector.options[i].value == "${req.getParameter('department.id')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  } 
		  <#if first>
		    <#if loginUser.department?exists>
		      getObjByNameRe("department.id").value = #{loginUser.department.id};
		    </#if>
		  </#if>
		  selector = document.all("device.faultBillstatus");
		  groups = selector.options.length;
		  for (i=0; i<groups; i++) {
		    <#if req.getParameter('device.faultBillstatus')?exists>
		    if (selector.options[i].value == "${req.getParameter('device.faultBillstatus')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  }
		  
		  selector = document.all("faultBill.faultCatorgy");
		  groups = selector.options.length;
		  for(i=0;i<groups;i++){
		  	<#if req.getParameter('faultBill.faultCatorgy')?exists>
		  		if(selector.options[i].value== "${req.getParameter('faultBill.faultCatorgy')?if_exists}"){
		  			 selector.options[i].selected="true";
		  		}
		  	</#if>
		  }
		  
		  selector = document.all("device.faultBillType");
		  groups = selector.options.length;
		  for (i=0; i<groups; i++) {
		    <#if req.getParameter('device.faultBillType')?exists>
		    if (selector.options[i].value == "${req.getParameter('device.faultBillType')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  }
		  
		  function checkInvalidParms() {
			if (getObjByNameRe("department.id").value == -1) {
			  getObjByNameRe("department.id").value = '';
			}
			if (getObjByNameRe("device.faultBillstatus").value == 'true') {
			  getObjByNameRe("solutionStatus").value = 'true';
			} else if (getObjByNameRe("device.faultBillstatus").value == 'false') {
			   getObjByNameRe("noSolutionStatus").value = 'false';
			}
			
			if (getObjByNameRe("device.faultBillType").value == 'FAULT') {
			  getObjByNameRe("faultBillType_fault").value = 'FAULT';
			} else if (getObjByNameRe("device.faultBillType").value == 'ACCIDENT') {
			   getObjByNameRe("faultBillType_accident").value = 'ACCIDENT';
			}
			
		    //验证故障发生日期格式
		    beginDateMsg="${action.getText('faultOccurDateTm')}" + "${action.getText('dateFormate.error')}";
			if(!queryDate("faultOccurDateTm_start","faultOccurDateTm_end",
			    beginDateMsg,null)){
			  return false;
			}	
			return true;
		  }
		
		</script>  
	