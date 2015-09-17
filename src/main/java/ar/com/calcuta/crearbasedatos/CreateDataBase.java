/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.calcuta.crearbasedatos;

import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Francisco M. Viola <francimviola@gmail.com>
 */
public class CreateDataBase {

    private static final String DB_NAME = "madreTeresa";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "root";
    private static final String FILE_RESTORE = "calcuta.sql";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";
    private static final Logger LOG = Logger.getLogger(CreateDataBase.class.getName());

    public Boolean createDB() {

        Connection conn = null;
        Statement stmt = null;
        try {
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

            //STEP 4: Execute a query
            System.out.println("Creating database...");
            stmt = (Statement) conn.createStatement();

            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sql);
            if (restoreDB()) {
                System.out.println("Database created successfully...");
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
            return Boolean.FALSE;
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }//end finally try
        }//end try
    }

    private boolean restoreDB() {
        String[] comando = new String[]{"mysql", DB_NAME, "-u" + DB_USER, "-p" + DB_PASS, "-e", " source " + FILE_RESTORE};
        Process runtimeProcess;
        int processComplete = 0;
        try {
            runtimeProcess = Runtime.getRuntime().exec(comando);
            processComplete = runtimeProcess.waitFor();
        } catch (IOException | InterruptedException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return processComplete == 0;
    }

    public static String getDB_NAME() {
        return DB_NAME;
    }

}
