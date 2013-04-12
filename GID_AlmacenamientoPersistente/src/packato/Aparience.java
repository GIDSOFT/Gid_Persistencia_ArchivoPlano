/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packato;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author Administrador
 */
public class Aparience {
    public String pathLF= "OkyDarkTheme.theme"; //ruta Look and Feel

    public static void main(String[] args) {
        BasicConfigurator.configure();
        try {
            UIManager.setLookAndFeel(new NimRODLookAndFeel());
            NimRODLookAndFeel nf = new NimRODLookAndFeel();
            Aparience apa = new Aparience();
            String folder = System.getProperty("user.dir");
            String separator = System.getProperty("file.separator");
            NimRODTheme nt = new NimRODTheme(folder+ separator + apa.pathLF);
            nf.setCurrentTheme(nt);
            UIManager.setLookAndFeel(nf);
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error de " + ex);
        }

        Usuarios user = new Usuarios();
        user.setVisible(true);


    }
}
