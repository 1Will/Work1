package main.java;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import main.java.task.BackVisitIntervalTimerTask;
import main.java.task.BirthdayTimerTask;
import main.java.task.ContractTimerTask;

public class TimerManager {
	 //ʱ����
    private long period;
    private int hour;
    private int mm;
    private int ss;
    private String type;
    /**
     * 
     * @param period ʱ����  �Ժ���Ϊ��λ
     * @param hour  ��ʱ  24Сʱ��ʽ
     * @param mm ����  
     * @param ss ����
     * @param type ����
     */
    public TimerManager( long period, int hour,int mm,int ss,String type) {
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
        	  task = new  BirthdayTimerTask();
          }else if(type.equals("0002")){
        	  task = new  ContractTimerTask();
          }else if(type.equals("0003")){//���°ݷ�ʱ����
        	  task = new  BackVisitIntervalTimerTask();
          }
//         NFDFlightDataTimerTask task = new NFDFlightDataTimerTask();
         //����ָ����������ָ����ʱ�俪ʼ�����ظ��Ĺ̶��ӳ�ִ�С�
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

