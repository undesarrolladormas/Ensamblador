package mx.uaemex.ensamblador.Tabla;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;

import mx.uaemex.ensamblador.classes.Elementos;

public class TablaElementos extends JPanel{

    private JTable tabla;
    private JComboBox<Integer> noPaginas;
    private DefaultTableModel modelo;
    private List<Elementos> elementos;
    private int pagina;
    private int noElementos;


    public void setElementos(List<Elementos> elementos) {
        this.elementos = elementos;
        this.pagina = 0;
        this.changePage(0);
    }

    public TablaElementos(String[] headers) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        tabla = new JTable(modelo = new DefaultTableModel(null, headers)){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.noElementos = 10;

        JButton btnAnterior = new JButton("Anterior");
        btnAnterior.addActionListener(e -> changePage(-1));
        JButton btnSiguiente = new JButton("Siguiente");
        btnSiguiente.addActionListener(e -> changePage(1));


        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        super.add(new JScrollPane(tabla), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        super.add(btnAnterior, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        super.add(btnSiguiente, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        noPaginas = new JComboBox<Integer>(new Integer[] {10, 15, 20, 40, 50});
        noPaginas.addActionListener(e -> {
            this.noElementos = (int) noPaginas.getSelectedItem();
            this.changePage(0);
        });
        super.add(noPaginas, gbc);

    }

    private void changePage(int pa) {
        if(this.elementos == null) return;
        if(this.elementos.size() == 0 ) return;
        int lastpage = (int) Math.ceil(this.elementos.size()/this.noElementos);
        if(this.pagina + pa < 0 || this.pagina + pa > lastpage) return;
        this.pagina += pa;
        this.modelo.setRowCount(0);

        if(this.pagina == lastpage) {
            for(int i = this.pagina * this.noElementos; i < this.elementos.size(); i++) {
                Object[] row = {i + 1, this.elementos.get(i).getNombre(), this.elementos.get(i).getTipo()};
                this.modelo.addRow(row);
            }
        } else {
            for(int i = this.pagina * this.noElementos; i < (this.pagina + 1) * this.noElementos; i++) {
                Object[] row = {i + 1, this.elementos.get(i).getNombre(), this.elementos.get(i).getTipo()};
                this.modelo.addRow(row);
            }
        }
        tabla.setModel(modelo);

    }


}
