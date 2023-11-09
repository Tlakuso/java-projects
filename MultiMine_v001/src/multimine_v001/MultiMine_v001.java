
package multimine_v001;

import javax.swing.JOptionPane;


public class MultiMine_v001 {

   
    public static void main(String[] args) {
        String userIn_S;
        int userIn_I;
        int threadNum;
        Thread controller;
        MineField mineField;
        userIn_S = JOptionPane.showInputDialog("How amny Threads do you want??");
        userIn_I = Integer.parseInt(userIn_S);
        
        for(threadNum =0; threadNum <userIn_I; threadNum++){
            mineField = new MineField(threadNum);
            controller = new Thread(mineField);
            controller.start();
        }
       
    }
    
}
