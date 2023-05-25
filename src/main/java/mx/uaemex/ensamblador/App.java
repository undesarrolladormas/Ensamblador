package mx.uaemex.ensamblador;

import javax.swing.UIManager;
import java.awt.EventQueue;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import mx.uaemex.ensamblador.gui.MainWindow;

public class App 
{
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new FlatMacLightLaf());
                } catch (Exception ex) {
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                MainWindow mw = new MainWindow();
                mw.setVisible(true);
            }
        });
    }
}
