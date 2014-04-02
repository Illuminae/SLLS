/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package active_record;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Erik
 */
public abstract class DatabaseUtility {

    static String dbUrl = "jdbc:derby://localhost:1527/slls";
    static String dbName = "administrator";
    static String dbPword = "geheim";

    protected static Connection getDatabaseConnection() throws Exception {
        Connection con = null;

        try {
            con = DriverManager.getConnection(dbUrl, dbName, dbPword);
        } catch (SQLException sqle) {
            String msg = "Connection to database could not be established.";
            throw new Exception(msg, sqle);
        } finally {
            return con;
        }

    }

}
