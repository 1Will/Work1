--1、申购汇总单打印视图

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

CREATE VIEW dbo.t_purchase_bill_detail_view
AS
SELECT subscribeDetail.ID, 
      CASE subscribeDetail.DETAIL_TYPE WHEN 'TOOLING_MAKE' THEN '工装制作' WHEN
       'SPARE_PURCHASE' THEN '备件采购' WHEN 'REPAIR_MAINTENANCE' THEN '维修保养'
       WHEN 'TECH_ALTER' THEN '技术改造' END AS PUR_DETAIL_TYPE, 
      subscribeDetail.GRAPH_NO AS PUR_GRAPH_NO, 
      subscribeDetail.NAME AS PUR_NAME, 
      subscribeDetail.CATEGORY_NAME AS PUT_CATEGORY_NAME, 
      subscribeDetail.DETAIL_CATEGORY_NAME AS PUR_DETAIL_CATEGORY_NAME, 
      subscribeDetail.MODEL AS PUR_MODEL, 
      subscribeDetail.specification AS PUR_SPECIFICATION, 
      subscribeDetail.UNITPRICE AS PUR_UNIT_PRICE, 
      subscribeDetail.AMOUNT AS PUR_AMOUNT, calUnit.name AS PUR_CALUNIT, 
      subscribeDetail.TOTAL_PRICE AS PUR_TOTAL_PRICE, 
      subscribeDetail.require_Date AS PUR_REQDATE, 
      subscribeDetail.ReqReason AS PUR_REQUEST_REASON, 
      subscribeDetail.COMMENT AS PUR_COMMENT, 
      CASE subscribeDetail.STATUS_ID WHEN 'NEW' THEN '新建' WHEN 'PURCHASEED' THEN
       '已采购' WHEN 'NOTPURCHASEED' THEN '不采购' WHEN 'COLLECTED' THEN '已汇总'
       WHEN 'INSPECTING' THEN '入库中/验收中' WHEN 'INSPECTED' THEN '已入库/已验收'
       END AS PUR_STATUS, dept.NAME AS PUR_DEPT_NAME, dept.id AS PUR_DEPT_ID, 
      buyingPerson.NAME AS PUR_BUYING_PERSON, 
      subscribe.SUBSCRIBE_DATE AS PUR_SUBSCRIBE_DATE, 
      subscribeDetail.SUBSCRIBE_ID AS PURCHASE_BILL_ID, 
      spareType.name AS PUR_CATEGORY_ID, 
      subscribeDetail.ORDER_NO AS PUR_ORDERNO, 
      spareDetailType.name AS PUR_DETAIL_CATEGORY, 
      subscribeDetail.require_Date AS PUR_REQUIRE_DATE, 
      supplier.name AS PUR_FACTORY, 
      subscribeDetail.ORDER_EQUIPMENT AS PUR_OWND_EQUIPMENT, 
      subscribeDetail.EQU_FACTORY_STR AS PUR_EQUIPMENT_FACTORY, 
      users.NAME AS PUR_BUYER, subscribeDetail.STOCKED AS PUR_STOCK, 
      spare.STOCKS AS PUR_STOCKS, 
      subscribeDetail.BUYE_AMOUNT AS PUR_BUYE_AMOUNT, 
      subscribeDetail.ARRIVAL_AMOUNT AS PUR_ARRIVAL_AMOUNT, 
      subscribeDetail.ARRIVAL_Date AS PUR_ARRIVAL_DATE, 
      subscribeDetail.PURCHASE_Date AS PUR_PURCHASE_DATE, 
      supplier.name AS PUR_SUPPLIER, 
      subscribe.ID as subscribeID,
      subscribeDetail.supplier_Name AS PUR_SUPPLIER_NAME,
      subscribeDetail.sssbzsl AS sssbzsl,
      subscribeDetail.shpc AS shpc,
      subscribeDetail.beizhu AS beizhu
FROM dbo.t_subscribe_detail subscribeDetail LEFT OUTER JOIN
      dbo.t_subscribe subscribe LEFT OUTER JOIN
      dbo.t_department dept ON subscribe.DEPT_ID = dept.id LEFT OUTER JOIN
      dbo.t_users buyingPerson ON subscribe.buying_Person_ID = buyingPerson.ID ON 
      subscribeDetail.SUBSCRIBE_ID = subscribe.ID LEFT OUTER JOIN
      dbo.t_code_value calUnit ON 
      subscribeDetail.CAL_UNIT_ID = calUnit.id LEFT OUTER JOIN
      dbo.t_spare_type spareType ON 
      subscribeDetail.CATEGORY_ID = spareType.id LEFT OUTER JOIN
      dbo.t_spare_detail_type spareDetailType ON 
      subscribeDetail.DETAIL_CATEGORY_ID = spareDetailType.id LEFT OUTER JOIN
      dbo.t_supplier supplier ON 
      subscribeDetail.FACTORY_ID = supplier.id LEFT OUTER JOIN
      dbo.t_users users ON subscribeDetail.BUYER_ID = users.ID LEFT OUTER JOIN
      dbo.t_spare spare ON subscribeDetail.SPARE_ID = spare.id
