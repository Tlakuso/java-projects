
package tictactoe_v003;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TheGame implements ActionListener {
    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JPanel buttonControl_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    JButton reset_button = new JButton("RESET");
    boolean player1_turn;
    JPanel score_panel = new JPanel();
    JLabel scoreLabelX = new JLabel();
    JLabel scoreLabelO = new JLabel();
    int scoreX = 0;
    int scoreO = 0;
    JButton exit_button = new JButton("EXIT");

    TheGame(){
         //JFRAME
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setTitle("Tic Tac Toe");
        frame.getContentPane().setBackground(Color.cyan);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        
        //TEXTFIELD 
        textField.setBackground(Color.PINK);
        textField.setForeground(Color.WHITE);
        textField.setFont(new Font("Ink Free",Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);
        title_panel.setLayout(new FlowLayout());
        title_panel.setBounds(0,0,800,100);
        title_panel.add(textField);
        mainPanel.add(title_panel);

        
        //SCORES
        scoreLabelX.setText("Player X: 0");
        scoreLabelO.setText("Player O: 0");
        score_panel.setLayout(new BorderLayout());
        scoreLabelX.setFont(new Font("MV Boli",Font.BOLD,50));
        scoreLabelO.setFont(new Font("MV Boli",Font.BOLD,50));
        score_panel.add(scoreLabelX,BorderLayout.EAST);
        score_panel.add(scoreLabelO,BorderLayout.WEST);
        mainPanel.add(score_panel);
        
        //BUTTONS
        for(int i=0; i<9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setBackground(Color.WHITE);
            buttons[i].setFont(new Font("MV Boli",Font.BOLD,120));
            buttons[i].setPreferredSize(new Dimension(200, 200));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this); 
        }
        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(Color.black);
        mainPanel.add(button_panel);
        
        //RESET AND EXIT BUTTON
        reset_button.setPreferredSize(new Dimension(200, 75));
        exit_button.setPreferredSize(new Dimension(200, 75));
        reset_button.setFont(new Font("MV Boli",Font.BOLD,30));
        exit_button.setFont(new Font("MV Boli",Font.BOLD,30));
        reset_button.setBackground(Color.GREEN); // Set the background color
        exit_button.setBackground(Color.RED); 
        buttonControl_panel.setLayout(new FlowLayout());
        buttonControl_panel.setBackground(Color.WHITE);
        reset_button.addActionListener(this);
        exit_button.addActionListener(this);
        buttonControl_panel.add(reset_button);
        buttonControl_panel.add(exit_button);
        //adding the components into the JFrame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(buttonControl_panel, BorderLayout.SOUTH);


        
        firstTurn();
    
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == exit_button){
            System.exit(0);
        }
        if(e.getSource() == reset_button){
            //reset the game
            for(int i=0; i<9; i++){
                buttons[i].setText("");
                buttons[i].setEnabled(true);
                buttons[i].setBackground(Color.WHITE);
            }
            textField.setText("Tic-Tac-Toe");
            firstTurn();
         
        }else{
            for( int i =0; i<9; i++){
            if(e.getSource()==buttons[i] && buttons[i].getText().equals("")){
                if(player1_turn){
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                    }
                }else{
                    if(buttons[i].getText()==""){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
        }
        
         
    }
    
     //determines who's turn is it to play
    public void firstTurn(){
        
        if(random.nextInt(2) ==0){
            player1_turn = true;
            textField.setText("X turn");
            
        }else{
            player1_turn = false;
            textField.setText("O turn");
        }
        
       
        
    }
     //track to wins
    public void check(){
        //check the wim conditions
        if(
                (buttons[0].getText()=="X") &&
                (buttons[1].getText()=="X") &&
                (buttons[2].getText()=="X")){
            xWins(0,1,2);
        }
        
         if(
                (buttons[3].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[5].getText()=="X")){
            xWins(3,4,5);
        }
         
          if(
                (buttons[6].getText()=="X") &&
                (buttons[7].getText()=="X") &&
                (buttons[8].getText()=="X")){
            xWins(6,7,8);
        }
           if(
                (buttons[0].getText()=="X") &&
                (buttons[3].getText()=="X") &&
                (buttons[6].getText()=="X")){
            xWins(0,3,6);
        }
            if(
                (buttons[1].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[7].getText()=="X")){
            xWins(1,4,7);
        }
             if(
                (buttons[2].getText()=="X") &&
                (buttons[5].getText()=="X") &&
                (buttons[8].getText()=="X")){
            xWins(2,5,8);
        }
              if(
                (buttons[0].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[8].getText()=="X")){
            xWins(0,4,8);
        }
               if(
                (buttons[2].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[6].getText()=="X")){
            xWins(2,4,6);
        }
               
        //check if O is winning
        if(
                (buttons[0].getText()=="O") &&
                (buttons[1].getText()=="O") &&
                (buttons[2].getText()=="O")){
            oWins(0,1,2);
        }
        
         if(
                (buttons[3].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[5].getText()=="O")){
            oWins(3,4,5);
        }
         
          if(
                (buttons[6].getText()=="O") &&
                (buttons[7].getText()=="O") &&
                (buttons[8].getText()=="O")){
            oWins(6,7,8);
        }
           if(
                (buttons[0].getText()=="O") &&
                (buttons[3].getText()=="O") &&
                (buttons[6].getText()=="O")){
            oWins(0,3,6);
        }
            if(
                (buttons[1].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[7].getText()=="O")){
            oWins(1,4,7);
        }
             if(
                (buttons[2].getText()=="O") &&
                (buttons[5].getText()=="O") &&
                (buttons[8].getText()=="O")){
            oWins(2,5,8);
        }
              if(
                (buttons[0].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[8].getText()=="O")){
            oWins(0,4,8);
        }
               if(
                (buttons[2].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[6].getText()=="O")){
            oWins(2,4,6);
        }
               
               //update scores if the there is a winner
               if(isWinner("X")){
                   scoreX++;
                   scoreLabelX.setText("Player X: "+scoreX);
               }else if (isWinner("O")){
                   scoreO++;
                   scoreLabelO.setText("Player O: "+scoreO);
               }
    }
    
    //checks for the winner
    public boolean isWinner(String player) {
    // Check rows
    for (int i = 0; i < 3; i++) {
        if (buttons[i].getText().equals(player) && buttons[i + 3].getText().equals(player)
                && buttons[i + 6].getText().equals(player)) {
            return true;
        }
    }

    // Check columns
    for (int i = 0; i < 9; i += 3) {
        if (buttons[i].getText().equals(player) && buttons[i + 1].getText().equals(player)
                && buttons[i + 2].getText().equals(player)) {
            return true;
        }
    }

    // Check diagonals
    if (buttons[0].getText().equals(player) && buttons[4].getText().equals(player)
            && buttons[8].getText().equals(player)) {
        return true;
    }

    if (buttons[2].getText().equals(player) && buttons[4].getText().equals(player)
            && buttons[6].getText().equals(player)) {
        return true;
    }

    return false;
}

    //checks if X won
    public void xWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("X wins");

        
    }
   
    //checkd if o won''
    public void oWins(int a, int b, int c){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for(int i = 0;i<9;i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("O wins");
    }
    


    
}
