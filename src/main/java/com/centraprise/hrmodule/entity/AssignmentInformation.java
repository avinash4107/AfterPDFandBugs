package com.centraprise.hrmodule.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.centraprise.hrmodule.entity.EmployeeDetails;

@Entity
@Table(name = "assignment_info")
public class AssignmentInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "assignment_info_id")
	private int assignmentInfoId;

	@Column(name = "assignment_start_date")
	private Date assignmentStartDate;

	@Column(name = "assignment_end_date")
	private Date assignmentEndDate;

	private String job;

	@Column(name = "date_of_joining")
	private Date dateOfJoining;

	@Column(name = "years_of_service")
	private float yearsOfService;

	private String manager;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id", unique = true)
	private EmployeeDetails employeeDetails;

	public int getAssignmentInfoId() {
		return assignmentInfoId;
	}

	public void setAssignmentInfoId(int assignmentInfoId) {
		this.assignmentInfoId = assignmentInfoId;
	}

	public Date getAssignmentStartDate() {
		return assignmentStartDate;
	}

	public void setAssignmentStartDate(Date assignmentStartDate) {
		this.assignmentStartDate = assignmentStartDate;
	}

	public Date getAssignmentEndDate() {
		return assignmentEndDate;
	}

	public void setAssignmentEndDate(Date assignmentEndDate) {
		this.assignmentEndDate = assignmentEndDate;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public float getYearsOfService() {
		return yearsOfService;
	}

	public void setYearsOfService(float yearsOfService) {
		this.yearsOfService = yearsOfService;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
