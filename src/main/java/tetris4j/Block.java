package tetris4j;

import java.awt.Color;

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
