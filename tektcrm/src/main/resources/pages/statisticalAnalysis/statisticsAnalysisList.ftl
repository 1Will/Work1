

<#include "../includes/hco2011.ftl" />
<@htmlPage title="${action.getText('公司数据动态分布图')}">
  <style>
	#vltable #table	tr:nth-child(odd){background: #FFFFFF;}//奇数
	#vltable #table	tr:nth-child(even){background: #F5F5F5;}//偶数
  </style>
<form name="'listForm'" namespace="'/data'" action="listStatisticalAnalysis.html" method="'post'">
  
 <#include "./myDataStatisticsSearcher.ftl"> 
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="mainBar"  name="mainBar" style="width:1165px; height: 50% ;position:absolute"></div>
    <script language="javascript">
			// 基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById('mainBar'));
		option = {
		    title: {
		        text: ''
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:[
		               '合同金额','收款金额','开票金额',
		            ]
		    },
		    toolbox: {
		        show : true,
		        feature: {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType: {show: true, type: ['bar','line']},
		            restore : {show: true},
		            saveAsImage: {show: true}
		        }
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '15%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            data : []
				}
		    ],
		    yAxis : [
		        {    
		             name : '金额(万)',
		             type : 'value'
		          }
		    ],
		     dataZoom: [  
			            {  
			                show: true,//dataZoom，框选区域缩放，自动与存在的dataZoom控件同步，分别是启用，缩放后退  
			                start: 0,
			                end: 20,
			                startValue:10 
			            }  
			        ],   
		    series : [
		        {
		            name:'合同金额',
		            type : 'bar',
		            barWidth : 30,
		            itemStyle : { normal: {label : {show: true, position: 'top'}}},
		            data:[]
		        },
		        {
		            name:'收款金额',
		            type : 'bar',
		            barWidth : 30,
		            itemStyle : { normal: {label : {show: true,position: 'top',distance:'30'}}},
		            data:[]
		        },
		        {
		            name:'开票金额',
		            type: 'bar',
		            barWidth : 30,
		            itemStyle : { normal: {label : {show: true, position: 'top'}}},
		            data:[]
		        }
		    ]
		};
	myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
    myChart.setOption(option);   //参数设置方法  
  var dates=[];    //日期（实际用来盛放X轴坐标值）
  var nums1=[];    //日报数量数组（实际用来盛放Y坐标值）
  var nums2=[];     //日报回复数数量数组（实际用来盛放Y坐标值）
  var nums3=[];     // 新增客户数量数组（实际用来盛放Y坐标值）  
  var flag="";
  var startTime="";
  var endTime="";
  var  businessType ="";
  var  classification="";
  var num1=encodeURI(encodeURI('合同数'));
  var mon1=encodeURI(encodeURI('合同金额'));
  var num2=encodeURI(encodeURI('收款笔'));
  var mon2=encodeURI(encodeURI('收款金额'));
  var num3=encodeURI(encodeURI('开票记录数'));
  var mon3=encodeURI(encodeURI('开票金额'));
  var _result="";
  monthDwr();
     function monthDwr() {
		     businessType= getObjByName("businessType.id").value;
		     classification= getObjByName("classification.id").value;
		     flag=getObjByName("data.state").value;
	        if (flag == "month"){
		        getObjByName("data.ciemdinghTime_start").parentNode.style.display = "";
	            getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "none";
		          startTime=getObjByName("data.ciemdinghTime_start").value.replace(/(\d{4})\-(\d{2})/,"$1年$2月");
		          endTime=getObjByName("data.ciemdinghTime_end").value.replace(/(\d{4})\-(\d{2})/,"$1年$2月");
		      }else if(flag == "year"){
		          getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "";
	              getObjByName("data.ciemdinghTime_start").parentNode.style.display = "none";
	              startTime= getObjByName("data.ciemdinghTime1_start").value.replace(/(\d{4})/,"$1年");
		          endTime= getObjByName("data.ciemdinghTime1_end").value.replace(/(\d{4})/,"$1年");
		      }else{
		        getObjByName("data.ciemdinghTime_start").parentNode.style.display = "";
	            getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "none";
		            startTime=getObjByName("data.ciemdinghTime_start").value.replace(/(\d{4})\-(\d{2})/,"$1年$2月");
		            endTime=getObjByName("data.ciemdinghTime_end").value.replace(/(\d{4})\-(\d{2})/,"$1年$2月");
		      }
		    DWREngine.setAsync(false); 
	 		myData.loadAllMyDataByContion(startTime,endTime,businessType,classification,flag,dataStatistic);
	 		DWREngine.setAsync(true);
          return _result;
		}
		
	<#--function findBydatas(){
	dates=[];
	nums1=[];
	nums2=[];
	nums3=[];
		 businessType= getObjByName("businessType.id").value;
		 classification= getObjByName("classification.id").value;
		flag=getObjByName("data.state").value;
		if(flag =="month"){
		     startTime= getObjByName("data.ciemdinghTime_start").value;
		     endTime= getObjByName("data.ciemdinghTime_end").value;
		       
		}else if(flag =="year"){
		     startTime= getObjByName("data.ciemdinghTime1_start").value;
		     endTime= getObjByName("data.ciemdinghTime1_end").value;
		}else{
		    startTime= getObjByName("data.ciemdinghTime_start").value;
		    endTime= getObjByName("data.ciemdinghTime_end").value;
		}
		DWREngine.setAsync(false); 
 		myData.loadAllMyDataByContion(startTime,endTime,businessType,classification,flag,dataStatistic);
 		DWREngine.setAsync(true);
 		return _result;
	}-->
	
 	 function dataStatistic(result) {
            //请求成功时执行该函数内容，result即为服务器返回的json数据
              var jsonData = JSON.parse(result);
              _result=jsonData;
		      if(jsonData==""){
	      			getObjByName("valueList").style.display = "none";
	      			getObjByName("mainBar").style.display = "none";
		      }
	    
              //echarts展示图（判断x轴year还是month显示）
               if(flag=="month"){
		         	 for(var i=0;i<jsonData.length;i++){
	                      dates.push(jsonData[i].month);    //挨个取出类别并填入类别数组
	                      }
	               }else if(flag=="year"){
	                 getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "";
                     getObjByName("data.ciemdinghTime_start").parentNode.style.display = "none";
            
		         	 for(var i=0;i<jsonData.length;i++){
	                      dates.push(jsonData[i].year);    //挨个取出类别并填入类别数组
	                      }
	               }else {
	                     getObjByName("data.ciemdinghTime_start").parentNode.style.display = "";
	                     getObjByName("data.ciemdinghTime1_start").parentNode.style.display = "none";
		     
	                    dates.push(startTime+"--"+endTime);
                   }
        	  for(var i=0;i<jsonData.length;i++){ 
                        nums1.push(jsonData[i].contractManagementMoney/10000);
                        nums2.push(jsonData[i].financialManagementMoney/10000);
                        nums3.push(jsonData[i].billingRecordMoney/10000);
              }
	               myChart.hideLoading();    //隐藏加载动画
                    myChart.setOption({        //加载数据图表
                        xAxis: {
                            name: '日期',
                            data:dates
                        },
                         series: [{
                            // 根据名字对应到相应的系列
                             data: nums1
                              },
                              {
                              data:nums2
                               },
                              {
                              data:nums3
                               } 
                              ]
                    });
	   }
	  
    //js处理表数据点击事件
    //月份点击
    function  listContractManagementNum(months){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=month;name="+num1+";months="+encodeURI(encodeURI(months))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listContractManagementMoney(months){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=month;name="+mon1+";months="+encodeURI(encodeURI(months))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listFinancialManagementNum(months){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=month;name="+num2+";months="+encodeURI(encodeURI(months))+";businessType="+businessType+";classification="+classification+"; ";
	   openNewWindow(url);
    }
     function  listFinancialManagementMoney(months){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=month;name="+mon2+";months="+encodeURI(encodeURI(months))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listBillingRecordNum(months){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=month;name="+num3+";months="+encodeURI(encodeURI(months))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listBillingRecordMoney(months){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=month;name="+mon3+";months="+encodeURI(encodeURI(months))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
    //年份点击
    function  listYContractManagementNum(years){
   
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=year;name="+num1+";months="+encodeURI(encodeURI(years))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listYContractManagementMoney(years){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=year;name="+mon1+";months="+encodeURI(encodeURI(years))+";businessType="+businessType+";classification="+classification+"; ";
	   openNewWindow(url);
    }
     function  listYFinancialManagementNum(years){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=year;name="+num2+";months="+encodeURI(encodeURI(years))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listYFinancialManagementMoney(years){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=year;name="+mon2+";months="+encodeURI(encodeURI(years))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listYBillingRecordNum(years){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=year;name="+num3+";months="+encodeURI(encodeURI(years))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listYBillingRecordMoney(years){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=year;name="+mon3+";months="+encodeURI(encodeURI(years))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     //总和点击
    function  listSumContractManagementNum(startTime,endTime){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=randomTime;name="+num1+";startTime="+encodeURI(encodeURI(startTime))+";endTime="+encodeURI(encodeURI(endTime))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listSumContractManagementMoney(startTime,endTime){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=randomTime;name="+mon1+";startTime="+encodeURI(encodeURI(startTime))+";endTime="+encodeURI(encodeURI(endTime))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listSumFinancialManagementNum(startTime,endTime){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=randomTime;name="+num2+";startTime="+encodeURI(encodeURI(startTime))+";endTime="+encodeURI(encodeURI(endTime))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listSumFinancialManagementMoney(startTime,endTime){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=randomTime;name="+mon2+";startTime="+encodeURI(encodeURI(startTime))+";endTime="+encodeURI(encodeURI(endTime))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listSumBillingRecordNum(startTime,endTime){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=randomTime;name="+num3+";startTime="+encodeURI(encodeURI(startTime))+";endTime="+encodeURI(encodeURI(endTime))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
     function  listSumBillingRecordMoney(startTime,endTime){
       var url = "${req.contextPath}/workspace/data/listStatisticalBusinessType.html?dataParame="+"times=randomTime;name="+mon3+";startTime="+encodeURI(encodeURI(startTime))+";endTime="+encodeURI(encodeURI(endTime))+";businessType="+businessType+";classification="+classification+";";
	   openNewWindow(url);
    }
 </script>



<table class="wwFormTable" cellspacing="1" id="table" systle="position:relative">
   
		 <tr class="input">
			<td>
				<input value="查询" class="button" type="submit" >
				<input id="hidden" type="hidden">
			</td>
			<#--><td>
				<input value="查询业务属性" class="button" type="submit" onclick="findByBusiness()" >
				<input id="hidden" type="hidden">
			</td>-->
		 </tr>
		<tr class="input">
	         <td class="title">
	                        公司分析列表
	         </td>
	   </tr>
	   <tr class="input">
	         <td>
	          <table id="vltable" class="defaultLook" name="valueList" width="100%">
			    <thead>
					<tr>
			        	<th nowrap="true" >日期</th>
			        	<th nowrap="true">合同数 </th>
			        	<th nowrap="true">合同金额 </th>
			        	<th nowrap="true"> 收款（笔）</th>
			        	<th nowrap="true">收款金额</th>
			        	<th nowrap="true"> 开票记录数</th>
			        	<th nowrap="true">开票金额 </th>
			      	</tr>
			    </thead> 
			    <tbody id="table">
			     <script language="javascript">
			     
			        var jsonData= _result;
				     var html="";
				     for(var i=0;i<jsonData.length;i++){
				      html += "<tr class='example defaultLook1'>";
					         if(flag=="month"){
				            html+="<td style=\"text-align:left;word-wrap:break-word; word-break:break-all; overflow:auto;\">"+jsonData[i].month+"</td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listContractManagementNum('"+jsonData[i].month+"')\">"+jsonData[i].contractManagementNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listContractManagementMoney('"+jsonData[i].month+"')\">"+jsonData[i].contractManagementMoney+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listFinancialManagementNum('"+jsonData[i].month+"')\">"+jsonData[i].financialManagementNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listFinancialManagementMoney('"+jsonData[i].month+"')\">"+jsonData[i].financialManagementMoney+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listBillingRecordNum('"+jsonData[i].month+"')\">"+jsonData[i].billingRecordNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listBillingRecordMoney('"+jsonData[i].month+"')\">"+jsonData[i].billingRecordMoney+"</a></td>";
			              }else if(flag=="year"){
				            html+="<td style='text-align:left;word-wrap:break-word; word-break:break-all; overflow:auto;'>"+jsonData[i].year+"</td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listYContractManagementNum('"+jsonData[i].year+"')\">"+jsonData[i].contractManagementNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listYContractManagementMoney('"+jsonData[i].year+"')\">"+jsonData[i].contractManagementMoney+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listYFinancialManagementNum('"+jsonData[i].year+"')\">"+jsonData[i].financialManagementNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listYFinancialManagementMoney('"+jsonData[i].year+"')\">"+jsonData[i].financialManagementMoney+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listYBillingRecordNum('"+jsonData[i].year+"')\">"+jsonData[i].billingRecordNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listYBillingRecordMoney('"+jsonData[i].year+"')\">"+jsonData[i].billingRecordMoney+"</a></td>";
			            }else{
				            if(startTime=="" && endTime==""){
				            	html+="<td style='text-align:left;word-wrap:break-word; word-break:break-all; overflow:auto;'>统计所有</td>";
				            }else{
				            	html+="<td style='text-align:left;word-wrap:break-word; word-break:break-all; overflow:auto;'>"+startTime+"--"+endTime+"</td>";
				            }
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listSumContractManagementNum(startTime,endTime)\">"+jsonData[i].contractManagementNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listSumContractManagementMoney(startTime,endTime)\">"+jsonData[i].contractManagementMoney+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listSumFinancialManagementNum(startTime,endTime)\">"+jsonData[i].financialManagementNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listSumFinancialManagementMoney(startTime,endTime)\">"+jsonData[i].financialManagementMoney+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listSumBillingRecordNum(startTime,endTime)\">"+jsonData[i].billingRecordNum+"</a></td>";
				            html +="<td style=\"text-align:right;word-wrap:break-word; word-break:break-all; overflow:auto;\"><a href=\"javascript:listSumBillingRecordMoney(startTime,endTime)\">"+jsonData[i].billingRecordMoney+"</a></td>";
			            }
				      html+="</tr>";
				      }
				    document.write(html);
				   
			     </script>
				 </tbody>
	        </table>      
	      </td>
	   </tr>

</table>



 <#--
   <div id="business" style="width: 1165px;height:800px;"></div>
   <script type='text/javascript' src='${req.contextPath}/dwr/interface/myDataBusiness.js'></script>
   <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChartOne = echarts.init(document.getElementById('business'));
		 myChartOneOption = {
					tooltip : {
					    trigger: 'item',
					    formatter: "{a} <br/>{b} : {c}元"
					    },
					     toolbox: {
					        show : true,
					        feature : {
					            mark : {show: true},
					            dataView : {show: true, readOnly: false},
					            magicType : {
					                show: true,
					                type: ['pie']
					            },
					            restore : {show: true},
					            saveAsImage : {show: true}
					        }
					    },
					    legend: {
					        orient: 'vertical',
					        left: 'left',
					        data: []
					    },
					    series : [
					        {
					            name: '业务属性',
					            type: 'pie',
					            radius : '35%',
					            center: ['50%', '30%'],
					            data:[],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        },
					         {
					            name: '军品',
					            type: 'pie',
					            radius : '35%',
					            center: ['25%', '70%'],
					            data:[],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        },
					         {
					            name: '民品',
					            type: 'pie',
					            radius : '35%',
					            center: ['70%', '70%'],
					            data:[],
					            itemStyle: {
					                emphasis: {
					                    shadowBlur: 10,
					                    shadowOffsetX: 0,
					                    shadowColor: 'rgba(0, 0, 0, 0.5)'
					                }
					            }
					        }
					    ]
					};

                     var names1 = [];  //类别数组（用于存放饼图的类别）
                     var names2 = [];
                     var names3 = [];  
                     var brower1 = [];
                     var brower2 = [];
                     var brower3 = [];
                  function  findByBusiness(){
                   		var date=new Date();
                   		var year=date.getFullYear();
                   		var startTime=year+"年";
                   		DWREngine.setAsync(false);
				 		myDataBusiness.loadAllMyDataByBusinessType("2017年","",'year', dataBusinessType);
				 		DWREngine.setAsync(true);
                   } 
                     function dataBusinessType(data) {
                     var brower1 = [];
                     var brower2 = [];
                     var brower3 = [];
			            //请求成功时执行该函数内容，result即为服务器返回的json对象
			             var json= JSON.parse(data);
			             for(var i=0;i<json.length;i++){
			             	if(i == 0){
			             		for(var j=0;j<json[i].length;j++){
				             		names1.push(json[i][j][1]);
					                brower1.push({
					                    name: json[i][j][1],
					                    value: json[i][j][0]});
				             	}
				             }
				             if(i >0 && i<4){
			             		for(var j=0;j<json[i].length;j++){
				             		names2.push(json[i][j][1]);
					                brower2.push({
					                    name: json[i][j][1],
					                    value: json[i][j][0]});
				             	}
				             }
				             if(i >3 && i<=6){
			             		for(var j=0;j<json[i].length;j++){
				             		names3.push(json[i][j][1]);
					                brower3.push({
					                    name: json[i][j][1],
					                    value: json[i][j][0]});
				             	}
			             	}
			             }
			             myChartOne.hideLoading();    //隐藏加载动画
			             myChartOne.setOption({        //加载数据图表                
				                legend: {                    
				                    data: names1
				                },
				                series: [{                    
				                    data: brower1
				                },
				                {                    
				                    data: brower2
				                },
				                {                    
				                    data: brower3
				                }]
				            });
                    }
        // 使用刚指定的配置项和数据显示图表。
        myChartOne.setOption(myChartOneOption);
    </script>-->

  <form>
</@htmlPage>
<script type="text/javascript">
window.onload=function(){
    var offset=document.getElementById("table").offsetHeight;
    document.getElementById("mainBar").style.top=offset+50+60+"px";
   }
 </script>
