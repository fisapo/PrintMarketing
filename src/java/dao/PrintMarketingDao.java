package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Client;

public class PrintMarketingDao {

    private String url;
    private String userDB;
    private String passDB;

    public PrintMarketingDao() {
    }

    public PrintMarketingDao(String url, String userDB, String passDB) {
        this.url = url;
        this.userDB = userDB;
        this.passDB = passDB;
    }

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, userDB, passDB);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public int addClient(Client clientObj) {
        int res = 0;
        String sql = "INSERT INTO clients (userId, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, clientObj.getUserId());
                stmt.setString(2, clientObj.getFirstName());
                stmt.setString(3, clientObj.getLastName());
                stmt.setInt(4, clientObj.getStreetNumber());
                stmt.setString(5, clientObj.getStreetName());
                stmt.setString(6, clientObj.getCity());
                stmt.setString(7, clientObj.getProvince());
                stmt.setString(8, clientObj.getPostalCode());
                stmt.setString(9, clientObj.getTelOffice());
                stmt.setString(10, clientObj.getTelCell());
                stmt.setString(11, clientObj.getEmail());
                stmt.setString(12, clientObj.getCompany());
                stmt.setString(13, clientObj.getCompanyType());
                res = stmt.executeUpdate();
                conn.close();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return res;
    }

    public ArrayList<Client> viewClients() {
        ArrayList<Client> clientList = new ArrayList();
        String sql = "SELECT * FROM clients";
        int id;
        int userId;
        String firstName;
        String lastName;
        int streetNumber;
        String streetName;
        String city;
        String province;
        String postalCode;
        String telOffice;
        String telCell;
        String email;
        String company;
        String companyType;

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                id = resultSet.getInt("id");
                userId = resultSet.getInt("userId");
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                streetNumber = resultSet.getInt("streetNumber");
                streetName = resultSet.getString("streetName");
                city = resultSet.getString("city");
                province = resultSet.getString("province");
                postalCode = resultSet.getString("postalCode");
                telOffice = resultSet.getString("telOffice");
                telCell = resultSet.getString("telCell");
                email = resultSet.getString("email");
                company = resultSet.getString("company");
                companyType = resultSet.getString("companyType");

                Client clientObj = new Client();

                clientObj.setId(id);
                clientObj.setUserId(userId);
                clientObj.setFirstName(firstName);
                clientObj.setLastName(lastName);
                clientObj.setStreetNumber(streetNumber);
                clientObj.setStreetName(streetName);
                clientObj.setCity(city);
                clientObj.setProvince(province);
                clientObj.setPostalCode(postalCode);
                clientObj.setTelOffice(telOffice);
                clientObj.setTelCell(telCell);
                clientObj.setEmail(email);
                clientObj.setCompany(company);
                clientObj.setCompanyType(companyType);

                clientList.add(clientObj);
            }
            resultSet.close();
            stmt.close();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return clientList;
    }

    public Client showClient(int id) throws SQLException {
        Client clientObj = null;
        String sql = "SELECT * FROM clients ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            clientObj = new Client();
            clientObj.setId(result.getInt("id"));
            clientObj.setUserId(result.getInt("userId"));
            clientObj.setFirstName(result.getString("firstName"));
            clientObj.setLastName(result.getString("lastName"));
            clientObj.setStreetNumber(result.getInt("streetNumber"));
            clientObj.setStreetName(result.getString("streetName"));
            clientObj.setCity(result.getString("city"));
            clientObj.setProvince(result.getString("province"));
            clientObj.setPostalCode(result.getString("PostalCode"));
            clientObj.setTelOffice(result.getString("telOffice"));
            clientObj.setTelCell(result.getString("telCell"));
            clientObj.setEmail(result.getString("email"));
            clientObj.setCompany(result.getString("company"));
            clientObj.setCompanyType(result.getString("companyType"));
        }
        return clientObj;
    }
}
