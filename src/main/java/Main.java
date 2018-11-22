/*
 * ==== Main ====
 * 
 */
public class Main {
	public static void main(String[] args) {
		/* Create reader object and pass file names. These must be hard coded in */
		Reader reader = new Reader("src/main/resources/loans.csv", "src/main/resources/investmentRequests.csv");
		/* Investment matched object created. The two arguments are array lists containing the details on loans and investors from the files */
		Matcher investmentMatcher = new Matcher(reader.loadLoans(), reader.loadInvestors());
		/* Does the matching process */
		investmentMatcher.processMatching();
		/* Prints the matched loan's ID, total amount, and a list of investors and how much they invested */
		investmentMatcher.printMatchedLoans();
		/* Prints a JSON representation and produces a file */
		investmentMatcher.printJSONrepresentation();
	}
}