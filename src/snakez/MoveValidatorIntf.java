/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakez;

import java.awt.Point;

/**
 *
 * @author alextsai
 */
public interface MoveValidatorIntf {
    public Point validateMove(Point proposedLocation);
}
