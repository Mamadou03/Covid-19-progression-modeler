package projet_SGBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Test {
	public static void main(String[] args) throws Exception {
		Conn();

}
	public static void Conn() throws Exception {
		try {
			// This will load the MySQL driver, each DB has its own driver
			Class.forName("com.mysql.jdbc.Driver");

			// Setup the connection with the DB
			Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bar", "root", "");
			System.out.println("Connection Reussit");
		} catch (Exception e) {
			throw e;
		}
	}
	//ajouter donnees communique dans  la base de donnees
	public static void addcom(String datecom, Double Soustrait,Double casgueri,Double mortotal,Double casimpor,Double casdeces,Double testrealise,Double casconct,Double casposit,Double cascommunautaire) {
		 
		 //Databassssseeeesssss addeddd
		 
		 
		 
		 try {
			// This will load the MySQL driver, each DB has its own driver
				Class.forName("com.mysql.jdbc.Driver");

				// Setup the connection with the DB
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bar", "root", "");
		      String query = " insert into data (Date, Soustraitement, casgueri,mortotal, casimpor,casdeces,testrealise,casconct,casposit,cascommunautaire)"
				        + " values ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				      // create the mysql insert preparedstatement
				      PreparedStatement preparedStmt = connect.prepareStatement(query);
				      preparedStmt.setString (1,datecom);
				      preparedStmt.setDouble (2,  Soustrait);
				      preparedStmt.setDouble  (3, casgueri);
				      preparedStmt.setDouble(4,mortotal);
				      preparedStmt.setDouble(5,casimpor);
				      preparedStmt.setDouble(6, casdeces);
				      preparedStmt.setDouble(7,testrealise);
				      preparedStmt.setDouble(8,casconct);
				      preparedStmt.setDouble(9,casposit);
				      preparedStmt.setDouble(10,cascommunautaire);

				      // execute the preparedstatement
				      preparedStmt.execute();
				      connect.close();
				      
				    
					 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// the mysql insert statement
	
		 //End Addedd
		 
		
		
	}
	public static void addDep(String nomDep,int nbcas) {
		
   try {
				// This will load the MySQL driver, each DB has its own driver	
		Class.forName("com.mysql.jdbc.Driver");

		// Setup the connection with the DB
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bar", "root", "roottoor");
      String query = " insert into departement (nom_dep,cas_dep)"
		        + " values ( ?, ?)";
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = connect.prepareStatement(query);
		      preparedStmt.setString (1,nomDep);
		      preparedStmt.setInt(2,nbcas);
		      // execute the preparedstatement
		      preparedStmt.execute();
		      connect.close();
		      
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// the mysql insert statement
		
			 //End Addedd
			 	      
	}
	
	public static void addReg(String nomReg,int nbcas) {
		
		   try {
						// This will load the MySQL driver, each DB has its own driver	
				Class.forName("com.mysql.jdbc.Driver");

				// Setup the connection with the DB
				Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/bar", "root", "");
		      String query = " insert into regions(nom_reg,cas_reg)"
				        + " values ( ?, ?)";
				      // create the mysql insert preparedstatement
				      PreparedStatement preparedStmt = connect.prepareStatement(query);
				      preparedStmt.setString (1,nomReg);
				      preparedStmt.setInt(2,nbcas);
				      // execute the preparedstatement
				      preparedStmt.execute();
				      connect.close();
				      
						 
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					// the mysql insert statement
				
					 //End Addedd
					 	      
		   }				
	

}
