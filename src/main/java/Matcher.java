import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
/*
 * ==== Investment Matcher ====
 * Class which facilitates the matching of loans to investors. 
 * 
 */
public class Matcher {
	/* Variables */
	private ArrayList<Loan> loans;
	private ArrayList<Investor> investors;
	// Separate loan lists for fixed and tracker
	private ArrayList<Loan> fixedLoans, trackerLoans;
	// Separate investors lists
	private ArrayList<Investor> fixedInvestors, trackerInvestors;
	// Stores the matched loans
	private ArrayList<MatchedLoans> matchedLoans;
	/**
	 * Constructor
	 * 
	 * @param loans
	 * @param investors
	 */
	public Matcher(ArrayList<Loan> loans, ArrayList<Investor> investors) {
		this.loans = loans;
		this.investors = investors;
		// Initialise array lists used in this class
		fixedLoans = new ArrayList<Loan>();
		trackerLoans = new ArrayList<Loan>();
		fixedInvestors = new ArrayList<Investor>();
		trackerInvestors = new ArrayList<Investor>();
		matchedLoans = new ArrayList<MatchedLoans>();
	}
	/*
	 * Method called in the main to process the matching.
	 * Populates specific arrays and calls the matching method. 
	 * 
	 */
	public void processMatching() {
		// Splits Fixed and Tracker loans
		for (Loan loan : loans) {
			if (loan.getType().equals("FIXED")) {
				fixedLoans.add(loan);
			}
			else {
				trackerLoans.add(loan);
			}
		}
		// Splits Fixed and Tracker investments
		for (Investor investor : investors) {
			if (investor.getType().equals("FIXED")) {
				fixedInvestors.add(investor);
			}
			else {
				trackerInvestors.add(investor);
			}
		}
		// Sorts the loan lists so they are processed oldest to newest
		Collections.sort(fixedLoans);
		Collections.sort(trackerLoans);
		// Calls the match loan functions
		matchLoans(fixedLoans, fixedInvestors);
		matchLoans(trackerLoans, trackerInvestors);
	}
	/**
	 * Match loans to investments
	 * 
	 * @param loans
	 * @param investors
	 */
	public void matchLoans(ArrayList<Loan> loans, ArrayList<Investor> investors) {
		// Two for loops to cycle through every investor for every loan
		for (Loan loan : loans) {
			// String = The investor. Integer = Amount invested.
			HashMap<String, Integer> investments = new HashMap<String, Integer>();
			/* Used to keep track of which investors are out of funds. We need this because they have to be 
			 * removed outside of the loop to stop it messing up. */
			ArrayList<Investor> removeInvestor = new ArrayList<Investor>();
			for (Investor investor : investors) {
				// If the loan has been funded stop cycling through investors
				if (loan.getAmount() == 0) {
					break;
				}
				// Make sure investors have a longer term than the loan
				if (loan.getTerm() < investor.getTerm()) {
					// If an investor has more funds than the loan needs
					if (loan.getAmount() <= investor.getAmount()) {
						// Subtract the amount funded from the investors total
						investor.setAmount(investor.getAmount() - loan.getAmount());
						// Add details to the map
						investments.put(investor.getName(), loan.getAmount());
						// Loan is now funded
						loan.setAmount(0);
						break;
					}
					// An investor can only part fund a loan
					else {
						// Subtract amount loan needs to be funded. 
						loan.setAmount(loan.getAmount() - investor.getAmount());
						// Add the investor to the map and to the removal list. 
						investments.put(investor.getName(), investor.getAmount());
						removeInvestor.add(investor);
					}
				}
			}
			// When a loan is funded. Add it to the matched loans list and remove those investors from the list
			if (loan.getAmount() == 0) {
				matchedLoans.add(new MatchedLoans(loan.getLoanID(), loan.getOriginalAmount(), investments));
				for (Investor investor : removeInvestor) {
					investors.remove(investor);
				}
			}
		}
	}
	/*
	 * Used to print out the loans and their details to the console.
	 * 
	 */
	public void printMatchedLoans() {
		for (MatchedLoans match : matchedLoans) {
			System.out.print("Loan ID: " + match.getLoanID() + "\r\nAmount funded: " + match.getTotalFunded() + "\r\n----------------\r\n");
			for (String key : match.getInvestors().keySet()) {
				System.out.print(key + ": " + match.getInvestors().get(key) + "\r\n");
			}
			System.out.println("\r\n");
		}	
	}
	/*
	 * Prints a JSON representation and produced a file output of the matched loans.
	 * 
	 */
	public void printJSONrepresentation() {
		System.out.println(new Gson().toJson(matchedLoans));
		try (FileWriter writer = new FileWriter("matched_loans.json")) {
			Gson gson = new GsonBuilder().create();
			gson.toJson(matchedLoans, writer);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Get methods
	 */
	public ArrayList<Loan> getFixedLoans() {
		return fixedLoans;
	}
	public ArrayList<Loan> getTrackerLoans() {
		return trackerLoans;
	}
	public ArrayList<Investor> getFixedInvest() {
		return fixedInvestors;
	}
	public ArrayList<Investor> getTrackerInvest() {
		return trackerInvestors;
	}
	public ArrayList<MatchedLoans> getMatched() {
		return matchedLoans;
	}
}