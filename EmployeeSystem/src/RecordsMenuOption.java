import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

public class RecordsMenuOption {

	EmployeeRecordDetails recordDetails;
	EmployeeDetails parent;
	CheckInput check = new CheckInput();
	Employee currentEmployee;

	void checkOptionClicked(ActionEvent e, JMenuItem first, JMenuItem secound) {
		if (e.getSource() == first || e.getSource() == secound) {
			if (check.checkInput() && !check.checkForChanges())
				returnAction(recordDetails);

		}
	}

	void returnAction(EmployeeRecordDetails recordDetails) {

	}

	void returnAction(EmployeeDetails parent) {

	}

}

class AddRecord extends RecordsMenuOption {
	@Override
	void returnAction(EmployeeDetails parent) {
		new AddRecordDialog(parent);

	}

}

class ModifyRecord extends RecordsMenuOption {
	@Override
	void returnAction(EmployeeRecordDetails recordDetails) {
		recordDetails.editDetails();

	}

}

class DeleteRecord extends RecordsMenuOption {
	@Override
	void returnAction(EmployeeRecordDetails recordDetails) {
		recordDetails.deleteRecord();

	}

}
