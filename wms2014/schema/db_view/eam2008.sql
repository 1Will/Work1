/*
  创建备件明细台帐视图
  chengding 更新于04/17/2009
  添加了备件类别
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spare_location_view]') and OBJECTPROPERTY(id, 

N'IsTable') = 1)
drop table [dbo].[t_spare_location_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spare_location_view]') and OBJECTPROPERTY(id, 

N'IsView') = 1)
drop view [dbo].[t_spare_location_view]
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
/*
create view t_spare_location_view([ID],spare_No,spare_Name,spare_EnName,unit,model_Specs,category,detail_Type,dept,unit_Price,
stocks,total_Price,location_Code,tooling_dev_flag
)
as
select spareLocation.id as id,spare.spare_no as spareNo,spare.name as spareName,spare.en_name as spareEnName,unit.name as unit,
       spare.model_specs as modelSpecs,category.name as category,detailType.name as detailType,
       dept.name as dept,spareLocation.unitPrice as unitPrice,spareLocation.stocks as stocks,
       spareLocation.unitPrice*spareLocation.stocks as totalPrice,
       location.code as locationCode, 
       case spare.tooling_dev_flag when 'TOOLING' THEN '工装'
                                   when 'DEVICE' THEN '设备'
       end
 from t_spare_location spareLocation left join (t_spare spare left join  t_code_value category on spare.category = category.id
                                                             left join t_spare_detail_type detailType on spare.spare_detail_type = detailType.id
                                                             left join t_code_value unit on spare.unit = unit.id)
                                            on spareLocation.spareId = spare.id
                                            left join t_location location on spareLocation.locationId = location.id
                                            left join t_department dept on spareLocation.deptId = dept.id 

*/
--zzb 2010-01-24
create view t_spare_location_view([ID],spare_No,spare_Name,spare_EnName,unit,model_Specs,warehouseId,category,detail_Type,dept,unit_Price,
stocks,total_Price,location_Code,warehouseName,regionalName,tooling_dev_flag)
as
select spareLocation.id as id,spare.spare_no as spareNo,spare.name as spareName,spare.en_name as spareEnName,unit.name as unit,
       spare.model_specs as modelSpecs,spareLocation.warehouseId as warehouseId,category.name as category,detailType.name as detailType,
       dept.name as dept,spareLocation.unitPrice as unitPrice,spareLocation.stocks as stocks,
       spareLocation.unitPrice*spareLocation.stocks as totalPrice,
       location.code as locationCode,
	   warehouse.name as warehouseName,
		regional.name as regionalName,
       case spare.tooling_dev_flag when 'TOOLING' THEN '工装'
                                   when 'DEVICE' THEN '设备'
       end
 from t_spare_location spareLocation left join (t_spare spare left join  t_code_value category on spare.category = category.id
                                                             left join t_spare_detail_type detailType on spare.spare_detail_type = detailType.id
                                                             left join t_code_value unit on spare.unit = unit.id)
                                            on spareLocation.spareId = spare.id
                                            left join t_location location on spareLocation.locationId = location.id
                                            left join t_department dept on spareLocation.deptId = dept.id 
											left join t_warehouse warehouse on spareLocation.warehouseId = warehouse.id
                                            left join t_regional regional on spareLocation.regionalId = regional.id 

--创建年度计划的视图
/*
drop view t_year_plan_detail
go;
create view t_year_plan_detail 
([ID],DETAIL_TYPE,PLAN_NO,PLAN_NAME,PLAN_YEAR,DEPT_NAME,PLAN_ALL_FEE,GRAPH_NO,NAME,SPECIFICATION,MODEL,CATEGORY_NAME,DETAIL_CATEGORY_NAME,UNIT_PRICE,NUMBER,ALL_PRICE,PLAN_FINISHED_DATE,REQUEST_REASON,COMMENT,YEAR_PLAN_ID)
as 
select yearPlanDetail.id,yearPlanDetail.DETAIL_TYPE,yearPlan.PLAN_NO,
       yearPlan.NAME, yearPlan.YEAR, yearPlan.DEPT_NAME, yearPlan.PLAN_ALL_FEE,yearPlanDetail.GRAPH_NO,
       yearPlanDetail.NAME,yearPlanDetail.SPECIFICATION,yearPlanDetail.MODEL,yearPlanDetail.CATEGORY_NAME,
       yearPlanDetail.DETAIL_CATEGORY_NAME,yearPlanDetail.UNIT_PRICE,yearPlanDetail.NUMBER,yearPlanDetail.ALL_PRICE,
       yearPlanDetail.REQUEST_DATE,yearPlanDetail.REQUEST_REASON,yearPlanDetail.COMMENT,yearPlanDetail.YEAR_PLAN_ID
FROM t_tooling_year_plan_detail yearPlanDetail, t_tooling_year_plan yearPlan
WHERE yearPlanDetail.YEAR_PLAN_ID = yearPlan.id
*/

