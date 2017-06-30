package bank.analyticsOfStatements;

import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * analitika izvoda
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "analyticsOfStatements")
public class AnalyticsOfStatementsXml {
	

	
	@XmlElement
	private String debtor_originator; //duznik-nalogodavac

	@XmlElement
	private String purposeOfPayment; //svrha placanja
	
	@XmlElement
	private String creditor_recipient; //poverilac-primalac
	
	@XmlJavaTypeAdapter(AddaptDate.class)
	@XmlElement
	private Date dateOfReceipt; //datum prijema
	
	@XmlJavaTypeAdapter(AddaptDate.class)
	@XmlElement
	private Date currencyDate; //datum valute
	
	
	@XmlElement
	private String debtorAccount; //racun duznika
	
	@XmlElement
	private Integer modelAssigments; //model zaduzenja
	
	@XmlElement
	private String referenceNumberAssigments; //poziv na broj zaduzenja
	
	@XmlElement
	private String accountCreditor; //racun poverioca
	
	@XmlElement
	private Integer modelApproval; //model odobrenja
	
	@XmlElement
	private String referenceNumberCreditor; //poziv na broj odobrenja
	
	@XmlElement
	private Boolean emergency;
	
	@XmlElement
	private Float sum;
	
	@XmlElement
	private Integer typeOfMistake; 
	
	@XmlElement
	private String status;
	
	
	// Analitika izvoda banke
	
	@XmlElement
	private String paymentType;	// tip placanja
	
	@XmlElement
	private String paymentCurrency;	// valuta placanja

	@XmlElement
	private String place;
	
	public AnalyticsOfStatementsXml() {
		super();
	}


	public String getDebtor_originator() {
		return debtor_originator;
	}

	public void setDebtor_originator(String debtor_originator) {
		this.debtor_originator = debtor_originator;
	}

	public String getPurposeOfPayment() {
		return purposeOfPayment;
	}

	public void setPurposeOfPayment(String purposeOfPayment) {
		this.purposeOfPayment = purposeOfPayment;
	}

	public String getCreditor_recipient() {
		return creditor_recipient;
	}

	public void setCreditor_recipient(String creditor_recipient) {
		this.creditor_recipient = creditor_recipient;
	}

	public Date getDateOfReceipt() {
		return dateOfReceipt;
	}

	public void setDateOfReceipt(Date dateOfReceipt) {
		this.dateOfReceipt = dateOfReceipt;
	}

	public Date getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(Date currencyDate) {
		this.currencyDate = currencyDate;
	}

	public String getDebtorAccount() {
		return debtorAccount;
	}

	public void setDebtorAccount(String debtorAccount) {
		this.debtorAccount = debtorAccount;
	}

	public Integer getModelAssigments() {
		return modelAssigments;
	}

	public void setModelAssigments(Integer modelAssigments) {
		this.modelAssigments = modelAssigments;
	}

	public String getReferenceNumberAssigments() {
		return referenceNumberAssigments;
	}

	public void setReferenceNumberAssigments(String referenceNumberAssigments) {
		this.referenceNumberAssigments = referenceNumberAssigments;
	}

	public String getAccountCreditor() {
		return accountCreditor;
	}

	public void setAccountCreditor(String accountCreditor) {
		this.accountCreditor = accountCreditor;
	}

	public Integer getModelApproval() {
		return modelApproval;
	}

	public void setModelApproval(Integer modelApproval) {
		this.modelApproval = modelApproval;
	}

	public String getReferenceNumberCreditor() {
		return referenceNumberCreditor;
	}

	public void setReferenceNumberCreditor(String referenceNumberCreditor) {
		this.referenceNumberCreditor = referenceNumberCreditor;
	}

	public Boolean isEmergency() {
		return emergency;
	}

	public void setEmergency(Boolean emergency) {
		this.emergency = emergency;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public Integer getTypeOfMistake() {
		return typeOfMistake;
	}

	public void setTypeOfMistake(Integer typeOfMistake) {
		this.typeOfMistake = typeOfMistake;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}



	public Boolean getEmergency() {
		return emergency;
	}
	
}
