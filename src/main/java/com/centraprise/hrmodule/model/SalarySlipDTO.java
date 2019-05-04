package com.centraprise.hrmodule.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "salary-info")
@XmlAccessorType(XmlAccessType.FIELD)

public class SalarySlipDTO {

	@XmlElement(name = "employee-id")
	private int employeeNumber;

	@XmlElement(name = "employee-name")
	private String employeeName;

	@XmlElement(name = "employee-joining-date")
	private String dateOfJoining;

	@XmlElement(name = "location")
	private String location;

	@XmlElement(name = "designation")
	private String designation;

	@XmlElement(name = "pf-number")
	private String pfNumber;

	@XmlElement(name = "account-number")
	private String accountNumber;

	@XmlElement(name = "bank-name")
	private String bankName;

	@XmlElement(name = "pan-number")
	private String panNumber;

	@XmlElement(name = "gender")
	private String gender;

	@XmlElement(name = "uan-number")
	private String uanNumber;

	@XmlElement(name = "number-of-days-worked")
	private int numberOfdaysWorked;

	@XmlElement(name = "loss-of-pay-days")
	private int lossOfpayDays;

	@XmlElement(name = "basic")
	private float basic;

	@XmlElement(name = "special-allowance")
	private float specialAllowance;

	@XmlElement(name = "sign-onbonus-rate")
	private float signOnBonusRate;

	@XmlElement(name = "provident-fund")
	private float providentFund;

	@XmlElement(name = "profession-tax")
	private float professionTax;

	@XmlElement(name = "basic-arrear")
	private float basicArrear;

	@XmlElement(name = "basic-gross")
	private float basicGross;

	@XmlElement(name = "special-allowance-arrear")
	private float specialAllowanceArrear;

	@XmlElement(name = "special-allowance-gross")
	private float specialAllowanceGross;

	@XmlElement(name = "sign-onBonus-currentmonth")
	private float signOnBonusCurrentMonth;

	@XmlElement(name = "sign-onbonus-arrear")
	private float signOnBonusArrear;

	@XmlElement(name = "sign-onbonus-gross")
	private float signOnBonusGross;

	@XmlElement(name = "gross-earning-month")
	private float grossEarningMonth;

	@XmlElement(name = "gross-earning-arrear")
	private float grossEarningArrear;

	@XmlElement(name = "gross-earning-total")
	private float grossEarningTotal;

	@XmlElement(name = "total-deductions")
	private float totalDeductions;

	@XmlElement(name = "net-pay")
	private float netPay;

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getPfNumber() {
		return pfNumber;
	}

	public void setPfNumber(String pfNumber) {
		this.pfNumber = pfNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUanNumber() {
		return uanNumber;
	}

	public void setUanNumber(String uanNumber) {
		this.uanNumber = uanNumber;
	}

	public int getNumberOfdaysWorked() {
		return numberOfdaysWorked;
	}

	public void setNumberOfdaysWorked(int numberOfdaysWorked) {
		this.numberOfdaysWorked = numberOfdaysWorked;
	}

	public int getLossOfpayDays() {
		return lossOfpayDays;
	}

	public void setLossOfpayDays(int lossOfpayDays) {
		this.lossOfpayDays = lossOfpayDays;
	}

	public float getBasic() {
		return basic;
	}

	public void setBasic(float basic) {
		this.basic = basic;
	}

	public float getSpecialAllowance() {
		return specialAllowance;
	}

	public void setSpecialAllowance(float specialAllowance) {
		this.specialAllowance = specialAllowance;
	}

	public float getSignOnBonusRate() {
		return signOnBonusRate;
	}

	public void setSignOnBonusRate(float signOnBonusRate) {
		this.signOnBonusRate = signOnBonusRate;
	}

	public float getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(float providentFund) {
		this.providentFund = providentFund;
	}

	public float getProfessionTax() {
		return professionTax;
	}

	public void setProfessionTax(float professionTax) {
		this.professionTax = professionTax;
	}

	public float getBasicArrear() {
		return basicArrear;
	}

	public void setBasicArrear(float basicArrear) {
		this.basicArrear = basicArrear;
	}

	public float getBasicGross() {
		return basicGross;
	}

	public void setBasicGross(float basicGross) {
		this.basicGross = basicGross;
	}

	public float getSpecialAllowanceArrear() {
		return specialAllowanceArrear;
	}

	public void setSpecialAllowanceArrear(float specialAllowanceArrear) {
		this.specialAllowanceArrear = specialAllowanceArrear;
	}

	public float getSpecialAllowanceGross() {
		return specialAllowanceGross;
	}

	public void setSpecialAllowanceGross(float specialAllowanceGross) {
		this.specialAllowanceGross = specialAllowanceGross;
	}

	public float getSignOnBonusCurrentMonth() {
		return signOnBonusCurrentMonth;
	}

	public void setSignOnBonusCurrentMonth(float signOnBonusCurrentMonth) {
		this.signOnBonusCurrentMonth = signOnBonusCurrentMonth;
	}

	public float getSignOnBonusArrear() {
		return signOnBonusArrear;
	}

	public void setSignOnBonusArrear(float signOnBonusArrear) {
		this.signOnBonusArrear = signOnBonusArrear;
	}

	public float getSignOnBonusGross() {
		return signOnBonusGross;
	}

	public void setSignOnBonusGross(float signOnBonusGross) {
		this.signOnBonusGross = signOnBonusGross;
	}

	public float getGrossEarningMonth() {
		return grossEarningMonth;
	}

	public void setGrossEarningMonth(float grossEarningMonth) {
		this.grossEarningMonth = grossEarningMonth;
	}

	public float getGrossEarningArrear() {
		return grossEarningArrear;
	}

	public void setGrossEarningArrear(float grossEarningArrear) {
		this.grossEarningArrear = grossEarningArrear;
	}

	public float getGrossEarningTotal() {
		return grossEarningTotal;
	}

	public void setGrossEarningTotal(float grossEarningTotal) {
		this.grossEarningTotal = grossEarningTotal;
	}

	public float getTotalDeductions() {
		return totalDeductions;
	}

	public void setTotalDeductions(float totalDeductions) {
		this.totalDeductions = totalDeductions;
	}

	public float getNetPay() {
		return netPay;
	}

	public void setNetPay(float netPay) {
		this.netPay = netPay;
	}

}
