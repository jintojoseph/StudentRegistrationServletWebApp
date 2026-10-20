import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;


import static java.lang.Class.forName;

public class JdbcUtil {
    private static Properties properties = new Properties();
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver is Registered");

            try (InputStream input = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
                if(input == null){
                    throw new RuntimeException("unable to get db credentials");
                }
                properties.load(input);
            }
        }catch (ClassNotFoundException | IOException e){
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database configuration: " + e.getMessage(), e);
        }

    }
    public static Connection getDBConnection() throws SQLException{
        String url = properties.getProperty("db.url");
        String userName = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        return DriverManager.getConnection(url,userName,password);

    }
    public static void closeResource(Connection connect, Statement stmnt) throws SQLException{
        if(stmnt != null) stmnt.close();
        if(connect != null) connect.close();

    }
}
