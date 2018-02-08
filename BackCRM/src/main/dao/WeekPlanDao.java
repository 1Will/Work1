package main.dao;


import java.util.List;

import org.hibernate.Session;

import main.pojo.WeekPlan;

public interface WeekPlanDao {
	
	public void saveWeekPlan(WeekPlan weekPlan);//�����ܼƻ�

	public void updateWeekPlan(WeekPlan weekPlan);//�����ܼƻ�
	
	public List<WeekPlan> getWeekPlanByWeekId(Long weekId,Long userid); //������id ���û�id weeklyIdΪnull ��ȡ���� 
    
    public WeekPlan getWeekPlanById(Long id);//����id��ȡ�ܼƻ�

    public List<WeekPlan> getWeekPlanByWeeklyId(Long weeklyId);//����weeklyId��ȡ�ܼƻ�
    
  //  public String getMaxWeeklyCode(String code,Long ratId);//���ݼ�¼�ߺ�codeǰ׺��ȡ��ǰ���code
	
	public Session getSuperSession();
	
	
}
