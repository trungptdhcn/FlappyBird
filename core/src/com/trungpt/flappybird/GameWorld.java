package com.trungpt.flappybird;

import java.awt.*;

/**
 * Created by trungpt on 10/22/2014.
 */
public class GameWorld
{
    private Bird bird;
    private ScrollHandler scrollHandler;

    public GameWorld(int midPointY)
    {
        this.bird = new Bird(33, midPointY - 5, 17, 12);
        scrollHandler = new ScrollHandler(midPointY + 66);
    }

    public void update(float delta)
    {
        bird.update(delta);
        scrollHandler.update(delta);
    }

    public Bird getBird()
    {
        return bird;
    }

    public void setBird(Bird bird)
    {
        this.bird = bird;
    }

    public ScrollHandler getScrollHandler()
    {
        return scrollHandler;
    }

    public void setScrollHandler(ScrollHandler scrollHandler)
    {
        this.scrollHandler = scrollHandler;
    }
}
