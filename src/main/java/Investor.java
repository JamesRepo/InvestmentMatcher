/*
 * ==== Investor ====
 * Class to store data on the investors.
 * 
 */
public class Investor {
	/* Variables */
	private String name;
	private int originalAmount;
	private int amount;
	private String type;
	private int term;
	/**
	 * Constructor
	 * 
	 * @param name
	 * @param amount
	 * @param type
	 * @param term
	 */
	public Investor(String name, int amount, String type, int term) {
		this.name = name;
		this.originalAmount = amount;
		this.amount = amount;
		this.type = type;
		this.term = term;
	}
	/*
	 * Get methods
	 */
	public String getName() {
		return name;
	}
	public int getOriginalAmount() {
		return originalAmount;
	}
	public int getAmount() {
		return amount;
	}
	public String getType() {
		return type;
	}
	public int getTerm() {
		return term;
	}
	/*
	 * Set methods
	 */
	public void setAmount(int newAmount) {
		amount = newAmount;	
	}
}