package com.hugo.levels;

import com.hugo.main.Game;
import com.hugo.pathfinder.PathFinder;
import com.hugo.pathfinder.Point;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

import static com.hugo.main.Game.TILES_IN_HEIGHT;
import static com.hugo.main.Game.TILES_IN_WIDTH;
import static com.hugo.main.Game.TILES_SIZE;

public class LevelManager {

    private Game game;
    private Tile[][] tiles;
    private List<com.hugo.pathfinder.Point> path;
    private com.hugo.pathfinder.Point start;
    private Point end;

    public LevelManager(Game game) {
        this.game = game;
        init();

        path = PathFinder.FindPath(tiles, start, end);
        if (path != null) {
            for (Point point : path) {
                System.out.println(point);
            }
        }
        else
            System.out.println("No path found");
    }

    private void init() {
/*        map = new int[][]{
                {0, 0, 0, 0, 0},
                {1, 0, 0, 0, 0},
                {1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0}
        };*/

        //map = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];

        tiles = new Tile[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        for (int i = 0; i < tiles.length; i ++) {
            for (int j = 0; j < tiles[0].length; j++) {
                tiles[i][j] = new Tile(j, i, TILES_SIZE);
            }
        }

        start = new Point(tiles[4][4].getX(), tiles[4][4].getY(), null);
        end = new Point(tiles[0][4].getX(), tiles[0][4].getY(), null);
        tiles[start.x][start.y].setVal(2);
        tiles[end.x][end.y].setVal(3);
        end = new Point(0, 4, null);
    }

    public void update() {

    }

    public void draw(Graphics g) {
        for (Tile[] innerTile : tiles) {
            for (Tile tile : innerTile) {
                tile.draw(g);
            }
        }

        drawPath(g);
    }

    private void drawPath(Graphics g) {
        if (path != null) {
            //List<Point> path = pathFinder.getPath();
            //g.fillRect(, );
            g.setColor(Color.BLUE);
            int size = TILES_SIZE / 2;

            for (int i = 0; i < path.size(); i++) {
                g.fillOval(path.get(i).x * TILES_SIZE + (size / 2), path.get(i).y * TILES_SIZE + (size / 2), size, size);
            }
        }
    }

    public void checkHover(int x, int y) {
        for (Tile[] innerTile : tiles) {
            for (Tile tile : innerTile) {
                tile.setHover(false);
            }
        }
        tiles[y / TILES_SIZE][x / TILES_SIZE].setHover(true);
    }

    private void setTile(int x, int y) {
        if (tiles[y / TILES_SIZE][x / TILES_SIZE].getVal() == 1) tiles[y / TILES_SIZE][x / TILES_SIZE].setVal(0);
        else if (tiles[y / TILES_SIZE][x / TILES_SIZE].getVal() == 0) tiles[y / TILES_SIZE][x / TILES_SIZE].setVal(1);
        path = PathFinder.FindPath(tiles, start, end);
    }

    public void mouseDragged(MouseEvent e) {
        //setTile(e.getX(), e.getY());
    }

    public void mouseMoved(MouseEvent e) {
//        gamePanel.getGame().getPathManager().setStart(e);
        checkHover(e.getX(), e.getY());
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        setTile(e.getX(), e.getY());
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
