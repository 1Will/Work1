package main.dao;

import java.util.List;

import main.pojo.Message;

import org.hibernate.Session;

public interface MessageDao {
	public void saveMessage(Message message);

	public void updateMessage(Message message);
	
	public void deleteMessage(Message message);
	
	public List<Message> getAllMessage(Long[] messageIds);

	public Message getMessageById(Long id);
	
	public Session getSuperSession();

	public List<Message> getSomeMessage();
}
