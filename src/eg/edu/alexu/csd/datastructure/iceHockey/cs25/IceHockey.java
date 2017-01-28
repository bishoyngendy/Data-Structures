package eg.edu.alexu.csd.datastructure.iceHockey.cs25;

import java.awt.Point;
import java.util.Arrays;
import java.util.Comparator;

import eg.edu.alexu.csd.datastructure.iceHockey.IPlayersFinder;

/**
 * Assignment 3 : IceHockey.
 * @author Bishoy Nader
 */
public class IceHockey implements IPlayersFinder {

  /**
   * Constant.
   */
  private static final int MAGIC_50 = 50;
  /**
   * Constant.
   */
  private static final int MAGIC_500 = 500;
  /**
   * Constant.
   */
  private static final int MAGIC_100 = -100;
  /**
   * Constant.
   */
  private static final int MAGIC_10000 = 10000;
  /**
   * Constant.
   */
  private static final int MAGIC_4 = 4;
  /**
   * Constant.
   */
  private static final int MAGIC_3 = 3;

  /**
   * Main Function.
   * @param args when running from the terminal
   */
  public static void main(final String[] args) { }

  /**
   * Comparator Object.
   */
  Comparator<Point> comp = new Comparator<Point>() {
    @Override
    public int compare(final Point arg0, final Point arg1) {
      if (arg0 == null || arg1 == null) {
        return 0;
      }
      if (arg0.x < arg1.x) {
        return -1;
      }
      if (arg0.x > arg1.x) {
        return 1;
      } else {
        return arg0.y - arg1.y;
        }
      }
  };
  /**
   * team parameter.
   */
  int locTeam;
  /**
   * rows number.
   */
  int rows;
  /**
   * columns number.
   */
  int columns;
  /**
   * threshold limit.
   */
  int locThreshold;
  /**
   * index of a return point.
   */
  int pointIndex = 0;
  /**
   * Photo itself.
   */
  String[] locPhoto;
  /**
   * return array.
   */
  Point[] pointsArray = new Point[MAGIC_500];
  /**
   * Storage array.
   */
  int[][] vis = new int[MAGIC_50][MAGIC_50];

  /**
   * reset Visited Array.
   */
  void resetArr() {
    for (int a = 0; a < rows; a++) {
      for (int b = 0; b < columns; b++) {
        if (vis[a][b] != 2) {
          vis[a][b] = 0;
        }
      }
    }
  }

  /**
   * reset visited cells.
   * @param horiz denotes x coordinate
   * @param vert denotes y coordinate
   */
  void resetVis(final int horiz, final int vert) {
    if (horiz < 0 || vert < 0 || horiz >= rows
        || vert >= columns) {
      return;
    }
    if (vis[horiz][vert] == 2) {
      return;
    }
    if (vis[horiz][vert] == 1) {
      vis[horiz][vert] = 2;
      resetVis(horiz + 1, vert);
      resetVis(horiz - 1, vert);
      resetVis(horiz, vert + 1);
      resetVis(horiz, vert - 1);
    }
    return;
  }

  /**
   * get depth of each continuous block.
   * @param horiz denotes x coordinate
   * @param vert denotes y coordinate
   * @return the length of block.
   */
  int getLen(final int horiz, final int vert) {
    if (horiz < 0 || vert < 0 || horiz >= rows
        || vert >= columns) {
      return 0;
    }
    if (vis[horiz][vert] == 1) {
      return 0;
    }
    vis[horiz][vert] = 1;
    if ((locPhoto[horiz].charAt(vert) - '0') == locTeam) {
      return 1 + getLen(horiz - 1, vert)
               + getLen(horiz + 1, vert)
               + getLen(horiz, vert - 1)
               + getLen(horiz, vert + 1);
    }
    return 0;
  }

