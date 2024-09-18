import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Thelma Ofoegbu
 */


public class HRSystem {
	static ArrayList<Employee> allEmployees = new ArrayList<Employee>();
	static ArrayList<Department> allDepartments = new ArrayList<Department>();
	static int centralEmployeeID = 1017;
	static int centralDepartmentID = 105;
	static Scanner input = new Scanner(System.in);
	static InputStreamReader textInput = new InputStreamReader(System.in); 
	static BufferedReader reader = new BufferedReader(textInput);
	static String capName;
	static String HRPassword = "Tee";
	
	/**
	 * Standard main method. Starting point of my program.
	 * it will call prePopulate method first before calling the start method which begins the program.
	 * 
	 */
	public static void main(String[]args) {
		prePopulate();
		start();
		
	}

	/**
	 * Displays a Welcome message to the User
	 * Prompts user to Enter their name then calls the mainMenu method which is the core program
	 * the employee menu, department menu, or exit the system.
	 */
	private static void start() {
		System.out.println("Welcome to Thelma's HR System");
		System.out.println("Please enter ur name");
		 String name = input.next();
		capName = name.substring(0,1).toUpperCase() + name.substring(1);
		
		mainMenu();
	}
	/**
	 * Displays the main menu for the HR system and allows the user to choose between
	 * the employee menu, department menu, or exit the system.
	 * It also has a hidden choice which leads to the HR report menu method which is like an Admin mode and it is password protected which means you'll be sent to the enterPassword method first.
	 */
	private static void mainMenu() {
		System.out.println("Hi " + capName );
		System.out.println("*********************************************");
		System.out.println("Press 1 for Employee Menu");
		System.out.println("Press 2 for Department Menu");
		System.out.println("Press 0 for HR Report Menu (PASSWORD REQUIRED)");
		System.out.println("Press X to Exit");
		System.out.println("*********************************************");
		
		String choice = input.next();// Get user's choice
		choice = choice.toLowerCase();// Convert choice to lowercase for easier comparison
		
	    // Process user's choice
		switch(choice) {
		
		case "0":{
		 
			enterPassword();
		
		}
		case "1":{
			employeeMenu();
			break;
		}
		case "2":{
			departmentMenu();
			break;
		}
		case "x":{
			System.out.println("System Shutting down");
			System.exit(0);
			break;
		}
		default:{
			break;
		}
		
		}
	
		mainMenu();//recursive method call
		
		
	}
	
	/**
	 * This method prompts the user to enter the HR password and 
	 * allows access to the HR report menu if the correct password is entered.
	 * 
	 * @throws Exception if an error occurs while getting input
	 */
	private static void enterPassword() {
		int attempts = 3;// Number of attempts user has to enter password
		System.out.println("Hi " + capName + " Please enter HR Password. You have " + attempts +  " attempts");
			String password = input.next();// Get password from user
			int passwordCount=0;
			
// Keep prompting user until correct password is entered or max attempts reached	
		 while (!(password.equalsIgnoreCase(HRPassword))){
			 System.out.println("Incorrect Password");
			 attempts--;
			 passwordCount++;
	
			if(passwordCount == 3){
				System.out.println("Max Password Entry reached...Access Denied");
				mainMenu();
			}
			System.out.println("Re-enter HR Password. You have " + attempts +  " attempt(s)");
			password = input.next();
		
			}
	
		    // If correct password entered, allow access to HR report menu	 	
		    System.out.println("Correct Password. Opening HR Report Menu.");
			HRReportMenu();
		
	}
	
