package com.sun.bosen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBUtil {
	
	    static String ip = "127.0.0.1";
	    static int port = 1433;
	    static String database = "UFDATA_003_2017";
	    static String encoding = "GBK";
	    static String loginName = "SA";
	    static String password = "Sa123040";

	    static {
	        try {
	            Class.forName("net.sourceforge.jtds.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	    }

	    public static Connection getSQLConnection() throws SQLException {
	        Connection con = null;

	        con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":" + port + "/" + database + ";charset=" + encoding, loginName, password);

	        return con;
	    }
	    public static void main(String[] args) throws SQLException {
			System.out.println(DBUtil.getSQLConnection());
		}
	 

}
