package com.revature.beans;

import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

import com.revature.beans.Message;
import com.revature.beans.User;

@Component(value="report")
@Entity
@Table(name="REPORTS")
public class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3560985547545616427L;

	public Report() {
		super();
	}

	public Report(int id, User user, Message message) {
		super();
		this.id = id;
		this.user = user;
		this.message = message;
	}
	
	public Report(User user, Message message) {
		super();
		this.user = user;
		this.message = message;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="ReportSequence")
	@SequenceGenerator(allocationSize=1,name="ReportSequence",sequenceName="SQ_Report_PK")
	@Column(name="ID")
	private int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MESSAGE_ID")
	private Message message;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", user=" + user + ", message=" + message + "]";
	}
	
}
