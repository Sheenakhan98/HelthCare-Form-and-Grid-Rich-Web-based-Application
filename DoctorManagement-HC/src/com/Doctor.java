package com;
import java.sql.*;

public class Doctor
{ 
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hospital_mgt?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true", "root", "");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	
	public String readDoctors()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for reading."; 
			}
			
	// Prepare the html table to be displayed
	output = "<table border=\"1\"><tr><th>First Name</th><th>Last Name</th><th>Specialization</th><th>Gender</th><th>Email</th><th>ContactNo</th><th>Standard Fees</th><th>Password</th><th>Update</th><th>Remove</th></tr>";
	String query = "select * from doctor";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String docId = Integer.toString(rs.getInt("docId"));
	String FirstName = rs.getString("FirstName");
	String LastName = rs.getString("LastName");
	String Specialization = rs.getString("Specialization");
	String Gender = rs.getString("Gender");
	String Email = rs.getString("Email");
	String ContactNo = rs.getString("ContactNo");
	String StandardFees = Double.toString(rs.getDouble("StandardFees"));
	String Password = rs.getString("Password");
	
	// Add into the html table
	output += "<tr><td><input id='hidDocIDUpdate' name='hidDocIDUpdate' type='hidden' value='"+ docId 
			+"'>" + FirstName + "</td>";
	output += "<td>" + LastName + "</td>";
	output += "<td>" + Specialization + "</td>";
	output += "<td>" + Gender + "</td>";
	output += "<td>" + Email + "</td>";
	output += "<td>" + ContactNo + "</td>";
	output += "<td>" + StandardFees + "</td>";
	output += "<td>" + Password + "</td>";
	
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
			+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-docid='"
			+ docId + "'>" + "</td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the doctors.";
	System.err.println(e.getMessage());
	}
	return output;
	}

	public String insertDoctor(String fname, String lname, String specialization, String gender, String email, String contactno, String fees, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting."; 
			}
			// create a prepared statement
			String query = " insert into doctor (`docId`,`FirstName`,`LastName`,`Specialization`,`Gender`,`Email`,`ContactNo`,`StandardFees`,`Password`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, specialization);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, email);
			preparedStmt.setString(7, contactno);
			preparedStmt.setDouble(8, Double.parseDouble(fees));
			preparedStmt.setString(9, password);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newDoctor=readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
	}
	catch (Exception e)
	{
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the doctor.\"}";
			System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	public String updateDoctor(String docid, String fname, String lname, String specialization, String gender, String email, String contactno, String fees, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE doctor SET FirstName=?,LastName=?,Specialization=?,Gender=?,Email=?,ContactNo=?,StandardFees=?,Password=?WHERE docId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, fname);
			preparedStmt.setString(2, lname);
			preparedStmt.setString(3, specialization);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, contactno);
			preparedStmt.setDouble(7, Double.parseDouble(fees));
			preparedStmt.setString(8, password);
			preparedStmt.setInt(9, Integer.parseInt(docid));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newDoctor = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
			
			}
	catch (Exception e)
	{
		output = "{\"status\":\"error\", \"data\":\"Error while updating the doctor.\"}";
			System.err.println(e.getMessage());
	}
	return output;
	}
	
	public String deleteDoctor(String docId)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from doctor where docId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(docId));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newDoctor = readDoctors();
			output = "{\"status\":\"success\", \"data\": \"" + newDoctor + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the doctor.\"}";
			System.err.println(e.getMessage());
			}
			return output;
	}
}
