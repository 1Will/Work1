package main.service.impl;

import java.util.List;

import org.hibernate.Session;

import main.dao.MessageDao;
import main.pojo.Message;
import main.service.MessageService;

public class MessageServiceImpl implements MessageService {
	private MessageDao messageDao;

	@Override
	public void saveMessage(Message message) {
		messageDao.saveMessage(message);
	}

	@Override
	public void updateMessage(Message message) {
		messageDao.updateMessage(message);
	}

	@Override
	public void deleteMessage(Message message) {
		messageDao.deleteMessage(message);
	}

	@Override
	public List<Message> getAllMessage(Long[] messageIds) {
		return messageDao.getAllMessage(messageIds);
	}
	
	@Override
	public List<Message> getSomeMessage() {
		return messageDao.getSomeMessage();
	}

	@Override
	public Message getMessageById(Long id) {
		return messageDao.getMessageById(id);
	}

	@Override
	public Session getSuperSession() {
		return messageDao.getSuperSession();
	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}
}
