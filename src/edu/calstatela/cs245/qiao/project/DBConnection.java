package edu.calstatela.cs245.qiao.project;
import java.sql.*;

public class DBConnection {
	
	private static final String USERNAME = "cs245s30"; 
	private static final String PASSWORD = "A*hV5bzp";
	private static final String URL = "jdbc:mysql://localhost:3306/employee";
	private static final String FILE_LOCATION = "G:/CS245/Project/Employees.csv";
	
	/*
	private static final String URL = "jdbc:mysql://cs1.calstatela.edu/cs245s30"; 
	private static final String USERNAME = "cs245s30"; 
	private static final String PASSWORD = "A*hV5bzp";
	private static final String FILE_LOCATION = "E:/CS245/Project/Employees.csv";
	*/
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;

	
	public static void openConnection() throws SQLException {

		conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

	}

	public static void closeResultSet() throws SQLException {

		if (rs != null) {
			rs.close();
		}
	}

	public static void closeStatementConnection() throws SQLException {

		if (stmt != null) {
			stmt.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	// First time call this method to create a table 
	// and load data from specified file to the created table.
	public static void createTable() {
		try {
			// openConnection();
			stmt = conn.createStatement();
			String createTable = "CREATE TABLE employee(Emp_Id INTEGER AUTO_INCREMENT, Emp_Name VARCHAR(20), Hire_Date DATE, "
					+ "Birth_Date DATE, Sex VARCHAR(10), Job_Status VARCHAR(5), Pay_Type VARCHAR(5), "
					+ "Annual_Salary DOUBLE, Years_Of_Service INTEGER(10), PRIMARY KEY(Emp_Id))";
			stmt.execute(createTable);
			System.out.println("Table created successfully!");

			String sql = "load data local infile '" + FILE_LOCATION
					+ "' into table employee fields terminated by ','";
			stmt.execute(sql);
			System.out.println("Data loaded successfully.");

			// closeStatementConnection();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public static void insertRow(String id, String lastName, String sex,
			String birthDate, String hireDate, String jobStatus,
			String payType, String salary, String yearS) throws SQLException {

		stmt = conn.createStatement();
		String insert = null;
		if (id.equals("")){
			insert = "INSERT INTO employee (Emp_Name, Hire_Date, Birth_Date, " +
					"Sex, Job_Status, Pay_Type, Annual_Salary, Years_Of_Service) VALUES(\'"
					+ lastName + "\', \'" + hireDate + "\', \'" + birthDate
					+ "\', \'" + sex + "\', \'" + jobStatus + "\', \'" + payType
					+ "\', \'" + salary + "\', \'" + yearS + "\');";
		}else{
			insert = "INSERT INTO employee VALUES(\'" + id + "\', \'"
					+ lastName + "\', \'" + hireDate + "\', \'" + birthDate
					+ "\', \'" + sex + "\', \'" + jobStatus + "\', \'" + payType
					+ "\', \'" + salary + "\', \'" + yearS + "\');";
		}
		stmt.executeUpdate(insert);
	}

	public static ResultSet searchID(String id) throws SQLException {
		stmt = conn.createStatement();
		String searchID = "SELECT * FROM employee WHERE Emp_ID = \'" + id
				+ "\';";
		rs = stmt.executeQuery(searchID);
		return rs;
	}
	
	public static void deleteRow(String id) throws SQLException {
		stmt = conn.createStatement();
		String delete = "DELETE FROM employee WHERE Emp_ID = \'" + id + "\';";
		stmt.executeUpdate(delete);
	}
	
	public static void updateRow(String id, String lastName, String sex,
			String birthDate, String hireDate, String jobStatus,
			String payType, String salary, String yearS) throws SQLException {
		stmt = conn.createStatement();
		String update = "UPDATE employee SET Emp_Name = \'" + lastName + "\', Hire_Date = \'" + hireDate +
				"\', Birth_Date = \'" + birthDate + "\', Sex =  \'" + sex + "\', Job_Status = \'" + jobStatus +
				"\', Pay_Type = \'" + payType + "\', Annual_Salary =  \'" + salary + "\', Years_Of_Service = \'" + yearS +
				"\' WHERE Emp_ID = \'" + id + "\';";
		stmt.executeUpdate(update);
	}
	
	public static String[][] getQueryData(String query) throws SQLException {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(query);
		
		ResultSetMetaData metaData = rs.getMetaData();
	    int numberColumns = metaData.getColumnCount();
	    
		rs.last();
		int numberRows = rs.getRow();
		rs.first();
		String[][] data = new String[numberRows][numberColumns];
		int row = 0;
		do{
			for(int column = 0; column < numberColumns; column++){
				data[row][column] = rs.getString(column + 1);	
			}			
			row++;
		}while(rs.next());
		return data;
	}
}
