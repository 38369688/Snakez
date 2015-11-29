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
import java.util.ArrayList;

/**
 *
 * @author alextsai
 */
class Prison extends Environment implements MoveValidatorIntf {

    private Grid grid;
    private Cobra hydra;
    ;
    private ArrayList<Prisoner> prisoners;

    public Prison() {
        grid = new Grid(50, 30, 20, 20, new Point(10, 50), Color.BLACK);
        hydra = new Cobra(Direction.RIGHT, grid, this);
        
        prisoners = new ArrayList<>();
        prisoners.add(new Prisoner());
    }

    @Override
    public void initializeEnvironment() {
    }

    int counter;

    int moveDelay = 0;
    int moveDelayLimit = 1;

    @Override
    public void timerTaskHandler() {
//        System.out.println("Hey dude..." + counter++);
        if (hydra != null) {
            if (moveDelay >= moveDelayLimit) {
                hydra.move();
                moveDelay = 0;
            } else {
                moveDelay++;
            }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
//        System.out.println("Key Event" + e.getKeyChar());
//        System.out.println("Key Event" + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            hydra.setDirection(Direction.LEFT);
            hydra.move();
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            hydra.setDirection(Direction.UP);
            hydra.move();

        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            hydra.setDirection(Direction.RIGHT);
            hydra.move();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            hydra.setDirection(Direction.DOWN);
            hydra.move();

        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_A) {
//            System.out.println("Realse LEFT!!!!!");
//        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            System.out.println("Realse UP!!!!!");
//        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            System.out.println("Realse RIGHT!!!!!");
//        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            System.out.println("Realse Down!!!!!");
//        }
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
        if (hydra != null) {
            hydra.draw(graphics);
        }
        if (prisoners != null) {
            for (int i = 0; i < prisoners.size(); i++) {
                prisoners.get(i).draw(graphics);
            }
        }
    }

//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf">
    @Override
    public Point validateMove(Point proposedLocation) {
        if (proposedLocation.x < 0) {
            System.out.println("Game Over");
        }else if (proposedLocation.y < 0){
            System.out.println("Game Over");
        }else if (proposedLocation.x > 50){
            System.out.println("Game Over");
        }else if (proposedLocation.y > 30){
            System.out.println("Game Over");
        }
        return proposedLocation;
    }
//</editor-fold>

}
