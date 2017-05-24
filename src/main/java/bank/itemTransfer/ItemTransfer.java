package bank.itemTransfer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import bank.analyticsOfStatements.AnalyticsOfStatements;
import bank.interbankTransfer.InterbankTransfer;

/**
 * stavke prenosa
 */

@Entity
public class ItemTransfer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;
	
	@ManyToOne
	private AnalyticsOfStatements analyticsOfStatements;
	
	@ManyToOne
	private InterbankTransfer interbankTransfer;

	public ItemTransfer() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public AnalyticsOfStatements getAnalyticsOfStatements() {
		return analyticsOfStatements;
	}

	public void setAnalyticsOfStatements(AnalyticsOfStatements analyticsOfStatements) {
		this.analyticsOfStatements = analyticsOfStatements;
	}

	public InterbankTransfer getInterbankTransfer() {
		return interbankTransfer;
	}

	public void setInterbankTransfer(InterbankTransfer interbankTransfer) {
		this.interbankTransfer = interbankTransfer;
	}
	
	
	
	
	
}
