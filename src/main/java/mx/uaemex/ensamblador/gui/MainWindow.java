package mx.uaemex.ensamblador.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import mx.uaemex.ensamblador.Tabla.TablaElementos;
import mx.uaemex.ensamblador.classes.Elementos;
import mx.uaemex.ensamblador.classes.Listado;

public class MainWindow extends JFrame {
    
    public MainWindow()
    {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ensamblador");
        this.setSize(1080, 720);
        this.setMinimumSize(new Dimension(800, 600));
        this.init();
    }
    
    private JTextArea input;
    private JTable tableSemantica;
    private JTable fullInfo;
    private TablaElementos tablaElementos;
    
    private String[][] values2 = {{"1","2"},{"1","3"}};
    private String[][] todo = {{"1","2","3","4","5"}};
    
    private ArrayList<String> content;
    
    private void init()
    {
        this.setLayout(new GridBagLayout());
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        GridBagConstraints gbc = new GridBagConstraints();
        
        JMenu menu = new JMenu("File");
        bar.add(menu);
        
        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				openFile();
			}
        });
        
        JMenu exec = new JMenu("Execution");
        bar.add(exec);
        JMenuItem listarItem = new JMenuItem("List");
        listarItem.setAccelerator(KeyStroke.getKeyStroke('P',Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exec.add(listarItem);
        listarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });

        input = new JTextArea();
        input.setFont(new Font("Consolas",Font.PLAIN,14));
        input.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(input,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        gbc.insets = new Insets(10,10,10,10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        this.add(scrollPane,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        String[] columnNames = {"No.","Elemento","Tipo"};
        //! Aqui se agregan los elementos a la tabla
        this.add(tablaElementos = new TablaElementos(columnNames),gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames_2 = {"Linea","Estado"};
        this.tableSemantica = new JTable(values2,columnNames_2);
        this.add(new JScrollPane(this.tableSemantica),gbc);
        
        String[] columnNamesFull = {"Simbolo","Tipo","Valor","Tamaño","Direccion"};
        fullInfo = new JTable(todo,columnNamesFull);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(this.fullInfo),gbc);
    }
    
    private void openFile()
    {
        input.setText("");
        content = new ArrayList<>();
        JFileChooser filechoose = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.asm", "asm");
        filechoose.setFileFilter(filtro);
        filechoose.showOpenDialog(this);
        File archivo = filechoose.getSelectedFile();
        if(archivo == null) return;
        if (archivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String st;
                while((st = br.readLine()) != null)
                {
                    content.add(st);
                    this.input.setText(this.input.getText() + st + "\n");
                }
                br.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void listar()
    {
        ArrayList<Elementos> aux = Listado.clasificarElementos(Listado.separarElementos(Listado.quitarComentarios(content)));
        tablaElementos.setElementos(aux);
        
    }
    
    
}
