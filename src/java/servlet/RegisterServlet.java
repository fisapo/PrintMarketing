package servlet;

import dao.AgentDao;
import dao.LocationDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agent;
import model.Location;
import services.RegisterAgentService;
import services.RegisterLocationService;

public class RegisterServlet extends HttpServlet {

    RegisterLocationService registerLocationService;
    RegisterAgentService registerAgentService;
    LocationDao registerLocationDao;
    AgentDao registerAgentDao;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        registerLocationDao = new LocationDao(jdbcURL, jdbcUserName, jdbcPassword);
        registerAgentDao = new AgentDao(jdbcURL, jdbcUserName, jdbcPassword);
        registerLocationService = new RegisterLocationService();
        registerAgentService = new RegisterAgentService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Action = request.getServletPath();//gets action+ word "Location"
        System.out.println(Action);//Used for debugging.
        switch (Action) {
            case "/newLocation":
                showNewLocationForm(request, response);
                break;

            case "/insertLocation":
                addLocation(request, response);
                break;

            case "/editLocation":
                showEditForm(request, response);
                break;

            case "/updateLocation":
                updateLocation(request, response);
                break;
            case "/deleteLocation":
                deleteLocation(request, response);
                break;
            case "/AgentFromLocation":
                viewAgents(request, response);
                break;
            case "/listLocation":
                viewLocations(request, response);
                break;
            case "/newAgent":
                showNewAgentForm(request, response);
                break;

            case "/insertAgent":
                addAgent(request, response);
                break;

            case "/editAgent":
                showAgentEditForm(request, response);
                break;

            case "/updateAgent":
                updateAgent(request, response);
                break;
            case "/deleteAgent":
                deleteAgent(request, response);
                break;
            case "/AgentToLocation":
                viewLocations(request, response);
                break;
            case "/listAgent":
                viewAgents(request, response);
                break;
            case "/":
                viewLocations(request, response);
                break;
            /*default:
                viewAgents(request, response);
                break;*/
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void addLocation(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "Enter numbers only for Distribution Capacity!";//In case of NumberFormatException.
        try {
            String locationName = request.getParameter("locationName");
            int distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
            int res = registerLocationService.addLocation(locationName, distributionCapacity, registerLocationDao);

            if (res > 0) {
                viewLocations(request, response);
            }
        } catch (NumberFormatException o) {//In case the user wants to try and break the code.
            request.setAttribute("error", message);
            request.getRequestDispatcher("/Location/register.jsp").forward(request, response);//Goes back to register,jsp when there is an error.

        }
    }

    protected void viewLocations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Location> locationList = new ArrayList();
        locationList = registerLocationService.viewLocations(registerLocationDao);

        request.setAttribute("locationList", locationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Location/viewLocationList.jsp");
        dispatcher.forward(request, response);
        //response.sendRedirect("viewLocationList.jsp");
    }

    private void showNewLocationForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Location/register.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Location location = registerLocationService.showLocation(id, registerLocationDao);
            request.setAttribute("location", location);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Location/editLocationForm.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateLocation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String message = "Enter numbers only for Distribution Capacity!";//In case of NumberFormatException.
        int id = Integer.parseInt(request.getParameter("id"));
        String locationName = request.getParameter("locationName");
        try {
            int distributionCapacity = Integer.parseInt(request.getParameter("distributionCapacity"));
            Location locationObj = new Location(id, locationName, distributionCapacity);
            try {
                registerLocationService.updateLocation(locationObj, registerLocationDao);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            viewLocations(request, response);

        } catch (NumberFormatException o) {//In case the user wants to try and break the code.
            request.setAttribute("error", message);
            request.setAttribute("locationName", locationName);
            request.getRequestDispatcher("/Location/editLocationForm.jsp").forward(request, response);//Goes back to register,jsp when there is an error.

        }
    }

    private void deleteLocation(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));

        Location locationObj = new Location(id);
        try {
            registerLocationService.deleteLocation(locationObj, registerLocationDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        viewLocations(request, response);

    }

    protected void addAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("Email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        int res = registerAgentService.addAgent(firstName, lastName, phoneNo, email, userName, password, registerAgentDao);

        if (res > 0) {
            viewAgents(request, response);
        }

    }

    protected void viewAgents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Agent> agentList = new ArrayList();
        agentList = registerAgentService.viewAgents(registerAgentDao);

        request.setAttribute("agentList", agentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Agent/viewAgentList.jsp");
        dispatcher.forward(request, response);
        //response.sendRedirect("viewAgentList.jsp");
    }

    private void showNewAgentForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Agent/register.jsp");
        dispatcher.forward(request, response);
    }

    private void showAgentEditForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int agentId = Integer.parseInt(request.getParameter("id"));
        try {
            Agent agent = registerAgentService.showAgent(agentId, registerAgentDao);
            request.setAttribute("agent", agent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Agent/editAgentForm.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int agentId = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("Email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Agent agentObj = new Agent(agentId, firstName, lastName, phoneNo, email, userName, password);
        try {
            registerAgentService.updateAgent(agentObj, registerAgentDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        viewAgents(request, response);
    }

    private void deleteAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int agentId = Integer.parseInt(request.getParameter("id"));

        Agent agentObj = new Agent(agentId);
        try {
            registerAgentService.deleteAgent(agentObj, registerAgentDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        viewAgents(request, response);
    }
}
