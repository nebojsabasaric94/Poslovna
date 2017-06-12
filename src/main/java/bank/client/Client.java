package bank.client;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.legalEntityAccount.LegalEntityAccount;
import bank.place.Place;

@Entity
@Inheritance(strategy  = InheritanceType.JOINED)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")	
	@XmlElement
	private Long id;
	
	@NotBlank
	@XmlElement
	private String address;
	
	@NotBlank
	@XmlElement
	private String phone;
	
	@XmlElement
	private String email;
	
	@XmlElement
	private String addressForStatements;
	
	@XmlElement
	private Boolean emailStatements;
	
	@NotBlank
	@XmlElement
	private String firstName;
	
	@NotBlank
	@XmlElement
	private String lastName;
	
	@Column(unique = true)
	@NotBlank
	@XmlElement
	private String jmbg;
	
	@NotBlank
	@XmlElement
	private String typeOfClient;

	//@NotBlank
	@ManyToOne
	@XmlElement
	private Place residence;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	@XmlTransient
	private List<LegalEntityAccount> legalEntityAccount;
	
	public Client() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddressForStatements() {
		return addressForStatements;
	}

	public void setAddressForStatements(String addressForStatements) {
		this.addressForStatements = addressForStatements;
	}


	public Boolean getEmailStatements() {
		return emailStatements;
	}

	public void setEmailStatements(Boolean emailStatements) {
		this.emailStatements = emailStatements;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getTypeOfClient() {
		return typeOfClient;
	}

	public void setTypeOfClient(String typeOfClient) {
		this.typeOfClient = typeOfClient;
	}


	public Place getResidence() {
		return residence;
	}



	public void setResidence(Place residence) {
		this.residence = residence;
	}



	public List<LegalEntityAccount> getLegalEntityAccount() {
		return legalEntityAccount;
	}



	public void setLegalEntityAccount(List<LegalEntityAccount> legalEntityAccount) {
		this.legalEntityAccount = legalEntityAccount;
	}
	
	
	
}
