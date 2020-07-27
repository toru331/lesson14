package controllers.iine;
import java.io.IOException;

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
 * Servlet implementation class IineCreateServlet
 */
@WebServlet("/iine")
public class IineCreateServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  /**
   * @see HttpServlet#HttpServlet()
   */
  public IineCreateServlet() {
    super();
    // TODO Auto-generated constructor stub
  }
  /**
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    //String _token = (String)request.getParameter("_token");
    //if(_token != null && _token.equals(request.getSession().getId())) {
      EntityManager em = DBUtil.createEntityManager();


      //日報のIDをリクエストパラメータから取得
      Report r = em.find(Report.class, Integer.parseInt(request.getParameter("id")));

      //いいねインスタンス取得
      Iine i = new Iine();
      //いいねインスタンスへセット
      i.setReport(r);
      //セッションよりログインしている従業員（Employee）のインスタンスを取得
      i.setEmployee((Employee)request.getSession().getAttribute("login_employee"));

      //いいねテーブルへ保存
      em.getTransaction().begin();
      em.persist(i);
      em.getTransaction().commit();
      request.getSession().setAttribute("flush", "いいねが押されました");
      em.close();

      //show.jspへリダイレクト
      response.sendRedirect(request.getContextPath() + "/reports/show?id=" + request.getParameter("id"));







  }
}
