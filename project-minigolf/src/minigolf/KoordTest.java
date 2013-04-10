package minigolf;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;


public class KoordTest extends JPanel implements MouseListener, MouseMotionListener{
	
	//FIELDS
	double xVel=0, yVel=0;
	double x,y,mouseX,mouseY,hideLn=0.008;
	public Rectangle screen, bounds;
	public JFrame frame;
	public MGTimerTask mgTask;
	public MGBall ball;
	private static boolean isMoving = false;
	
	//CONSTRUCTORS
	public KoordTest(){ 

		super();
		screen = new Rectangle(0,0,1000,800);
		bounds = new Rectangle(0,0,1000,800);
		ball = new MGBall();
		frame = new JFrame("MGKernel");
		mgTask = new MGTimerTask();	
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	//INNER CLASS TIMERTASK
	class MGTimerTask extends TimerTask{

		public void run() {
			ball.move();
			frame.repaint();
		}
	}

	//INNER CLASS BALL
	class MGBall{

		//FIELDS
		public int width, height;

		//CONSTRUCTORS
		public MGBall(){

			width=10;
			height=10;
		}

		//METHODS
		public void move(){

			//check if the ball is moving
//			boolean isMoving;
			if(xVel!=0. || yVel !=0.){isMoving = true;}
			else isMoving = false;
			
			//debug boolean
//			System.out.println(isMoving);
			
//			if(isMoving==false){mgTask.cancel();}
			
			
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
			
			//make the ball stop earlier
			if(Math.abs(xVel)<0.05){xVel=0.;}
			
			if(Math.abs(yVel)<0.05){yVel=0.;}
			
			//debug velocity
//			System.out.println(xVel+" "+yVel);
		}
	}
	
/*	//INNER CLASS HOLE
	class MGHole extends Rectangle {
			
		//CONSTRUCTORS
		public MGHole(int x, int y, int width, int height){
		// TODO INSERT CODE HERE
		}
			
		//METHODS
		public void paint(Graphics g){
		// TODO	INSERT CODE HERE
		}
			
		public void intersectionDetection(){
		// TODO	INSERT CODE HERE
		}
	}
*/
	//MAIN CLASS METHODS
	public void paintComponent(Graphics g){

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bounds = g.getClipBounds();
		Color fieldColor=new Color(0,128,0);
		g.setColor(fieldColor);
		g.fillRect(screen.x, screen.y, screen.width, screen.height);
		g.setColor(Color.RED);

		if  /* (( xVel<=-0 && yVel<=-0 && xVel>-hideLn && yVel>-hideLn) ||( xVel>=0 && yVel>=0 && xVel<hideLn && yVel<hideLn) ) */ (xVel==0){
			g.drawLine((int)(x+ball.width/2), (int)(y+ball.height/2), (int)(mouseX), (int)(mouseY));
		}
		g.setColor(Color.BLACK);
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		g.fillOval(950, 725, 15, 15);

		//draw ball
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, ball.width, ball.height);

	}

	//MOUSE EVENTS
	public void mouseClicked(MouseEvent e) {

		if(isMoving){e.consume();}
		
		else
		{
			int xclick = e.getX();
			int yclick = e.getY();

			xVel=((xclick-(x+ball.width/2))/10);
			yVel=((yclick-(y+ball.height/2))/10);

			//debug mouse click coords
//			System.out.println(xVel+" "+yVel);
		}
		
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseMoved(MouseEvent o) {
		// TODO Auto-generated method stub
		mouseX=o.getX();
		mouseY=o.getY();
		frame.repaint();
	}

	//MAIN METHOD
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
}