package example;

import java.sql.*;

public class CommandOperation {

    private Statement stmt = null;

    public CommandOperation(){
        String url = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
        String user = "user01";
        String pass = "pass01";

        try {
            Connection conn = DriverManager.getConnection(url, user, pass);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int selectOperation(String name){
        try{
            if(stmt != null) {
                //SELECT
                ResultSet rs = stmt.executeQuery("SELECT id, name, email, country FROM user WHERE name = '" + name + "'");
                boolean next = rs.next();
                if(next) {
                    int id = rs.getInt("id");
                    String name2 = rs.getString("name");
                    String email = rs.getString("email");
                    String country = rs.getString("country");
                    System.out.println(id + ", " + name2 + ", " + email + ", " + country);
                    return id;
                }else{
                    return -1;
                }
            }else{
                return -1;
            }
        }catch (SQLException ex){
            ex.printStackTrace();
            return -1;
        }
    }

    private void insertOperation(String name, String email, String country){
        try{
            if(stmt != null) {
                //INSERT
                int ret = stmt.executeUpdate("INSERT user(name, email, country) VALUES " +
                        "('" + name + "', '" + email + "', '" + country + "')");
                System.out.println("Insert return: " + (ret == 1 ? "OK" : "ERROR"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void updateOperation(int id, String name, String email, String country){
        try{
            if(stmt != null) {
                //UPDATE
                int ret = stmt.executeUpdate("UPDATE user SET name = '" + name + "', " +
                        "email = '" + email + "', country = '" + country + "' WHERE id = " + id);
                System.out.println("Update return: " + (ret == 1 ? "OK" : "ERROR"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void deleteOperation(int id){
        try{
            if(stmt != null) {
                //DELETE
                int ret = stmt.executeUpdate("DELETE FROM user WHERE id = " + id);
                System.out.println("Delete return: " + (ret == 1 ? "OK" : "ERROR"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void commandOperation(){
        CommandOperation command = new CommandOperation();
        command.insertOperation("test1", "test1@test.com", "Poland");
        int id = command.selectOperation("test1");
        command.updateOperation(id, "test2", "test2@test.com", "Poland");
        id = command.selectOperation("test2");
        command.deleteOperation(id);
    }
}
