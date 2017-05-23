package bank.exchageRateList;

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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.bank.Bank;
import bank.currencyRate.CurrencyRate;

/**
 * kursna lista
 */
@Entity
public class ExchangeRateList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "exchange_rate_list_id")
	private Long id;

	@Column
	@NotNull
	private Date date;

	@Column(unique = true)
	private int numberOfExchangeRateList; // broj kursne liste

	@Column
	private Date appliedBy; // primenjuje se od

	@JsonIgnore
	@OneToMany(mappedBy = "currencyInList", cascade = CascadeType.ALL)
	private List<CurrencyRate> currencyRates;

	@ManyToOne
	private Bank commercialBankRate; // kurs poslovne banke

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getNumberOfExchangeRateList() {
		return numberOfExchangeRateList;
	}

	public void setNumberOfExchangeRateList(int numberOfExchangeRateList) {
		this.numberOfExchangeRateList = numberOfExchangeRateList;
	}

	public Date getAppliedBy() {
		return appliedBy;
	}

	public void setAppliedBy(Date appliedBy) {
		this.appliedBy = appliedBy;
	}

	public Bank getCommercialBankRate() {
		return commercialBankRate;
	}

	public void setCommercialBankRate(Bank commercialBankRate) {
		this.commercialBankRate = commercialBankRate;
	}

	public List<CurrencyRate> getCurrencyRates() {
		return currencyRates;
	}

	public void setCurrencyRates(List<CurrencyRate> currencyRates) {
		this.currencyRates = currencyRates;
	}

}
