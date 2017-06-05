package bank.country;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.currency.Currency;
import bank.place.Place;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "country")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_id")
	@XmlElement
	private Long id;

	@Column(unique = true, columnDefinition = "CHAR(3)")
	@NotBlank(message = "Country code is mandatory")
	@XmlElement
	private String country_code;

	@Column(length = 40, unique = true)
	@NotBlank(message = "Name is mandatory")
	@XmlElement
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@XmlTransient
	private List<Place> places;

	@JsonIgnore
	@OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
	@XmlTransient
	private List<Currency> currencies;

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

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}

}
