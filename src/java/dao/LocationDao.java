package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Agent;
import model.Location;

public class LocationDao {

    private String url;
    private String locationDB;
    private String passDB;

    public LocationDao(String url, String locationDB, String passDB) {
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
}
