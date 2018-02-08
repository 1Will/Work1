package main.service;

import java.util.List;

import main.pojo.Message;

import org.hibernate.Session;

public interface MessageService {

	public void saveMessage(Message message);

	public void updateMessage(Message message);// 更新实体

	public void deleteMessage(Message message);

	public List<Message> getAllMessage(Long[] messageIds);// 根据ids返回backlist集合

	public Message getMessageById(Long id);// 根据id返回backlist

	public Session getSuperSession();

	public List<Message> getSomeMessage();
}
