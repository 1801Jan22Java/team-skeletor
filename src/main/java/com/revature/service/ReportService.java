package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Message;
import com.revature.beans.Report;
import com.revature.dao.ReportDao;
import com.revature.dao.ReportDaoImpl;

@Service(value="reportService")
public class ReportService{

	@Autowired
	ReportDao reportDaoImpl;
	
	public List<Report> viewAllReports() {
		
		return reportDaoImpl.viewAllReports();
	}

	
	public List<Report> viewReportsByMessageId(int messageId) {
		
		return reportDaoImpl.viewReportsByMessageId(messageId);
	}

	
	public Report getReportById(int reportId) {
	
		return reportDaoImpl.getReportById(reportId);
	}

	public void addReport(Report report) {
		
		reportDaoImpl.addReport(report);
		
	}

	
	public void deleteReport(int reportId) {
		
		reportDaoImpl.deleteReport(reportId);
		
	}
	
	public void deleteReportByMessageId(int messageId) {
		reportDaoImpl.deleteReportByMessageId(messageId);
	}


	public List<Integer> getFiveTimesReported() {
		return reportDaoImpl.getFiveTimesReported();
	}

}
