/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakez;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author alextsai
 */
class GridItem {
    
//   public GridItem(int x, int y, String type, CellDataProviderIntf cellData){
   public GridItem(Point location, String type, CellDataProviderIntf cellData){
       this.x = location.x;
       this.y = location.y;
       this.type = type;
       this.cellData = cellData;     
   }

    public void draw(Graphics graphics) {
        if (type.equals(ITEM_TYPE_WORKER)) {
            graphics.setColor(Color.red);
        } else if (type.equals(ITEM_TYPE_BOMB)) {
            graphics.setColor(Color.BLACK);
        }
        
        graphics.drawOval(cellData.getSystemCoordX(x, y), cellData.getSystemCoordY(x, y), cellData.getCellWidth(), cellData.getCellHeight());   
    }

    public static final String ITEM_TYPE_WORKER = "WORKER";
    public static final String ITEM_TYPE_BOMB = "BOMB";
//    public static final String ITEM_TYPE = "";
    
    private int x, y;
    private final String type;
//    private Image image;
    private CellDataProviderIntf cellData;    
}
