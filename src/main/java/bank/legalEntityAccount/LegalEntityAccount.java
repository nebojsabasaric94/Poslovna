package bank.legalEntityAccount;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.bank.Bank;
import bank.client.Client;
import bank.currency.Currency;
import bank.dailyAccountBalance.DailyAccountBalance;
import bank.suspension.Suspension;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "legalEntityAccount")
public class LegalEntityAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@XmlElement
	private Long id;

	@Column(unique = true, columnDefinition = "CHAR(18)")
	@NotBlank
	@XmlElement
	private String brojRacuna;

	@XmlElement
	private Date datumOtvaranja;
	
	@XmlElement
	private Boolean vazeci;

	@ManyToOne
	@XmlElement
	private Client client;

	@ManyToOne
	@XmlElement
	private Bank bank;

	@ManyToOne
	@XmlElement
	private Currency currency;

	@JsonIgnore
	@OneToMany(mappedBy = "legalEntityAccount", cascade = CascadeType.ALL)
	@XmlTransient
	private List<Suspension> suspensions;
	
	@JsonIgnore
	@OneToMany(mappedBy = "legalEntityAccount", cascade = CascadeType.ALL)
	@XmlTransient
	private List<DailyAccountBalance> dailyAccountBalances;

	public LegalEntityAccount() {
		super();
	}

	
	public LegalEntityAccount(Long id, String brojRacuna, Date datumOtvaranja, boolean vazeci, Client client,
			Bank bank, Currency currency, List<Suspension> suspensions) {
		super();
		this.id = id;
		this.brojRacuna = brojRacuna;
		this.datumOtvaranja = datumOtvaranja;
		this.vazeci = vazeci;
		this.client = client;
		this.bank = bank;
		this.currency = currency;
		this.suspensions = suspensions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public Date getDatumOtvaranja() {
		return datumOtvaranja;
	}

	public void setDatumOtvraranja(Date datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}



	public Boolean getVazeci() {
		return vazeci;
	}


	public void setVazeci(Boolean vazeci) {
		this.vazeci = vazeci;
	}


	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public List<Suspension> getSuspensions() {
		return suspensions;
	}

	public void setSuspensions(List<Suspension> suspensions) {
		this.suspensions = suspensions;
	}


	public List<DailyAccountBalance> getDailyAccountBalances() {
		return dailyAccountBalances;
	}


	public void setDailyAccountBalances(List<DailyAccountBalance> dailyAccountBalances) {
		this.dailyAccountBalances = dailyAccountBalances;
	}


	public void setDatumOtvaranja(Date datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}
	
	

}
