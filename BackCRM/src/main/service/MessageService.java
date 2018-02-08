package main.service;

import java.util.List;

import main.pojo.Message;

import org.hibernate.Session;

public interface MessageService {

	public void saveMessage(Message message);

	public void updateMessage(Message message);// ����ʵ��

	public void deleteMessage(Message message);

	public List<Message> getAllMessage(Long[] messageIds);// ����ids����backlist����

	public Message getMessageById(Long id);// ����id����backlist

	public Session getSuperSession();

	public List<Message> getSomeMessage();
}
