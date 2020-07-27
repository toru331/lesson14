package controllers.iine;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Iine;
import utils.DBUtil;

/**
 * Servlet implementation class IinesIndexServlet
 */
@WebServlet("/iine/index")
public class IinesIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IinesIndexServlet() {
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



	    int page;
	    try {
	        page = Integer.parseInt(request.getParameter("page"));
	    } catch(Exception e) {
	        page = 1;
	    }

	    List<Iine> getMyAllIines = em.createNamedQuery("getMyAllIines", Iine.class)
	            .setParameter("employee", (Employee)request.getSession().getAttribute("login_employee"))
	            .getResultList();


	    em.close();

	    request.setAttribute("getMyAllIines", getMyAllIines);
	    request.setAttribute("page", page);
	    RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/iine/index.jsp");
	    rd.forward(request, response);

	    if(request.getSession().getAttribute("flush") != null) {
	        request.setAttribute("flush", request.getSession().getAttribute("flush"));
	        request.getSession().removeAttribute("flush");
	    }



	}

}
