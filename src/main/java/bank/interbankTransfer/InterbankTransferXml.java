package bank.interbankTransfer;

import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bank.analyticsOfStatements.AddaptDate;
import bank.analyticsOfStatements.AnalyticsOfStatementsXml;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "interbankTransfer")
public class InterbankTransferXml {


	@XmlElement
	private String typeOfMessage;


	@XmlJavaTypeAdapter(AddaptDate.class)
	@XmlElement
	private Date date;

	@XmlElement
	private Float sum;
	
	@XmlElement
	private String senderBankCode;

	@XmlElement
	private String recieverBankCode;
	
	
	@XmlElement
	private List<AnalyticsOfStatementsXml> statements; 
	
	
	public InterbankTransferXml() {
		super();
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






	public String getSenderBankCode() {
		return senderBankCode;
	}



	public void setSenderBankCode(String senderBankCode) {
		this.senderBankCode = senderBankCode;
	}



	public String getRecieverBankCode() {
		return recieverBankCode;
	}



	public void setRecieverBankCode(String recieverBankCode) {
		this.recieverBankCode = recieverBankCode;
	}



	public List<AnalyticsOfStatementsXml> getStatements() {
		return statements;
	}

	public void setStatements(List<AnalyticsOfStatementsXml> statements) {
		this.statements = statements;
	}


	
	

}

