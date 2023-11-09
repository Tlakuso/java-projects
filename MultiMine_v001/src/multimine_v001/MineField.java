
package multimine_v001;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


class MineField extends JFrame implements Runnable,ActionListener{
    int threadNum;
    JButton btnArr[][] = new JButton[10][10];
    int intArr[][] = new int[btnArr.length][btnArr[0].length];


    MineField(int threadNum) {
        this.threadNum = threadNum;
        
        
        //setup the JFrame
        setVisible(true);
        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("MineField on Thread "+threadNum);
        setLocation(threadNum*200,50);
        
        //create the buttons 
        setLayout(new GridLayout(btnArr.length,btnArr[0].length));
        for(int col =0; col<btnArr.length;col++){
            for(int row =0; row<btnArr[0].length;row++){
                btnArr[col][row] = new JButton();
                this.add(btnArr[col][row]);
                btnArr[col][row].setText(" ");
                btnArr[col][row].setBackground(Color.WHITE);
                btnArr[col][row].setForeground(Color.RED);
                btnArr[col][row].addActionListener(this);
            }
        }
    }

    @Override
    public void run() {
        System.out.println(threadNum);
        Random rand = new Random();
        int col,row,maxBomb,countBomb, numBombs,sumCol,sumRow;
        //flll the array with zeroes
        for( col =0; col<intArr.length;col++){
            for( row =0; row<intArr[0].length;row++){
                intArr[col][row] = 0;
            }
        }
        
        //fill the array with bombs
        maxBomb = (intArr.length * intArr[0].length)/5;
        countBomb = 0;
        while(countBomb < maxBomb){
            col = rand.nextInt(intArr.length);
            row = rand.nextInt(intArr[0].length);
            if(intArr[col][row] != 10){
                intArr[col][row] = 10;
                countBomb++;
            }
        }
        //count the surrounding bombs and include the edges
         for( col = 1; col<intArr.length - 1;col++){
            for( row = 1; row<intArr[0].length -1;row++){
                if(intArr[col][row] != 10){
                   numBombs = 0;
                  
                   for( int innerCol = -1; innerCol < 2; innerCol++){
                       for(int innerRow = -1; innerRow < 2; innerRow++){
                            sumCol = col + innerCol;
                            sumRow = row + innerRow;
                            if((sumCol >= 0 )&& (sumCol < intArr.length)&&(sumRow >= 0)&&(sumRow <intArr[0].length)){
                                if(intArr[sumCol][sumRow] == 10)numBombs++;
                            }
                       }   
                   }
                   intArr[col][row] = numBombs++;                    
                }                
            }
         }
        
        for( col =0; col<intArr.length;col++){
            for( row =0; row<intArr[0].length;row++){
                System.out.print(" "+intArr[col][row]);
            }
            System.out.println();

        }
        
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        
        for(int col =0; col<btnArr.length;col++){
            for(int row =0; row<btnArr[0].length;row++){
                if(event.getSource() == btnArr[col][row]){
                    pressButton(col,row);                    
                    break;
                }
            }
        }
       
    }

    private void pressButton(int col, int row) {
        System.out.println("pressedButton  "+col+" , "+row);
        String toScreen;
        int sumCol,sumRow;

        toScreen = Integer.toString(intArr[col][row]);
        btnArr[col][row].setText(toScreen);
        
        //show multiple zeroes with one click
        if(intArr[col][row] == 0){
            
            for(int innerCol =-1 ; innerCol <2; innerCol++){
                for(int innerRow = -1; innerRow<2; innerRow++){
                    sumCol = col+innerCol;
                    sumRow = row +innerRow;
                    if((sumCol >= 0 )&& (sumCol < intArr.length)&&(sumRow >= 0)&&(sumRow <intArr[0].length)){
                        if((innerCol != 0) || (innerRow != 0)){
                            if(!btnArr[sumCol][sumRow].getText().endsWith("0"))pressButton(sumCol,sumRow);
                             
                        }
                    }
                }
            }
        }
        System.out.println("Thread "+threadNum+ " button ["+col+"] ["+row+"}");
                if(intArr[col][row] == 10){
                    System.out.println("Game Over!!");
                    JOptionPane.showMessageDialog(this,"Game Over!!");
                    System.exit(0);
                    }
    }
        
    
    
}
