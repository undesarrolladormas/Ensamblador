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
import javax.swing.table.DefaultTableModel;

import mx.uaemex.ensamblador.gui.tabla.TablaElementos;
import mx.uaemex.ensamblador.gui.tabla.TablaRenglones;
import mx.uaemex.ensamblador.classes.ClasificadorElementos;
import mx.uaemex.ensamblador.classes.ClasificadorSegmentos;
import mx.uaemex.ensamblador.classes.LimpiadorCodigo;
import mx.uaemex.ensamblador.classes.Semantica;

public class MainWindow extends JFrame {

    public MainWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Ensamblador");
        this.setSize(1080, 720);
        this.setMinimumSize(new Dimension(800, 600));
        this.init();
    }

    private JTextArea input;
    private JTable simbolos;
    private DefaultTableModel modeloSimbolos;
    private TablaElementos tablaElementos;
    private TablaRenglones tablaRenglones;

    private final String instan = "[0-9]+";
    private final String hex = "(0(\\d|[a-fA-F])+(h|H))";
    private final String bin = "(0|1)+(B|b)";
    String Cons = "";
    private final String ACCM = "(BX|SI|DI)|"
            + "(BX\\s*\\+\\s*SI|BX\\s*\\+\\s*DI)"
            + "|" + "SI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
            + "|" + "DI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
            + "|" + "BP\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
            + "|" + "BX\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
            + "|" + "SI\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")"
            + "|" + "BX\\s*\\+\\s*(SI|DI)\\s*\\+\\s*" + "(" + instan + "|" + hex + "|" + bin + "|" + Cons + ")";

    private ArrayList<String> content;

    private void init() {
        this.setLayout(new GridBagLayout());
        JMenuBar bar = new JMenuBar();
        this.setJMenuBar(bar);
        GridBagConstraints gbc = new GridBagConstraints();

        JMenu menu = new JMenu("File");
        bar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        menu.add(openItem);
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        JMenu exec = new JMenu("Execution");
        bar.add(exec);
        JMenuItem listarItem = new JMenuItem("List");
        listarItem.setAccelerator(KeyStroke.getKeyStroke('P', Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        exec.add(listarItem);
        listarItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listar();
            }
        });

        input = new JTextArea();
        input.setFont(new Font("Consolas", Font.PLAIN, 14));
        input.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(input, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 2;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        this.add(scrollPane, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;

        String[] columnNames = { "No.", "Elemento", "Tipo" };
        this.add(tablaElementos = new TablaElementos(columnNames), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        String[] columnNames_2 = { "No", "Linea", "Estado" };
        this.add(tablaRenglones = new TablaRenglones(columnNames_2), gbc);

        String[] columnNamesFull = { "Simbolo", "Tamaño", "Tipo", "Valor", "Direccion" };
        simbolos = new JTable(modeloSimbolos = new DefaultTableModel(null, columnNamesFull));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        this.add(new JScrollPane(this.simbolos), gbc);
    }

    private void openFile() {
        input.setText("");
        content = new ArrayList<>();
        JFileChooser filechoose = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.asm", "asm");
        filechoose.setFileFilter(filtro);
        filechoose.showOpenDialog(this);
        File archivo = filechoose.getSelectedFile();
        if (archivo == null)
            return;
        if (archivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String st;
                while ((st = br.readLine()) != null) {
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

    private void listar() {
        ArrayList<String> Simb = new ArrayList<>();
        ArrayList<String> Etiq = new ArrayList<>();
        String Cons = ""; // String para almacenar las constantes del data segment

        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> tipos = new ArrayList<>();
        if (this.content == null || this.content.size() < 1)
            return;
        var listado = new LimpiadorCodigo();
        var texto = listado.filtrarArchivo(this.content);

        var segmentos = new ClasificadorSegmentos();
        var elementos = new ClasificadorElementos();

        var DS = segmentos.DS(texto);
        for (String a : elementos.sepElementos(DS)) {
            tokens.add(a);
            tipos.add(elementos.clasElementoDS(a)); // Insertar en la tabla de Clasifica los elementos del Data Segment
        }

        var SS = segmentos.SS(texto);
        for (String a : elementos.sepElementos(SS)) {
            tokens.add(a);
            tipos.add(elementos.clasElementoSS(a)); // Insertar en la tabla de Clasifica los elementos del Stack Segment
        }

        var CS = segmentos.CS(texto);
        for (String a : elementos.sepElementos(CS)) {
            tokens.add(a);
            var aux = elementos.clasElementoCS(a, null);
            tipos.add(aux);
            if (aux.equalsIgnoreCase("Etiqueta")) {
                Etiq.add(a.substring(0, a.length() - 1));
            }
            // Insertar en la tabla de Clasifica los elementos del Code Segment
        }

        // Actualiza la tabla de elementos
        tablaElementos.setElementos(tokens, tipos);

        // * Analisis semantico
        Semantica se = new Semantica();
        ArrayList<Object[]> semantica = new ArrayList<>();

        // ? Mejorar la generacion de data segment
        this.modeloSimbolos.setRowCount(0);
        int contadorMem = 0; // Se inicializa el contador de la memoria al comenzar el Segmento de datos
        // int contG = 0;//Contador general
        // String contH = "0000";//Contador hexadecimal
        for (var a : DS) {
            var aux = se.semanticaDS(a);
            semantica.add(new Object[] { a, aux }); // Insertar en la tabla de Semantica los valores correctos e
                                                    // incorrectos del Data Segment
            // ! Aqui va la generacion de la tabla de simbolos
            // ! Tambien con esa tabla se recupera el identificador de las variables y
            // constantes
            if (aux.equalsIgnoreCase("Correcto")) {
                String[] vals = a.split(" ");
                if (vals.length > 2) {
                    if (!vals[1].equalsIgnoreCase("equ")) {
                        Simb.add(vals[0]);
                    } else {
                        Cons += vals[0] + "|";
                    }
                }

                if (vals.length == 3) { // Detecta variables sin dup o dupy
                    if (vals[1].equalsIgnoreCase("db")) {
                        modeloSimbolos.addRow(new Object[] { vals[0], vals[1], "Variable", vals[2],
                                String.format("%04x", contadorMem) }); // Insertar en la tabla de Símbolos
                        contadorMem += se.contarBytesDS(vals[1], vals[2]); // Contar los bytes de la variable correcta
                    } else if (vals[1].equalsIgnoreCase("dw")) {
                        modeloSimbolos.addRow(new Object[] { vals[0], vals[1], "Variable", vals[2],
                                String.format("%04x", contadorMem) }); // Insertar en la tabla de Símbolos
                        contadorMem += se.contarBytesDS(vals[1], vals[2]); // Contar los bytes de la variable correcta
                    } else if (vals[1].equalsIgnoreCase("equ")) {
                        modeloSimbolos.addRow(new Object[] { vals[0], vals[1], "Constante", vals[2], "N/A" }); // Insertar
                                                                                                               // en la
                                                                                                               // tabla
                                                                                                               // de
                                                                                                               // Símbolos
                    }
                } else if (vals.length > 3) { // Detecta variables con dup o dupy
                    if (vals.length == 5) {
                        modeloSimbolos.addRow(new Object[] { vals[0], vals[1], "Variable", vals[3] + " " + vals[4],
                                String.format("%04x", contadorMem) }); // Insertar en la tabla de Símbolos
                        contadorMem += se.contarBytesDSE(vals[1], vals[2]); // Contar los bytes de la variable correcta
                    } else if (vals[1].equalsIgnoreCase("db") || vals[1].equalsIgnoreCase("dw")) {
                        modeloSimbolos.addRow(new Object[] { vals[0], vals[1], "Variable", vals[3],
                                String.format("%04x", contadorMem) }); // Insertar en la tabla de Símbolos
                        contadorMem += se.contarBytesDSE(vals[1], vals[2]); // Contar los bytes de la variable correcta
                    }
                }
            }

        }
        simbolos.setModel(modeloSimbolos);

        // ? Mejorar la generacion de stack segment
        for (var a : SS) {
            var aux = se.semanticaSG(a);
            semantica.add(new Object[] { a, aux }); // Insertar en la tabla de Semantica los valores correctos e
                                                    // incorrectos del Stack Segment
        }

        // ? Mejorar la generacion de code segment
        for (var a : CS) {
            var aux = se.semanticaCS(a, ACCM, Simb, Etiq, Cons);
            semantica.add(new Object[] { a, aux }); // Insertar en la tabla de Semantica los valores correctos e
                                                    // incorrectos del Code Segment
        }

        tablaRenglones.setElementos(semantica);
    }

}
