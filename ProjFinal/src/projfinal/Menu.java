/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projfinal;

import java.io.IOException;

/**
 *
 * @author sw4rtz
 */
public class Menu extends javax.swing.JFrame {

        /**
         * Creates new form Menu
         */
        public Menu() {
                initComponents();
        }

        /**
         * This method is called from within the constructor to initialize the form.
         * WARNING: Do NOT modify this code. The content of this method is always
         * regenerated by the Form Editor.
         */
        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                btnSearch = new javax.swing.JButton();
                txtLink = new javax.swing.JTextField();
                flagDominios = new javax.swing.JCheckBox();
                dropDown = new javax.swing.JComboBox<>();
                tableImages = new javax.swing.JScrollPane();
                jTable1 = new javax.swing.JTable();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Projeto");
                setBackground(new java.awt.Color(255, 255, 255));

                btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Assets/pesquisa.png"))); // NOI18N
                btnSearch.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
                btnSearch.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                try {
                                        btnSearchActionPerformed(evt);
                                } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                }
                        }
                });

                txtLink.setText("Link");

                flagDominios.setText("Dominios");

                dropDown.setMaximumRowCount(3);
                dropDown.setModel(new javax.swing.DefaultComboBoxModel<>(
                                new String[] { "Nivel 1", "Nivel 2", "Nivel 3" }));

                jTable1.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Title 1", "Title 2", "Title 3", "Title 4"
                                }));
                tableImages.setViewportView(jTable1);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(tableImages,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                0,
                                                                                                Short.MAX_VALUE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(flagDominios)
                                                                                                                                .addGap(63, 63, 63)
                                                                                                                                .addComponent(dropDown,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                                .addComponent(txtLink,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                360,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(btnSearch,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                68,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING,
                                                                                false)
                                                                                .addComponent(btnSearch)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(txtLink,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                29,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                .addComponent(flagDominios)
                                                                                                                .addComponent(dropDown,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(tableImages,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                281,
                                                                                Short.MAX_VALUE)));

                pack();
        }// </editor-fold>//GEN-END:initComponents

        private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) throws IOException {// GEN-FIRST:event_btnSearchActionPerformed
                String link = txtLink.getText();
                boolean teste = false;
                //Coloca https:// no inicio do link caso o utilizador não o coloque
                teste = link.contains("http://");
                if(teste == false){
                    teste = link.contains("https://");
                    if(teste == false){
                        link = "https://" + link;
                    }
                }
                
                
                EDACrawler eda = new EDACrawler();
                Payload ini = eda.process(link);
                System.out.println(ini.links);
                System.out.println(ini.imgs);
        }// GEN-LAST:event_btnSearchActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnSearch;
        private javax.swing.JComboBox<String> dropDown;
        private javax.swing.JCheckBox flagDominios;
        private javax.swing.JTable jTable1;
        private javax.swing.JScrollPane tableImages;
        private javax.swing.JTextField txtLink;
        // End of variables declaration//GEN-END:variables
}