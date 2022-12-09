package com.hugo.levels;

import java.awt.*;
import static com.hugo.main.Game.TILES_SIZE;
public class Tile {

    private boolean hover;
    private int x;
    private int y;
    private Rectangle bounds;
    private int val;

    public Tile(int x, int y, int size) {
        this.x = x;
        this.y = y;
        bounds = new Rectangle(x  * TILES_SIZE, y * TILES_SIZE, size, size);
    }

    public void draw(Graphics g) {

        if (val == 1) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        else if (val == 2) {
            g.setColor(Color.GREEN);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        else if (val == 3) {
            g.setColor(Color.RED);
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }


        if (hover) {
            g.setColor(new Color(0,0,0,100));
            g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
        }

        g.setColor(Color.BLACK);
        g.drawRect(bounds.x, bounds.y, bounds.width, bounds.height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isHover() {
        return hover;
    }

    public void setHover(boolean hover) {
        this.hover = hover;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
