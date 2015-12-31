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
 * Copyright Georgia Institute of Technology 2504-2505
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
		String fileName= FileChooser.pickAFile();
		Picture fred = new Picture (fileName);
		fred.explore();
		//		fred.frame(60);
		//				fred.grayAllButRed();
		//		fred.darken();
		//				fred.grayscale();
		//				 fred.swap();
		//		fred.myEffect(100);
		//				fred.soccerFix();
		//		//		
		//		fred.blackenLowerLeft();

		//		fred.gradient();
		fred.peaceEffect();
		fred.explore();
	}

	public boolean inCircle(int x, int y)
	{
		int width = getWidth();
		int height = getHeight();
		int r = 0;

		if (height<width)
		{
			r = (height/2);
		}
		else
		{
			r= (width/2);
		}

		if ((x-(width/2))*(x-(width/2)) + (y-(height/2))*(y-(height/2)) < (r*r))
			return true;
		else
			return false;
	}

	public void peaceEffect()
	{
		int w = getWidth();
		int h = getHeight();

		for (int x= 0; x < w; x++)
			for (int y = 0;  y< h; y++)
			{
				if(inCircle(x,y))
				{
					Pixel p = getPixel(x,y);

					if (inRegionA(x,y))effectA(p);
					if (inRegionB(x,y))effectB(p);
					if (inRegionC(x,y))effectC(p);
					if (inRegionD(x,y))effectD(p);
				}
			}
	}

	public boolean  inRegionA(int x, int y)
	{
		int width = getWidth();
		int height = getHeight();
		{
			if(x < width/2 && y <( -x + (height + width) / 2 ))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}

	public void effectA(Pixel p)
	{
		if (p.getGreen() > p.getRed() || p.getGreen() > p.getBlue())
		{
			int red = p.getRed();
			int green = p.getGreen();
			int blue= p.getBlue();
			int avg = (red+green+blue)/3;
			p.setRed(0);
			p.setGreen(avg);
			p.setBlue(0);
		}
	}

	public boolean inRegionB(int x, int y)
	{
		int h = getHeight();
		int w = getWidth();
		if (x >= getWidth()/2 && y <= (y=x+((h-w)/2)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void effectB(Pixel p)

	{
		int red = p.getRed();
		int green = p.getGreen();
		int blue = p.getBlue();
		p.setRed(red - 5);
		p.setGreen(green - 50);
		p.setBlue(blue-50);
	}

	public boolean inRegionC(int x, int y)
	{
		int width = getWidth();
		int height = getHeight();
		if (x > width/2 && y-height/2 > +1*(x-width/2))
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	
	public void effectC(Pixel p) 
	{
	int red = p.getRed();
	int green = p.getGreen();
	int blue = p.getBlue();
	double f = Math.sqrt(red*red*.299 + green*green*.587 +blue*blue*.114);
	p.setRed((int)(f + (red - f)*3));
	p.setBlue((int)(f + (blue - f)*3));
	p.setGreen((int)(f + (green - f)*3));

	}


	public boolean inRegionD(int x, int y) 
	{
		int width= getWidth();
		int height= getHeight();

		if(x<width/2 && y-height/2 >=-1*(x-width/2))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void effectD(Pixel p)
	{

		{
			int green = (int) (p.getGreen()* 0.587);
			int red = (int) (p.getRed() * 0.299);
			int blue = (int) (p.getBlue() * 0.114);
			int getAverage = (green + red + blue);
			p.setBlue(getAverage);
			p.setGreen(getAverage);
			p.setRed(getAverage);
		}
	}

	//	public boolean lowerLeft(int x, int y)
	//	{
	//		int width = getWidth();
	//		int height = getHeight();
	//		{
	//			if (x < 0.5 *width && y > 0.5 * height)
	//			{
	//				return true;
	//			}
	//			else
	//			{
	//				return false;
	//			}
	//		}
	//	}
	//	public void blackenLowerLeft()
	//	{
	//		int width = getWidth();
	//		int height = getHeight();
	//		for (int x = 0; x < width; x++)
	//			for (int y = 0; y < height; y++)
	//				if (lowerLeft(x,y)==true)
	//				{
	//
	//					Pixel p = getPixel(x,y);
	//					p.setBlue(0);
	//					p.setRed(0);			
	//					p.setGreen(0);
	//				}
	//	}
	//
	//
	//	public boolean upperRightCorner(int x, int y)
	//	{
	//		{
	//			if(x > 0.5 * getWidth() && y < 0.5  * getHeight())
	//			{
	//				return true;
	//			}
	//			else
	//			{
	//				return false;
	//			}
	//		}
	//	}
	//	public void blackenUpperRightCorner()
	//	{
	//		int width = getWidth();
	//		int height = getHeight();
	//		for (int x = 0; x < width; x++)
	//			for (int y = 0; y < height; y++)
	//				if (upperRightCorner(x,y)==true)
	//				{
	//
	//					Pixel p = getPixel(x,y);
	//					p.setBlue(0);
	//					p.setRed(0);			
	//					p.setGreen(0);
	//				}
	//	}
	//
	//	public void gradient()
	//	{
	//		// Local variables used
	//		int width = getWidth();
	//		int height = getHeight();
	//		int row = 0;
	//		double factor = 0.0;
	//
	//		// loop through all the pixels
	//		for (int x = 0; x < width; x++)
	//			for (int y = 0; y < height; y++)
	//			{
	//				// get the current pixel
	//				Pixel point = getPixel(x, y);
	//				row = y;
	//
	//				// Only do the effect when in the top 3/4 of the image
	//				if (row < 0.75*height )
	//				{
	//					factor = (0.9/(0.75*height)*row)+0.1; // **truth** this line does not need to be fixed
	//
	//					// get the red value of the current pixel
	//					int redValue = (int) (point.getRed()*factor);
	//					int greenValue = (int) (point.getGreen()*factor);
	//					int blueValue = (int) (point.getBlue()*factor);
	//
	//					// set the red value of the current pixel to the new value
	//					point.setRed(redValue);
	//					point.setGreen(greenValue);
	//					point.setBlue(blueValue);
	//				}
	//			}
	//	}
	//	public void diagonal()
	//	{
	//		int width = getWidth();
	//		int height = getHeight();
	//		for (int x = 0; x < width; x++)
	//		{to
	//			for (int y = 0; y < height; y++)
	//			{
	//				if ((int)( height*x)/width > y)
	//				{
	//					Pixel p = getPixel(x, y);
	//					darkenPixel(p, 60);
	//				}
	//			}
	//		} 
	//	}
	public void darkenPixel(Pixel p, int howMuch )
	{

		int blue = p.getBlue();
		int red = p.getRed();
		int green = p.getGreen();
		p.setBlue(blue - howMuch);
		p.setRed(red - howMuch);			
		p.setGreen(green - howMuch);
	}

	//		public void soccerFix()
	//		{
	//			Pixel p;
	//			int width = getWidth();
	//			int height = getHeight();
	//			for (int x = 0; x < width; x++)
	//				for (int y = 0; y < height; y++)
	//				{
	//					p= getPixel(x,y);
	//					if(p.getRed()< p.getGreen() || p.getRed()< p.getBlue())
	//					{
	//						soften(p);
	//					}
	//				}
	//		}
	//		public void soften(Pixel p)
	//		{
	//	
	//			int blue = p.getBlue();
	//			int red = p.getRed();
	//			int green = p.getGreen();
	//			int avg = ((green+red+blue)/3);
	//			p.setBlue ((int)((avg + blue)/1.659));
	//			p.setRed ((int)((avg + red) /1.659));
	//			p.setGreen ((int)((avg + green) /1.659));
	//		}

}

//	public void myEffect(int m)
//	{
//		Pixel p;
//		int width = getWidth();
//		int height = getHeight();
//		for (int x = 0; x < width; x++)
//			for (int y = 0; y < m; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/4);
//				p.setRed(red/4);			
//				p.setGreen(green/4);
//			}
//		for (int x = 0; x < m; x++)
//			for (int y = m; y < height; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/4);
//				p.setRed(red/4);			
//				p.setGreen(green/4);
//			}
//		for (int x = m; x < width-m; x++)
//			for (int y = height-m; y < height; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/4);
//				p.setRed(red/4);			
//				p.setGreen(green/4);
//			}
//		for (int x = width-m; x < width; x++)
//			for (int y = m; y < height; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/4);
//				p.setRed(red/4);			
//				p.setGreen(green/4);
//			}
////
//		for (int x = 200; x < width - 200; x++)
//			for (int y = 200; y < m + 200; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				int a = ((green+red+blue)/3);
//				p.setBlue(blue=a);
//				p.setRed(red=a);
//				p.setGreen(green=a);
//			}
//		for (int x = 200; x < m + 200; x++)
//			for (int y = m + 200; y < height - 200; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				int a = ((green+red+blue)/3);
//				p.setBlue(blue=a);
//				p.setRed(red=a);
//				p.setGreen(green=a);
//			}
//		for (int x = m + 200; x < width-(m + 200); x++)
//			for (int y = height -(m + 200); y < height - 200; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				int a = ((green+red+blue)/3);
//				p.setBlue(blue=a);
//				p.setRed(red=a);
//				p.setGreen(green=a);
//			}
//		for (int x = width - (m + 200); x < width - 200; x++)
//			for (int y = m + 200; y < height - 200; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				int a = ((green+red+blue)/3);
//				p.setBlue(blue=a);
//				p.setRed(red=a);
//				p.setGreen(green=a);
//			}
//
//
//		for (int x = 300; x < width - 300; x++)
//			for (int y = 300; y < m + 300; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue * 2);
//				p.setRed(red*2);			
//				p.setGreen(green*2);
//			}
//		for (int x = 300; x < m + 300; x++)
//			for (int y = m + 300; y < height - 300; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue * 2);
//				p.setRed(red*2);			
//				p.setGreen(green*2);
//			}
//		for (int x = m + 300; x < width-(m + 300); x++)
//			for (int y = height -(m + 300); y < height - 300; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue * 2);
//				p.setRed(red*2);			
//				p.setGreen(green*2);
//			}
//		for (int x = width - (m + 300); x < width - 300; x++)
//			for (int y = m + 300; y < height - 300; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue * 2);
//				p.setRed(red*2);			
//				p.setGreen(green*2);
//			}
//		
//	
//		for (int x = 100; x < width - 100; x++)
//			for (int y = 100; y < m + 100; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/2);
//				p.setRed(red/2);			
//				p.setGreen(green/2);
//			}
//		for (int x = 100; x < m + 100; x++)
//			for (int y = m + 100; y < height - 100; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/2);
//				p.setRed(red/2);			
//				p.setGreen(green/2);
//			}
//		for (int x = m + 100; x < width-(m + 100); x++)
//			for (int y = height -(m + 100); y < height - 100; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/2);
//				p.setRed(red/2);			
//				p.setGreen(green/2);
//			}
//		for (int x = width - (m + 100); x < width - 100; x++)
//			for (int y = m + 100; y < height - 100; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/ 2);
//				p.setRed(red/2);			
//				p.setGreen(green/2);
//			}
//

//	public void grayAllButRed()
//	{
//		Pixel p;
//		int width = getWidth();
//		int height = getHeight();
//		for (int x = 0; x < width; x++)
//			for (int y = 0; y < height; y++)
//			{
//				p= getPixel(x,y);
//				if(p.getRed()< p.getGreen() || p.getRed()< p.getBlue())
//				{
//					grayscalePixel(p);
//				}
//			}
//	}
//	public void grayscalePixel(Pixel p)
//	{
//		int blue = p.getBlue();
//		int red = p.getRed();
//		int green = p.getGreen();
//		int avg = (green+red+blue)/3;
//		p.setBlue(avg);
//		p.setRed(avg);
//		p.setGreen(avg);
//	}

//	public void Saturate(Pixel p, double change)
//	{

//	public void frame(int m)
//	{
//		Pixel p;
//		int width = getWidth();
//		int height = getHeight();
//		for (int x = 0; x < width; x++)
//			for (int y = 0; y < m; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setBlue(blue/2);
//				p.setRed(red/2);			
//				p.setGreen(green/2);
//			}
//				for (int x = 0; x < m; x++)
//					for (int y = m; y < height; y++)
//					{
//						p= getPixel(x,y);
//						int blue = p.getBlue();
//						int red = p.getRed();
//						int green = p.getGreen();
//						p.setBlue(blue/2);
//						p.setRed(red/2);			
//						p.setGreen(green/2);
//					}
//				for (int x = m; x < width-m; x++)
//					for (int y = height-m; y < height; y++)
//					{
//						p= getPixel(x,y);
//						int blue = p.getBlue();
//						int red = p.getRed();
//						int green = p.getGreen();
//						p.setBlue(blue/2);
//						p.setRed(red/2);			
//						p.setGreen(green/2);
//					}
//				for (int x = width-m; x < width; x++)
//					for (int y = m; y < height; y++)
//					{
//						p= getPixel(x,y);
//						int blue = p.getBlue();
//						int red = p.getRed();
//						int green = p.getGreen();
//						p.setBlue(blue/2);
//						p.setRed(red/2);			
//						p.setGreen(green/2);
//					}

//	public void grayscale()
//	{
//		Pixel p;
//		int width = getWidth();
//		int height = getHeight();
//		for (int x = 0; x < width; x++)
//			for (int y = 0; y < height; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				int red = p.getRed();
//				int green = p.getGreen();
//				int m = ((green+red+blue)/3);
//				p.setBlue(blue=m);
//				p.setRed(red=m);
//				p.setGreen(green=m);
//			}


//		public void darken()
//		{
//			Pixel p;
//			int width = getWidth();
//			int height = getHeight();
//			for (int x = 0; x < width; x++)
//				for (int y = 0; y < height; y++)
//				{
//					p= getPixel(x,y);
//					int blue = p.getBlue();
//					int red = p.getRed();
//					int green = p.getGreen();
//					p.setBlue(blue/2);
//					p.setRed(red/2);			
//					p.setGreen(green/2);
//				}
//	public void swap()
//	{
//		Pixel p;
//				int width = getWidth();
//				int height = getHeight();
//				for (int x = 0; x < width; x++)
//					for (int y = 0; y < height; y++)
//					{
//						p= getPixel(x,y);
//						int blue = p.getBlue();
//						int red = p.getRed();
//						int green = p.getGreen();
//						p.setBlue(green);
//						p.setRed(blue);
//						p.setGreen(red);
//					}
//	
//		Picture wilma = fred.scale(1,1);
//		fred.explore();
//		fred.moreBlue();
//		fred.explore();
//		wilma.moreYellow();
//		wilma.explore();
//		//		Picture spike = new Picture(800,600);
//	}
//	public void moreBlue()
//	{
//		Pixel p;
//		int width = getWidth();
//		int height = getHeight();
//		for (int x = 0; x < width; x++)
//			for (int y = 0; y < height; y++)
//			{
//				p= getPixel(x,y);
//				int blue = p.getBlue();
//				p.setBlue(blue * 2);
//			}
//	}
//	public void moreYellow()
//	{
//		Pixel p;
//		int width = getWidth();
//		int height = getHeight();
//		for (int x = 0; x < width; x++)
//			for (int y = 0; y < height; y++)
//			{
//				p= getPixel(x,y);
//				int red = p.getRed();
//				int green = p.getGreen();
//				p.setRed(red * 2);
//				p.setGreen(green * 2);
//				int blue = p.getBlue();
//				p.setBlue(blue *-2);
//
//			}


//Chaos Game Below
//	public void drawDot(Color c, int x, int y)
//	{
//		Graphics g = getGraphics();
//		g.setColor(c);
//		g.fillRect(x, y, 1, 1);
//	}
//	public void drawChaos()
//	{
//		int x1 = 10;
//		int y1 = getHeight() - 10;
//		int x2 = getWidth() / 2;
//		int y2 = 10;
//		int x3 = getWidth() - 10;
//		int y3 = getHeight() - 10;
//		int x = getWidth() / 2;
//		int y = getHeight() / 2;
//		for (int i=0; i<150000; i++)
//		{
//			int mad = (int)(Math.random() *3 + 1);
//			if (mad == 1)
//			{
//				x = (x + x1) / 2;
//				y = (y + y1) / 2;
//				drawDot(Color.blue, x ,y);
//			}
//			if (mad == 2)
//			{
//				x = (x + x2) / 2;
//				y = (y + y2) / 2;
//				drawDot(Color.red, x, y);
//			}
//			if (mad == 3)
//			{
//				x = (x + x3) / 2;
//				y = (y + y3) / 2;
//				drawDot(Color.black, x, y); 
//			}
//		}
//	}



// this } is the end of class Picture, put all new methods before this
