<#include "../../includes/eam2008.ftl" />

<@framePage title="${action.getText('subscribeCollectBillDetails.title')}">	
 	<link rel="stylesheet" href="${req.contextPath}/stylesheets/calendar.css" type="text/css"/>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/lang/calendar.js"></script>
   <script language="javascript" src="${req.contextPath}/javascripts/calendar-setup.js"></script>
   <@ww.hidden name="'pagingPage'" value="'${req.getParameter('pagingPage')?if_exists}'"/>  
	<@ww.form namespace="'/devicesubscribeSummary'" name="'listForm'" action="'searchSubscribeCollectBillDetails'" method="'post'">
	<@ww.token name="searchSubscribeCollectBillDetailToken"/>
		<#if subscribeCollectBill.id?exists>
			<@ww.hidden name="'subscribeCollectBill.id'" value="'#{subscribeCollectBill.id}'"/>
		</#if>
		 <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>		
		 <#assign loopNum=0/>
		
		  <input type="hidden" name="allSubscribeCollectBillDetailBuyer" value=""/>
		  <input type="hidden" name="allSubscribeDetailIds" value=""/>
		  <input type="hidden" name="notPurchaseIds" value=""/>
		  <input type="hidden" name="allBeizhu" value=""/>
		  
		 <#assign itemNo=1/>
		<#assign requestDateIdentityName = 'request.Date' + '${itemNo}'/>
		<#assign requestDateImgIdentity = 'listForm_' + '${requestDateIdentityName}' + '_trigger'/>
	
		 <@list title=""  includeParameters="subscribeCollectBill.id|toolingDevFlag" fieldMap="">
			<@vlh.checkbox property="id" name="subscribeDtlIds">
                <@vlh.attribute name="width" value="25" />
            </@vlh.checkbox>    
            <input type="hidden" name="hiddStatu" value="${object.status}"/>   
            <@vcolumn title="${action.getText('itemNo')}">
               ${itemNo}
                <@alignCenter />
           </@vcolumn>
           <#assign itemNo = itemNo + 1/>
             <@vcolumn title="${action.getText('graphNo')}" property="graphNo" attributes="width='85'">
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('name')}" property="name" attributes="width='100'">
          	  ${object.name}
            	<@alignLeft />
            </@vcolumn>
            <@vcolumn title="${action.getText('规格型号')}" property="model" attributes="width='100'">      
            	<@alignLeft />
            </@vcolumn>            
            
              <@vcolumn title="${action.getText('订货号')}" property="orderNo" attributes="width='50'">
            	<@alignLeft />
            </@vcolumn>     
            
            <@vcolumn title="${action.getText('生产厂家')}" property="factory.name">
        	<@alignRight />
       		</@vcolumn>
       		 <@vcolumn title="${action.getText('种类')}" property="category.name">           
            	<@alignLeft />
            </@vcolumn>
            <#--
             <@vcolumn title="${action.getText('明细种类')}" property="detailCategory.name">
            	<@alignLeft />
            </@vcolumn>    
            -->
             <@vcolumn title="${action.getText('单位')}" property="calUnit.value">
            	<@alignLeft />
            </@vcolumn>    
           <@vcolumn title="${action.getText('所属设备')}" property="ownedEquipment"/>
           <@vcolumn title="${action.getText('设备厂家')}" property="equFactoryStr"/>   
           <@vcolumn title="供应商" property="supplierName">
            	<@alignLeft />
            </@vcolumn> 
          <@vcolumn title="${action.getText('申购数量')}" property="amount">
        		<@alignRight />
       		</@vcolumn>
       		<@vcolumn title="${action.getText('总库存')}" property="spare.stocks">
        		<@alignRight />
       		</@vcolumn>
       		<@vcolumn title="${action.getText('库存明细')}">
	       		<#if object.spare?exists>
	       		<#--
	       			<a href="javascript:open_stock(#{object.spare.id});">查看</a>
	       			-->
	       			<a href="javascript:open_selectDialog('${object.graphNo}');">查看</a>
	       			<#else>
	       			<a href="javascript:alert('无库存');">查看</a>
	       		</#if>
        		<@alignCenter />
       		</@vcolumn>          	
       		<@vcolumn title="${action.getText('采购人')}">
           	    <#assign BuyerCode = ''/>
           	    <#assign BuyerId = ''/>
           	    <#assign BuyerName = ''/>
				<#if object.buyer?exists>
				 	<#assign BuyerCode = "${object.buyer.loginName?if_exists}" />
				 	<#assign BuyerName = "${object.buyer.name?if_exists}" />
				    <#assign BuyerId = "${object.buyer.id?if_exists}"/>
				</#if>				
			    <@eam2008_getBuyer id= "${BuyerId?if_exists}" code="${BuyerCode?if_exists}" name="${BuyerName?if_exists}" loopNum="${loopNum}" inputName="buyer.code" inputId="buyer.id" inputN="buyer.name" disabled="false"/>   
       		</@vcolumn>         
                
            <@vcolumn title="${action.getText('采购数量')}" property="buyeAmount">          
        	<@alignLeft />
       		</@vcolumn>
             <@vcolumn title="${action.getText(' 采购日期 ')}" property="purchaseDate" format="yyyy-MM-dd">
         	   <@alignRight />
            </@vcolumn>     
            <@vcolumn title="${action.getText('unitPrice')}" property="unitPrice" format="${action.getText('currencyFormat')}">
             <@alignRight />
       		</@vcolumn>
            <@vcolumn title="${action.getText('totalPrice')}" property="totalPrice" format="${action.getText('currencyFormat')}">
            	<@alignRight />
            </@vcolumn>       
       
       		<@vcolumn title="${action.getText('到货数量')}" property="arrivalAmount">       		
        		<@alignRight />
       		</@vcolumn>
       		<@vcolumn title="${action.getText(' 实到日期 ')}" property="arrivalDate" format="yyyy-MM-dd">       		
        	 <@alignRight />
       		</@vcolumn>
       	   <@vcolumn title="${action.getText('申购部门')}" property="department">
            	<@alignLeft />
            </@vcolumn> 
           <@vcolumn title="${action.getText(' 申购日期 ')}" property="subscribe.subscribeDate" format="yyyy-MM-dd">
         	   <@alignRight />
           </@vcolumn>
           
           <@vcolumn title="备注">
            	 <input type="text" name="beizhu" 
	    		   class="underline"  value="${object.comment?if_exists}"  maxlength="50" size="10" style="text-align:right"/>
	    		   <@vlh.attribute name="style" value="VERTICAL-ALIGN:middle"/>
            	<@alignRight />
            </@vcolumn>
     
       		 <#assign subscribeStatus = ''/>
       		 
       		<#if object.status?exists>
       			<#if '${object.status}' == 'NEW'>
       			<#assign subscribeStatus = "${action.getText('NEWPURCHASE')}"/>
       			<#elseif '${object.status}' == 'COLLECTED'>
       			<#assign subscribeStatus = "${action.getText('NEW')}"/>
       			<#elseif '${object.status}' == 'UNPURCHASE'>
       			<#assign subscribeStatus = "${action.getText('UNPURCHASE')}"/>
       			<#elseif '${object.status}' == 'INPURCHASE'>
       			<#assign subscribeStatus = "${action.getText('PURCHASING')}"/>
       			<#elseif '${object.status}' == 'PURCHASEED'>
       			<#assign subscribeStatus = "${action.getText('PURCHASEED')}"/>
       			<#elseif '${object.status}' == 'INSPECTING'>
       			<#assign subscribeStatus = "${action.getText('STORAGING')}"/>
       			<#elseif '${object.status}' == 'INSPECTED'>
       			<#assign subscribeStatus = "${action.getText('STORAGED')}"/>
       			<#elseif '${object.status}' == 'NOTPURCHASEED'>
       			<#assign subscribeStatus = "${action.getText('不采购')}"/>
       			</#if>
       		 </#if>
            <@vcolumn title="${action.getText('status')}" attributes="width='50'">          
            	${subscribeStatus}
            	<@alignLeft />
            </@vcolumn> 
            
             <#assign loopNum=loopNum+1/>
             
              <div style="display:none">
            	  <input   name= "status" type= "checkbox"  value= "${object.status}" style="visibility:hidden"/> 
            </div>
            
            
            
		</@list>
		
                    
        <@buttonBar>
        	<#if !first>
        	<#--<#if !(action.isReadOnly())>  -->          
            <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return submitDetailIds()'">
            	<@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>               
            <#assign confirmMessage = "${action.getText('confirm.delete')}${action.getText('subscribedtl')}?" />
             <@vsubmit name="'stock'" value="'${action.getText('不采购')}'" onclick="'return chooseRecord();'"/>
            <#--
            <@vsubmit name="'delete'" value="'${action.getText('delete')}'">
                <@ww.param name="'onclick'" value="'return confirmDeletes(\"subscribeDtlIds\", \"${confirmMessage}\")'"/>
                <@ww.param name="'disabled'" value="${valueListNoRecords?string}"/>
            </@vsubmit>
            -->
            <#--</#if>-->
            </#if>              
        </@buttonBar>
	</@ww.form>

