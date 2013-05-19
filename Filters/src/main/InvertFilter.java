package main;

import java.awt.image.*;

public class InvertFilter implements Java2DImageFilter {
	
	public BufferedImage processImage(BufferedImage image){
		byte[] invertArray = new byte[256];
		
		for (int counter = 0; counter < 256; counter++)
			invertArray[counter] = (byte)(255-counter);
		BufferedImageOp invertFilter = new LookupOp(new ByteLookupTable(0, invertArray),null);
		return invertFilter.filter(image,null);
		
	}
	
	

}
