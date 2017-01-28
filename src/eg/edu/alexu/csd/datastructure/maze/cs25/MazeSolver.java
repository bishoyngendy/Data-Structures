package eg.edu.alexu.csd.datastructure.maze.cs25;

import java.awt.Point;
import java.io.File;
import java.util.Scanner;

import eg.edu.alexu.csd.datastructure.maze.IMazeSolver;
import eg.edu.alexu.csd.datastructure.queue.cs25.LinkedListQueue;
import eg.edu.alexu.csd.datastructure.stack.cs25.MyStack;

/**
 * Assignment 7 : Maze.
 * @author Bishoy Nader
 */
public class MazeSolver implements IMazeSolver {

  /**
   * Constant.
   */
  private static final int CONST_4 = 4;

  /**
   * Array Length.
   */
  int length;
  /**
   * Array Width.
   */
  int width;
  /**
   * Returned Array.
   */
  int[][] retArr;
  /**
   * Scanner to scan from file.
   */
  Scanner scan;
  /**
   * File Content.
   */
  char[][] map;
  /**
   * Visited Array.
   */
  Boolean[][] vis;
  /**
   * Way moved.
   */
  Point[][] way;
  /**
   * Parent of each cell.
   */
  Point[][] parent;
  /**
   * Moving Directions.
   */
  int[][] dir = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

  @Override
  public int[][] solveBFS(final File maze) {
    LinkedListQueue queue = new LinkedListQueue();
    if (!maze.exists()) {
      throw new RuntimeException();
    }
    loadData(maze);
    Point start = getStart();
    queue.enqueue(start);
    Boolean found = false;
    while (!queue.isEmpty()) {
      Point tmp = (Point) queue.dequeue();
      vis[tmp.x][tmp.y] = true;
      if (map[tmp.x][tmp.y] == 'E') {
        found = true;
        break;
      }
      Boolean canMove = false;
      for (int k = 0; k < CONST_4; k++) {
        int horiz = dir[k][0];
        int vert = dir[k][1];
        if (isAvailable(tmp.x + horiz, tmp.y + vert)) {
          canMove = true;
          way[tmp.x][tmp.y] = new Point(tmp.x + horiz, tmp.y + vert);
          parent[tmp.x + horiz][tmp.y + vert] = new Point(tmp.x, tmp.y);
          queue.enqueue(new Point(tmp.x + horiz, tmp.y + vert));
        }
      }
      if (!canMove && map[tmp.x][tmp.y] == 'S') {
        return null;
      }
      if (!canMove) {
        queue.enqueue(new Point(parent[tmp.x][tmp.y].x,
                                parent[tmp.x][tmp.y].y));
      }
    }
    if (found) {
      int wayLength = 1;
      int horiz = start.x;
      int vert = start.y;
      while (map[horiz][vert] != 'E') {
        int tmpHoriz = horiz;
        int tmpVert = vert;
        horiz = way[tmpHoriz][tmpVert].x;
        vert = way[tmpHoriz][tmpVert].y;
        wayLength++;
      }
      horiz = start.x;
      vert = start.y;
      retArr = new int[wayLength][2];
      for (int i = 0; i < wayLength; i++) {
        retArr[i][0] = horiz;
        retArr[i][1] = vert;
        if (i != wayLength - 1) {
          int tmpHoriz = horiz;
          int tmpVert = vert;
          horiz = way[tmpHoriz][tmpVert].x;
          vert = way[tmpHoriz][tmpVert].y;
        }
      }
      return retArr;
    } else {
      return null;
    }
  }

  @Override
  public int[][] solveDFS(final File maze) {
    MyStack stack = new MyStack();
    if (!maze.exists()) {
      throw new RuntimeException();
    }
    loadData(maze);
    Point start = getStart();
    stack.push(start);
    Boolean found = false;
    while (!stack.isEmpty()) {
      Point tmp = (Point) stack.pop();
      vis[tmp.x][tmp.y] = true;
      if (map[tmp.x][tmp.y] == 'E') {
        found = true;
        break;
      }
      Boolean canMove = false;
      for (int k = 0; k < CONST_4; k++) {
        int horiz = dir[k][0];
        int vert = dir[k][1];
        if (isAvailable(tmp.x + horiz, tmp.y + vert)) {
          canMove = true;
          way[tmp.x][tmp.y] = new Point(tmp.x + horiz, tmp.y + vert);
          parent[tmp.x + horiz][tmp.y + vert] = new Point(tmp.x, tmp.y);
          stack.push(new Point(tmp.x + horiz, tmp.y + vert));
          break;
        }
      }
      if (!canMove && map[tmp.x][tmp.y] == 'S') {
        return null;
      }
      if (!canMove) {
        stack.push(new Point(parent[tmp.x][tmp.y].x,
                             parent[tmp.x][tmp.y].y));
      }
    }
    if (found) {
      int wayLength = 1;
      int horiz = start.x;
      int vert = start.y;
      while (map[horiz][vert] != 'E') {
        int tmpHoriz = horiz;
        int tmpVert = vert;
        horiz = way[tmpHoriz][tmpVert].x;
        vert = way[tmpHoriz][tmpVert].y;
        wayLength++;
      }
      horiz = start.x;
      vert = start.y;
      retArr = new int[wayLength][2];
      for (int i = 0; i < wayLength; i++) {
        retArr[i][0] = horiz;
        retArr[i][1] = vert;
        if (i != wayLength - 1) {
          int tmpHoriz = horiz;
          int tmpVert = vert;
          horiz = way[tmpHoriz][tmpVert].x;
          vert = way[tmpHoriz][tmpVert].y;
        }
      }
      return retArr;
    } else {
      return null;
    }
  }

  /**
   * Checks whether it is a valid point or not.
   * @param horiz x dimension
   * @param vert y dimension
   * @return the result of the condition
   */
  private Boolean isAvailable(final int horiz, final int vert) {
    if (horiz < 0 || vert < 0) {
      return false;
    }
    if (horiz >= length || vert >= width) {
      return false;
    }
    if (map[horiz][vert] == '#') {
      return false;
    }
    if (vis[horiz][vert]) {
      return false;
    }
    return true;
  }

  /**
   * Loading Data from file.
   * @param maze file parameter
   */
  private void loadData(final File maze) {
    try {
      scan = new Scanner(maze);
      length = scan.nextInt();
      width = scan.nextInt();
      map = new char[length][width];
      vis = new Boolean[length][width];
      way = new Point[length][width];
      parent = new Point[length][width];
      int horizInd = 0;
      int vertInd = 0;
      while (scan.hasNext()) {
        String line = scan.next();
        if (line.length() != width) {
          throw new RuntimeException();
        }
        for (int i = 0; i < line.length(); i++) {
          if (vertInd == width) {
            vertInd = 0;
            horizInd++;
          }
          map[horizInd][vertInd] = line.charAt(i);
          vertInd++;
        }
      }
    } catch (Exception except) {
      throw new RuntimeException();
    }
  }

  /**
   * Getting the Starting point of the maze.
   * @return the starting point of maze
   */
  private Point getStart() {
    Boolean found = false;
    Boolean end = false;
    Point start = new Point();
    for (int i = 0; i < length; i++) {
      for (int j = 0; j < width; j++) {
        vis[i][j] = false;
        if (map[i][j] == 'S') {
          start.x = i;
          start.y = j;
          found = true;
        }
        if (map[i][j] == 'E') {
          end = true;
        }
      }
    }
    if (!end || !found) {
      throw new RuntimeException();
    } else {
      return start;
    }
  }
}
