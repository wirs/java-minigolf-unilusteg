package minigolf;

import java.util.TimerTask;



public class Clock {

	//Felder
	
	int minutes, seconds, millis,paused = 0;
	String dspmillis;
	String dspSeconds;
	String dspMinutes;
	String Time2 ="";
	public ClockTimerTask clTask;
	java.util.Timer vgTimer;
	
	//Konstruktor
	
	public Clock(){
		
		super();
		clTask = new ClockTimerTask();
		vgTimer = new java.util.Timer();
		//start();
	}
	
	//METHODS
	public String toStringdsp(){
		Time2 ="";
		Time2= Time2 + dspMinutes + ":" + dspSeconds + ":" + dspmillis;
		return  Time2;
	}
	
	
public void start(){
		
		millis = 0;
		minutes = 0;
		seconds = 0;
		clTask = new ClockTimerTask();
		vgTimer = new java.util.Timer();
		vgTimer.scheduleAtFixedRate(clTask, 0, 1);
			
	}

public void resume(){
	paused=0;
	clTask = new ClockTimerTask();
	vgTimer = new java.util.Timer();
	vgTimer.scheduleAtFixedRate(clTask, 0, 1);
		
}
	
	public void stop(){	
		paused=1;
		vgTimer.cancel();
	}
	
	
	
	//INNER CLASS TIMERTASK
			class ClockTimerTask extends TimerTask{
				
				
				//Run Method of TimerTask
				public void run() {
					millis+=1;
					if (millis==1000){
						millis = 0;
						seconds+=1;
					}
					if (seconds == 60){
						seconds = 0;
						minutes+=1;
					}
					if (minutes == 60)
						minutes = 0;
						
					if (millis<100){
						if (millis<10)dspmillis="00"+millis;
						else
							dspmillis="0"+millis;
					}
					else
						dspmillis=Integer.toString(millis);
					
					if (seconds<10)dspSeconds="0"+seconds;
					else
						dspSeconds=Integer.toString(seconds);
					
					if (minutes<10)dspMinutes="0"+minutes;
					else
						dspMinutes = Integer.toString(minutes);
		
					//System.out.println(Clock.toStringdsp());
				}
			}
	
}
