package controllers.reports;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class ReportsShowServlet
 */
@WebServlet("/reports/show")
public class ReportsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	    EntityManager em = DBUtil.createEntityManager();

	    Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));
	    //Report r = em.find(Report.class, (Integer)(request.getSession().getAttribute("id")));

	    long getIineReportCount = (long)em.createNamedQuery("getIineReportCount", Long.class)
	            .setParameter("report", r)
	            .getSingleResult();


	    long iineCount = em.createNamedQuery("checkIine", Long.class)
	            .setParameter("report", r)
	            .setParameter("employee", (Employee)request.getSession().getAttribute("login_employee"))
	            .getSingleResult();

	    em.close();

	    request.setAttribute("report", r);
	    request.setAttribute("_token", request.getSession().getId());
	    request.setAttribute("iineCount", iineCount);
	    request.setAttribute("getIineReportCount", getIineReportCount);

	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/reports/show.jsp");
	    rd.forward(request, response);
	}

}
