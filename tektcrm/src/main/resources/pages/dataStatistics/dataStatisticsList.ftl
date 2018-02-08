<#include "../includes/hco2011.ftl" />
<#-- 两个echarts插件 -->
<script type="text/javascript" src="${req.contextPath}/javascripts/json2.js"></script>
<script type="text/javascript" src="${req.contextPath}/javascripts/echarts.js"></script>
<script type="text/javascript" src="${req.contextPath}/javascripts/vintage.js"></script>
<@htmlPage title="${action.getText('天鹅数据动态分布图')}">
<script type='text/javascript' src='${req.contextPath}/dwr/interface/DailyStatistic.js'></script>
<#-- 两个echarts插件 -->
<script type="text/javascript" src="${req.contextPath}/javascripts/json2.js"></script>
<script type="text/javascript" src="${req.contextPath}/javascripts/echarts.js"></script>
<script type="text/javascript" src="${req.contextPath}/javascripts/vintage.js"></script>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width:100%; height: 80%"></div>
<button onclick="getMonth()" >月</button>
<button onclick="getYear()">日</button>
<!-- ECharts单文件引入 -->
<script type="text/javascript">
// 基于准备好的dom，初始化echarts图表
var myChart = echarts.init(document.getElementById('main'));
option = {
    title: {
        text: ''
    },
    tooltip : {
        trigger: 'axis',
       
    },
    legend: {
    	 x: 'left',
        data:['日报数','日报回复数','新增客户','新增联系人','回访次数','回访回复数','新建项目数',
              '项目计划数','项目计划改变数','项目计划完成数','合同数量','合同金额','合同计划数量',
              '合同计划改变数','合同计划完成数','新建收款单数量','收款金额','新建开票数量','开票金额',
              '出差数量','请假数量','加班数量'],
         orient: 'vertical',//数值显示
         width: 150,
         selectedMode: 'single',//每次显示一条
    },
    toolbox: {//右上角显示展示图标
        feature: {
            dataView: {readOnly: false},
            magicType: {type: ['line','bar','tiled']},
            restore: {},
            saveAsImage: {}
        }
    },
    calculable : true,//固定位子
    grid: {
        left: '12%',
        right: '4%',
        bottom: '3%',
        containLabel: true,
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : []
        }
    ],
    yAxis : [
        {
             type : 'value',
              
        }
    ],
    series : [//节点显示数据
        {
            name:'日报数',
            type:'line',
            stack: '总量',
            data:[]
        },
        {
            name:'日报回复数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
         {
            name:'新增客户',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'新增联系人',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'回访次数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'回访回复数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'新建项目数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'项目计划数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'项目计划改变数',
            type:'line',
      
           
            data:[]
        },
        {
            name:'项目计划完成数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'合同数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        
        {
            name:'合同金额',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'合同计划数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },{
            name:'合同计划改变数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'合同计划完成数',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'新建收款单数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'收款金额',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        
        {
            name:'新建开票数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        {
            name:'开票金额',
            type:'line',
            stack: '总量',
           
            data:[]
        },
        
        {
            name:'出差数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },
         {
            name:'请假数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },
         {
            name:'加班数量',
            type:'line',
            stack: '总量',
           
            data:[]
        },
       
    ]
};
	
	
  myChart.showLoading();    //数据加载完之前先显示一段简单的loading动画
  var dates=[];    //日期（实际用来盛放X轴坐标值）
  var nums1=[];    //日报数量数组（实际用来盛放Y坐标值）
  var nums2=[];     //日报回复数数量数组（实际用来盛放Y坐标值）
  var nums3=[];     // 新增客户数量数组（实际用来盛放Y坐标值）  
  var nums4=[];     //新增联系人数量数组（实际用来盛放Y坐标值）  
  var nums5=[];     //新建项目数数量数组（实际用来盛放Y坐标值） 
  var nums6=[];      //项目计划数数量数组（实际用来盛放Y坐标值）  
  var nums7=[];     //项目计划改变数数量数组（实际用来盛放Y坐标值）
  var nums8=[];      //项目计划完成数数量数组（实际用来盛放Y坐标值）
  var nums9=[];      //项目计划完成数数量数组（实际用来盛放Y坐标值）
  var nums10=[];     //项目计划完成数量数组（实际用来盛放Y坐标值）  
  var nums11=[];     //合同数量数量数组（实际用来盛放Y坐标值）   
  var nums12=[];     //合同金额数量数组（实际用来盛放Y坐标值）
  var nums13=[];    //合同计划数量数组（实际用来盛放Y坐标值）  
  var nums14=[];     //合同计划改变数数组（实际用来盛放Y坐标值）   
  var nums15=[];     //合同计划完成数数组（实际用来盛放Y坐标值） 
  var nums16=[];     //新建收款单数量数组（实际用来盛放Y坐标值）  
  var nums17=[];     //收款金额数组（实际用来盛放Y坐标值）  
  var nums18=[];     //新建开票数量数组（实际用来盛放Y坐标值）  
  var nums19=[];     //开票金额数组（实际用来盛放Y坐标值）  
  var nums20=[];     //出差数量数组（实际用来盛放Y坐标值）
  var nums21=[];     //请假数量数组（实际用来盛放Y坐标值）     
  var nums22=[];     //加班数量数组（实际用来盛放Y坐标值）  
           
     window.onload =function () {
		    DWREngine.setAsync(false); 
	 		DailyStatistic.loadAllDailyStatisticByContion("MONTH",dataStatistic);
	 		DWREngine.setAsync(true);
		}
		
	function getMonth(){
		DWREngine.setAsync(false); 
 		DailyStatistic.loadAllDailyStatisticByContion("MONTH",dataStatistic);
 		DWREngine.setAsync(true);
	}
	function getYear(){
 
		DWREngine.setAsync(false); 
 		DailyStatistic.loadAllDailyStatisticByContion("DAY",dataStatistic);
 		DWREngine.setAsync(true);
	}	
	

	
 	 function dataStatistic(result) {
 	      var dates=[];    //日期（实际用来盛放X轴坐标值）
  var nums1=[];    //日报数量数组（实际用来盛放Y坐标值）
  var nums2=[];     //日报回复数数量数组（实际用来盛放Y坐标值）
  var nums3=[];     // 新增客户数量数组（实际用来盛放Y坐标值）  
  var nums4=[];     //新增联系人数量数组（实际用来盛放Y坐标值）  
  var nums5=[];     //新建项目数数量数组（实际用来盛放Y坐标值） 
  var nums6=[];      //项目计划数数量数组（实际用来盛放Y坐标值）  
  var nums7=[];     //项目计划改变数数量数组（实际用来盛放Y坐标值）
  var nums8=[];      //项目计划完成数数量数组（实际用来盛放Y坐标值）
  var nums9=[];      //项目计划完成数数量数组（实际用来盛放Y坐标值）
  var nums10=[];     //项目计划完成数量数组（实际用来盛放Y坐标值）  
  var nums11=[];     //合同数量数量数组（实际用来盛放Y坐标值）   
  var nums12=[];     //合同金额数量数组（实际用来盛放Y坐标值）
  var nums13=[];    //合同计划数量数组（实际用来盛放Y坐标值）  
  var nums14=[];     //合同计划改变数数组（实际用来盛放Y坐标值）   
  var nums15=[];     //合同计划完成数数组（实际用来盛放Y坐标值） 
  var nums16=[];     //新建收款单数量数组（实际用来盛放Y坐标值）  
  var nums17=[];     //收款金额数组（实际用来盛放Y坐标值）  
  var nums18=[];     //新建开票数量数组（实际用来盛放Y坐标值）  
  var nums19=[];     //开票金额数组（实际用来盛放Y坐标值）  
  var nums20=[];     //出差数量数组（实际用来盛放Y坐标值）
  var nums21=[];     //请假数量数组（实际用来盛放Y坐标值）     
  var nums22=[];     //加班数量数组（实际用来盛放Y坐标值）  
            //请求成功时执行该函数内容，result即为服务器返回的json数据
            
              var jsonData = JSON.parse(result);
	         if (jsonData) {	 
                    for(var i=0;i<jsonData.length;i++){
                       dates.push(jsonData[i].dnDate);    //挨个取出类别并填入类别数组
                     }  
        	  for(var i=0;i<jsonData.length;i++){ 
                        nums1.push(jsonData[i].dailyNum);      //挨个取出销量并填入销量数组
                        nums2.push(jsonData[i].dailyReplyNum);
                        nums3.push(jsonData[i].customerNum);
                        nums4.push(jsonData[i].contactArchivesNum);
                        nums5.push(jsonData[i].backVisitNum);
                        nums6.push(jsonData[i].backVisitReplyNum);
                        nums7.push(jsonData[i].projectNum);
                        nums8.push(jsonData[i].projectPlanNum);
                        nums9.push(jsonData[i].projectPlanChangeNum);
                        nums10.push(jsonData[i].projectPlanFinishNum);
                        nums11.push(jsonData[i].contractManagementNum);
                        nums12.push(jsonData[i].contractManagementMoney);
                        nums13.push(jsonData[i].contractManagementPlanNum);
                        nums14.push(jsonData[i].contractManagementPlanChangeNum);
                        nums15.push(jsonData[i].contractManagementPlanFinishNum);
                        nums16.push(jsonData[i].financialManagementNum);
                        nums17.push(jsonData[i].financialManagementMoney);
                        nums18.push(jsonData[i].billingRecordNum);
                        nums19.push(jsonData[i].billingRecordMoney);
                        nums20.push(jsonData[i].businessTravelNum);
                        nums21.push(jsonData[i].leaveNum);
                        nums22.push(jsonData[i].overtimeNum);
                      }
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
                               },
                              {
                              data:nums4
                               },
                              {
                              data:nums5
                               },
                              {
                              data:nums5
                              
                               },
                              {
                              data:nums6
                               },
                              {
                              data:nums7
                               },
                              {
                              data:nums8
                               },
                              {
                              data:nums9
                               },
                              {
                              data:nums10
                               },
                              {
                              data:nums12
                               },
                              {
                              data:nums13
                               },
                              {
                              data:nums14
                               },
                              {
                              data:nums15
                               },
                              {
                              data:nums16
                               },
                              {
                              data:nums17
                              
                               },
                              {
                              data:nums18
                               },
                              {
                              data:nums19
                               },
                              {
                              data:nums20
                               },
                              {
                              data:nums21
                               },
                              {
                              data:nums22
                          }]
                    });
	}
	


    myChart.setOption(option);   //参数设置方法     
	

</script>
</@htmlPage>
