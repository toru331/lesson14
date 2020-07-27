package controllers.iine;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Iine;
import models.Report;
import utils.DBUtil;
/**
 * Servlet implementation class IinesDestroyServlet
 */
@WebServlet("/iines/destroy")
public class IinesDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IinesDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    EntityManager em = DBUtil.createEntityManager();

	    //日報のIDをリクエストパラメータから取得
	    Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));


	    List<Iine> iines = em.createNamedQuery("getIine", Iine.class)
                .setParameter("report", r)
                .setParameter("employee", (Employee)request.getSession().getAttribute("login_employee"))
                .getResultList();

	    if(iines.size() == 1) {
	        //いいねテーブル削除
	        em.getTransaction().begin();
	        em.remove(iines.get(0));
	        em.getTransaction().commit();
	        request.getSession().setAttribute("flush", "いいねの削除が完了しました。");
	    } else {
	        request.getSession().setAttribute("flush", "いいねの削除が失敗しました");
	    }
	    em.close();

	    //セッションスコープ上の不要になったデータを削除
	    request.getSession().removeAttribute("id");

	    //show.jspへリダイレクト
	    response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("id"));

	}

}
