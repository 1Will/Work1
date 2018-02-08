package main.java;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class NFDFlightDataTaskListener implements  ServletContextListener {
	 public ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
	 public void contextInitialized(ServletContextEvent sce) {
		 
		 //��������
         new TimerManager(context,24*60*60*1000L,8,15,0,"0001");
         //��ʱ�䲻һ�£�����sql����߳�����,��ͬ����
         new TimerManager(context,24*60*60*1000L,8,20,0,"0002");
         //���°ݷ�ʱ�������澯״̬���´ΰݷ�����
         new TimerManager(context,24*60*60*1000L,8,00,0,"0003");
         //��Ŀ�ƻ�����
         new TimerManager(context,24*60*60*1000L,8,10,0,"0004");
         //�����������
         new TimerManager(context,24*60*60*1000L,8,25,0,"0005");
         //�ձ����Ѽ��  ÿ��12��
         new TimerManager(context,24*60*60*1000L,12,00,0,"0006");
         //ÿ��9�� �����ձ��������
         new TimerManager(context,24*60*60*1000L,9,00,0,"0007");
         //���ÿ�ܣ�һ���ִ��һ��
         new TimerManager(context,24*60*60*1000L,1,00,0,"0008");
         //���ͳ�Ʊ�����,һ����ִ��һ��
         new TimerManager(context,24*60*60*1000L,1,40,0,"0009");
         
         new TimerManager(context,24*60*60*1000L,8,30,0,"0010");
         //�����ᣬÿ��ִ��һ��
         new TimerManager(context,24*60*60*1000L,16,30,0,"0012");
         //Timer���
//         new TimerManager(context,60*1000L,18,11,0,"0011");
         //��¼ÿ�������˵Ĳ������
         new TimerManager(context,24*60*60*1000L,2,0,0,"0013");
       //һ�㲻ִ�У�ֻҪ�����ͬ�����ݣ�ֻҪִ�е�timer���ָ��ҵ������ҵ��Ŷ�����
//         new TimerManager(context,2400*60*60*1000L,15,8,0,"0014");
         //������Ӫ�ƻ�ɨ��
         new TimerManager(context,24*60*60*1000L,0,30,0,"0015");
         //�ʸ�֤��ɨ��
         new TimerManager(context,24*60*60*1000L,9,10,0,"0016");
         //��ʱ ����t_news ��2����ǰ������
         new TimerManager(context,24*60*60*1000L,0,10,0,"0017");
         //������ɨ��
         new TimerManager(context,60*1000L,8,00,0,"0018");
         //�ճ�ɨ��
         new TimerManager(context,60*1000L,8,01,0,"0019");
         
    }
	 
    public void contextDestroyed(ServletContextEvent sce) {
         System.out.println("*****************�������ر�***************");
    }
}
