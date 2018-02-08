<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
<#include "../../../includes/eam2008.ftl" />
<@htmlPage title="维修单查询">
	 <STYLE TYPE="text/css" >
	 .displayRed{
	   font-weight: bold;
	   color: #FFFFFF;
	   background-color: #FFCC66;	 
	 }
	  .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        width:80%;
    }
     </STYLE>
	 <@ww.form name="'spareOutBills'" action="'searchOldSpareMaintainBills'" method="'post'">
         <@ww.token name="searchSpareOutBillsToken"/>
          <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	 	  <#include "./oldSpareMaintainBillSearcher.ftl"/>
	       <#assign itemNo = 0/>
        <@buttonBar>
        	<@vsubmit value="'${action.getText('search')}'" onclick="'return checkInvalidParms();'"/>
        	 <#if !(action.isReadOnly())>
	        <@redirectButton value="${action.getText('new')}" url="${req.contextPath}/spare/editOldSpareMaintainBill.html?readOnly=${req.getParameter('readOnly')?if_exists}"/>
             </#if>
             <#--
            <@blurbutton name="excelPrints" class="button" value="${action.getText('pdfReportPrints')}" 
	                          onclick="excelButtonManagers('pdf')" 
	                          onblur="onBlurs('excels')"/>
		   		  <div id="excels" style="display:none;position:relative;left:100px;top:0px;width:20px;">
					<@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_spareOutBillPdf()"/>
					<@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_spareOutBillXls()"/>
		   		 </div>  
             -->
        </@buttonBar>  
        
            
        <@list title="维修单列表" 
                   includeParameters="id|code|name|borrower|outPeople|outDate_start|outDate_end|onlyValid|onlyinvalid|spareOutBill.department|outStatus|storageGrade.id|warehouse.id|inWarehouse.id" 
                   fieldMap="like:code|name|borrower|outPeople" >          
               
                <@vlh.checkbox title="" name="spareOutBillIds" property="id" >
	                 <@vlh.attribute name="width" value="30"/>
               </@vlh.checkbox>
          	<#if (object.disabled)>
          	<@vcolumn title="维修单编码" property="code" sortable="desc">
            	<@alignLeft />
            </@vcolumn>
          	<#else>
             <@vcolumn title="维修单编码" property="code" sortable="desc">
                <a href="editOldSpareMaintainBill.html?spareOutBill.id=#{object.id}&readOnly=${req.getParameter('readOnly')?if_exists}&pagingPage=${pagingPage?if_exists}">${object.code}</a>
            	<@alignLeft/>
             </@vcolumn>
            </#if>	
            <@vcolumn title="维修单名称" property="name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            <@vcolumn title="领料部门" property="department.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
             <@vcolumn title="领料人" property="borrower" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
            
            <@vcolumn title="出库人" property="outPeople.name" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
           
            <@vcolumn title="出库日期" property="outDate" format="yyyy-MM-dd" sortable="desc">
            	<@alignLeft/>
            </@vcolumn>
          
            <@vcolumn title="仓库级别" property="warehouse.storageGrade.value" sortable="desc">
				<@alignLeft />
            </@vcolumn>     
           
            <@vcolumn title="出仓库"  property="warehouse.name" sortable="desc">
				<@alignLeft />
            </@vcolumn> 
               <@vcolumn title="进入仓库"  property="inWarehouse.name" sortable="desc">
				<@alignLeft />
            </@vcolumn>                 
            <@vcolumn title="已出总金额" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="已入总金额" property="inTotalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight/>
            </@vcolumn>
            <@vcolumn title="备注" property="comment" >
            	<@alignLeft/>
            </@vcolumn>
          <#assign status=''/>
          
        	<#if object.status?exists>
		       <#if '${object.status}' == 'NEWSTATUS'>
		       <#assign status = "${action.getText('NEWSTATUS')}"/>
		       <#elseif '${object.status}' == 'OUTWAREHOUSEING'>
		       <#assign status = "${action.getText('OUTWAREHOUSEING')}"/>
		       <#elseif '${object.status}' == 'OUTWAREHOUSEED'>
		       <#assign status = "${action.getText('OUTWAREHOUSEED')}"/>
        	</#if>
        	</#if>
         <@vcolumn title="状态" attributes="width='50'">
           ${status}
                 <@alignLeft/>
            </@vcolumn>
        </@list>
        <#if !first>
          <tr class="input">
             
			<td align='right'>
				<label class="label" style="text-align:right">总金额(元): ${(showOutTotalPrice?string('######0.00'))?if_exists}</label>
				 		
			</td>
			</tr>
        <#if !(action.isReadOnly())>
        <@buttonBar>
            <@eam2008_disabledOrEnabled_button message="${action.getText('spareOutBill')}" boxName="spareOutBillIds" jsFunctionName="checkInvalidParms()"/>
        	<@vbutton name="printPdf"  class="button" value="${action.getText('pdfspareOutBillReportPrint')}" onclick="open_SpareOutBillReportPdf()"/>
	    	<@vbutton name="printXls"  class="button" value="${action.getText('xlsspareOutBillReportPrint')}" onclick="open_SpareOutBillReportXls()"/>
             <@vbutton name="printPdf"  class="button" value="${action.getText('pdfPrint')}" onclick="return open_spareOutBillPdf()"/>
			 <@vbutton name="printXls"  class="button" value="${action.getText('xlsPrint')}" onclick="return open_spareOutBillXls()"/>
        
        </@buttonBar>
        </#if>
        </#if> 	
     </@ww.form>
