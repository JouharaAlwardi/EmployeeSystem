import java.awt.Color;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CheckInput {

	RandomFile application = new RandomFile();
	File file;
	Employee currentEmployee;
	JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	private boolean change = false;
	EditTextField edit;
	DisplayRecords display;
	FileActions save;
	long currentByteStart = 0;

	// check if any of records in file is active - ID is not 0
	public boolean isSomeoneToDisplay() {
		boolean someoneToDisplay = false;
		application.openReadFile(file.getAbsolutePath());
		someoneToDisplay = application.isSomeoneToDisplay();
		application.closeReadFile();
		if (!someoneToDisplay) {
			emptyTextFields();
			JOptionPane.showMessageDialog(null, "No Employees registered!");

		}
		return someoneToDisplay;
	}

	public void emptyTextFields() {
		currentEmployee = null;
		idField.setText("");
		ppsField.setText("");
		surnameField.setText("");
		firstNameField.setText("");
		salaryField.setText("");
		genderCombo.setSelectedIndex(0);
		departmentCombo.setSelectedIndex(0);
		fullTimeCombo.setSelectedIndex(0);

	}

	// check for correct PPS format and look if PPS already in use
	public boolean correctPps(String pps, long currentByte) {
		boolean ppsExist = false;

		if (pps.length() == 8 || pps.length() == 9) {
			if (Character.isDigit(pps.charAt(0)) && Character.isDigit(pps.charAt(1)) && Character.isDigit(pps.charAt(2))
					&& Character.isDigit(pps.charAt(3)) && Character.isDigit(pps.charAt(4))
					&& Character.isDigit(pps.charAt(5)) && Character.isDigit(pps.charAt(6))
					&& Character.isLetter(pps.charAt(7)) && (pps.length() == 8 || Character.isLetter(pps.charAt(8)))) {

				application.openReadFile(file.getAbsolutePath());

				ppsExist = application.isPpsExist(pps, currentByte);
				application.closeReadFile();
			} else
				ppsExist = true;
		} else
			ppsExist = true;

		return ppsExist;
	}

	// check if file name has extension .dat
	public boolean checkFileName(File fileName) {
		boolean checkFile = false;
		int length = fileName.toString().length();
		if (fileName.toString().charAt(length - 4) == '.' && fileName.toString().charAt(length - 3) == 'd'
				&& fileName.toString().charAt(length - 2) == 'a' && fileName.toString().charAt(length - 1) == 't')
			checkFile = true;
		return checkFile;
	}

	// check if any changes text field where made
	public boolean checkForChanges() {
		boolean anyChanges = false;
		if (change) {
			save.saveChanges();
			anyChanges = true;
		}

		else {
			edit.setEnabled(false);
			display.displayRecords(currentEmployee);
		}

		return anyChanges;
	}

	public boolean checkInput() {
		boolean valid = true;

		if (ppsField.isEditable() && ppsField.getText().trim().isEmpty()) {
			ppsField.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if

		if (ppsField.isEditable() && correctPps(ppsField.getText().trim(), currentByteStart)) {
			ppsField.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if
		if (surnameField.isEditable() && surnameField.getText().trim().isEmpty()) {
			surnameField.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if
		if (firstNameField.isEditable() && firstNameField.getText().trim().isEmpty()) {
			firstNameField.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if
		if (genderCombo.getSelectedIndex() == 0 && genderCombo.isEnabled()) {
			genderCombo.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if
		if (departmentCombo.getSelectedIndex() == 0 && departmentCombo.isEnabled()) {
			departmentCombo.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if
		try {// try to get values from text field
			Double.parseDouble(salaryField.getText());
			// check if salary is greater than 0
			if (Double.parseDouble(salaryField.getText()) < 0) {
				salaryField.setBackground(new Color(255, 150, 150));
				valid = false;
			} // end if
		} // end try
		catch (NumberFormatException num) {
			if (salaryField.isEditable()) {
				salaryField.setBackground(new Color(255, 150, 150));
				valid = false;
			} // end if
		} // end catch
		if (fullTimeCombo.getSelectedIndex() == 0 && fullTimeCombo.isEnabled()) {
			fullTimeCombo.setBackground(new Color(255, 150, 150));
			valid = false;
		} // end if
			// display message if any input or format is wrong
		if (!valid)
			JOptionPane.showMessageDialog(null, "Wrong values or format! Please check!");
		// set text field to white colour if text fields are editable
		if (ppsField.isEditable())
			edit.setToWhite();

		return valid;
	}

}
