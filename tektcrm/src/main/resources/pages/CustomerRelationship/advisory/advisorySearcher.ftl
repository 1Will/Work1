<#include "../../includes/hco2011.ftl" />
<@inputTable>
	<@ww.hidden name="'isNoB'" value="'${req.getParameter('isNoB')?if_exists}'"/>
	<tr>
		<@ww.textfield label="'${action.getText('advisory.name')}'" name="'advisory.name'" value="'${req.getParameter('advisory.name')?if_exists}'" cssClass="'underline'"/>
		<#--
		<@ww.select 
    		label="'${action.getText('advisory.cust_type')}'"
			required="false"
			name="'customerType'" 
			value="${req.getParameter('customerType')?if_exists}" 
			listKey="id"
			listValue="name"
		    list="allCustTypes"
		    emptyOption="false" 
		    disabled="false"/>
		-->
		<@ww.textfield label="'${action.getText('advisory.connectPeople')}'" name="'advisory.connectPeople'" value="'${req.getParameter('advisory.connectPeople')?if_exists}'" cssClass="'underline'"/>
	</tr>
	<tr>
		<@ww.textfield label="'${action.getText('advisory.customerService')}'" name="'advisory.customerServiceName'" value="'${req.getParameter('advisory.customerServiceName')?if_exists}'" cssClass="'underline'"/>
		<@pp.dateRanger label="'${action.getText('advisory.consultationTime')}'" name="'advisory.advisoryTime'" 
            value="'${req.getParameter('advisory.advisoryTime_start')?if_exists}|${req.getParameter('advisory.advisoryTime_end')?if_exists}'"
             cssClass="'underline'" maxlength="10" />
        <script language="javascript">
        <#--
	        <#if first>
	        	if(getObjByName("advisory.advisoryTime_start").value==""){
			        	var date = new Date();
						getObjByName("advisory.advisoryTime_end").value = date.format("yyyy-MM-dd");
				}
			</#if>-->
		</script>
	</tr>
	<tr>
		<@ww.select 
    		label="'${action.getText('advisory.industry_ID.id')}'"
			required="false"
			name="'industry'" 
			value="${req.getParameter('industry')?if_exists}" 
			listKey="id"
			listValue="name"
		    list="allIndustrys"
		    emptyOption="false" 
		    disabled="false"/>
		    <@ww.select 
	    		label="'${action.getText('advisory.infoSource')}'"
				required="false"
				name="'advisory.infoSource'" 
				value="${req.getParameter('advisory.infoSource')?if_exists}" 
				listKey="id"
				listValue="name"
			    list="allInfoOriginId"
			    emptyOption="false" 
			    disabled="false"/>
	</tr>
		<tr>
			<td align="right"><label for="" class="label">${action.getText('advisory.NoBack')}:</label></td>
	        <td align="left">
	        	<input type="radio" id="advisory.isNoBack1" name="advisory.isNoBack" value="0" checked>是
	        	<input type="radio" id="advisory.isNoBack2" name="advisory.isNoBack" value="1">否
	        	<script language="javascript">
		     		if(getObjByName('isNoB').value=='true'){
		     			getObjByName('advisory.isNoBack1').checked = true;
		     		}else if(getObjByName('isNoB').value=='false'){
		     			getObjByName('advisory.isNoBack2').checked = true;
		     		}
				</script>
			</td>
	</tr>
	<tr>
		<@crm_onlySearchInvalid_checkBox />
	</tr>
			    
</@inputTable>
<script language="javascript">

	  <#if req.getParameter('advisory.infoSource')?exists>
		getObjByName("advisory.infoSource").value="${req.getParameter('advisory.infoSource')?if_exists}";
      </#if>
	<#--
    var selector=document.all("customerType");
    var groups=selector.options.length;
    for(i=0;i<groups;i++){
      <#if req.getParameter('customerType')?exists>
        if(selector.options[i].value=="${req.getParameter('customerType')?if_exists}"){
           selector.options[i].selected="true";
        }
      </#if>
    }
    -->
    var selecter=document.all("industry");
    var group=selecter.options.length;
    for(i=0;i<group;i++){
      <#if req.getParameter('industry')?exists>
        if(selecter.options[i].value=="${req.getParameter('industry')?if_exists}"){
           selecter.options[i].selected="true";
        }
      </#if>
    }
    function checkInvalidParms(){
      <#--
       if (getObjByName("customerType").value==-1){
           getObjByName("customerType").value='';
       }
       -->
       if (getObjByName("industry").value==-1){
           getObjByName("industry").value='';
       }
       if (getObjByName('advisory.isNoBack1').checked) {
       		getObjByName('isNoB').value=true;
	   }else if(getObjByName('advisory.isNoBack2').checked){
	   		getObjByName('isNoB').value=false;
	   }
       //beginDateMsg="${action.getText('advisory.consultationTime')}" + "${action.getText('dateFormate.error')}";
       beginDateMsg="咨询时间：${action.getText('dateTimeFormat.error')}";
	   if(!queryDate("advisory.advisoryTime_start","advisory.advisoryTime_end",
	      beginDateMsg,null)){
	      return false;
	   }
       return true;
    }
         
</script>