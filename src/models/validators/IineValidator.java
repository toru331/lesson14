//package models.validators;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//
//import models.Employee;
//import models.Iine;
//import models.Report;
//import utils.DBUtil;
//
//public class IineValidator {
//    public static List<String> validate(Iine i) {
//        List<String> errors = new ArrayList<String>();
//
//        String iine_error = _validateIine(i.getEmployee(), i.getReport());
//        if(!iine_error.equals("")){
//            errors.add(iine_error);
//        }
//
//        return errors;
//    }
//
//    //いいねに対するバリデーション
//    private static String _validateIine(Employee employee, Report report) {
//        EntityManager em = DBUtil.createEntityManager();
//        long iine_count = (long)em.createNamedQuery("checkIine", Long.class)
//                .setParameter("employee_id", employee)
//                .setParameter("report_id", report)
//                .getSingleResult();
//
//        em.close();
//
//        //いいねが既に押されている場合
//        if(iine_count > 0) {
//            return "いいねの削除が完了しました";
//
//        }
//
//        return "";
//    }
//
//}