--2008/06/13zsm修改了“when 'REPAIR_MAINTENANCE' THEN '维修保养'”
/*
     创建年度计划的视图
	chengding 更新于09/1/8
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_year_plan_detail_view]') and OBJECTPROPERTY(id, 

N'IsTable') = 1)
drop table [dbo].[t_year_plan_detail_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_year_plan_detail_view]') and OBJECTPROPERTY(id, 

N'IsView') = 1)
drop view [dbo].[t_year_plan_detail_view]
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
create view t_year_plan_detail_view
([ID],DETAIL_TYPE,PLAN_NO,PLAN_NAME,PLAN_YEAR,DEPT_NAME,PLAN_ALL_FEE,GRAPH_NO,NAME,SPECIFICATION,
MODEL,CATEGORY_NAME,DETAIL_CATEGORY_NAME,UNIT_PRICE,NUMBER,ALL_PRICE,PLAN_FINISHED_DATE,
REQUEST_REASON,COMMENT,YEAR_PLAN_ID,UNIT)
as 
select yearPlanDetail.id,case yearPlanDetail.DETAIL_TYPE when 'TOOLING_MAKE'       THEN '工装制作' 
                                                         when 'SPARE_PURCHASE'     THEN '备件采购'
                                                         when 'REPAIR_MAINTENANCE' THEN '维修保养'
                                                         when 'TECH_ALTER'         THEN '技术改造' 
                                                         end ,
       yearPlan.PLAN_NO,
       yearPlan.NAME,
       yearPlan.YEAR, 
       dept.name, 
       yearPlan.PLAN_ALL_FEE,
       yearPlanDetail.GRAPH_NO,
       yearPlanDetail.NAME,
       yearPlanDetail.SPECIFICATION,
       yearPlanDetail.MODEL,
       yearPlanDetail.CATEGORY_NAME,
       yearPlanDetail.DETAIL_CATEGORY_NAME,
       yearPlanDetail.UNIT_PRICE,
       yearPlanDetail.NUMBER,
       yearPlanDetail.ALL_PRICE,
       yearPlanDetail.REQUEST_DATE,
       yearPlanDetail.REQUEST_REASON,
       yearPlanDetail.COMMENT,
       yearPlanDetail.YEAR_PLAN_ID,
       t_code_value.name 
FROM t_tooling_year_plan_detail yearPlanDetail LEFT JOIN  t_code_value
      ON yearPlanDetail.cal_unit_id=t_code_value.id, 
	t_tooling_year_plan yearPlan left join t_department dept on yearPlan.DEPT_ID = dept.id

WHERE yearPlanDetail.YEAR_PLAN_ID = yearPlan.id

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO




/*
	创建季度计划的视图
	yli 更新于08/12/16
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_quarter_plan_detail_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_quarter_plan_detail_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_quarter_plan_detail_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_quarter_plan_detail_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO
create view t_quarter_plan_detail_view 
([ID],DETAIL_TYPE,QUARTER_TYPE,PLAN_NO,PLAN_NAME,PLAN_YEAR,DEPT_NAME,PLAN_ALL_FEE,GRAPH_NO,NAME,CATEGORY_NAME,DETAIL_CATEGORY_NAME,SPECIFICATION,MODEL,UNIT_PRICE,NUMBER,ALL_PRICE,REQUEST_DATE,REQUEST_REASON,COMMENT,QUARTER_PLAN_ID,calunit)
as
 select quarterPlanDetail.id,case quarterPlanDetail.DETAIL_TYPE  when 'TOOLING_MAKE'       THEN '工装制作' 
                                                                 when 'SPARE_PURCHASE'     THEN '备件采购'
                                                                 when 'REPAIR_MAINTENANCE' THEN '维修保养'
                                                                 when 'TECH_ALTER'         THEN '技术改造'
                                                                 end,
 quarterPlan.QUARTER_TYPE,
 quarterPlan.PLAN_NO,
 quarterPlan.NAME,
 quarterPlan.YEAR,
 quarterPlan.DEPT_NAME,
 quarterPlan.PLAN_ALL_FEE,
 quarterPlanDetail.GRAPH_NO,
 quarterPlanDetail.NAME,
 quarterPlanDetail.CATEGORY_NAME,
 quarterPlanDetail.DETAIL_CATEGORY_NAME,
 quarterPlanDetail.SPECIFICATION,
 quarterPlanDetail.MODEL,
 quarterPlanDetail.UNIT_PRICE,
 quarterPlanDetail.NUMBER,
 quarterPlanDetail.ALL_PRICE,
 quarterPlanDetail.REQUEST_DATE,
 quarterPlanDetail.REQUEST_REASON,
 quarterPlanDetail.COMMENT,
 quarterPlanDetail.QUARTER_PLAN_ID,
calunit.name as calunit
 FROM t_tooling_quarter_plan_detail quarterPlanDetail 
left join t_code_value calunit on quarterPlanDetail.cal_unit_id = calunit.id
left join t_tooling_quarter_plan quarterPlan on quarterPlan.id=quarterPlanDetail.QUARTER_PLAN_ID
 WHERE quarterPlan.disabled=0
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO



/*
create view t_year_plan_detail 
([ID],DETAIL_TYPE,PLAN_NO,PLAN_NAME,PLAN_YEAR,DEPT_NAME,PLAN_ALL_FEE,NAME,SPECIFICATION,MODEL,CATEGORY_NAME,UNIT_PRICE,NUMBER,ALL_PRICE,PLAN_FINISHED_DATE,REQUEST_REASON,COMMENT,YEAR_PLAN_ID)
as 
SELECT 10000 + toolingMakeDetail.id, '工装制造' DETAIL_TYPE, yearPlan.PLAN_NO, 
      yearPlan.NAME, yearPlan.YEAR, yearPlan.DEPT_NAME, yearPlan.PLAN_ALL_FEE, 
      toolingMakeDetail.NAME, toolingMakeDetail.SPECIFICATION, 
      toolingMakeDetail.MODEL, toolingMakeDetail.CATEGORY_NAME, 
      toolingMakeDetail.UNIT_PRICE, toolingMakeDetail.NUMBER, 
      toolingMakeDetail.ALL_PRICE, toolingMakeDetail.PLAN_FINISHED_DATE, 
      toolingMakeDetail.REQUEST_REASON, toolingMakeDetail.COMMENT, 
      toolingMakeDetail.YEAR_PLAN_ID
FROM t_tooling_make_detail toolingMakeDetail, t_tooling_year_plan yearPlan
WHERE toolingMakeDetail.YEAR_PLAN_ID = yearPlan.id
UNION
SELECT 20000 + sparePurchaseDetail.id, '备件采购' DETAIL_TYPE, yearPlan.PLAN_NO, 
      yearPlan.NAME, yearPlan.YEAR, yearPlan.DEPT_NAME, yearPlan.PLAN_ALL_FEE, 
      sparePurchaseDetail.NAME, sparePurchaseDetail.SPECIFICATION, 
      sparePurchaseDetail.MODEL, 
      sparePurchaseDetail.DETAIL_CATEGORY_NAME CATEGORY_NAME,
      sparePurchaseDetail.UNIT_PRICE, sparePurchaseDetail.NUMBER, 
      sparePurchaseDetail.ALL_PRICE, 
      sparePurchaseDetail.REQUEST_DATE PLAN_FINISHED_DATE, 
      sparePurchaseDetail.REQUEST_REASON, sparePurchaseDetail.COMMENT, 
      sparePurchaseDetail.YEAR_PLAN_ID
FROM t_spare_purchase_detail sparePurchaseDetail, t_tooling_year_plan yearPlan
WHERE sparePurchaseDetail.YEAR_PLAN_ID = yearPlan.id
UNION
SELECT 40000 + repairMaintenanceDetail.id, '维修保养' DETAIL_TYPE, yearPlan.PLAN_NO, 
      yearPlan.NAME, yearPlan.YEAR, yearPlan.DEPT_NAME, yearPlan.PLAN_ALL_FEE, 
      repairMaintenanceDetail.TOOLING_NAME, 
      repairMaintenanceDetail.TOOLING_SPECIFICATION, 
      repairMaintenanceDetail.TOOLING_MODEL, 
      repairMaintenanceDetail.TOOLING_CATEGORY, repairMaintenanceDetail.PLAN_FEE, 
      1, repairMaintenanceDetail.PLAN_FEE, 
      repairMaintenanceDetail.PLAN_FINISHED_DATE, repairMaintenanceDetail.REASON, 
      repairMaintenanceDetail.COMMENT, repairMaintenanceDetail.YEAR_PLAN_ID
FROM t_repair_maintenance_detail repairMaintenanceDetail, t_tooling_year_plan yearPlan
WHERE repairMaintenanceDetail.YEAR_PLAN_ID = yearPlan.id
UNION
SELECT 60000 + techAlterDetail.id, '技术改造' DETAIL_TYPE, yearPlan.PLAN_NO, 
      yearPlan.NAME, yearPlan.YEAR, yearPlan.DEPT_NAME, yearPlan.PLAN_ALL_FEE, 
      techAlterDetail.TOOLING_NAME, techAlterDetail.TOOLING_SPECIFICATION, 
      techAlterDetail.TOOLING_MODEL, techAlterDetail.TOOLING_CATEGORY, 
      techAlterDetail.PLAN_FEE, 1, techAlterDetail.PLAN_FEE, 
      techAlterDetail.PLAN_FINISHED_DATE, techAlterDetail.TECH_ALTER_CONTENT, 
      techAlterDetail.COMMENT, techAlterDetail.YEAR_PLAN_ID
FROM t_tech_alter_detail techAlterDetail, t_tooling_year_plan yearPlan
WHERE techAlterDetail.YEAR_PLAN_ID = yearPlan.id
*/

--删除年度计划明细表
drop table t_tooling_make_detail
drop table t_repair_maintenance_detail
drop table t_spare_purchase_detail
drop table t_tech_alter_detail
drop table t_tooling_year_plan
drop table t_tooling_quarter_plan



--更新年度计划已批状态
update t_tooling_year_plan set APPROVE_FLAG=0
--更新季度计划锁定状态
update t_tooling_year_plan set LOCKED_FLAG=0

update t_tooling_make_detail set LOCKED_FLAG=0

update t_repair_maintenance_detail set LOCKED_FLAG=0

update t_spare_purchase_detail set LOCKED_FLAG=0

update t_tech_alter_detail set LOCKED_FLAG=0

--更新备件清查
update t_spare_inventory set submit = 0
update t_spare_inout_bill set submit = 0
update t_check_point_report set submit = 0
----创建工装管理卡视图
/*create view t_tooling_account 
as 
select  borrow.id id,
	tooling.id toolingID,
	tooling.device_no device_no,
        tooling.product_string product_string,
 	tooling.used_quota usedQuota,
	tooling.tooling_type_detail_string tooling_type_detail_string,
	tooling.acceptance_time acceptanceTime,
	tooling.name toolingName,
	tooling.maker maker,
	tooling.checker_string checker_string,
	tooling.made_time madeTime,
	tooling.check_time checkTime,
	tooling.tooling_designer_string tooling_designer_string,
	tooling.complete_time completeTime,
	tooling.design_time designTime,
	tooling.put_no putNo,
	tooling.disabled_time disabledTime,
	tooling.used_started_time usedStartedTime,
	tooling.suggestion suggestion,
	tooling.cal_unit_string cal_unit_string,
	borrow.total_output totalOutput,
	borrow.bor_ret_datetime borrowReturnDateTm,
	borrow.produce_num produceNum,
	t_users.name borrowerName
from (t_device_card tooling left join t_tooling_borrow_bill borrow on  tooling.id = borrow.tooling_id)
left join t_users on borrow.borrower_id = t_users.id
*/
/*
   zbzhang 2008.6.24 把tooling.id修改case  when borrow.id is null then tooling.id 
                        else borrow.id+tooling.id end id,
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_tooling_account]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_tooling_account]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO
create view t_tooling_account 
as 
select  case  when borrow.id is null then tooling.id 
                        else borrow.id+tooling.id 
        end id,
	tooling.id toolingID,
	tooling.device_no device_no,
    tooling.product_string product_string,
 	tooling.used_quota usedQuota,
	tooling.tooling_type_detail_string tooling_type_detail_string,
	tooling.acceptance_time acceptanceTime,
	tooling.name toolingName,
	tooling.maker maker,
	tooling.checker_string checker_string,
	tooling.made_time madeTime,
	tooling.check_time checkTime,
	tooling.tooling_designer_string tooling_designer_string,
	tooling.complete_time completeTime,
	tooling.design_time designTime,
	tooling.put_no putNo,
	tooling.disabled_time disabledTime,
	tooling.used_started_time usedStartedTime,
	tooling.suggestion suggestion,
	tooling.cal_unit_string cal_unit_string,
	borrow.total_output totalOutput,
	borrow.bor_ret_datetime borrowReturnDateTm,
	borrow.produce_num produceNum,
	t_users.name borrowerName
from (t_device_card tooling left join t_tooling_borrow_bill borrow on  tooling.id = borrow.tooling_id)
left join t_users on borrow.borrower_id = t_users.id

GO
SET QUOTED_IDENTIFIER OFF  
GO
SET ANSI_NULLS ON 
GO


--创建点检报告视图
/*
create view t_check_point_report_view
(ID,[month],dept_name,report_no,name,device_no,device_name,model,specification,run_time,maintenance_time,fault_time,comment,report_id)
as 
select reportDetail.id,report.month,report.dept_name,report.report_no,report.name,
       device.device_no,device.name,device.model,device.specification,
       reportDetail.run_time,reportDetail.maintenance_time,reportDetail.fault_time,reportDetail.comment,report.id
from t_check_point_report report,t_check_point_report_detail reportDetail,t_device_card device
where report.id = reportDetail.report_id and reportDetail.device_id = device.id
*/

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_check_point_report_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_check_point_report_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_check_point_report_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_check_point_report_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

