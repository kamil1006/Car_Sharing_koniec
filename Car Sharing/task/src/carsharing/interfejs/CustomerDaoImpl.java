package carsharing.interfejs;

import carsharing.entity.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CustomerDaoImpl implements CustomerDao{


    static final String JDBC_DRIVER = "org.h2.Driver";
    static final  String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    //  Database credentials
    static final String USER = "";//"sa";
    static final String PASS = "";

    static final String tableName="CUSTOMER";



    //----------------------------------------------------------------------------------
    @Override
    public List<Customer> getAllCustomers() {
        Connection conn = null;
        Statement stmt = null;

        List<Customer> lista= new ArrayList<>();

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
                int idCar = rs.getInt("RENTED_CAR_ID");

                // Display values
                // System.out.print("ID: " + id);
                //  System.out.print(", name: " + name);

                lista.add(new Customer(id,name,idCar));


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
    public Customer getCustomerById(int odCustomer) {
        Connection conn = null;
        Statement stmt = null;

        Customer customer=null;

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
            String sql = "SELECT * FROM "+tableName+" WHERE ID = "+odCustomer;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int idCar = rs.getInt("RENTED_CAR_ID");
               // System.out.println(idCar);
                // Display values
                // System.out.print("ID: " + id);
                //  System.out.print(", name: " + name);

                customer=new Customer(id,name,idCar);


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

        return customer;

    }
    //----------------------------------------------------------------------------------
    @Override
    public void insertCustomer(Customer customer) {

        String value1= customer.getName();


        Connection conn = null;
        Statement stmt = null;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(true);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            String sql = "INSERT INTO "+tableName+" " + "(NAME) VALUES ( '"+value1+"')";

            stmt.executeUpdate(sql);

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
    public void rentCar(int idCustomer, int idCar) {

        Connection conn = null;
        Statement stmt = null;
        String sql;
        try{
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            conn.setAutoCommit(true);

            // STEP 3: Execute a query
            stmt = conn.createStatement();
            if(idCar!=0) {
                sql = "UPDATE " + tableName + " SET RENTED_CAR_ID = " + idCar + " WHERE ID = " + idCustomer + " ";
                //System.out.println(sql);
            }
            else {
                sql = "UPDATE " + tableName + " SET RENTED_CAR_ID = NULL WHERE ID = " + idCustomer + " ";

            }

            stmt.executeUpdate(sql);

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
    public void returnCar(int idCustomer, int idCar) {

        this.rentCar(idCustomer,0);







    }
    //----------------------------------------------------------------------------------
    @Override
    public boolean czyCustomerWynajal(int idCustomer) {

        Customer c=this.getCustomerById(idCustomer);
       if( !Objects.isNull(c.getRentedCarId()) && c.getRentedCarId()!=0){
           //System.out.println(true+String.valueOf(c.getRentedCarId()));
           return true;
       }else{
          // System.out.println(false+String.valueOf(c.getRentedCarId()));
           return false;
       }



    }
    //----------------------------------------------------------------------------------
}
