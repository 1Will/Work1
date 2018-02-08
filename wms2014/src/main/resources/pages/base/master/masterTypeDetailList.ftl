<#include "../../includes/macros.ftl" />
<@htmlPage title="${action.getText('masterDetailList.title')}">
    <@ww.form name="'listForm'" action="'listMasterTypeDetail'" method="'post'">
        <@ww.token name="searchMasterDetailToken"/>
        <@inputTable>
            <tr>
                <@ww.textfield label="'${action.getText('masterType.code')}'" name="'masterType.code'" value="'${masterType.code?if_exists}'" cssClass="'underline'"  readonly="true"/>
                <@ww.textfield label="'${action.getText('masterType.name')}'" name="'masterType.name'" value="'${masterType.name?if_exists}'"  size="32"  cssClass="'underline'"  readonly="true"/>
            </tr>
        </@inputTable>
        <@buttonBar>
            <@redirectButton value="${action.getText('back')}" url="${req.contextPath}/base/master/listMasterTypes.html"/>
        </@buttonBar>
     <#if masterType.id?exists>
      <@titleBar title="${action.getText('MasterDetailList')}"/>
        <@listTable>
            <tr>
                <th>
                </th>
                <th>${action.getText('masterTypeDetail.id')}</th>
                <th>${action.getText('masterTypeDetail.name')}</th>
            </tr>
     	   <#if (masterType.details.size()>0)>
	           <#list masterType.details as masterTypeDetail>
	               <tr>
	                <td>
	                   <input type="checkbox" name="orderDetailIds" value="#{masterTypeDetail.id}"/>
	                </td>
	                <td>${masterTypeDetail.id?if_exists} </td>
	                <td>${masterTypeDetail.name?if_exists}</td>
	              </tr>
	     	   </#list>
	      </#if>
      </@listTable> 
        <@buttonBar>
        	<@vbutton name="'new'"  class="button" value="${action.getText('new')}" onclick="open_detailDialog(#{masterType.id});"/>
            <#assign confirmMessage = "${action.getText('delete.msg')}" />
              <@vbutton name="'delete'"  class="button" value="${action.getText('delete')}" onclick="confirm('${action.getText('delete.msg')}')"/>
       </@buttonBar>   
      </#if>
       <script language="javascript">
	    	function open_detailDialog(masterId) {
	    		var url = "${req.contextPath}/popup/editMasterTypeDetail.html?master.id="+masterId;
				//showModalWindow(url, window);
				window.showModelessDialog(url,window,
			        'center:yes;dialogWidth:600px;dialogHeight:300px;status:no');
	    	}
	    </script>
    </@ww.form>
</@htmlPage>