create view t_check_point_report_view
(ID,[month],dept_name,report_no,name,device_no,device_name,model,specification,run_time,maintenance_time,fault_time,comment,report_id)
as 
select reportDetail.id,
       report.month,
       report.dept_name,
       report.report_no,
       report.name,
       device.device_no,
       device.name,
       device.model,
       device.specification,
       reportDetail.run_time,
       reportDetail.maintenance_time,
       reportDetail.fault_time,
       reportDetail.comment,report.id
from   t_check_point_report report,t_check_point_report_detail reportDetail,t_device_card device
where  report.id = reportDetail.report_id and reportDetail.device_id = device.id

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

--创建保养计划视图
/*
create view t_maintenance_plan_view
(ID,[month],dept_name,maintenance_planNo,name,device_no,device_name,model,specification,plan_date,actual_date,result,duty_people,result_type,comment,maintenance_id)
as
select  maintenanceDetail.id,
	maintenance.month,
	dpt.name,
	maintenance.plan_no,
	maintenance.plan_name,
       	device.device_no,
	device.name,
	device.model,
	device.specification,
       	maintenanceDetail.plan_date,
        maintenanceDetail.actual_date,
        maintenanceDetail.result,
	u.name,
	maintenanceDetail.result_type,
	maintenanceDetail.comment,
	maintenance.id
from 	t_maintenance maintenance,
	t_maintenance_detail maintenanceDetail,
	t_device_card device,
	t_department dpt,
	t_users u
where 	maintenance.id = maintenanceDetail.plan_ID 
and 	maintenanceDetail.device_id = device.id
and 	dpt.id = maintenance.dept_ID
and	u.id = maintenanceDetail.duty_people
*/



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_maintenance_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_maintenance_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_maintenance_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_maintenance_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
--2008-08-26zsm增加了失效判断和修改了视图
create view t_maintenance_plan_view
(ID,[month],dept_name,maintenance_planNo,name,device_no,device_name,model,specification,plan_date,actual_date,result,duty_people,result_type,comment,maintenance_id)
as
select  maintenanceDetail.id,
        maintenance.month,
        dpt.name,
        maintenance.plan_no,
	    maintenance.plan_name,
       	device.device_no,
	    device.name,
	    device.model,
	    device.specification,
       	maintenanceDetail.plan_date,
        maintenanceDetail.actual_date,
        maintenanceDetail.result,
	    u.name,
	    maintenanceDetail.result_type,
	    maintenanceDetail.comment,
	    maintenance.id
from 	t_maintenance maintenance left join t_department dpt on maintenance.dept_ID = dpt.id ,
        (t_maintenance_detail maintenanceDetail left join t_device_card device on maintenanceDetail.device_id = device.id)
        left join t_users u on maintenanceDetail.duty_people = u.id
where 	maintenance.id = maintenanceDetail.plan_ID 
and     maintenance.disabled = 0

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

--创建润滑计划修改后的视图
-- yli更新于 2008/12/19
/*
create view t_lubrication_plan_view
(ID,planBeginTime,dept_name,lubrication_planNo,name,device_no,device_name,[position],ruleDsp,methodDsp,lubricatingOil_name,lubricatingOilMeasureUnit,planLubricatingOilQty,planExePeople,estimateExecDate,comment,lubricationPlan_id,
actualLubricatingOilQty,actualExePeople,actualExecDate,plan_status)
as
select lubricationPlanDetail.id,lubricationPlan.planBeginTime,dept.NAME,lubricationPlan.PlanNo,lubricationPlan.name,
       device.device_no,device.name,lubricationPlanDetail.position,lubricationPlanDetail.rule_description,lubricationPlanDetail.lubriction_method,
       lubricationPlanDetail.lubricatingOil,lubricationPlanDetail.lubricatingOil_measure_unit,lubricationPlanDetail.plan_lubricatingOil_Qty,
       lubricationPlanDetail.plan_exe_people,lubricationPlanDetail.estimate_exec_date,lubricationPlanDetail.comment,
       lubricationPlan.id,lubricationPlanDetail.actual_lubricatingOil_Qty,lubricationPlanDetail.actual_exe_people,
       lubricationPlanDetail.actual_exec_date,lubricationPlanDetail.plan_status
from   t_lubrication_plan lubricationPlan,t_lubrication_plan_detail lubricationPlanDetail,t_device_card device,t_department dept
where  lubricationPlan.plan_id = lubricationPlanDetail.lubricaion_plan_id
and    lubricationPlanDetail.device_id = device.id
and    dept.id = lubricationPlan.department
*/

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_lubrication_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_lubrication_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_lubrication_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_lubrication_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

create view t_lubrication_plan_view
(ID,[month],dept_name,lubrication_planNo,name,device_no,device_name,

[position],ruleDsp,methodDsp,lubricatingOil_name,lubricatingOilMeasureUnit,
planLubricatingOilQty,planExePeople,planExectPeople,estimateExecDate,comment,
lubricationPlan_id,actualLubricatingOilQty,actualExePeople,actualExecDate,plan_status)
as
select lubricationPlanDetail.id,
lubricationPlan.month,
dept.NAME,
lubricationPlan.PlanNo,
lubricationPlan.name,
device.device_no,
device.name,
lubricationPlanDetail.position,
lubricationPlanDetail.rule_description,
lubricationPlanDetail.lubriction_method,
oil.name,
lubricationPlanDetail.lubricatingOil_measure_unit,
lubricationPlanDetail.plan_lubricatingOil_Qty,
lubricationPlanDetail.plan_exe_people,
lubricationPlanDetail.planExectPeople,
lubricationPlanDetail.estimate_exec_date,
lubricationPlanDetail.comment,
lubricationPlan.id,
lubricationPlanDetail.actual_lubricatingOil_Qty,
lubricationPlanDetail.actual_exe_people,
lubricationPlanDetail.actual_exec_date,
lubricationPlanDetail.plan_status
from   t_lubrication_plan lubricationPlan,t_device_card device,t_department dept, 
       (t_lubrication_plan_detail lubricationPlanDetail left join t_lubrication_oil oil on  

lubricationPlanDetail.lubricatingOil = oil.id)
where  lubricationPlan.id = lubricationPlanDetail.lubricaion_plan_id
and    lubricationPlanDetail.device_id = device.id
and    dept.id = lubricationPlan.department

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

--创建Sum_Fault_Times视图
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[Sum_Fault_Times]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[Sum_Fault_Times]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

Create View Sum_Fault_Times as

select tooling_dev_id,
DATEPART(year, fault_occur_datetime) Occur_Year ,DATEPART(m, fault_occur_datetime) Occur_Month,
sum(fault_loss_time) LossTime
from  t_fault_bill
group by tooling_dev_id, 
DATEPART(year, fault_occur_datetime) ,DATEPART(m, fault_occur_datetime)

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

--创建 EventLog 和 EventType
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EventLog]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EventLog]
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[EventType]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[EventType]
GO

