import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * Copyright Georgia Institute of Technology 2004-2005
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments 
	 */
	public Picture ()
	{
		/* not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor 
		 */
		super();  
	}

	/**
	 * Constructor that takes a file name and creates the picture 
	 * @param fileName the name of the file to create the picture from
	 */
	public Picture(String fileName)
	{
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * @param width the width of the desired picture
	 * @param height the height of the desired picture
	 */
	public Picture(int width, int height)
	{
		// let the parent class handle this width and height
		super(width,height);
	}

	/**
	 * Constructor that takes a picture and creates a 
	 * copy of that picture
	 */
	public Picture(Picture copyPicture)
	{
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * @param image the buffered image to use
	 */
	public Picture(BufferedImage image)
	{
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * @return a string with information about the picture such as fileName,
	 * height and width.
	 */
	public String toString()
	{
		String output = "Picture, filename " + getFileName() + 
				" height " + getHeight() 
				+ " width " + getWidth();
		return output;

	}

	public static void main(String[] args) 
	{
		Picture spike = new Picture(800,600);
		spike.drawChaos();
		spike.explore();
	}

	public void drawDot(Color c, int x, int y)
	{
		Graphics g = getGraphics();
		g.setColor(c);
		g.fillRect(x, y, 1, 1);
	}
	public void drawChaos()
	{
		int x1 = 10;
		int y1 = getHeight() - 10;
		int x2 = getWidth() / 2;
		int y2 = 10;
		int x3 = getWidth() - 10;
		int y3 = getHeight() - 10;
		int x = getWidth() / 2;
		int y = getHeight() / 2;
		for (int i=0; i<150000; i++)
		{
			int mad = (int)(Math.random() *3 + 1);
			if (mad == 1)
			{
				x = (x + x1) / 2;
				y = (y + y1) / 2;
				drawDot(Color.blue, x ,y);
			}
			if (mad == 2)
			{
				x = (x + x2) / 2;
				y = (y + y2) / 2;
				drawDot(Color.red, x, y);
			}
			if (mad == 3)
			{
				x = (x + x3) / 2;
				y = (y + y3) / 2;
				drawDot(Color.black, x, y); 
			}
		}
	}
} // this } is the end of class Picture, put all new methods before this
