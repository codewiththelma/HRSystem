import java.util.ArrayList;

public class Department {
	private int departmentID;
	private String departmentName;
	private ArrayList<Employee> deptEmployees = new ArrayList<Employee>();
	
	
	public Department() {
		
	}


	public Department(int departmentID, String departmentName) {
		this.departmentID = departmentID;
		this.departmentName = departmentName;

	}
	public void addEmployee(Employee e) {
		deptEmployees.add(e); //Add a new Employee to the department list of dept Employees
	}
	
	public void removeEmployee(Employee e) {
		deptEmployees.remove(e); //remove an Employee from the department list of dept Employees
	}
	
	public void printDeptEmployees() {
		 System.out.println("--------"+ departmentName + " Department Employees--------");
		for(Employee e: deptEmployees) {
			System.out.println(e.getEmployeeID() + " " + e.getFirstName() + " " + e.getLastName());
			
		}
		 System.out.println("----------------------------------------------");
	}
	public Double getSalaryExpense() {
        Double salaryExpense = 0.00;
        for (Employee e : deptEmployees) {
            salaryExpense += e.getAnnualSalary();
        }
		return salaryExpense;
	}
	

	public int getDepartmentID() {
		return departmentID;
	}


	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public ArrayList<Employee> getDeptEmployees() {
		return deptEmployees;
	}


	public void setDeptEmployees(ArrayList<Employee> deptEmployees) {
		this.deptEmployees = deptEmployees;
	}
	
	
}
	
