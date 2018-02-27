package com.revature.dao;

import org.hibernate.Session;

import com.revature.beans.Chatroom;
import com.revature.util.HibernateUtil;

public class ChatroomDaoImpl implements ChatroomDao{

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

}
