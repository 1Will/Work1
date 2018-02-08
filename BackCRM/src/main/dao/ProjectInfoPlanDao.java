package main.dao;


import java.util.List;

import org.hibernate.Session;

import main.pojo.PersonnelFiles;
import main.pojo.ProjectInfoPlan;

// �����ƻ�

public interface ProjectInfoPlanDao
{
    public ProjectInfoPlan getProjectInfoPlanById(Long id); //����id ��ȡ�����ƻ���Ϣ����
    
    public List<ProjectInfoPlan> getProjectInfoPlanByPerId(Long personnelId); // ���ݸ�����id ��ȡ�ƻ�����
    
    public void updateProjectInfoPlan(ProjectInfoPlan projectInfoPlan); // ���¹����ƻ�
    
    public Session getSuperSession();
}