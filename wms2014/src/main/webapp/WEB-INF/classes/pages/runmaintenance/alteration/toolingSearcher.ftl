<#include "../../includes/eam2008.ftl" />
<@inputTable>
	 	  <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
	 		 <tr>
	        	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillNo')}'" name="'sealedBillNo'" value="'${req.getParameter('sealedBillNo')?if_exists}'" cssClass="'underline'"  />
	        	<@ww.textfield label="'${action.getText('toolingsealed.sealedBillName')}'" name="'sealedBillName'" value="'${req.getParameter('sealedBillName')?if_exists}'" cssClass="'underline'"  />
	        </tr>
	        <tr>
	 		    <#if toolingDevFlag=="TOOLING">
	 		        <#assign deviceNoTitle="${action.getText('toolingsealed.asset.deviceNo')}"/>
	 		        <#assign assetNameTitle="${action.getText('toolingsealed.asset.name')}"/>
	 		       <#-- <#assign assetgraphNoTitle="${action.getText('toolingsealed.asset.graphNo')}"/>-->
	 		    <#else>
	 		        <#assign deviceNoTitle="${action.getText('devicesealed.asset.deviceNo')}"/>
	 		        <#assign assetNameTitle="${action.getText('devicesealed.asset.name')}"/>
	 		        <#--<#assign assetgraphNoTitle="${action.getText('devicesealed.asset.graphNo')}"/>-->
	 		    </#if>
	 		    <#if toolingDevFlag=="TOOLING">
			 	<@ww.textfield label="'${deviceNoTitle}'" name="'asset.deviceNo'" value="'${req.getParameter('asset.deviceNo')?if_exists}'" cssClass="'underline'"  />
			 	<@ww.textfield label="'${assetNameTitle}'" name="'asset.name'" value="'${req.getParameter('asset.name')?if_exists}'" cssClass="'underline'"  />
			 	<#--<@ww.textfield label="'${assetgraphNoTitle}'" name="'asset.graphNo'" value="'${req.getParameter('asset.graphNo')?if_exists}'" cssClass="'underline'"  />-->
			 	<#else>
			 	<@ww.textfield label="'${deviceNoTitle}'" name="'asset.deviceNo'" value="'${req.getParameter('asset.deviceNo')?if_exists}'" cssClass="'underline'"  />
			 	<@ww.textfield label="'${assetNameTitle}'" name="'asset.name'" value="'${req.getParameter('asset.name')?if_exists}'" cssClass="'underline'"  />
			 	</#if>
	        </tr>	        
	        <tr>
	           <#if toolingDevFlag=="TOOLING">
	 		        <#assign assetgraphNoTitle="${action.getText('toolingsealed.asset.graphNo')}"/>
	 		    <#else>
	 		        <#assign assetgraphNoTitle="${action.getText('devicesealed.asset.graphNo')}"/>
	 		    </#if>
	 		    <#if toolingDevFlag=="TOOLING">
			    	<@ww.textfield label="'${assetgraphNoTitle}'" name="'asset.graphNo'" value="'${req.getParameter('asset.graphNo')?if_exists}'" cssClass="'underline'"  />
			 	</#if>
	           <@pp.dateRanger label="'${action.getText('toolingsealed.sealedDateApp')}'" name="'sealedDateApp'" 
			       value="'${req.getParameter('sealedDateApp_start')?if_exists}|${req.getParameter('sealedDateApp_end')?if_exists}'"
			       cssClass="'underline'" dateFormat="date"/>
			    </tr>
			    <tr>   
	            <@ww.select label="'${action.getText('sealed_state')}'" 
                         required="false" name="'status'" 
                         value="'${status?if_exists}'"
                         listKey="value" listValue="label"
                         list="sealedStatus" 
                          emptyOption="false" 
                          disabled="false">
                </@ww.select>
	            
	        	<@eam2008_onlySearchInvalid_checkBox/>
	        </tr>
</@inputTable>
<script language="javascript" type="text/JavaScript">
	selector = document.all("status");
  	groups = selector.options.length;

  	for (i=0; i<groups; i++) {
	    <#if req.getParameter('status')?exists>
	    if (selector.options[i].value == "${req.getParameter('status')?if_exists}") {
	      selector.options[i].selected="true";
		}
	    </#if>
    }
    
    function checkInvalidParms() {
	    if (document.getElementById("status").value == -1) {
	      document.getElementById("status").value = '';
	    }
	    var sealedDateAppFormatMsg="${action.getText('select.right.sealedDateTm')}";
		var sealedDateAppOrderMsg="${action.getText('sealedDateTm.order.error')}";
	    if(!queryDate("sealedDateApp_start","sealedDateApp_end",
	      sealedDateAppFormatMsg,sealedDateAppOrderMsg)){
		  return false;
	    }
  	}
  	
</script>