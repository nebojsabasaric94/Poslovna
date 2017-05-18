package bank.suspension;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import bank.legalEntityAccount.LegalEntityAccount;

/**
 * ukidanje
 */
@Entity
public class Suspension {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@Column
	@NotNull
	private Date date;
	
	@Column(length = 20)
	@NotBlank
	private String transferToAccount; //sredstva se prenose na racun

	
	@ManyToOne
	private LegalEntityAccount legalEntityAccount;
	
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

	public String getTransferToAccount() {
		return transferToAccount;
	}

	public void setTransferToAccount(String transferToAccount) {
		this.transferToAccount = transferToAccount;
	}

	public LegalEntityAccount getLegalEntityAccount() {
		return legalEntityAccount;
	}

	public void setLegalEntityAccount(LegalEntityAccount legalEntityAccount) {
		this.legalEntityAccount = legalEntityAccount;
	}
	
	
	
}
