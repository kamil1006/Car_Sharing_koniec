package carsharing.interfejs;

import carsharing.entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao{

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    // static final String DB_URL = "jdbc:h2:file:~/carsharing/db/carsharing";
    //static final String DB_URL = "jdbc:h2:file:"+System.getProperty("user.dir")+"/src/carsharing/db/carsharing";
   // static final String DB_URL = "jdbc:h2:file:"+"./Car Sharing/task/src/carsharing/db/carsharing";
    static final  String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    //  Database credentials
    static final String USER = "";//"sa";
    static final String PASS = "";

    static final String tableName="COMPANY";

    //----------------------------------------------------------------------------------
    @Override
    public List<Company> getAllCompanies() {

        Connection conn = null;
        Statement stmt = null;

        List<Company> lista= new ArrayList<>();

        try{

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            conn.setAutoCommit(true);

            // STEP 3: Execute a query
           // System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tableName+"";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");

                // Display values
               // System.out.print("ID: " + id);
              //  System.out.print(", name: " + name);

                lista.add(new Company(id,name));


            }
            // STEP 5: Clean-up environment
            rs.close();



        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try




        return lista;
    }
    //----------------------------------------------------------------------------------
    @Override
    public Company getCompanyById(int idCompany) {
        Connection conn = null;
        Statement stmt = null;

        Company company = null;

        try{

            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            //System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            conn.setAutoCommit(true);

            // STEP 3: Execute a query
            // System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM "+tableName+" WHERE ID= "+idCompany;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");

                // Display values
                // System.out.print("ID: " + id);
                //  System.out.print(", name: " + name);

                company=new Company(id,name);


            }
            // STEP 5: Clean-up environment
            rs.close();



        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        }finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try




        return company;
    }
    //----------------------------------------------------------------------------------
    @Override
    public void deleteCompanyById(int id) {

    }
    //----------------------------------------------------------------------------------
    @Override
    public void insertCompany(Company company) {



        String value1=company.getName();

        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            //System.out.println("Connecting to a selected database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(true);

            //System.out.println("Connected database successfully...");

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO "+tableName+" " + "(NAME) VALUES ( '"+value1+"' )";

            stmt.executeUpdate(sql);

           // System.out.println("Inserted records into the table...");

            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try





    }
    //----------------------------------------------------------------------------------
    @Override
    public void updateCompany(Company company) {

    }
    //----------------------------------------------------------------------------------
    @Override
    public void deleteCompany(Company company) {

    }
    //----------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------
}
