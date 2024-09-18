import java.text.DecimalFormat;

public class Employee {
	private int employeeID;
	private String firstName;
	private String lastName;
	private Department emDepartment;
	private String position;
	private Double annualSalary;
	private String contact;
	
	public Employee() {
		
	}

	public Employee(int employeeID, String firstName,String lastName, Department emDepartment, String position, Double annualSalary, String contact) {
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emDepartment = emDepartment;
		this.position = position;
		this.annualSalary = annualSalary;
		this.contact = contact;
	}
	public void printEmployeeDetails() {// To Print the entire details of the Employee
		   DecimalFormat currency = new DecimalFormat("€0.00");
		   
		   System.out.println("----------------------------------------------------");
		   System.out.println("ID:             " + employeeID);
		   System.out.println("Name:           " + firstName + " " + lastName);
		   System.out.println("Department:     " + emDepartment.getDepartmentName());
		   System.out.println("Position:       "+ position);
		   System.out.println("Annual Salary:  " + currency.format(annualSalary));
		   System.out.println("Contact number: " + contact);
		   System.out.println("----------------------------------------------------");
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Department getEmDepartment() {
		return emDepartment;
	}

	public void setEmDepartment(Department emDepartment) {
		this.emDepartment = emDepartment;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Double getAnnualSalary() {
		return annualSalary;
	}

	public void setAnnualSalary(Double annualSalary) {
		this.annualSalary = annualSalary;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
