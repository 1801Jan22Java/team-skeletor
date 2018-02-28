package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Message;
import com.revature.util.HibernateUtil;

public class MessageDaoImpl implements MessageDao {

	public void createMessage(Message msg) {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		s.save(msg);
		t.commit();
		s.close();

	}

	public Message getMessageById(int msgId) {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		
		Criteria c = s.createCriteria(Message.class);
		c.add(Restrictions.eq("id", msgId));
		Message msg = (Message) c.list().get(0);
		
		t.commit();
		s.close();
		return msg;
	}

	public List<Message> getMessages() {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		
		Criteria c = s.createCriteria(Message.class);
		List<Message> list = c.list();
		
		t.commit();
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

}
