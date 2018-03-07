package com.revature.dao;

import java.util.List;

import com.revature.beans.Message;
import com.revature.beans.Report;

public interface ReportDao {
	public List<Report> viewAllReports();
	public List<Report> viewReportsByMessageId(int messageId);
	public Report getReportById(int reportId);
	public void addReport(Report report);
	public void deleteReport(int reportId);
	public List<Integer> getFiveTimesReported();
	public void deleteReportByMessageId(int messageId);
}
