package com.trungpt.flappybird;

import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by trungpt on 10/23/2014.
 */
public class Pipe extends Scrollable
{
    private Random r;
    private Rectangle skullUp, skullDown, barUp, barDown;
    public static final int VERTICAL_GAP = 45;
    public static final int SKULL_WIDTH = 24;
    public static final int SKULL_HEIGHT = 11;

    private float groundY;


    public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY)
    {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        skullUp = new Rectangle();
        skullDown = new Rectangle();
        barDown = new Rectangle();
        barUp = new Rectangle();
        this.groundY = groundY;
    }

    @Override
    public void update(float delta)
    {
        super.update(delta);
        barUp.set(position.x, position.y, width, height);
        barDown.set(position.x, position.y + height + VERTICAL_GAP, width, groundY - (position.y + height + VERTICAL_GAP));
        skullUp.set(position.x-(SKULL_WIDTH-width)/2,position.y+height-SKULL_HEIGHT,SKULL_WIDTH,SKULL_HEIGHT);
        skullDown.set(position.x-(SKULL_WIDTH-width)/2,barDown.y,SKULL_WIDTH,SKULL_HEIGHT);
    }

    @Override
    public void reset(float newX)
    {
        super.reset(newX);
        height = r.nextInt(90) + 15;
    }

    public Rectangle getSkullUp()
    {
        return skullUp;
    }

    public void setSkullUp(Rectangle skullUp)
    {
        this.skullUp = skullUp;
    }

    public Rectangle getSkullDown()
    {
        return skullDown;
    }

    public void setSkullDown(Rectangle skullDown)
    {
        this.skullDown = skullDown;
    }

    public Rectangle getBarUp()
    {
        return barUp;
    }

    public void setBarUp(Rectangle barUp)
    {
        this.barUp = barUp;
    }

    public Rectangle getBarDown()
    {
        return barDown;
    }

    public void setBarDown(Rectangle barDown)
    {
        this.barDown = barDown;
    }
}
