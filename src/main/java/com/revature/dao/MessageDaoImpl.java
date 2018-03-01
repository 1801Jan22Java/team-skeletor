package com.revature.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.revature.beans.Message;
import com.revature.util.HibernateUtil;

@Component(value="messageDaoImpl")
public class MessageDaoImpl implements MessageDao {

	public void createMessage(Message msg) {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		msg.setDate(LocalDateTime.now());
		s.save(msg);
		t.commit();
		s.close();

	}

	public Message getMessageById(int msgId) {
		Session s = HibernateUtil.getSession();
		
		Message msg = (Message) s.get(Message.class, new Integer(msgId));

		s.close();
		return msg;
	}

	public List<Message> getMessages() {
		Session s = HibernateUtil.getSession();

		Criteria c = s.createCriteria(Message.class);
		List<Message> list = c.list();

		s.close();
		return list;
	}

	public void deleteMessage(int msgId) {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();

		Criteria c = s.createCriteria(Message.class);
		c.add(Restrictions.eq("id", msgId));
		Message msg = (Message) c.list().get(0);
		s.delete(msg);

		t.commit();
		s.close();

	}

	public void updateMessage(Message msg) {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();

		s.saveOrUpdate(msg);

		t.commit();
		s.close();

	}

	@Override
	public List<Message> getMessagesByChatroomId(int roomId) {
		Session s = HibernateUtil.getSession();

		Criteria c = s.createCriteria(Message.class);
		c.add(Restrictions.eq("room.id", roomId));
		List<Message> msg = c.list();
		
		s.close();

		return msg;
	}

}
