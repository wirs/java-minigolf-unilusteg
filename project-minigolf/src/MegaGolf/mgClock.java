/*
 ==================
 = MEGAGOLF CLOCK =
 ==================
 */
package MegaGolf;

import java.util.TimerTask;

/**
 *
 * @author Luc Welter, Claude Ernzer, Daniel Warnimont
 */
public class mgClock {
    
    //FIELDS
    int minutes, seconds, millis;
    boolean paused = true;
    String dspmillis;
    String dspSeconds;
    String dspMinutes;
    String Time2 = "";
    java.util.Timer clkTimer;
    public ClockTimerTask clkTask;

    //CONSTRUCTORS
    public mgClock() {
        super();
        clkTask = new ClockTimerTask();
        clkTimer = new java.util.Timer();
    }

    //METHODS
    public String toStringdsp() {
        Time2 = "";
        Time2 = Time2 + dspMinutes + ":" + dspSeconds + ":" + dspmillis;
        return Time2;
    }

    private void startTimer(){
        clkTask = new ClockTimerTask();
        clkTimer = new java.util.Timer();
        clkTimer.scheduleAtFixedRate(clkTask, 0, 1);
    }
    
    private void stopTimer(){
        clkTimer.cancel();
    }
    
    public void start() {

        paused = false;
        millis = 0;
        minutes = 0;
        seconds = 0;
        startTimer();
    }

    public void resume() {
        paused = false;
        startTimer();
    }
    
    public void reset(){
    	paused = true;
    	millis = 0;
        minutes = 0;
        seconds = 0;
        stopTimer();
    }

    public void stop() {
        paused = true;
        stopTimer();
    }

    //INNER CLASS TIMERTASK
    class ClockTimerTask extends TimerTask {

        //Run Method of TimerTask
        public void run() {
            millis += 1;
            if (millis == 1000) {
                millis = 0;
                seconds += 1;
            }
            if (seconds == 60) {
                seconds = 0;
                minutes += 1;
            }
            if (minutes == 60) {
                minutes = 0;
            }

            if (millis < 100) {
                if (millis < 10) {
                    dspmillis = "00" + millis;
                } else {
                    dspmillis = "0" + millis;
                }
            } else {
                dspmillis = Integer.toString(millis);
            }

            if (seconds < 10) {
                dspSeconds = "0" + seconds;
            } else {
                dspSeconds = Integer.toString(seconds);
            }

            if (minutes < 10) {
                dspMinutes = "0" + minutes;
            } else {
                dspMinutes = Integer.toString(minutes);
            }

            //System.out.println(Clock.toStringdsp());
        }
    }
}
