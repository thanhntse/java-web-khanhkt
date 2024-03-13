/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thanhnt.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author thinkpad
 */
public class DBHelper implements Serializable {

    public static Connection getConnection() 
            throws /*ClassNotFoundException,*/ SQLException, NamingException {
        
        //1. Get current context
        Context currentContext = new InitialContext();
        //2. Get tomcat context
        Context tomcatContext = (Context)currentContext.lookup("java:comp/env");
        //3. Access DS
        DataSource ds = (DataSource)tomcatContext.lookup("DS007");
        //4. Open connection
        Connection con = ds.getConnection();
        
        return con;
        
        
//        //1. Load Driver (driver is available)
//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        //2. Create Connection String
//        String url = "jdbc:sqlserver://localhost:1433;"
//                + "databaseName=javaweb;"
//                + "instanceName=NEWSQLEXPRESS";
//        //3. Open Connection
//        Connection con = DriverManager.getConnection(url, "sa", "123");
//        
//        return con;
    }
}
