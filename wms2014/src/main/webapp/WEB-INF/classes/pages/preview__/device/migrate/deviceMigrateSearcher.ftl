<@inputTable>
		<tr>
			<@ww.textfield label="'转移编号'" name="'discardNo'" value="'" cssClass="'underline'" />
	 		<@ww.textfield label="'转移名称'" name="'name'" value="''" cssClass="'underline'"/>
		</tr>
		<tr>
	        <@pp.dateRanger label="'转移日期'" name="'applyDatetime'" 
		       value="''"
		       cssClass="'underline'" dateFormat="date"/> 
	        <@ww.checkbox label="'仅查询失效'" name="'includeDisabled'" value="'false'" fieldValue="'value'" onclick="'changeDisabledStatus();'"/>
	    </tr> 
	    <script language="javascript">
	           function changeDisabledStatus() {
	             if (document.getElementById("includeDisabled").checked) {
	               document.getElementById("delete").value="有效";
	             } else {
	               document.getElementById("delete").value="失效";
	             }
	           }
	    </script>  
</@inputTable>