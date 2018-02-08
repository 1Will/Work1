<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('masterTypeList.new')}">
    <@ww.form namespace="'/base/master'" name="'masterType'" action="'saveMasterType'" method="'post'" >
        <@ww.token name="saveMasterTypeToken"/>
        <@inputTable>
        	<#if masterType.id?exists>
                <@ww.hidden name="'master.id'" value="#{masterType.id}"/>
            </#if>
            <@ww.hidden name="'masterType.version'" value="#{masterType.version?if_exists}"/>
            <tr>
                <@ww.textfield label="'${action.getText('masterType.code')}'" name="'masterType.code'" value="'${masterType.code?if_exists}'" cssClass="'underline'" readonly="true"/>
                <@ww.textfield label="'${action.getText('masterType.name')}'" name="'masterType.name'" value="'${masterType.name?if_exists}'"  size="20"  cssClass="'underline'" readonly="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
        <#--
            <@vsubmit name="'save'" value="'${action.getText('save')}'" />
        -->
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/master/listMasterTypes.html"/>
        </@buttonBar>
        <#if masterType.id?exists>
	      <@titleBar title="${action.getText('MasterDetailList')}"/>
	        <@listTable>
	            <tr>
	                <th></th>
	                <th>${action.getText('masterTypeDetail.id')}</th>
	                <th>${action.getText('masterTypeDetail.name')}</th>
	            </tr>
	     	   <#if (masterType.details.size()>0)>
		           <#list masterType.details as detail>
		               <tr>
		                <td><input type="checkbox" name="detailIds" value="#{detail.id}"/></td>
		                <td><a href="#" onclick="open_detailDialog(#{masterType.id},#{detail.id})">${detail.id?if_exists}</a></td>
		                <td>${detail.name?if_exists}</td>
		              </tr>
		     	   </#list>
		      </#if>
	      </@listTable> 
	      <@buttonBar>
	      		<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_detailDialog(#{masterType.id},null);"/>
	      		<#if (masterType.details.size()>0)>
	            	<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('masterTypeDetail')}?" />
	            	<@vsubmit name="'delete'"  value="'${action.getText('delete')}'">
	            		 <@ww.param name="'onclick'" value="'return confirmDeletes(\"detailIds\", \"${confirmMessage}\");'"/>
	            	</@vsubmit>
	           	</#if>
	      </@buttonBar>
	    </#if>
	    
	    <script language="javascript">
	    	function showModalWindow(url,params){
			    if(isIE()){
			    	return result = window.showModalDialog(url,params,
			        'center:yes;dialogWidth:550px;dialogHeight:300px;status:no');
		        }
		        else{
			       	ModalDialogShow(url,550,300,null);
			    }
	    	}
	    	function open_detailDialog(masterId, detailId) {
	    		var url = "${req.contextPath}/popup/editMasterTypeDetail.html?master.id="+masterId;
	    		if (detailId != null) {
	    			url += "&detail.id=" + detailId;
	    		}
				showModalWindow(url, window); 
				self.location.reload();
//	    		popupModalDialog('${req.contextPath}/popup/editMasterTypeDetail.html?master.id='+masterId, null, 300);

	    	}
	    </script>
    </@ww.form>
</@htmlPage>