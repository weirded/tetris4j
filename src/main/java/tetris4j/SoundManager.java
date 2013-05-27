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

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequencer;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.IOException;
import java.io.InputStream;

import static tetris4j.ProjectConstants.getResStream;
import static tetris4j.ProjectConstants.getResURL;

/*This class loads, plays, and manages sound effects and
 * music for Tetris4j. The sound URL's are hardcoded
 * into this class and is loaded statically at runtime.*/
public class SoundManager {

  /*This represents the list of sounds available.*/
  public static enum Sounds {
    // sound/tetris.midi
    TETRIS_THEME,

    // sound/soundfall.wav
    FALL,

    // sound/soundrotate.wav
    ROTATE,

    // sound/soundclear.wav
    CLEAR,

    // sound/soundtetris.wav
    TETRIS,

    // sound/sounddie.wav
    DIE;
  }

  // do we even play music at all?
  public static final boolean PLAY_MUSIC = true;

  private Sequencer midiseq; //Midi sequencer, plays the music.

  private InputStream tetheme; //Tetris theme (midi-inputstream).

  //The collection of
  //sound effects used.
  private AudioClip fall, rotate, tetris, clear, die;

  private static SoundManager soundmanager = null;
  //Reference of the SoundManager.

  /*Since this class locks certain system resources, it's
   * best to only have one instance of this class. If an
   * instance of SoundManager already exists, this replaces
   * that with a new instance.*/
  public static SoundManager getSoundManager() {
    soundmanager = new SoundManager();
    return soundmanager;
  }

  //private initializer method.
  private SoundManager() {
    try {
      tetheme = getResStream("/sound/tetris.midi");
      fall = loadsound("/sound/soundfall.wav");
      rotate = loadsound("/sound/soundrotate.wav");
      tetris = loadsound("/sound/soundtetris.wav");
      clear = loadsound("/sound/soundclear.wav");
      die = loadsound("/sound/sounddie.wav");
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Cannot load sound.");
    }
  }

  /*Plays a sound. Sounds should be short because once this
   * is called again, the previous sound teminates and
   * the new sound starts.*/
  public synchronized void sfx(Sounds s) {
    if (!PLAY_MUSIC) return;

    switch (s) {
      case FALL:
        fall.play();
        break;
      case ROTATE:
        rotate.play();
        break;
      case TETRIS:
        tetris.play();
        break;
      case CLEAR:
        clear.play();
        break;
      case DIE:
        die.play();
        break;
      default:
        throw new IllegalArgumentException();
    }
  }

  /*Plays a music track. Currently the only track
   * is the default MIDI track (theme song).*/
  public synchronized void music(Sounds s) {
    if (!PLAY_MUSIC) return;

    if (s == null) {
      midiseq.close();
      return;
    } else if (s == Sounds.TETRIS_THEME) {

      try {
        midiseq = MidiSystem.getSequencer();
        midiseq = MidiSystem.getSequencer();
        midiseq.open();
        //Sometimes throws MidiUnavailableException.
        midiseq.setSequence(MidiSystem.getSequence(tetheme));
        midiseq.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
        midiseq.start();
      } catch (Exception e) {
        throw new RuntimeException("Cannot play MIDI.");
      }

    } else throw new IllegalArgumentException();
  }

  //returns an AudioClip from a String filename.
  private static AudioClip loadsound(String name) throws IOException {
    return Applet.newAudioClip(getResURL(name));
  }
}
