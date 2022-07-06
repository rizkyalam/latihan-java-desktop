package configs;

import java.sql.*;

/**
 *
 * @author rizkyalam
 */
public abstract class Database {
    private int dbPort = 3306;
    private String dbName = "db_latihan_java";
    private String dbUrl = "jdbc:mysql://localhost:"+ dbPort +"/"+ dbName;
    private String username = "root";
    private String password = "";

    protected Connection conn;

    public Database() {
        this.connection();
    }

    private void connection() {
        try {
            conn = DriverManager.getConnection(dbUrl, username, password);

            if (conn != null) {
                System.out.println("Connected");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
