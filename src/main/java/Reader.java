import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
/*
 * ==== Reader ====
 * Class to handle file reading
 * 
 */
public class Reader {
	// File name variables
	private String loanFile;
	private String investorFile;
	/**
	 * Constructor 
	 * @param loanFile
	 * @param investorFile
	 */
	public Reader(String loanFile, String investorFile) {
		this.loanFile = loanFile;
		this.investorFile = investorFile;
	}
	/*
	 * Loads the loans into an array list.
	 * 
	 */
	public ArrayList<Loan> loadLoans() {
		// List of loans, to be returned.
		ArrayList<Loan> loans = new ArrayList<Loan>();
		try {
			BufferedReader loanReader = new BufferedReader(new FileReader(loanFile));
			loanReader.readLine(); // Skip the first line of headers
			String line = "";
			while ((line = loanReader.readLine()) != null) {
				String [] detail = line.split(","); 
				/* Add a new loan to the list */
				loans.add(new Loan(
						detail[0], // Loan ID
						Integer.parseInt(detail[1]), // Amount
						detail[2], // Type of loan
						Integer.parseInt(detail[3]), // Term
						LocalDate.parse(detail[4]) // Completion date
				));
			}
		}
		// Exceptions
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} 
		/* Return the list */
		return loans;
	}
	/*
	 * Load the investors into an array list.
	 * 
	 */
	public ArrayList<Investor> loadInvestors() {
		// List of investors to be returned
		ArrayList<Investor> investors = new ArrayList<Investor>();
		try {
			BufferedReader investorReader = new BufferedReader(new FileReader(investorFile));
			investorReader.readLine(); // Skip the first line of headers
			String line = "";
			while ((line = investorReader.readLine()) != null) {
				String [] detail = line.split(",");
				/* Add a new investor to the list */
				investors.add(new Investor(
						detail[0], // Name of investor 
						Integer.parseInt(detail[1]), // Amount investing
						detail[2], // Type of loan
						Integer.parseInt(detail[3]) // Term
				)); 
			}
		}
		// Exceptions
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		/* Return the list */
		return investors;
	}
}