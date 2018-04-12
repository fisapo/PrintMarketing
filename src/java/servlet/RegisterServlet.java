package servlet;

import dao.PrintMarketingDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;
import services.RegisterClientService;

public class RegisterServlet extends HttpServlet {

    RegisterClientService registerClientService;
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
        registerClientService = new RegisterClientService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/new":
                showNewClientForm(request, response);
                break;

            case "/insert":
                addClient(request, response);
                break;

            default:
                viewClients(request, response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void addClient(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int streetNumber = Integer.parseInt(request.getParameter("streetNumber"));
        String streetName = request.getParameter("streetName");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String postalCode = request.getParameter("postalCode");
        String telOffice = request.getParameter("telOffice");
        String telCell = request.getParameter("telCell");
        String email = request.getParameter("email");
        String company = request.getParameter("company");
        String companyType = request.getParameter("companyType");

        int res = registerClientService.addClient(userId, firstName, lastName, streetNumber, streetName, city, province, postalCode, telOffice, telCell, email, company, companyType, registerDao);

        if (res > 0) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("list");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    protected void viewClients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Client> clientList = new ArrayList();
        clientList = registerClientService.viewClients(registerDao);

        request.setAttribute("clientList", clientList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("viewClientList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewClientForm(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
//        ArrayList<Client> clientList = new ArrayList();
//        clientList = registerClientService.viewClients(registerDao);
//
//        request.setAttribute("clientList", clientList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
        dispatcher.forward(request, response);
    }
}
