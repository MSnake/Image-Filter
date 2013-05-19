package main;

import java.awt.image.*;

public class SharpenFilter implements Java2DImageFilter {
	
	public BufferedImage processImage(BufferedImage image){
		float[] sharpMatrix = {
				0.0f, -1.0f, 0.0f,
				-1.0f, 5.0f, -1.0f,
				0.0f, -1.0f, 0.0f
	     };
		
		BufferedImageOp sharpenFilter = new ConvolveOp(new Kernel(3,3, sharpMatrix),ConvolveOp.EDGE_NO_OP,null);
		return sharpenFilter.filter(image, null);
		
	}

}
