package com.yongjun.tdms.service.report.custcontact;

import com.yongjun.tdms.model.report.custcontact.CustContactReport;
import java.util.List;

public abstract interface CustContactReportManager
{
  public abstract CustContactReport loadCustContactReport(Long paramLong);

  public abstract List<CustContactReport> loadAllCustContactReport();

  public abstract List<CustContactReport> loadAllCustContactReport(Long[] paramArrayOfLong);
}

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.service.report.custcontact.CustContactReportManager
 * JD-Core Version:    0.6.2
 */