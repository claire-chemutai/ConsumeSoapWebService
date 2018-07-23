
package companydata;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import webservice.Departments;
import webservice.Staff;

/**
 *
 * CompanyData.java
 * Purpose: Retrieve departments and stuff data and store them in a database.
 *
 * @author Claire Chemutai
 * @version 1.0 5/14/18
 */
public class CompanyData {
    private static Connection conn;

    //makes connection to the database
    private  void DBConnection() throws SQLException{
        System.out.println("Connecting to the Database");
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/company?user=root&password=root");

        }
        catch (ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e) {
            System.err.println(e);
            System.exit(0);
            
        }
}
    /**
    * Returns the list of departments
    * The departmentID argument must specify the ID of a specific department. 
    *
    * @return  the departments 
    */
    private static java.util.List<webservice.Departments> fetchDepartments() {
        webservice.Company_Service service = new webservice.Company_Service();
        webservice.Company port = service.getCompanyPort();
        return port.fetchDepartments();
    }
    
    
    /**
    * Returns the staff in each department 
    * The departmentID argument must specify the ID of a specific department. 
    *
    * @param  departmentID  an integer specifying the department 
    * @return the staff in the specified department
    */
    private static java.util.List<webservice.Staff> fetchStaff(java.lang.Integer departmentID) {
        webservice.Company_Service service = new webservice.Company_Service();
        webservice.Company port = service.getCompanyPort();
        return port.fetchStaff(departmentID);
    }
    
    
    /**
    * inserts the department details into the departments table
    */
    public  void insertDepartments(){
        List<Departments> departments= fetchDepartments();
        
        for(Departments department: departments ){
            String name= department.getName();
            String dateCreated= department.getDateCreated();
            String dateModified= department.getDateModified();
            String description= department.getDescription();
            
            try{
                PreparedStatement ps;
                ps = conn.prepareStatement("Insert Into departments set  name=?,"
                        + " dateCreated=?, dateModified=?, description=?");
                ps.setString(1, name);
                ps.setString(2, dateCreated);
                ps.setString(3, dateModified);
                ps.setString(4, description);

                ps.execute(); //use execute if no results expected back;
            }catch(SQLException error){
                System.err.println("Error "+error.toString());
                return;
            }
    
    }
    }
    
    /**
    * inserts the staff details into the staff table
    */
        public void insertStaff(){
           List<Departments> departments= fetchDepartments();
           for(Departments department: departments ){
            List<Staff> stafflist= fetchStaff(department.getDepartmentID());
           
            for(Staff depStaff: stafflist){
                String firstname= depStaff.getFirstName();
                System.out.println(firstname);
                String lastName= depStaff.getLastName();
                String emailAddress= depStaff.getEmailAddress();
                int departmentID= depStaff.getDepartmentID();
                String dateCreated_staff= depStaff.getDateCreated();
                String dateModified_staff= depStaff.getDateModified();
            try{
                PreparedStatement ps;
                ps = conn.prepareStatement("Insert Into staff set firstname=?,lastName=?,"
                        + " emailAddress=?, departmentID=?,dateCreated=?, dateModified=? ");
                ps.setString(1, firstname);
                ps.setString(2, lastName);
                ps.setString(3, emailAddress);
                ps.setInt(4, departmentID);
                ps.setString(5, dateCreated_staff);
                ps.setString(6, dateModified_staff);


                ps.execute(); //use execute if no results expected back
            }catch(SQLException error){
                System.err.println("Error "+error.toString());
                return;
            }
            }
           }
    
    }
        
        
   /**
    * The main method for the CompanyData program.
    *
    * @param args Not used
    */
    
     public static  void main(String[] args) {
         Logger log=Logger.getLogger("MyLogger");
         FileHandler fh;
         try{
             fh=new FileHandler("C:/Users/study/Documents/MEGA/NetBeansProjects/CompanyData/ActivityLog.txt", true);
             log.addHandler(fh);
             SimpleFormatter format= new SimpleFormatter();
             fh.setFormatter(format);
             
       
        CompanyData company= new CompanyData();
        log.info("connect database");
        company.DBConnection(); //creates connection
        log.info("insert departments");
        company.insertDepartments();//inserts into the departments table
        log.info("insert staff");
        company.insertStaff(); //inserts into the staff table
        
           
        }
         catch (SecurityException e){
             e.printStackTrace();
         } catch (IOException ex) {
            Logger.getLogger(CompanyData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(CompanyData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
        
    }
    
