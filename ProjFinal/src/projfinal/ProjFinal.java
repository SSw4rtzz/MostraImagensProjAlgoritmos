/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projfinal;

import java.io.IOException;

/**
 *
 * @author sw4rtz
 */
public class ProjFinal {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
        
    }
    
}
