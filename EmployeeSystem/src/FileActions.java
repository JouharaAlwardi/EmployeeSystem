import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileActions {

	File file;
	boolean change = false;
	static EmployeeGUI frame = new EmployeeGUI();
	RandomFile application = new RandomFile();
	FileNameExtensionFilter datfilter = new FileNameExtensionFilter("dat files (*.dat)", "dat");
	EmployeeGUI parent = new EmployeeGUI();
	Employee currentEmployee;
	NavigateRecords navigate;
	DisplayRecords displayRecord;
	boolean changesMade = false;
	CheckInput check;
	String generatedFileName;
	EditTextField editField;
	EmployeeRecordDetails record;
	JTextField idField;
	long currentByteStart = 0;

	// open file
	public void openFile() {
		final JFileChooser fc = new JFileChooser();
		fc.setDialogTitle("Open");

		fc.setFileFilter(datfilter);
		File newFile;
		if (file.length() != 0 || change) {
			int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
					JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if (returnVal == JOptionPane.YES_OPTION) {
				saveFile();
			}
		}

		int returnVal = fc.showOpenDialog(parent);

		if (returnVal == JFileChooser.APPROVE_OPTION) {
			newFile = fc.getSelectedFile();
			if (file.getName().equals(generatedFileName))
				file.delete();
			file = newFile;
			application.openReadFile(file.getAbsolutePath());
			navigate.firstRecord();
			displayRecord.displayRecords(currentEmployee);
			application.closeReadFile();
		}
	}

	// allow to save changes to file when exiting the application
	public void exitApp() {
		// if file is not empty allow to save changes
		if (file.length() != 0) {
			if (changesMade) {
				int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				// if user chooses to save file, save file
				if (returnVal == JOptionPane.YES_OPTION) {
					saveFile();// save file
					// delete generated file if user saved details to other file
					if (file.getName().equals(generatedFileName))
						file.delete();// delete file
					System.exit(0);// exit application
				} // end if
					// else exit application
				else if (returnVal == JOptionPane.NO_OPTION) {
					// delete generated file if user chooses not to save file
					if (file.getName().equals(generatedFileName))
						file.delete();// delete file
					System.exit(0);// exit application
				} // end else if
			} // end if
			else {
				// delete generated file if user chooses not to save file
				if (file.getName().equals(generatedFileName))
					file.delete();// delete file
				System.exit(0);// exit application
			} // end else
				// else exit application
		} else {
			// delete generated file if user chooses not to save file
			if (file.getName().equals(generatedFileName))
				file.delete();// delete file
			System.exit(0);// exit application
		} // end else
	}// end exitApp

	// save file
	public void saveFile() {
		// if file name is generated file name, save file as 'save as' else save
		// changes to file
		if (file.getName().equals(generatedFileName))
			saveFileAs();// save file as 'save as'
		else {
			// if changes has been made to text field offer user to save these
			// changes
			if (change) {
				int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes?", "Save",
						JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
				// save changes if user choose this option
				if (returnVal == JOptionPane.YES_OPTION) {
					// save changes if ID field is not empty
					if (!idField.getText().equals("")) {
						// open file for writing
						application.openWriteFile(file.getAbsolutePath());
						// get changes for current Employee
						currentEmployee = record.getChangedDetails();
						// write changes to file for corresponding Employee
						// record
						application.changeRecords(currentEmployee, currentByteStart);
						application.closeWriteFile();// close file for writing
					} // end if
				} // end if
			} // end if

			displayRecord.displayRecords(currentEmployee);
			editField.setEnabled(false);
		} // end else
	}// end saveFile

	// save changes to current Employee
	public void saveChanges() {
		int returnVal = JOptionPane.showOptionDialog(frame, "Do you want to save changes to current Employee?", "Save",
				JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
		// if user choose to save changes, save changes
		if (returnVal == JOptionPane.YES_OPTION) {
			// open file for writing
			application.openWriteFile(file.getAbsolutePath());
			// get changes for current Employee
			currentEmployee = record.getChangedDetails();
			// write changes to file for corresponding Employee record
			application.changeRecords(currentEmployee, currentByteStart);
			application.closeWriteFile();// close file for writing
			changesMade = false;// state that all changes has bee saved
		} // end if
		displayRecord.displayRecords(currentEmployee);
		editField.setEnabled(false);
	}// end saveChanges

	// save file as 'save as'
	public void saveFileAs() {
		final JFileChooser fc = new JFileChooser();
		File newFile;
		String defaultFileName = "new_Employee.dat";
		fc.setDialogTitle("Save As");
		// display files only with .dat extension
		fc.setFileFilter(datfilter);
		fc.setApproveButtonText("Save");
		fc.setSelectedFile(new File(defaultFileName));

		int returnVal = fc.showSaveDialog(parent);
		// if file has chosen or written, save old file in new file
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			newFile = fc.getSelectedFile();
			// check for file name
			if (!check.checkFileName(newFile)) {
				// add .dat extension if it was not there
				newFile = new File(newFile.getAbsolutePath() + ".dat");
				// create new file
				application.createFile(newFile.getAbsolutePath());
			} // end id
			else
				// create new file
				application.createFile(newFile.getAbsolutePath());

			try {// try to copy old file to new file
				Files.copy(file.toPath(), newFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				// if old file name was generated file name, delete it
				if (file.getName().equals(generatedFileName))
					file.delete();// delete file
				file = newFile;// assign new file to file
			} // end try
			catch (IOException e) {
			} // end catch
		} // end if
		changesMade = false;
	}// end saveFileAs

	// generate 20 character long file name
	private String getFileName() {
		String fileNameChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-";
		StringBuilder fileName = new StringBuilder();
		Random rnd = new Random();
		// loop until 20 character long file name is generated
		while (fileName.length() < 20) {
			int index = (int) (rnd.nextFloat() * fileNameChars.length());
			fileName.append(fileNameChars.charAt(index));
		}
		String generatedfileName = fileName.toString();
		return generatedfileName;
	}// end getFileName

	// create file with generated file name when application is opened
	public void createRandomFile() {
		generatedFileName = getFileName() + ".dat";
		// assign generated file name to file
		file = new File(generatedFileName);
		// create file
		application.createFile(file.getName());
	}// end createRandomFile

}
