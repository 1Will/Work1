package main.java;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import main.java.task.BackVisitIntervalTimerTask;
import main.java.task.BirthdayTimerTask;
import main.java.task.BoardroomTask;
import main.java.task.ClearOldNewsTimerTask;
import main.java.task.ContractTimerTask;
import main.java.task.DailyStatisticTimerTask;
import main.java.task.DailyTimerTask;
import main.java.task.DataTimerTask;
import main.java.task.DataUpdateTimerTask;
import main.java.task.MeetingTimerTask;
import main.java.task.NewTaskTask;
import main.java.task.PlanTimerTask;
import main.java.task.ProductionOperationTimerTask;
import main.java.task.ProjectPlanTimerTask;
import main.java.task.QualificationTimerTask;
import main.java.task.SpaceCheckerTimerTask;
import main.java.task.TimerCheckTimerTask;
import main.java.task.WeekTimerTask;

import org.springframework.context.ApplicationContext;

public class TimerManager {
    /**
     * 
     * @param period 时间间隔  以毫秒为单位
     * @param hour  几时  24小时格式
     * @param mm 几分  
     * @param ss 几秒
     * @param type 类型
     */
    public TimerManager(ApplicationContext context, long period, int hour,int mm,int ss,String type) {
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
        	  task = new  BirthdayTimerTask(context);
          } 
          if(type.equals("0002")){
        	  task = new  ContractTimerTask(context);
          }
          if(type.equals("0003")){//更新拜访时间间隔
        	  task = new  BackVisitIntervalTimerTask(context);
          }
          if(type.equals("0004")){//项目计划提醒
        	  task = new  ProjectPlanTimerTask(context);
          }
          if(type.equals("0005")){//磁盘容量检查
        	  task = new  SpaceCheckerTimerTask(context);
          }
          if(type.equals("0006")){//日报完成情况检查
        	  task = new DailyTimerTask(context);
          }
          if(type.equals("0007")){//9点日报提醒检查
        	  task = new DailyTimerTask(context);
          }
          if(type.equals("0008")){//添加周
        	  task = new WeekTimerTask(context);
          }
          if(type.equals("0009")){//添加周
        	  task = new DataTimerTask(context);
          }
          if(type.equals("0010")){//检测工作任务超过预计时间还未完成未滞后
        	  task = new PlanTimerTask(context);
          }
          if(type.equals("0011")){//检测工作任务超过预计时间还未完成未滞后
        	  task = new TimerCheckTimerTask(context);
          }
          if (type.equals("0012")) {//检查明天是否有早会，并通知
			  task=new MeetingTimerTask(context);
          }
          if (type.equals("0012")) {//记录每天所有人的操作情况
			  task=new MeetingTimerTask(context);
          }
          if (type.equals("0013")) {//记录每天所有人的操作情况
			  task=new DailyStatisticTimerTask(context);
          }
          if (type.equals("0014")) {//一般不执行，只要导入合同等数据，只要执行的timer，恢复我的数据我的团队数据
			  task=new DataUpdateTimerTask(context);
          }
          
          if (type.equals("0015")) {//生产经营计划扫描
        	  task=new ProductionOperationTimerTask(context);
          }
          
          if (type.equals("0016")) {//资格证书扫描
        	  task=new QualificationTimerTask(context);
          }
          
          if (type.equals("0017")) { //定时 清理t_news 表2个月前的数据 
        	  task=new ClearOldNewsTimerTask(context);  
          }
          
          if (type.equals("0018")) { //会议室扫描
        	  task=new BoardroomTask(context);  
          }
          
          if (type.equals("0019")) { //会议室扫描
        	  task=new NewTaskTask(context);  
          }

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

