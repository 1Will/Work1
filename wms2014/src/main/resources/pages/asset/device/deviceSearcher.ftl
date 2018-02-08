<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
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
<#-- $Id: -->

<@inputTable>
	<@ww.hidden name="'invalid'" value="false"/>
	<@ww.hidden name="'includeAllType'" value="true"/>
	<@ww.hidden name="'includeValid'" value="true"/>
	<@ww.hidden name="'yesFull'" value=""/>
	<@ww.hidden name="'noFull'" value=""/>
	<@ww.hidden name="'yesStandard'" value=""/>
	<@ww.hidden name="'noStandard'" value=""/>
	<@ww.hidden name="'yesEmphasis'" value=""/>
	<@ww.hidden name="'noEmphasis'" value=""/>
    <tr>
        <@ww.textfield label="'${action.getText('device.no')}'" name="'deviceNo'" value="'${req.getParameter('deviceNo')?if_exists}'" cssClass="'underline'"/>
        <@ww.textfield label="'${action.getText('device.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
    	<@ww.select label="'${action.getText('device.status')}'" required="false" name="'deivceStatus.code'" 
    			  value="'${req.getParameter('deivceStatus.code')?if_exists}'" listKey="code" listValue="value"
                  list="deviceStatus" emptyOption="false" disabled="false">
        </@ww.select> 
    </tr>
    <tr>
    	<@ww.select label="'${action.getText('device.category')}'"  name="'category.id'" 
    				listKey="id" listValue="name"
    			    list="deviceTypes" emptyOption="false" disabled="false">
        </@ww.select>  
        <@ww.select label="'${action.getText('device.filiale')}'" required="false" name="'filiale.id'" 
    			  value="'${req.getParameter('filiale.id')?if_exists}'" listKey="id" listValue="name"
                  list="filiales" emptyOption="false" disabled="false" onchange="'filialeSelectDeptDWR()'">
        </@ww.select>
    	<@ww.select label="'${action.getText('department')}'"  name="'department.id'"  listKey="id" listValue="name"
    			 value="${req.getParameter('department.id')?if_exists}"
                list="departments" emptyOption="false" disabled="false">
        </@ww.select>
        
            
     </tr>
     <tr>
       <@ww.textfield label="'${action.getText('managerPep')}'" name="'manager'" value="'${req.getParameter('manager')?if_exists}'" cssClass="'underline'"/>
        <@ww.select label="'${action.getText('device.useCategory')}'" required="false" name="'useCategory.code'" 
    			  value="'${req.getParameter('useCategory.code')?if_exists}'" listKey="code" listValue="value"
                  list="useCategory" emptyOption="false" disabled="false">
        </@ww.select>
        <@ww.select label="'${action.getText('device.specificationProp')}'" required="false" name="'specificationProp.code'" 
    			  value="'${req.getParameter('specificationProp.code')?if_exists}'" listKey="code" listValue="value"
                  list="specificationProp" emptyOption="false" disabled="false">
        </@ww.select>
	</tr>
	<tr>
		<td align="right" valign="top"><label class="label">${action.getText('device.emphasis')}:</label></td>
           <td>
             <select name="device.emphasis" >       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('YES')}</option>       
               <option value="false">${action.getText('NO')}</option>              
            </select>
           </td>
		<td align="right" valign="top"><label class="label">${action.getText('device.full')}:</label></td>
           <td>
             <select name="device.full" >       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('YES')}</option>       
               <option value="false">${action.getText('NO')}</option>              
            </select>
           </td>
	
       <td align="right" valign="top"><label class="label">${action.getText('device.standard')}:</label></td>
           <td>
             <select name="device.standard" >       
               <option value="" >${action.getText('select.option.all')}</option>       
               <option value="true">${action.getText('YES')}</option>       
               <option value="false">${action.getText('NO')}</option>              
            </select>
           </td>
      </tr>
     <tr>
    	<@pp.dateRanger label="'${action.getText('device.cardCreatedTime')}'" name="'cardCreatedTime'" 
		                value="'${req.getParameter('cardCreatedTime_start')?if_exists}|${req.getParameter('cardCreatedTime_end')?if_exists}'"
		                cssClass="'underline'" dateFormat="date"/>
		<@ww.select label="'${action.getText('device.managementLevel')}'" 
                   required="false" name="'device.managementLevel'" 
                   value="${req.getParameter('device.managementLevel')?if_exists}"
                   listKey="value" listValue="label"
                   list="managementLevels" 
                   emptyOption="false" 
                   disabled="false">
        </@ww.select>     
    	<@eam2008_onlySearchInvalid_checkBox/>
    </tr>
    
	<script language="javascript">
	
        var selector = document.all("department.id");
        var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('department.id')?exists>
            if (selector.options[i].value=="${req.getParameter('department.id')?if_exists}") {
               selector.options[i].selected="true";
            }
            </#if>
        }  
        //分公司 与 部门级联 04/09/2009
	   selector = document.all("filiale.id");
	  groups = selector.options.length;
	  for (i=0; i<groups; i++) {
	    <#if req.getParameter('filiale.id')?exists>
	    if (selector.options[i].value == "${req.getParameter('filiale.id')?if_exists}") {
	      selector.options[i].selected="true";
	    }
	    </#if>
	  }
        
        
        selector = document.all("category.id");
         var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('category.id')?exists>
            if (selector.options[i].value=="${req.getParameter('category.id')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        }  
        
        selector = document.all("device.managementLevel");
        var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('device.managementLevel')?exists>
            if (selector.options[i].value=="${req.getParameter('device.managementLevel')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        } 
        selector = document.all("deivceStatus.code");
        var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('deivceStatus.code')?exists>
            if (selector.options[i].value=="${req.getParameter('deivceStatus.code')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        } 
        
        selector = document.all("useCategory.code");
        var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('useCategory.code')?exists>
            if (selector.options[i].value=="${req.getParameter('useCategory.code')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        } 
        
        selector = document.all("specificationProp.code");
        var groups=selector.options.length;  
        for (i=0; i<groups; i++){
            <#if req.getParameter('specificationProp.code')?exists>
            if (selector.options[i].value=="${req.getParameter('specificationProp.code')?if_exists}"){
               selector.options[i].selected="true";
            }
            </#if>
        }
        
        selector = document.all("device.full");
		var groups = selector.options.length;
		for (i=0; i<groups; i++) {
		    <#if req.getParameter('device.full')?exists>
		    if (selector.options[i].value == "${req.getParameter('device.full')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  } 
		  
		selector = document.all("device.standard");
		var groups = selector.options.length;
		for (i=0; i<groups; i++) {
		    <#if req.getParameter('device.standard')?exists>
		    if (selector.options[i].value == "${req.getParameter('device.standard')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  }
		selector = document.all("device.emphasis");
		var groups = selector.options.length;
		for (i=0; i<groups; i++) {
		    <#if req.getParameter('device.emphasis')?exists>
		    if (selector.options[i].value == "${req.getParameter('device.emphasis')?if_exists}") {
		      selector.options[i].selected="true";
		    }
		    </#if>
		  } 
        
        function checkInvalidParms() {
        	var startDate = getObjByNameRe("cardCreatedTime_start").value;
        	var endDate = getObjByNameRe("cardCreatedTime_end").value;
        	var strStartDate = new String(startDate);
        	var strEndDate = new String(endDate);		    		
        	if (getObjByNameRe("department.id").value==-1) {
        		//getObjByNameRe("invalid").value=true;
        		getObjByNameRe("department.id").value='';
        	} else {
        		getObjByNameRe("invalid").value=false;
        	}
        	//分公司 与部门级联 04/09/2009
		     if (-1 == getObjByNameRe("filiale.id").value) {
				getObjByNameRe("filiale.id").value = '';
		    }
        	
        	if (getObjByNameRe("category.id").value==-1) {
        		getObjByNameRe("category.id").value='';
        	} 
        	if (getObjByNameRe("device.managementLevel").value == -1) {
        	  getObjByNameRe("device.managementLevel").value='';
        	}
        	if (getObjByNameRe("deivceStatus.code").value == -1) {
        	  getObjByNameRe("deivceStatus.code").value='';
        	}
        	if (getObjByNameRe("useCategory.code").value == -1) {
        	  getObjByNameRe("useCategory.code").value='';
        	}
        	if (getObjByNameRe("specificationProp.code").value == -1) {
        	  getObjByNameRe("specificationProp.code").value='';
        	}
        	if (getObjByNameRe("device.full").value == 'true') {
			  getObjByNameRe("yesFull").value = 'true';
			} else if (getObjByNameRe("device.full").value == 'false') {
			   getObjByNameRe("noFull").value = 'false';
			}
			if (getObjByNameRe("device.standard").value == 'true') {
			  getObjByNameRe("yesStandard").value = 'true';
			} else if (getObjByNameRe("device.standard").value == 'false') {
			   getObjByNameRe("noStandard").value = 'false';
			}
			if (getObjByNameRe("device.emphasis").value == 'true') {
			  getObjByNameRe("yesEmphasis").value = 'true';
			} else if (getObjByNameRe("device.emphasis").value == 'false') {
			   getObjByNameRe("noEmphasis").value = 'false';
			}
        	if (strStartDate!='') {
	        	if (!isDate("cardCreatedTime_start")) {
	        	  alert("${action.getText('device.cardCreatedTime')}" + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if (strEndDate!='') {
	            if (!isDate("cardCreatedTime_end")) {
	        	  alert("${action.getText('device.cardCreatedTime')}" + "${action.getText('dateFormate.error')}");
	        	  return false;
	        	}
	        }
	        if ((strStartDate!='' && strEndDate!='')) {
	        	if (strStartDate > strEndDate) {
	        		alert("${action.getText('cardCreatedTime.order.error')}");
	        		return false;
	        	}
	        }
		    return true;
		}
    </script>
</@inputTable>