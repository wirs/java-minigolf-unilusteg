package minigolf;
import java.util.*;
import java.awt.*;

import javax.swing.*;
import java.lang.Math;

public class MGKernel extends JPanel{
	
	public Rectangle screen, bounds;
	public JFrame frame;
	public MGTimerTask mgTask;
	public MGBall ball;
	
	public MGKernel(){ 
		
		super();
		screen = new Rectangle(0,0,600,400);
		bounds = new Rectangle(0,0,600,400);
		ball = new MGBall();
		frame = new JFrame("MGKernel");
		mgTask = new MGTimerTask();	
	}
	
	class MGTimerTask extends TimerTask{
		
		public void run() {
			ball.move();
			frame.repaint();
		}
	}
	
	class MGBall{
		
		public int width, height;
		double xVel, yVel,x, y;
		
		public MGBall(){
			
			x=50;
			y=50;
			width=10;
			height=10;
			xVel=10;
			yVel=10;
		}
		
		public void move(){
			
			xVel=xVel*0.97;
			yVel=yVel*0.97;
			
			x=x+xVel;
			y=y+yVel;
			if (x>(bounds.width-width)){
				xVel = -xVel;
				x = bounds.width-width;
			}
			if(y>(bounds.height-height)){
				yVel = -yVel;
				y = bounds.height-height;
			}
			if (x <= 0) { xVel = -xVel; x = 0; }
			if (y <= 0) { yVel = -yVel; y = 0; }
		}
	}
	
	public void paintComponent(Graphics g){
	
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bounds = g.getClipBounds();
		Color fieldColor=new Color(0,128,0);
		g.setColor(fieldColor);
	    g.fillRect(screen.x, screen.y, screen.width, screen.height);
	    g.setColor(Color.yellow);
	    g.fillOval((int)ball.x, (int)ball.y, ball.width, ball.height);
	}
	
	public static void main(String arg[]){

	    java.util.Timer vgTimer = new java.util.Timer();  // Create a Timer object
	    MGKernel panel = new MGKernel(); 
	    
	    panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    panel.frame.setSize(panel.screen.width, panel.screen.height);

	    panel.frame.setContentPane(panel); 
	    panel.frame.setVisible(true);

	    // Set up a timer to do the vgTask regularly.
	    vgTimer.schedule(panel.mgTask, 0, 20);
	}
}
