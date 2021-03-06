package main;

import java.awt.image.*;

public class BlurFilter implements Java2DImageFilter {
	
	
	public BufferedImage processImage(BufferedImage image){
		float[] blurMatrix = {
				1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
				1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f,
				1.0f / 9.0f, 1.0f / 9.0f, 1.0f / 9.0f
		};
		
		BufferedImageOp blurFilter = new ConvolveOp(new Kernel(3,3, blurMatrix), ConvolveOp.EDGE_NO_OP,null);
		
		return blurFilter.filter(image, null);
	}

}
