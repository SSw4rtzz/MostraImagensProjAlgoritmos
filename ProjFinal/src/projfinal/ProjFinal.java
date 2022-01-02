/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projfinal;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ProjFinal extends JFrame{
    public ProjFinal() throws IOException{
        
        Image image = null;
        URL url = new URL("https://w.wallhaven.cc/full/pk/wallhaven-pkgkkp.png");
        image = ImageIO.read(url);
        BufferedImage img = ImageIO.read(new File("/Users/sw4rtz/Desktop/facil.jpg"));
        String [] columns = {"Id", "Link", "Image"};    
        Object[][] data = {
            {1, "www.google.com", new ImageIcon(img)},
            {2, "www.google.com", new ImageIcon("/Users/sw4rtz/Desktop/facil.jpg")},
            {3, "www.google.com", new ImageIcon(image)}
        };
         

        
        //Object[] row = { data1, data2};
        
    //DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    
    DefaultTableModel model = new DefaultTableModel(data, columns);
    
    JTable table = new JTable(model){
        public Class getColumnClass(int column) {
            return(column == 2) ? Icon.class : Object.class;
        }
    };
    
    table .setRowHeight(70);
    
    JScrollPane scrollPane = new JScrollPane(table);
    getContentPane().add(scrollPane);

    
    }
    
    public static void main(String[] args) throws IOException {
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });*/
        
        //URL url = new URL("https://w.wallhaven.cc/full/pk/wallhaven-pkgkkp.png");
        //Image image = ImageIO.read(url);
        
        ProjFinal frame = new ProjFinal();
        //JLabel label = new JLabel(new ImageIcon(image));
        //frame.getContentPane().add(label, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(700, 350);
        frame.setVisible(true);
    }
           
}