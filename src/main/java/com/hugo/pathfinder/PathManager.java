package com.hugo.pathfinder;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.hugo.main.Game.TILES_IN_WIDTH;
import static com.hugo.main.Game.TILES_IN_HEIGHT;

import static com.hugo.main.Game.TILES_SIZE;

public class PathManager {
    //private PathFinder pathFinder;
    private int[][] map;

    private Random random;

    private List<Point> path;
    private Point start;
    private Point end;

    private boolean keyDown = false;
    private boolean addTileMode = false;
    private int x;
    private int y;

    public PathManager() {
        newGrid();
    }

    public void newGrid() {
        random = new Random();
        map = new int[TILES_IN_HEIGHT][TILES_IN_WIDTH];
        System.out.println(map.length + " | " + map[0].length);

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                int state = random.nextInt(4);
                if (state == 3) {
                    map[i][j] = 1;
                } else {
                    map[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + ",");
            }
            System.out.println();
        }

        for (int[] u : map) {
            for (int elem : u) {
                System.out.print(elem + ",");
            }
            System.out.println();
        }

        initPathFinder();
    }

    private void initPathFinder() {
/*        x = 3;
        y = 4;
        pathFinder = new PathFinder();
        pathFinder.setMap(map);
        pathFinder.setStart(0, 0);
        pathFinder.setEnd(x, y);
        path = pathFinder.getPath();*/
        start = new Point(0, 0, null);
        end = new Point(4, 3, null);
        path = PathFinder.FindPath(map, start, end);
        if (path != null) {
            for (Point point : path) {
                System.out.println(point);
            }
        }
        else
            System.out.println("No path found");
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) g.setColor(Color.DARK_GRAY);
                else g.setColor(Color.LIGHT_GRAY);
                g.fillRect(i * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE);

                g.setColor(Color.BLACK);
                g.drawRect(i * TILES_SIZE, j * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            }
        }

        drawStartStop(g);
        drawPath(g);
        g.setColor(new Color(0, 0, 0, 100));
        if (addTileMode) {
            g.drawString("ADD", 0, 10);
        } else {
            g.drawString("REMOVE", 0, 10);
        }
    }

    private void drawStartStop(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(start.x * TILES_SIZE, start.y * TILES_SIZE, TILES_SIZE, TILES_SIZE);

        g.setColor(Color.RED);
        g.fillRect(end.x * TILES_SIZE, end.y * TILES_SIZE, TILES_SIZE, TILES_SIZE);
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

    public void setStart(MouseEvent e) {

        int finalCalcX = (e.getX() / TILES_SIZE);
        int finalCalcY = (e.getY() / TILES_SIZE);
        Point point = new Point(finalCalcX, finalCalcY, null);
        if (PathFinder.IsWalkable(map, point)) {
            end.x = finalCalcX;
            end.y = finalCalcY;
            path = PathFinder.FindPath(map, start, end);
        }
        //System.out.println(finalCalcX + " | " + finalCalcY);
    }

    public void removeTile(MouseEvent e) {
        int finalCalcX = (e.getX() / TILES_SIZE);
        int finalCalcY = (e.getY() / TILES_SIZE);

        if (addTileMode) {
            map[finalCalcY][finalCalcX] = 1;
        } else {
            map[finalCalcY][finalCalcX] = 0;
        }
        path = PathFinder.FindPath(map, start, end);
    }

    public void newStart(int newX, int newY) {
        x += newX;
        y += newY;
        Point point = new Point(x, y, null);
        if (PathFinder.IsWalkable(map, point)) {
            end.x = x;
            end.y = y;
            path = PathFinder.FindPath(map, start, end);
        } else {
            x -= newX;
            y -= newY;
        }
    }


    public void keyPressed(KeyEvent e) {
        if (!keyDown) {
            keyDown = true;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_R:
                    newGrid();
                    break;
                case KeyEvent.VK_UP:
                    newStart(0, -1);
                    break;
                case KeyEvent.VK_DOWN:
                    newStart(0, 1);
                    break;
                case KeyEvent.VK_LEFT:
                    newStart(-1, 0);
                    break;
                case KeyEvent.VK_RIGHT:
                    newStart(1, 0);
                    break;
                case KeyEvent.VK_E:
                    addTileMode = !addTileMode;
                default:
                    break;
            }
        }
    }


    public void keyReleased(KeyEvent e) {
        keyDown = false;
    }
}
