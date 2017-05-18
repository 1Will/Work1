package souvc.resp;

/** 
 * 鍥炬枃model 
 */  
public class Article {  
    // 鍥炬枃娑堟伅鍚嶇О  
    private String Title;  
    // 鍥炬枃娑堟伅鎻忚堪  
    private String Description;  
    // 鍥剧墖閾炬帴锛屾敮鎸丣PG銆丳NG鏍煎紡锛岃緝濂界殑鏁堟灉涓哄ぇ鍥�40*320锛屽皬鍥�0*80锛岄檺鍒跺浘鐗囬摼鎺ョ殑鍩熷悕闇�涓庡紑鍙戣�濉啓鐨勫熀鏈祫鏂欎腑鐨刄rl涓�嚧  
    private String PicUrl;  
    // 鐐瑰嚮鍥炬枃娑堟伅璺宠浆閾炬帴  
    private String Url;  
  
    public String getTitle() {  
        return Title;  
    }  
  
    public void setTitle(String title) {  
        Title = title;  
    }  
  
    public String getDescription() {  
        return null == Description ? "" : Description;  
    }  
  
    public void setDescription(String description) {  
        Description = description;  
    }  
  
    public String getPicUrl() {  
        return null == PicUrl ? "" : PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
  
    public String getUrl() {  
        return null == Url ? "" : Url;  
    }  
  
    public void setUrl(String url) {  
        Url = url;  
    }  
  
}  
