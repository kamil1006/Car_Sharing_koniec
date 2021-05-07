package carsharing.interfejs;

import carsharing.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao{


    static final String JDBC_DRIVER = "org.h2.Driver";
      static final  String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";
    //  Database credentials
    static final String USER = "";//"sa";
    static final String PASS = "";

    static final String tableName="CAR";


    @Override
    public List<Car> getAllCarsByCompanyId(int idCompany) {
        Connection conn = null;
        Statement stmt = null;

        List<Car> lista= new ArrayList<>();

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
            String sql = "SELECT * FROM "+tableName+" WHERE COMPANY_ID = "+idCompany;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int idComp= rs.getInt("COMPANY_ID");
                // Display values
                // System.out.print("ID: " + id);
                //  System.out.print(", name: " + name);

                //lista.add(new Company(id,name));
                lista.add(new Car(id,name,idComp));

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

    @Override
    public void insertCar(Car car) {


        String value1= car.getName();
        int value2= car.getCompanyId();

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
            String sql = "INSERT INTO "+tableName+" " + "(NAME,COMPANY_ID) VALUES ( '"+value1+"' , "+value2+")";

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

    @Override
    public Car getCarById(int idCar) {

        Connection conn = null;
        Statement stmt = null;

       Car car = null;

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
            String sql = "SELECT * FROM "+tableName+" WHERE ID = "+idCar;
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int idComp= rs.getInt("COMPANY_ID");
                // Display values
                // System.out.print("ID: " + id);
                //  System.out.print(", name: " + name);

                //lista.add(new Company(id,name));
                car=new Car(id,name,idComp);

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




        return car;




    }

    @Override
    public List<Car> getAllNotRentedCarsByCompanyId(int idCompany) {
        Connection conn = null;
        Statement stmt = null;

        List<Car> lista= new ArrayList<>();

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
            String sql = "SELECT ca.ID,ca.NAME,ca.COMPANY_ID FROM CAR ca " +
                    " LEFT JOIN CUSTOMER cust ON ca.id=cust.RENTED_CAR_ID"+
                    " WHERE ca.COMPANY_ID = "+idCompany +" AND cust.RENTED_CAR_ID IS NULL";
            ResultSet rs = stmt.executeQuery(sql);

            // STEP 4: Extract data from result set
            while(rs.next()) {
                // Retrieve by column name
                int id  = rs.getInt("ID");
                String name = rs.getString("NAME");
                int idComp= rs.getInt("COMPANY_ID");
                // Display values
                // System.out.print("ID: " + id);
                //  System.out.print(", name: " + name);

                //lista.add(new Company(id,name));
                lista.add(new Car(id,name,idComp));

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
}
