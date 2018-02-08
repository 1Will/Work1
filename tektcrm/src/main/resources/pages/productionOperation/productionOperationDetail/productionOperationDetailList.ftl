<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('经营计划查询页面')}">
	<@ww.form name="'listForm'" namespace="'/productionOperation'" action="'searchProductionOperationDetail'" method="'post'">
		<@ww.token name="searchProductionOperationDetailToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'productionOperation.id'"  value="'#{productionOperation.id}'"/>
         <#assign itemNo=1/>
        <@list title="${action.getText('详细列表')}" 
            includeParameters="productionOperation.id|" 
        	fieldMap="">
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="productionOperationDetailIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('序号')}" property="" >
          		<a href="javascript:editProductionOperationDetail('#{object.id}')">#{itemNo}</a>
				<@alignLeft/>
            </@vcolumn>
          	<@vcolumn title="${action.getText('产品名称')}" property="product.name" sortable="desc" >
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('产品编码')}" property="product.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('产品型号')}" property="product.model" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('交付数量')}" property="deliverNum" sortable="desc">
     			<@alignRight/>
            </@vcolumn>
			<@vcolumn title="${action.getText('检验方式')}" property="testType.name" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('产品状态')}" property="proStatuString" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('合同编码')}" property="contractManagement.code" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('交付节点')}" property="deliverDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('技术资料节点')}" property="technologyDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('采购节点')}" property="purchaseDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('制造节点')}" property="makeDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('厂检节点')}" property="testDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('军检节点')}" property="jtestDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('执行状态')}" property="proStatu.name" sortable="desc" format="yyyy-MM-dd">
              <#if (object.proStatu?exists)>
		             <#if object.proStatu.code=='21602'>
		             	<font color="red">${object.proStatu.name}</font>
		             <#else>
		             	<font color="green">${object.proStatu.name}</font>
		             </#if>
             	</#if>
     			<@alignLeft/>
            </@vcolumn>
             <#assign itemNo = itemNo+1/>
        </@list>
          <#if !first>
        	<#if !(action.isReadOnly())>
		         <@buttonBar>
       <#if !(action.isReadOnly())>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newProductionOperationDetail()"/>
		  	<#assign confirmMessage = "${action.getText('确认删除')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"productionOperationDetailIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	     </#if>
		</@buttonBar>
			</#if>
		</#if>
    </@ww.form>

<script language="javascript">
  //打开编辑社会关系模态窗口
  function editProductionOperationDetail(id){
  var url = "${req.contextPath}/productionOperation/editProductionOperationDetail.html?productionOperationDetail.id="+id+"&productionOperation.id=#{productionOperation.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
	    //openNewWindow(url);
	    window.open(url,'','width=1100,height=600,top=200, left=300'); 
         window.opener=null; 
     // if(isIE()){self.location.reload();};
  }
  //打开新建社会关系模态窗口
    function newProductionOperationDetail(){
  		var url = "${req.contextPath}/productionOperation/editProductionOperationDetail.html?productionOperation.id=#{productionOperation.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
	   // openNewWindow(url);
	    window.open(url,'','width=1100,height=600,top=200, left=300'); 
         window.opener=null; 
	 //if(isIE()){self.location.reload();};
  }
</script>

</@framePage>
