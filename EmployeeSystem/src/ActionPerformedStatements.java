import java.awt.event.ActionEvent;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JMenuItem;

public class ActionPerformedStatements extends Observable {

	private JMenuItem open, save, saveAs, create, modify, delete, firstItem, lastItem, nextItem, prevItem, searchById,
			searchBySurname, listAll, closeApp;

	private JButton first, previous, next, last, add, edit, deleteButton, displayAll, searchId, searchSurname,
			saveChange, cancelChange;

	NavigateMenuOption current;
	ChangeOption cuurentChanges;
	RecordsMenuOption cuurentRecord;
	FileMenuOption currentFileOption;
	CloseMenuOption closeCurrent;
	SearchOption currentSearch;

	public void actionPerformed(ActionEvent e) {

		ActionPerformedStatements action = new ActionPerformedStatements();

		// First
		action.setState(new FirstRecord());
		action.checkOptionClicked(e.getSource(), firstItem, first);
		// Previous
		action.setState(new PreviousRecord());
		action.checkOptionClicked(e.getSource(), prevItem, previous);
		// Next
		action.setState(new NextRecord());
		action.checkOptionClicked(e.getSource(), nextItem, next);
		// Last
		action.setState(new LastRecord());
		action.checkOptionClicked(e.getSource(), lastItem, last);
		// Search by Surname from menu option Navigate
		action.setState(new SearchBySurname());
		action.checkOptionClickedSearch(e.getSource(), searchBySurname);
		// Search by ID from menu option Navigate
		action.setState(new SearchById());
		action.checkOptionClickedSearch(e.getSource(), searchById);
		// Search by Surname and display Dialog
		action.setState(new SearchBySurname());
		action.returnActionDialogs(e.getSource(), searchBySurname);
		// Display All Emplyees from menu option Navigate
		action.setState(new DisplayEmployees());
		action.checkOptionClicked(e.getSource(), listAll, displayAll);

		// Save Changes
		action.setState(new SaveChanges());
		action.checkoptionclicked(e.getSource(), saveChange);
		// Cancel Changes
		action.setState(new CancelChanges());
		action.checkoptionclicked(e.getSource(), cancelChange);

		// Add Record from menu option Record
		action.setState(new AddRecord());
		action.checkOptionClicked(e.getSource(), create, add);
		// Delete Record from menu option Record
		action.setState(new DeleteRecord());
		action.checkOptionClicked(e.getSource(), delete, deleteButton);
		// Edit Record from menu option Record
		action.setState(new ModifyRecord());
		action.checkOptionClicked(e.getSource(), modify, edit);

		// Save file from menu option File
		action.setState(new OpenFile());
		action.checkOptionClicked(e.getSource(), open);
		// Open file from menu option File
		action.setState(new SaveFile());
		action.checkOptionClicked(e.getSource(), save);
		// SaveAs file from menu option File
		action.setState(new SaveAs());
		action.checkOptionClicked(e.getSource(), saveAs);

		// Close App from menu Close
		action.setState(new CloseApp());
		action.checkOptionClicked(e.getSource(), closeApp);

		// Search Id from panel
		action.setState(new SearchId());
		action.checkoptionclicked(e.getSource(), searchById, searchId);

		// Search Surname from panel
		action.setState(new SearchSurname());
		action.checkoptionclicked(e.getSource(), searchBySurname, searchSurname);

	}// end actionPerformed

	private void checkoptionclicked(Object source, JMenuItem searchById2, JButton searchId2) {
		// TODO Auto-generated method stub

	}

	private void checkOptionClicked(Object source, JMenuItem open2) {
		// TODO Auto-generated method stub

	}

	private void checkoptionclicked(Object source, JButton saveChange2) {
		// TODO Auto-generated method stub

	}

	private void returnActionDialogs(Object source, JMenuItem searchBySurname2) {
		// TODO Auto-generated method stub

	}

	private void checkOptionClickedSearch(Object source, JMenuItem searchById2) {
		// TODO Auto-generated method stub

	}

	private void checkOptionClicked(Object source, JMenuItem firstItem2, JButton first2) {
		// TODO Auto-generated method stub

	}

	public void setState(SearchOption state) {
		this.currentSearch = state;

	}

	public void setState(CloseMenuOption state) {
		this.closeCurrent = state;

	}

	public void setState(FileMenuOption state) {
		this.currentFileOption = state;

	}

	public void setState(RecordsMenuOption state) {
		this.cuurentRecord = state;

	}

	public void setState(ChangeOption state) {
		this.cuurentChanges = state;

	}

	public void setState(NavigateMenuOption state) {
		this.current = state;

	}

}
