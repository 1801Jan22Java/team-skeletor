package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.revature.beans.Message;
import com.revature.beans.Report;
import com.revature.util.HibernateUtil;

@Component
public class ReportDaoImpl implements ReportDao{

	public List<Report> viewAllReports() {
		Session s = HibernateUtil.getSession();
		List<Report> allReports = s.createQuery("from Report").list();
		s.close();
		return allReports;
	}

	//Currently have it passing as a integer, but may change to a Message Object
	//because Report has a Message object field. Not too sure if the ID is the only thing stored in DB
	public List<Report> viewReportsByMessageId(int messageId) {
		Session s = HibernateUtil.getSession();
		Criteria c = s.createCriteria(Report.class);
		c.add(Restrictions.eq("message.id", messageId));
		List<Report> messageReports = c.list();
		s.close();
		return messageReports;
	}

	public Report getReportById(int reportId) {
		Session s = HibernateUtil.getSession();
		Report reportById = (Report) s.get(Report.class, reportId);
		s.close();
		return reportById;
	}

	public void addReport(Report report) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		s.save(report);
		tx.commit();
		s.close();		
	}
	
	public List<Integer> getFiveTimesReported() {
		List<Integer> fiveReports = new ArrayList<>();
		Session s = HibernateUtil.getSession();
		String hql = "SELECT message.id FROM Report Group By message HAVING COUNT(message)>5";
		Query query = s.createQuery(hql);
		fiveReports = query.list();
		s.close();
		return fiveReports;
		
		
	}

	// Implementation details.
	// Not sure if we want to actually delete the report or just have it contain a status saying it has been resolved.
	public void deleteReport(int reportId) {
		Session s = HibernateUtil.getSession();
		Transaction t = s.beginTransaction();
		Report reportObj = (Report) s.get(Report.class, reportId);
		s.delete(reportObj);
		t.commit();
		s.close();
	}
	
}
