package bank.legalEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

import bank.bussinessActivityCode.BusinessActivityCode;
import bank.client.Client;

@Entity
public class LegalEntity extends Client{

	@NotBlank
	private String naziv_klijenta;
	
	@NotBlank
	private String skraceni_naziv_klijenta;
	
	
	private String fax;
	
	@NotBlank
	private String maticni_broj;
	
	@NotBlank
	private String pib;
	
	@NotBlank
	private String naziv_organa;
	
	@NotBlank
	private String nadlezni_poreski_organ_za_klijenta;

	@ManyToOne
	private BusinessActivityCode businessActivityCode;
	
	public LegalEntity(String naziv_klijenta, String skraceni_naziv_klijenta, String fax, String maticni_broj,
			String pib, String naziv_organa, String nadlezni_poreski_organ_za_klijenta) {
		super();
		this.naziv_klijenta = naziv_klijenta;
		this.skraceni_naziv_klijenta = skraceni_naziv_klijenta;
		this.fax = fax;
		this.maticni_broj = maticni_broj;
		this.pib = pib;
		this.naziv_organa = naziv_organa;
		this.nadlezni_poreski_organ_za_klijenta = nadlezni_poreski_organ_za_klijenta;
	}

	public LegalEntity() {
		super();
	}

	public String getNaziv_klijenta() {
		return naziv_klijenta;
	}

	public void setNaziv_klijenta(String naziv_klijenta) {
		this.naziv_klijenta = naziv_klijenta;
	}

	public String getSkraceni_naziv_klijenta() {
		return skraceni_naziv_klijenta;
	}

	public void setSkraceni_naziv_klijenta(String skraceni_naziv_klijenta) {
		this.skraceni_naziv_klijenta = skraceni_naziv_klijenta;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMaticni_broj() {
		return maticni_broj;
	}

	public void setMaticni_broj(String maticni_broj) {
		this.maticni_broj = maticni_broj;
	}

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getNaziv_organa() {
		return naziv_organa;
	}

	public void setNaziv_organa(String naziv_organa) {
		this.naziv_organa = naziv_organa;
	}

	public String getNadlezni_poreski_organ_za_klijenta() {
		return nadlezni_poreski_organ_za_klijenta;
	}

	public void setNadlezni_poreski_organ_za_klijenta(String nadlezni_poreski_organ_za_klijenta) {
		this.nadlezni_poreski_organ_za_klijenta = nadlezni_poreski_organ_za_klijenta;
	}

	public BusinessActivityCode getBusinessActivityCode() {
		return businessActivityCode;
	}

	public void setBusinessActivityCode(BusinessActivityCode businessActivityCode) {
		this.businessActivityCode = businessActivityCode;
	}
	
	
}
