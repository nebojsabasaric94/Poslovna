package bank.accountStatement;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import bank.analyticsOfStatements.AnalyticsOfStatementsXml;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "accountStatement")
public class AccountStatementXml {

	@XmlElement
	private Date fromDate;
	
	@XmlElement
	private Date toDate;
	
	@XmlElement
	private String accountNumber;
	
	@XmlElement
	private Float startAccountState;
	
	@XmlElement
	private Float trafficToBenefit;
	
	@XmlElement
	private Float trafficToTheBurden; //promet na teret
	
	@XmlElement
	private Float stateAtTheEndOfPeriod;
	
	@XmlElement
	private ArrayList<AnalyticsOfStatementsXml>statements;




	public ArrayList<AnalyticsOfStatementsXml> getStatements() {
		return statements;
	}


	public void setStatements(ArrayList<AnalyticsOfStatementsXml> statements) {
		this.statements = statements;
	}


	public Date getFromDate() {
		return fromDate;
	}


	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public Date getToDate() {
		return toDate;
	}


	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}


	public Float getStartAccountState() {
		return startAccountState;
	}


	public void setStartAccountState(Float startAccountState) {
		this.startAccountState = startAccountState;
	}


	public Float getTrafficToBenefit() {
		return trafficToBenefit;
	}


	public void setTrafficToBenefit(Float trafficToBenefit) {
		this.trafficToBenefit = trafficToBenefit;
	}


	public Float getTrafficToTheBurden() {
		return trafficToTheBurden;
	}


	public void setTrafficToTheBurden(Float trafficToTheBurden) {
		this.trafficToTheBurden = trafficToTheBurden;
	}


	public Float getStateAtTheEndOfPeriod() {
		return stateAtTheEndOfPeriod;
	}


	public void setStateAtTheEndOfPeriod(Float stateAtTheEndOfPeriod) {
		this.stateAtTheEndOfPeriod = stateAtTheEndOfPeriod;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}



	
	
	
}
