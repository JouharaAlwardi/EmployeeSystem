
public class DisplayDialogs {

	EmployeeGUI parent;
	CheckInput checkInput = new CheckInput();
	EmployeeRecordDetails displayeEmployeeRecords = new EmployeeRecordDetails();

	public void displayEmployeeSummaryDialog() {
		// display Employee summary dialog if these is someone to display
		if (checkInput.isSomeoneToDisplay())
			new EmployeeSummaryDialog(displayeEmployeeRecords.getAllEmloyees());
	}// end displaySummaryDialog

	// display search by ID dialog
	public void displaySearchByIdDialog() {
		if (checkInput.isSomeoneToDisplay())
			new SearchByIdDialog(parent);
	}// end displaySearchByIdDialog

	// display search by surname dialog
	public void displaySearchBySurnameDialog() {
		if (checkInput.isSomeoneToDisplay())
			new SearchBySurnameDialog(parent);
	}// end displaySearchBySurnameDialog

}
