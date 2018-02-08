	<!--开始日期-->
	<@pp.datePicker 
			label="'${action.getText('开始时间')}'"
			name="'newTask.startTime'"
			value="'${(newTask.startTime?string('yyyy-MM-dd'))?if_exists}'" 
			cssClass="'underline'" 
			required="true"
			readonly="true"
			dateFormat="'%Y-%m-%d'"
			maxlength="10">
	</@pp.datePicker>
			
   <td>
		   <select name="startHour">
			   <option value="07">07</option>
			   <option value="08">08</option>
			   <option value="09">09</option>
			   <option value="10">10</option>
			   <option value="11">11</option>
			   <option value="12">12</option>
			   <option value="13">13</option>
			   <option value="14">14</option>
			   <option value="15">15</option>
			   <option value="16">16</option>
			   <option value="17">17</option>
			   <option value="18">18</option>
			   <option value="19">19</option>
			   <option value="20">20</option>
		   </select>:
		   <select name="startMinute">
			   <option value="00">00</option>
			   <option value="05">05</option>
			   <option value="10">10</option>
			   <option value="15">15</option>
			   <option value="20">20</option>
			   <option value="25">25</option>
		       <option value="30">30</option>
			   <option value="35">35</option>
			   <option value="40">40</option>
			   <option value="45">45</option>
			   <option value="50">50</option>
			   <option value="55">55</option>
		   </select>
		   </td>
		   
		   	<!--到期日期-->
			<@pp.datePicker
					label="'${action.getText('结束时间')}'"
					name="'newTask.endTime'"
					value="'${(newTask.endTime?string('yyyy-MM-dd'))?if_exists}'" 
					cssClass="'underline'" 
					required="true"
					readonly="true"
					dateFormat="'%Y-%m-%d'"
					maxlength="10">
			</@pp.datePicker>
		<td>
		   <select name="endHour">
			   <option value="07">07</option>
			   <option value="08">08</option>
			   <option value="09">09</option>
			   <option value="10">10</option>
			   <option value="11">11</option>
			   <option value="12">12</option>
			   <option value="13">13</option>
			   <option value="14">14</option>
			   <option value="15">15</option>
			   <option value="16">16</option>
			   <option value="17">17</option>
			   <option value="18">18</option>
			   <option value="19">19</option>
			   <option value="20">20</option>
		   </select>:
		   <select name="endMinute">
			   <option value="00">00</option>
			   <option value="05">05</option>
			   <option value="10">10</option>
			   <option value="15">15</option>
			   <option value="20">20</option>
			   <option value="25">25</option>
		       <option value="30">30</option>
			   <option value="35">35</option>
			   <option value="40">40</option>
			   <option value="45">45</option>
			   <option value="50">50</option>
			   <option value="55">55</option>
		   </select>
		   </td>
	<#if newTask.id?exists>
		<script>
		var start = '${(newTask.startTime?string('kk:mm'))?if_exists}';
		var end = '${(newTask.endTime?string('kk:mm'))?if_exists}';
		if(start != ''){
			var time = start.split(":");
			getObjByName("startHour").value = time[0];
			getObjByName("startMinute").value =  time[1]
		}
		if(end != ''){
			var time = end.split(":");
			getObjByName("endHour").value = time[0];
			getObjByName("endMinute").value =  time[1]
		}
		</script>
	</#if>