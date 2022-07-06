package models;

import configs.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author rizkyalam
 */
public class Users extends Database {
    public Users() {
        super();
    }

    public ArrayList login(String username, String password) throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(
                "SELECT * FROM users WHERE password='"+ password +"' and username='"+ username +"'"
        );

        int id = 0;
        String name = "";

        if(result.next()){
            id = result.getInt(1);
            name = result.getString(2);
        }
        
        ArrayList<Object> newResultList = new ArrayList<>();
        newResultList.add(id);
        newResultList.add(name);

        return newResultList;
    }
}
