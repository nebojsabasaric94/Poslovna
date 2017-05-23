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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.bank.Bank;
import bank.client.Client;
import bank.currency.Currency;
import bank.suspension.Suspension;

@Entity
public class LegalEntityAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true, columnDefinition = "CHAR(18)")
	@NotBlank
	private String brojRacuna;

	
	private Date datumOtvaranja;
	
	private boolean vazeci;

	@ManyToOne
	private Client client;

	@ManyToOne
	private Bank bank;

	@ManyToOne
	private Currency currency;

	@JsonIgnore
	@OneToMany(mappedBy = "legalEntityAccount", cascade = CascadeType.ALL)
	private List<Suspension> suspensions;

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

	public boolean isVazeci() {
		return vazeci;
	}

	public void setVazeci(boolean vazeci) {
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

}
