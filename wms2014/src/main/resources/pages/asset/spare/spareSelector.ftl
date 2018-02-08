<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../includes/eam2008.ftl" />
<@htmlPage title="${action.getText('spare.Query')}">
<base target="_self">
	 <@ww.form name="'listForm'" action="'spareSelector'" method="'post'">
         <@ww.hidden name="'strValue'" value=""/>
         <@ww.hidden name="'repairSpare'" value="'${req.getParameter('repairSpare')?if_exists}'"/>
          <@ww.hidden name="'isOld'" value="'${req.getParameter('isOld')?if_exists}'"/>
         <@ww.hidden name="'spareBillId'" value="'${req.getParameter('spareBillId')?if_exists}'"/>
         <@ww.hidden name="'inOutFlag'" value="'${req.getParameter('inOutFlag')?if_exists}'"/>
         <@ww.hidden name="'oldSpareIds'" value="'${filterSpareIds?if_exists}'"/>
          <@ww.hidden name="'toolingDevFlag'" value="'${req.getParameter('toolingDevFlag')?if_exists}'"/>
         <#include "./openSpareSeacher.ftl"/>
         <@buttonBar>        
        	 <@vsubmit name="'search'" value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
         </@buttonBar> 
          <@list title="${action.getText('spare.list')}" 
                 excel=false setupTable=false 
                 includeParameters="id|isOld|inOutFlag|toolingDevFlag|spareNo|putPostion|spareName|oldSpareIds|repairSpare|categoryName|modelSpecs|spareDetailType.id|category.code|department.id|checkbox|inoutFlag|inOutFlag|onlyValid|onlyInvalid|spareBillId" 
                 fieldMap="like:id|spareNo|spareName|putPostion|oldSpareIds|categoryName|modelSpecs" >

            <#if req.getParameter("repairSpare")?has_content>
            <#else>
             <#--<#if (object.disabled)||((object.stocks==0)&&(inoutFlag=='F'))>
                <@vlh.checkbox title="" name="spareIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
	                 <@vlh.attribute name="disabled" value="true"/>
               </@vlh.checkbox>
            <#else>-->
	           <@vlh.checkbox title="" name="spareIds" property="id" ><#--attributes="id=f onclick=\"callBack();\""-->
	                 <@vlh.attribute name="width" value="30"/>
               </@vlh.checkbox>
            <#--</#if>-->
            </#if>
            <#assign spareCategory = ''/>
			<#if object.category?exists>
			 	<#assign spareCategory = "${object.category.name}" />
			</#if>
            <#if req.getParameter("repairSpare")?has_content>
	            <@vcolumn title="${action.getText('spare.code')}" property="spareNo" sortable="desc">
	            	<a href="javascript: returnDialog(new Array(#{object.id}, '${object.name}', '${object.spareNo}', '${object.modelSpecs}','${spareCategory}','${object.unitPrice}'));">${object.spareNo}</a>
	                <@alignLeft/>
	            </@vcolumn>
	        <#else>
	        	 <@vcolumn title="${action.getText('spare.code')}" property="spareNo" sortable="desc">	            
            		<@alignLeft/>
            	 </@vcolumn>
            </#if>
            <@vcolumn title="${action.getText('备件名称')}" property="name" >${object.name?if_exists}
            <#if (!object.disabled)||(object.stocks==0)>
                 <@ww.hidden name="'status'" value="'true'"/>
            <#else>
                 <@ww.hidden name="'status'" value="'false'"/>
            </#if>
            </@vcolumn>
            <@vcolumn title="${action.getText('型号')}" property="modelSpecs">
              <@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.orderNo')}"  property="orderNo" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.factory')}"  property="factory.name" sortable="asc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.category')}" property="category.name" >
              <@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.spareDetailType')}" property="spareDetailType.name">
              <@alignLeft/>
            </@vcolumn>
            -->
              <@vcolumn title="${action.getText('spare.unit')}" property="unit.value" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('spare.safe_stock')}" property="safeStock">
              <@alignRight/>
            </@vcolumn>
            <@ww.hidden name="'spare.safe_stock'" value="${object.safeStock?if_exists}"/>
            <@vcolumn title="${action.getText('spare.stocks')}" property="stocks" >
              <@alignRight/>
            </@vcolumn>
            <@ww.hidden name="'spare.hiddenStocks'" value="${object.stocks?if_exists}"/>
            <@vcolumn title="${action.getText('spare.supplier')}" property="supplier.name" >
              <@alignLeft/>
            </@vcolumn>
            <#--
            <@vcolumn title="${action.getText('spare.device')}" property="device.name">
              <@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="${action.getText('spare.status')}" >
            <#if !object.disabled>
               ${action.getText('spare.ableStatus')}
            <#else>
               ${action.getText('spare.unableStatus')}
            </#if>
            </@vcolumn>-->
         </@list>
         <#if req.getParameter("repairSpare")?has_content>
         <#else>
	         <#if !first>
	         	<@buttonBar>
		      		<@vsubmit name="'choose'" value="'${action.getText('choose')}'" >
		      			<@ww.param name="'onclick'" value="'return confirmSelects(\"spareIds\");'"/>
	                	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            	</@vsubmit>
	            	<#--
	            	<@vsubmit name="'choose'" value="'${action.getText('选择并关闭')}'" >
		      			<@ww.param name="'onclick'" value="'return confirmSelects(\"spareIds\");'"/>
	                	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            	</@vsubmit>
	            	-->
		    	</@buttonBar>
		    </#if>
	    </#if>
	    <script language="javascript">
	     //  alert('jjjjjjj'+ getObjByNameRe("toolingDevFlag").value);
        <#if (action.isOnlyInvalid())>
          getObjByNameRe("onlyDisabled").checked=true;
          changValidOrInvalidStatus();
        </#if>
        
       /* 全选checkbox事件处理 */
       function callBack() {
         var statusSelector = document.getElementsByName("status");
       	   var spareIdSelector = document.getElementsByName("spareIds");
            for ( var i = 0; i < spareIdSelector.length; i ++) {
        	  if (statusSelector[i].value=="true") {
        		  spareIdSelector[i].checked="";
        	  }
          }
       }
	
	      	
	      	function confirmSelects(boxname) {
	      		if (!hasChecked(boxname)) {
	      			alert("${action.getText('spare.noSelect')}");
	      			return false;
	      		}else if(getObjByNameRe("inOutFlag").value=='out'){
	      			var selector = document.getElementsByName(boxname);
	      			var Stocks = document.getElementsByName("spare.hiddenStocks");
	      			if (selector.length) {
				        for (i = 0; i < selector.length; i++) {
				            if (selector[i].checked&&Stocks[i].value==0) {
				            	alert("${action.getText('di')}"+(i+1)+" ${action.getText('please.select.spare')}");
				            	return false;
				            }
				        }
				    } 
	      		}
	      		chooseSpares(boxname);
	      		return true;
	      	}
	      	
	      	function chooseSpares(boxname) {
	      		var selector = document.getElementsByName(boxname);
			    if (!selector) {
			        return false;
			    }
			    
			    var spareIds = "";
			    if (selector.length) {
			        for (i = 0; i < selector.length; i++) {
			            if (selector[i].checked) {
			                spareIds += selector[i].value + ",";
			            }
			        }
			    }
			    
			    returnDialog(spareIds,false);
	      	}
	      	
	      
	      </script>
    </@ww.form>
    <#include "../commonSpareType.ftl"/>
</@htmlPage> 