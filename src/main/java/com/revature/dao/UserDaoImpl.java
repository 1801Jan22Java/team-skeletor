package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.revature.beans.User;
import com.revature.util.HibernateUtil;

@Component(value="userDaoImpl")
public class UserDaoImpl implements UserDao {

	public List<User> getUsers() {
		Session s = HibernateUtil.getSession();
		List<User> users =s.createCriteria(User.class).list();
		//Query query = s.createQuery("from Users");
		//List<User> users=query.list();
		for(User u: users) {
			u.setPassword("****");
			System.out.println(u);
		}
		s.close();
		return users;
	}

	public User getUserById(int id) {
		Session s = HibernateUtil.getSession();
		User u = (User)s.get(User.class,id);
		s.close();
		return u;
	}

	public int getUserId(User user) {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(User.class);
		
		Integer id =(Integer)c.add(Restrictions.eq("username",user.getUsername())).uniqueResult();
		return id;
	}

	public User getUserByUsername(String username) {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(User.class);
		User u = (User)c.add(Restrictions.eq("username",username)).uniqueResult();
		return u;
	}
	
	public List<User> getUsersByUsername(String username) {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(User.class);
		List<User> users = c.add(Restrictions.eq("username",username)).list();
		return users;
	}

	public int addUser(User user) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		int result = 0;
		user.setActive(true);
		s.persist(user);
		tx.commit();
		s.close();
		return result;
	}

	public void deleteUser(int userID) {
		User user= getUserById(userID);
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.persist(user);
		s.delete(user);
		s.close();
		
	}

	
	public void updateUser(int userID) {
		// TODO Auto-generated method stub
		
	}
	
	public void makeUserAdmin(int userID) {
		User user = getUserById(userID);
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		user.setAdmin(true);
		s.update(user);
		tx.commit();
		s.close();
	}
	
	public void updateUserPhoto(int userID, int photoID) {
		User user = getUserById(userID);
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		user.setProfileImageURL(photoID);
		s.update(user);
		tx.commit();
		s.close();
		
		
	}
	
	/*banUser takes in a userID as an Integer
	 * sets active to false, thus deactivating user
	*/
	public void banUser(Integer userID) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		try {
			User user = (User)s.get(User.class, userID);
			user.setActive(false);
			s.update(user);
			tx.commit();
		}
		catch(HibernateException e) {
			if(tx!=null) tx.rollback();
		}
		finally {
		s.close();
		}
	}
	
	/*reactivateUser takes in a userID as an Integer
	 * sets active to true, thus reactivating user
	*/
	public void reactivateUser(Integer userID) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		try {
			User user = (User)s.get(User.class, userID);
			user.setActive(true);
			s.update(user);
			tx.commit();
		}
		catch(HibernateException e) {
			if(tx!=null) tx.rollback();
		}
		finally {
		s.close();
		}
		
	}
	
	
	public static void main(String [] args) {
		//UserDaoImpl udi = new UserDaoImpl();
		//User user = udi.getUserByUsername("Skeletor");
		//List<User> user =udi.getUsers();
		//System.out.println(user);
		//udi.makeUserAdmin(1);
		//User user = udi.getUserById(1);
		//System.out.println(user);
		//udi.reactivateUser(1);
		//user = udi.getUserById(1);
		//System.out.println(user);
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(User.class);
		c.add(Restrictions.eq("username",username));
		c.add(Restrictions.eq("password",password));
		c.add(Restrictions.eq("isActive",true));
		User user = (User)c.uniqueResult();
		s.close();
		return user;
		
	}



}
