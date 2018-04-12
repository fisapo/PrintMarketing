package services;

import dao.LocationDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Location;

public class RegisterLocationService {

    public int addLocation(String LocationName, int DistributionCapacity,
            LocationDao dao) {
        int res = 0;
        Location LocationObj = new Location();
        if (LocationName != null && String.valueOf(DistributionCapacity) != null) {
            LocationObj.setLocationName(LocationName);
            LocationObj.setDistributionCapacity(DistributionCapacity);
            res = dao.addLocation(LocationObj);
        }
        System.out.println(res);
        return res;
    }

    public ArrayList<Location> viewLocations(LocationDao dao) {
        ArrayList<Location> locationList = new ArrayList();
        locationList = dao.viewLocations();
        return locationList;
    }

    public Location showLocation(int id, LocationDao dao) throws SQLException {
        Location LocationObj = dao.showLocation(id);
        return LocationObj;
    }

    public boolean updateLocation(Location LocationObj, LocationDao dao) throws SQLException {
        boolean res = dao.updateLocation(LocationObj);
        return res;
    }

    public boolean deleteLocation(Location LocationObj, LocationDao dao) throws SQLException {
        boolean res = dao.deleteLocation(LocationObj);
        return res;
    }
}
