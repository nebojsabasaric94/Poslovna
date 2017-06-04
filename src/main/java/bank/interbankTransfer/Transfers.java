package bank.interbankTransfer;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "transfers")
public class Transfers {

	@XmlElement
	private List<InterbankTransfer> interbankTransfers;

	public List<InterbankTransfer> getInterbankTransfers() {
		return interbankTransfers;
	}

	public void setInterbankTransfers(List<InterbankTransfer> interbankTransfers) {
		this.interbankTransfers = interbankTransfers;
	}

	public Transfers(List<InterbankTransfer> interbankTransfers) {
		super();
		this.interbankTransfers = interbankTransfers;
	}

	public Transfers() {
		super();
		this.interbankTransfers = new ArrayList<InterbankTransfer>();
	}
	
	
}
