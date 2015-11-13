/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakez;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author alextsai
 */
class Prison extends Environment {
    
    Grid grid;

    public Prison() {
        grid = new Grid(25, 25, 20, 20, new Point(10,50), Color.BLACK);
    }

    @Override
    public void initializeEnvironment() {
    }
    
    int counter;

    @Override
    public void timerTaskHandler() {
//        System.out.println("Hey dude..." + counter++);
        
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
//        System.out.println("Key Event" + e.getKeyChar());
//        System.out.println("Key Event" + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Go LEFT!!!!!");
        }else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Go UP!!!!!");
        }else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Go RIGHT!!!!!");
        }else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Go Down!!!!!");
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Realse LEFT!!!!!");
        }else if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Realse UP!!!!!");
        }else if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("Realse RIGHT!!!!!");
        }else if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Realse Down!!!!!");
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("mouse clicked at " + e.getPoint());
        System.out.println("mouse clicked in cell" + grid.getCellLocationFromSystemCoordinate(e.getPoint()));
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
        }
    }
    
}
