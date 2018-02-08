<#include "../../includes/hco2011.ftl" />

<@framePage title="${action.getText('产品计划异常查询页面')}">
	<@ww.form name="'listForm'" namespace="'/productionOperation'" action="'searchProductionOperationException'" method="'post'">
		<@ww.token name="searchProductionOperationExceptionToken"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.hidden name="'productionOperationDetail.id'"  value="'#{productionOperationDetail.id}'"/>
         <#assign itemNo=1/>
        <@list title="${action.getText('详细列表')}" 
            includeParameters="productionOperationDetail.id|" 
        	fieldMap="">
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="productionOperationExceptionIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>
            </#if>
            <@vcolumn title="${action.getText('序号')}" property="" >
          		<a href="javascript:editProductionOperationException('#{object.id}')">#{itemNo}</a>
				<@alignLeft/>
            </@vcolumn>
          	<@vcolumn title="${action.getText('异常编码')}" property="code" sortable="desc" >
				<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="${action.getText('异常名称')}" property="name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
           <@vcolumn title="${action.getText('发生节点')}" property="projectInfoPlan.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('节点负责人')}" property="projectInfoPlan.personnelFiles.name" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('异常提出人')}" property="findPersion.name" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
			<@vcolumn title="${action.getText('提出时间')}" property="findDate" format="yyyy-MM-dd" sortable="desc">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('异常描述')}" property="describe" sortable="desc">
            <span title="${object.describe?if_exists}">
		            <script>
		            	var s = '${object.describe?if_exists}';
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>5){
		            	document.write(s.slice(0,5)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('解决人')}" property="solvePersion.name" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('解决时间')}" property="solveDate" sortable="desc" format="yyyy-MM-dd">
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('异常原因')}" property="reason" sortable="desc" >
           <span title="${object.reason?if_exists}">
		            <script>
		            	var s = '${object.reason?if_exists}';
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>5){
		            	document.write(s.slice(0,5)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('解决方案')}" property="solution" sortable="desc" >
             <span title="${object.solution?if_exists}">
		            <script>
		            	var s = '${object.solution?if_exists}';
		            	s=s.replace(/[\r\n]/g, "");
		            	if(s.length>5){
		            	document.write(s.slice(0,5)+"...");
		            	}else{
		            	document.write(s);
		            	}
		            </script>
	            </span>
     			<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="${action.getText('异常状态')}" property="statu.name" sortable="desc" >
     			<@alignLeft/>
            </@vcolumn>
             <#assign itemNo = itemNo+1/>
        </@list>
          <#if !first>
        	<#if !(action.isReadOnly())>
		         <@buttonBar>
       <#if !(action.isReadOnly())>
		  <@vbutton class="button" value="${action.getText('new')}" onclick="newProductionOperationException()"/>
		  	<#assign confirmMessage = "${action.getText('确认删除')}?" />	 
	            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
	               <@ww.param name="'onclick'" value="'return confirmDeletes(\"productionOperationExceptionIds\", \"${confirmMessage}\");'"/>
	                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
	            </@vsubmit>
	     </#if>
		</@buttonBar>
			</#if>
		</#if>
    </@ww.form>

<script language="javascript">
  //打开编辑社会关系模态窗口
  function editProductionOperationException(id){
  var url = "${req.contextPath}/productionOperation/editProductionOperationException.html?productionOperationException.id="+id+"&productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
	   // openNewWindow(url);
	    //popupModalDialog(url, 850, 600);
     window.open(url,'','width=1100,height=600,top=200, left=300'); 
         window.opener=null; 
  }
  //打开新建社会关系模态窗口
    function newProductionOperationException(){
  		var url = "${req.contextPath}/productionOperation/editProductionOperationException.html?productionOperationDetail.id=#{productionOperationDetail.id}&readOnly=${req.getParameter('readOnly')?if_exists}";
	    //openNewWindow(url);
	     //popupModalDialog(url, 850, 600);
	     window.open(url,'','width=850,height=600,top=200, left=300'); 
         window.opener=null; 
  }
</script>

</@framePage>
