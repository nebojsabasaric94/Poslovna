package bank.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_id")
	private Long id;

	@Column(unique = true, columnDefinition = "CHAR(3)")
	@NotBlank(message = "Country code is mandatory")
	private String country_code;

	@Column(length = 40, unique = true)
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	/*@OneToMany
	@JoinTable(name = "national_currency", joinColumns = @JoinColumn(name = "country_id"), inverseJoinColumns = @JoinColumn(name = "currency_id"))
	private List<Currency> currencies;*/

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}*/
	
	

}
