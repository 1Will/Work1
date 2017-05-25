package main.java;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import main.java.task.BackVisitIntervalTimerTask;
import main.java.task.BirthdayTimerTask;
import main.java.task.ContractTimerTask;

public class TimerManager {
	 //时间间隔
    private long period;
    private int hour;
    private int mm;
    private int ss;
    private String type;
    /**
     * 
     * @param period 时间间隔  以毫秒为单位
     * @param hour  几时  24小时格式
     * @param mm 几分  
     * @param ss 几秒
     * @param type 类型
     */
    public TimerManager( long period, int hour,int mm,int ss,String type) {
         Calendar calendar = Calendar.getInstance(); 
                
         /*** 定制每日执行方法 ***/

         calendar.set(Calendar.HOUR_OF_DAY, hour);
         calendar.set(Calendar.MINUTE, mm);
         calendar.set(Calendar.SECOND, ss);
          
         Date date=calendar.getTime(); //第一次执行定时任务的时间
         System.out.println(date);
         System.out.println("before 方法比较："+date.before(new Date()));
         //如果第一次执行定时任务的时间 小于 当前的时间
         //此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。循环执行的周期则以当前时间为准
         if (date.before(new Date())) {
             date = this.addDay(date, 1);
             System.out.println(date);
         }
          
         Timer timer = new Timer();
         TimerTask task=null;
          if(type.equals("0001")){//生日提醒
        	  task = new  BirthdayTimerTask();
          }else if(type.equals("0002")){
        	  task = new  ContractTimerTask();
          }else if(type.equals("0003")){//更新拜访时间间隔
        	  task = new  BackVisitIntervalTimerTask();
          }
//         NFDFlightDataTimerTask task = new NFDFlightDataTimerTask();
         //安排指定的任务在指定的时间开始进行重复的固定延迟执行。
         timer.schedule(task,date,period);
        }

        // 增加或减少天数
        public Date addDay(Date date, int num) {
         Calendar startDT = Calendar.getInstance();
         startDT.setTime(date);
         startDT.add(Calendar.DAY_OF_MONTH, num);
         return startDT.getTime();
        }
}

