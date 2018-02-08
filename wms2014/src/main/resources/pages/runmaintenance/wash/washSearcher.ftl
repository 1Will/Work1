	<@inputTable>
		<tr>
			<@ww.textfield label="'${action.getText('wash.planNo')}'" name="'planNo'" value="'${req.getParameter('planNo')?if_exists}'" cssClass="'underline'" />
	 		<@ww.textfield label="'${action.getText('wash.palnName')}'" name="'planName'" value="'${req.getParameter('planName')?if_exists}'" cssClass="'underline'"/>
		</tr>
		<tr>
	 		<@ww.select label="'${action.getText('department')}'" required="false" name="'department.id'" 
		    		value="'${req.getParameter('department.id')?if_exists}'" listKey="id" listValue="name"
		            list="departments" emptyOption="false" disabled="false">
		    </@ww.select>
	 		<@pp.dateRanger label="'${action.getText('wash.planBeginDate')}'" name="'palnBeginDate'" 
		                    value="'${req.getParameter('palnBeginDate_start')?if_exists}|${req.getParameter('palnBeginDate_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
	 	</tr>
	 	<tr>
	 		<@ww.textfield label="'${action.getText('wash.planCreator')}'" name="'planCreator'" value="'${req.getParameter('planCreator')?if_exists}'" cssClass="'underline'"/>
    	    <@pp.dateRanger label="'${action.getText('wash.planCreateDate')}'" name="'planCreateDate'" 
		                    value="'${req.getParameter('planCreateDate_start')?if_exists}|${req.getParameter('planCreateDate_end')?if_exists}'"
		                    cssClass="'underline'" maxlength="10"/> 
		</tr>
		<#if planProcFlag?exists>
          <#if planProcFlag == 'PROC'>
			<tr>
		 		<@ww.textfield label="'${action.getText('wash.reportor')}'" name="'reportor'" value="'${req.getParameter('reportor')?if_exists}'" cssClass="'underline'"/>
	    	    <@pp.dateRanger label="'${action.getText('wash.reportDate')}'" name="'reportDate'" 
			                    value="'${req.getParameter('reportDate_start')?if_exists}|${req.getParameter('reportDate_end')?if_exists}'" 
			                    cssClass="'underline'"/> 
			</tr>
		  </#if>
		</#if>
	</@inputTable>
	<script language="javascript">
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
	  function checkInvalidParms() {
		if (getObjByNameRe("department.id").value == -1) {
		  getObjByNameRe("department.id").value = '';
		}
		palnBeginDateFormatMsg="${action.getText('select.right.wash.planBeginDate')}";
		palnBeginDateOrderMsg="${action.getText('planBeginDate.order.error')}";
	    if(!queryDate("palnBeginDate_start","palnBeginDate_end",
	    palnBeginDateFormatMsg,null)){
	    	return false;
	    }
	    planCreateDateFormatMsg="${action.getText('select.right.wash.planCreateDate')}";
		planCreateDateOrderMsg="${action.getText('planCreateDate.order.error')}";
	    if(!queryDate("planCreateDate_start","planCreateDate_end",
	    planCreateDateFormatMsg,null)){
	    	return false;
	    }
	    <#if planProcFlag?exists>
	      <#if (planProcFlag=='PROC')>
		    //验证报告日期格式
		    reportDateMsg="${action.getText('wash.reportDate')}" + "${action.getText('dateFormate.error')}";
		    if(!queryDate("reportDate_start","reportDate_end",reportDateMsg,null)){
		      return false;
		    }
		  </#if>
		</#if>
		return true;
	  }
	</script>  
	