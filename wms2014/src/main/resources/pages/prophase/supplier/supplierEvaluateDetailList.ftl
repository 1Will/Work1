<#--
	Copyright (c) 2001-2007 YongJun Technology Pte.,Ltd. All
	Rights Reserved.
	
	This software is the confidential and proprietary information of 
	YongJun Technology Pte.,Ltd. ("Confidential Information"). You
	shall not disclose such Confidential Information and shall use it only in
	accordance with the terms of the license agreement you entered into with
	YongJun.
	
	YONGJUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
	SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE IMPLIED
	WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE, OR
	NON-INFRINGEMENT. YONGJUN SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED BY
	LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING THIS SOFTWARE OR ITS
 	DERIVATIVES.
-->
<#-- $Id: -->
<#include "../../includes/eam2008.ftl" />
<@framePage title="${action.getText('evaluate.title')}">
     <@ww.form namespace="'/prophase/supplier'" name="'evaluateDetail'" action="'sercherEvaluateDetails'" method="'post'">
	       <#if supplierEvaluate.id? exists>
	      <@ww.hidden name="'supplierEvaluate.id'" value="#{supplierEvaluate.id}"/>
	      </#if>
	        <input type="hidden" name="allsupplierGrade" value=""/>
	        <input type="hidden" name="allsuppliermoment" value=""/>
	        <input type="hidden" name="designCapabilityInfo" value=""/>
	        <input type="hidden" name="makeCapabilityInfo" value=""/>
	        <input type="hidden" name="qACapabilityInfo" value=""/>
	        <input type="hidden" name="seviceCapabilityInfo" value=""/>
	        <input type="hidden" name="basicControlCapabilityInfo" value=""/>
	        <@ww.hidden name="'readOnly'" value="'${req.getParameter('readOnly')?if_exists}'"/>
	     <#-- <@titleBar title="${action.getText('spareDoc.list')}"/>-->
	      <@listTable>
	      	<tr>
	      		<th>${action.getText('itemNo')}</th>
	      		<th>${action.getText('evaluate.conmect')}</th>
 	      		<th>${action.getText('evaluate.grade')}</th>
	      		<th>${action.getText('evaluate.stander')}</th>
	      		<#--
	      		<th>${action.getText('evaluate.gradeinstruct')}</th>
	      		-->
	      		<th>${action.getText('evaluate.minute')}</th>
	      		<th>${action.getText('evaluate.coment')}</th>
	         </tr>

	               <tr>

	         <tr>

		                <td class="alignCenter">1</td>
		                <td class="alignLeft">设计能力</td>
		                <td class="alignLeft">30</td>
		                <td  class="alignLeft" width="300" height="80">
		                 <p>${action.getText('desice.abilty1')}</p>
		                 <p>${action.getText('desice.abilty2')}</p>
		                 <p>${action.getText('desice.abilty3')}</p>
		                </td>
		                <#--
		                <td class="alignLeft"><=30</td>
		                -->
		                <#assign designGrade=''/>
		                <#assign designCommet=''/>
		                <#if designCapability?exists>
		                  <#assign designGrade = '${designCapability.grade?if_exists}'/>
		                  <#assign designCommet = '${designCapability.coment?if_exists}'/>
		                </#if>
		                <td class="alignLeft"><input type="text" name="designGrade" 
	    		           class="underline"  value="${designGrade}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <td class="alignLeft"><input type="text" name="designComment" 
	    		           class="underline"  value="${designCommet}"  maxlength="250" size="15"/>
	    		       </td>
    		           <#if designCapability?exists>
    		             <input type="hidden" name="designId" value="#{designCapability.id?if_exists}"/>
    		           </#if>
	    		    </tr>
	    		    </tr>
		            <tr>
		            <td class="alignCenter">2</td>
		            <td class="alignLeft">制作能力(交货期)</td>
		            <td class="alignLeft">20</td>
		            <td  class="alignLeft" width="300" height="80">
		                <p>${action.getText('zhizuo.abilty1')}</p>
		                <p>${action.getText('zhizuo.abilty2')}</p>
		                <p>${action.getText('zhizuo.abilty3')}</p>
		            </td>
		            <#--
		              <td class="alignLeft"><=20</td>
		              -->
		               <#assign makeGrade=''/>
		                <#assign makeComent=''/>
		                <#if makeCapability?exists>
		                  <#assign makeGrade = '${makeCapability.grade?if_exists}'/>
		                  <#assign makeComent = '${makeCapability.coment?if_exists}'/>
		                </#if>
		                <td class="alignLeft"><input type="text" name="makeGrade" 
	    		           class="underline"  value="${makeGrade}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <td class="alignLeft"><input type="text" name="makeComment" 
	    		           class="underline"  value="${makeComent}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <#if makeCapability?exists>
	    		          <input type="hidden" name="makeId" value="#{makeCapability.id?if_exists}"/>
	    		       </#if>
		            </tr>
		            <tr>
		            <td class="alignCenter">3</td>
		            <td class="alignLeft">质量保证能力 </td>
		            <td class="alignLeft">25</td>
		            <td  class="alignLeft" width="300" height="80">
		                <p>${action.getText('zhiliang.abilty1')}</p>
		                <p>${action.getText('zhiliang.abilty2')}</p>
		                <p>${action.getText('zhiliang.abilty3')}</p>
		            </td>
		            <#--
		              <td class="alignLeft"><=25</td>
		              -->
		               <#assign QaGrade=''/>
		                <#assign QaComent=''/>
		                <#if qaCapability?exists>
		                  <#assign QaGrade = '${qaCapability.grade?if_exists}'/>
		                  <#assign QaComent = '${qaCapability.coment?if_exists}'/>
		                </#if>
		                <td class="alignLeft"><input type="text" name="QaGrade" 
	    		           class="underline"  value="${QaGrade}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <td class="alignLeft"><input type="text" name="QaComent" 
	    		           class="underline"  value="${QaComent}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <#if qaCapability?exists>
	    		         <input type="hidden" name="qaId" value="#{qaCapability.id?if_exists}"/>
	    		       </#if>
		            </tr>
		            <tr>
		            <td class="alignCenter">4</td>
		            <td class="alignLeft">服务能力 </td>
		            <td class="alignLeft">15</td>
		            <td  class="alignLeft" width="300" height="80">
		               <p> ${action.getText('server.abilty1')}</p>
		               <p>${action.getText('server.abilty2')}</p>
		               <p>${action.getText('server.abilty3')}</p>
		            </td>
		            <#--
		              <td class="alignLeft"><=15</td>
		              -->
		               <#assign seviceGrade=''/>
		                <#assign seviceComent=''/>
		                <#if seviceCapability?exists>
		                  <#assign seviceGrade = '${seviceCapability.grade?if_exists}'/>
		                  <#assign seviceComent = '${seviceCapability.coment?if_exists}'/>
		                </#if>
		                <td class="alignLeft"><input type="text" name="seviceGrade" 
	    		           class="underline"  value="${seviceGrade}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <td class="alignLeft"><input type="text" name="seviceComent" 
	    		           class="underline"  value="${seviceComent}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <#if seviceCapability?exists>
	    		         <input type="hidden" name="seviceId" value="#{seviceCapability.id?if_exists}"/>
	    		       </#if>
		            </tr>
		            <tr>
		            <td class="alignCenter">5</td>
		            <td class="alignLeft">基本控制能力
		            </td>
		            <td class="alignLeft">10</td>
		            <td  class="alignLeft" width="300" height="80">
		             <p>${action.getText('base.abilty1')}</p>
		             <p>${action.getText('base.abilty2')}</p>
		            </td>
		            <#--
		              <td class="alignLeft"><=15</td>
		            -->
		              <#assign basicGrade=''/>
		                <#assign basicComent=''/>
		                <#if basicControlCapability?exists>
		                  <#assign basicGrade = '${basicControlCapability.grade?if_exists}'/>
		                  <#assign basicComent = '${basicControlCapability.coment?if_exists}'/>
		                </#if>
		                <td class="alignLeft"><input type="text" name="basicControlgrade" 
	    		           class="underline"  value="${basicGrade}"  maxlength="250" size="15"/>
	    		       </td>
	    		       <td class="alignLeft"><input type="text" name="basicControlComent" 
	    		           class="underline"  value="${basicComent}"  maxlength="250" size="15"/>
	    		       </td>
	    		        <#if basicControlCapability?exists>
	    		          <input type="hidden" name="basicId" value="#{basicControlCapability.id?if_exists}"/>
	    		        </#if>
		            </tr>
	      </@listTable> 
	      <#if !(action.isReadOnly())>
	         <@buttonBar>
	         	 <@vsubmit name="'save'" value="'${action.getText('save')}'" onclick="'return customize_validate();'">
	         	 </@vsubmit>
	         </@buttonBar>
	       </#if>
     </@ww.form>
  <script language="javascript">
    window.onload = function() {
      <#if supplierEvaluate.level?exists>
       parent.getObjByNameRe("level.id").value = '${supplierEvaluate.level.value?if_exists}';
     </#if>
       parent.getObjByNameRe("evaluateMinute").value = '${supplierEvaluate.evaluateMinute?if_exists}';
  }
      function customize_validate(){
     
        var designGrade = getObjByNameRe("designGrade").value; 
        if (''!=designGrade) {
          if (!isDoubleNumberBetweenBoolean(designGrade, 31, 0)) {
            alert("${action.getText('designGrade.and.bettwen')}");
           
            return false;
          }
        }
        if(getObjByNameRe("designComment").value!=''){
			if(!isValidLength(document.forms[0], "designComment", null, 250)){
				alert("${action.getText('designComment.length')}");
				return  false;
		    }
		}
        var makeGrade = getObjByNameRe("makeGrade").value; 
        if ('' != makeGrade) {
          if (!isDoubleNumberBetweenBoolean(makeGrade, 21, 0)) {
            alert("${action.getText('makeGrade.and.bettwen')}");
            return false;
          }
        }
        if(getObjByNameRe("makeComment").value!=''){
			if(!isValidLength(document.forms[0], "makeComment", null, 250)){
				alert("${action.getText('makeComment.length')}");
				return  false;
			   }
			}
			
       var QaGrade = getObjByNameRe("QaGrade").value; 
        if ('' != QaGrade) {
          if (!isDoubleNumberBetweenBoolean(QaGrade, 26, 0)) {
            alert("${action.getText('QaGrade.and.bettwen')}");
            return false;
          }
        }
         if(getObjByNameRe("QaComent").value!=''){
			if(!isValidLength(document.forms[0], "QaComent", null, 250)){
				alert("${action.getText('QaComent.length')}");
				return  false;
			   }
			}
        var seviceGrade = getObjByNameRe("seviceGrade").value; 
        if ('' != seviceGrade) {
          if (!isDoubleNumberBetweenBoolean(seviceGrade, 16, 0)) {
            alert("${action.getText('seviceGrade.and.bettwen')}");
            return false;
          }
        }
        if(getObjByNameRe("seviceComent").value!=''){
			if(!isValidLength(document.forms[0], "seviceComent", null, 250)){
				alert("${action.getText('seviceComent.length')}");
				return  false;
			   }
			}
        var basicControlgrade = getObjByNameRe("basicControlgrade").value; 
        if ('' != basicControlgrade) {
          if (!isDoubleNumberBetweenBoolean(basicControlgrade, 11, 0)) {
            alert("${action.getText('basicControlgrade.and.bettwen')}");
            return false;
          }
        }
         if(getObjByNameRe("basicControlComent").value!=''){
			if(!isValidLength(document.forms[0], "basicControlComent", null, 250)){
				alert("${action.getText('basicControlComent.length')}");
				return  false;
			   }
			}
        
        retrieveAllGradeAndComment("designId","designGrade","designComment","designCapabilityInfo");//获得供应商评估设计能力项的信息
        retrieveAllGradeAndComment("makeId","makeGrade","makeComment","makeCapabilityInfo");//获得供应商评估制作能力项的信息
        retrieveAllGradeAndComment("qaId","QaGrade","QaComent","qACapabilityInfo");//获得供应商评估质量保证项的信息
        retrieveAllGradeAndComment("seviceId","seviceGrade","seviceComent","seviceCapabilityInfo");//获得供应商评估服务能力项的信息
        retrieveAllGradeAndComment("basicId","basicControlgrade","basicControlComent","basicControlCapabilityInfo");//获得供应商评估基本控制能力项的信息
        return true;
      }
      
      function retrieveAllGradeAndComment(idName, gradeName, commentName,hiddenAllValueName) {
        var ary = new Array();
        var idValue = getObjByNameRe(idName).value;
        var gradeValue = getObjByNameRe(gradeName).value;
        var commentValue = getObjByNameRe(commentName).value;
        
        if (idValue != '') {
          ary.push("ID");
          ary.push(idValue);
        }
        if (gradeValue != '') {
          ary.push("grade");
          ary.push(gradeValue);
        }
        if (commentValue != '') {
          ary.push("comment");
          ary.push(commentValue);
        }
        getObjByNameRe(hiddenAllValueName).value = ary;
      }
      </script>
</@framePage>
