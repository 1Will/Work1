<#--$Id: extInfoList.ftl 6197 2007-08-08 11:21:08Z zbzhang $ -->
      <#include "../../includes/eam2008.ftl"/>
      
	 	<@inputTable>
	 	  	   <tr>
		 	  	<@ww.textfield label="'${action.getText('toolingunsealed.unsealedBillNo')}'" name="'alteration.unSealBillNo'" value="'${alteration.unSealBillNo?if_exists}'" cssClass="'underline'" disabled="true" />
		 	  	<@ww.textfield label="'${action.getText('toolingunsealed.unsealedBillName')}'" name="'alteration.unSealBillName'" value="'${alteration.unSealBillName?if_exists}'" cssClass="'underline'" required="true" />
		 	  </tr>
		 	  <tr>
		 	    <td align="right" valign="top"><span class="required">*</span><label class="label">${action.getText('toolingunsealed.unsealedPeopleProfile')}:</label></td>
		 	    <td>
		 			<input type="text" name="alteration.unsealedPeopleDisplay" class="underline"  value="${alteration.unSealPeople?if_exists}"  maxlength="150" disabled/>
		 			<@ww.hidden name="'alteration.unSealPeople'" value="'${alteration.unSealPeople?if_exists}'"/>
		 			<a onClick='user_OpenDialog()'>
	 					<img src="${req.contextPath}/images/icon/files.gif" align=absMiddle border=0/>
	 				</a>
		 		</td>		 
		 	  	<@pp.datePicker label="'${action.getText('toolingunsealed.unsealedDateTmProfile')}'" name="'alteration.unSealDateTm'"
	     				value="'${(alteration.unSealDateTm?string('yyyy-MM-dd'))?if_exists}'" cssClass="'underline'" size="15" required="true"/>
		 	  </tr>
		 	  	     	<tr>
	     	    <@ww.textarea  label="'${action.getText('toolingunsealed.unsealedComment')}'" 
	        	         name="'alteration.unSealComment'" 
	        	         value="'${alteration.unSealComment?if_exists}'"  
	        	         rows="3" cols="50" cssClass="'underline'"  />
		 	</tr>
		 	<#if  alteration.id?exists>
	     	<tr><td colspan="4"><HR align="middle" size="1" width="95%" color="#999999" noshade></td></tr>
	     	<tr>
		 	  	<td align="right" valign="top"><span class="required"></span><label class="label">${action.getText('toolingunsealed.unsealedStatus')}:</label></td>
            	<td>
		 			<input type="checkbox" name="faultStateFlag" value="TOOLING_NORMAL" onclick="changeFaultState()"/>${action.getText('toolingunsealed.audit')}
		 		</td>
		 	 </tr>
		  </#if>
	 	</@inputTable>

