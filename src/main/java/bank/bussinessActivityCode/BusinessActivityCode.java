package bank.bussinessActivityCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class BusinessActivityCode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "business_activity_code_id")
	private Long id;
	
	@NotBlank
	private String code;
	
	@NotBlank
	private String name;

	public BusinessActivityCode() {
		super();
	}

	public BusinessActivityCode(Long id, String code, String name) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}