import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

public class CloseMenuOption {

	FileActions fileOption = new FileActions();
	CheckInput check = new CheckInput();

	void checkOptionClicked(ActionEvent e, JMenuItem menuItem) {
		if (e.getSource() == menuItem) {
			if (check.checkInput() && !check.checkForChanges())
				returnAction(fileOption);
		}
	}

	void returnAction(FileActions fileOption) {
	}

}

class CloseApp extends CloseMenuOption {
	@Override
	void returnAction(FileActions fileOption) {
		fileOption.exitApp();
	}

}