</@framePage>
 <script>
      	function open_selectDialog(spareNo) {
	 		var url = '${req.contextPath}/asset/spare/listSpareDetailSearcherCommon.html?readOnly=true&spareNo='+spareNo;
	 		popupModalDialog(url,800,600,callback);
	 	} 
 		function chooseRecord(){
 	        var selector = document.getElementsByName("subscribeDtlIds");
 	        var hiddStatuSelector = document.getElementsByName("hiddStatu");
 	        var spareIds = ""; 	
 	        var length = selector.length;
 	        if(length){
 	           for(var i=0;i<length;i++){
 	               if(selector[i].checked){
 	                    if("NEW" != hiddStatuSelector[i].value && "COLLECTED" != hiddStatuSelector[i].value){
 	                        alert("请选择状态为【新建】的记录!");
 	                        return false;
 	                    }else{
 	                        spareIds+=selector[i].value+",";
 	                    }
 	               }
 	           }
 	        }
 	       
 		<#--	var id=document.getElementsByName("subscribeDtlIds");
 				
 			   if (id.length>0) {
			        for (i = 0; i < id.length; i++) {		        
			           if (id[i].checked) {
			          	  spareIds+=id[i].value+",";
			       }		          
			   }			    
		   	}-->		   
		   	if(spareIds.length){
		  		 document.forms[0].elements["notPurchaseIds"].value=spareIds;		    	
			     return true;		   	
		   	}else{
		   		alert("请选择需要不采购的纪录!");
 				return false;
		   	} 
 		}
 		
 		<#if subscribeCollectBill.billStatus!='NEW'>
      	 	 document.forms[0].elements["save"].disabled="true";
      	 //	 document.forms[0].elements["stock"].disabled="true";
      	 //	 document.forms[0].elements["delete"].disabled="true";
      	 
        </#if>
        
        
       	 	var selector = document.getElementsByName("subscribeDtlIds");//获取所有id
      		var status = document.getElementsByName("status");//获取所有状态
      		
			var st="";	
		    var spareIds = "";
		    if (selector.length) {
		        for (i = 0; i < selector.length; i++) {		        
		            if (status[i].value=='NEW') {
		            	 document.forms[0].elements["save"].disabled=null;
      	 				//document.forms[0].elements["delete"].disabled=null; 
		            }
		        }
		    }
		    if(selector.length==0){
		    	document.forms[0].elements["stock"].disabled="true";
		    }
       		function open_newDeviceAccountDialog(){       		
        		var url = '${req.contextPath}/devicesubscribeSummary/editSubscribeCollectBillDetails.html?subscribeCollectBill.id=#{subscribeCollectBill.id}';
        		popupModalDialog(url, 800, 600)
        	}
        	
        	//保存申购单明细
        	function submitDetailIds(){
        		if(0!=document.getElementsByName("subscribeDtlIds").length){ 	//判断申购单明细中是否有记录        		
	        		<#if subscribeCollectBill.typeStatus?exists>	        	
	        		if(informationValidate()==true){							//验证信息安全	        
	        		    retrieveSubscribeCollectBillDetailubScribeDtlIdsText();			
	        			retrieveSubscribeCollectBillDetailBuyerText();
	        			retrieveSubscribeDetailBeizhuText();
	        			return true;
	        		}else{
						return false;
        			}
        			<#else>
        			alert("${action.getText('please.select.subscribe.typeStatus')}");
        			return false;
        			</#if>
        	  	}
        	}
        	function retrieveSubscribeCollectBillDetailubScribeDtlIdsText(){
        	    var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
        	    var ary2=new Array();
        	    for(var i=0;i<allSubscribeDtlId.length;i++){
        	        ary2.push(allSubscribeDtlId[i].value);
        	    }
        	    document.forms[0].elements["allSubscribeDetailIds"].value=ary2;
        	    //alert(document.forms[0].elements["allSubscribeDetailIds"].value);
        	}
    	 	
		    //获取申购单明细采购人的所有id值		  
		    function retrieveSubscribeCollectBillDetailBuyerText(){		
		    	var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
		       	var ary = new Array();
		       	var ary2=new Array();   
		       	
			     	for(var i=0;i<allSubscribeDtlId.length;i++){
				     	if('' != getObjByNameRe("buyer.id"+i).value){			     	
				     	  // ary2.push(allSubscribeDtlId[i].value);
				     	   ary.push(allSubscribeDtlId[i].value);
			               ary.push(formatDigital(getObjByNameRe("buyer.id"+i).value));
			              //ary.push(formatDigital("3,345"));
				     	}
		             }
		     
		     	//document.forms[0].elements["allSubscribeDetailIds"].value=ary2;
		      	document.forms[0].elements["allSubscribeCollectBillDetailBuyer"].value=ary;
		      
		      
		    }
		    
		      /*
	     *  获取申购单明细备注的所有值 zzb 备注（beizhu）
	     */
	    function  retrieveSubscribeDetailBeizhuText(){
	       var allSubscribeDtlId = document.getElementsByName("subscribeDtlIds");
	       var allBeizhuValue = document.getElementsByName("beizhu");
	       var ary = new Array();
	       var ary2 = new Array();
	       
           for (var i=0; i<allSubscribeDtlId.length; i++) {
              if ('' != allBeizhuValue[i].value) {
               //ary2.push(allSubscribeDtlId[i].value);
               ary.push(allSubscribeDtlId[i].value);
               ary.push( allBeizhuValue[i].value);
             }
           }
           
         // document.forms[0].elements["allSubscribeDetailIds"].value=ary2;
          document.forms[0].elements["allBeizhu"].value=ary;
          //alert(document.forms[0].elements["allSubscribeDetailIds"].value);
	    }
		   
		   
		   
    	  function informationValidate(){
			 
			  return true;           	
		    }
		    
		    function open_stock(spareId){		 	  
		    	var url = '${req.contextPath}/devicesubscribeSummary/listStocksDetals.html?spare.id='+spareId;
	 			popupModalDialog(url,800,600);
		    }
 </script>