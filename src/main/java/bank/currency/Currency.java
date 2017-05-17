package bank.currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * valuta
 */
@Entity
public class Currency {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "currency_id")
	private Long id;

	@Column(columnDefinition = "CHAR(3)", unique = true)
	@NotBlank(message = "Official code is mandatory")
	private String official_code;

	@Column(unique = true, length = 30)
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Column
	@NotNull
	private boolean domicilna; // sta ovde treba da pise?
	
	/*@ManyToOne(cascade = CascadeType.ALL)
	Country countryCurrency;*/	//drzavna valuta
	

	/*@OneToMany
	@JoinTable(name = "base_currency", joinColumns = @JoinColumn(name = "currency_id"), inverseJoinColumns = @JoinColumn(name = "serial_number"))
	private List<CurrencyRate> baseCurrencyRates; // osnovna valuta
	@OneToMany(mappedBy = "baseCurrencyRates")
	private List<CurrencyRate> baseCurrencyRates;

	@OneToMany
	@JoinTable(name = "by_currency", joinColumns = @JoinColumn(name = "currency_id"), inverseJoinColumns = @JoinColumn(name = "serial_number"))
	private List<CurrencyRate> byCurrencyRates;*/

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

	public boolean isDomicilna() {
		return domicilna;
	}

	public void setDomicilna(boolean domicilna) {
		this.domicilna = domicilna;
	}

	

	/*public Country getCountryCurrency() {
		return countryCurrency;
	}

	public void setCountryCurrency(Country countryCurrency) {
		this.countryCurrency = countryCurrency;
	}*/
	
	


}
