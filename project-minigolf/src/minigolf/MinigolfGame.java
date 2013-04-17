package minigolf;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;


public class MinigolfGame extends JPanel implements MouseListener, MouseMotionListener{

	//FIELDS
	double xVel=0, yVel=0;
	double x,y,mouseX,mouseY;
	int clicks=0;
	public Rectangle screen, bounds;
	public JFrame frame;
	public MGTimerTask mgTask;
	public MGBall ball;
	public MGHole goal;
	public static boolean isMoving = false;
	public static boolean hasBall = false;
	Clock cl;

	//CONSTRUCTORS
	public MinigolfGame(){ 

		super();
		screen = new Rectangle(0,0,800,600);
		bounds = new Rectangle(0,0,800,600);
		ball = new MGBall();
		//frame = new JFrame("Super Minigolf");
		mgTask = new MGTimerTask();	
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		cl = new Clock(50,50);
		//this.add(cl);
		this.validate();
		/*java.util.Timer 
		vgTimer = new java.util.Timer();  // Create a Timer object
		vgTimer.schedule(mgTask, 0, 20);*/
		
		
	}

	//INNER CLASS TIMERTASK
	class MGTimerTask extends TimerTask{

		public void run() {
			ball.move();
			repaint();
		}
	}

	//INNER CLASS HOLE
	class MGHole extends Rectangle {
		int xHole,yHole;
		//CONSTRUCTORS
		public MGHole(int xh, int yh, int width, int height,Graphics g){
			g.setColor(Color.BLACK);
			g.fillOval(xh, yh, width, height);
			xHole=xh;
			yHole=yh;
			//System.out.println(xHole + " " + yHole);
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

			//			System.out.println("x:"+x + " "+ "y:"+y);
			if(x > 750 && x < 750+5 && y > 550 && y < 550+5){
				xVel=0;
				yVel=0; 
				hasBall=true;
				cl.stop();
			}




			//make the ball stop earlier
			if(Math.abs(xVel)<0.05){xVel=0.;}

			if(Math.abs(yVel)<0.05){yVel=0.;}

			//debug velocity
			//			System.out.println(xVel+" "+yVel);
		}
	}



	//MAIN CLASS METHODS
	public void paintComponent(Graphics g){

		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		bounds = g.getClipBounds();
		Color fieldColor=new Color(0,128,0);
		g.setColor(fieldColor);
		g.fillRect(screen.x, screen.y, screen.width, screen.height);
		goal = new MGHole(750,550,15,15,g);

		g.setColor(Color.RED);		
		if(isMoving==false){
			g.drawLine((int)(x+ball.width/2), (int)(y+ball.height/2), (int)(mouseX), (int)(mouseY));
		}

		g.setColor(Color.BLACK);
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
		//g.fillOval(950, 725, 15, 15);

		//draw ball
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, ball.width, ball.height);
		
		//display Clock
		cl.toStringdsp(740, 10, g);
		

	}

	//MOUSE EVENTS
	public void mouseClicked(MouseEvent e) {

		if(hasBall){e.consume();}
		else{
			if(isMoving){e.consume();}

			else
			{
				int xclick = e.getX();
				int yclick = e.getY();

				xVel=((xclick-(x+ball.width/2))/10);
				yVel=((yclick-(y+ball.height/2))/10);
				clicks+=1;

				//debug mouse click coords
				//			System.out.println(xVel+" "+yVel);
				//			System.out.println(clicks);
			}
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
		//System.out.println("x:"+ mouseX + " "+ "y:"+mouseY);
		if(isMoving==false)repaint();

	}

	//MAIN METHOD
	/*public static void main(String arg[]){
	int frameHeight=0;
	int frameWidth=0;
	
		java.util.Timer 
		vgTimer = new java.util.Timer();  // Create a Timer object
		MinigolfGame panel = new MinigolfGame(); 
		panel.frame.setContentPane(panel);
       panel.frame.add(panel, BorderLayout.CENTER);

        JButton button = new JButton("Button 1 (PAGE_START)");
        panel.frame.add(button, BorderLayout.PAGE_START);
        button = new JButton("Button 1 (PAGE_START)");
        panel.frame.add(button, BorderLayout.LINE_START);
        
		panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//
		panel.frame.setVisible(true);

		//-5 Because jre7 has a bug with set.resizable(false)
		int brdT=panel.frame.getInsets().top-5;
		int brdB=panel.frame.getInsets().bottom-5;
		int brdL=panel.frame.getInsets().left-5;
		int brdR=panel.frame.getInsets().right-5;
		frameHeight= brdT+brdB+panel.screen.height;
		frameWidth= brdL+brdR+panel.screen.width;
	
		
		panel.frame.setSize(frameWidth,frameHeight );
		panel.frame.setResizable(false);
		
		// Set up a timer to do the vgTask regularly.
		java.util.Timer 
		vgTimer = new java.util.Timer();  // Create a Timer object
		vgTimer.schedule(panel.mgTask, 0, 20);
	}*/
}