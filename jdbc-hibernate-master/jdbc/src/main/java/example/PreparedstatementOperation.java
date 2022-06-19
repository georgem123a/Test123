package example;

import java.sql.*;

public class PreparedstatementOperation {

    private Connection conn = null;
    private static final String SELECT = "SELECT id, name, email, country FROM user WHERE name = ?";
    private static final String INSERT = "INSERT user(name, email, country) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET name = ?, email = ?, country = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM user WHERE id = ?";

    public PreparedstatementOperation(){
        String url = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
        String user = "user01";
        String pass = "pass01";

        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private int selectOperation(String name){
        try{
            if(conn != null) {
                //SELECT
                PreparedStatement pstmt = conn.prepareStatement(SELECT);
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();
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
            if(conn != null) {
                //INSERT
                PreparedStatement pstmt = conn.prepareStatement(INSERT);
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, country);
                int ret = pstmt.executeUpdate();
                System.out.println("Insert return: " + (ret == 1 ? "OK" : "ERROR"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void updateOperation(int id, String name, String email, String country){
        try{
            if(conn != null) {
                //UPDATE
                PreparedStatement pstmt = conn.prepareStatement(UPDATE);
                pstmt.setString(1, name);
                pstmt.setString(2, email);
                pstmt.setString(3, country);
                pstmt.setInt(4, id);
                int ret = pstmt.executeUpdate();
                System.out.println("Update return: " + (ret == 1 ? "OK" : "ERROR"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    private void deleteOperation(int id){
        try{
            if(conn != null) {
                //DELETE
                PreparedStatement pstmt = conn.prepareStatement(DELETE);
                pstmt.setInt(1, id);
                int ret = pstmt.executeUpdate();
                System.out.println("Delete return: " + (ret == 1 ? "OK" : "ERROR"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static void preparedstatementOperation(){
        PreparedstatementOperation command = new PreparedstatementOperation();
        command.insertOperation("test1", "test1@test.com", "Poland");
        int id = command.selectOperation("test1");
        command.updateOperation(id, "test2", "test2@test.com", "Poland");
        id = command.selectOperation("test2");
        command.deleteOperation(id);
    }
}