  /**
   * Calculate the central point of the block.
   * @param horiz denotes x coordinate
   * @param vert denotes y coordinate
   * @param req required dimension to calculate
   * @return the central point of the block
   */
  int calc(final int horiz, final int vert, final int req) {
    if (req == 0) {
      if (horiz < 0 || vert < 0 || horiz >= rows
          || vert >= columns) {
        return MAGIC_10000;
      }
      if (vis[horiz][vert] == 1
          || (locPhoto[horiz].charAt(vert) - '0') != locTeam) {
        return MAGIC_10000;
      }
      int newDimension = horiz;
      if ((locPhoto[horiz].charAt(vert) - '0') == locTeam) {
        vis[horiz][vert] = 1;
        newDimension = Math.min(newDimension, calc(horiz - 1, vert, 0));
        newDimension = Math.min(newDimension, calc(horiz + 1, vert, 0));
        newDimension = Math.min(newDimension, calc(horiz, vert - 1, 0));
        newDimension = Math.min(newDimension, calc(horiz, vert + 1, 0));
      }
      return newDimension;
    } else if (req == 1) {
      if (horiz < 0 || vert < 0 || horiz >= rows
          || vert >= columns) {
        return MAGIC_100;
      }
      if (vis[horiz][vert] == 1
          || (locPhoto[horiz].charAt(vert) - '0') != locTeam) {
        return MAGIC_100;
      }
      int newDimensionx = horiz;
      if ((locPhoto[horiz].charAt(vert) - '0') == locTeam) {
        vis[horiz][vert] = 1;
        newDimensionx = Math.max(newDimensionx, calc(horiz - 1, vert, 1));
        newDimensionx = Math.max(newDimensionx, calc(horiz + 1, vert, 1));
        newDimensionx = Math.max(newDimensionx, calc(horiz, vert - 1, 1));
        newDimensionx = Math.max(newDimensionx, calc(horiz, vert + 1, 1));
      }
      return newDimensionx;
    } else if (req == 2) {
      if (horiz < 0 || vert < 0 || horiz >= rows
          || vert >= columns) {
        return MAGIC_10000;
      }
      if (vis[horiz][vert] == 1
          || (locPhoto[horiz].charAt(vert) - '0') != locTeam) {
        return MAGIC_10000;
      }
      int newDimension = vert;
      if ((locPhoto[horiz].charAt(vert) - '0') == locTeam) {
        vis[horiz][vert] = 1;
        newDimension = Math.min(newDimension, calc(horiz - 1, vert, 2));
        newDimension = Math.min(newDimension, calc(horiz + 1, vert, 2));
        newDimension = Math.min(newDimension, calc(horiz, vert - 1, 2));
        newDimension = Math.min(newDimension, calc(horiz, vert + 1, 2));
      }
      return newDimension;
    } else {
      if (horiz < 0 || vert < 0 || horiz >= rows
          || vert >= columns) {
        return MAGIC_100;
      }
      if (vis[horiz][vert] == 1
          || (locPhoto[horiz].charAt(vert) - '0') != locTeam) {
        return MAGIC_100;
      }
      int newDimension = vert;
      if ((locPhoto[horiz].charAt(vert) - '0') == locTeam) {
        vis[horiz][vert] = 1;
        newDimension = Math.max(newDimension, calc(horiz - 1, vert, MAGIC_3));
        newDimension = Math.max(newDimension, calc(horiz + 1, vert, MAGIC_3));
        newDimension = Math.max(newDimension, calc(horiz, vert - 1, MAGIC_3));
        newDimension = Math.max(newDimension, calc(horiz, vert + 1, MAGIC_3));
      }
      return newDimension;
    }
  }

  /**
   * Calls other functions.
   * @param horiz denotes x coordinate
   * @param vert denotes y coordinate
   */
  void solve(final int horiz, final int vert) {
    int len = getLen(horiz, vert);
    if ((len * MAGIC_4) >= locThreshold) {
      Point pnt = new Point();
      resetArr();
      int top = calc(horiz, vert, 0) * 2;
      resetArr();
      int bottom = (calc(horiz, vert, 1) * 2) + 2;
      pnt.y = top + (bottom - top) / 2;
      resetArr();
      int left = calc(horiz, vert, 2) * 2;
      resetArr();
      int right = (calc(horiz, vert, MAGIC_3) * 2) + 2;
      resetVis(horiz, vert);
      pnt.x = left + (right - left) / 2;
      pointsArray[pointIndex] = pnt;
      pointIndex++;
    }
  }

  @Override
  public Point[] findPlayers(final String[] photo, final int team,
                             final int threshold) {
    if (photo.length == 0 || photo[0].length() == 0) {
      return new Point[0];
    }
    this.locTeam = team;
    this.locPhoto = photo;
    this.locThreshold = threshold;
    rows = photo.length;
    columns = photo[0].length();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if ((photo[i].charAt(j) - '0') == team) {
          if (vis[i][j] != 2) {
            solve(i, j);
          }
        }
      }
    }
    Arrays.sort(pointsArray, comp);
    Point[] retArray = new Point[pointIndex];
    for (int i = 0; i < pointIndex; i++) {
      retArray[i] = pointsArray[i];
    }
    pointIndex = 0;
    for (int a = 0; a < rows; a++) {
      for (int b = 0; b < columns; b++) {
        vis[a][b] = 0;
      }
    }
    return retArray;
  }
}
