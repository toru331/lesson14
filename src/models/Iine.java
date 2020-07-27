package models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Table(name="iines")
@NamedQueries({
  @NamedQuery(
    name = "getAllIines", //いいね情報を全て取得
    query = "SELECT e FROM Iine As e ORDER BY e.id DESC"
  ),
  @NamedQuery(
    name = "getIinesCount", //いいね情報の全件数を取得
    query = "SELECT COUNT(e) FROM Iine AS e"
    ),
  @NamedQuery(
          name = "getIineReportCount", //いいね押された数を取得
          query = "SELECT COUNT(i) FROM Iine AS i WHERE i.report = :report"
          ),
  @NamedQuery(
          name = "getMyAllIines", //自分のいいねした日報
          query = "SELECT i FROM Iine AS i WHERE i.employee = :employee ORDER BY i.id DESC"
          ),
  @NamedQuery(
          name = "getMyIinesCount", //自分のいいねした日報の数
          query = "SELECT COUNT(i) FROM Iine AS i WHERE i.report = :report"
          ),
  @NamedQuery(
          name = "checkIine", //指定された社員idと日報idが既にDBに存在しているか調べる
          query = "SELECT COUNT(i) FROM Iine AS i WHERE i.employee = :employee AND  i.report = :report"
          ),
  @NamedQuery(
          name = "getIine",
          query ="SELECT i FROM Iine AS i WHERE i.employee = :employee AND  i.report = :report"
          )
})
@Entity
public class Iine {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @ManyToOne
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;
  @ManyToOne
  @JoinColumn(name = "report_id", nullable = false)
  private Report report;
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Employee getEmployee() {
    return employee;
  }
  public void setEmployee(Employee employee) {
    this.employee = employee;
  }
  public Report getReport() {
    return report;
  }
  public void setReport(Report report) {
    this.report = report;
  }
}