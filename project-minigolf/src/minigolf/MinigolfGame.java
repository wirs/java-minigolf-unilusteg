package minigolf;
import java.util.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;


public class MinigolfGame extends JPanel implements MouseListener, MouseMotionListener{

	//FIELDS
	int clicks=0;
	
	double xVel=0, yVel=0;
	double x,y,mouseX,mouseY;
	
	boolean isMoving = false;
	boolean hasBall = false;
	boolean started = false;
	boolean drawHelperLine = false;
	
	public Rectangle screen, bounds;
	public JFrame frame;
	public MGTimerTask mgTask;
	public MGBall ball;
	public MGHole goal;
	
	//CONSTRUCTORS
	public MinigolfGame(){ 

		super();
		screen = new Rectangle(0,0,800,600);
		bounds = new Rectangle(0,0,800,600);
		ball = new MGBall();
		mgTask = new MGTimerTask();	
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.validate();
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
		
		//FIELDS
		int x,y,width,height;
		
		//CONSTRUCTORS
		public MGHole(int x, int y, int width, int height,Graphics g){
			this.x=x;
			this.y=y;
			this.width=width;
			this.height=height;
			g.setColor(Color.BLACK);
			g.fillOval(x, y, width, height);
		}
	}
	
	//INNER CLASS WALL
	class MGWall extends Rectangle{
		
		//FIELDS
		int x, y, width, height;
		
		//CONSTRUCTORS
		public MGWall(int x,int y, int width, int height, Graphics g){
			this.x = x;
			this.y = y;
			this.width = width;
			this.height= height;
			g.setColor(Color.lightGray);
			g.fillRect(x, y, width, height);
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

			//tests if the ball is moving
			if(xVel!=0. || yVel !=0.){
				isMoving = true;
				drawHelperLine = false;
			}
			else{
				isMoving = false;
				drawHelperLine = true;
			}

			//friction factor
			xVel=xVel*0.97;
			yVel=yVel*0.97;

			//determine new coordinates
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

			//System.out.println("x:"+x + " "+ "y:"+y);
			if(x > 750 && x < 750+5 && y > 550 && y < 550+5){
				xVel=0;
				yVel=0; 
				hasBall=true;
				drawHelperLine = false;
			}

			//make the ball stop earlier
			if(Math.abs(xVel)<0.05){xVel=0.;}

			if(Math.abs(yVel)<0.05){yVel=0.;}
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

		//draw line helper
		g.setColor(Color.RED);
		if (drawHelperLine){g.drawLine((int)(x+ball.width/2), (int)(y+ball.height/2), (int)(mouseX), (int)(mouseY));}
		
		//crosshair cursor
		g.setColor(Color.BLACK);
		setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

		//draw ball
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, ball.width, ball.height);
		
/*		//draw walls
		MGWall wall1 = new MGWall(0,0,800,20,g);
		MGWall wall2 = new MGWall(0,580,800,20,g);
		MGWall wall3 = new MGWall(0,20,20,560,g);
		MGWall wall4 = new MGWall(780,20,20,560,g);*/
	}

	//MOUSE EVENTS
	public void mouseClicked(MouseEvent e) {

		if(!started){e.consume();}
		else{
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
		if(!isMoving)repaint();
	}
}