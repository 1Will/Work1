   function validDate(date,alertStr){
        if (date=="")
           return true;
		var pattern = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/;
   		if(!pattern.test(date)){
   		    alert(alertStr);
            return false;
        }
        
        if (checkDate(date)){
            alert(alertStr);
            return false;
        }
		return true;
	}
    function validDateTime(datetime,alertStr){
        if (datetime=="")
           return true;
		var pattern = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}$/;
   		if(!pattern.test(datetime)){
   		    alert(alertStr);
            return false;
        }
        
        if (checkDateTime(datetime)){
            alert(alertStr);
            return false;
        }
        
		return true;
	}
	
	function validDateForDateRanger(date_start,date_end,alertStr){
		var pattern = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/;
   		if(!pattern.test(date_start)){
   		    alert(alertStr);
            return false;
        }else{
            if (!pattern.test(date_end)){
                alert(alertStr);
                return false;
            }
        }
        
        if (checkDate(date_start) || checkDate(date_end)){
            alert(alertStr);
            return false;
        }
        
        if (date_start>date_end){
            alert(alertStr);
            return false;
        }
        
		return true;
	}
	function validDateTimeForDateRanger(datetime_start,datetime_end,alertStr){
		var pattern = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2} [0-9]{2}:[0-9]{2}$/;
   		if(!pattern.test(datetime_start) && datetime_start!=""){
   		    alert(alertStr);
            return false;
        }else{
            if(!pattern.test(datetime_end) && datetime_end!=""){
   		        alert(alertStr);
                return false;
            }
        }
        
        if (checkDateTime(datetime_start) || checkDateTime(datetime_end)){
            alert(alertStr);
            return false;
        }
        if (datetime_start!="" && datetime_end!=""){
           if (datetime_start>datetime_end){
              alert(alertStr);
              return false;
           }
        }
		return true;
	}
	
	function checkDate(dateStr){
	  var pattern = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	  var r = dateStr.match(pattern); 
        if(r==null)return false; 
        var d= new Date(r[1], r[3]-1,r[4]); 
        var newStr=d.getFullYear()+r[2];
        
        if (((d.getMonth()+1)+"").length==1)
            newStr+="0"+(d.getMonth()+1)+r[2];
        else
            newStr+=(d.getMonth()+1)+r[2];
        
        if (((d.getDate())+"").length==1)
            newStr+="0"+d.getDate();
        else
            newStr+=d.getDate();
            
        if (newStr!=dateStr){
            return true;
        }
        return false;
	}
	
	function checkDateTime(dateTimeStr){
	    var patternD = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/; 
        var r = dateTimeStr.match(patternD); 
        if(r==null)return false;         
        var d= new Date(r[1], r[3]-1,r[4],r[5],r[6]); 
        var newStr=d.getFullYear()+r[2];
        if (((d.getMonth()+1)+"").length==1)
            newStr+="0"+(d.getMonth()+1)+r[2];
        else
            newStr+=(d.getMonth()+1)+r[2];
        
        if (((d.getDate())+"").length==1)
            newStr+="0"+d.getDate()+" ";
        else
            newStr+=d.getDate()+" ";
            
        if (((d.getHours())+"").length==1)
            newStr+="0"+d.getHours()+":";
        else
            newStr+=d.getHours()+":";
            
        if (((d.getMinutes())+"").length==1)
            newStr+="0"+d.getMinutes();
        else
            newStr+=d.getMinutes();
        
        if (newStr!=dateTimeStr){
            return true;//??
        }
        return false;
	}
	
	function compareDateTime(minDateTime,morDateTime){
	    var pattern = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2})$/;  
        var minr = minDateTime.match(pattern); 
        var morr = morDateTime.match(pattern); 
        if (morr==null) return false;
        var mind= new Date(minr[1], minr[3]-1,minr[4],minr[5],minr[6]); 
        var mord= new Date(morr[1], morr[3]-1,morr[4],morr[5],morr[6]); 
        if (minr>morr) 
            return true;
         else
            return false;
	}
	
	function compareDate(minDateTime,morDateTime){
	    var pattern = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
        var minr = minDateTime.match(pattern); 
        var morr = morDateTime.match(pattern); 
        if (morr==null) return false;
        var mind= new Date(minr[1], minr[3]-1,minr[4],minr[5],minr[6]); 
        var mord= new Date(morr[1], morr[3]-1,morr[4],morr[5],morr[6]); 
        if (minr>morr) 
            return true;
         else
            return false;
	}
	
	function isTheDayOfterTomoro(dateStr){
	    var pkStr = formartDateTime(dateStr);
	    var patternD = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	    var r = pkStr.match(patternD); 
        if(r==null)return false; 
        var d= new Date(r[1], r[3]-1,r[4]); 
	    var a=new Date();   
        a.setUTCDate((a.getUTCDate()+2));//??   
        var gd = Date.UTC(a.getYear(),a.getMonth(),a.getDate(),0,0);
        var dd = Date.UTC(d.getYear(),d.getMonth(),d.getDate(),0,0);
        if (dd>=gd){ 
            return true;
        }else{
            return false;
        }
	}
	
	function isTomoro(dateStr){//??????????????
	    var pkStr = formartDateTime(dateStr);
	    var patternD = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	    var r = pkStr.match(patternD); 
        if(r==null)return false; 
        var d= new Date(r[1], r[3]-1,r[4]); 
	    var a=new Date();   
        a.setUTCDate((a.getUTCDate()+1));//??   
        var gd = Date.UTC(a.getYear(),a.getMonth(),a.getDate(),0,0);
        var dd = Date.UTC(d.getYear(),d.getMonth(),d.getDate(),0,0);
        if (dd!=gd){ 
            return true;
        }else{
            return false;
        }
	}
	
	function formartDateTime(dateStr){
	    var tempStr = dateStr.split(" ");
	    var tempDateStr = tempStr[0].split("-");
	    var returnStr = tempDateStr[0]+"-";
	    if (tempDateStr[1].length==1){
	        returnStr+="0"+tempDateStr[1]+"-";
	    }else{
	        returnStr+=tempDateStr[1]+"-";
	    }
	    
	    if (tempDateStr[2].length==1){
	        returnStr+="0"+tempDateStr[2];
	    }else{
	        returnStr+=tempDateStr[2];
	    }
	    return returnStr;
	}
	
