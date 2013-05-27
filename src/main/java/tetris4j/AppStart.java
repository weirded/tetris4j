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

import static tetris4j.ProjectConstants.formatStackTrace;

/*Class that starts the app!*/
public class AppStart
{
	/*Errors go to console if true, otherwise go to GUI logger.*/
	public static final boolean REPORT_TO_CONSOLE = true;
	
	public static void main(String... args)
	{
		System.setErr(System.out);
		
		try{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		}catch(Throwable t){}
		
		//Better exception catching.
		Thread.setDefaultUncaughtExceptionHandler(
				new Thread.UncaughtExceptionHandler(){

					public void uncaughtException(Thread t, Throwable e)
					{
						
						if(REPORT_TO_CONSOLE)
							e.printStackTrace();
						else
							JOptionPane.showMessageDialog(null,
							"Java version: " + System.getProperty("java.version")
							+ "\nOperating System: " + System.getProperty("os.name")
							+ "\nFatal exception in thread: " + t.getName()
							+ "\nException: " + e.getClass().getName()
							+ "\nReason given: " + e.getMessage()
							+ "\n\n"+formatStackTrace(e.getStackTrace()));
						
						System.exit(1);
					}
				});
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run()
			{
				//Get the ball rolling!
				
				new Thread(new Runnable()
				{
					public void run()
					{
						GameWindow win = new GameWindow();
					}
				}).start();
			}
		});
	}
}
