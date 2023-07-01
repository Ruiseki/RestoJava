package com.example.javaresto.classes;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Chrono
{
    private long value = 0;
    private boolean pause = false, forceEnd = false;
    private Label chronoLabel;

    public Chrono(Label label)
    {
        chronoLabel = label;
    }
    
    public void startSync(long wallOfTheEnd, boolean reverse)
    {
        boolean end;
        value = reverse ? wallOfTheEnd : 0;
        long referenceTime = System.currentTimeMillis();
        String oldTime = getTimeMinSec();
        boolean serviceLocked = false;

        do
        {
            if(!pause)
            {
                if(!reverse)
                    value += Math.abs( referenceTime - System.currentTimeMillis() );
                else
                    value += Math.abs( referenceTime - System.currentTimeMillis() ) * -1;
            }

            // lock the service when 15min is remaining
            if(!serviceLocked && reverse)
            {
                if(value <= 15 * 60 * 1000)
                {
                    serviceLocked = true;
                    // Order.lockService();
                }
            }

            referenceTime = System.currentTimeMillis();
            if (displayTimeEachSecond(oldTime))
                oldTime = getTimeMinSec();

            end = forceEnd || (wallOfTheEnd != 0 && ((!reverse && value >= wallOfTheEnd) || (reverse && value <= 0)));

        } while (!end);
        forceEnd = false;
        System.out.println("Time limit reached");
    }

    public void startThreaded(long wallOfTheEnd, boolean reverse)
    {
        Thread task = new Thread(() -> startSync(wallOfTheEnd, reverse));
        task.start();
    }

    private boolean displayTimeEachSecond(String oldTime)
    {
        String time = getTimeMinSec();

        if (!time.equals(oldTime))
            Platform.runLater(() -> chronoLabel.setText(time));

        else return false;

        return true;
    }

    public void pause()
    {
        pause = true;
    }
    public void resume()
    {
        pause = false;
    }

    public void stop()
    {
        forceEnd = true;
        value = 0;
    }

    public String getTimeMinSec()
    {
        long minutes, seconds;
        String time;

        minutes = value / 60000;
        seconds = value / 1000;

        while(seconds >= 60) seconds -= 60;

        time = minutes < 10 ? "0" + minutes : Long.toString(minutes);
        time += ":";
        time += seconds < 10 ? "0" + seconds : Long.toString(seconds);

        return time;
    }

    public boolean isPaused()
    {
        return pause;
    }
}
