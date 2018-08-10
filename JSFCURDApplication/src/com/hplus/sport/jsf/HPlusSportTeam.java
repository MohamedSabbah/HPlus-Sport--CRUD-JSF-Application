package com.hplus.sport.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "team", eager = true)
@SessionScoped
public class HPlusSportTeam implements Serializable {

	private static final long serialVersionUID = 1L;
	private final String EMPLOYEES_FILE = "employees.txt";
	private final String SEPARATOR = ";";
	private final int DEFAULT_ID = 1001;

	private Employee employee;
	private List<Employee> employeeList = new ArrayList<Employee>();
	private FileUtils file;
	private int lastEmpolyeeID;
	private String breadCrumb;
	public HPlusSportTeam() {
		init();
	}

	public void init() {
		file = new FileUtils(EMPLOYEES_FILE, SEPARATOR);
		setEmplyeeList();
	}
	
	public String response() {
		return breadCrumb + "?faces-redirect=true";
	}
	
	public void action(ActionEvent event) {
		breadCrumb = (String)event.getComponent().getAttributes().get("breadCrumb");
		
	}

	public String add() {
		
		if(employee.getUploadedFile()!=null) {
			file.saveFile(employee.getUploadedFile());
			employee.setPhoto(file.getUploadedFileName());
		}
		int id = employeeList.isEmpty() ? DEFAULT_ID : getLastEmpolyeeID() + 1;
		employee.setID(id);
		employeeList.add(employee);

		employee = new Employee();
		return "dashboard.xhtml?faces-redirect=true";
	}
	public String edit(Employee employee) {
		this.employee = employee;
		return "edit.xhtml?faces-redirect=true";
	}
	public String saveEdit() {
		
		if(employee.getUploadedFile()!=null) {
			file.saveFile(employee.getUploadedFile());
			employee.setPhoto(file.getUploadedFileName());
		}
		return "dashboard.xhtml?faces-redirect=ture";
	}
	public String delete(Employee employee) {
		employeeList.remove(employee);
		return "dashboard.xhtml?faces-redirect=ture";
	}
	
	

	public void setEmplyeeList() {
		List<String> line = file.getDataList();
		Iterator<String> iterator = line.iterator();
		while (iterator.hasNext()) {
			String[] data = iterator.next().toString().split(SEPARATOR);
			int i = 0;
			employee = new Employee();
			employee.setID(Integer.parseInt(data[i++]));
			employee.setFirstName(data[i++]);
			employee.setLastName(data[i++]);
			employee.setDegree(data[i++]);
			employee.setTitle(data[i++]);
			employee.setPhoto(data[i++]);
			employee.setDescription(data[i++]);

			// add to employeeList
			employeeList.add(employee);
		}
		employee = new Employee();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getEMPLOYEES_FILE() {
		return EMPLOYEES_FILE;
	}

	public String getSEPARATOR() {
		return SEPARATOR;
	}

	public FileUtils getFile() {
		return file;
	}

	public void setFile(FileUtils file) {
		this.file = file;
	}

	public int getLastEmpolyeeID() {
		setLastEmpolyeeID();
		return lastEmpolyeeID;
	}

	public void setLastEmpolyeeID() {
		this.lastEmpolyeeID = employeeList.get(employeeList.size() - 1).getID();
	}

	public String getBreadCrumb() {
		return breadCrumb;
	}

	public void setBreadCrumb(String breadCrumb) {
		this.breadCrumb = breadCrumb;
	}

}
