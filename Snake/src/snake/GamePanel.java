/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.*;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.*;
import java.util.Random;


public class GamePanel extends JPanel implements ActionListener {
    //instance variables
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;//how many things can fit into the screen 
    static final int DELAY = 100;//bigger the delay the slower 
    final int x[] = new int[GAME_UNITS];//for the movement of the snake around the grid
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int applex;
    int appley;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;
    
    //constructor
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);//allows respond to keyboard events and user interactions
        this.addKeyListener(new MyKeyAdapter());//aloows componets to respond ro keyboard events
        startGame();
    }
    //methods needed
    public void startGame(){
        newApple();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){
        if(running){
        //create a grid depending on the unit size
            for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++){
              g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
              g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE);
           }
        //DRAWING THE APPLE
             g.setColor(Color.RED);
             g.fillOval(applex, appley, UNIT_SIZE, UNIT_SIZE);
        
        //draw the snake
            for(int i = 0; i< bodyParts; i++){
            //the head of the snake
              if(i==0){
                  g.setColor(Color.green);
                  g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
               }
               else{
                  g.setColor(new Color(45,180,0));
                  g.fillRect(x[i],y[i],UNIT_SIZE,UNIT_SIZE);
               }
            }
            //Print the score at the top
             g.setColor(Color.yellow);
             g.setFont(new Font("Ink Free",Font.BOLD,40));
             FontMetrics metrics = getFontMetrics(g.getFont());//aligns the word to the center of the screen
             g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
    
        }
        else{
            gameOver(g);
        }
    }
    public void newApple(){
        //randomise the appearance of the apple and alligning it with the grid
        applex = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appley = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }
    public void move(){
        for(int i = bodyParts; i>0; i--){
            //shifting the body parts of the snake
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        //switch case to chnge the direction of thr snake
        switch(direction){
            case 'U':
                y[0] = y[0]-UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0]+UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0]-UNIT_SIZE;
                break;
            case'R':
                x[0] = x[0]+UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if((x[0] == applex) && (y[0] == appley)){
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void checkCollisions(){
        for(int i = bodyParts; i>0 ;i--){
            //chack if head collides with body
            if((x[0]==x[i]) && (y[0]==y[i])){
                running = false;
            }
        }
        //check if head touches left border
        if(x[0]<0){
            running = false;
        }
        //check if head touches right border
        if(x[0]>SCREEN_WIDTH){
            running = false;
        }
        //check if head touches top border
        if(y[0]<0){
            running = false;
        }
        //check if the head touches bottom border
        if(y[0]>SCREEN_HEIGHT){
            running = false;
        }
        if (!running){
            timer.stop();
        }
    }
    public void gameOver(Graphics g){
        //display the score
         g.setColor(Color.yellow);
         g.setFont(new Font("Ink Free",Font.BOLD,40));
         FontMetrics metrics1 = getFontMetrics(g.getFont());//aligns the word to the center of the screen
         g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2,g.getFont().getSize());
    
        
        //game over text
        g.setColor(Color.yellow);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());//aligns the word to the center of the screen
        g.drawString("Game Over",(SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,SCREEN_HEIGHT/2);
    }
    
     

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(running){
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }
    
    public class MyKeyAdapter extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e){
        //examine the 
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                if(direction != 'R'){
                    direction = 'L';
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(direction != 'L'){
                    direction = 'R';
                }
                break;
            case KeyEvent.VK_UP:
                if(direction != 'D'){
                    direction = 'U';
                }
                break;
            case KeyEvent.VK_DOWN:
                if(direction != 'U'){
                    direction = 'D';
                }
                break;
                
        }
    }

}
}
