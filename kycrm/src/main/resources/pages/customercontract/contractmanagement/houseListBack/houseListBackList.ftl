<#include "../../../includes/hco2011.ftl" />
<@framePage>
	<@ww.form name="'listForm'" namespace="'/productList'" action="'searchHouseListBack'" method="'post'">
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<@ww.token name="searchHouseListBackToken"/>
    	<@ww.hidden name="'contractManagement.id'" value="${req.getParameter('contractManagement.id')?if_exists}"/>
        <@list title="" 
            includeParameters="contractManagement.id|onlyInvalid|onlyValid" 
        	fieldMap="like:" >
        	<#if !(action.isReadOnly())>
	        	<@vlh.checkbox property="id" name="houseListBackIds">
	            	<@vlh.attribute name="width" value="30" />
	            </@vlh.checkbox>     
            </#if>   
           	 <@alignCenter/>
	        <@vcolumn title="${action.getText('房间编码')}"   >
	         <a href="javascript:" onclick="editHouseListBack('#{object.id}')"><#if object.house?exists>${object.house.code?if_exists}</#if></a>
            	<@alignLeft/>
            	<@vlh.attribute name="width" value="130" />
         	</@vcolumn>
         	
         	<@vcolumn title="${action.getText('房间名称')}" property="house.name" >
            	<@alignLeft/>
         	</@vcolumn>
         	
         	<@vcolumn title="${action.getText('开始时间')}" format="yyyy-MM-dd" property="startTime" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn>
         	
         	<@vcolumn title="${action.getText('结束时间')}" format="yyyy-MM-dd" property="endTime" sortable="desc">
            	<@alignLeft/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('房租收款金额')}" property="backRent" >
            	<@alignRight/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('水电收款金额')}" property="waterEleFee" >
            	<@alignRight/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('收款总金额')}" property="allFee" >
            	<@alignRight/>
         	</@vcolumn>
         	<@vcolumn title="${action.getText('备注')}" property="remark" >
            	<@alignRight/>
         	</@vcolumn>
         	
        </@list>
        <#if !(action.isReadOnly())>
        <@buttonBar>
		  	<@vbutton class="button" value="${action.getText('new')}" onclick="newHouseList()"/>
	  		<#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('退房合同')}?" />	 
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
               <@ww.param name="'onclick'" value="'return confirmDeletes(\"houseListBackIds\", \"${confirmMessage}\");'"/>
            </@vsubmit>
	     </@buttonBar>	
		</#if>
   </@ww.form>
</@framePage>
<script language="javascript">
   /* 
   window.onload=function(){
   //判断是否删除，如果删除同时刷新父页面
   var flag=getCookieValue("listHouseFlag");
   if(flag=="flag"){
     self.parent.location.reload();
     document.cookie="listHouseFlag=''";
   }
   }
   //获取Cookie中字段的值
   function getCookieValue(name) {
    var strCookie=document.cookie;
    var arrCookie=strCookie.split(";");
    for(var i=0;i<arrCookie.length;i++){
        var c=arrCookie[i].split("=");
        if(c[0]==name){
            return c[1];
        }
    }
    return "";
}
*/
	function editHouseListBack(id){
      var url='${req.contextPath}/productList/editHouseListBack.html?houseListBack.id='+id+'&contractManagement.id='+${req.getParameter('contractManagement.id')?if_exists}+"&readOnly=${req.getParameter('readOnly')?if_exists}";
      openNewWindow(url);
      if(isIE()){self.location.reload();};
	}
	 //获得模态窗体返回值
	function newHouseList(){
      var url='${req.contextPath}/productList/editHouseListBack.html?readOnly=${req.getParameter('readOnly')?if_exists}&contractManagement.id='+${req.getParameter('contractManagement.id')?if_exists};
      openNewWindow(url);
      if(isIE()){self.location.reload();};
	}
	//function checkInvalidParms(){
	//	return true;
    //}
</script>
