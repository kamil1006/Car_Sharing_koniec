package carsharing.interfejs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tworzymy {

    public Tworzymy() {
        tworz();
    }


    public void tworz(){


        // JDBC driver name and database URL
         String JDBC_DRIVER = "org.h2.Driver";
        // static final String DB_URL = "jdbc:h2:file:~/carsharing/db/carsharing";

        //  Database credentials
         String USER = "";//"sa";
         String PASS = "";


        final String dir = System.getProperty("user.dir");
       // System.out.println("current dir = " + dir);
        //String DB_URL = "jdbc:h2:file:"+dir+"/src/carsharing/db/carsharing";
       // String DB_URL = "jdbc:h2:file:"+"./Car Sharing/task/src/carsharing/db/carsharing";
        String DB_URL = "jdbc:h2:./src/carsharing/db/carsharing";

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 2: Open a connection
         //   System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            conn.setAutoCommit(true);


            var catalog = conn.getCatalog();
        //    System.out.println("------------");
        //    System.out.println(catalog);
         //   System.out.println(DB_URL);
          //  System.out.println("------------");

            //STEP 3: Execute a query
         //   System.out.println("Creating table in given database...");
            stmt = conn.createStatement();
            String sql =  "CREATE TABLE  IF NOT EXISTS COMPANY " +
                    "(ID INTEGER AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE not NULL, " +

                    " PRIMARY KEY ( ID ))";
            stmt.executeUpdate(sql);
       //     System.out.println("Created table in given database...");

            String sql2 =  "CREATE TABLE  IF NOT EXISTS CAR " +
                    "(ID INTEGER AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    " COMPANY_ID INTEGER NOT NULL, " +
                    "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID), "+
                    " PRIMARY KEY ( ID ))";
            stmt.executeUpdate(sql2);


            String sql3 =  "CREATE TABLE  IF NOT EXISTS CUSTOMER " +
                    "(ID INTEGER AUTO_INCREMENT, " +
                    " NAME VARCHAR(255) UNIQUE NOT NULL, " +
                    " RENTED_CAR_ID INTEGER DEFAULT NULL, " +
                    "CONSTRAINT fk_car FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID), "+
                    " PRIMARY KEY ( ID ))";
            stmt.executeUpdate(sql3);


            // STEP 4: Clean-up environment
            stmt.close();
            conn.close();


        } catch(SQLException se) {
            //Handle errors for JDBC
           // se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
           // e.printStackTrace();
        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
               // se.printStackTrace();
            } //end finally try
        } //end try
        //System.out.println("Goodbye!");











    }

}
