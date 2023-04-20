package mx.uaemex.ensamblador;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mx.uaemex.ensamblador.gui.MainWindow;

public class App 
{
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            
        }
        MainWindow mw = new MainWindow();
        mw.setVisible(true);
    }
}
