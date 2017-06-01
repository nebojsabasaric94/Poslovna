package bank.analyticsOfStatements;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "statements")
public class Statements {
	
	public Statements() {
		super();
		analyticsOfStatements = new ArrayList<AnalyticsOfStatements>();
		// TODO Auto-generated constructor stub
	}
	@XmlElement
	private List<AnalyticsOfStatements> analyticsOfStatements;

	public List<AnalyticsOfStatements> getAnalyticsOfStatements() {
		return analyticsOfStatements;
	}

	public void setAnalyticsOfStatements(List<AnalyticsOfStatements> analyticsOfStatements) {
		this.analyticsOfStatements = analyticsOfStatements;
	}
	
	

}
