package dao;
//test for github - andrij
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Agent;
import model.Location;

public class PrintMarketingDao {

    private String url;
    private String locationDB;
    private String passDB;

    public PrintMarketingDao() {
    }

    public PrintMarketingDao(String url, String locationDB, String passDB) {
        this.url = url;
        this.locationDB = locationDB;
        this.passDB = passDB;
    }

    protected Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(url, locationDB, passDB);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    public int addLocation(Location locationObj) {
        int res = 0;
        String sql = "INSERT INTO location (locationName,distributionCapacity) VALUES (?,?)";
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, locationObj.getLocationName());
                stmt.setInt(2, locationObj.getDistributionCapacity());
                res = stmt.executeUpdate();
                stmt.close();
                conn.close();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return res;
    }

    public ArrayList<Location> viewLocations() {
        ArrayList<Location> locationList = new ArrayList();
        String sql = "SELECT * FROM location";
        String locationNM = "";
        String DCapacity = "";
        int id = 0;

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                locationNM = resultSet.getString("locationName");
                DCapacity = resultSet.getString("distributionCapacity");
                id = resultSet.getInt("id");
                Location locationObj = new Location();

                locationObj.setLocationId(id);
                locationObj.setLocationName(locationNM);
                locationObj.setDistributionCapacity(Integer.parseInt(DCapacity));
                locationList.add(locationObj);
            }
            resultSet.close();
            stmt.close();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return locationList;
    }

    public Location showLocation(int id) throws SQLException {
        Location locationObj = null;
        String sql = "SELECT * FROM location ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            locationObj = new Location();
            locationObj.setLocationId(result.getInt("id"));
            locationObj.setLocationName(result.getString("locationName"));
            locationObj.setDistributionCapacity(result.getInt("distributionCapacity"));
        }
        return locationObj;
    }

    public boolean updateLocation(Location locationObj) throws SQLException {
        boolean res;
        String sql = "UPDATE location SET locationName = ?, distributionCapacity = ? ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, locationObj.getLocationName());
        statement.setInt(2, locationObj.getDistributionCapacity());
        statement.setInt(3, locationObj.getLocationId());

        res = statement.executeUpdate() > 0;

        return res;
    }

    public boolean deleteLocation(Location locationObj) throws SQLException {
        boolean res;
        String sql = "delete from location  ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, locationObj.getLocationId());
        res = statement.executeUpdate() > 0;

        return res;
    }

    public int addAgent(Agent agentObj) {
        int res = 0;
        String sql = "INSERT INTO marketingagent (firstName, lastName, phoneNo, email, userName, password) VALUES (?,?,?,?,?,?)";
        try {
            Connection conn = getConnection();
            if (conn != null) {
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, agentObj.getFirstName());
                stmt.setString(2, agentObj.getLastName());
                stmt.setString(3, agentObj.getPhoneNo());
                stmt.setString(4, agentObj.getEmail());
                stmt.setString(5, agentObj.getUserName());
                stmt.setString(6, agentObj.getPassword());
                res = stmt.executeUpdate();
                stmt.close();
                conn.close();
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return res;
    }

    public ArrayList<Agent> viewAgents() {
        ArrayList<Agent> agentList = new ArrayList();
        String sql = "SELECT * FROM marketingagent";
        String firstName, lastName, phoneNo, email, userName, password = "";
        int id = 0;

        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);

            while (resultSet.next()) {
                firstName = resultSet.getString("firstName");
                lastName = resultSet.getString("lastName");
                phoneNo = resultSet.getString("phoneNo");
                email = resultSet.getString("email");
                userName = resultSet.getString("userName");
                password = resultSet.getString("password");
                id = resultSet.getInt("id");
                Agent agentObj = new Agent();

                agentObj.setAgentId(id);
                agentObj.setFirstName(firstName);
                agentObj.setLastName(lastName);
                agentObj.setPhoneNo(phoneNo);
                agentObj.setEmail(email);
                agentObj.setUserName(userName);
                agentObj.setPassword(password);
                agentList.add(agentObj);
            }
            resultSet.close();
            stmt.close();
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        return agentList;
    }

    public Agent showAgent(int id) throws SQLException {
        Agent agentObj = null;
        String sql = "SELECT * FROM marketingagent ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            agentObj = new Agent();
            agentObj.setAgentId(result.getInt("id"));
            agentObj.setFirstName(result.getString("firstName"));
            agentObj.setLastName(result.getString("lastName"));
            agentObj.setPhoneNo(result.getString("phoneNo"));
            agentObj.setEmail(result.getString("email"));
            agentObj.setUserName(result.getString("userName"));
            agentObj.setPassword(result.getString("password"));//I wasn't gonna include this primarily cause it's for privacy reasons
            //but I'll add it anyways in case we lose marks if we don't.
        }
        return agentObj;
    }

    public boolean updateAgent(Agent agentObj) throws SQLException {
        boolean res;
        String sql = "UPDATE marketingagent SET firstName = ?, lastName = ?, phoneNo = ?, email = ?,userName = ?, password = ? ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, agentObj.getFirstName());
        statement.setString(2, agentObj.getLastName());
        statement.setString(3, agentObj.getPhoneNo());
        statement.setString(4, agentObj.getEmail());
        statement.setString(5, agentObj.getUserName());
        statement.setString(6, agentObj.getPassword());
        statement.setInt(7, agentObj.getAgentId());

        res = statement.executeUpdate() > 0;

        return res;
    }

    public boolean deleteAgent(Agent agentObj) throws SQLException {
        boolean res;
        String sql = "delete from marketingagent  ";
        sql += "WHERE id = ?";

        Connection con = getConnection();
        PreparedStatement statement = con.prepareStatement(sql);
        statement.setInt(1, agentObj.getAgentId());
        res = statement.executeUpdate() > 0;

        return res;
    }
}