CREATE TABLE [dbo].[EventLog] (
	[EventId] [int] NOT NULL ,
	[EventType] [int] NOT NULL ,
	[CreationTime] [datetime] NOT NULL ,
	[ProcessTime] [datetime] NOT NULL ,
	[Status] [int] NOT NULL ,
	[OrgId] [int] NULL ,
	[UserId] [int] NULL ,
	[DocType] [int] NULL ,
	[DocId] [int] NULL ,
	[MoreInfo] [nvarchar] (1000) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO

CREATE TABLE [dbo].[EventType] (
	[EventId] [int] NOT NULL ,
	[EventName] [nvarchar] (255) COLLATE Chinese_PRC_CI_AS NULL 
) ON [PRIMARY]
GO



/*
--创建标定计划视图
	yli 更新于08/12/23
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_calibration_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_calibration_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_calibration_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_calibration_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
--2008/06/14zsm增加了“t_calibration calibration left join t_department dept on calibration.dept_id = dept.id,”
create view t_calibration_plan_view
(ID,[month],dept_name,calibration_planNo,name,asset_no,asset_name,graphNo,demarcateCycle,duty_people,planDate,actual_date,result,calibration_result,calibration_id)
as
select  calibrationDetail.id,
	    calibration.month,
	    dept.name,
	    calibration.plan_no,
	    calibration.plan_name,
       	device.device_no,
	    device.name,
	    device.graph_no,
	    device.demarcate_cycle,
	    u.name,
	    calibrationDetail.plan_date,
        calibrationDetail.actual_date,
        case calibrationDetail.result when 'UNFINISHED' then '未完成'
                                      when 'FINISHED'   then '已完成'
                                      end,
        case calibrationDetail.calibration_result when 'PASS'   then '合格'
                                                  when 'NOPASS' then '不合格' 
                                                  end,
	    calibration.id
from 	t_calibration calibration left join t_department dept on calibration.dept_id = dept.id,
	    (t_calibration_detail calibrationDetail left join t_device_card device on  calibrationDetail.tooling_id = device.id)
        left join t_users u on calibrationDetail.duty_people = u.id
where 	calibration.id = calibrationDetail.plan_id
AND calibration.disabled=0

--创建预防性维修计划汇总视图
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_prerepair_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_prerepair_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_prerepair_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_prerepair_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
CREATE VIEW t_prerepair_plan_view
(ID,begin_date,deptName,device_no,device_name,graph_no,content,duty_deptName,externalHelpFlag,repairLevel,dutyPeople,execPeople,
 planEstimateFinishDate,plan_all_Fee,proc_exec_people,procEstimateFinishDate,proc_all_Fee,procResult,prerepair_plan_id,
 planAllFee,procAllFee,tooling_dev_flag)
 as 
 select preRepairPlanDetail.id,
        preRepairPlan.begin_date,
        preRepairPlan.dept_name,
        device.device_no,
        device.name,
        device.graph_no,
        preRepairPlanDetail.content,
        preRepairPlanDetail.dept_name,
        preRepairPlanDetail.external_help_flag,
        code.name,
        user1.name,
        user2.name,
        preRepairPlanDetail.plan_estimate_finish_date,
        preRepairPlanDetail.plan_all_fee,
        user3.name,
        preRepairPlanDetail.proc_estimate_finish_date,
        preRepairPlanDetail.proc_all_fee,
        case preRepairPlanDetail.proc_result when 'UNFINISHED' then '未完成'
                                             when 'FINISHED'   then '完成'
                                             when 'CANCEL'     then '取消'
                                             when 'SHIFT'      then '转移'end,
        preRepairPlan.plan_id,
        preRepairPlan.plan_All_fee,
        preRepairPlan.proc_All_fee,
        preRepairPlan.tooling_dev_flag
 from   t_prerepair_plan preRepairPlan,
 		( t_prerepair_plan_detail preRepairPlanDetail left join t_device_card device on preRepairPlanDetail.asset_id = device.id)
 		left join t_users user1 on preRepairPlanDetail.duty_people = user1.id 
 		left join t_users user2 on preRepairPlanDetail.exec_people = user2.id
 		left join t_users user3 on preRepairPlanDetail.proc_exec_people = user3.id
 		left join t_code_value code on preRepairPlanDetail.repair_Level = code.id 
where  preRepairPlan.id = preRepairPlanDetail.plan_id 

--采购计划汇总
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_purchase_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_purchase_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_purchase_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_purchase_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
--2008-8-12zsm增加了失效的判断
create view t_purchase_plan_view
(ID,year,deptName,name,specification,model,unitPrice,number,allPrice,planPurchaseDate,comment,PurchasePlanId)
as
select PurchasePlanDetail.id,
       PurchasePlan.[YEAR],
       dept.name,
       PurchasePlanDetail.NAME,
       PurchasePlanDetail.SPECIFICATION,
       PurchasePlanDetail.MODEL,
       PurchasePlanDetail.UNIT_PRICE,
       PurchasePlanDetail.NUMBER,
       PurchasePlanDetail.ALL_PRICE,
       PurchasePlanDetail.PLAN_PURCHASE_DATE,
       PurchasePlanDetail.COMMENT,
       PurchasePlanDetail.PURCHASE_PLAN_ID
from   t_purchase_plan PurchasePlan,
       t_purchase_plan_detail PurchasePlanDetail,
       t_department dept
where  PurchasePlan.id = PurchasePlanDetail.PURCHASE_PLAN_ID and PurchasePlan.DEPT_ID = dept.id and PurchasePlan.disabled = 0

--大项修计划实施汇总
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_year_repair_plan_and_proc_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_year_repair_plan_and_proc_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_year_repair_plan_and_proc_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_year_repair_plan_and_proc_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
create view t_year_repair_plan_and_proc_view
(ID,[year],departName,work_no,device_no,device_name,model,machine_flag,electrical_flag,category_id,use_dept,repair_dept,
plan_detail_all_fee,plan_repair_date,plan_begin_date,plan_end_date,content,proc_repair_date,proc_begin_date
,proc_end_date,proc_exec_people,proc_detail_all_fee,proc_result,yearRepairPlanAndProcId)
as
select reRepairPlanAndProcDetail.id,
       yearRepairPlanAndProc.year as year,
       yearRepairPlanAndProc.depart_Name as departName,
       reRepairPlanAndProcDetail.work_no as workNo,
       device.device_no as deviceNo,
       device.name as deviceName,
       device.model as model,
       reRepairPlanAndProcDetail.machine_flag as machineFlag,
       reRepairPlanAndProcDetail.electrical_flag as electricalFlag,
       reRepairPlanAndProcDetail.category_id as [category.value],
       device_dept.name as useDept,
       dept.name as repairDept,
       reRepairPlanAndProcDetail.plan_detail_all_fee as planDetailAllFee,
       reRepairPlanAndProcDetail.plan_repair_date as planRepairDate,
       reRepairPlanAndProcDetail.plan_begin_date as planBeginDate,
       reRepairPlanAndProcDetail.plan_end_date as planEndDate,
       reRepairPlanAndProcDetail.content as content,
       reRepairPlanAndProcDetail.proc_repair_date as procRepairDate,
       reRepairPlanAndProcDetail.proc_begin_date as procBeginDate,
       reRepairPlanAndProcDetail.proc_end_date as procEndDate,
       reRepairPlanAndProcDetail.proc_exec_people as procExecPeople,
       reRepairPlanAndProcDetail.proc_detail_all_fee as procDetailAllFee,
       reRepairPlanAndProcDetail.proc_result as procResult,
       reRepairPlanAndProcDetail.year_repair_plan_id 
from   t_year_repair_plan_and_proc yearRepairPlanAndProc,
       ( t_repair_plan_and_proc_detail reRepairPlanAndProcDetail left join (t_device_card device left join t_department device_dept on device.dept_id = device_dept.id) on reRepairPlanAndProcDetail.asset_id = device.id)
       left join t_department dept on reRepairPlanAndProcDetail.dept_id = dept.id
where  yearRepairPlanAndProc.id = reRepairPlanAndProcDetail.year_repair_plan_id 


--创建清洗计划汇总视图
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_wash_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_wash_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_wash_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_wash_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
create view t_wash_plan_view
(ID,[month],dept_name,wash_planNo,wash_name,asset_no,asset_name,graphNo,product_model,process_no,planWashDate,procWashDate,duty_people,supervise_people,result,comment,wash_id)
as
select  washDetail.id,
	wash.plan_begin_date,
	wash.dept_name,
	wash.plan_no,
	wash.name,
    device.device_no,
	device.name,
	device.graph_no,
    washDetail.product_model,
    device.process_no,
    washDetail.plan_wash_date,
	washDetail.proc_wash_date,
	u.name,
    u.name,             case washDetail.wash_result when 'UNFINISHED' then '未完成'
                                                    when 'FINISHED'   then '已完成'
                                                    end,
    washDetail.comment,
	wash.id
    from t_wash wash,
	(t_wash_detail washDetail left join t_device_card device on  washDetail.tooling_id = device.id)
    left join t_users u on washDetail.duty_people_id = u.id and washDetail.supervise_people_id = u.id
    where 	wash.id = washDetail.wash_plan_id
    
--创建供应商评定试图
create view supplier_Evaluate_view
(ID,year,dept_name,grade_Number,evaluateName,evaluate_date,grade,comment,supplierEvaluate_id)
as
 select supplierEvalueDetail.id,supplierEvalue.Unit_Year,supplierEvalue.dept_Name,supplierEvalue.evaluate_MINUTE,
 supplierEvalue.evaluate_Name,supplierEvalue.EVALUATE_DATE,supplierEvalue.evaluate_MINUTE,supplierEvalueDetail.grade_no,
 supplierEvalueDetail.coment_NO,supplierEvalueDetail.SUPPLIEREVALUE_ID
 from t_supplierEvalue supplierEvalue,t_supplierEvalue_detail supplierEvalueDetail
 where  supplierEvalueDetail.SUPPLIEREVALUE_ID=supplierEvalue.id


/*--创建申购单视图
create view t_subscribe_detail_view
(ID,NAME,MODEL,specification,supplier_Name,AMOUNT,UNITPRICE,TOTAL_PRICE,require_Date,COMMENT,STATUS_ID,
 SUBSCRIBE_DATE,buyingPerson,dept,subscribeid)
 as
 select subscribeDetail.id,
        subscribeDetail.NAME,
        subscribeDetail.MODEL,
        subscribeDetail.specification,
        subscribeDetail.supplier_Name,
        subscribeDetail.AMOUNT,
        subscribeDetail.UNITPRICE,
        subscribeDetail.TOTAL_PRICE,
        subscribeDetail.require_Date,
        subscribeDetail.COMMENT,
        subscribeDetail.STATUS_ID,
        subscribe.SUBSCRIBE_DATE,
        subscribe.buyingPersonID,
        subscribe.DEPTID,
        subscribe.id
 from   t_subscribe_detail subscribeDetail,t_subscribe subscribe
 where  subscribe.id = subscribeDetail.SUBSCRIBE_ID*/
 
 
 /*  陈晓松更新于2008/5/24 */
 /* 预防性维修计划报表查询语句*/
 select preRepairPlan.begin_date as beginDate,
        preRepairPlan.dept_name as deptName,
        device.device_no as deviceNo,
        device.name as deviceName,
        device.graph_no as graphNo,
        preRepairPlanDetail.content as content,
        preRepairPlanDetail.dept_name as dutyDeptName,
        preRepairPlanDetail.external_help_flag as externalHelpFlag,
        preRepairPlanDetail.repair_Level as [repairLevel.value],
        user1.name as dutyPeople,
        user2.name as execPeople,
        preRepairPlanDetail.plan_estimate_finish_date as planEstimateFinishDate,
        preRepairPlanDetail.plan_all_fee as planAllFee     
 from   t_prerepair_plan preRepairPlan,
        ( t_prerepair_plan_detail preRepairPlanDetail left join t_device_card device on preRepairPlanDetail.asset_id = device.id)
 	left join t_users user1 on preRepairPlanDetail.duty_people = user1.id 
 	left join t_users user2 on preRepairPlanDetail.exec_people = user2.id
where  preRepairPlan.id = preRepairPlanDetail.plan_id



/* 更新后预防性维修计划报表查询语句*/
select prerePairplanview.begin_date as beginDate ,
        prerePairplanview.deptName as  deptName,
        prerePairplanview.device_no as deviceNo ,
        prerePairplanview.device_name as deviceName,
        prerePairplanview.graph_no as graphNo ,
        prerePairplanview.content as content,
        prerePairplanview.duty_deptName as dutyDeptName,
        prerePairplanview.externalHelpFlag as externalHelpFlag,
        prerePairplanview.repairLevel as repairLevel ,
        prerePairplanview.dutyPeople as dutyPeople,
        prerePairplanview.execPeople as execPeople,

        prerePairplanview.planEstimateFinishDate as planEstimateFinishDate,
        prerePairplanview.plan_all_fee   as  planAllFee  
 from  t_prerepair_plan_view prerePairplanview
 
 
 
 /*更新后预防性维修视图
   zbzhang 2008/07/03 将device.name修改为        case  when preRepairPlanDetail.asset_id is not null then device.name
                         else preRepairPlanDetail.toolingName end,
 */
 if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_prerepair_plan_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_prerepair_plan_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_prerepair_plan_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_prerepair_plan_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

 CREATE VIEW t_prerepair_plan_view
(ID,begin_date,deptName,device_no,device_name,graph_no,content,duty_deptName,externalHelpFlag,repairLevel,dutyPeople,execPeople,proc_exec_people,
 planEstimateFinishDate,plan_all_Fee,procEstimateFinishDate,proc_all_Fee,procResult,prerepair_plan_id,
 planAllFee,procAllFee,tooling_dev_flag)
 as 
 select preRepairPlanDetail.id,
        preRepairPlan.begin_date,
        preRepairPlan.dept_name,
        device.device_no,
        case  when preRepairPlanDetail.asset_id is not null then device.name
                         else preRepairPlanDetail.toolingName end,
        device.graph_no,
        preRepairPlanDetail.content,
        preRepairPlanDetail.dept_name,
        preRepairPlanDetail.external_help_flag,
        code.name,
        preRepairPlanDetail.dutyPeople,
        preRepairPlanDetail.plan_Exec_People,
        preRepairPlanDetail.practice_Exec_People,
        preRepairPlanDetail.plan_estimate_finish_date,
        preRepairPlanDetail.plan_all_fee,
        preRepairPlanDetail.proc_estimate_finish_date,
        preRepairPlanDetail.proc_all_fee,
        case preRepairPlanDetail.proc_result when 'UNFINISHED' then '未完成'
                                             when 'FINISHED'   then '完成'
                                             when 'CANCEL'     then '取消'
                                             when 'SHIFT'      then '转移'end,
        preRepairPlan.plan_id,
        preRepairPlan.plan_All_fee,
        preRepairPlan.proc_All_fee,
        preRepairPlan.tooling_dev_flag
 from   t_prerepair_plan preRepairPlan,
 		( t_prerepair_plan_detail preRepairPlanDetail left join t_device_card device on preRepairPlanDetail.asset_id = device.id)
 		left join t_code_value code on preRepairPlanDetail.repair_Level = code.id 
where  preRepairPlan.id = preRepairPlanDetail.plan_id 
 
 
/*
	生成入库单明细报表的视图
	更新于chengding 2009/3/20
    添加了入库状态一列
*/

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareinbill_detail_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spareinbill_detail_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareinbill_detail_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareinbill_detail_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

create view t_spareinbill_detail_view
([ID],CODE,SPARE_NAME,IN_STATUS,MODEL,SPECIFICATION,CATEGORY,UNIT,
	NUMBER,UNIT_PRICE,TOTAL_PRICE,STOCKS_BEFORE_IN_OR_OUT,
	STOCKS_AFTER_IN_OR_OUT,COMMENT,INPEOPLE,CHECKPEOPLE,SPAREINBILL_NAME,
	SPAREINBILL_INDATE,spareInBillCode,spareInBillId,supplierName)
as
select 
	spareinbilldetail.ID,
	spareinbilldetail.CODE,
	spareinbilldetail.NAME,
        case  spareinbilldetail.IN_STATUS when 'INWAREHOUSED'  THEN '已入库' 
                                          when 'UNINWAREHOUSE'THEN '未入库'end ,
	spare.model_specs,
	spare.specification,
	codevaluecategory.name as category,
	codevalueunit.name as unit,
	spareinbilldetail.NUMBER,
	spareinbilldetail.UNIT_PRICE,
	spareinbilldetail.TOTAL_PRICE,
	spareinbilldetail.STOCKS_BEFORE_IN_OR_OUT,
	spareinbilldetail.STOCKS_AFTER_IN_OR_OUT,
	spareinbilldetail.COMMENT,
	inpeople.NAME as INPEOPLE,
	checkPeople.NAME as CHECKPEOPLE,
	spareinbill.NAME as spareinbillNAME,
	spareinbill.IN_DATE,
	spareinbill.CODE as spareinbillCODE,
	spareinbilldetail.IN_BILL_ID,
	supplier.NAME as supplierNAME
from (t_spareIn_bill_detail spareinbilldetail left join (t_spareIn_bill spareinbill left 

join t_users inpeople on spareinbill.IN_PEOPLE_ID = inpeople.id
     left join t_supplier supplier on spareinbill.SUPPLIER_ID = supplier.id left join 

t_users checkPeople on spareinbill.CHECK_PEOPLE_ID = checkPeople.id) 
     on spareinbilldetail.IN_BILL_ID = spareinbill.id)
     left join  (t_spare spare left join t_code_value codevaluecategory on 

codevaluecategory.id = spare.category
     left join t_code_value codevalueunit on codevalueunit.id = spare.unit) on 

spareinbilldetail.SPARE_ID = spare.id
where spareinbill.disabled=0

/*
from t_spareIn_bill spareinbill,t_spareIn_bill_detail spareinbilldetail,
     t_spare spare,t_users inpeople,t_code_value codevaluecategory,
     t_code_value codevalueunit,t_supplier supplier

where spareinbilldetail.SPARE_ID = spare.id 
AND spareinbill.id = spareinbilldetail.IN_BILL_ID
AND spareinbill.IN_PEOPLE_ID = inpeople.id 
AND spare.category = codevaluecategory.id
AND spare.unit = codevalueunit.id
AND spareinbill.SUPPLIER_ID = supplier.id
*/

/*
  出库单明细报表视图
	chengding 更新于2009/3/20
   添加了出库状态一列
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spare_outWareHouse_detail_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spare_outWareHouse_detail_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spare_outWareHouse_detail_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spare_outWareHouse_detail_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

create view t_spare_outWareHouse_detail_view
([ID],OUT_STATUS,OUT_BILL_CODE,OUT_BILL_DATE,OUT_BILL_PERSON,BORROWER,OUTBILLNAME,
	DEPT_NAME,CODE,NAME,MODEL,SPECIFICATION,CATE_NAME,
	UNIT,NUMBER,UNIT_PRICE,ALL_PRICE,STOCK_OUT,STOCK_IN,
	OUT_WARE_HOUSE_Bill_Id,OUT_WAREHOUSE,LOCATION_CODE,DETAIL_CATEGORY_NAME,
ORDER_NO,FACTORY_NAME,BORROWER_PEOPLE,DEVICE,BANZU,NEWOROLD,OUTWAREHOUSER,OUTWAREHOUSE_DATE)
as
select 	spareOutBillDetail.id,
                 case   spareOutBillDetail.STATUS 
                        when 'OUTWAREHOUSEED'  THEN '已出库' 
                        when 'UNOUTWAREHOUS'   THEN '未出库' end,
		spareOutBill.Out_CODE,
		spareOutBill.OUT_DATE,
		outbillPeople.name as outbillpeopleNAME,
		spareOutBill.BORROWER,
		spareOutBill.NAME,
		dept.name as deptNAME,
		spareOutBillDetail.code,
		spareOutBillDetail.name,
                spare.model_specs,
		spare.specification,
		codevaluecategory.name as category ,
		codevalueunit.name as unit,
		spareOutBillDetail.NUMBER,
        	spareOutBillDetail.UNIT_PRICE,
		spareOutBillDetail.TOTAL_PRICE,
		spareOutBillDetail.STOCKS_BEFORE_IN_OR_OUT,
        	spareOutBillDetail.STOCKS_AFTER_IN_OR_OUT,
       	        spareOutBillDetail.OUT_BILL_ID,
		warehouse.NAME,
		spareOutBillDetail.LOCATION_CODE,
                spareDetailType.name,
		spare.ORDER_NO,
		spare.FACTORY_STR,
		spareOutBillDetail.BORROWER_PEOPLE,
		spareOutBillDetail.SHEBEI,
		spareOutBillDetail.BANZU,
		CASE
		spareOutBillDetail.NEW_OR_OLD
		when 0 then '否'
		when 1 then '是' end,
		users.NAME,
		spareOutBillDetail.OUT_DATE
 		
from    (t_spareOut_bill spareOutBill left join 
	    (t_spareOut_bill_detail spareOutBillDetail left join 
	    (t_spare spare left join t_spare_type codevaluecategory on codevaluecategory.id = spare.category
	    left join t_code_value codevalueunit on codevalueunit.id = spare.unit) 
	    on spareOutBillDetail.SPARE_ID=spare.id)
	    on spareOutBillDetail.OUT_BILL_ID=spareOutBill.id)
	    left join t_department dept on spareOutBill.DEEPT_ID=dept.id
	    left join t_users outbillPeople on spareOutBill.OUT_PEOPLE_ID=outbillPeople.id
	    left join t_warehouse warehouse on spareOutBill.WAREHOUSE_ID=warehouse.id
	    left join t_spare_detail_type spareDetailType on spareDetailType.id=spare.SPARE_DETAIL_TYPE
	    left join t_users users on users.ID = spareOutBillDetail.OUT_PEOPLE_ID
where   spareOutBill.disabled = 0

 
     
	  
/*工装设备备件权限划分的insert语句
 xschen 08/11/13
*/	 
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_READ','工装设备备件查询',0,200)
/*
  工装设备备件出库权限划分的insert语句
  xschen 08/11/13
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_OUT_WAREHOUSE_READ','工装设备备件出库查询',0,201)
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_OUT_WAREHOUSE_WRITE','工装设备备件出库维护',0,202)
/*
  工装设备备件入库权限划分的insert语句
  xschen 08/11/13
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_IN_WAREHOUSE_READ','工装设备备件入库查询',0,203)
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_IN_WAREHOUSE_WRITE','工装设备备件入库维护',0,204)
/*
  工装设备备件盘点权限划分的insert语句
  xschen 08/11/13
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_INVENTORY_READ','工装设备备件盘点查询',0,205)
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_DEVICE_SPARE_INVENTORY_WRITE','工装设备备件盘点维护',0,206)
 

 /*
   设置采购单/入库单/出库单/盘点单是否已经发送了提醒  默认为未发送
 */
update t_purchase_bill set submit=0
update t_sparein_bill set submit=0
update t_spareout_bill set submit=0
update t_spare_inventory set submit=0

/*
  工装备件领用单权限划分的insert语句
  xschen  08/11/26
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_SPAREBORROW_BILL_READ','工装备件领用单查询',0,65)
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'TOOLING_SPAREBORROW_BILL_WRITE','工装备件领用单维护',0,66)
/*
  设备备件领用单权限划分的insert语句
  xschen  08/11/26
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'DEVICE_SPAREBORROW_BILL_READ','设备备件领用单查询',0,155)
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'DEVICE_SPAREBORROW_BILL_WRITE','设备备件领用单维护',0,156)


/**
   xschen 更新于08/12/14
*/
--更新季度计划明细中的备件采购明细种类的id
update t_tooling_quarter_plan_detail set TOOLING_CATEGORY_ID=3 where DETAIL_TYPE='SPARE_PURCHASE'And CATEGORY_NAME='夹具'
update t_tooling_quarter_plan_detail set TOOLING_CATEGORY_ID=2 where DETAIL_TYPE='SPARE_PURCHASE'And CATEGORY_NAME='工位器具'

--更新季度计划明细中的维修保养明细种类的id

update t_tooling_quarter_plan_detail set TOOLING_CATEGORY_ID=3 where DETAIL_TYPE='REPAIR_MAINTENANCE'And CATEGORY_NAME='夹具'
update t_tooling_quarter_plan_detail set TOOLING_CATEGORY_ID=2 where DETAIL_TYPE='REPAIR_MAINTENANCE'And CATEGORY_NAME='工位器具'

--更新季度计划明细中的技术改造明细种类的id

update t_tooling_quarter_plan_detail set TOOLING_CATEGORY_ID=3 where DETAIL_TYPE='TECH_ALTER'And CATEGORY_NAME='夹具'
update t_tooling_quarter_plan_detail set TOOLING_CATEGORY_ID=1 where DETAIL_TYPE='TECH_ALTER'And CATEGORY_NAME='模具'
/**
  向eventtype表中插入验收提醒
*/
insert into eventtype(eventid,eventname) values (1055,'验收提醒')
/*
	采购报表视图
	qcshen  10/11/18
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_toolingpurchase_contract_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_toolingpurchase_contract_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_toolingpurchase_contract_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_toolingpurchase_contract_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
create view t_toolingpurchase_contract_view
(ID,purchaseBillId,purchaseBillName,FACTORY_ID,graphNo,
name,detailType,department,BUSER_ID,purchaseDate,categoryName,
detailCategoryName,model,specification,unitprice,amount,calUnit
,allPrice,reqDeliveryDate,status,comment,CONSIGNEE_ADD,
CONSIGNEE_NAME,CONSIGNEE_TEL,CONSIGNEE_FAX,postCode,taxId,bank,
bankAccount,address,ACTUAL_DELIVERY_DATE,total,filialeName,supplierName,duty,billNo,orderNo)
as
select  PurchaseBillDetail.id,
	PurchaseBill.id ,
	PurchaseBill.name,
	supplier.name,
        PurchaseBillDetail.graph_No,
        PurchaseBillDetail.name,	
	case PurchaseBillDetail.detail_type  when 'TOOLING_MAKE' THEN '工装制作' 
                                       when 'SPARE_PURCHASE'     THEN '备件采购'
                                       when 'REPAIR_MAINTENANCE' THEN '维修保养'
                                       when 'TECH_ALTER'         THEN '技术改造'
                                       end detailType,			
	purchasebillDetail.DEPARTMENT,
	users.name,
	PurchaseBill.purchase_date,
	PurchaseBillDetail.category_name,
	PurchaseBillDetail.DETAIL_CATEGORY_NAME,
	PurchaseBillDetail.model,
	PurchaseBillDetail.specification,
	PurchaseBillDetail.unitprice/1.17,
	PurchaseBillDetail.amount,
	codeValue.name,
	PurchaseBillDetail.unitprice*PurchaseBillDetail.amount,
	PurchaseBillDetail.REQ_DELIVERY_DATE,
	PurchaseBillDetail.STATUS_VALUE,
	PurchaseBillDetail.COMMENT,
	purchaseBill.CONSIGNEE_ADD,
	purchaseBill.CONSIGNEE_NAME,
	purchaseBill.CONSIGNEE_TEL,
	purchaseBill.CONSIGNEE_FAX,
	filiale.POSTCODE,
	filiale.TAXID,
	filiale.BANK,
	filiale.BANKACCOUNT,
	filiale.ADDRESS,
	purchasebillDetail.REQ_DELIVERY_DATE,
	PurchaseBillDetail.unitprice*PurchaseBillDetail.amount/1.17,
	filiale.NAME,
	supplier2.name,
	PurchaseBillDetail.unitprice*PurchaseBillDetail.amount-PurchaseBillDetail.unitprice*PurchaseBillDetail.amount/1.17,
	PurchaseBill.BILL_NO,
	PurchaseBillDetail.ORDER_NO
from   t_purchasebill_detail purchasebillDetail
	left join t_purchase_bill purchaseBill on PurchaseBillDetail.PURCHASE_BILL_ID=purchaseBill.id
        left join t_users users on purchaseBill.buyer_ID = users.id
	left join t_filiale filiale on users.Filiale_ID=filiale.id
	left join t_supplier supplier on PurchaseBillDetail.FACTORY_ID=supplier.id
	left join t_code_value codeValue on PurchaseBillDetail.CAL_UNIT_ID=codeValue.id
	left join t_supplier supplier2 on purchaseBill.SUPPLIER_ID=supplier2.id
where  PurchaseBill.id = PurchaseBillDetail.PURCHASE_BILL_ID and PurchaseBill.disabled = 0
/*
  工装商务管理中采购单明细报表视图
  yli 08/12/15
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_purchase_bill_detail_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_purchase_bill_detail_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_purchase_bill_detail_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_purchase_bill_detail_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

/*
	申购单,申购汇总单明细报表视图
	qcshen 10/11/17
*/
drop view t_purchase_bill_detail_view;
create view t_purchase_bill_detail_view
([ID],PUR_DETAIL_TYPE,PUR_GRAPH_NO,PUR_NAME,PUT_CATEGORY_NAME,
PUR_DETAIL_CATEGORY_NAME,PUR_MODEL,PUR_SPECIFICATION,PUR_UNIT_PRICE,
PUR_AMOUNT,PUR_CALUNIT,PUR_TOTAL_PRICE,PUR_REQDATE,PUR_REQUEST_REASON,
PUR_COMMENT,PUR_STATUS,PUR_DEPT_NAME,PUR_BUYING_PERSON,PUR_SUBSCRIBE_DATE,
PURCHASE_BILL_ID,PUR_CATEGORY_ID,PUR_ORDERNO,PUR_DETAIL_CATEGORY,PUR_REQUIRE_DATE,
PUR_FACTORY,PUR_OWND_EQUIPMENT,PUR_EQUIPMENT_FACTORY,PUR_BUYER,PUR_STOCK)
as
select 
subscribeDetail.id,
case subscribeDetail.DETAIL_TYPE when 'TOOLING_MAKE'		then '工装制作'
				 when 'SPARE_PURCHASE'		then '备件采购'
				 when 'REPAIR_MAINTENANCE'	then '维修保养'
				 when 'TECH_ALTER'		then '技术改造'
				 end,
subscribeDetail.GRAPH_NO,
subscribeDetail.NAME,
subscribeDetail.CATEGORY_NAME,
subscribeDetail.DETAIL_CATEGORY_NAME,
subscribeDetail.MODEL,
subscribeDetail.specification,
subscribeDetail.UNITPRICE,
subscribeDetail.AMOUNT,
calUnit.name as calUnit,
subscribeDetail.TOTAL_PRICE,
subscribeDetail.require_Date,
subscribeDetail.ReqReason,
subscribeDetail.COMMENT,
case subscribeDetail.STATUS_ID
when 'NEW' then '新建'
when 'PURCHASEED' then '已采购'
when 'COLLECTED' then '已汇总'
when 'INSPECTING' then '入库中/验收中'
when 'INSPECTED' then '已入库/已验收'
 end as status,
dept.name as dept_Name,
buyingPerson.name as buyPerson,
subscribe.SUBSCRIBE_DATE,
subscribeDetail.SUBSCRIBE_ID,
spareType.name as category,
subscribeDetail.ORDER_NO as orderNo,
spareDetailType.NAME as detailCategory,
subscribeDetail.require_Date,
supplier.name as factory,
subscribeDetail.ORDER_EQUIPMENT,
subscribeDetail.EQUIPMENT_FACTORY,
users.name,
subscribeDetail.STOCKED
from t_subscribe_detail subscribeDetail 
left join 
( t_subscribe subscribe left join t_department dept on subscribe.DEPT_ID = dept.id
			left join t_users buyingPerson on subscribe.buying_Person_ID = buyingPerson.id
)
on subscribeDetail.SUBSCRIBE_ID = subscribe.ID
left join t_code_value calUnit on subscribeDetail.cal_unit_id = calUnit.id
left join t_spare_type spareType on subscribeDetail.CATEGORY_ID=spareType.id
left join t_spare_detail_type spareDetailType on subscribeDetail.DETAIL_CATEGORY_ID=spareDetailType.id
left join t_supplier supplier on subscribeDetail.FACTORY_ID=supplier.id
left join t_users users on subscribeDetail.BUYER_ID=users.id
WHERE subscribe.disabled=0

/*
	工装商务管理中验收单明细视图
	yli 08/12/24
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_accept_bill_detail_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_accept_bill_detail_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_accept_bill_detail_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_accept_bill_detail_view]
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

create view t_accept_bill_detail_view
(
  [ID],detailType,graphNo,name,categoryName,detailCategoryName,model,specification,unitPrice,amount,
  calUnit,totalPrice,result,memo,acceptDate,department,acceptPeople,supplier,acceptId,applyDept,establish
)
as
select 
acceptBillDetail.id,
case acceptBillDetail.DETAIL_TYPE when 'TOOLING_MAKE'		then '工装制作'
				 when 'SPARE_PURCHASE'		then '备件采购'
				 when 'REPAIR_MAINTENANCE'	then '维修保养'
				 when 'TECH_ALTER'		then '技术改造'
				 end,
acceptBillDetail.GRAPH_NO,
acceptBillDetail.NAME,
acceptBillDetail.CATEGORY_NAME,
acceptBillDetail.DETAIL_CATEGORY_NAME,
acceptBillDetail.MODEL,
acceptBillDetail.SPECIFICATION,
acceptBillDetail.UNITPRICE,
acceptBillDetail.AMOUNT,
calUnit.name as calUnit,
acceptBillDetail.TOTAL_PRICE,
acceptBillDetail.RESULT_VALUE as result,
acceptBillDetail.MEMO,
acceptBill.accept_Date,
dept.name as department,
acceptPeople.name as acceptPeople,
supplier.name as supplier,
acceptBill.id as acceptId,
applyDept.name as applyDept,
acceptBillDetail.ESTABLISH as establish
from t_acceptBill_Detail acceptBillDetail
left join
( t_accept_bill acceptBill left join t_department dept on acceptBill.DEPARTMENT_ID = dept.id
			   left join t_users acceptPeople on acceptBill.acceptPeople_ID = acceptPeople.id
			   left join t_supplier supplier on acceptBill.SUPPLIER_ID = supplier.id
)
on acceptBillDetail.ACCEPT_BILL_ID = acceptBill.id
left join t_code_value calUnit on acceptBillDetail.cal_unit_id = calUnit.id
left join t_department applyDept on acceptBillDetail.DEPARTMENT_ID = applyDept.id
WHERE acceptBill.disabled=0



/*
  库位权限的insert语句
  chengding 04/11/2009
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'LOCATION_READ','库位查询',0,207)
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'LOCATION_WRITE','库位维护',0,208)



/*
  收发存日报表
  xschen 2009/04/17
*/
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareInAndOut_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spareInAndOut_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareInAndOut_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareInAndOut_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO
create view t_spareInAndOut_view 
([ID],spareNo,spareName,model,specification,unit,warehouseName,regionalName,locationCode,unitPrice,beforeStocks,inStocks,outStocks,afterStocks,totalPrice,createTime,orgId,deptId,toolingDevFlag,LOCATION_CODE,LOCATION_ID)
 as 
select spareInAndOut.id,
 spareInAndOut.spareNo,
 spareInAndOut.spareName,
 spareInAndOut.model,
 spareInAndOut.specification,
 spareInAndOut.unit,
 spareInAndOut.WAREHOUSE_NAME,
 spareInAndOut.REGIONAL_NAME,
 spareInAndOut.LOCATION_CODE,
 spareInAndOut.unitPrice,
 spareInAndOut.beforeStocks,
 spareInAndOut.inStocks,
 spareInAndOut.outStocks,
 spareInAndOut.afterStocks,
 spareInAndOut.totalPrice,
 spareInAndOut.createTime,
 spareInAndOut.orgId,
 dept.name as department,
 case spareInAndOut.toolingDevFlag when 'TOOLING' THEN '工装'
                                   when 'DEVICE' THEN '设备'
end,LOCATION_CODE,
LOCATION_ID
 FROM t_spareInAndOut spareInAndOut 
left join t_department dept on spareInAndOut.deptId = dept.id 
GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

/*
  收发存月报表
  xschen  2009/04/17  
  zbzhang 与2009/04/30修改
  t_spareInAndOut_month1_view去掉了beforeStocks和afterStocks
  t_spareInAndOut_month2_view修改了beforeStocks和afterStocks的取值
*/ 

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareInAndOut_month1_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareInAndOut_month1_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO

create view t_spareInAndOut_month1_view(ID,spareNo,inStocks,outStocks,createTime,MONTH)
   as
     select max(t.id),
     t.spareNo as spareNo,
     sum(t.inStocks) as inStocks,
     sum(t.outStocks) as outStocks,   
     max(t.createTime) as createTime
    ,convert(char(7),T.createTime,20) AS MONTH,
     min(t.id)
     from  t_spareInAndOut t 
     group by t.spareNo,convert(char(7),T.createTime,20)


GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareInAndOut_month2_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareInAndOut_month2_view]
GO

SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO



if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareInAndOut_month2_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareInAndOut_month2_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO 

create view t_spareInAndOut_month2_view(ID,spareNo,spareName,modelSpecs,unitPrice,unit,beforeStocks,inStocks,outStocks,afterStocks,totalPrice,createTime,MONTH,toolingDevFlag)
   as
     select t1.id,
     t1.spareNo as spareNo,
     t2.name as spareName,
     t2.model_specs as modelSpecs,
     t2.unit_price as unitPrice,
     codeValue.name as unit,
     t2.previousStocks  as beforeStocks,
     t1.inStocks as inStocks,
     t1.outStocks as outStocks,
     t2.stocks as afterStocks,
     t2.stocks*t2.unit_price  as totalPrice,
     t1.createTime as createTime
     ,t1.MONTH AS MONTH,

     case t2.tooling_dev_flag when 'TOOLING' THEN '工装'
                                   when 'DEVICE' THEN '设备'end as toolingDevFlag
     from t_spareInAndOut_month1_view t1,t_spare t2 left join t_code_value codeValue on t2.unit=codeValue.id where t1.spareNo=t2.spare_no

GO
SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO






/*
    清仓盘点报表
*/ 

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareClear1_Inventory_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spareClear1_Inventory_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareClear1_Inventory_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareClear1_Inventory_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO   
create view t_spareClear1_Inventory_view(ID,spareNo,beforeStocks,inStocks,outStocks,afterStocks,createTime,MONTH,deptName,deptId,locationCode,minId)
   as
     select max(t.id),
     t.spareNo as spareNo,
     min(beforeStocks)  as beforeStocks,
     sum(t.inStocks) as inStocks,
     sum(t.outStocks) as outStocks,
    ((sum(t.inStocks)+ min(beforeStocks))- sum(t.outStocks)) as afterStocks,
     max(t.createTime) as createTime
     ,convert(char(7),T.createTime,20) AS MONTH,
     dept.name as deptName,
     dept.id as deptId, 
     location.code as locationCode,
     min(t.id)
     from  t_spareInAndOut t
                             left join t_location location on t.LOCATION_ID = location.id
                             left join t_department dept on t.deptId = dept.id 
     group by t.spareNo,dept.name,dept.id,location.code,convert(char(7),T.createTime,20)
     



