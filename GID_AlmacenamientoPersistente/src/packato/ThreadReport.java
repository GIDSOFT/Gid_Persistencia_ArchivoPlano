/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packato;

import java.io.File;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class ThreadReport  extends Thread{
     
    public JRCsvDataSource getDataSource() throws JRException, URISyntaxException {
        String[] columnNames = new String[]{"Tipo de identificación", "Identificación", "Nombre", "Apellidos", "Fecha de nacimiento", "Edad", "Sexo", "Ciudad", "Eps"};
        File fi = null;
        String separatorFile = System.getProperty("file.separator");
        fi = new File(System.getProperty("user.dir").concat(separatorFile).concat("file.csv"));
        String filePath = fi.getAbsolutePath().toString();
        JRCsvDataSource ds = new JRCsvDataSource(filePath);
        ds.setRecordDelimiter("\r\n");
        ds.setFieldDelimiter(';');
        ds.setColumnNames(columnNames);
        return ds;
    }
    
    public  void report() throws JRException {
        File f = null;
        try {
            String separator = System.getProperty("file.separator");
            f = new File(System.getProperty("user.dir").concat(separator).concat("CsvReport.jasper"));
            String filePath = f.getAbsolutePath().toString();
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(filePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, getDataSource());
            JasperViewer view = new JasperViewer(jasperPrint, false);
            view.setTitle("Reporte Informativo");
            view.setVisible(true);
            
        } catch (Exception exec) {
            System.out.println("" + exec);
        }
    }
    
    @Override
    public void run(){
        try {
            report();
        } catch (JRException ex) {
            Logger.getLogger(ThreadReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
