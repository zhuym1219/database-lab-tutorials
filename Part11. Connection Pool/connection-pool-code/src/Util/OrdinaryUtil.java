package Util;

import java.sql.*;

public class OrdinaryUtil extends DBUtil {
    private static OrdinaryUtil instance = new OrdinaryUtil();
    private String host = "localhost";
    private String dbname = "cs307";
    private String user = "checker";
    private String pwd = "123456";
    private String port = "5432";

    private OrdinaryUtil() {
    }

    public static OrdinaryUtil getInstance() {
        return instance;
    }


    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (Exception e) {
            System.err.println("Cannot find the PostgreSQL driver. Check CLASSPATH.");
            System.exit(1);
        }
        try {
            String url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
            return DriverManager.getConnection(url, user, pwd);

        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
        return null;
    }

    @Override
    public String getConnectState() {
        return "null";
    }
}