go

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareClear2_Inventory_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spareClear2_Inventory_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareClear2_Inventory_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareClear2_Inventory_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO  
create view t_spareClear2_Inventory_view(ID,spareNo,spareName,unitPrice,beforeStocks,inStocks,outStocks,afterStocks,createTime,MONTH,deptName,deptId,locationCode,tooling_dev_flag,totalPrice,modelSpecs,unit)
   as
      select t.id,
      t.spareNo as spareNo,
      t1.name as spareName,
      t1.unit_price as unitPrice,
     (select beforeStocks from t_spareInAndOut where id=t.minId)  as beforeStocks,
      t.inStocks as inStocks,
      t.outStocks as outStocks,
     (select afterStocks from t_spareInAndOut where id=t.Id) as afterStocks,
      t.createTime as createTime,
      t.MONTH AS MONTH,
      t.deptName as deptName,
      t.deptId as deptId,
      t.locationCode as locationCode,
      case t1.tooling_dev_flag when 'TOOLING' THEN '工装'
                                   when 'DEVICE' THEN '设备'
     end,
     (select afterStocks from t_spareInAndOut where id=t.Id)*t1.unit_price  as totalPrice,
     t1.model_specs as modelSpecs,
     t2.name as unit
     from  t_spareClear1_Inventory_view t,t_spare t1
     left join t_code_value t2 on t1.unit=t2.id where t.spareNo=t1.spare_no
