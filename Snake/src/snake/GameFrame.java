/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JFrame;


public class GameFrame extends JFrame {
    
    //constructor
    GameFrame(){
        //combine the game frame and game panel
        this.add(new GamePanel());
        this.setTitle("Snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();//pack the the frame
        this.setVisible(true);
        this.setLocationRelativeTo(null);//to make the frame appear in the middle
        
        
    }
    
}
