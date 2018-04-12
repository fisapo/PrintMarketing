package services;

import dao.PrintMarketingDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Client;

public class RegisterClientService {

    public int addClient(int userId, String firstName, String lastName, int streetNumber, String streetName, String city, String province, String postalCode, String telOffice, String telCell, String email, String company, String companyType, PrintMarketingDao dao) {
        int res = 0;
        Client clientObj = new Client();
        if (userId != 0 && firstName != null && lastName != null && streetNumber != 0 && streetName != null && city != null && province != null && postalCode != null && telOffice != null && telCell != null && email != null && company != null && companyType != null) {
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

            res = dao.addClient(clientObj);
        }
        return res;
    }

    public ArrayList<Client> viewClients(PrintMarketingDao dao) {
        ArrayList<Client> clientList = new ArrayList();
        clientList = dao.viewClients();
        return clientList;
    }

    public Client showClient(int id, PrintMarketingDao dao) throws SQLException {
        Client clientObj = dao.showClient(id);
        return clientObj;
    }
}
