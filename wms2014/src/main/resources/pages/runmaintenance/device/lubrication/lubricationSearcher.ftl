<@inputTable>
	 		<tr>
	 		    <@ww.textfield label="'${action.getText('lubricationPlan.planNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
			 	<@ww.textfield label="'${action.getText('lubricationPlan.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'"/>
	        </tr>
	        <tr>
			 	<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		        </@ww.select>
		        <@pp.datePicker label="'${action.getText('month')}'" name="'month'" 
		       		value="'${req.getParameter('month')?if_exists}'" cssClass="'underline'" size="15" 
	     			dateFormat="'%Y-%m'"/>
	        </tr>	   
	        <tr>
	            <@ww.textfield label="'${action.getText('planCreator')}'" name="'planCreator.name'" value="'${req.getParameter('planCreator.name')?if_exists}'" cssClass="'underline'" />
	            <@pp.dateRanger label="'${action.getText('planCreatedTime')}'" name="'planCreatedTime'" 
		                        value="'${req.getParameter('planCreatedTime_start')?if_exists}|${req.getParameter('planCreatedTime_end')?if_exists}'"
		                        cssClass="'underline'"/>
			</tr>
			<#if planProcFlag?exists>
	          <#if (planProcFlag=='PROC')>
		        <tr>
			      <@ww.textfield label="'${action.getText('reporter')}'" name="'reportor.name'" value="'${req.getParameter('reportor.name')?if_exists}'" cssClass="'underline'" />
		    	  <@pp.dateRanger label="'${action.getText('reportCreatedTime')}'" name="'reportDate'" 
			                    value="'${req.getParameter('reportDate_start')?if_exists}|${req.getParameter('reportDate_end')?if_exists}'"
			                    cssClass="'underline'"/>
		        </tr>
	          </#if>
	        </#if>
		    <script language="javascript">
		        var selector = document.all("department.id");
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
		     
		     function checkInvalidParms() {
		       if (getObjByNameRe("department.id").value == -1) {
		  		  getObjByNameRe("department.id").value = '';
			   }
			<#if planProcFlag?exists>
	          <#if (planProcFlag=='PROC')>
	           beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	             if(!queryDate("planCreatedTime_start","planCreatedTime_end",
	              beginDateMsg,null)){
	               return false;
	            }
	            beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	              if(!queryDate("reportDate_start","reportDate_end",
	                    beginDateMsg,null)){
	                      return false;
	            }
	          <#else>
	             beginDateMsg="${action.getText('beginDateTime')}" + "${action.getText('dateFormate.error')}";
	              if(!queryDate("planCreatedTime_start","planCreatedTime_end",
	                    beginDateMsg,null)){
	                      return false;
	            }
	            </#if>
	         </#if>
			   return true;
		     }
		   </script>
</@inputTable> 