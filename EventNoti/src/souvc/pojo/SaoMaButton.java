package souvc.pojo;

/** 
 * 鏅�鎸夐挳锛堝瓙鎸夐挳锛�
 */  
public class SaoMaButton extends Button {  
    private String type;  
    private String key;  
    private Button[] sub_button;
  
    public String getType() {  
        return type;  
    }  
  
    public void setType(String type) {  
        this.type = type;  
    }  
  
    public String getKey() {  
        return key;  
    }  
  
    public void setKey(String key) {  
        this.key = key;  
    }  
} 