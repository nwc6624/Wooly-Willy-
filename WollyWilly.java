/*3- 10-17 
Noah Caulfield

Written for ISCS 215 

"I pledge that this program represents my own program
  code. I received help from (enter the names of others 
  that helped with the assignment, write no one if you received
  no help) in designing and debugging my program."
*************/


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class WollyWilly extends JPanel {
	
   private final static String PATH = "http://i.imgur.com/inTr5Ex.jpg"; // image via imgur
   private final ArrayList<Point> points = new ArrayList<>(); //array to hold points of paint 

   private BufferedImage backgroundImg;//willi

   private int newW;// declare values for height and width to be passed until actual image is found 
   private int newH;
 
   public WollyWilly() throws IOException {  
	   System.out.println("Willy's face was located.");
      URL imgUrl = new URL(PATH);
      BufferedImage bImg = ImageIO.read(imgUrl);
      newW = bImg.getWidth();
      newH = bImg.getHeight();
      backgroundImg = new BufferedImage(newW, newH,
            BufferedImage.TYPE_INT_ARGB);
      Graphics g = backgroundImg.getGraphics();
      g.drawImage(bImg, 0, 0, this);
      g.dispose();

      addMouseMotionListener(    //instantiate mouse motion listener 
    	         new MouseMotionAdapter() // anonymous inner class
    	         {  
    	            
    	            @Override
    	            public void mouseDragged(MouseEvent event)// stores mouse coordinates 
    	            {
    	               points.add(event.getPoint());// get point to add to array 
    	               repaint(); // repaint JFrame, set black 
    	            } 
    	         } 
    	      ); 
   }

   @Override
   protected void paintComponent(Graphics g) {

      super.paintComponent(g);
      if (backgroundImg != null) {   // not equal to black, as to not re-paint 
         g.drawImage(backgroundImg, 0, 0, this);
      }
      for (Point point : points)
          g.fillOval(point.x, point.y, 8, 8); // set brush size, 8,8 
    } 

   @Override
   public Dimension getSize() {     //get and return size 
      return new Dimension(newW, newH);
   }

  

   private static void createAndShowGui() {    // private method to build gui window 
      WollyWilly mainPanel = null;
      try {
         mainPanel = new WollyWilly();
      } catch (IOException e) {
    	  System.out.println("Error displaying window.");
         e.printStackTrace();
         System.exit(-1);
      }

      JFrame frame = new JFrame("~Wooly Willy~"); // title 
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit 
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setSize(380, 500); // limit size of actual window built since the .jpg is edited to fit 
      frame.setLocationByPlatform(true);
      frame.add(new JLabel("Click the mouse to draw on his face!"), BorderLayout.SOUTH); //instructions to appear at the bottom 
      frame.setVisible(true);
   }

   
   
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {   //instantiate new runnable object 
         public void run() {
            createAndShowGui(); //run method 
         }
      });
   }
    }

