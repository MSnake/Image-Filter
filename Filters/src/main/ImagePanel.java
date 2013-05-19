 package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;


public class ImagePanel extends JPanel {
	 /**
	  *  ласс дл€ отображени€ изображений....
	  */
	private BufferedImage displayImage;
	private BufferedImage originalImage;
	private Image image;
	BufferedImage imag;
	private int xImg, yImg, wImg, hImg; // переменные в которых хран€тс€ координаты части картинки которую над показывать.
	// при чем wImg, hImg - это не длина части картинки которую над показать,
	// а координата второго угла пр€моугольника.
	private double zoom = 1;
	private Rectangle2D rect = new Rectangle2D.Double();
	private static Image img = null;
	private Point2D press = new Point2D.Double(0, 0);
	private boolean pressedBtn = false;
	Toolkit toolkit;
    MediaTracker tracker;
	
	
	//конструктор
	public ImagePanel(String fName) {
    toolkit = Toolkit.getDefaultToolkit();
    tracker = new MediaTracker(this);
	Image image = toolkit.getImage(fName);
	tracker.addImage(image, 0);
	try {
		tracker.waitForAll();
	}
	catch(InterruptedException interruptedException){
		interruptedException.printStackTrace();
	}
	originalImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
	displayImage = originalImage;
	
	Graphics2D graphics = displayImage.createGraphics();
	graphics.drawImage(image, null, null);
	}
	public void changeImage(String fName) {
		Image image = toolkit.getImage(fName);
		tracker.addImage(image, 0);
		try {
			tracker.waitForAll();
		}
		catch(InterruptedException interruptedException){
			interruptedException.printStackTrace();
		}
		originalImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		displayImage = originalImage;
		
		Graphics2D graphics = displayImage.createGraphics();
		graphics.drawImage(image, null, null);
		this.repaint();
	}
	
	//¬ыделение части изображени€
	/*
	this.addComponentListener(new ComponentAdapter() {
		//перерисовывает картинку в случае изменени€ размеров фрейма...
		@Override
		public void componentResized(ComponentEvent ce) {
		repaint();
		}
	});
		
		this.addMouseWheelListener(new MouseWheelListener() {
			//добавл€ем обработчик событий который слушает колЄсико мышки, ну и измен€ет размер картинки
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
			if (e.getWheelRotation() == 1) {
			zoom += 0.1;
			} else {
			zoom -= 0.1;
			}
			repaint();
			}
		});
		
		this.addMouseListener(new MouseAdapter() {
			//записываем первую точку где кликаетс€ мышкой и начинаетс€ Drag&Dpop
				@Override
				public void mousePressed(MouseEvent e) {
				press = e.getPoint();
				pressedBtn = true;
			}
			
			@Override
			public void mouseReleased(MouseEvent e) {
				pressedBtn = false;
				if ((int) rect.getWidth() != 0 && (int) rect.getHeight() != 0) {
				xImg += rect.getX() / zoom;
				yImg += rect.getY() / zoom;
				if ((rect.getX() + rect.getWidth()) <= wImg) {
				wImg = (int) (rect.getWidth() / zoom);
				} else {
				wImg = (int)((wImg - (int)rect.getX()) / zoom);
				}
				if ((rect.getY() + rect.getHeight()) <= hImg) {
				hImg = (int) (rect.getHeight() / zoom);
				} else {
				hImg = (int)((hImg - (int)rect.getY()) / zoom);
				}
				zoom = 1;
				repaint();
				}
				}
			});
		this.addMouseMotionListener(new MouseAdapter() {
			@Override
			//ќтрисовываем пр€моугольник, который показывает что выдел€етс€.
			public void mouseDragged(MouseEvent e) {
			double x, y, w, h;
			 
			if (e.getX() > (int) press.getX()) {
			x = press.getX();
			w = (double) e.getX() - press.getX();
			} else {
			x = (double) e.getX();
			w = press.getX() - (double) e.getX();
			}
			 
			if (e.getY() > (int) press.getY()) {
			y = press.getY();
			h = (double) e.getY() - press.getY();
			} else {
			y = (double) e.getY();
			h = press.getY() - (double) e.getY();
			}
			setRect(new Rectangle2D.Double(x, y, w, h));
			}
		});
		}
		 
		public void setRect(Rectangle2D rect) {
			this.rect = rect;
			repaint();
		}
		 
		@Override
		public void paintComponent(Graphics g) {
		super.paintComponent(g);
 
			Graphics2D g2 = (Graphics2D) g;
			if (img != null) {
			g2.drawImage(img, 0, 0, (int)(wImg*zoom), (int)(hImg*zoom),
			xImg,
			yImg,
			xImg + wImg,
			yImg + hImg,
			null);
			if (pressedBtn) {
			g2.draw(rect);
			}
			}
		}
 
		public void setImage(String path) {
		try {
			img = ImageIO.read(new File(path));
			zoom = 1;
			xImg = 0;
			yImg = 0;
			wImg = img.getWidth(ImagePanel.this);
			hImg = img.getHeight(ImagePanel.this);
			repaint();
		} 	catch (IOException e) {
			e.printStackTrace();
		} */
	
//	}  // конец конструктора

	//приминение фильтров
	
	public void applyFilter( Java2DImageFilter filter ) {
		displayImage = filter.processImage(displayImage);
		repaint();
	}
	
	public void displayOriginalImage() {
		displayImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = displayImage.createGraphics();
		graphics.drawImage(originalImage, null, null);
		repaint();
		}
	public void paintComponent(Graphics g) {
		if(displayImage==null)
	       {
			displayImage = new  BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D d2 = (Graphics2D) displayImage.createGraphics();
            d2.setColor(Color.white);
            d2.fillRect(0, 0, this.getWidth(), this.getHeight());
	       }
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(displayImage, 0, 0, null);
        g.drawImage(imag, 0, 0,this); 
		}

	public Dimension getPreferredSize(){
		return new Dimension(displayImage.getWidth(), displayImage.getHeight());
		}
	
	public Dimension getMinimumSize(){
		return getPreferredSize();
		}
	
	
}
