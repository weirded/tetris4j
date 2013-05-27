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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/*This class contains a group of project constants.
 * Use import static tetris.tetris4j.ProjectConstants.*/
public class ProjectConstants {

  /*Should the application start fullscreened?*/
  public static final boolean STARTFS = false;

  /*Yes this adds leading zeroes.*/
  public static String addLeadingZeroes(int n, int zeroes) {
    if (zeroes > 10) throw new IllegalArgumentException();
    String ret = Integer.toString(n);
    while (ret.length() < zeroes) {
      ret = "0" + ret;
    }
    return ret;
  }

  /*Sleeps the current thread.*/
  public static void sleep_(int n) {
    try {
      Thread.sleep(n);
    } catch (Throwable t) {
      // Might throw a ThreadDeath if we're sleeping while we terminate the thread
      // but we're just going to ignore it.
    }
  }


  /*Returns a resource as an InputStream. First it
   * tries to create a FileInputStream from the parent
   * directory (if contents are unzipped) and then tries
   * to use getResourceAsStream if that fails.*/
  public static InputStream getResStream(String path)
      throws IOException {
    try {
      //This is actually helpful for those downloading it
      //with something other than Eclipse (Tortoise for example).
      //However this screws up with Eclipse.
      File f = new File("." + path);
      return new FileInputStream(f.getCanonicalFile());
    } catch (Exception ea) {
      //eclipse workaround.
      try {
        return ProjectConstants.class.getResourceAsStream(path);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    throw new RuntimeException("Filestream: " + path + " not found.");

  }

  /*Returns a resource as a URL object, for certain file
   * parsing. Should accomodate Eclipse and other clients/IDEs
   * as well. Currently it loads resources from Eclipse, the
   * jar file, and from Tortoise.*/
  @SuppressWarnings("deprecation")
  public static URL getResURL(String path) throws IOException {
    return ProjectConstants.class.getResource(path);
  }


  /*In case of errors, call this.*/
  public static String formatStackTrace(StackTraceElement[] e) {
    StringBuffer ret = new StringBuffer();
    for (StackTraceElement el : e) {
      ret.append("[");
      ret.append(el.getFileName() == null ?
          "Unknown source" : el.getFileName());
      ret.append(":");
      ret.append(el.getMethodName());
      ret.append(":");
      ret.append(el.getLineNumber());
      ret.append("]\n");
    }
    return ret.toString();
  }


  /*Enum representation of the current game's state*/
  //Moving this here lol.
  public enum GameState {
    STARTSCREEN,
    PLAYING,
    PAUSED,
    HISCORE,
    GAMEOVER,
    BUSY;
  }
}
