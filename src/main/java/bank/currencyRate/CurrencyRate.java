package bank.currencyRate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import bank.currency.Currency;
import bank.exchageRateList.ExchangeRateList;

/**
 * Kurs u valuti
 */

@Entity
public class CurrencyRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_rate_id")
	private Long id;
	
	@Column(length = 9, precision = 4)
	//@NotBlank
	private Float buyingExchangeRate;
	
	@Column(length = 9, precision = 4)
	//@NotBlank
	private Float middleExchangeRate;
	
	@Column(length = 9, precision = 4)
	//@NotBlank
	private Float sellingExchangeRate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Currency baseCurrency; //osnovna valuta
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "currency_in_list_id")
	private ExchangeRateList currencyInList; //valute u listi
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Currency accordingToCurrency; // prema valuti
	
	
	public CurrencyRate() {
		super();
	}

	public CurrencyRate(Float buyingExchangeRate, Float middleExchangeRate, Float sellingExchangeRate,
			Currency baseCurrency, ExchangeRateList currencyInList, Currency accordingToCurrency) {
		super();
		this.buyingExchangeRate = buyingExchangeRate;
		this.middleExchangeRate = middleExchangeRate;
		this.sellingExchangeRate = sellingExchangeRate;
		this.baseCurrency = baseCurrency;
		this.currencyInList = currencyInList;
		this.accordingToCurrency = accordingToCurrency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getBuyingExchangeRate() {
		return buyingExchangeRate;
	}

	public void setBuyingExchangeRate(Float buyingExchangeRate) {
		this.buyingExchangeRate = buyingExchangeRate;
	}

	public Float getMiddleExchangeRate() {
		return middleExchangeRate;
	}

	public void setMiddleExchangeRate(Float middleExchangeRate) {
		this.middleExchangeRate = middleExchangeRate;
	}

	public Float getSellingExchangeRate() {
		return sellingExchangeRate;
	}

	public void setSellingExchangeRate(Float sellingExchangeRate) {
		this.sellingExchangeRate = sellingExchangeRate;
	}

	public Currency getBaseCurrency() {
		return baseCurrency;
	}

	public void setBaseCurrency(Currency baseCurrency) {
		this.baseCurrency = baseCurrency;
	}

	public ExchangeRateList getCurrencyInList() {
		return currencyInList;
	}

	public void setCurrencyInList(ExchangeRateList currencyInList) {
		this.currencyInList = currencyInList;
	}

	public Currency getAccordingToCurrency() {
		return accordingToCurrency;
	}

	public void setAccordingToCurrency(Currency accordingToCurrency) {
		this.accordingToCurrency = accordingToCurrency;
	}
}
