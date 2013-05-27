/**
 * This file is part of tetris4j.
 *
 * tetris4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * tetris4j is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with tetris4j.  If not, see <http://www.gnu.org/licenses/>.
 */
package tetris4j;

import java.awt.*;

/*More concrete representation of a block.*/
public class Block implements Cloneable {

  private static int RED_VALUE = 180;
  private static int GREEN_VALUE = 160;

  public static final Color[] colors = {

      new Color(RED_VALUE, 0, 0, 240),
      new Color(RED_VALUE, 0, 0, 225),
      new Color(RED_VALUE, 0, 0, 210),
      new Color(RED_VALUE, 0, 0, 185),
      new Color(RED_VALUE, 0, 0, 160),
      new Color(RED_VALUE, 0, 0, 155),
      new Color(RED_VALUE, 0, 0, 140)
  };


  public static final int EMPTY = 0, FILLED = 1, ACTIVE = 2;

  /*Color of an empty block.*/
  public static final Color emptycolor = new Color(120, 120, 120, 90);

  /*State of the block.*/
  private volatile int state = EMPTY;

  /*Color of the block.*/
  private volatile Color color = emptycolor;

  /*Null constructor.*/
  public Block() {
  }

  /*Initializing constructor.*/
  public Block(int s) {
    state = s;
  }

  /*String representation of this object.*/
  public String toString() {
    return color.toString();
  }

  /*Compares the state for equality.*/
  public boolean equals(Object o) {
    if (!(o instanceof Block)) return false;
    Block b = (Block) o;
    return b.state == state;
  }

  /*Implements the Clonable interface.*/
  public Block clone() {
    Block ret = new Block(state);
    ret.setColor(color);
    return ret;
  }

  public byte toByte() {
    switch (state) {
      case EMPTY:
      case FILLED:
      case ACTIVE:
        return (byte) state;
      default:
        return -1;
    }
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void colorFilled() {
    color = new Color(0, GREEN_VALUE, 0, color.getAlpha());
  }
}
