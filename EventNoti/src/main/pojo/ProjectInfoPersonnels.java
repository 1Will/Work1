package main.pojo;


public class ProjectInfoPersonnels implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Long projectInfo_id;
	private Long proPerson_id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectInfo_id() {
		return projectInfo_id;
	}
	public void setProjectInfo_id(Long projectInfo_id) {
		this.projectInfo_id = projectInfo_id;
	}
	public Long getProPerson_id() {
		return proPerson_id;
	}
	public void setProPerson_id(Long proPerson_id) {
		this.proPerson_id = proPerson_id;
	}

}