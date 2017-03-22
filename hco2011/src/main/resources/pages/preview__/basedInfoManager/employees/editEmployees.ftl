<#include "../../../includes/macros.ftl" />
<@htmlPage title="员工信息维护">
<@ww.form name="'listForm'" action="'editEmployees_'" method="'post'">
	<@ww.token name="editEmployeesToken"/>
	<@inputTable>
		<tr>
			<@ww.textfield label="'工号'" name="'code'" value="'YJ-YF-001'" cssClass="'underline'" required="true"/>
			<@ww.textfield label="'姓名'" name="'name'" value="'李飞鸿'" cssClass="'underline'" required="true"/>
			<@ww.select 
    		label="'性别'"
			required="true"
			name="'province'" 
			value="${req.getParameter('province')?if_exists}" 
			headerKey="1"
			headerValue="selectedType"
		    list="{
				'男',
				'女'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
		</tr>
		<tr>
		<@ww.select 
    		label="'公司'"
			required="false"
			name="'inst'" 
			value="selectedInst" 
			headerKey="1"
			headerValue="selectedInst"
		    list="{
				'永君(中国)有限公司合肥分公司',
				'永君(中国)有限公司南京分公司',
				'永君(中国)有限公司上海分公司',
				'永君(中国)有限公司大连分公司'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	
	    	<@ww.select 
    		label="'部门'"
			required="false"
			name="'dept'" 
			value="selectedDept" 
			headerKey="1"
			headerValue="selectedDept"
		    list="{
				'软件研发部',
				'网络事业部',
				'系统集成部',
				'电子安防部'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	<@ww.select 
    		label="'职务'"
			required="false"
			name="'duty'" 
			value="selectedDuty" 
			headerKey="1"
			headerValue="selectedDuty"
		    list="{
				'部门经理',
				'项目组组长',
				'配置组组长',
				'ORACLE DBA',
				'程序员'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
		</tr>
		<tr>
			<@ww.textfield label="'电话'" name="'name'" value="'05512341148'" cssClass="'underline'"/>
			<@ww.textfield label="'手机'" name="'name'" value="'13865809273'" cssClass="'underline'"/>
			<@ww.select 
    		label="'是否系统用户'"
			required="false"
			name="'duty'" 
			value="selectedDuty" 
			headerKey="1"
			headerValue="selectedDuty"
		    list="{
				'是',
				'否'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
	    	<@ww.select 
    		label="'是否销售人员'"
			required="false"
			name="'duty'" 
			value="selectedDuty" 
			headerKey="1"
			headerValue="selectedDuty"
		    list="{
				'是',
				'否'
				}"
	    	emptyOption="false" 
	    	disabled="false"/>
		</tr>
		<tr>
	       <td colspan="8">
	        		<HR align="middle" size="1" width="95%" color="#999999" noshade>
	       </td>
	    </tr>
	    <tr>
		  <td align="right" valign="top">
        	<label class="label">
        		<font color=red>*</font>毕业院校:
        	</label>
		  </td>
		        <td colspan="8">
		        	<input type="text" name="college" value = "安徽农业大学" size="45" class="underline">
		        </td>
	    </tr>
		<tr>
        	<td align="right" valign="top">
        		<label class="label">
        			个人简历:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="jianlicomment" rows="4" cols="95" >熟练Java以及数据库 MySql、SQLServer2000、ORACLE 10g;
熟练基于Servlet JSP 的web编程及数据库编程：SQL，HQL,JDBC；
熟练应用 Tomcat应用服务器,了解Jboss应用服务器；
熟练 Struts、Spring、Hibernate、WebWork主流框架技术；
熟悉 安全认证框架acegi、以及valuelist分页框架技术；
熟悉版本控制工具:svn 熟悉测试工具：Junit；
开发工具：熟练Eclipse；
使用log4j控制日志输出级别，提高代码调试时输出的效率。
	        	</textarea>
	        </td>
	    </tr>
	    <tr>
        	<td align="right" valign="top">
        		<label class="label">
        			项目/获奖情况:
        		</label>
        	</td>
	        <td colspan="10">
	        	<textarea name="huojiangcomment" rows="4" cols="95" >
参加过Linux系统的设备管理系统的开发；
参加过江淮工装设备管理系统的开发（EAM）；
参加过江淮快速信息反馈系统的开发（SMS）；
	        	</textarea>
	        </td>
	    </tr> 
	</@inputTable>
	<@buttonBar>
		<@vsubmit name="'save'" value="'${'保存'}'"/>
		<@redirectButton value="${'返回'}" url="${req.contextPath}/employees/listEmployees_.html"/>
    </@buttonBar>
</@ww.form>
</@htmlPage>