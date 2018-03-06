package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.MyResponseMessage;
import com.revature.beans.Report;
import com.revature.service.ReportService;

@Controller("reportController")
@RequestMapping("/report")
@CrossOrigin(origins="http://localhost:8084")
public class ReportController {

	@Autowired
	ReportService reportService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Report> getReportById(@PathVariable int id) {
		ResponseEntity<Report> resp = null;

		Report rep = reportService.getReportById(id);
		if (rep == null) {
			resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(rep, HttpStatus.OK);
		}

		return resp;

	}

	@PostMapping("/addReport")
	@ResponseBody
	public ResponseEntity<MyResponseMessage> createReport(@RequestBody Report report) {

		ResponseEntity<MyResponseMessage> resp = null;

		try {
			reportService.addReport(report);
			resp = new ResponseEntity<>(new MyResponseMessage(report.toString()), HttpStatus.OK);
		} catch (Exception e) {
			resp = new ResponseEntity<>(new MyResponseMessage("failed to add message"),
					HttpStatus.BAD_REQUEST);
		}

		return resp;

	}

	@RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Report>> getReportByMessageId(
			@PathVariable int id) {
		ResponseEntity<List<Report>> resp = null;

		List<Report> reps = reportService.viewReportsByMessageId(id);
		if (reps == null) {
			resp = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} else {
			resp = new ResponseEntity<>(reps, HttpStatus.OK);
		}

		return resp;

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteReport(@PathVariable int id) {
		ResponseEntity<String> resp = null;

		reportService.deleteReport(id);

		resp = new ResponseEntity<>("Report deleted", HttpStatus.OK);

		return resp;

	}
}
