import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class NavigateMenuOption {

	DisplayRecords empRecord;
	NavigateRecords navigate;
	SearchRecords serach;

	DisplayDialogs displayDialog = new DisplayDialogs();

	CheckInput check = new CheckInput();
	Employee currentEmployee;

	void checkOptionClicked(ActionEvent e, JMenuItem first, JButton secound) {
		if (e.getSource() == first || e.getSource() == secound) {
			if (check.checkInput() && !check.checkForChanges())
				returnAction(navigate);
			empRecord.displayRecords(currentEmployee);

		}
	}

	void checkOptionClickedSearch(ActionEvent e, JMenuItem first) {
		if (e.getSource() == first) {
			if (check.checkInput() && !check.checkForChanges())
				returnActionDialogs(displayDialog);

		}
	}

	void returnAction(NavigateRecords navigate) {

	}

	void returnActionDialogs(DisplayDialogs displayDialog) {

	}

	void returnActionDialogs() {

	}

}

class SearchById extends NavigateMenuOption {
	@Override
	void returnActionDialogs(DisplayDialogs displayDialog) {

		displayDialog.displaySearchByIdDialog();

	}

}

class SearchBySurname extends NavigateMenuOption {

	EmployeeDetails parent;

	@Override
	void returnActionDialogs(DisplayDialogs displayDialog) {
		displayDialog.displaySearchByIdDialog();

	}

	@Override
	void returnActionDialogs() {

		new SearchBySurnameDialog(parent);

	}

}

class DisplayEmployees extends NavigateMenuOption {
	@Override
	void returnActionDialogs(DisplayDialogs displayDialog) {

		if (check.isSomeoneToDisplay())
			displayDialog.displayEmployeeSummaryDialog();

	}

}

class FirstRecord extends NavigateMenuOption {
	@Override
	void returnAction(NavigateRecords navigate) {
		navigate.firstRecord();

	}

}

class PreviousRecord extends NavigateMenuOption {
	@Override
	void returnAction(NavigateRecords navigate) {
		navigate.previousRecord();

	}

}

class NextRecord extends NavigateMenuOption {
	@Override
	void returnAction(NavigateRecords navigate) {
		navigate.nextRecord();

	}

}

class LastRecord extends NavigateMenuOption {
	@Override
	void returnAction(NavigateRecords navigate) {
		navigate.lastRecord();

	}

}
