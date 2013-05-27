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

/*Object representation of a tetromino.*/
public class Tetromino implements Cloneable
{
	/*Constructor.*/
	public Tetromino(){}
	
	
	/*Contents (Block array)*/
	public Block[][] array;
	
	
	/*Position, rotation, type, etc*/
	public volatile int x, y, rot, type;
	
	
	/*Color.*/
	public volatile Color color;
	
	
	/*Copy.*/
	public Tetromino clone()
	{
		Tetromino ret = new Tetromino();
		ret.array = array.clone();
		ret.x = x;
		ret.y = y;
		ret.rot = rot;
		ret.type = type;
		ret.color = color;
		return ret;
	}
	
	
	/*String representation.*/
	public String toString()
	{
		switch(type)
		{
		case 0:
			return "Long";
		case 1:
			return "Box";
		case 2:
			return "L";
		case 3:
			return "J";
		case 4:
			return "Dick";
		case 5:
			return "S";
		case 6:
			return "Z";
		default:
			return "NULL";
		}
	}
}
