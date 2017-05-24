package bank.analyticsOfStatements;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.currency.Currency;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.itemTransfer.ItemTransfer;
import bank.paymentType.PaymentType;
import bank.place.Place;

/**
 * analitika izvoda
 */

@Entity
public class AnalyticsOfStatements {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long itemNumber;
	
	@Column(length = 256)
	private String debtor_originator; //duznik-nalogodavac

	@Column(length = 256)
	private String purposeOfPayment; //svrha placanja
	
	@Column(length = 256)
	private String creditor_recipient; //poverilac-primalac
	
	@Column
	@NotNull
	private Date dateOfReceipt; //datum prijema
	
	@Column
	@NotNull
	private Date currencyDate; //datum valute
	
	
	@Column(length = 18)
	private String debtorAccount; //racun duznika
	
	@Column
	@Max(value = 2)
	private Integer modelAssigments; //model zaduzenja
	
	@Column(length = 20)
	private String referenceNumberAssigments; //poziv na broj zaduzenja
	
	@Column(length = 18)
	private String accountCreditor; //racun poverioca
	
	@Column
	@Max(value = 2)
	private Integer modelApproval; //model odobrenja
	
	@Column(length = 20)
	private String referenceNumberCreditor; //poziv na broj odobrenja
	
	@Column
	@NotNull
	private Boolean emergency;
	
	@Column(length = 15, precision = 2)
	private Float sum;
	
	@Column
	@Max(value = 1)
	private Integer typeOfMistake; 
	
	@Column(length = 1)
	private String status;
	
	@ManyToOne
	private DailyAccountBalance dailyAccountBalance;
	
	// Analitika izvoda banke
	
	@ManyToOne
	private PaymentType paymentType;	// tip placanja
	
	@ManyToOne
	private Currency paymentCurrency;	// valuta placanja
	
	@JsonIgnore
	@OneToMany
	private List<ItemTransfer> itemTransfer;
	

	@ManyToOne
	private Place place;
	
	public AnalyticsOfStatements() {
		super();
	}

	public Long getItemNumber() {
		return itemNumber;
	}

	public void setItemNumber(Long itemNumber) {
		this.itemNumber = itemNumber;
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

	public DailyAccountBalance getDailyAccountBalance() {
		return dailyAccountBalance;
	}

	public void setDailyAccountBalance(DailyAccountBalance dailyAccountBalance) {
		this.dailyAccountBalance = dailyAccountBalance;
	}



	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Currency getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(Currency paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public List<ItemTransfer> getItemTransfer() {
		return itemTransfer;
	}

	public void setItemTransfer(List<ItemTransfer> itemTransfer) {
		this.itemTransfer = itemTransfer;
	}

	public Boolean getEmergency() {
		return emergency;
	}
	
}