	/**
	 * This method displays the HR report menu options and allows the user to choose between viewing all employees details, viewing salary expense by department or viewing salary expense by the company.
	 */
	private static void HRReportMenu() {
		
		System.out.println("*********************************************");
		System.out.println("Press 1 to View all Employees Details");
		System.out.println("Press 2 to View Deparment Salary Expense");
		System.out.println("Press 3 to View Total Salary Expense by Company");
		System.out.println("Press M to Return to main menu");
		System.out.println("*********************************************");
		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
		
		case "1":{	
			viewEmployeeDetails();	
			break;
		}
		case "2":{
			salaryByDept();
			break;
		}
		case "3":{
			salaryByCompany();
			break;
		}
		case "m":{
			mainMenu();
			break;
		}
		
		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}
		
	}
		HRReportMenu();
	}

	/**
	 * This method displays the details of all employees in the company.
	 */
	private static void viewEmployeeDetails() {
		for (Employee e: allEmployees) {	
			e.printEmployeeDetails();
		}
		
	}

	/**
	 * This method displays the total salary expense of each department in the company.
	 */
	private static void salaryByDept() {
		DecimalFormat currency = new DecimalFormat("€0.00");
		
		for(Department d: allDepartments) {
			
			d.getSalaryExpense();
			System.out.println(d.getDepartmentName()+ " Annual Salary Expense: " + currency.format(d.getSalaryExpense()));
		}
		
			
	}

	/**
	 * This method displays the total salary expense of the entire company.
	 */
	private static void salaryByCompany() {
		 DecimalFormat currency = new DecimalFormat("€0.00");
		 Double totalExpense = 0.00;
	        for (Employee e : allEmployees) {
	            totalExpense += e.getAnnualSalary();
	            }
		     System.out.println("Total Annual Salary Expense for the Company: " + currency.format(totalExpense));
		
	}

	/**
	 * Displays the employee menu of the HR system and allows the user to choose between
	 * creating a new employee, editing an existing employee, deleting an employee,
	 * viewing all employees, or returning to the main menu.
	 */
	private static void employeeMenu() {
		System.out.println("*********************************************");
		System.out.println("Press 1 to Create a new Employee");
		System.out.println("Press 2 to Edit an Employee");
		System.out.println("Press 3 to Delete an Employee");
		System.out.println("Press 4 to view all Employees");
		System.out.println("Press M to Return to main menu");
		System.out.println("*********************************************");
		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
		case "1":{
			
			try {
				createEmployee();
			} catch (Exception e) {
				System.out.println("An error was logged");
			}
			break;
		}
		case "2":{
			editEmployee();
			break;
		}
		case "3":{
			removeEmployee();
			break;
		}
		case "4":{
			viewAllEmployees();
			break;
		}
		case "m":{
			mainMenu();
			break;
		}
		
		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}
		
		}
		
		
		employeeMenu();
		
		
	}
	/**
	 * Method to create a new Employee object and add it to a Department
	 * @throws Exception when an error is encountered
	 */

	private static void createEmployee() throws Exception {
		Employee newemployee = new Employee();// Call null Constructor to create object
		newemployee.setEmployeeID(centralEmployeeID);
		centralEmployeeID++;//increment value by 1 so a unique value is given each time this method is called
		System.out.println("Enter Employee's FirstName");
		String firstName=reader.readLine();
		newemployee.setFirstName(firstName.substring(0,1).toUpperCase() + firstName.substring(1));
		System.out.println("Enter Employee's LastName");
		String lastName=reader.readLine();
		newemployee.setLastName(lastName.substring(0,1).toUpperCase() + lastName.substring(1));
		System.out.println("Please enter Employee's position");
		String position=reader.readLine();
		newemployee.setPosition(position.substring(0,1).toUpperCase() + position.substring(1));
		System.out.println("Please enter Annual salary for Employee");
		newemployee.setAnnualSalary(input.nextDouble());
		System.out.println("Press enter contact number");
		newemployee.setContact(input.next());
		
		System.out.println("Please Select Department by Department ID");
		viewAllDepartments();
		int chosenDepartment = input.nextInt();//Capture ID value
		
		boolean isFound = false;
		
		for(Department currentDepartment:allDepartments) {
			// If the chosen Employee is found, display the edit menu
			if(currentDepartment.getDepartmentID()== chosenDepartment) {
				isFound = true;
				newemployee.setEmDepartment(currentDepartment);;
				currentDepartment.addEmployee(newemployee);
				System.out.println("Employee succesfully Added to " + currentDepartment.getDepartmentName());
			}
		}
		// If no department is found, print an error message and do not add the Employee
		if (isFound==false) {
			System.out.println("No Department with the ID" +"" + chosenDepartment+""+ " found");
			System.out.println("All Employee data has been lost...Please Try again");
		}
		// Add the Employee to the list of all Employees
			allEmployees.add(newemployee);
	
	}
		
	/**
	 * Method to edit an existing Employee's details
	 * 
	 * calls the editEmployeeMenu and passes the employee toEdit
	 */
	private static void editEmployee() {
		// display a list of all employees and prompt user to choose an Employee by ID number
		viewAllEmployees();
		System.out.println("Choose Employee by ID number");
		int chosenEmployee=input.nextInt();
		boolean isFound=false;
		
		for(Employee currentEmployee:allEmployees) {
			if (chosenEmployee==currentEmployee.getEmployeeID()){
				isFound=true;
				try {
					editEmployeeMenu(currentEmployee);
				} catch (Exception e) {
					System.out.println("An error was logged");
				}
			}
		}
		if(isFound== false) {
			System.out.println("No Employee with the ID" +" " + chosenEmployee + " found");
			System.out.println("Please Try again");
		}
		
	}

	/**
	 * Method displays the menu for editing an Employee's details, allows user to select which employee detail to edit
	 *
	 * @throws Exception when an error is encountered
	 */
	private static void editEmployeeMenu(Employee toEdit) throws Exception {
		System.out.println("*********************************************");
		System.out.println("Press 1 to Edit Employee's Name");
		System.out.println("Press 2 to Edit Position");
		System.out.println("Press 3 to Edit contact number");
		System.out.println("Press 4 to Edit Annual Salary");
		System.out.println("Press 5 to Change department");
		System.out.println("Press m to Return to Employee menu");
		System.out.println("*********************************************");
		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
		case "1":{
			System.out.println("Enter new First Name");
			String firstName=reader.readLine();
			toEdit.setFirstName(firstName.substring(0,1).toUpperCase() + firstName.substring(1));
			System.out.println("Successfully Changed");
			System.out.println("Enter new Last Name");
			String lastName=reader.readLine();
			toEdit.setLastName(lastName.substring(0,1).toUpperCase() + lastName.substring(1));
			System.out.println("Successfully Changed");
			break;
		}
		case "2":{
			System.out.println("Enter new position");
			String position=reader.readLine();
			toEdit.setPosition(position.substring(0,1).toUpperCase() + position.substring(1));
			System.out.println("Successfully Changed");
			break;
		}
		case "3":{
			System.out.println("Enter new contact number");
			toEdit.setContact(input.next());
			System.out.println("Successfully Changed");
			break;
		}
		case "4":{
			System.out.println("Enter new Annual Salary");
			toEdit.setAnnualSalary(input.nextDouble());
			System.out.println("Successfully Changed");
			break;
		}
		case "5":{
			changeDepartment(toEdit);
			System.out.println("Successfully Changed");
			break;
		}
		case "m":{
			employeeMenu();
			break;
		}
		
		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}
		
		}
		
		editEmployeeMenu(toEdit);
	}
		
	private static void changeDepartment(Employee toEdit) {
		viewAllDepartments();// Display all departments
		System.out.println("please choose ID of new Department to replace " + toEdit.getEmDepartment().getDepartmentName());
		
		int chosenDepartment = input.nextInt();
		toEdit.getEmDepartment().removeEmployee(toEdit);// Remove the employee from the current department
		
		
		boolean isFound = false;
		for(Department currentDepartment:allDepartments) {
			if (chosenDepartment== currentDepartment.getDepartmentID()) {
				isFound = true;
			toEdit.setEmDepartment(currentDepartment);
			currentDepartment.addEmployee(toEdit);
			}
		}
		// Notify the user if the new department was not found
		if(isFound== false) {
			System.out.println("No Department with the ID" +" " + chosenDepartment + " found");
			System.out.println("Please Try again");
		}
		
	}

	
	/**
	 * Allows the user to delete an existing Employee from all employees and from department.
	 */
	private static void removeEmployee() {
		viewAllEmployees();
		System.out.println("Choose Employee by ID number");
		int chosenEmployee=input.nextInt();
		boolean isFound=false;
		
		for(Employee currentEmployee:allEmployees) {
			if (chosenEmployee==currentEmployee.getEmployeeID()){
				isFound=true;
				
				// remove employee from department
					currentEmployee.getEmDepartment().removeEmployee(currentEmployee);
					//remove employee from all employees
					allEmployees.remove(currentEmployee);
					System.out.println(currentEmployee.getFirstName() + " "+currentEmployee.getLastName() + " has been deleted");
					break;//use when deleting 
			}
		}
		if(isFound== false) {
			System.out.println("No Employee with the ID" +" " + chosenEmployee + " found");
			System.out.println("Please Try again");
		}
		
	}

	private static void viewAllEmployees() {
		for (Employee e:allEmployees) {
			System.out.println(e.getEmployeeID() + " " + e.getFirstName() + " " + e.getLastName());
		}
		
		
	}

	/**
	 * Displays the department menu of the HR system and allows the user to choose between
	 * creating a new department, editing an existing department, deleting a department,
	 * viewing all departments, viewing all employees by department, or returning to the
	 * main menu.
	 */
	private static void departmentMenu() {
		System.out.println("*********************************************");
		System.out.println("Press 1 to Create a new Department");
		System.out.println("Press 2 to Edit a Department");
		System.out.println("Press 3 to Delete a Department");
		System.out.println("Press 4 to view all Departments");
		System.out.println("Press 5 to view all Employees By their Departments");
		System.out.println("Press M to Return to main menu");
		System.out.println("*********************************************");
		
		String choice = input.next();
		choice = choice.toLowerCase();
		
		switch(choice) {
		case "1":{
			try {
				addDepartment();
			} catch (Exception e) {
				System.out.println("An error was logged");
			}
			break;
		}
		case "2":{
			editDepartment();
			break;
		}
		case "3":{
			removeDepartment();
			break;
		}
		case "4":{
			viewAllDepartments();
			break;
		}
		case "5":{
			viewEmployeesByDepartments();
			break;
		}
		case "m":{
			mainMenu();
			break;
		}
		
		default:{
			System.out.println("Invalid choice. Please try again");
			break;
		}
		
		}
		
		
		departmentMenu();
		
		
		
	}
		
	/**
	 * Allows user to create a new department and add it to the system.
	 * 
	 * @throws Exception if an error occurs while reading user input
	 */
	private static void addDepartment() throws Exception {
		Department newDepartment = new Department();
		newDepartment.setDepartmentID(centralDepartmentID);
		centralDepartmentID++;
		System.out.println("Please enter a New Department Name");
		String dept=reader.readLine();
		newDepartment.setDepartmentName(dept.substring(0,1).toUpperCase() + dept.substring(1));
	
		allDepartments.add(newDepartment);
		System.out.println(newDepartment.getDepartmentName() + " was added successfully");
		
	}
	
	/**
	 * Allows the user to edit an existing department in the system by changing its name.
	 */
	private static void editDepartment() {
		viewAllDepartments();//Show users all Departments
		System.out.println("Choose Department by ID number");
		int chosenDepartment=input.nextInt();//capture user selection
		
		boolean isFound=false;
		for(Department currentDepartment:allDepartments) {
			if (chosenDepartment==currentDepartment.getDepartmentID()){
				isFound=true;
				try {
					System.out.println("Please enter new name to replace " + currentDepartment.getDepartmentName());
					String dept=reader.readLine();
					currentDepartment.setDepartmentName(dept.substring(0,1).toUpperCase() + dept.substring(1));
					System.out.println("Successfully Changed");
				} catch (Exception e) {
					System.out.println("An error was logged editing Department");
				}

			}
		}
			if(isFound== false) {
				System.out.println("No Department with the ID" +"" + chosenDepartment+""+ " found");
				System.out.println("Please Try again");
			}
		
		
	}
	
	/**
	 * Allows the user to delete an existing department from the system.
	 */
	private static void removeDepartment() {
		viewAllDepartments();//Show users all Departments
		System.out.println("Choose Department by ID number");
		int chosenDepartment=input.nextInt();//capture user selection
		
		boolean isFound=false;
		for(Department currentDepartment:allDepartments) {
			if (chosenDepartment==currentDepartment.getDepartmentID()){//if the id of the current object matches user input
				isFound=true;
				if(currentDepartment.getDeptEmployees().isEmpty()) {
					allDepartments.remove(currentDepartment);
					System.out.println(currentDepartment.getDepartmentName() + " has been deleted");
					break;
				}
				else {
					System.out.println("Unable to delete " + currentDepartment.getDepartmentName());
					System.out.println("Employees are associated with this Department. Please delete Employees first");
				}
				
			}
		}
			if(isFound== false) {
				System.out.println("No Department with the ID" +"" + chosenDepartment+ " found");
				System.out.println("Please Try again");
			}
		
			
		
	}

	/**
	 * Displays all the departments in the system.
	 */
	private static void viewAllDepartments() {
		for (Department d: allDepartments) {
			System.out.println(d.getDepartmentID() +"\t"+d.getDepartmentName());
		}
		
	}

	/**
	 * Displays all the employees, grouped by department.
	 */
	private static void viewEmployeesByDepartments() {		
		for (Department d: allDepartments) {
			d.printDeptEmployees();
		}
	}

	/**
	 * Pre-populates the HR system with some initial data, such as employees and departments.
	 */
	private static void prePopulate() {
		Department sales = new Department (101,"Sales");
		Department production = new Department (102, "Production");
		Department finance = new Department (103, "Finance");
		Department marketing = new Department (104, "Marketing");
		
		Employee OliveJ = new Employee(1001,"Olive", "Johnson",sales,"Manager",60000.0,"0893647599");
		Employee EthanS = new Employee(1002,"Ethan", "Smith",sales,"Team Leader",50000.65,"0893648699");
		Employee AvaD = new Employee(1003,"Ava", "Davis",sales,"Customer Assistant",32000.29,"0893675599");
		Employee WilliamB = new Employee(1004,"William", "Brown",sales,"Customer Assistant",29500.50,"0895677599");
		
		Employee CharlotteW = new Employee(1005,"Charlotte", "Wilson",production,"Manager",70000.0,"0893647599");
		Employee JamesA = new Employee(1006,"James", "Anderson",production,"Team Leader",60900.52,"0893648699");
		Employee LeeB = new Employee(1007,"Lee", "Benjamin",production,"Customer Assistant",42700.80,"0893675599");
		Employee DanielP = new Employee(1008,"Daniel", "Parker",production,"Customer Assistant",29800.90,"0895677599");
		
		Employee MiaW = new Employee(1009,"Mia", "White",finance,"Manager",50000.75,"0893647599");
		Employee GreenH = new Employee(1010,"Green", "Harper",finance,"Team Leader",40100.62,"0893648699");
		Employee ClarkM = new Employee(1011,"Clark", "Mason",finance,"Customer Assistant",22000.90,"0893675599");
		Employee EvelynY = new Employee(1012,"Evelyn", "Young",finance,"Customer Assistant",24000.80,"0895677599");
		
		Employee MitchellA = new Employee(1013,"Mitchell", "Anderson",marketing,"Manager",35580.50,"0893647599");
		Employee AlexA = new Employee(1014,"Alex", "Adams",marketing,"Team Leader",30800.69,"0893648699");
		Employee BennyG = new Employee(1015,"Benny", "Godwin",marketing,"Customer Assistant",24200.55,"0893675599");
		Employee IsabellaC = new Employee(1016,"Isabella", "Campbell",marketing,"Customer Assistant",12450.20,"0895677599");
		
		sales.addEmployee(OliveJ);
		sales.addEmployee(EthanS);
		sales.addEmployee(AvaD);
		sales.addEmployee(WilliamB);
		
		production.addEmployee(CharlotteW);
		production.addEmployee(JamesA);
		production.addEmployee(LeeB);
		production.addEmployee(DanielP);
		
		finance.addEmployee(MiaW);
		finance.addEmployee(GreenH);
		finance.addEmployee(ClarkM);
		finance.addEmployee(EvelynY);
		
		marketing.addEmployee(MitchellA);
		marketing.addEmployee(AlexA);
		marketing.addEmployee(BennyG);
		marketing.addEmployee(IsabellaC);
		
		allEmployees.add(OliveJ);
		allEmployees.add(EthanS);
		allEmployees.add(AvaD);
		allEmployees.add(WilliamB);
		allEmployees.add(CharlotteW);
		allEmployees.add(JamesA);
		allEmployees.add(LeeB);
		allEmployees.add(DanielP);
		allEmployees.add(MiaW);
		allEmployees.add(GreenH);
		allEmployees.add(ClarkM);
		allEmployees.add(EvelynY);
		allEmployees.add(MitchellA);
		allEmployees.add(AlexA);
		allEmployees.add(BennyG);
		allEmployees.add(IsabellaC);
		
		allDepartments.add(sales);
		allDepartments.add(production);
		allDepartments.add(finance);
		allDepartments.add(marketing);
		
	
			
		
			
		
		
	}
}
