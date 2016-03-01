/*
 * Have worker as food running away from you and they will sence you when you are 
 5 tiles away from them, they will also drop bombs on a random generated number from 1- 50
 every tile they travel and if the number maches with the set number they will drop it, and
 the bomb will go after 3 seconds and does nothing or go when the snake head enters the same tile.
 And stun you for 2 seconds. Also safe zone if we get there.



    Homework for Alex
      - get image for workers
      - get image for bombs
      - get sounds for events 
         - drop bomb
        - bomb explode
        - eating
        - game end
        - game start
        - background music
        - grid shrink

      - think about worker movement
      - randomly drop bombs (need ArralyList for bombs, and way yo create them
      - draw bombs in paint method
      - think about score
      - think about thinking
      - think about Mr. Lawrence being unhappy when you are not assertive and doing work
      - design menu
      - think about drawing snake a different, more exciting way
      - think about difficult progression...


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
class Forest extends Environment implements MoveValidatorIntf, CellDataProviderIntf {

    private Grid grid;
    private Cobra hydra;
    private Image forest;

    private ArrayList<GridItem> workers;
    private ArrayList<GridItem> bombs;

    public Forest() {
        this.setBackground(ResourceTools.loadImageFromResource("snakez/mumbai.png"));
        forest = ResourceTools.loadImageFromResource("snakez/forest.jpg");

        grid = new Grid(50, 30, 20, 20, new Point(10, 50), Color.BLACK);
//        grid.setPosition(new Point((this.getWidth() - this.getGridWidth())/2, (this.getHeight() - this.getGridHeigth())/2));
        hydra = new Cobra(Direction.RIGHT, grid, this);

        workers = new ArrayList<>();
//        workers.add(new GridItem());
    }

    @Override
    public void initializeEnvironment() {
    }

    int counter;

    int moveDelay = 0;
    int moveDelayLimit = 1;
    int workerTimer = 0;
    int workerTimerLimit = 30;

    int workerLimit = 15;

    @Override
    public void timerTaskHandler() {
        if (hydra != null) {
            if (moveDelay >= moveDelayLimit) {
                hydra.move();
                moveDelay = 0;
            } else {
                moveDelay++;
            }
        }

        if (grid != null) {
            if (workerTimer < workerTimerLimit) {
                workerTimer++;
            } else if (!hydra.isStopped()) {
                workers.add(new GridItem(getRandomBoundaryPoint(), GridItem.ITEM_TYPE_WORKER, this));

                //if we reach the worker limit, then shrink grid and reset workers (eliminate them)
                if (workers.size() >= workerLimit) {
                    grid.setColumns(grid.getColumns() - 1);
                    grid.setRows(grid.getRows() - 1);
                    grid.setPosition(new Point((this.getWidth() - this.getGridWidth()) / 2, (this.getHeight() - this.getGridHeigth()) / 2));

                    workers.clear();
                }
                workerTimer = 0;
            }
        }
    }

    private Point getRandomBoundaryPoint() {
        double random = Math.random();
        int x, y;

        if (random <= .25) { // scenario #1 left column of grid
            x = 0;
            y = (int) (Math.random() * grid.getRows());
        } else if (random <= .5) { // scenario #2 rightmost column of grid
            x = grid.getColumns() - 1;
            y = (int) (Math.random() * grid.getRows());
        } else if (random <= .75) { // scenario #3 top row of grid
            x = (int) (Math.random() * grid.getColumns());
            y = 0;
        } else { // scenario #4 bottom row of grid
            x = (int) (Math.random() * grid.getColumns());
            y = grid.getRows() - 1;
        }

        return new Point(x, y);
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

    private int getGridHeigth() {
        return grid.getCellHeight() * grid.getRows();
    }

    private int getGridWidth() {
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

        if (workers != null) {
            for (GridItem worker : workers) {
                worker.draw(graphics);
            }
        }
    }

//<editor-fold defaultstate="collapsed" desc="MoveValidatorIntf Methods">
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

//<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf Methods">
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;
    }

    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
    }
//</editor-fold>

}
