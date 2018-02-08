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
     * @param period ʱ����  �Ժ���Ϊ��λ
     * @param hour  ��ʱ  24Сʱ��ʽ
     * @param mm ����  
     * @param ss ����
     * @param type ����
     */
    public TimerManager(ApplicationContext context, long period, int hour,int mm,int ss,String type) {
         Calendar calendar = Calendar.getInstance(); 
                
         /*** ����ÿ��ִ�з��� ***/

         calendar.set(Calendar.HOUR_OF_DAY, hour);
         calendar.set(Calendar.MINUTE, mm);
         calendar.set(Calendar.SECOND, ss);
          
         Date date=calendar.getTime(); //��һ��ִ�ж�ʱ�����ʱ��
         System.out.println(date);
         System.out.println("before �����Ƚϣ�"+date.before(new Date()));
         //�����һ��ִ�ж�ʱ�����ʱ�� С�� ��ǰ��ʱ��
         //��ʱҪ�� ��һ��ִ�ж�ʱ�����ʱ�� ��һ�죬�Ա���������¸�ʱ���ִ�С��������һ�죬���������ִ�С�ѭ��ִ�е��������Ե�ǰʱ��Ϊ׼
         if (date.before(new Date())) {
             date = this.addDay(date, 1);
             System.out.println(date);
         }
          
         Timer timer = new Timer();
         TimerTask task=null;
          if(type.equals("0001")){//��������
        	  task = new  BirthdayTimerTask(context);
          } 
          if(type.equals("0002")){
        	  task = new  ContractTimerTask(context);
          }
          if(type.equals("0003")){//���°ݷ�ʱ����
        	  task = new  BackVisitIntervalTimerTask(context);
          }
          if(type.equals("0004")){//��Ŀ�ƻ�����
        	  task = new  ProjectPlanTimerTask(context);
          }
          if(type.equals("0005")){//�����������
        	  task = new  SpaceCheckerTimerTask(context);
          }
          if(type.equals("0006")){//�ձ����������
        	  task = new DailyTimerTask(context);
          }
          if(type.equals("0007")){//9���ձ����Ѽ��
        	  task = new DailyTimerTask(context);
          }
          if(type.equals("0008")){//�����
        	  task = new WeekTimerTask(context);
          }
          if(type.equals("0009")){//�����
        	  task = new DataTimerTask(context);
          }
          if(type.equals("0010")){//��⹤�����񳬹�Ԥ��ʱ�仹δ���δ�ͺ�
        	  task = new PlanTimerTask(context);
          }
          if(type.equals("0011")){//��⹤�����񳬹�Ԥ��ʱ�仹δ���δ�ͺ�
        	  task = new TimerCheckTimerTask(context);
          }
          if (type.equals("0012")) {//��������Ƿ�����ᣬ��֪ͨ
			  task=new MeetingTimerTask(context);
          }
          if (type.equals("0012")) {//��¼ÿ�������˵Ĳ������
			  task=new MeetingTimerTask(context);
          }
          if (type.equals("0013")) {//��¼ÿ�������˵Ĳ������
			  task=new DailyStatisticTimerTask(context);
          }
          if (type.equals("0014")) {//һ�㲻ִ�У�ֻҪ�����ͬ�����ݣ�ֻҪִ�е�timer���ָ��ҵ������ҵ��Ŷ�����
			  task=new DataUpdateTimerTask(context);
          }
          
          if (type.equals("0015")) {//������Ӫ�ƻ�ɨ��
        	  task=new ProductionOperationTimerTask(context);
          }
          
          if (type.equals("0016")) {//�ʸ�֤��ɨ��
        	  task=new QualificationTimerTask(context);
          }
          
          if (type.equals("0017")) { //��ʱ ����t_news ��2����ǰ������ 
        	  task=new ClearOldNewsTimerTask(context);  
          }
          
          if (type.equals("0018")) { //������ɨ��
        	  task=new BoardroomTask(context);  
          }
          
          if (type.equals("0019")) { //������ɨ��
        	  task=new NewTaskTask(context);  
          }

          timer.schedule(task,date,period);
        }

        // ���ӻ��������
        public Date addDay(Date date, int num) {
         Calendar startDT = Calendar.getInstance();
         startDT.setTime(date);
         startDT.add(Calendar.DAY_OF_MONTH, num);
         return startDT.getTime();
        }
}

