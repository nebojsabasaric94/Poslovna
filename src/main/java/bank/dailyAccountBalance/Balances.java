package bank.dailyAccountBalance;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "statements")
public class Balances {

	@XmlElement
	private ArrayList<DailyAccountBalance> balances;

	public ArrayList<DailyAccountBalance> getBalances() {
		return balances;
	}

	public void setBalances(ArrayList<DailyAccountBalance> balances) {
		this.balances = balances;
	}


	public Balances() {
		super();
		balances= new ArrayList<>();
	}
	
	
}
