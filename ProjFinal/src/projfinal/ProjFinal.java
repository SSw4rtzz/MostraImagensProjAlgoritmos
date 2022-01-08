/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projfinal;

import java.io.IOException;
import javax.swing.JFrame;

public class ProjFinal extends JFrame {
    public static void main(String[] args) throws IOException {
          java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
          });
    }
}