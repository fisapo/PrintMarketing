package services;

import dao.PrintMarketingDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Location;

public class RegisterLocationService {

    public int addLocation(String LocationName, int DistributionCapacity,
            PrintMarketingDao dao) {
        int res = 0;
        Location LocationObj = new Location();
        if (LocationName != null && String.valueOf(DistributionCapacity) != null) {
            LocationObj.setLocationName(LocationName);
            LocationObj.setDistributionCapacity(DistributionCapacity);
            res = dao.addLocation(LocationObj);
        }
        return res;
    }

    public ArrayList<Location> viewLocations(PrintMarketingDao dao) {
        ArrayList<Location> locationList = new ArrayList();
        locationList = dao.viewLocations();
        return locationList;
    }

    public Location showLocation(int id, PrintMarketingDao dao) throws SQLException {
        Location LocationObj = dao.showLocation(id);
        return LocationObj;
    }

    public boolean updateLocation(Location LocationObj, PrintMarketingDao dao) throws SQLException {
        boolean res = dao.updateLocation(LocationObj);
        return res;
    }

    public boolean deleteLocation(Location LocationObj, PrintMarketingDao dao) throws SQLException {
        boolean res = dao.deleteLocation(LocationObj);
        return res;
    }
}