WHERE (subscribe.disabled = 0)

--2、备件库明细台帐打印视图
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spare_location_view]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)
drop table [dbo].[t_spare_location_view]
GO
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[t_spare_location_view]') and OBJECTPROPERTY(id, N'IsView') = 1)
drop view [dbo].[t_spare_location_view]
GO

SET QUOTED_IDENTIFIER OFF 
GO
SET ANSI_NULLS ON 
GO
create view t_spare_location_view([ID],spare_No,spare_Name,spare_EnName,unit,model_Specs,warehouseId,category,detail_Type,dept,unit_Price,
stocks,total_Price,location_Code,warehouseName,regionalName,regionalId,tooling_dev_flag)
as
select spareLocation.id as id,spare.spare_no as spareNo,spare.name as spareName,spare.en_name as spareEnName,unit.name as unit,
       spare.model_specs as modelSpecs,spareLocation.warehouseId as warehouseId,category.name as category,detailType.name as detailType,
       dept.name as dept,spareLocation.unitPrice as unitPrice,spareLocation.stocks as stocks,
       spareLocation.unitPrice*spareLocation.stocks as totalPrice,
       location.code as locationCode,warehouse.name as warehouseName,regional.name as regionalName,spareLocation.regionalId as regionalId,
               
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
                                            
--3、出库单查询页导出报表
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
ORDER_NO,FACTORY_NAME,BORROWER_PEOPLE,DEVICE,BANZU,NEWOROLD,OUTWAREHOUSER,OUTWAREHOUSE_DATE,COMMENT)
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
		spareOutBillDetail.OUT_DATE,
		spareOutBillDetail.COMMENT
 		
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

--4、入库单查询页导出报表

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
	SPAREINBILL_INDATE,spareInBillCode,spareInBillId,supplierName,DEPT_NAME,warehouse,LOCATION_CODE)
as

SELECT spareinbilldetail.ID, spareinbilldetail.CODE, 
      spareinbilldetail.NAME AS SPARE_NAME, 
      CASE spareinbilldetail.IN_STATUS WHEN 'INWAREHOUSED' THEN '已入库' WHEN 'UNINWAREHOUSE'
       THEN '未入库' END AS IN_STATUS, spare.model_specs AS MODEL, 
      spare.specification, codevaluecategory.name AS CATEGORY, 
      codevalueunit.name AS UNIT, spareinbilldetailview0.number, 
      spareinbilldetail.TAX_PRICE, 
      spareinbilldetailview0.number * spareinbilldetail.TAX_PRICE AS TOTAL_PRICE, 
      spareinbilldetail.STOCKS_BEFORE_IN_OR_OUT, 
      spareinbilldetail.STOCKS_AFTER_IN_OR_OUT, spareinbilldetail.COMMENT, 
      inpeople.NAME AS INPEOPLE, checkPeople.NAME AS CHECKPEOPLE, 
      spareinbill.NAME AS SPAREINBILL_NAME, 
      spareinbill.IN_DATE AS SPAREINBILL_INDATE, spareinbill.CODE AS spareInBillCode, 
      spareinbilldetail.IN_BILL_ID AS spareInBillId, supplier.name AS supplierName, 
      spareinbilldetail.dept_name, warehouse.name,spareinbilldetail.LOCATION_CODE
FROM dbo.t_spareIn_bill_detail spareinbilldetail INNER JOIN
      dbo.t_spareinbill_detail_view0 spareinbilldetailview0 ON 
      spareinbilldetail.ID = spareinbilldetailview0.id LEFT OUTER JOIN
      dbo.t_spareIn_bill spareinbill LEFT OUTER JOIN
      dbo.t_users inpeople ON spareinbill.IN_PEOPLE_ID = inpeople.ID LEFT OUTER JOIN
      dbo.t_supplier supplier ON spareinbill.SUPPLIER_ID = supplier.id LEFT OUTER JOIN
      dbo.t_users checkPeople ON spareinbill.CHECK_PEOPLE_ID = checkPeople.ID ON 
      spareinbilldetail.IN_BILL_ID = spareinbill.ID LEFT OUTER JOIN
      dbo.t_spare spare LEFT OUTER JOIN
      dbo.t_code_value codevaluecategory ON 
      codevaluecategory.id = spare.category LEFT OUTER JOIN
      dbo.t_code_value codevalueunit ON codevalueunit.id = spare.unit ON 
      spareinbilldetail.SPARE_ID = spare.id left outer join t_warehouse warehouse on spareinbill.WAREHOUSE_ID=warehouse.id
WHERE (spareinbill.disabled = 0)
