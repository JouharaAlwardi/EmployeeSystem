import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class ChangeOption {
	EmployeeRecordDetails cancel = new EmployeeRecordDetails();
	FileActions fileOption = new FileActions();
	CheckInput check = new CheckInput();

	public boolean change = false;

	void checkoptionclicked(ActionEvent e, JButton menuItem) {
		if (e.getSource() == menuItem) {
			returnAction(fileOption);
		}
	}

	void returnAction(FileActions fileOption) {
	}

}

class SaveChanges extends ChangeOption {
	@Override
	void returnAction(FileActions fileOption) {
		if (check.checkInput() && !check.checkForChanges())
			;

	}

}

class CancelChanges extends ChangeOption {
	@Override
	void returnAction(FileActions fileOption) {
		cancel.cancelChange();

	}

}
