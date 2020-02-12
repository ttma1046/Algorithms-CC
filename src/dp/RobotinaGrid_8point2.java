package dp;

import java.util.ArrayList;
import java.util.HashSet;

class RobotinaGrid_8point2 {
    ArrayList<Point> getPath(boolean[][] maze) {
        if (maze == null || maze.length == 0)
            return null;

        ArrayList<Point> path = new ArrayList<Point>();
        if (getPath(maze, maze.length - 1, maze[0].length - 1, path)) {
            return path;
        }
        return null;
    }

    boolean getPath(boolean[][] maze, int row, int col, ArrayList<Point> path) {
        /* If out of bounds or not availabe, return. */
        if (row < 0 || col < 0 || !maze[row][col]) {
            return false;
        }

        boolean isAtOrgin = (row == 0) && (col == 0);

        if (isAtOrgin || getPath(maze, row, col - 1, path) || getPath(maze, row - 1, col, path)) {
            Point p = new Point(row, col);
            path.add(p);
            return true;
        }

        return false;
    }

    ArrayList<Point> getPathII(boolean[][] maze) {
        if (maze == null || maze.length == 0) {
            return null;
        }

        ArrayList<Point> path = new ArrayList<Point>();

        HashSet<Point> failedPoints = new HashSet<Point>();

        if (getPathII(maze, maze.length - 1, maze[0].length - 1, path, failedPoints)) {
            return path;
        }
        return null;
    }

    boolean getPathII(boolean[][] maze, int row, int col, ArrayList<Point> path, HashSet<Point> failedPoints) {
        /* If out of bounds or not availabe, return. */
        if (row < 0 || col < 0 || !maze[row][col]) {
            return false;
        }

        Point p = new Point(row, col);

        if (failedPoints.contains(p)) {
            return false;
        }

        boolean isAtOrgin = (row == 0) && (col == 0);

        if (isAtOrgin || getPathII(maze, row, col - 1, path, failedPoints)
                || getPathII(maze, row - 1, col, path, failedPoints)) {

            path.add(p);
            return true;
        }

        failedPoints.add(p);

        return false;
    }

}