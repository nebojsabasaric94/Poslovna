package bank.interbankTransfer;

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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bank.bank.Bank;
import bank.itemTransfer.ItemTransfer;

/**
 * medjubankarski prenos
 */

@Entity
public class InterbankTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long idMessage;

	@Column(columnDefinition = "CHAR(5)")
	@NotBlank
	private String typeOfMessage;

	@Column
	@NotNull
	private Date date;

	@Column
	//@NotBlank
	private Float sum;
	
	@ManyToOne
	private Bank senderBank;

	@ManyToOne
	private Bank bank;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "interbankTransfer",cascade = CascadeType.ALL)
	private List<ItemTransfer> itemTransfers; 
	
	
	public InterbankTransfer() {
		super();
	}

	public Long getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}

	public String getTypeOfMessage() {
		return typeOfMessage;
	}

	public void setTypeOfMessage(String typeOfMessage) {
		this.typeOfMessage = typeOfMessage;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getSum() {
		return sum;
	}

	public void setSum(Float sum) {
		this.sum = sum;
	}

	public Bank getSenderBank() {
		return senderBank;
	}

	public void setSenderBank(Bank senderBank) {
		this.senderBank = senderBank;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<ItemTransfer> getItemTransfers() {
		return itemTransfers;
	}

	public void setItemTransfers(List<ItemTransfer> itemTransfers) {
		this.itemTransfers = itemTransfers;
	}
	
	

}
