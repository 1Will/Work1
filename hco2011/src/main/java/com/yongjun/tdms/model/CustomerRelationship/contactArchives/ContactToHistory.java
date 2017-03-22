/*    */ package com.yongjun.tdms.model.CustomerRelationship.contactArchives;
/*    */ 
		import com.yongjun.pluto.model.BaseInfoEntity;
/*    */ 
/*    */ public class ContactToHistory extends BaseInfoEntity
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   private ContactArchives contactArchivesId;
           private String conment;
/*    */   
		@Override
		public boolean equals(Object arg0) {
			if (arg0 == this) {
				       return true;
				     }
				   if (!(arg0 instanceof ContactsJobResume)) {
				       return false;
				     }
				     return false;
		}
		public int hashCode() {
			// TODO Auto-generated method stub
			return 0;
		}
		public ContactArchives getContactArchivesId() {
			return contactArchivesId;
		}
		public void setContactArchivesId(ContactArchives contactArchivesId) {
			this.contactArchivesId = contactArchivesId;
		}
		public String getConment() {
			return conment;
		}
		public void setConment(String conment) {
			this.conment = conment;
		}
		
		
		

/*    */ }

/* Location:           E:\crm2010\110\crm2009\WEB-INF\classes\
 * Qualified Name:     com.yongjun.tdms.model.CustomerRelationship.stepInfo.StepInfo
 * JD-Core Version:    0.6.2
 */