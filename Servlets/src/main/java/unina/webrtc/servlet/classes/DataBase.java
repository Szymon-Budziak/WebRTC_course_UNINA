package unina.webrtc.servlet.classes;

import java.sql.*;

public class DataBase {
    private Connection connection;
    private String text = new String();

    public DataBase(String driver, String url) {
        String urlTZ = url + "?serverTimezone=UTC";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(urlTZ);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean isClosed() {
        boolean closed = false;
        try {
            closed = connection.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return closed;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
        }
    }

    public ResultSet sqlExp(String sql) {
        ResultSet r = null;
        try {
            Statement statement = connection.createStatement();
            boolean hasResults = statement.execute(sql);
            if (hasResults) {
                r = statement.getResultSet();
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return r;
    }

    public ResultSet sqlUpdate(String sql) {
        ResultSet r = null;
        try {
            Statement statement = connection.createStatement();
            int hasResults = statement.executeUpdate(sql);
            if (hasResults != 0) {
                r = statement.getResultSet();
            }
        } catch (Exception ex) {
        }
        return r;
    }

    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/WebRTC";

        DataBase db = new DataBase(driver, url);

        String query = "INSERT INTO User (username, name, surname, nickname, email) VALUES ('admin','admin', 'admin', 'admin', 'admin')";
        db.sqlExp(query);
    }
}