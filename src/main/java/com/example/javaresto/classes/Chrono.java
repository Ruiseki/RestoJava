package com.example.javaresto.classes;

import javafx.application.Platform;
import javafx.scene.control.Label;

public class Chrono
{
    private long value = 0, referenceTime;
    private boolean pause = false, forceEnd = false, threadMode, elapsed = true;
    private Thread task;
    private Label chronoLabel;

    public Chrono(Label label)
    {
        chronoLabel = label;
    }
    
    public void startSync(long wallOfTheEnd, boolean reverse)
    {
        elapsed = false;
        threadMode = false;
        boolean end = false;
        value = reverse ? wallOfTheEnd : 0;
        referenceTime = System.currentTimeMillis();
        String oldTime = getTimeMinSec();

        do
        {
            if(!pause)
            {
                if(!reverse)
                    value += Math.abs( referenceTime - System.currentTimeMillis() );
                else
                    value += Math.abs( referenceTime - System.currentTimeMillis() ) * -1;
            }

            referenceTime = System.currentTimeMillis();
            if (displayTimeEachSecond(oldTime));
                oldTime = getTimeMinSec();

            end =   forceEnd ? true :
                    wallOfTheEnd == 0 ? false :
                    (!reverse && value >= wallOfTheEnd) || (reverse && value <= 0) ? true : false;

        } while (!end);
        forceEnd = false;
        elapsed = true;
        System.out.println("Time limit reached");
    }
    
    public void startSync()
    {
        startSync(0, false);
    }

    public void startThreaded(long wallOfTheEnd, boolean reverse)
    {
        threadMode = true;
        task = new Thread(() -> startSync(wallOfTheEnd, reverse));
        task.start();
    }

    public void startThreaded()
    {
        startThreaded(0, false);
    }
    
    private boolean displayTimeEachSecond(String oldTime)
    {
        String time = getTimeMinSec();

        if (!time.equals(oldTime))
        {
            Platform.runLater(() -> chronoLabel.setText(time));
            System.out.println(time);
        }

        else return false;

        return true;
    }

    public boolean isTimeElapsed()
    {
        return elapsed;
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

    public long getTimeMs()
    {
        return value;
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
