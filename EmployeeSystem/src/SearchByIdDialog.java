/*
 * 
 * This is the dialog for Employee search by ID
 * 
 * */

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class SearchByIdDialog extends JDialog implements ActionListener {
	EmployeeGUI parent;
	JButton search, cancel;
	JTextField searchField;
	Font font1 = new Font("SansSerif", Font.BOLD, 16);
	JTextField searchByIdField;
	SearchRecords searchId;
	SearchPanel searchPanel;

	// constructor for SearchByIdDialog
	public SearchByIdDialog(EmployeeGUI parent) {
		setTitle("Search by Id");
		JScrollPane scrollPane = new JScrollPane(searchPane());
		setContentPane(scrollPane);
		searchPanel.search();

	}// end SearchByIdDialog

	// initialize search container
	public Container searchPane() {
		JPanel searchPanel = new JPanel(new GridLayout(3, 1));
		JPanel textPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JLabel searchLabel;

		searchPanel.add(new JLabel("Search by ID"));
		textPanel.add(searchLabel = new JLabel("Enter ID:"));

		// searchPanel.setPanel(searchLabel);

		return searchPanel;
	}// end searchPane

	// action listener for save and cancel button
	public void actionPerformed(ActionEvent e) {
		// if option search, search for Employee
		if (e.getSource() == search) {
			// try get correct valus from text field
			try {
				Double.parseDouble(searchField.getText());
				searchByIdField.setText(searchField.getText());
				// search Employee by ID
				searchId.searchEmployeeById();
				dispose();// dispose dialog
			} // end try
			catch (NumberFormatException num) {
				// display message and set colour to text field if entry is wrong
				searchField.setBackground(new Color(255, 150, 150));
				JOptionPane.showMessageDialog(null, "Wrong ID format!");
			} // end catch
		} // end if
			// else dispose dialog
		else if (e.getSource() == cancel)
			dispose();
	}// end actionPerformed
}// end class searchByIdDialog
