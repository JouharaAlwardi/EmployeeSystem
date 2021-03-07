/*
 * 
 * This is a dialog for searching Employees by their surname.
 * 
 * */

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SearchBySurnameDialog extends JDialog implements ActionListener {
	EmployeeGUI parent;
	JButton search, cancel;
	SearchRecords searchRecords;
	JTextField searchBySurnameField, searchField;
	Font font1 = new Font("SansSerif", Font.BOLD, 16);
	SearchPanel searchPanel;

	// constructor for search by surname dialog
	public SearchBySurnameDialog(EmployeeGUI parent) {
		setTitle("Search by Surname");
		JScrollPane scrollPane = new JScrollPane(searchPane());
		setContentPane(scrollPane);
		searchPanel.search();
	}// end SearchBySurnameDialog

	// initialize search container
	public Container searchPane() {
		JPanel searchPanel = new JPanel(new GridLayout(3, 1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel searchLabel;

		searchPanel.add(new JLabel("Search by Surname"));
		textPanel.add(searchLabel = new JLabel("Enter Surname:"));
		// setPanel(searchLabel);

		return searchPanel;
	}// end searchPane

	// action listener for save and cancel button
	public void actionPerformed(ActionEvent e) {
		// if option search, search for Employee
		if (e.getSource() == search) {
			searchBySurnameField.setText(searchField.getText());
			// search Employee by surname
			searchRecords.searchEmployeeBySurname();
			dispose();// dispose dialog
		} // end if
			// else dispose dialog
		else if (e.getSource() == cancel)
			dispose();// dispose dialog
	}// end actionPerformed
}// end class SearchBySurnameDialog
