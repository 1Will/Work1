<#include "../../../includes/eam2008.ftl" />

<@htmlPage title="出库单维护">
  <style type="text/css">
    .noBorderLine{
       border-width :0px;
       border-style : none;
       outline-style : none;
       text-align:right;
       width:80%;
       }
    .definedLength{
        border-width: 1px;
        border-style: solid;
        border-color: white white black;
        text-align:right;
        width:80%;
    }
  </style>
	<base target="_self">
	<@ww.form name="'spareoutBill'" action="'saveOldSpareOutBill'" method="'post'">
		<@ww.token name="saveSpareOutBillToken"/>	
		<@ww.hidden name="'spareOutBill.oldSpare'" value="'0'"/>
		<@ww.hidden name="'spareOutBill.outType'" value="'0'"/>
		<@ww.hidden name="'storageGrade.id'" value="209"/>
		<@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
		<#if spareOutBill.status?exists>
         	<@ww.hidden name="'status'" value="'${spareOutBill.status}'"/>
		</#if>
		<#if spareOutBill.outPeople?exists>
			<@ww.hidden name="'outPeople.id'" value="'${spareOutBill.outPeople.id?if_exists}'"/>
		<#else>
			<@ww.hidden name="'outPeople.id'" value=""/>
		</#if>
		<#assign itemNo = 1/> 
		<#assign sum = 1/>  
		<@inputTable>
			<#if spareOutBill.id?exists>
	            <@ww.hidden name="'spareOutBill.id'" value="#{spareOutBill.id}"/>
	        </#if>
			<tr>
				<@ww.textfield label="'出库单编码'" name="'spareOutBill.code'" value="'${spareOutBill.code?if_exists}'" cssClass="'underline'" readonly="false" required="true" />
	 	  		<@ww.textfield label="'出库单名称'" name="'spareOutBill.name'" value="'${spareOutBill.name?if_exists}'" cssClass="'underline'" required="true"/>
	 	    </tr>
	 	 
	 	   
			 <tr><td colspan="6"><HR align="middle" size="1" width="95%" color="#999999" noshade /></td></tr>
       	<tr>
       	 <#-- 出仓库 -->
   	 	<@ww.select 
					label="'出仓库'" 
					required="true" 
					id="warehouse.id"
					name="'warehouse.id'" 
					value="'${req.getParameter('warehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allWarehouse" 
					emptyOption="false" 
					disabled="false">
				
			</@ww.select> 
	 	
			  <#-- 不存在入仓库 
		<@ww.select 
					label="'进入仓库'" 
					required="false" 
					id="inWarehouse.id"
					name="'inWarehouse.id'" 
					value="'${req.getParameter('inWarehouse.id')?if_exists}'" 
					listKey="id" 
					listValue="name"
					list="allOutWarehouse" 
					emptyOption="false" 
					disabled="false">
				
			</@ww.select>  
			--> 
			<@pp.datePicker label="'出库日期'" 
							name="'spareOutBill.outDate'" 
							value="'${(spareOutBill.outDate?string('yyyy-MM-dd'))?if_exists}'" 
							cssClass="'underline'" 
							required="true"/>   
			         </tr>
	 	    <tr>
			 
			<#--出库日期-->
	 	    <@ww.select label="'领料部门'" 
		                   required="true" 
		                   name="'department.id'" 
	    			       value="'${req.getParameter('department.id')?if_exists}'" 
	    			       listKey="id" 
	    			       listValue="name"
	                       list="departments" 
	                       emptyOption="true" 
	                       disabled="false"/>
	                       </tr>
			  
	      
	 	    <tr>
	 	  	<@ww.textfield label="'领料人'" 
	 	  				   name="'spareOutBill.borrower'" 
	 	  				   value="'${spareOutBill.borrower?if_exists}'" 
	 	  				   cssClass="'underline'" 
	 	  				   required="true"/>
	 	    
	 	    <#--出库人-->
			<@pp.remotePicker label="'领料人'" 
							  name="'spareOut.outPeople'"
							  selectorName="'userSelectorAjax'" 
							  cssClass="'underline'" 
							  value="spareOutBill.outPeople"
							  popup="'${req.contextPath}/popup/userSelector.html'" 
							  size="15" 
							  codeName="'loginName'" 
							  required="true"/>
