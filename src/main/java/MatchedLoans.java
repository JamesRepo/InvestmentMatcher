import java.util.HashMap;
/*
 * ==== Matched Loans ====
 * Class to store the matched loans
 *
 */
public class MatchedLoans {
	/* Variables */
	private String loanID;
	private int totalFunded;
	private HashMap<String, Integer> investors;
	/**
	 * Constructor
	 * 
	 * @param loanID
	 * @param total
	 * @param investors
	 */
	public MatchedLoans(String loanID, int total, HashMap<String, Integer> investors) {
		this.loanID = loanID;
		this.totalFunded = total;
		this.investors = investors;
	}
	/*
	 * Get methods
	 */
	public String getLoanID() {
		return loanID;
	}	
	public int getTotalFunded() {
		return totalFunded;
	}
	public HashMap<String, Integer> getInvestors() {
		return investors;
	}
}