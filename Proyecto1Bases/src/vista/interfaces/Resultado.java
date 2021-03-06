package vista.interfaces;

import java.sql.ResultSet;
import javax.swing.table.*;
import controller.DataModel;

/**
 * @author Kristin
 */
public class Resultado extends javax.swing.JFrame {
    
    AbstractTableModel modelo;
    
    public Resultado(ResultSet res) throws Exception{
        
        this.modelo = new DataModel(res); 
        initComponents();
        
        this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaExpresion = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableResultante = new javax.swing.JTable();
        labelExpresion = new javax.swing.JLabel();
        labelTR = new javax.swing.JLabel();
        ImagenFondo = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        Opciones = new javax.swing.JMenu();
        Regersar = new javax.swing.JMenuItem();
        Acerca_de = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Resultado");
        setMinimumSize(new java.awt.Dimension(407, 374));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        textAreaExpresion.setColumns(20);
        textAreaExpresion.setRows(5);
        jScrollPane1.setViewportView(textAreaExpresion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 63, 370, 110));

        tableResultante.setModel(this.modelo);
        jScrollPane2.setViewportView(tableResultante);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 470, 140));

        labelExpresion.setFont(new java.awt.Font("Vani", 1, 14)); // NOI18N
        labelExpresion.setText("Expresión:");
        getContentPane().add(labelExpresion, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, -1, -1));

        labelTR.setFont(new java.awt.Font("Vani", 1, 14)); // NOI18N
        labelTR.setText("Tabla Resultante:");
        getContentPane().add(labelTR, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, -1, -1));

        ImagenFondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/bitmap1.png"))); // NOI18N
        getContentPane().add(ImagenFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 380));

        Opciones.setText("Opciones");

        Regersar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.ALT_MASK));
        Regersar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/back.png"))); // NOI18N
        Regersar.setText("Regresar");
        Regersar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegersarActionPerformed(evt);
            }
        });
        Opciones.add(Regersar);

        Acerca_de.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        Acerca_de.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vista/imagenes/info.png"))); // NOI18N
        Acerca_de.setText("Acerca de");
        Acerca_de.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Acerca_deActionPerformed(evt);
            }
        });
        Opciones.add(Acerca_de);

        Menu.add(Opciones);

        setJMenuBar(Menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RegersarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegersarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_RegersarActionPerformed

    private void Acerca_deActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Acerca_deActionPerformed
        // TODO add your handling code here:
        Acerca_de AC = new Acerca_de();
        AC.setVisible(true);
    }//GEN-LAST:event_Acerca_deActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Acerca_de;
    private javax.swing.JLabel ImagenFondo;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu Opciones;
    private javax.swing.JMenuItem Regersar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelExpresion;
    private javax.swing.JLabel labelTR;
    private javax.swing.JTable tableResultante;
    private javax.swing.JTextArea textAreaExpresion;
    // End of variables declaration//GEN-END:variables
}
