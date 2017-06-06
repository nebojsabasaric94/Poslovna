package bank.dailyAccountBalance;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.legalEntityAccount.LegalEntityAccount;

/**
 * dnevno stanje racuna
 */


@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "dailyAccountBalance")
public class DailyAccountBalance {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id; //broj izvoda
	
	@Column
	@NotNull
	@XmlElement
	private Date trafficDate; //datum prometa
	
	@Column(length = 15, precision = 2)
	//@NotBlank
	@XmlElement
	private Float previousState;  // prethodno stanje
	
	@Column(length = 15, precision = 2)
	//@NotBlank
	@XmlElement
	private Float trafficToBenefit; //promet u korist
	
	@Column(length = 15, precision = 2)
	//@NotBlank
	@XmlElement
	private Float trafficToTheBurden; //promet na teret
	
	@Column(length = 15, precision = 2)
	//@NotBlank
	@XmlElement
	private Float newState;
	
	@ManyToOne
	private LegalEntityAccount legalEntityAccount;
	
	@JsonIgnore
	@OneToMany(mappedBy = "dailyAccountBalance", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@XmlElement
	private List<AnalyticsOfStatements>analyticsOfStatements;

	

	
	
	public DailyAccountBalance() {
		super();
	}
	
	public DailyAccountBalance(Date trafficDate, Float previousState, Float trafficToBenefit, Float trafficToTheBurden,
			Float newState) {
		super();
		this.trafficDate = trafficDate;
		this.previousState = previousState;
		this.trafficToBenefit = trafficToBenefit;
		this.trafficToTheBurden = trafficToTheBurden;
		this.newState = newState;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTrafficDate() {
		return trafficDate;
	}

	public void setTrafficDate(Date trafficDate) {
		this.trafficDate = trafficDate;
	}

	public Float getPreviousState() {
		return previousState;
	}

	public void setPreviousState(Float previousState) {
		this.previousState = previousState;
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

	public Float getNewState() {
		return newState;
	}

	public void setNewState(Float newState) {
		this.newState = newState;
	}

	public LegalEntityAccount getLegalEntityAccount() {
		return legalEntityAccount;
	}

	public void setLegalEntityAccount(LegalEntityAccount legalEntityAccount) {
		this.legalEntityAccount = legalEntityAccount;
	}

	public List<AnalyticsOfStatements> getAnalyticsOfStatements() {
		return analyticsOfStatements;
	}

	public void setAnalyticsOfStatements(List<AnalyticsOfStatements> analyticsOfStatements) {
		this.analyticsOfStatements = analyticsOfStatements;
	}


	
	
	
	
	
}
