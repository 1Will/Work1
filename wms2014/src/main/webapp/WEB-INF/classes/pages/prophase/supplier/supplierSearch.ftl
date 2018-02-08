<#--
  Copyright (c) 2001-2005 vTradEx Information Technology Co.,Ltd. All Rights Reserved.
    This software is the confidential and proprietary information of vTradEx
  Information Technology Co.,Ltd. ("Confidential Information").  You shall not
  disclose such Confidential Information and shall use it only in
  accordance with the terms of the license agreement you entered into
  with vTradEx.
    VTRADEX MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
  SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
  IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
  PURPOSE, OR NON-INFRINGEMENT. VTRADEX SHALL NOT BE LIABLE FOR ANY DAMAGES
  SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
  THIS SOFTWARE OR ITS DERIVATIVES.
 -->
<#--$Id: supplierSearch.ftl 11283 2008-03-12 02:37:38Z zbzhang $ -->
	 	<@inputTable>
	 	<@ww.hidden name="'unSubmit'" value=""/>
	 	 <@ww.hidden name="'toolingDevFlag'" value="'${toolingDevFlag?if_exists}'"/>
		<tr>
		   <@ww.textfield label="'${action.getText('supplier.supplierNo')}'" name="'supplierNo'" value="'${req.getParameter('supplierNo')?if_exists}'" cssClass="'underline'" />	 
	 	   <@ww.textfield label="'${action.getText('supplier.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	 	</tr>
	 	<tr>
	       <@ww.select label="'${action.getText('supplier.cartory')}'" 
	                   required="false" 
	                   name="'cartory.name'" 
    			       value="'${req.getParameter('cartory.name')?if_exists}'" 
    			       listKey="code" 
    			       listValue="value"
                       list="supplierCatory" 
                       emptyOption="false" 
                       disabled="false">
        </@ww.select>
         <@ww.select label="'${action.getText('supplier.level')}'" 
                     required="false" 
                     name="'level.id'" 
    			     value="'${req.getParameter('level.id')?if_exists}'" 
    			     listKey="id" 
    			     listValue="value"
                     list="supplierLevel" 
                     emptyOption="false" 
                     disabled="false">
        </@ww.select>
        
       </tr>
	 	
	 	<tr>
	          <@ww.textfield label="'${action.getText('supplier.country')}'" name="'country'" value="'${req.getParameter('country')?if_exists}'" cssClass="'underline'"  />	
	          <@ww.textfield label="'${action.getText('supplier.zone')}'" name="'zone'" value="'${req.getParameter('zone')?if_exists}'" cssClass="'underline'"  />	 
	    </tr>
	    <tr>
	        <#--  <@ww.select label="'${action.getText('supplier.registerFunds')}'" 
                     required="false" 
                     name="'supplier.registerFunds'" 
    			     value="'${req.getParameter('supplier.registerFunds')?if_exists}'" 
    			     listKey="value" 
    			     listValue="label"
                     list="registeredFunds" 
                     emptyOption="false" 
                     disabled="false">
             <@ww.hidden  name="'minValue'" value=""/>
             <@ww.hidden name="'maxValue'" value=""/>    
             </@ww.select>-->
           <#--<@ww.textfield  label="'${action.getText('supplier.registerFunds')}'" name="'registerFunds'" 
		       value="'${req.getParameter('registerFunds_min')?if_exists}|${req.getParameter('registerFunds_max')?if_exists}'"
		       cssClass="'underline'" /> -->
		       <#--
		      <td align="right" valign="top">
                <span class="required"></span><label class="label">${action.getText('supplier.registerFunds')}:</label>
               </td>
               <td>
		       <input type='text'name="registerFunds" class="underline" value="${registerFunds?if_exists}"></td>
		       <td align="left" valign="top">
               ${action.getText('h')}
               </td>
               <td>
               <input type='text'name="registerFunds" class="underline" value="${registerFunds?if_exists}">
		       </td>
		      
		      <td align="right" valign="top"><label  for="registerFunds" class="label">${action.getText('supplier.registerFunds')}:</label></td>
		      <td>
		        <input type="text" name="registerFunds_min" size="10" maxlength="10" value="" id="registerFunds_min" class="underline" />
		        --
                <input type="text" name="registerFunds_max" size="10"  maxlength="10" value="" id="registerFunds_max" class="underline"/>
	         -->
	        <#-- <@ww.select label="'${action.getText('state')}'" 
	                     required="false" 
	                     name="'docState.code'" 
    			         value="'${req.getParameter('docState.code')?if_exists}'" 
    			         listKey="code" listValue="value"
                         list="docStates" 
                         emptyOption="false" 
                         disabled="false">
            </@ww.select>-->
              	
         <@eam2008_onlySearchInvalid_checkBox/>
       </tr>
      <script language="javascript">
    	      var selector = document.all("cartory.name");
              var groups=selector.options.length;  
              for (i=0; i<groups; i++){
                 <#if req.getParameter('cartory.name')?exists>
                  if (selector.options[i].value=="${req.getParameter('cartory.name')?if_exists}"){
                     selector.options[i].selected="true";
                 }
                </#if>
              } 
             var selector = document.all("level.id");
             var groups=selector.options.length;  
             for (i=0; i<groups; i++){
                <#if req.getParameter('level.id')?exists>
               //  if (selector.options[i].value=="${req.getParameter('level.id.realCode')?if_exists}"){
                 if (selector.options[i].value=="${req.getParameter('level.id')?if_exists}"){
                    selector.options[i].selected="true";
                 }
                </#if>
             } 
        
       function checkInvalidParms(){
           if (document.getElementById("cartory.name").value==-1){
              document.getElementById("cartory.name").value='';
           }
           if (document.getElementById("level.id").value==-1){
              document.getElementById("level.id").value='';
           }

          return true;
       }
     <#--
       function getQueryString(returnValue){
           var startPos=returnValue.indexOf("LT");
           var startValue=returnValue.lastIndexOf("_");
           var stringLength=returnValue.length;
           var endValue;
           if(startPos!=-1){
              document.getElementById("maxValue").value=returnValue.substring(startValue+1,stringLength);
              document.getElementById("minValue").value='';
           }
       
          startPos=returnValue.indexOf("GT");
          startValue=returnValue.lastIndexOf("_");
          stringLength=returnValue.length;
          if(startPos!=-1){
             document.getElementById("minValue").value=returnValue.substring(startValue+1,stringLength);
             document.getElementById("maxValue").value='';
          }
            
          startPos=returnValue.indexOf("BETWEEN");
          stringLength=returnValue.length;
          startValue=(returnValue.substring(startPos,stringLength)).indexOf("_");
          if(startPos!=-1){
             endValue=returnValue.indexOf("AND");
             document.getElementById("minValue").value=returnValue.substring(startValue+4,endValue-1);
             document.getElementById("maxValue").value=returnValue.substring(endValue+4,stringLength);
          }
       }
       -->
      </script>
	</@inputTable> 