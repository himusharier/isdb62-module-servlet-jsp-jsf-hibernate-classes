package com.himu;

public class Employee {
	private int id;
	private String name;
	private String phone;
	private String email;
	private int salary;
	private String address;

	// Default constructor
	public Employee() {
	}

	public Employee(int id, String name, String phone, String email, int salary, String address) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.salary = salary;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}