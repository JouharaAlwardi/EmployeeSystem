import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

public class SearchOption {

	SearchRecords serach;
	CheckInput check = new CheckInput();
	Employee currentEmployee;

	void checkoptionclicked(ActionEvent e, JMenuItem first, JMenuItem secound) {
		if (e.getSource() == first || e.getSource() == secound) {

			returnAction(serach);

		}
	}

	void returnAction(SearchRecords search) {

	}

}

class SearchId extends SearchOption {
	@Override
	void returnAction(SearchRecords search) {
		serach.searchEmployeeById();

	}

}

class SearchSurname extends SearchOption {
	@Override
	void returnAction(SearchRecords search) {
		serach.searchEmployeeBySurname();

	}

}