</tr>
	 	    <tr>
	 	    <@ww.textfield label="'出库总金额(元)'" name="'spareOutBill.totalPrice'" value="'${spareOutBill.totalPrice?if_exists}'" cssClass="'underline'" disabled="'true'"/>    
	 	    
	 	    <#assign status=''/>
        	<#if spareOutBill.status?exists>
				<#if '${spareOutBill.status}' == 'NEWSTATUS'>
		      		<#assign status = "${action.getText('NEWSTATUS')}"/>
		      	<#elseif '${spareOutBill.status}' == 'OUTWAREHOUSEING'>
		   			<#assign status = "${action.getText('OUTWAREHOUSEING')}"/>
		       	<#elseif '${spareOutBill.status}' == 'OUTWAREHOUSEED'>
		       		<#assign status = "${action.getText('OUTWAREHOUSEED')}"/>
        		</#if>
        	</#if>
			<@ww.textfield label="'状态'" 
						   name="'spareOutBill.status'" 
						   value="'${status}'" 
						   cssClass="'underline'" 
						   readonly="true"/>
	 	    </tr>
	 	    <tr>
	 	    <@ww.textarea label="'备注'" 
	 	    			  name="'spareOutBill.comment'" 
	 	    			  value="'${spareOutBill.comment?if_exists}'" 
	 	    			  cssClass="'underline'" 
	 	    			  required="false" 
	 	    			  rows="3" 
	 	    			  cols="50"/> 	    
	 	    </tr>    
		</@inputTable> 
		<@buttonBar>
	 	    <#if !(action.isReadOnly())>	
	        	<@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return checkInput()'"/>
	        	   <#if spareOutBill.id?exists>	 
	        	       <@redirectButton value="${action.getText('继续新建')}" url="${req.contextPath}/spare/editOldSpareOutBill.html?readOnly=${req.getParameter('readOnly')?if_exists}&pagingPage=${pagingPage?if_exists}"/>  
	               </#if>
	        </#if>
	        	<@redirectButton  value="${action.getText('back')}" url="listOldSpareOutBill.html?readOnly=${req.getParameter('readOnly')?if_exists}&pagingPage=${pagingPage?if_exists}"/>
	        	<#if spareOutBill.id?exists>
	        	    <#if spareOutBill.storageGrade.id==209?if_exists>
	        	       <@vbutton name="print"  class="button" value="${action.getText('pdfOutPrint')}" onclick="open_twoOutWareHouseBillPdf('#{spareOutBill.id}')"/>
		               <@vbutton name="print"  class="button" value="${action.getText('xlsOutPrint')}" onclick="open_twoOutWareHouseBillXls('#{spareOutBill.id}')"/>
	        	    <#else>
	        	       <@vbutton name="print"  class="button" value="${action.getText('pdfOutPrint')}" onclick="open_outWareHouseBillPdf('#{spareOutBill.id}')"/>
		               <@vbutton name="print"  class="button" value="${action.getText('xlsOutPrint')}" onclick="open_outWareHouseBillXls('#{spareOutBill.id}')"/>
	        	    </#if>
	    		<#--
	    		<#if !(action.isReadOnly())>
	    		<@vsubmit name="'submitRecord'" value="'${action.getText('sendsubmit')}'" />
	    		</#if>
	    		-->
	    		</#if>
	     </@buttonBar>
	</@ww.form>
	<script language="JavaScript" type="text/JavaScript">
		 <#if spareOutBill.status!='NEWSTATUS'>	
      	 	 document.forms[0].elements["save"].disabled="true";
        </#if>
        <#--
		<#if spareOutBill.storageGrade?exists>
          getObjByName('storageGrade.id').value = #{spareOutBill.storageGrade.id?if_exists};
           DWREngine.setAsync(false); 
	    	//回调种类的值后触发DWR 级联明细种类  
	      wareHouseCascadeDWR("storageGrade.id","warehouse.id",${loginUser.id?if_exists},"")
	    	//重新设置为异步方式
	      DWREngine.setAsync(true);
       
        </#if> --> 
	   window.onload=function(){
	    //显示隐藏标签
	    <#if spareOutBill.storageGrade?exists>
            getObjByName('storageGrade.id').value= #{spareOutBill.storageGrade.id};
              <#if spareOutBill.storageGrade.id==209?if_exists>
              <#--
                   getObjByName('inWarehouse.id').parentNode.style.display="none";
	               getObjByName('inWarehouse.id').parentNode.previousSibling.style.display="none";
	          -->    
	               getObjByName('department.id').parentNode.style.display="none";
	  	           getObjByName('department.id').parentNode.previousSibling.style.display="none";
	  	           	 
	  	           getObjByName('spareOutBill.borrower').parentNode.style.display="none";
	         	   getObjByName('spareOutBill.borrower').parentNode.previousSibling.style.display="none";	 
	         	  <#--
	         	   getObjByName('spareOut.outPeople.code').parentNode.style.display="none";
	         	   getObjByName('spareOut.outPeople.code').parentNode.previousSibling.style.display="none";	
	         	   
	         	   getObjByName('spareOutBill.outDate').parentNode.style.display="none";
         	       getObjByName('spareOutBill.outDate').parentNode.previousSibling.style.display="none";	
         	       -->
              </#if>
              <#if spareOutBill.storageGrade.id==208?if_exists>
               <#--
                   getObjByName('inWarehouse.id').value=2;
                   
                   getObjByName('department.id').parentNode.style.display="block";
	  	           getObjByName('department.id').parentNode.previousSibling.style.display="block";	 
	  	           
	  	           getObjByName('spareOutBill.borrower').parentNode.style.display="block";
	         	   getObjByName('spareOutBill.borrower').parentNode.previousSibling.style.display="block";
	         	   
	         	   getObjByName('spareOut.outPeople.code').parentNode.style.display="block";
	         	   getObjByName('spareOut.outPeople.code').parentNode.previousSibling.style.display="block";
	         	   
	         	   getObjByName('spareOutBill.outDate').parentNode.style.display="block";
         	       getObjByName('spareOutBill.outDate').parentNode.previousSibling.style.display="block";
         	       <#if spareOutBill.department?exists>	
         	           getObjByNameRe("department.id").value=#{spareOutBill.department.id};  
         	       </#if> 
         	       --> 
         	       
         	        <#--
                   getObjByName('inWarehouse.id').parentNode.style.display="none";
	               getObjByName('inWarehouse.id').parentNode.previousSibling.style.display="none";
	          -->    
	               getObjByName('department.id').parentNode.style.display="none";
	  	           getObjByName('department.id').parentNode.previousSibling.style.display="none";
	  	           	 
	  	           getObjByName('spareOutBill.borrower').parentNode.style.display="none";
	         	   getObjByName('spareOutBill.borrower').parentNode.previousSibling.style.display="none";	 
	         	  <#--
	         	   getObjByName('spareOut.outPeople.code').parentNode.style.display="none";
	         	   getObjByName('spareOut.outPeople.code').parentNode.previousSibling.style.display="none";	
	         	   
	         	   getObjByName('spareOutBill.outDate').parentNode.style.display="none";
         	       getObjByName('spareOutBill.outDate').parentNode.previousSibling.style.display="none";	
         	       --> 	
              </#if>
        </#if>
        
        <#--
	    <#if loginUser.department?exists>
	     getObjByNameRe("department.id").value = #{loginUser.department.id};
	   </#if>-->
       <#if  spareOutBill.id?exists>
         <#--
         <#if spareOutBill.department?exists>
            getObjByNameRe("department.id").value=#{spareOutBill.department.id};              
        </#if>-->
      <#else>
       	a = new Date();
		var time=a.format("yyyy-MM-dd");
		document.forms["spareoutBill"].elements["spareOutBill.outDate"].value=time;
      </#if>
      <#if spareOutBill.id?exists>   
      	<#if spareOutBill.inWarehouse?exists>
			 var url = 'listOldSpareOutBillDetail.html?spareOutBill.id=#{spareOutBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}&warehouse=${spareOutBill.warehouse.id}&inwarehouse=${spareOutBill.inWarehouse.id}';      
			 <#else>
			 var url = 'listOldSpareOutBillDetail.html?spareOutBill.id=#{spareOutBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}&warehouse=${spareOutBill.warehouse.id}';      
		 </#if>
	     document.all.frame.src = url;
		 getObjByNameRe("details").className = "selectedtab";		
  	  </#if>
  	  <#--
	  <#if !(action.isReadOnly())>
		   <#if spareOutBill.id?exists>	  
		  	  <#if spareOutBill.submit==false>
				document.forms[0].elements["submitRecord"].disabled="true";
				<#else>
				document.forms[0].elements["submitRecord"].disabled="false";
			  </#if>
		   </#if>
	 </#if>
	 -->
          //出仓库          
         <#if spareOutBill.warehouse?exists>
			getObjByName('warehouse.id').value='${spareOutBill.warehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('warehouse.id')?exists>
	    		getObjByName('warehouse.id').value='${req.getParameter('warehouse.id')?if_exists}';
	    	</#if>
	    </#if> 
	    //入仓库
	      <#if spareOutBill.inWarehouse?exists>
			getObjByName('inWarehouse.id').value='${spareOutBill.inWarehouse.id?if_exists}';
	    <#else>
	    	<#if req.getParameter('inWarehouse.id')?exists>
	    		getObjByName('inWarehouse.id').value='${req.getParameter('inWarehouse.id')?if_exists}';
	    	</#if>
	    </#if>
	    
	    <#if spareOutBill.storageGrade?exists>
            getObjByName('storageGrade.id').value= #{spareOutBill.storageGrade.id};
        </#if>
       
      }
     function removewarehouse(){
	   	 var storage = getObjByName('storageGrade.id').value;
	     <#--显示影藏标签-->
	   	 if(storage=='209'){	
	   	 <#--
         	getObjByName('inWarehouse.id').parentNode.style.display="none";
            getObjByName('inWarehouse.id').parentNode.previousSibling.style.display="none";
            getObjByName('warehouse.id').value="";
         -->   
            getObjByName('department.id').parentNode.style.display="none";
  	        getObjByName('department.id').parentNode.previousSibling.style.display="none";	 
  	        
  	        getObjByName('spareOutBill.borrower').parentNode.style.display="none";
         	getObjByName('spareOutBill.borrower').parentNode.previousSibling.style.display="none";	 
         	<#-- 
         	getObjByName('spareOut.outPeople.code').parentNode.style.display="none";
         	getObjByName('spareOut.outPeople.code').parentNode.previousSibling.style.display="none";	
         	
         	getObjByName('spareOutBill.outDate').parentNode.style.display="none";
         	getObjByName('spareOutBill.outDate').parentNode.previousSibling.style.display="none";	
         	-->
              
	  	  }else{
  	   		getObjByName('inWarehouse.id').parentNode.style.display="block";
            getObjByName('inWarehouse.id').parentNode.previousSibling.style.display="block";
            
            getObjByName('department.id').parentNode.style.display="block";
  	        getObjByName('department.id').parentNode.previousSibling.style.display="block";	
  	         
  	        getObjByName('spareOutBill.borrower').parentNode.style.display="block";
         	getObjByName('spareOutBill.borrower').parentNode.previousSibling.style.display="block";
         	
         	getObjByName('spareOut.outPeople.code').parentNode.style.display="block";
         	getObjByName('spareOut.outPeople.code').parentNode.previousSibling.style.display="block";	
         	
         	getObjByName('spareOutBill.outDate').parentNode.style.display="block";
         	getObjByName('spareOutBill.outDate').parentNode.previousSibling.style.display="block";	
	                 	  
	  	  }
	  	  if(storage=='208'){	
	         	 getObjByName('inWarehouse.id').value=2;
	         	        
	  	  }
    }
	 function user_OpenDialog() {
	  		var url = "${req.contextPath}/popup/userSelector.html";
	  		popupModalDialog(url, 800, 600, userSelectorHandler);
	 }	
	 function userSelectorHandler(result) {
	  		document.forms[0].elements["spareOutBill.outPeople"].value = result[1];
	  		document.forms[0].elements["outPeople.id"].value = result[0];
	 }
	 	function checkInput() {
	 	
	 	//验证出库单编号    
     	if ('' == document.forms["spareoutBill"].elements["spareOutBill.code"].value) {       
			alert("${action.getText('spareOutBill.code.requiredstring')}");
			getObjByName("spareOutBill.code").focus();
			return false;
		} else {
			if (!isValidLengthValue(document.forms["spareoutBill"].elements["spareOutBill.code"].value,0,50)) {
				 alert("${action.getText('spareOutBill.code.stringlength')}");
				 return false;
			}
		 }
		 
	     //验证出库单名称
     	if ('' == document.forms["spareoutBill"].elements["spareOutBill.name"].value) {       
			alert("${action.getText('spareOutBill.name.requiredstring')}");
			getObjByName("spareOutBill.name").focus();
			return false;
		} else {
			if (!isValidLengthValue(document.forms["spareoutBill"].elements["spareOutBill.name"].value,0,50)) {
				 alert("${action.getText('spareOutBill.name.stringlength')}");
				 return false;
			}
		 }
		 
		 
	
		 var storageGradeId = getObjByName('storageGrade.id').value;
		 
		  if(storageGradeId==''){
		      alert("请选择仓库级别！");
		      getObjByName('storageGrade.id').focus();
		   return false;
		  }
		  
		  
		<#--验证部门
		if('208'==storageGradeId){
		   if ('' == document.forms["spareoutBill"].elements["department.id"].value) {       
				alert("${action.getText('spareOutBill.department.requiredstring')}");
				getObjByName("department.id").focus();
				return false;
		   }
		}
      
		//验证领料人
		if('208'==storageGradeId){
			if (''== document.forms["spareoutBill"].elements["spareOutBill.borrower"].value) {       
					alert("${action.getText('spareOutBill.borrower.requiredstring')}");
					getObjByName("spareOutBill.borrower").focus();
					return false;
			}
		}
		-->
		//验证出仓库
		
		 if(getObjByName('warehouse.id').value==-1||getObjByName('warehouse.id').value==null||getObjByName('warehouse.id').value==''){
		 	alert("请选择出仓库");
		 	return false;
		 }	
		 <#--	
		 //验证入仓库
		 if(getObjByName('inWarehouse.id').parentNode.style.display!="none" && '208'==storageGradeId){
			 if(getObjByName('inWarehouse.id').value==-1||getObjByName('inWarehouse.id').value==null||getObjByName('inWarehouse.id').value==''){
			 	alert("请选择入仓库");
			 	return false;
			 }
		}
		-->
		//验证出库时间
		if('208'==storageGradeId){
         	if ('' == document.forms["spareoutBill"].elements["spareOutBill.outDate"].value) {       
				alert("${action.getText('spareOutBill.outDate.requiredstring')}");
				return false;
		  }
		   //验证出库时间格式 yy-mm-dd
		  var date=getObjByNameRe("spareOutBill.outDate").value;
			  if(!isDate("spareOutBill.outDate")){
			    alert("${action.getText('select.right.outDate')}");
				return false;
			  }
		
		}
     
		  <#--验证出库人-->
		 if('208'==storageGradeId){
		      if ('' == document.forms["spareoutBill"].elements["spareOut.outPeople.id"].value) {       
				alert("${action.getText('spareOutBill.outPeople.requiredstring')}");
				return false;
		     }
		  
		  }
		
		
	 }	
    function open_outWareHouseBillPdf(id){
		var spareOutWareBillId = id;
		var url='${req.contextPath}/reports/spare/spareOutWareHouseBill.pdf?spareOutWareBillId='+spareOutWareBillId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	
	function open_outWareHouseBillXls(id){
		var spareOutWareBillId = id;
		var url='${req.contextPath}/reports/spare/spareOutWareHouseBill.xls?spareOutWareBillId='+spareOutWareBillId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}
	
	function open_twoOutWareHouseBillXls(id){
	    var spareOutWareBillId = id;
		var url='${req.contextPath}/reports/spare/twoSpareOutWareHouseBill.xls?spareOutWareBillId='+spareOutWareBillId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}
	function open_twoOutWareHouseBillPdf(id){
		var spareOutWareBillId = id;
		var url='${req.contextPath}/reports/spare/twoSpareOutWareHouseBill.pdf?spareOutWareBillId='+spareOutWareBillId;  
		url = encodeURI(url);
		window.open(url,"_new","toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=screen.width,height=screen.height,left=0,top=0"); 
	}	
	
	
</script>
    <#if spareOutBill.id?exists>
		<ul id="beautytab">
			<li><a id="details" onclick="activeTab(this);"  
				href="listOldSpareOutBillDetail.html?spareOutBill.id=#{spareOutBill.id}&readOnly=${req.getParameter('readOnly')?if_exists}" target="frame" >出库单明细</a>
			</li>
		</ul>
		<iframe  name="frame" frameborder="0.5" src="" marginHeight="0" marginWidth="0" scrolling="auto"  vspace=0 hspace=0 width="100%" height="100%"/>
	 </#if>
</@htmlPage>