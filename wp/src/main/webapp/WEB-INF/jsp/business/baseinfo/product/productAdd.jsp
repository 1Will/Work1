<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/business/baseinfo/product/productAdd.js"></script>
	<!--右边窗口begin-->
	<div region="center" border="false" style="padding: 1px;">
		<!--基础信息begin-->
		<form id="pro_form" method="get">
		<input type="hidden" name="id" id="id"
											value="${product.id}" />
										<input type="hidden" name="effectflag" id="effectflag"
											value="${product.effectflag}" />
										<input type="hidden" name="version" id="version"
											value="${product.version}" />
											
											<input type="hidden" name="isnomianvalue" id="isnomianvalue"
											value="${product.isnomain}" />
			<table width="100%" border="0" cellspacing="0" cellpadding="5"
				class="gp_maintable">
				<tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">产品编码</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="code" id="code" value="${product.code}" ></div>
					</td>
					<td width="15%">
						<div align="right">产品名称</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="name" id="name" value="${product.name}" ></div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">型号</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="model" id="model" value="${product.model}" ></div>
					</td>
					<td width="15%">
						<div align="right">规格</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="standard" id="standard" value="${product.standard}" ></div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">成本价</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="etcprice" id="etcprice" value="${product.etcprice}" ></div>
					</td>
					<td width="15%">
						<div align="right">销售价</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="saleprice" id="saleprice" value="${product.saleprice}" ></div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">产品类型</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="ptId" id="ptId" value="${product.ptId}" ></div>
					</td>
					<td width="15%">
						<div align="right">产品来源</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="productSourceId" id="productSourceId" value="${product.productSourceId}"  ></div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				<tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">供应商</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="supplierId" id="supplierId" value="${product.supplierId}" ></div>
					</td>
					<td width="15%">
						<div align="right">主营</div>
					</td>
					<td width="32%">
						<div align="left">
						<input type="radio" name="isnomain" id="isnomain1"  value="1"
						/>是
						<input type="radio" name="isnomain" id="isnomain2"  value="0"
						 />否
						
						</div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">销售额度（%）</div>
					</td>
					<td width="32%">
						<div align="left"><input type="text" name="salelimit" id="salelimit" value="${product.salelimit}" ></div>
					</td>
					<td width="15%">
						<div align="right">推出时间</div>
					</td>
					<td width="32%">
						<div align="left"><input class="easyui-datetimebox"  type="text" name="launch" id="launch" value="${product.launch}" ></div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				
				
				<tr>
					<td width="2%">&nbsp;</td>
					<td width="15%">
						<div align="right">备注</div>
					</td>
					<td colspan="3">
						<div align="left"><textarea
								class="KindEditor" style="width:80%; height: 200px;" name="remark" id="remark">${product.remark}</textarea></div>
					</td>
					<td width="2%">&nbsp;</td>
				</tr>
				
				
				
			</table>
		</form>
		<!--基础信息end-->
	</div>
	<!--右边窗口begin-->
	<!--窗口底部begin-->
	<div region="south" border="false"
		style="text-align: right; height: 30px; padding: 2px; border-top: 1px solid #ccc;">
		<a class="easyui-linkbutton" id="addmenu_but" iconCls="icon-save"
			href="javascript:void(1)" onclick="saveOrUpdate()">保存</a>
	</div>
