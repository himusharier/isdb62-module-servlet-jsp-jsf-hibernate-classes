package com.himu;

import java.io.Serializable;
import java.util.List;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Employee employee = new Employee(); // Holds the current employee data
	private EmployeeDAO employeeDAO = new EmployeeDAO(); // DAO for database operations
	private List<Employee> employeeList; // List of all products
	private boolean editMode = false; // Tracks whether the form is in edit mode

	// Saves or updates a employee
	public String saveEmployee() {
		try {
			if (editMode) {
				employeeDAO.update(employee); // Update existing employee
			} else {
				employeeDAO.create(employee); // Create new employee
			}
			refreshList(); // Refresh the employee list
			clearForm(); // Clear the form
			return "employee?faces-redirect=true"; // Navigate to the employee list page
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Stay on the same page if an error occurs
		}
	}

	// Enables edit mode and loads the selected employee into the form
	public void editEmployee(Employee employee) {
		this.employee = employee; // Set the selected employee
		editMode = true; // Enable edit mode
	}

	// Deletes a employee by ID
	public void deleteEmployee(int id) {
		try {
			employeeDAO.delete(id); // Delete the employee
			refreshList(); // Refresh the employee list
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Clears the form and resets edit mode
	public void clearForm() {
		employee = new Employee(); // Reset the employee object
		editMode = false; // Disable edit mode
	}

	// Refreshes the employee list from the database
	private void refreshList() {
		try {
			employeeList = employeeDAO.readAll(); // Fetch all products
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getters and Setters
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Employee> getEmployeeList() {
		if (employeeList == null) {
			refreshList(); // Lazy load the employee list
		}
		return employeeList;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}