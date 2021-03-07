
/* * 
 * This is a menu driven system that will allow users to define a data structure representing a collection of 
 * records that can be displayed both by means of a dialog that can be scrolled through and by means of a table
 * to give an overall view of the collection contents.
 * 
 * */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

public class EmployeeGUI extends JFrame implements ActionListener, ItemListener, DocumentListener, WindowListener {

	JMenuBar menuBar = new JMenuBar();
	private boolean change = false;
	private static EmployeeGUI frame = new EmployeeGUI();

	public void actionPerformed(ActionEvent e) {
		ActionPerformedStatements action = new ActionPerformedStatements();
		action.actionPerformed(e);

	}

	// content pane for main dialog
	private void createContentPane() {
		FileActions createRandomFile = new FileActions();
		setTitle("Employee Details");
		createRandomFile.createRandomFile();
		JPanel dialog = new JPanel(new MigLayout());
		Panels panel = new Panels();

		setJMenuBar(menuBar);

		dialog.add(panel.searchPanel(), "width 400:400:400, growx, pushx");
		dialog.add(panel.navigPanel(), "width 150:150:150, wrap");
		dialog.add(panel.buttonPanel(), "growx, pushx, span 2,wrap");
		dialog.add(panel.detailsPanel(), "gap top 30, gap left 150, center");

		JScrollPane scrollPane = new JScrollPane(dialog);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		addWindowListener(this);
	}

	// create and show main dialog
	private static void createAndShowGUI() {

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.createContentPane();
		frame.setSize(760, 600);
		frame.setLocation(250, 200);
		frame.setVisible(true);
	}

	// main method
	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	// DocumentListener methods
	public void changedUpdate(DocumentEvent d) {
		change = true;
		new JTextFieldLimit(20);
	}

	public void insertUpdate(DocumentEvent d) {
		change = true;
		new JTextFieldLimit(20);
	}

	public void removeUpdate(DocumentEvent d) {
		change = true;
		new JTextFieldLimit(20);
	}

	// ItemListener method
	public void itemStateChanged(ItemEvent e) {
		change = true;
	}

	// WindowsListener methods
	public void windowClosing(WindowEvent e) {
		// exit application
		FileActions exit = new FileActions();
		exit.exitApp();
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowOpened(WindowEvent e) {
	}
}// end class EmployeeDetails
