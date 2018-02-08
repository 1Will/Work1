--更新采购项
-- 判断要创建的存储过程名是否存在
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[updPurch]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
-- 删除存储过程
drop procedure [dbo].[updPurch]
GO

DECLARE @SubCount int		--申购单采购数
DECLARE @SubCollCount int	--汇总单采购数
DECLARE @totalPriceSub numeric	--申购单总价
DECLARE @totalPriceCol numeric	--汇总单总价
SET @PointerPrev = 1
WHILE (@PointerPrev < LEN(@purchIds))
BEGIN 
Set @PointerCurr= CharIndex(',',@purchIds,@PointerPrev)
if(@PointerCurr>0) 
	Begin 
		set @TId=cast(SUBSTRING(@purchIds,@PointerPrev,@PointerCurr-@PointerPrev) as numeric)
		select @SubId = SUBSCRIBE_ID,@SubColId = SUBSCRIBE_COLLECT_BILL_ID from t_subscribe_detail where id = @TID
--申购单采购数
		select @SubCount = count(*) from t_purchasebill_detail where Subscribe_Detail_id in (select ID from t_subscribe_detail where SUBSCRIBE_ID = @SubId)
--汇总单采购数
		select @SubCollCount = count(*) from t_purchasebill_detail where Subscribe_Detail_id in (select ID from t_subscribe_detail where SUBSCRIBE_COLLECT_BILL_ID = @SubColId)
--申购单的总价
		select @totalPriceSub=sum(TOTAL_PRICE) from t_subscribe_detail where SUBSCRIBE_ID = @SubId
--汇总单的总价		
		select @totalPriceCol=sum(TOTAL_PRICE) from t_subscribe_detail where SUBSCRIBE_COLLECT_BILL_ID=@SubColId
--统计申购单中的采购项、总价
		update t_subscribe set PUR_NUM = @SubCount,TOTAL_PRICE=@totalPriceSub where id = @SubId
--统计汇总单中的采购项、总价
		update t_subscribe_collect_bill set PUR_NUM = @SubCollCount,TOTAL_MONEY=@totalPriceCol where id = @SubColId
		set @pointerPrev = @pointerCurr+1
		set @SubId=-1
		set @SubColId=-1
		set @totalPriceSub=0
		set @totalPriceCol=0
        End 
else 
	Break
End
GO





--更新入库项
-- 判断要创建的存储过程名是否存在
if exists (select * from dbo.sysobjects where id = object_id(N'[dbo].[updInbill]') and OBJECTPROPERTY(id, N'IsProcedure') = 1)
-- 删除存储过程
drop procedure [dbo].[updInbill]
GO


CREATE PROCEDURE updInbill
@inBillIds nvarchar(1000)	--采明细ID
AS
DECLARE @PointerPrev int
DECLARE @PointerCurr int
DECLARE @TId numeric
DECLARE @SubId numeric		--申购单ID
DECLARE @SubColId numeric	--汇总单ID
DECLARE @PurchId numeric	--采购单ID
DECLARE @SubCount int		--申购单入库数
DECLARE @SubCollCount int	--汇总单入库数
DECLARE @PurCount int		--采购单入库数
SET @PointerPrev = 1
WHILE (@PointerPrev < LEN(@inBillIds))
BEGIN 
Set @PointerCurr= CharIndex(',',@inBillIds,@PointerPrev)
if(@PointerCurr>0) 
	Begin 
		set @TId=cast(SUBSTRING(@inBillIds,@PointerPrev,@PointerCurr-@PointerPrev) as numeric)
		
		select @SubId = SUBSCRIBE_ID,@SubColId = SUBSCRIBE_COLLECT_BILL_ID from t_subscribe_detail where id =(
			select Subscribe_Detail_id from t_purchasebill_detail where id  = @TId)
		select @PurchId = PURCHASE_BILL_ID from t_purchasebill_detail where id  = @TId
		print @SubId
		print @PurchId
--申购单入库数
		select @SubCount = count(distinct(PO_DETAIL_ID)) from t_spareIn_bill_detail where PO_DETAIL_ID in (
			select [ID] from t_purchasebill_detail where Subscribe_Detail_id in (
			select [ID] from t_subscribe_detail where SUBSCRIBE_ID = @SubId))
--汇总单入库数
		select @SubCollCount = count(distinct(PO_DETAIL_ID)) from t_spareIn_bill_detail where PO_DETAIL_ID in (
			select [ID] from t_purchasebill_detail where Subscribe_Detail_id in (
			select [ID] from t_subscribe_detail where SUBSCRIBE_COLLECT_BILL_ID =@SubColId))
--采购单入库数
		select @PurCount = count(distinct(PO_DETAIL_ID)) from t_spareIn_bill_detail where PO_DETAIL_ID in (select [ID] from t_purchasebill_detail where PURCHASE_BILL_ID =@PurchId and AMOUNT=ARRIVAL_AMOUNT)
		print  @PurCount
--统计申购单中的入库数
		update t_subscribe set INS_NUM = @SubCount where id = @SubId
--统计汇总单中的入库数
		update t_subscribe_collect_bill set INS_NUM = @SubCollCount where id = @SubColId
--统计采购单中的入库数
		update t_purchase_bill set INS_NUM = @PurCount where id = @PurchId
		set @pointerPrev = @pointerCurr+1
		set @SubId=-1
		set @SubColId=-1
		set @PurchId=-1
        End 
else 
	Break 
End
GO 