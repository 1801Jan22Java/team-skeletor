package com.revature.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	
	private static SessionFactory getSessionFactory(String filename) {
		if (HibernateUtil.sessionFactory == null) {
			Configuration c = new Configuration().configure(filename);
			/*
			c.setProperty("hibernate.connection.username", System.getenv("DB_USERNAME"));
			c.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
			c.setProperty("hibernate.connection.url", System.getenv("DB_URL"));
			*/
			c.setProperty("hibernate.connection.username", "jaboyles");
			c.setProperty("hibernate.connection.password", "Skelet0r");
			c.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@boylesdemo.cewwysudyuaa.us-east-1.rds.amazonaws.com:1521:ORCL");
			
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
			HibernateUtil.sessionFactory = c.buildSessionFactory(sr);
		}
		return HibernateUtil.sessionFactory;
	}
	
	public static Session getSession() {
		return getSessionFactory("hibernate.cfg.xml").openSession();
	}
	
}
