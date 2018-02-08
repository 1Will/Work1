<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('toolingDemarcateRuleSearch.title')}">
<base target="_self">
	 <@ww.form name="'listForm'" action="'demarceteRuleSelector'" method="'post'">
          <#include "toolingDemarcateRuleSearcher.ftl"/>
         <@buttonBar>        
        	 <@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
         </@buttonBar> 
          <@list title="${action.getText('demarcateRule.list')}" 
                 includeParameters="toolingNo|toolingName|toolingGraphNo|department.id" 
                 fieldMap="like:toolingNo|toolingName|toolingGraphNo" >
                  <#--
          	<@vlh.checkbox property="id" name="demarcateRuleIds">
          		  <input type="checkbox" name="demarcateRuleIds" value="#{object.id},${object.name}" width="30"/>
            </@vlh.checkbox>
                -->
            <@vlh.checkbox property="id" name="demarcateRuleIds">
            	<@vlh.attribute name="width" value="30" />
            </@vlh.checkbox>
       
            <@vcolumn title="${action.getText('tooling.no')}" property="deviceNo" sortable="asc"/>
            <@vcolumn title="${action.getText('tooling.graphNo')}" property="graphNo" sortable="asc"/>
            <@vcolumn title="${action.getText('tooling.name')}" property="name" />
            <@vcolumn title="${action.getText('department')}" property="department.name" />
            <@vcolumn title="${action.getText('tooling.Manager')}" property="manager.name" />
            <@vcolumn title="${action.getText('tooling.preDemarcateTime')}" property="preDemarcateTime" format="yyyy-MM-dd"/>
            <@vcolumn title="${action.getText('tooling.demarcateCycle')}" property="demarcateCycle" />
         </@list>
         <#if !first>
         	<@buttonBar>
	      		<@vsubmit name="'choose'" value="'${action.getText('choose')}'" >
	      			<@ww.param name="'onclick'" value="'return confirmSelects(\"demarcateRuleIds\");'"/>
                	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            	</@vsubmit>
	    	</@buttonBar>
	    </#if>
	    <script language="javascript">	
	      	
	      	function confirmSelects(boxname) {
	      		if (!hasChecked(boxname)) {
	      			alert("${action.getText('demarcateRule.noSelect')}");
	      			return false;
	      		}
	      		chooseDemarcateRule(boxname);
	      		return true;
	      	}
	      	
	      	function chooseDemarcateRule(boxname) {
	      		var selector = document.getElementsByName(boxname);
			    if (!selector) {
			        return false;
			    }
			    
			    var demarcateRuleIds = "";
			    var ary = new Array();
			    if (selector.length) {
			        for (i = 0; i < selector.length; i++) {
			            if (selector[i].checked) {
			                demarcateRuleIds += selector[i].value + ",";
			              // alert(selector[i].value);
			              //  var tmp = selector[i].value.split(",");
	      				    //var a = new Array(tmp[0], tmp[1]);
	      				  //  ary.push(a);
			            }
			        }
			    }
			    
			    returnDialog(demarcateRuleIds,false);
	      	}
	      </script>
    </@ww.form>
</@htmlPage> 