/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakez;

import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author alextsai
 */
public class Cobra {

    public Cobra(Direction direction, Grid grid) {
        this.direction = direction;
        this.grid = grid;

        body = new ArrayList<>();
        body.add(new Point(5, 5));
        body.add(new Point(5, 4));
        body.add(new Point(5, 3));
        body.add(new Point(4, 3));
        body.add(new Point(3, 3));

    }

    private Direction direction = Direction.LEFT;
    private ArrayList<Point> body;
    private Grid grid;
    private Color bodyColor = Color.green;

    public void draw(Graphics graphics) {
        graphics.setColor(getBodyColor());

        for (int i = 0; i < getBody().size(); i++) {
            System.out.println("body locations = " + getBody().get(i).toString());
            graphics.fillOval(getGrid().getCellSystemCoordinate(getBody().get(i)).x,
                              getGrid().getCellSystemCoordinate(getBody().get(i)).y, 
                              getGrid().getCellWidth(), getGrid().getCellWidth());
        }
    }
    
    public void move(){
        Point newHead = new Point(getHead());
        
        if (getDirection() == Direction.LEFT) {
            newHead.x--;
        }       
        if (getDirection() == Direction.RIGHT) {
            newHead.x++;
        }       
        if (getDirection() == Direction.UP) {
            newHead.y--;
        }       
        if (getDirection() == Direction.DOWN) {
            newHead.y++;
        }
        getBody().add(0, newHead);
        getBody().remove(getBody().size() - 1);
    }
    
    public Point getHead(){
        return getBody().get(0);
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the body
     */
    public ArrayList<Point> getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(ArrayList<Point> body) {
        this.body = body;
    }

    /**
     * @return the grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * @return the bodyColor
     */
    public Color getBodyColor() {
        return bodyColor;
    }

    /**
     * @param bodyColor the bodyColor to set
     */
    public void setBodyColor(Color bodyColor) {
        this.bodyColor = bodyColor;
    }

}
