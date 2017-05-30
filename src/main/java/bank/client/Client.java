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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.legalEntityAccount.LegalEntityAccount;
import bank.place.Place;

@Entity
@Inheritance(strategy  = InheritanceType.JOINED)
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id")	
	private Long id;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String phone;
	
	private String email;
	
	private String addressForStatements;
	
	private boolean emailStatements;
	
	@NotBlank
	private String firstName;
	
	@NotBlank
	private String lastName;
	
	@Column(unique = true)
	@NotBlank
	private String jmbg;
	
	@NotBlank
	private String typeOfClient;

	//@NotBlank
	@ManyToOne
	private Place residence;
	
	@JsonIgnore
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
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

	public boolean isEmailStatements() {
		return emailStatements;
	}

	public void setEmailStatements(boolean emailStatements) {
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
