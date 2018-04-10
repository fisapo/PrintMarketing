package servlet;

import dao.PrintMarketingDao;
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
    PrintMarketingDao registerDao;
    String jdbcUserName;
    String jdbcPassword;
    String jdbcURL;

    @Override
    public void init() throws ServletException {
        jdbcURL = getServletContext().getInitParameter("jdbcURL");
        jdbcUserName = getServletContext().getInitParameter("jdbcUserName");
        jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

        registerDao = new PrintMarketingDao(jdbcURL, jdbcUserName, jdbcPassword);
        registerLocationService = new RegisterLocationService();
        registerAgentService = new RegisterAgentService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String Action = request.getServletPath() + "Location";//gets action+ word "Location"
        System.out.println(Action);//Used for debugging.
        //String Action = request.getParameter("submit");
        switch (Action) {
            case "/newLocLocation1":
                showNewLocationForm(request, response);
                break;

            case "/insertLocLocation1":
                addLocation(request, response);
                break;

            case "/editLocLocation1":
                showEditForm(request, response);
                break;

            case "/updateLocLocation1":
                updateLocation(request, response);
                break;
            case "/deleteLocation1":
                deleteLocation(request, response);
                break;
            case "/AgentFromLocation1":
                viewAgents(request, response);
                break;
            default:
                viewLocations(request, response);
                break;

        }

        switch (Action) {

            case "/NewAgeLocation":
                showNewAgentForm(request, response);
                break;

            case "/insertAgentLocation":
                addAgent(request, response);
                break;

            case "/editAgeLocation":
                showAgentEditForm(request, response);
                break;

            case "/updateAgeLocation":
                updateAgent(request, response);
                break;
            case "/deleteAgeLocation":
                deleteAgent(request, response);
                break;
            case "/AgentToLocation":
                viewLocations(request, response);
                break;
            default:
                viewAgents(request, response);
                break;
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
            int res = registerLocationService.addLocation(locationName, distributionCapacity, registerDao);

            if (res > 0) {
                response.sendRedirect("listLoc");
            }
        } catch (NumberFormatException o) {//In case the user wants to try and break the code.
            request.setAttribute("error", message);
            request.getRequestDispatcher("/Location/register.jsp").forward(request, response);//Goes back to register,jsp when there is an error.

        }
    }

    protected void viewLocations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Location> locationList = new ArrayList();
        locationList = registerLocationService.viewLocations(registerDao);

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
            Location location = registerLocationService.showLocation(id, registerDao);
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
                registerLocationService.updateLocation(locationObj, registerDao);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            response.sendRedirect("listLocLocation");
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
            registerLocationService.deleteLocation(locationObj, registerDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("listLoc");
    }

    protected void addAgent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        int res = registerAgentService.addAgent(firstName, lastName, phoneNo, email, userName, password, registerDao);

        if (res > 0) {
            response.sendRedirect("listAgent");
        }
    }

    protected void viewAgents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Agent> agentList = new ArrayList();
        agentList = registerAgentService.viewAgents(registerDao);

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
        int agentId = Integer.parseInt(request.getParameter("agentId"));
        try {
            Agent agent = registerAgentService.showAgent(agentId, registerDao);
            request.setAttribute("agent", agent);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/Agent/editAgentForm.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void updateAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int agentId = Integer.parseInt(request.getParameter("agentId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phoneNo = request.getParameter("phoneNo");
        String email = request.getParameter("email");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        Agent agentObj = new Agent(agentId, firstName, lastName, phoneNo, email, userName, password);
        try {
            registerAgentService.updateAgent(agentObj, registerDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("listAgeAgent");
    }

    private void deleteAgent(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int agentId = Integer.parseInt(request.getParameter("agentId"));

        Agent agentObj = new Agent(agentId);
        try {
            registerAgentService.deleteAgent(agentObj, registerDao);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("listAgeAgent");
    }
}
