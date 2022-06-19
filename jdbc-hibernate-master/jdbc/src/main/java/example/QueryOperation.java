package example;

import java.sql.*;

public class QueryOperation {

    public static void queryOperation(){
        String url = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
        String user = "user01";
        String pass = "pass01";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, name, email, country FROM user");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                System.out.println(id + ", " + name + ", " + email + ", " + country);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
