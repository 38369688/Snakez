/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakez;

import audio.AudioPlayer;
import environment.Environment;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author alextsai
 */
class Forest extends Environment implements MoveValidatorIntf {

    private Grid grid;
    private Cobra hydra;
    private Image forest;
    
    
    private ArrayList<Prisoner> prisoners;

    public Forest() {
        this.setBackground(ResourceTools.loadImageFromResource("snakez/mumbai.png"));
        forest = ResourceTools.loadImageFromResource("snakez/forest.jpg");
        
        grid = new Grid(50, 30, 20, 20, new Point(), Color.BLACK);
        grid.setPosition(new Point((this.getWidth() - this.getGridWidth())/2, (this.getHeight() - this.getGridHeigth())/2));
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
    int timer = 0;
    int timerLimit = 20;

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

        if (grid != null) {
//      if hydra isn't stoped and the time is less than the timerlimit than the following applies
//            if () {

//            }
            if (timer <= timerLimit) {
                timer++;
            }
            if ((timer >= timerLimit) && (!hydra.isStopped())) {
                grid.setColumns(grid.getColumns() - 1);
                grid.setRows(grid.getRows() - 1);
                grid.setPosition(new Point((this.getWidth() - this.getGridWidth())/2, (this.getHeight() - this.getGridHeigth())/2));
                
                timer = 0;
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

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/snakez/mp5_smg.wav");
        }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("Realse LEFT!!!!!");
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("Realse UP!!!!!");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("Realse RIGHT!!!!!");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("Realse Down!!!!!");
        } else if (e.getKeyCode() == KeyEvent.VK_P) {
            System.out.println("PAUSED!!!!!");
            hydra.stop();
        } else if (e.getKeyCode() == KeyEvent.VK_G) {
            System.out.println("GO!!!!!");
            hydra.go();
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("mouse clicked at " + e.getPoint());
        System.out.println("mouse clicked in cell" + grid.getCellLocationFromSystemCoordinate(e.getPoint()));
    }
    
    private int getGridHeigth(){
        return grid.getCellHeight() * grid.getRows();
    }
    
    private int getGridWidth(){
        return grid.getCellWidth() * grid.getColumns();
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        
        if (forest != null) {
            graphics.drawImage(forest, grid.getPosition().x, grid.getPosition().y, 
                    getGridWidth(), getGridHeigth(), this);
        }
        
        
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
            hydra.stop();
            System.out.println("Game Over");
        } else if (proposedLocation.y < 0) {
            hydra.stop();
            System.out.println("Game Over");
        } else if (proposedLocation.x > grid.getColumns()) {
            hydra.stop();
            System.out.println("Game Over");
        } else if (proposedLocation.y > grid.getRows()) {
            hydra.stop();
            System.out.println("Game Over");
        }
        return proposedLocation;
    }
//</editor-fold>

}
