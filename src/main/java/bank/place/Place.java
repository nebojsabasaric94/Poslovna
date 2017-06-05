package bank.place;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.client.Client;
import bank.country.Country;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "place")
public class Place {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@XmlElement
	Long id;
	
	@Column(columnDefinition = "CHAR(5)")
	@XmlElement
	String pttNumber;
	
	@Column(length = 50)
	@XmlElement
	String name;
	
	@ManyToOne
	@XmlElement
	private Country country;
	
	@JsonIgnore
	@OneToMany(mappedBy = "residence", cascade = CascadeType.ALL)
	@XmlTransient
	private List<Client> clients;
	
	@JsonIgnore
	@OneToMany(mappedBy = "place", cascade = CascadeType.ALL)
	@XmlTransient
	private List<AnalyticsOfStatements> analyticsOfStatements;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPttNumber() {
		return pttNumber;
	}

	public void setPttNumber(String pttNumber) {
		this.pttNumber = pttNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<AnalyticsOfStatements> getAnalyticsOfStatements() {
		return analyticsOfStatements;
	}

	public void setAnalyticsOfStatements(List<AnalyticsOfStatements> analyticsOfStatements) {
		this.analyticsOfStatements = analyticsOfStatements;
	}
	
	

}
