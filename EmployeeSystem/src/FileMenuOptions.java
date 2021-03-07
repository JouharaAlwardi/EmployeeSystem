import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

abstract class FileMenuOption {
	FileActions fileOption = new FileActions();
	CheckInput check = new CheckInput();

	public boolean change = false;

	void checkOptionClicked(ActionEvent e, JMenuItem menuItem) {
		if (e.getSource() == menuItem) {
			if (check.checkInput() && !check.checkForChanges())
				returnAction(fileOption);
		}
	}

	void returnAction(FileActions fileOption) {
	}

}

class OpenFile extends FileMenuOption {
	@Override
	void returnAction(FileActions fileOption) {
		fileOption.openFile();

	}

}

class SaveFile extends FileMenuOption {
	@Override
	void returnAction(FileActions fileOption) {
		fileOption.saveFile();
		change = false;

	}

}

class SaveAs extends FileMenuOption {
	@Override
	void returnAction(FileActions fileOption) {
		fileOption.saveFileAs();
		change = false;

	}

}
