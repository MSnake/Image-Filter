package main;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import  javax.imageio.*;
import  javax.swing.filechooser.FileFilter;
import javax.swing.text.Position;

import javax.imageio.ImageIO;
import javax.swing.*;


public class Java2DExample extends JFrame {
	
	private JMenu filterMenu;
	private JMenu fileMenu;
	private JMenu loadMenu;
	private ImagePanel imagePanel;
	private String fileName;
	boolean loading=false;
	private Image image;

	//filters
	
	private Java2DImageFilter invertFilter;
	private Java2DImageFilter sharpenFilter;
	private Java2DImageFilter blurFilter;
	private Java2DImageFilter colorFilter;
	
	
	
	public Java2DExample() {
		super("Image Reader");
		setSize(500,500);
		blurFilter = new BlurFilter();
		sharpenFilter = new SharpenFilter();
		invertFilter = new InvertFilter();
		colorFilter = new ColorFilter();
		imagePanel = new ImagePanel("example2.png");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		filterMenu = new JMenu("Image Filters");
		filterMenu.setMnemonic('I');

		JMenuItem originalMenuItem = new JMenuItem("Исходное изображение");
		originalMenuItem.setMnemonic('O');
		fileMenu = new  JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        openItem.addActionListener(
        		new ActionListener() {
					public void actionPerformed(ActionEvent event) {
			         
						JFileChooser openImg= new  JFileChooser();
			              int  result = openImg.showOpenDialog(null);
			               if(result==JFileChooser.APPROVE_OPTION)
			                {
			                  try
			                  {
			                      // при выборе изображения подстраиваем размеры формы
			                      // и панели под размеры данного изображения
			                	   fileName = openImg.getSelectedFile().getAbsolutePath();
			                	   imagePanel.changeImage(fileName);
			                    } 
			                  catch (Exception ex) {
			                	  System.out.println(ex);
			                    }
			                } 
					}	
				});

        
		originalMenuItem.addActionListener(
			new ActionListener() {
			public void actionPerformed(ActionEvent action){
				imagePanel.displayOriginalImage();
				}
			}
		);

		JMenuItem invertMenuItem = createMenuItem("Инвертиировать цвета", 'I', invertFilter);
		JMenuItem sharpenMenuItem = createMenuItem("Шум", 'S', sharpenFilter);
		JMenuItem blurMenuItem = createMenuItem("Размытие", 'B', blurFilter);
		JMenuItem changeColorMenuItem = createMenuItem("Коррекция цвета", 'C', colorFilter);
		
		filterMenu.add(originalMenuItem);
		filterMenu.add(invertMenuItem);
		filterMenu.add(sharpenMenuItem);
		filterMenu.add(blurMenuItem);
		filterMenu.add(changeColorMenuItem);
		
		menuBar.add(filterMenu);
		
		getContentPane().add(imagePanel,BorderLayout.CENTER);
		
}
	
	public  JMenuItem createMenuItem(String menuItemName, char mnemotic, final Java2DImageFilter filter){
	    JMenuItem menuItem = new JMenuItem(menuItemName);
		menuItem.setMnemonic(mnemotic);
		menuItem.addActionListener(
			new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent action) {
					imagePanel.applyFilter(filter);
				}
			});
		return menuItem;
	}

	
	public static void main(String args[]) {
		Java2DExample application = new Java2DExample();
		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
		application.pack();
		application.setVisible(true);
	}

}
