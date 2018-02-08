package main.pojo;

import java.util.HashSet;
import java.util.Set;


/**
 * 事件类型
 */

public class EventType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	
	private Long id;
	private String name;
	private String code;
	private String description;
	private String effectflag;
	
//	private Set<EventNew> events = new HashSet<EventNew>();
//	private Set<EventLog> eventLogs = new HashSet<EventLog>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
//	public Set<EventNew> getEvents() {
//		return events;
//	}
//	public void setEvents(Set<EventNew> events) {
//		this.events = events;
//	}
//	public Set<EventLog> getEventLogs() {
//		return eventLogs;
//	}
//	public void setEventLogs(Set<EventLog> eventLogs) {
//		this.eventLogs = eventLogs;
//	}
	public String getEffectflag() {
		return effectflag;
	}
	public void setEffectflag(String effectflag) {
		this.effectflag = effectflag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}