</@htmlPage>

<style type="text/css">
.noBorderLine{
   border-width :0px;
   border-style : none;
   outline-style : none;
   text-align:right;
   
}
</style>
<script language="javascript">
     window.onload = function () {
     <#if !(action.isReadOnly())>
        <#if !first && valueListNoRecords>
             document.forms[0].elements["printPdf"].disabled="true";
             document.forms[0].elements["printXls"].disabled="true";
        </#if>
     </#if>
     }
function open_SpareOutBillReportPdf(){
      var spareOutBillNo=document.forms[0].elements["code"].value;
      var spareOutBillName=document.forms[0].elements["name"].value;
      var deptId=document.forms[0].elements["spareOutBill.department"].value;
      var borrower=document.forms[0].elements["borrower"].value;
      var outPeople=document.forms[0].elements["outPeople"].value;
      var outDate_start=document.forms[0].elements["outDate_start"].value;
      var outDate_end=document.forms[0].elements["outDate_end"].value;
      var warehouseId = document.forms[0].elements["warehouse.id"].value;
      var inWarehouseId = "";//document.forms[0].elements["inWarehouse.id"].value;
      var url='${req.contextPath}/reports/spare/listAllSpareOutBill.pdf?oldSpare=0&outType=2&spareOutBillNo='+spareOutBillNo+
      '&spareOutBillName='+spareOutBillName+'&deptId='+deptId+'&borrower='+borrower+'&outPeople='+
       outPeople+'&outDate_start='+outDate_start+'&outDate_end='+outDate_end + '&warehouseId=' + warehouseId + '&inWarehouseId=' + inWarehouseId;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
function open_SpareOutBillReportXls(){
      var spareOutBillNo=document.forms[0].elements["code"].value;
      var spareOutBillName=document.forms[0].elements["name"].value;
      var deptId=document.forms[0].elements["spareOutBill.department"].value;
      var borrower=document.forms[0].elements["borrower"].value;
      var outPeople=document.forms[0].elements["outPeople"].value;
      var outDate_start=document.forms[0].elements["outDate_start"].value;
      var outDate_end=document.forms[0].elements["outDate_end"].value;
      var warehouseId = document.forms[0].elements["warehouse.id"].value;
      var inWarehouseId = "";//document.forms[0].elements["inWarehouse.id"].value;
      var url='${req.contextPath}/reports/spare/listAllSpareOutBill.xls?oldSpare=0&outType=2&spareOutBillNo='+spareOutBillNo+
       '&spareOutBillName='+spareOutBillName+'&deptId='+deptId+'&borrower='+borrower+'&outPeople='+
       outPeople+'&outDate_start='+outDate_start+'&outDate_end='+outDate_end + '&warehouseId=' + warehouseId + '&inWarehouseId=' + inWarehouseId;
      url = encodeURI(url);
      window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0");
     }
     
         
	   //点击批量打印按钮控制层的显示和隐藏
	   function excelButtonManagers(type){
		 var op = getObjByNameRe('excels')
		 if(op.style.display=="none"){
			 getObjByName("excelPrints").value="${action.getText('pdfReportPrints')}";
			 op.style.display="block";
		 }else if(op.style.display=="block"){
			getObjByName("excelPrints").value="${action.getText('pdfReportPrints')}";
			op.style.display="none";
		 }
		 window.scrollTo(0,document.body.scrollHeight);	//滚动条到最下面
		
	   }
	   //失去焦点隐藏导航层
	   function onBlurs(op){
		getObjByName(op).style.display="none";
	   }
	   
	   //按月份供应商pdf
       function open_spareOutBillPdf(){
	       var outDate = getObjByName("outDate_start").value;
	       if(""==outDate || !validateTime(outDate)){
	          alert("请输入正确的时间格式，正确格式2011-01-01");
	          getObjByName("outDate_start").focus();
	          return false;
	       }
	       var url='${req.contextPath}/reports/spare/spareOutBillMonthView.pdf?outDate='+outDate;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   }
        //按月份供应商xls  
	   function open_spareOutBillXls(){
	       var outDate = getObjByName("outDate_start").value;
	        if(""==outDate || !validateTime(outDate)){
	          alert("请输入正确的时间格式，正确格式2011-01-01");
	          getObjByName("outDate_start").focus();
	          return false;
	       }
	       var url='${req.contextPath}/reports/spare/spareOutBillMonthView.xls?outDate='+outDate;  
           url = encodeURI(url);
           window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	   } 
</script>