/*
	清仓盘点的insert语句
	chengding 04/22/2009
*/
insert  into t_roles (VERSION,CODE,NAME,ADVANCED,SORT_IDX)values (1,'InventoryAndCleanHouse_READ','清仓盘点报表查询',0,218)





/*
   采购单月度计划落实情况报表 废弃不用 zzb 2011-03-23
*/ 

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_mid_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_mid_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_mid_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_mid_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO   
--创建视图语句
   
create view t_mid_view(dept,detail_id,createDate,category_name,price,amount,status)
as select
b.department,
b.id,
b.created_time,
t.name,
b.total_price,
b.amount,
b.status_id
from t_subscribe_detail b
left join t_spare_type t on t.id = b.category_id  



go


/*
   一级出库单月度计划报表 zzb 2011-03-23
*/ 

if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareOut_bill_month_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spareOut_bill_month_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spareOut_bill_month_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spareOut_bill_month_view]
GO
SET QUOTED_IDENTIFIER ON 
GO
SET ANSI_NULLS ON 
GO   
--创建视图语句
create view t_spareOut_bill_month_view
(id,category,dept,detailId,createDate,amount,price,num,totalPrice,bnum,btotalPrice,month,storageGrade)
as
select 
sob.id,
tst.name,
d.name,
tsb.id,
sob.out_date,
tsb.number,
tsb.unit_price,
tsb.number,
tsb.unit_price,
tsb.number,
tsb.unit_price,
sob.out_date,
sob.storage_grade
from  t_spareOut_bill_detail tsb
join t_spareOut_bill sob  on tsb.out_bill_id=sob.id
join t_department d on sob.deept_id=d.id
join t_spare ts on ts.id=tsb.spare_id
join t_spare_type tst on tst.id=ts.category
where sob.storage_grade=208



go
