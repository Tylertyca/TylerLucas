import java.sql.*;

class jdbc {
    static Connection connect;

    public static void main(String[] args) {
        try {
            connect = DriverManager.getConnection(
                    "jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/TylerLucas?user=TylerLucas&password=365rocks");

            Statement statement = connect.createStatement();

            ResultSet resultSet = statement.executeQuery("show tables");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}