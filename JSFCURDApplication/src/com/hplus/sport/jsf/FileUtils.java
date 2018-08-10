package com.hplus.sport.jsf;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class FileUtils {

	//Text file separator for parsing
	private final String SEPARATOR = ";";
	
	//New updated employee data file
	private final String UPDATED_EMPLOYEES_FILE = "updated_employees.txt";

	// Directories
	final private String PROJECT_DIR 		=	"C:\\Users\\Sab\\eclipse-workspace\\JSFCURDApplication\\"; 
	final private String SOURCES_DIR 		=	PROJECT_DIR + "sources\\";
	final private String EMPLOYEES_IMG_DIR 	=	PROJECT_DIR + "WebContent\\images\\employees\\";

	//The input and output text files
	private String inputFile;
	private String outputFile;
	
	//Name of uploaded file
	private String uploadedFileName;
	private ArrayList<String> dataList = new ArrayList<String>();

	/**
	 * Constructors
	 */
	public FileUtils() {
	}

	public FileUtils(String inputFile, String separator) {
		setInputFile(inputFile);
		setOutputFile(UPDATED_EMPLOYEES_FILE);
		readFile(separator);
	}

	/**
	 * Save the uploaded files to the target directory
	 * @param uploadedFile
	 */
	public void saveFile(Part uploadedFile) {

		try (InputStream input = uploadedFile.getInputStream()) {
			uploadedFileName = uploadedFile.getSubmittedFileName();
			Files.copy(input, new File(EMPLOYEES_IMG_DIR, uploadedFileName).toPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open and read text file
	 * @param separator
	 */
	public void readFile(String separator) {

		String line = null;

		try {
			FileReader fstream = new FileReader(SOURCES_DIR + inputFile);
			BufferedReader inFile = new BufferedReader(fstream);

			// Read each line and parse it
			while ((line = inFile.readLine()) != null) {
				dataList.add(line);
			}

			// Close file.
			inFile.close();

		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inputFile + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + inputFile + "'");
			System.out.println("Error Msg: " + ex);
		}
	}

	/**
	 * Write data to new employee text file
	 * @param employeeList
	 */
	public void writeEmployeeFile(List<Employee> employeeList) {

		try {

			FileWriter fstream = new FileWriter(SOURCES_DIR + outputFile, false);
			BufferedWriter outFile = new BufferedWriter(fstream);
			Iterator<Employee> itr = employeeList.iterator();

			while (itr.hasNext()) {
				// Each employee
				Employee employee = itr.next();
				writeLineToEmployeeFile(outFile, employee);
			}

			// Flush and close files.
			outFile.flush();
			outFile.close();

		} catch (IOException ex) {
			System.out.println("Error writing to file '" + outputFile + "'");
		}

	}

	/**
	 * Write each line (employee record) to the new employee text file
	 * @param outFile
	 * @param employee
	 * @throws IOException
	 */
	private void writeLineToEmployeeFile(BufferedWriter outFile, Employee employee) throws IOException {
		// Append each field to a line in the file
		outFile.write(employee.getID() + SEPARATOR);
		outFile.write(employee.getFirstName() + SEPARATOR);
		outFile.write(employee.getLastName() + SEPARATOR);
		outFile.write(employee.getTitle() + SEPARATOR);
		outFile.write(employee.getDegree() + SEPARATOR);
		outFile.write(employee.getPhoto() + SEPARATOR);
		outFile.write(employee.getDescription());
		outFile.newLine();
	}

	/*
	 * Getters and Setters
	 */
	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getUploadedFileName() {
		return uploadedFileName;
	}

	public void setUploadedFileName(String uploadedFileName) {
		this.uploadedFileName = uploadedFileName;
	}

	public ArrayList<String> getDataList() {
		return dataList;
	}

}
