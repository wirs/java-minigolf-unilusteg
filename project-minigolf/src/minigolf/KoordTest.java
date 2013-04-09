package minigolf;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.lang.Math;



public class KoordTest extends JPanel implements MouseListener{
	double xVel=0, yVel=0;
	public Rectangle screen, bounds;
	public JFrame frame;
	public MGTimerTask mgTask;
	public MGBall ball;
	
	public KoordTest(){ 
		
		super();
		screen = new Rectangle(0,0,600,400);
		bounds = new Rectangle(0,0,600,400);
		ball = new MGBall();
		frame = new JFrame("MGKernel");
		mgTask = new MGTimerTask();	
		this.addMouseListener(this);
	}
	
	class MGTimerTask extends TimerTask{
		
		public void run() {
			ball.move();
			frame.repaint();
		}
	}
	
	class MGBall{
		
		public int width, height;
		double x, y;
		
		public MGBall(){
			
			x=50;
			y=50;
			width=10;
			height=10;
			
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

	    java.util.Timer 
	    vgTimer = new java.util.Timer();  // Create a Timer object
	    KoordTest panel = new KoordTest(); 
	    
	    panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    panel.frame.setSize(panel.screen.width, panel.screen.height);

	    panel.frame.setContentPane(panel); 
	    panel.frame.setVisible(true);
	    
	    // Set up a timer to do the vgTask regularly.
	    vgTimer.schedule(panel.mgTask, 0, 20);
	}
 

	
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 int x = e.getX();
	        int y = e.getY();
	    System.out.println(x +" "+ y);
	    xVel=x;
		yVel=y;
	    
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
		
}