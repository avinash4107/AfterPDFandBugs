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

@Entity
@Table(name = "Provident_fund_details")
public class ProvidentFundInformation implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "provident_fund_id")
	private int providentFundId;

	@Column(name = "uan_number")
	private String uanNumber;

	@Column(name = "adhar_number")
	private String adharNumber;

	@Column(name = "prevoius_employee")
	private String prevoiusEmployee;

	@Column(name = "date_of_leaving")
	private Date dateOfLeaving;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "emp_id", unique = true)
	private EmployeeDetails employeeDetails;

	public int getProvidentFundId() {
		return providentFundId;
	}

	public void setProvidentFundId(int providentFundId) {
		this.providentFundId = providentFundId;
	}

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public String getAdharNumber() {
		return adharNumber;
	}

	public void setAdharNumber(String adharNumber) {
		this.adharNumber = adharNumber;
	}

	public String getPrevoiusEmployee() {
		return prevoiusEmployee;
	}

	public void setPrevoiusEmployee(String prevoiusEmployee) {
		this.prevoiusEmployee = prevoiusEmployee;
	}

	public Date getDateOfLeaving() {
		return dateOfLeaving;
	}

	public void setDateOfLeaving(Date dateOfLeaving) {
		this.dateOfLeaving = dateOfLeaving;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public EmployeeDetails getEmployeeDetails() {
		return employeeDetails;
	}

	public void setEmployeeDetails(EmployeeDetails employeeDetails) {
		this.employeeDetails = employeeDetails;
	}

}
