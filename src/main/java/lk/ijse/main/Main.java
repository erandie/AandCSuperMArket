//package main;
//
//import db.DbConnection;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//
//import java.sql.SQLException;
//
//public class Main {
//    public static void main(String[] args) {
//        try{
//            JasperDesign design = JRXmlLoader.load("/home/erandi/desctop/src/x/superMarket-3.0/src/main/resources/reports/Testt_A4.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(design);
//            JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());
//        } catch (JRException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
