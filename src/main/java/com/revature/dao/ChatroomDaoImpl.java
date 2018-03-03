package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.revature.beans.Chatroom;
import com.revature.util.HibernateUtil;

@Component(value = "chatroomDaoImpl")
public class ChatroomDaoImpl implements ChatroomDao {

	public Chatroom getChatroomById(int id) {
		Session s = HibernateUtil.getSession();
		Chatroom c = (Chatroom) s.get(Chatroom.class, id);
		s.close();
		return c;
	}

	public void deleteChatroom(int id) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		try {
			String hql = "DELETE FROM CHATROOM WHERE ID = :id";
			Query query = s.createQuery(hql);
			query.setInteger("id", id);
			query.executeUpdate();

			tx.commit();
		} catch (Exception t) {
			tx.rollback();
		}
	}

	public void addChatroom(String name) {
		Chatroom c = new Chatroom(name);
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.save(c);
		tx.commit();
		s.close();

	}

	@Override
	public List<Chatroom> getChatrooms() {
		List<Chatroom> chatrooms;
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Chatroom.class);
		chatrooms = c.list();
		s.close();
		return chatrooms;
	}

}
