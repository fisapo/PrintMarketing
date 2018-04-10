package services;
//ansdansdpoans
//solve this

import dao.LocationDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Agent;

public class RegisterAgentService {

    public int addAgent(String firstName, String lastName, String phoneNo, String email,
            String userName, String password, LocationDao dao) {
        int res = 0;
        Agent AgentObj = new Agent();
        if (FormFilled(firstName, lastName, phoneNo, email, userName, password)) {
            AgentObj.setFirstName(firstName);
            AgentObj.setLastName(lastName);
            AgentObj.setPhoneNo(phoneNo);
            AgentObj.setEmail(email);
            AgentObj.setUserName(userName);
            AgentObj.setPassword(password);
            res = dao.addAgent(AgentObj);
        }
        return res;
    }

    public ArrayList<Agent> viewAgents(LocationDao dao) {
        ArrayList<Agent> agentList = new ArrayList();
        agentList = dao.viewAgents();
        return agentList;
    }

    public Agent showAgent(int id, LocationDao dao) throws SQLException {
        Agent AgentObj = dao.showAgent(id);
        return AgentObj;
    }

    public boolean updateAgent(Agent AgentObj, LocationDao dao) throws SQLException {
        boolean res = dao.updateAgent(AgentObj);
        return res;
    }

    public boolean deleteAgent(Agent AgentObj, LocationDao dao) throws SQLException {
        boolean res = dao.deleteAgent(AgentObj);
        return res;
    }

    private boolean FormFilled(String fnm, String lnm, String Phno, String email, String user, String pass) {//Checks if all of textboxes are filled.
        if (fnm != null && lnm != null && Phno != null && email != null && user != null && pass != null) {
            return true;
        }
        return false;
    }
}
