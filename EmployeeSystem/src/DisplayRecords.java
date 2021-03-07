import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class DisplayRecords {

	JTextField searchByIdField, searchBySurnameField;

	private boolean change = false;

	private static final DecimalFormat format = new DecimalFormat("\u20ac ###,###,##0.00");
	String[] gender = { "", "M", "F" };
	// department combo box values
	String[] department = { "", "Administration", "Production", "Transport", "Management" };
	// full time combo box values
	String[] fullTime = { "", "Yes", "No" };

	private JButton first, previous, next, last, add, edit, deleteButton, displayAll, searchId, searchSurname,
			saveChange, cancelChange;

	private JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	private JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;

	// display current Employee details
	public void displayRecords(Employee thisEmployee) {
		int countGender = 0;
		int countDep = 0;
		boolean found = false;

		searchByIdField.setText("");
		searchBySurnameField.setText("");

		if (thisEmployee == null || thisEmployee.getEmployeeId() == 0) {
		} else {

			findGenderValue(countGender, found, thisEmployee);
			found = false;
			findDeptartmentValue(countDep, found, thisEmployee);
			setFields(thisEmployee, countDep, countGender);
			// set corresponding full time combo box value to current employee
			if (thisEmployee.getFullTime() == true)
				fullTimeCombo.setSelectedIndex(1);
			else
				fullTimeCombo.setSelectedIndex(2);
		}
		change = false;
	}// end display records

	public void setFields(Employee thisEmployee, int countGender, int countDep) {
		idField.setText(Integer.toString(thisEmployee.getEmployeeId()));
		ppsField.setText(thisEmployee.getPps().trim());
		surnameField.setText(thisEmployee.getSurname().trim());
		firstNameField.setText(thisEmployee.getFirstName());
		salaryField.setText(format.format(thisEmployee.getSalary()));

	}

	// find corresponding gender combo box value to current employee
	public void findGenderValue(int countGender, boolean found, Employee thisEmployee) {
		while (!found && countGender < gender.length - 1) {
			if (Character.toString(thisEmployee.getGender()).equalsIgnoreCase(gender[countGender]))
				found = true;
			else
				countGender++;
		}
	}

	// find corresponding department combo box value to current employee
	public void findDeptartmentValue(int countDep, boolean found, Employee thisEmployee) {
		while (!found && countDep < department.length - 1) {
			if (thisEmployee.getDepartment().trim().equalsIgnoreCase(department[countDep]))
				found = true;
			else
				countDep++;
		}
	}

}
