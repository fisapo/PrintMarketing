package services;

import dao.AgentDao;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Agent;

public class RegisterAgentService {

    public int addAgent(String firstName, String lastName, String phoneNo, String email,
            String userName, String password, AgentDao dao) {
        int res = 0;
        Agent AgentObj = new Agent();
        if (FormFilled(firstName, lastName, phoneNo, email, userName, password)) {
            AgentObj.setFirstName(firstName.trim());//Trim removes the white spaces.
            AgentObj.setLastName(lastName.trim());
            AgentObj.setPhoneNo(phoneNo.trim());
            AgentObj.setEmail(email.trim());
            AgentObj.setUserName(userName.trim());
            AgentObj.setPassword(password.trim());
            res = dao.addAgent(AgentObj);
        }
        System.out.println(firstName + lastName + phoneNo + email + userName + password);

        return res;
    }

    public ArrayList<Agent> viewAgents(AgentDao dao) {
        ArrayList<Agent> agentList = new ArrayList();
        agentList = dao.viewAgents();
        return agentList;
    }

    public Agent showAgent(int id, AgentDao dao) throws SQLException {
        Agent AgentObj = dao.showAgent(id);
        return AgentObj;
    }

    public boolean updateAgent(Agent AgentObj, AgentDao dao) throws SQLException {
        boolean res = dao.updateAgent(AgentObj);
        return res;
    }

    public boolean deleteAgent(Agent AgentObj, AgentDao dao) throws SQLException {
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
