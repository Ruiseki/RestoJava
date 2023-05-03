package com.example.javaresto.classes;

public class Chrono
{
    private long value = 0, referenceTime;
    private boolean pause = false, forceEnd = false;

    public Chrono() {}
    
    public void start(long wallOfTheEnd, boolean reverse)
    {
        boolean end = false;
        value = reverse ? wallOfTheEnd : 0;
        referenceTime = System.currentTimeMillis();

        do
        {
            if(!pause)
            {
                if(!reverse)
                    value += Math.abs( referenceTime - System.currentTimeMillis() );
                else
                    value -= Math.abs( referenceTime - System.currentTimeMillis() ) * -1;
            }

            referenceTime = System.currentTimeMillis();

            System.out.println(value);

            end =   forceEnd ? true :
                    wallOfTheEnd == 0 ? false :
                    (!reverse && value >= wallOfTheEnd) || (reverse && value <= wallOfTheEnd) ? true : false;

        } while (!end);
    }

    public void start()
    {
        start(0, false);
    }

    public void pause() { pause = true; }
    public void resume() { pause = false; }

    public void stop()
    {
        forceEnd = true;
        value = 0;
    }

    public long getTimeMs()
    {
        return value;
    }
}
