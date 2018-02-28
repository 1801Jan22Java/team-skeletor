package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.beans.Chatroom;
import com.revature.util.HibernateUtil;

public class ChatroomDaoImpl implements ChatroomDao {

	public String getNameById(int id) {
		Session s = HibernateUtil.getSession();
		Chatroom c = (Chatroom) s.get(Chatroom.class, id);
		s.close();
		return c.getName();
	}

	public int getIdByName(String name) {
		Session s = HibernateUtil.getSession();
		Chatroom c = (Chatroom) s.get(Chatroom.class, name);
		s.close();
		return c.getId();
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
			s.persist(c);
		tx.commit();
		s.close();

	}

}
