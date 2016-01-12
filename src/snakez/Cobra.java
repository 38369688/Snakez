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

    public Cobra(Direction direction, Grid grid, MoveValidatorIntf validator) {
        this.direction = direction;
        this.grid = grid;
        this.validator = validator;

        body = new ArrayList<>();
        body.add(new Point(5, 5));
        body.add(new Point(5, 4));
        body.add(new Point(5, 3));
        body.add(new Point(4, 3));
        body.add(new Point(2, 3));
        body.add(new Point(1, 3));
        body.add(new Point(1, 2));

    }

    public void draw(Graphics graphics) {
        graphics.setColor(getBodyColor());

        for (int i = 0; i < getBody().size(); i++) {
//            System.out.println("body locations = " + getBody().get(i).toString());
            graphics.fillOval(getGrid().getCellSystemCoordinate(getBody().get(i)).x,
                    getGrid().getCellSystemCoordinate(getBody().get(i)).y,
                    getGrid().getCellWidth(), getGrid().getCellWidth());
        }
    }

    public void move() {
        if (!stopped) {
            Point newHead = new Point(getHead());

            if (getDirection() == Direction.LEFT) {
                newHead.x--;
            } else if (getDirection() == Direction.RIGHT) {
                newHead.x++;
            } else if (getDirection() == Direction.UP) {
                newHead.y--;
            } else if (getDirection() == Direction.DOWN) {
                newHead.y++;
            }
            getBody().add(HEAD_POSITION, validator.validateMove(newHead));
            getBody().remove(getBody().size() - 1);
        }
    }

    private static final int HEAD_POSITION = 0;
    private Direction direction = Direction.LEFT;
    private ArrayList<Point> body;
    private Grid grid;
    private Color bodyColor = Color.green;
    private final MoveValidatorIntf validator;
    private boolean stopped = false;

    public void stop(){
        stopped = true;
    }

    public void go(){
        stopped = false;
    }
    
    public Point getHead() {
        return getBody().get(HEAD_POSITION);
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
