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
		<tr>
		   <@ww.textfield label="'${action.getText('factory.supplierNo')}'" name="'supplierNo'" value="'${req.getParameter('supplierNo')?if_exists}'" cssClass="'underline'" />	 
	 	   <@ww.textfield label="'${action.getText('factory.name')}'" name="'name'" value="'${req.getParameter('name')?if_exists}'" cssClass="'underline'" />	
	 	</tr>
	 	<tr>
	       <@ww.select label="'${action.getText('factory.supplierType')}'" 
	                   required="false" 
	                   name="'cartory.name'" 
    			       value="'${req.getParameter('cartory.supplierType')?if_exists}'" 
    			       listKey="code" 
    			       listValue="value"
                       list="factoryType" 
                       emptyOption="false" 
                       disabled="false">
        </@ww.select>
        <@ww.select label="'${action.getText('factory.country')}'" 
	                  required="false" 
	                  name="'country.id'" 
    			      listKey="id" 
    			      listValue="name"
                      list="factoryCountry" 
                      emptyOption="true" 
                      disabled="false">
        </@ww.select>
       </tr>
	 	<tr>
	          <@ww.textfield label="'${action.getText('factory.zone')}'" name="'zone'" value="'${req.getParameter('zone')?if_exists}'" cssClass="'underline'"  />	 
	    </tr>
	    <tr>   	
         <@eam2008_onlySearchInvalid_checkBox/>
       </tr>
      <script language="javascript">
    	    <#if req.getParameter('cartory.name')?exists>
		    	//设置同步
    	        $('cartory.name').value='${req.getParameter('cartory.name')?if_exists}';
	    	</#if>
        	<#if req.getParameter('country.id')?exists>
		    	//设置同步
    	        $('country.id').value='${req.getParameter('country.id')?if_exists}';
	    	</#if>
       function checkInvalidParms(){
           if (document.getElementById("cartory.name").value==-1){
              document.getElementById("cartory.name").value='';
           }
          return true;
       }
      </script>
	</@inputTable> 