package bank.currency;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.country.Country;
import bank.currencyRate.CurrencyRate;
import bank.legalEntityAccount.LegalEntityAccount;

/**
 * valuta
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "paymentCurrency")
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_id")
	@XmlElement
	private Long id;

	@Column(columnDefinition = "CHAR(3)", unique = true)
	@NotBlank(message = "Official code is mandatory")
	@XmlElement
	private String official_code;

	@Column(unique = true, length = 30)
	@NotBlank(message = "Name is mandatory")
	@XmlElement
	private String name;

	@Column
	@NotNull
	@XmlElement
	private Boolean domicilna; // sta ovde treba da pise?

	@ManyToOne
	private Country country;

	@JsonIgnore
	@OneToMany(mappedBy = "currency", cascade = CascadeType.ALL)
	@XmlTransient
	private List<LegalEntityAccount> legalEntityAccount;

	@JsonIgnore
	@OneToMany(mappedBy = "paymentCurrency", cascade = CascadeType.ALL)
	@XmlTransient
	private List<AnalyticsOfStatements> analyticsOfStatements;
	
	@JsonIgnore
	@OneToMany(mappedBy = "baseCurrency", cascade = CascadeType.ALL)
	@XmlTransient
	private List<CurrencyRate> baseCurrencyRate;
	
	@JsonIgnore
	@OneToMany(mappedBy = "accordingToCurrency", cascade = CascadeType.ALL)
	@XmlTransient
	private List<CurrencyRate> accordingToCurrencyRate;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOfficial_code() {
		return official_code;
	}

	public void setOfficial_code(String official_code) {
		this.official_code = official_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public Boolean getDomicilna() {
		return domicilna;
	}

	public void setDomicilna(Boolean domicilna) {
		this.domicilna = domicilna;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<LegalEntityAccount> getLegalEntityAccount() {
		return legalEntityAccount;
	}

	public void setLegalEntityAccount(List<LegalEntityAccount> legalEntityAccount) {
		this.legalEntityAccount = legalEntityAccount;
	}

	public List<AnalyticsOfStatements> getAnalyticsOfStatements() {
		return analyticsOfStatements;
	}

	public void setAnalyticsOfStatements(List<AnalyticsOfStatements> analyticsOfStatements) {
		this.analyticsOfStatements = analyticsOfStatements;
	}

	public List<CurrencyRate> getBaseCurrencyRate() {
		return baseCurrencyRate;
	}

	public void setBaseCurrencyRate(List<CurrencyRate> baseCurrencyRate) {
		this.baseCurrencyRate = baseCurrencyRate;
	}

	public List<CurrencyRate> getAccordingToCurrencyRate() {
		return accordingToCurrencyRate;
	}

	public void setAccordingToCurrencyRate(List<CurrencyRate> accordingToCurrencyRate) {
		this.accordingToCurrencyRate = accordingToCurrencyRate;
	}
	
	

}
