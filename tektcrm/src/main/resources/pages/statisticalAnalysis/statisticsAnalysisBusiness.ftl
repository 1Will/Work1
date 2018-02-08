<#include "../includes/hco2011.ftl" />

<@htmlPage title="${action.getText('部门数据动态分布图')}">
<script type="text/javascript" src="${req.contextPath}/javascripts/json2.js"></script>
<script type="text/javascript" src="${req.contextPath}/javascripts/echarts.min.js" ></script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="mainBar" style="width:1000px; height: 50%"></div>
<!-- ECharts单文件引入 -->
    <script language="javascript">
                var nums = [];    //类别数组（实际用来盛放Y轴坐标值）
                var depts = [];    //销量数组（实际用来盛放X坐标值）
                var date=[];      //日期数组
                var names=[];     //查询的业务名称数组
                var sT=[];        //开始时间
                var eD=[];        //结束时间
                
 	  window.onload=function() {
            //请求成功时执行该函数内容，result即为服务器返回的json数据
              var result ='${result}';
              var jsonResult = JSON.parse(result);
               if (jsonResult == null) {
                return false;  
              } else{
                    //echarts展示图（判断x轴部门显示）
	         	for(var i=0;i<jsonResult.length;i++){
	         			for(var j=0;j<jsonResult[i].length;j++){
	         			if(jsonResult[i][j][0].indexOf("年") != -1){
	         			    date.push(jsonResult[i][j][0]);
	         				nums.push(jsonResult[i][j][1]);
			                depts.push(jsonResult[i][j][2]);
			                names.push(jsonResult[i][j][3]);
	         			}else{
	         			    nums.push(jsonResult[i][j][1]);
	         				depts.push(jsonResult[i][j][0]);
	         				names.push(jsonResult[i][j][2]);
	         			    date.push(jsonResult[i][j][3]+'--'+jsonResult[i][j][4]);
	         			  
	         			}
	         		}
	         	}
	         	  
                    myChart.setOption({        //加载数据图表
                      
	                    title:{  
						       text:date[encodeURI(encodeURI(0))],  
						       subtext:names[0]
						     },  
                        xAxis: {
                            name: '部门',
                            data:depts
                        },
                         series: [{
			                    data: nums
			                    }]
                    });
              }
			   
	}
			// 基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById('mainBar'));
		option = {
		  title:{  
		       text:[],  
		       subtext:[] ,
		       left:'center'
		     },  
		    tooltip : {
		        trigger: 'axis'
		    },
		    toolbox: {
		        show : true,
		        feature: {
		            mark : {show: true},
		            magicType: {show: true, type: ['bar']},
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
		            data : [],
		             axisLabel:{  
                         interval:0,//横轴信息全部显示  
                         rotate:-30 //-30度角倾斜显示  
                    }  
				}
		    ],
		    yAxis : [
		        {
		             type : 'value'
		        }
		    ],
		     
		    series : [
		        {
		            type:'bar',
		            barWidth : 30,
		            itemStyle : { normal: {label : {show: true, position: 'top'}}},
		            data:[]
		        }
		      
		    ]
		};

    myChart.setOption(option);   //参数设置方法  
  
 </script>

</@htmlPage>

