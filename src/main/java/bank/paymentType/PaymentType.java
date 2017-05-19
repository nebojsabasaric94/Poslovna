package bank.paymentType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

/**
 * vrste placanja
 */

@Entity
public class PaymentType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//treba promeniti u number, ali ne znam koji tip tu da koristimo
	@Column(unique = true)
	private String code;
	
	@Column(length = 120)
	@NotBlank
	private String nameOfPaymentType;

	
	
	public PaymentType() {
		super();
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getNameOfPaymentType() {
		return nameOfPaymentType;
	}



	public void setNameOfPaymentType(String nameOfPaymentType) {
		this.nameOfPaymentType = nameOfPaymentType;
	}


	
	
	
}
