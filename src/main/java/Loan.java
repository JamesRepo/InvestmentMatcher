import java.time.LocalDate;
/*
 * ==== Loan ====
 * Class to store date on the different loans
 * 
 */
public class Loan implements Comparable<Loan> {
	/* Instance variables */
	private String loanID; // Loan ID stored as a string as integer representation is not needed
	private int originalAmount;
	private int amount; 
	private String type;
	private int term;
	private LocalDate completionDate;
	/**
	 * Constructor
	 * 
	 * @param loanID
	 * @param amount
	 * @param type
	 * @param term
	 * @param date
	 */
	public Loan(String loanID, int amount, String type, int term, LocalDate date) {
		this.loanID = loanID;
		this.originalAmount = amount;
		this.amount = amount;
		this.type = type;
		this.term = term; 
		this.completionDate = date;
	}
	/*
	 * Get methods
	 */
	public String getLoanID() {
		return loanID;
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
	public LocalDate getCompletionDate() {
		return completionDate;
	}
	/*
	 * Set methods
	 */
	public void setAmount(int newAmount) {
		amount = newAmount;
	}
	/*
	 * Implement compareTo to ensure loans will be funded in order of oldest to newest
	 */
	public int compareTo(Loan loan) {
		int comp = completionDate.compareTo(loan.getCompletionDate());
		return comp;
	}
}