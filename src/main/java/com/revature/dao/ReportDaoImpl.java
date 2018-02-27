package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.revature.beans.Report;
import com.revature.util.HibernateUtil;

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
		c.add(Restrictions.eq("MESSAGE_ID", messageId));
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

	// Implementation details.
	// Not sure if we want to actually delete the report or just have it contain a status saying it has been resolved.
	public void deleteReport(int reportId) {
		Session s = HibernateUtil.getSession();
		Report reportObj = new Report();
		reportObj.setId(reportId);
		s.delete(reportObj);		
	}
	
}
