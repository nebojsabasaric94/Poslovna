package bank.legalEntityAccount;

public class AccountForBank {

	private String client;
	
	private String accountNumber;
	
	private String money;
	
	private String currency;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public AccountForBank(String client, String accountNumber, String money, String currency) {
		super();
		this.client = client;
		this.accountNumber = accountNumber;
		this.money = money;
		this.currency = currency;
	}
	
	
	
	
}
