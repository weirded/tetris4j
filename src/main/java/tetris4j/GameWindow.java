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

import javax.swing.*;
import java.awt.*;


/*The game window.*/
public class GameWindow extends JFrame
{
	
	private GraphicsDevice dev;
	private TetrisPanel t;
	
	
	/*Creates a GameWindow, by default.*/
	public GameWindow()
	{
		this(ProjectConstants.STARTFS, null);
	}
	
	
	/*Creates a GameWindow and make it fullscreen or not.
	 * May be from another GameWindow.*/
	public GameWindow(boolean fullscreen, GameWindow old)
	{
		super();
		if(fullscreen)
		{
			createFullscreenWindow(old);
		}
		
		else createWindow(old);
		
		if(old!=null)
		{
			//Cleanup
			old.setVisible(false);
			old.dispose();
			old = null;
		}
	}
	
	
	
	private void createWindow(GameWindow old)
	{
		setUndecorated(false);
		setTitle("JTetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		if(old != null)
			t = old.t;
		else t = new TetrisPanel();
		
		t.setPreferredSize(new Dimension(800,600));
		setContentPane(t);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
		if(old==null)
			t.engine.startengine();
	}
	
	
	
	private void createFullscreenWindow(GameWindow old)
	{
		setUndecorated(true);
		setTitle("JTetris");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		
		if(old != null)
			t = old.t;
		else t = new TetrisPanel();
		
		t.setPreferredSize(new Dimension(800,600));
		setContentPane(t);
		
		try{
			dev =  GraphicsEnvironment
				.getLocalGraphicsEnvironment().getDefaultScreenDevice();
			dev.setFullScreenWindow(this);
			dev.setDisplayMode(new DisplayMode
					(800,600,32,DisplayMode.REFRESH_RATE_UNKNOWN));
		}catch(Throwable t){
				throw new RuntimeException("Getting screen device failed");
		}
		
		t.setPreferredSize(new Dimension(800,600));
		setContentPane(t);
		
		setVisible(true);
		SwingUtilities.updateComponentTreeUI(this);
		
		if(old==null)
			t.engine.startengine();
	}
	
	
	/*Creates a fullscreen window from an old window.*/
	public static GameWindow enterFullScreen(GameWindow win)
	{
		win = new GameWindow(true, win);
		try{
			win.dev.setFullScreenWindow(win);
			//800x600 fullscreen?
			win.dev.setDisplayMode(new DisplayMode
					(800,600,32,DisplayMode.REFRESH_RATE_UNKNOWN));
		
		}catch(Throwable t)
		{
			win.dev.setFullScreenWindow(null);
			throw new RuntimeException("Failed fullscreen");
		}
		return win;
	}
	
	
	/*Creates a windowed window (lol?) from an old window.*/
	public static GameWindow exitFullScreen(GameWindow win)
	{
		if(win.dev != null)
			win.dev.setFullScreenWindow(null);
		win = new GameWindow(false, win);
		return win;
	}
}
